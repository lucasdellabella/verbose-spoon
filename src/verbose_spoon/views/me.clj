(ns verbose-spoon.views.me
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [hiccup.element :as e]))

(defn me-page
  []
  (html5
    [:head
      [:title "Me"]]
    [:body
      [:h1 "Me"]
      [:div
        [:div
          ; TODO: Change to Edit Profile Link
            (e/link-to "/edit-profile" "Edit Profile")]
        [:div
          ; TODO: Change to Application Link
            (e/link-to "/registration" "My Application")]
        [:a {:href "/main"}
          [:button "Back"]]]]))
