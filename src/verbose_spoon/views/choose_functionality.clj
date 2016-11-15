(ns verbose-spoon.views.choose-functionality
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]))

(defn page
  []
  (html5
    [:head
      [:title "Choose Functionality"]]
    [:body
      [:h1 "Choose Functionality"]
      [:a {:href "REPLACE"} "View Applications"] [:br]
      [:a {:href "REPLACE"} "View Popular Project Reports"] [:br]
      [:a {:href "REPLACE"} "View Application Report"] [:br]
      [:a {:href "REPLACE"} "Add a Project"] [:br]
      [:a {:href "REPLACE"} "Add a Course"] [:br]
      [:a {:href "REPLACE"} [:button "Apply"]] [:br]]))

