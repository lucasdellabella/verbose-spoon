(ns verbose-spoon.views.edit-profile
  (:require [hiccup.page :refer [html5 include-js]]
            [hiccup.form :as f]
            [hiccup.element :as e]
            [verbose-spoon.model.core :refer [fetch-major-list]]
            [verbose-spoon.model.queries :refer [major-department-query]]
            [verbose-spoon.model.queries :refer [user-major-query]]
            [verbose-spoon.model.queries :refer [user-year-query]]
            [verbose-spoon.model.queries :refer [department-only-query]]))

(defn format-as-MajorDeptEntry [[k v]]
  (format "addMajorDeptEntry('%s', '%s');\n" k v))

(defn build-major-to-dept-JSO []
  (->> (major-department-query)
       ; build major to dept pairs
       (map (fn [{:keys [major_name dept_name]}] [major_name dept_name]))
       ; convert each pair to a js fn call string
       (map format-as-MajorDeptEntry)
       ; put them all inside of a script element
       (#(conj % :script))
       (vec)))

(defn page
  [username]
  (html5
    [:head
      [:title "Edit Profile"]
      ; (include-js "/js/generate-major-department-map.js")
      (include-js "/js/edit-profile.js")
      ; (build-major-to-dept-JSO)
    [:body
      [:h1 "Edit Profile"]
      (f/form-to [:post "/edit-profile"]
       [:table
         [:tr
           [:td
             (f/label :major "Major:")]
           [:td
             (f/drop-down {:onchange "getDepartment(this)" :id "major-dropdown"} :major (fetch-major-list))]]
        [:tr
          [:td
            (f/label :year "Year:")]
          [:td
            (f/drop-down {:id "year-dropdown"} :year ["Freshman", "Sophomore", "Junior", "Senior"])]]
        [:tr
          [:td
            (f/label :department "Department:")]
          [:td
            [:h6 {:id "dept-id" :name "department"} ""]]]
        [:tr
          [:td
           [:a {:href "/me"}
            (f/submit-button "Submit")]]
          [:td
           [:a {:href "/me"}
            [:button "Back"]]]]])
    [:script (format "setMajor('%s'); setYear('%s'); setDepartment('%s');"
      (pr-str (user-major-query username))
      (pr-str (user-year-query username))
      (pr-str (department-only-query username ))
      )]]]))
            ; (pr-str (department-only-query ((user-major-query username))
; setDepartment('%s');
