(ns verbose-spoon.views.view-course
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [verbose-spoon.model.queries :refer [view-course-query]]
            [verbose-spoon.views.core :refer [commacat]]))

(defn page
  [course]
  (let [view-course-results (view-course-query course)
        {:keys [course_num name est_num_students instructor designation_name category_name]} (first view-course-results)]
    (html5
      [:head
        [:title "View Course Page"]]
      [:body
        [:h1 course_num]
        [:table
          [:tr
            [:td
              (f/label :coursename "Course Name:")]
            [:td
              (f/label :coursename name)]]
          [:tr
            [:td
              (f/label :instructor "Instructor:")]
            [:td
              (f/label :instructor instructor)]]
          [:tr
            [:td
              (f/label :designation "Designation:")]
            [:td
              (f/label :designation designation_name)]]
          [:tr
            [:td
              (f/label :category "Category:")]
            [:td
              (f/label :category (commacat (map :category_name view-course-results)))]]
          [:tr
            [:td
              (f/label :numstudents "Estimated Num Students:")]
            [:td
              (f/label :numstudents est_num_students)]]
        ]
        [:a {:href "/main"}
          [:button "Back"]]
    ]))
  )
