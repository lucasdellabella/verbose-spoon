(ns verbose-spoon.views.choose-functionality
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]))

(defn page
  []
  (html5
    [:head
      [:title "Choose Functionality"]]
    [:body
      [:h1 "Choose Functionality"]
      [:a {:href "/view-applications"} "View Applications"] [:br]
      [:a {:href "/view-project-report"} "View Popular Project Reports"] [:br]
      [:a {:href "/view-application-report"} "View Application Report"] [:br]
      [:a {:href "/add-project"} "Add a Project"] [:br]
      [:a {:href "/add-course"} "Add a Course"] [:br]
      [:a {:href "/login"} [:button "Logout"]] [:br]]))
