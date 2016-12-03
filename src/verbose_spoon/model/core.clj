(ns verbose-spoon.model.core
  (:require [verbose-spoon.model.queries :as q]))

(defn fetch-major-list []
 (map :major_name (q/major-query)))

(defn fetch-category-list []
 (map :name (q/category-query)))

(defn fetch-designation-list []
 (map :name (q/designation-query)))

;; name is wrong
(defn key-is-category [[k v]]
  (when (re-matches #"category" k) v))

(defn insert-course [{:keys [coursenum coursename instructor designation numstudents] :as params}]
  (let [categories (map key-is-category params)]
    (q/insert-course-query coursenum coursename instructor designation numstudents)
    (dorun (map (partial q/insert-course-is-category-query coursename) categories))))
