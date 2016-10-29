(ns verbose-spoon.routes.handler
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [verbose-spoon.views.registration :refer [registration-page]]))


(defroutes routes
  (GET "/login" [] "Login page replaces this string") ;;Put your login page here
  (GET "/registration" [] (registration-page))
  (route/not-found "<h1>Page not found</h1>"))

(def handler
  (-> #'routes
      wrap-reload))

(defonce server (jetty/run-jetty handler {:port 8080 :join? false}))
