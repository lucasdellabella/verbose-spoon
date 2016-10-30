(ns verbose-spoon.views.edit-profile
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]))

(defn edit-profile-page
  []
  (html5
    [:head
      [:title "Edit Profile"]]
    [:body
      [:h1 "Edit Profile"]
      (f/form-to [:post ""]
       [:table
         [:tr
           [:td
             (f/label :year "Major:")]
           [:td
             (f/drop-down :year ["Get", "Majors"])]]
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
