(ns verbose-spoon.views.view-apply-project
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]))

(defn page
  []
  (html5
    [:head
      [:title #_"Insert Project Name Here"]]
    [:body
      [:h1 #_"Insert Project Name Here"]
      [:p "Advisor: " ]
      [:p "Description: " ]
      [:p "Designation " ]
      [:p "Category: "]
      [:p "Requirements: "]
      [:p "Estimated number of students: "]
      [:div
       [:a {:href "REPLACE"} [:button "Back"]]
       [:a {:href "REPLACE"} [:button "Apply"]]]]))
