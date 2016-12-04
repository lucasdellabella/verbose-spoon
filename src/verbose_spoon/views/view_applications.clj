(ns verbose-spoon.views.view-applications
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [verbose-spoon.model.queries :refer [view-applications-query]]))

(defn app-row [project major year status username]
  [:tr
    #_[:td (f/radio-button :applications "false" (format "%s|%s" username project))]
    [:td project]
    [:td major]
    [:td year]
    [:td status]])

(defn wrap-table [l]
    (vec (conj l [:tr
                  [:th "Project"]
                  [:th "Applicant Major"]
                  [:th "Applicant Year"]
                  [:th "Status"]] :table)))

(defn build-app-row [{:keys [project_name major year status username]}] 
  (app-row project_name major year status username))

(defn page
  []
  (let [view-applications-results (map build-app-row (view-applications-query))]
    (html5
      [:head
        [:title "View Applications"]]
      [:body
        [:h1 "My Application"]
        (f/form-to "/view-applications"
          (wrap-table view-applications-results)
          [:div
            [:a {:href "/choose-functionality"} [:button "Back"]]
            [:a {:href "/view-applications"} (f/submit-button "Accept")]
            [:a {:href "/view-applications"} (f/submit-button "Reject")]])])))
