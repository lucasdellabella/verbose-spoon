(ns verbose-spoon.views.view-project-report
  (:require [hiccup.page :refer [html5]]))

(defn page
  []
  (html5
    [:head
      [:title "Popular Project"]]
    [:body
      [:h1 "Popular Project"]
      [:table
        [:tr
          [:th "Project"]
          [:th "# of Applicants"]]
        [:tr ; create function that creates these table rows
          [:td "Insert Project Name Here"]
          [:td "Insert Number of Applicants Here"]]]
      [:a {:href "/choose-functionality"}
        [:button "Back"]]]))
