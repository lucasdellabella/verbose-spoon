(ns verbose-spoon.views.view-apply-project
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [verbose-spoon.views.core :refer [commacat]]
            [verbose-spoon.model.queries :refer [view-project-query view-project-category-query view-project-requirement-query]]))

(defn page
  [project_name]
  (let [view-project-results (view-project-query project_name)
        view-project-category-results (view-project-category-query project_name)
        view-project-requirement-results (view-project-requirement-query project_name)
        {:keys [name description est_num_students advisor_name advisor_email designation_name]} (first view-project-results)]
    (html5
      [:head
        [:title "View Course Page"]]
      [:body
        [:h1 name]
        [:table
          [:tr
            [:td
              (f/label :advisor "Advisor:")]
            [:td
              (f/label :advisor (str advisor_name " (" advisor_email ")"))]]
          [:tr
            [:td
              (f/label :description "Description:")]
            [:td
              (f/label :description description)]]
          [:tr
            [:td
              (f/label :designation "Designation:")]
            [:td
              (f/label :designation designation_name)]]
          [:tr
            [:td
              (f/label :category "Category:")]
            [:td
              (f/label :category (commacat (map :category_name view-project-category-results)))]]
          [:tr
            [:td
              (f/label :requirements "Requirements:")]
            [:td
              (f/label :requirements (commacat (map (fn [s] (subs s 2)) (map :requirement_type view-project-requirement-results))))]]
          [:tr
            [:td
              (f/label :numstudents "Estimated Num Students:")]
            [:td
              (f/label :numstudents est_num_students)]]
        ]
        [:a {:href "/main"}
          [:button "Back"]]
        [:a {:href "/main"}
          [:button "Apply"]]
      ]
    )
  )
)
