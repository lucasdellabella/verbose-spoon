(ns verbose-spoon.views.main
  (:require [hiccup.page :refer [html5 include-js]]
            [hiccup.form :as f]
            [hiccup.element :as e]
            [cemerick.url :refer [url-encode]]
            [verbose-spoon.model.core :refer [take-categories fetch-major-list fetch-category-list fetch-designation-list]]
            [verbose-spoon.model.queries :refer [populate-main-query]]))

(defn table-entry [iname icourse itype]
  [:tr
   [:td [:a {:href (if (= itype "Course")
                     (format "/view-course/%s" (url-encode icourse))
                     (format "/view-apply-project/%s" (url-encode iname)))} [:button "View"]]]
   [:td iname]
   [:td itype]])

; Move these functions outta here
(defn format-categories []
  (apply str (map (fn [l] (str l "','")) (fetch-category-list))))

(defn create-categories-js-func []
  (str "addCategory(['" (format-categories) "'])"))

(defn page
  [{:strs [title designation major year itype] :as params}]
  (let [categories (-> params take-categories vals set)
        main-query-results (when-not (empty? params) (populate-main-query title designation major year categories itype))
        cleaned-main-query-results (remove empty? main-query-results)
        formatted-tuples (seq (set (map (fn [{n :name, c :course_num, t :type}] (table-entry n c t)) cleaned-main-query-results)))
        table (vec (conj formatted-tuples [:tr [:th "View"] [:th "Name"] [:th "Type"]] :table))]
    (println main-query-results)
  (html5
    [:head
      [:title "Main Page"]
      (include-js "/js/add-category.js")]
    [:body
      [:h1 "Main Page"]
      [:div
        [:a {:href "/me"}
          [:button "Me"]]]
      [:div
        (f/form-to [:get "/main"]
          [:div {:id "category"}
              (f/label :title "Title:")
              (f/text-field :title)
              (f/label :category "Category:")
              (f/drop-down :category (conj (fetch-category-list) ""))
              [:a {:onclick (create-categories-js-func) :href "#"} "Add new Category"]]
          [:div
              (f/label :designation "Designation:")
              (f/drop-down :designation (conj (fetch-designation-list) ""))]
          [:div
              (f/label :major "Major:")
              (f/drop-down :major (conj (fetch-major-list) ""))]
          [:div
              (f/label :year "Year:")
              (f/drop-down :year ["", "Freshman", "Sophomore", "Junior", "Senior"])
              (f/label :itype "Project")
              (f/radio-button :itype "true" "Project")
              (f/label :itype "Course")
              (f/radio-button :itype "true" "Course")
              (f/label :itype "Both")
              (f/radio-button {:checked "checked"} :itype "true" "Both")]
          [:div
              (f/submit-button "Apply Filter")
              (f/reset-button "Reset")])
        table
          [:a {:href "/me"}]]])))
