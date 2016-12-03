(ns verbose-spoon.views.login
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [hiccup.element :as e]))

(defn page
  []
  (html5
    [:head
      [:title "Login"]]
    [:body
      [:h1 "Login"]
      [:p "Username"]
      [:input {:type "text"}]
      [:p "Password"]
      [:input {:type "text"}]
      [:div
        [:button "Login"]
        [:button "Register"]]]))
