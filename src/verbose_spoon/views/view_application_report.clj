(ns verbose-spoon.views.view-application-report
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]))

(defn page
  []
  (html5
    [:head
      [:title "View Application Report Page"]]
    [:body
      [:h1 "Application Report"]
      [:h3 "151 applications in total, accepted 89 applications"]


      [:a {:href "/choose-functionality"}
        [:button "Back"]]
  ]))
