(ns verbose-spoon.views.add-project
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [hiccup.element :as e]))

(defn page
  []
  (html5
    [:head
      [:title "Add a Project"]]
    [:body
      [:h1 "Add a Project"]
      [:div
        (f/form-to [:post ""]
          [:div
            (f/label :project-name "Project Name:")
            (f/text-field :project-name)]
          [:div
            (f/label :advisor "Advisor:")
            (f/text-field :advisor)]
          [:div
            (f/label :advisor-email "Advisor Email:")
            (f/text-field :advisor-email)]
          [:div
            (f/label :description "Description:")
            (f/text-area :description)]
          [:div
            (f/label :category "Category:")
            (f/drop-down :category ["Function", "To", "Get", "All", "Categories", "Here"])
            (e/link-to "/main" "Add New Category")] ;; TODO: make javascript function
          [:div
            (f/label :designation "Designation:")
            (f/drop-down :designation-drop ["Get", "Designations", "From", "DB"])]
          [:div
            (f/label :num-students "Estimated # of students")
            (f/text-field :num-students)] ;; need JS to make sure input is number]
          [:div
            (f/label :major-requirement "Major Requirement:")
            (f/drop-down :major-requirement ["Get", "Major", "Requirements"])]
          [:div
            (f/label :year-requirement "Year Requirement:")
            (f/drop-down :year-requirement ["Get", "Year", "Requirements"])]
          [:div
            (f/label :deparment-requiremnt "Department Requirement:")
            (f/drop-down :department-requiremtn ["Get", "Department", "Requirements"])])
        [:div
          [:a {:href "/choose-functionality"}
            [:button "Back"]]
          (f/submit-button "Submit")]]]))
