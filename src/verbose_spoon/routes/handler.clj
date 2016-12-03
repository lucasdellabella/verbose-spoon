(ns verbose-spoon.routes.handler
  ;;(:alias 'verbose-spoon.views 'views)
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [verbose-spoon.model.core :refer [insert-course]]
            [verbose-spoon.views [registration :as registration]
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

;; Later we could structure routes more restfully
(defroutes routes
  (GET "/login" [] "Login page replaces this string") ;;Put your login page here
  (GET "/registration" [] (registration/page))
  (GET "/main" [] (main/page))
  (GET "/me" [] (me/page))
  (GET "/edit-profile" [] (edit-profile/page))
  (GET "/my-application" [] (my-application/page))
  (GET "/view-apply-project" [] (view-apply-project/page))
  (GET "/view-course" [] (view-course/page))
  ;; The rest are the admin routes
  (GET "/view-project-report" [] (view-project-report/page))
  (GET "/choose-functionality" [] (choose-functionality/page))
  (GET "/view-applications" [] (view-applications/page))
  (GET "/view-application-report" [] (view-application-report/page))
  (GET "/add-project" [] (add-project/page))
  (GET "/add-course" [] (add-course/page))
  ;; POST routes for the pages with forms
  ;; 1. Validate inputs 2. Run appropriate query
  (POST "/add-course" req (insert-course (:params req)))
  ;(POST "/add-project" req (insert-project (:params req)))
  ;(POST "/edit-profile" req ())
  ;(POST "/main" req ())
  ;(POST "/registration" req ())
  (route/not-found "<h1>Page not found</h1>"))

(def handler
  (-> #'routes
      wrap-reload))

(defonce server (jetty/run-jetty handler {:port 8080 :join? false}))
