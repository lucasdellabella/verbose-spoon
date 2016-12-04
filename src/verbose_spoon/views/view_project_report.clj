(ns verbose-spoon.views.view-project-report
  (:require [hiccup.page :refer [html5]]
    [verbose-spoon.model.queries :refer [view-project-report]]))

(defn app-row [project_name applicants]
  [:tr
    [:td project_name]
    [:td applicants]])

(defn wrap-table [l]
    (vec (conj l [:tr [:th "Project"] [:th "# of Applicants"]] :table)))

(defn page
  []
  (let [view-project-reports-results (map (fn [map] (app-row (:project_name map) (:applicants map))) (view-project-report))]
    (html5
      [:head
        [:title "Popular Project"]]
      [:body
        [:h1 "Popular Project"]
        (wrap-table view-project-reports-results)
        [:a {:href "/choose-functionality"}
          [:button "Back"]]]))
        )
