(ns verbose-spoon.views.me
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [hiccup.element :as e]))

(defn page
  []
  (html5
    [:head
      [:title "Me"]]
    [:body
      [:h1 "Me"]
      [:div
        [:div
            (e/link-to "/edit-profile" "Edit Profile")]
        [:div
            (e/link-to "/my-application" "My Application")]
        [:a {:href "/main"}
          [:button "Back"]]]]))
