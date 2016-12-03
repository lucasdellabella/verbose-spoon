(ns verbose-spoon.views.add-course
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [hiccup.element :as e]
            [verbose-spoon.model.core :refer [fetch-major-list fetch-designation-list fetch-category-list]]))

(defn page
  []
  (html5
    [:head
      [:title "Add Course Page"]]
    [:body
      [:h1 "Add a Course"]
      [:div
        (f/form-to [:post "/add-course"]
          [:table
            [:tr
              [:td
                (f/label :coursenum "Course Number:")]
              [:td
                (f/text-field :coursenum)]]
            [:tr
              [:td
                (f/label :coursename "Course Name:")]
              [:td
                (f/text-field :coursename)]]
            [:tr
              [:td
                (f/label :instructor "Instructor:")]
              [:td
                (f/text-field :instructor)]]
            [:tr
              [:td
                (f/label :designation "Designation:")]
              [:td
                (f/drop-down :designation (fetch-designation-list))]]
            [:tr
              [:td
                (f/label :category "Category:")]
              [:td
                (f/drop-down :category (fetch-category-list))]
              [:td
                [:a {:href ""} "Add new Category"]]]
            [:tr
              [:td
                (f/label :numstudents "Estimated Num Students")]
              [:td
                (f/text-field :numstudents)]]]
          [:div
              [:a {:href "/choose-functionality"}
                [:button "Back"]]
              (f/submit-button "Submit")])]]))
