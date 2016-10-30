(ns verbose-spoon.views.main
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [hiccup.element :as e]))

(defn main-page
  []
  (html5
    [:head
      [:title "Main Page"]]
    [:body
      [:h1 "Main Page"]
      [:div
        [:button "Me"]]
      [:div
        (f/form-to [:post ""]
          [:div
              (f/label :title "Title:")
              (f/text-field :title)
              (f/label :category "Category:")
              (f/drop-down :category-drop ["Function", "To", "Get", "From", "DB", "Here"])
              (e/link-to "/registration" "Add Category")]
          [:div
              (f/label :designation "Designation:")
              (f/drop-down :designation-drop ["Get", "Designations", "From", "DB"])]
          [:div
              (f/label :major "Major:")
              (f/drop-down :major ["Get", "Majors", "From", "DB"])]
          [:div
              (f/label :year "Year:")
              (f/drop-down :year ["Freshman", "Sophomore", "Junior", "Senior"])
              (f/label :project "Project")
              (f/radio-button :project)
              (f/label :project "Course")
              (f/radio-button :project)
              (f/label :project "Both")
              (f/radio-button :project)]
          [:div
              (f/submit-button "Apply Filter")
              (f/reset-button "Reset")])
        [:table
          [:tr
            [:td "Name"]
            [:td "Type"]]]]]))
