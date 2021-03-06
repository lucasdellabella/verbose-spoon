(ns verbose-spoon.views.registration
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]))

(defn page
  []
  (html5
    [:head
      [:title "Registration Page"]]
    [:body
      [:h1 "New Student Registration"]
      (f/form-to [:post "/registration"]
       [:table
        [:tr
          [:td
            (f/label :username "Username:")]
          [:td
            (f/text-field {:required ""} :username)]]
        [:tr
          [:td
           (f/label :email "Email:")]
          [:td
            (f/email-field {:required ""} :email)]]
        [:tr
          [:td
            (f/label :password "Password:")]
          [:td
            (f/password-field {:required ""} :password)]]
        [:tr
          [:td
            (f/label :confirmpassword "Confirm Password:")]
          [:td
            (f/password-field {:required ""} :confirmpassword)]]
        [:tr
          [:td
            (f/submit-button "Create")]]])]))
