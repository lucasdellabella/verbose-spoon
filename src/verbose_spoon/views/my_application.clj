(ns verbose-spoon.views.my-application
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [verbose-spoon.model.queries :refer [my-application-query]]            ))

(defn app-row [date project status]
  [:tr
    [:td date]
    [:td project]
    [:td status]])

(defn wrap-table [l]
    (vec (conj l [:tr [:th "Date"] [:th "Project Name"] [:th "Status"]] :table)))

(defn page
  []
  ; I HARDCODED A USER "AVA26" TO THIS QUERY
  (let [my-application-results (map (fn [map] (app-row (:date map) (:project_name map) (:status map))) (my-application-query "ava26"))]
  (html5
    [:head
      [:title "My Application"]]
    [:body
      [:h1 "My Application"]
      (wrap-table my-application-results)
      [:a {:href "/me"}
        [:button "Back"]]])))
