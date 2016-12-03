(ns verbose-spoon.model.core
  (:require [verbose-spoon.model.queries :as q]))

(defn fetch-major-list []
 (map :major_name (q/major-query)))

(defn fetch-category-list []
 (map :name (q/category-query)))

(defn fetch-designation-list []
 (map :name (q/designation-query)))

(defn insert-course [{:strs [coursenum coursename instructor designation numstudents] :as params}]
  (let [categories (vals (filter (fn [[k _]] (re-matches #"category" k)) params))]
    (q/insert-course-query coursenum coursename numstudents instructor designation)
    (dorun (map (partial q/insert-course-is-category-query coursename) categories))))

;(defn application-status-list [user]
;  (map vals (application-status-query user)))
