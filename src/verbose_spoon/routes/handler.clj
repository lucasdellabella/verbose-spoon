(ns verbose-spoon.routes.handler
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [verbose-spoon.views.registration :refer [registration-page]]
            [verbose-spoon.views.main :refer [main-page]]
            [verbose-spoon.views.me :refer [me-page]]
            [verbose-spoon.views.edit-profile :refer [edit-profile-page]]))


(defroutes routes
  (GET "/login" [] "Login page replaces this string") ;;Put your login page here
  (GET "/registration" [] (registration-page))
  (GET "/main" [] (main-page))
  (GET "/me" [] (me-page))
  (GET "/edit-profile" [] (edit-profile-page))
  (route/not-found "<h1>Page not found</h1>"))

(def handler
  (-> #'routes
      wrap-reload))

(defonce server (jetty/run-jetty handler {:port 8080 :join? false}))
