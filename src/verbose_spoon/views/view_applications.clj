(ns verbose-spoon.views.view-applications
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [verbose-spoon.model.queries :refer [view-applications-query]]))

(defn app-row [project major year status]
  [:tr
    [:td project]
    [:td major]
    [:td year]
    [:td status]])

(defn wrap-table [l]
    (vec (conj l [:tr [:th "Project"] [:th "Applicant Major"] [:th "Applicant Year"] [:th "Status"]] :table)))

(defn page
  []
  (let [view-applications-results (map (fn [map] (app-row (:project_name map) (:major map) (:year map) (:status map))) (view-applications-query))]
    (html5
      [:head
        [:title "View Applications"]]
      [:body
        [:h1 "My Application"]
        (wrap-table view-applications-results)
        [:div
         [:a {:href "REPLACE"} [:button "Back"]]
         [:a {:href "REPLACE"} [:button "Accept"]]
         [:a {:href "REPLACE"} [:button "Reject"]]]])))
