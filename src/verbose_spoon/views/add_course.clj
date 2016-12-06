(ns verbose-spoon.views.add-course
  (:require [hiccup.page :refer [html5 include-js]]
            [hiccup.form :as f]
            [hiccup.element :as e]
            [clojure.string :refer [join]]
            [verbose-spoon.model.core :refer [fetch-major-list fetch-designation-list fetch-category-list]]
            [verbose-spoon.views.core :refer [commacat]]))

; Move these functions outta here
(defn format-categories []
  (apply str (map (fn [l] (str l "','")) (fetch-category-list))))

(defn create-categories-js-func []
  (str "addCategory(['" (format-categories) "'])"))

(defn page
  []
  (html5
    [:head
      [:title "Add Course Page"]
      (include-js "/js/add-category.js")]
    [:body
      [:h1 "Add a Course"]
      [:div
        (f/form-to [:post "/add-course"]
          [:table {:id "table"}
            [:tr
              [:td
                (f/label :coursenum "Course Number:")]
              [:td
                (f/text-field {:required ""} :coursenum)]]
            [:tr
              [:td
                (f/label :coursename "Course Name:")]
              [:td
                (f/text-field {:required ""} :coursename)]]
            [:tr
              [:td
                (f/label :instructor "Instructor:")]
              [:td
                (f/text-field {:required ""} :instructor)]]
            [:tr
              [:td
                (f/label :designation "Designation:")]
              [:td
                (f/drop-down :designation (fetch-designation-list))]]
            [:tr {:id "category"}
              [:td
                (f/label :category "Category:")]
              [:td
                (f/drop-down :category (fetch-category-list))]
              [:td
                [:a {:onclick (create-categories-js-func) :href "#"} "Add new Category"]            [:tr]]
              [:td
                (f/label :numstudents "Estimated Num Students")]
              [:td
                (f/text-field {:required ""} :numstudents)]]]
            [:div
              (f/submit-button "Submit")])
          [:a {:href "/choose-functionality"}
            [:button "Back"]]]]))
