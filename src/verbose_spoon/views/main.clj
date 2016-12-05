(ns verbose-spoon.views.main
  (:require [hiccup.page :refer [html5 include-js]]
            [hiccup.form :as f]
            [hiccup.element :as e]
            [verbose-spoon.model.core :refer [fetch-major-list fetch-category-list fetch-designation-list]]))

; Move these functions outta here
(defn format-categories []
  (apply str (map (fn [l] (str l "','")) (fetch-category-list))))

(defn create-categories-js-func []
  (str "addCategory(['" (format-categories) "'])"))

(defn page
  []
  (html5
    [:head
      [:title "Main Page"]
      (include-js "/js/add-category.js")]
    [:body
      [:h1 "Main Page"]
      [:div
        [:a {:href "/me"}
          [:button "Me"]]]
      [:div
        (f/form-to [:post ""]
          [:div {:id "category"}
              (f/label :title "Title:")
              (f/text-field :title)
              (f/label :category "Category:")
              (f/drop-down :category-drop (fetch-category-list))
              [:a {:onclick (create-categories-js-func) :href "#"} "Add new Category"]]
          [:div
              (f/label :designation "Designation:")
              (f/drop-down :designation-drop (fetch-designation-list))]
          [:div
              (f/label :major "Major:")
              (f/drop-down :major (fetch-major-list))]
          [:div
              (f/label :year "Year:")
              (f/drop-down :year ["Freshman", "Sophomore", "Junior", "Senior"])
              (f/label :project "Project")
              (f/radio-button :project)
              (f/label :project "Course")
              (f/radio-button :project)
              (f/label :project "Both")
              (f/radio-button {:checked "checked"} :project)]
          [:div
              (f/submit-button "Apply Filter")
              (f/reset-button "Reset")])
        [:table
          [:tr
            [:th "Name"]
            [:th "Type"]]
          [:a {:href "/me"}
            [:tr
              [:td "Sample Name"]
              [:td "Sample Type"]]]]]]))
