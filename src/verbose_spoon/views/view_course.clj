(ns verbose-spoon.views.view-course
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]))

(defn page
  []
  (html5
    [:head
      [:title "View Course Page"]]
    [:body
      [:h1 "CS/PSYC 3750"]
      [:table
        [:tr
          [:td
            (f/label :coursename "Course Name:")]
          [:td
            (f/label :coursename "Intro to Computer Interaction")]]
        [:tr
          [:td
            (f/label :instructor "Instructor:")]
          [:td
            (f/label :instructor "Rosa Arriaga")]]
        [:tr
          [:td
            (f/label :designation "Designation:")]
          [:td
            (f/label :designation "Sustainable Community")]]
        [:tr
          [:td
            (f/label :category "Category:")]
          [:td
            (f/label :category "Doing good in your neighborhood")]]
        [:tr
          [:td
            (f/label :numstudents "Estimated Num Students:")]
          [:td
            (f/label :numstudents "100")]]
      ]
      [:a {:href "/main"}
        [:button "Back"]]
  ]))
