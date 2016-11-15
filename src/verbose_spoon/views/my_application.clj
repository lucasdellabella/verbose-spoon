(ns verbose-spoon.views.my-application
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]))

(defn application-row [date project status]
  [:tr
    [:td date]
    [:td project]
    [:td status]])

(defn page
  []
  (html5
    [:head
      [:title "My Application"]]
    [:body
      [:h1 "My Application"]
      [:table
        [:tr
          [:th
            "Date"]
          [:th
            "Project Name"]
          [:th
            "Status"]]
        (application-row "16/8/30" "Excel Current Events" "Approved")
        (application-row "16/9/2" "Know Your Water" "Pending")]
      [:a {:href "/me"}
        [:button "Back"]]]))
