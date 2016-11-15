(ns verbose-spoon.views.view-apply-project
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [verbose-spoon.model.core :refer [fetch-major-list]]))

(defn page
  []
  (html5
    [:head
      [:title "Insert Project Name Here"]]
    [:body
      [:h1 "Insert Project Name Here"]
      (f/form-to [:post ""]
       [:table
         [:tr
           [:td
             (f/label :year "Major:")]
           [:td
             (f/drop-down :year (fetch-major-list))]]
        [:tr
          [:td
            (f/label :year "Year:")]
          [:td
            (f/drop-down :year ["Freshman", "Sophomore", "Junior", "Senior"])]]
        [:tr
          [:td
            (f/label :department "Department:")]
          [:td
            (f/text-field {:readonly ""}:department "Get Department From Major")]]
        [:tr
          [:td
           [:a {:href "/me"}
            (f/submit-button "Back")]]]])]))
