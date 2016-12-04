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
            "Status"]
        ]
      ];(map (fn [x] (apply application-row x)) (application-status-list "ava26"))]
      [:a {:href "/me"}
        [:button "Back"]]]))
