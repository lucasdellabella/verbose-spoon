(ns verbose-spoon.views.view-application-report
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as f]
            [verbose-spoon.model.queries :refer [view-application-report-query
            view-application-report-num-applications-query
            view-application-report-num-accepted-query]]
            [verbose-spoon.views.core :refer [commacat]]))


(defn app-row [project_name applications accept_rate top_majors]
  [:tr
    [:td project_name]
    [:td applications]
    [:td accept_rate]
    [:td top_majors]])

(defn wrap-table [l]
    (vec (conj l [:tr [:th "Project"] [:th "# of Applicants"] [:th "accept rate"] [:th "top 3 majors"]] :table)))


; (defn num-applications [name]
;   (:count (first (view-application-report-num-applications-query name))))
;
; (defn num-accepted-applications [name]
;   (:count (first (view-application-report-num-accepted-query name))))

(defn percent-rate [name]
  (str (int name) "%"))

; (defn top-majors [name]
;   (commacat (map :major (view-application-report-top-majors-query name))))
;
; (defn application-report-results [names]
;   (map (fn [name] (app-row name (num-applications name) (percent-rate name) (top-majors name))) names))
;
(defn build-app-row [{:keys [project_name num_applicants acceptance_rate top_majors]}]
  (app-row project_name num_applicants (percent-rate acceptance_rate) top_majors))

(defn page
  []
  (let [view-application-report-results (map build-app-row (view-application-report-query))]
    (html5
      [:head
        [:title "View Application Report Page"]]
      [:body
        [:h1 "Application Report"]
        [:h3 (format "%s Applications, %s Accepted" (:count (first (view-application-report-num-applications-query))) (:count (first (view-application-report-num-accepted-query))))]
        (wrap-table view-application-report-results)
        [:a {:href "/choose-functionality"}
          [:button "Back"]]
    ])))
