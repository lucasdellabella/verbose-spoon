(ns verbose-spoon.views.registration
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]))

(defn registration-page
  []
  (html5
    [:head
      [:title "Registration Page"]]
    [:body
      (f/form-to [:post ""]
       [:table
        [:tr
          [:td
            (f/label :username "Username:")]
          [:td
            (f/text-field :username)]]
        [:tr
          [:td
           (f/label :email "Email:")]
          [:td
            (f/email-field :email)]]
        [:tr
          [:td
            (f/label :pasword "Password:")]
          [:td
            (f/password-field :password)]]
        [:tr
          [:td
            (f/label :confirmpassword "Confirm Password:")]
          [:td
            (f/password-field :confirmpassword)]]
        [:tr
          [:td
            (f/submit-button "Submit")]]])]))
