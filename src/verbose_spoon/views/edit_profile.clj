(ns verbose-spoon.views.edit-profile
  (:require [hiccup.page :refer [html5 include-js]]
            [hiccup.form :as f]
            [verbose-spoon.model.core :refer [fetch-major-list]]))

(defn page
  []
  (html5
    [:head
      [:title "Edit Profile"]
      (include-js "/js/get-major-department.js")]
    [:body
      [:h1 "Edit Profile"]
      (f/form-to [:post ""]
       [:table
         [:tr
           [:td
             (f/label :year "Major:")]
           [:td {:onclick "myFunction()"}
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
