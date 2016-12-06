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
     (f/form-to [:post "/login"]
      [:h1 "Login"]
      [:p "Username"]
      [:input {:type "text" :name "username" :required ""}]
      [:p "Password"]
      [:input {:type "text" :name "password" :required ""}]
      [:div
        [:a {:href "/me"}
         (f/submit-button "Submit")]
        ])[:a {:href "/registration"}
         [:button "Register"]]
      ]))
