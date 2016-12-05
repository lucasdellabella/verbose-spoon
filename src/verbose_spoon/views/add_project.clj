(ns verbose-spoon.views.add-project
  (:require [hiccup.page :refer [html5 include-js]]
            [hiccup.form :as f]
            [hiccup.element :as e]
            [verbose-spoon.model.core :refer [fetch-major-list
                                              fetch-category-list
                                              fetch-designation-list
                                              fetch-department-list]]))

; Move these functions outta here
(defn format-categories []
  (apply str (map (fn [l] (str l "','")) (fetch-category-list))))

(defn create-categories-js-func []
  (str "addCategory(['" (format-categories) "'])"))

(defn page
  []
  (html5
    [:head
      [:title "Add a Project"]
      (include-js "/js/add-category.js")]
    [:body
      [:h1 "Add a Project"]
      [:div
        (f/form-to [:post "/add-project"]
          [:div
            (f/label :projectname "Project Name:")
            (f/text-field :projectname)]
          [:div
            (f/label :advisorname "Advisor:")
            (f/text-field :advisorname)]
          [:div
            (f/label :advisoremail "Advisor Email:")
            (f/text-field :advisoremail)]
          [:div
            (f/label :description "Description:")
            (f/text-area :description)]
          [:div {:id "category"}
            (f/label :category "Category:")
            (f/drop-down :category (fetch-category-list))
            [:a {:onclick (create-categories-js-func) :href "#"} "Add new Category"]            [:tr]]
          [:div
            (f/label :designation "Designation:")
            (f/drop-down :designation (fetch-designation-list))]
          [:div
            (f/label :numstudents "Estimated # of students")
            (f/text-field :numstudents)] ;; need JS to make sure input is number]
          [:div
            (f/label :majorreq "Major Requirement:")
            (f/drop-down :majorreq (conj (fetch-major-list) ""))]
          [:div
            (f/label :yearreq "Year Requirement:")
            (f/drop-down :yearreq ["" "Freshman" "Sophomore" "Junior" "Senior"])]
          [:div
            (f/label :departmentreq "Department Requirement:")
            (f/drop-down :departmentreq (conj (fetch-department-list) ""))]
          (f/submit-button "Submit"))
        [:div
          [:a {:href "/choose-functionality"}
            [:button "Back"]]]]]))
