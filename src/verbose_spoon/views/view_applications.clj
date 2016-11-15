(ns verbose-spoon.views.view-applications
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]))

(defn view-application-row [project major year status]
  [:tr
    [:td project]
    [:td major]
    [:td year]
    [:td status]])

(defn page
  []
  (html5
    [:head
      [:title "View Applications"]]
    [:body
      [:h1 "My Application"]
      [:table
        [:tr
          [:th "Project Name"]
          [:th "Applicant Major"]
          [:th "Applicant Year"]
          [:th "Application Status"]]
        (view-application-row "Excel Current Events" "HTS" "freshman" "Approved")
        (view-application-row "Know Your Water" "EnvE" "sophomore" "Pending")]
      [:div
       [:a {:href "REPLACE"} [:button "Back"]]
       [:a {:href "REPLACE"} [:button "Accept"]]
       [:a {:href "REPLACE"} [:button "Reject"]]]]))

