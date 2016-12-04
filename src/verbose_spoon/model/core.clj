(ns verbose-spoon.model.core
  (:require [verbose-spoon.model.queries :as q]))

(defn fetch-major-list []
 (map :major_name (q/major-query)))

(defn fetch-category-list []
 (map :name (q/category-query)))

(defn fetch-designation-list []
 (map :name (q/designation-query)))

(defn fetch-department-list []
 (map :dept_name (q/department-query)))

(defn take-categories [params]
  (filter (fn [[k _]] (re-matches #"category" k)) params))

(defn insert-course [{:strs [coursenum coursename instructor designation numstudents] :as params}]
  (let [categories (-> params take-categories vals)]
    (q/insert-course-query coursenum coursename numstudents instructor designation)
    (dorun (map (partial q/insert-course-is-category-query coursename) categories))))

;(defn apply-project [{:strs []}])

;(defn application-status-list [user]
;  (map vals (application-status-query user)))

(defn insert-project [{:strs [projectname advisorname advisoremail description designation numstudents majorreq yearreq departmentreq] :as params}]
  (let [categories (-> params take-categories vals)
        ; remove any nonreqs and prefix the existing ones
        reqs (remove empty? #{(when-not (empty? majorreq) (str "M:" majorreq))
                              (when-not (empty? yearreq) (str "Y:" yearreq))
                              (when-not (empty? departmentreq) (str "D:" departmentreq))})]
    reqs
    (q/insert-project-query projectname description numstudents advisorname advisoremail designation)
    (dorun (map (partial q/insert-project-is-category-query projectname) categories))
    (dorun (map (partial q/insert-project-requirement-query projectname) reqs))))
