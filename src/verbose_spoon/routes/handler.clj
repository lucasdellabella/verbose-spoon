(ns verbose-spoon.routes.handler
  ;;(:alias 'verbose-spoon.views 'views)
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [ring.util.response :refer [response redirect]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.reload :refer [wrap-reload]]
            ;[verbose-spoon.model.core :refer [apply-project]]
            [verbose-spoon.model.core :refer [insert-course
                                              insert-project
                                              update-profile
                                              update-apply-project
                                              creds-correct?
                                              attempt-to-register
                                              accept-reject-application
                                              is-admin?]]
            [verbose-spoon.model.queries :refer [populate-main-query]]
            [verbose-spoon.views [registration :as registration]
                                 [login :as login]
                                 [main :as main]
                                 [me :as me]
                                 [edit-profile :as edit-profile]
                                 [my-application :as my-application]
                                 [view-apply-project :as view-apply-project]
                                 [view-course :as view-course]
                                 [choose-functionality :as choose-functionality]
                                 [view-applications :as view-applications]
                                 [view-project-report :as view-project-report]
                                 [view-application-report :as view-application-report]
                                 [add-project :as add-project]
                                 [add-course :as add-course]]))

(def current-user (atom nil))
(def admin-bool? (atom false))
(def admin-pages (atom (set ["/view-project-report" "/choose-functionality" "/view-applications" "/view-application-report" "/add-project" "/add-course"])))

;; Later we could structure routes more restfully
(defroutes routes
  (GET "/login" [] (login/page)) ;;Put your login page here
  (GET "/registration" [] (registration/page))
  (GET "/main" req (main/page (:params req)))
  (GET "/me" [] (me/page))
  (GET "/edit-profile" [] (edit-profile/page @current-user))
  (GET "/my-application" [] (my-application/page @current-user))
  (GET "/view-apply-project/:project_name" [project_name] (view-apply-project/page project_name))
  (GET "/view-course/:coursenum" [coursenum] (view-course/page coursenum))
  ;; The rest are the admin routes
  (GET "/view-project-report" [] (view-project-report/page))
  (GET "/choose-functionality" [] (choose-functionality/page))
  (GET "/view-applications" [] (view-applications/page))
  (GET "/view-application-report" [] (view-application-report/page))
  (GET "/add-project" [] (add-project/page))
  (GET "/add-course" [] (add-course/page))
  ;; POST routes for the pages with forms
  ;; 1. Validate inputs 2. Run appropriate query
  (POST "/add-course" req (do (insert-course (:params req))
                                        (redirect "/choose-functionality")))
  (POST "/add-project" req (do (insert-project (:params req))
                               (redirect "/choose-functionality")))
  (POST "/edit-profile" req (do (update-profile (:params req) @current-user) (redirect "/edit-profile")))
  ;(POST "/main" req ())
  (POST "/view-apply-project/:project_name" req (do (update-apply-project (-> req :route-params :project_name) @current-user)))
  (POST "/view-applications" req (do (accept-reject-application (:params req))
                                     (redirect "/view-applications")))
  ;; if attempt to register fails, don't redirect to login
  (POST "/registration" req (do (attempt-to-register (:params req))))
  (POST "/login" req (let [username (-> req :params (get "username"))
                           password (-> req :params (get "password"))]
                        (if (creds-correct? username password)
                          (do (reset! current-user username)
                            (if (is-admin? username)
                              (do
                                (reset! admin-bool? true)
                                (redirect "/choose-functionality"))
                              (do
                                (reset! admin-bool? false)
                                (redirect "/main"))))
                          (response "Incorrect username password combination"))))
                         ;; should probably notify)))
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))

(defn wrap-auth [handler]
  (fn [request]
    (cond
      (or (-> request :uri (= "/registration"))
            (-> request :uri (= "/login")))(handler request)
      (and (some? @current-user)
          @admin-bool?
          (@admin-pages (:uri request)))(handler request)
      (and (some? @current-user)
          (not @admin-bool?)
          (not (@admin-pages (:uri request))))(handler request)
      :else (response "Access Denied: Please log in to access this page"))))


(def handler
  (-> #'routes
      wrap-reload
      wrap-params
      wrap-auth))

(defonce server (jetty/run-jetty handler {:port 8080 :join? false}))
