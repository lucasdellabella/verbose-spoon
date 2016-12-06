(ns verbose-spoon.model.core
  (:require [ring.util.response :refer [response]]
            [verbose-spoon.model.queries :as q]))

(defonce gatech-email-regex #"([a-zA-Z0-9]+)([\.{1}])?([a-zA-Z0-9]+)\@gatech([\.])edu")

(defn creds-correct? [username password]
  (= password (-> username q/user-password-query first :password)))

(defn is-admin? [username]
  (= "ADMIN" (-> username q/admin-query first :type)))

(defn attempt-to-register [{:strs [username password confirmpassword email]}]
  (cond
    (seq (q/check-user-name-exists-query username)) (response "The username already exists!")
    (not (re-find gatech-email-regex email)) (response "The email must be a gatech email!")
    (seq (q/check-user-email-exists-query email)) (response "This account already exists!")
    (= password "") (response "Password cannot be empty!")
    (not= password confirmpassword) (response "Passwords don't match!")
    :else (do (q/insert-register-user username password email)
            (response "Successful Registration!"))))

(defn fetch-major-list []
 (map :major_name (q/major-query)))

(defn fetch-category-list []
 (map :name (q/category-query)))

(defn fetch-designation-list []
 (map :name (q/designation-query)))

(defn fetch-department-list []
 (map :dept_name (q/department-query)))

(defn take-categories [params]
  (filter (fn [[k _]] (re-matches #"category.*" k)) params))

(defn insert-course [{:strs [coursenum coursename instructor designation numstudents] :as params}]
  (let [categories (-> params take-categories vals set)]
    (q/insert-course-query coursenum coursename numstudents instructor designation)
    (dorun (map (partial q/insert-course-is-category-query coursenum) categories))))

;(defn apply-project [{:strs []}])

;(defn application-status-list [user]
;  (map vals (application-status-query user)))

(defn insert-project [{:strs [projectname advisorname advisoremail description designation numstudents majorreq yearreq departmentreq] :as params}]
  (let [categories (-> params take-categories vals set)
        ; remove any nonreqs and prefix the existing ones
        reqs (remove empty? #{(when-not (empty? majorreq) (str "M:" majorreq))
                              (when-not (empty? yearreq) (str "Y:" yearreq))
                              (when-not (empty? departmentreq) (str "D:" departmentreq))})]
    reqs
    (q/insert-project-query projectname description numstudents advisorname advisoremail designation)
    (dorun (map (partial q/insert-project-is-category-query projectname) categories))
    (dorun (map (partial q/insert-project-requirement-query projectname) reqs))))

(defn update-profile [{:strs [year major]} username]
  (q/update-profile-query major year username)
  "~~Updated~~")

;; NOTIFY USER WHEN USER HAS ALREADY BEEN REJECTED
(defn update-apply-project [projectname currentuser]
  ;(when-not (empty? (q/check-rejected-application-query projectname currentuser)) (q/update-apply-query projectname currentuser))
  (cond
    (seq (q/check-already-applied-application-query projectname currentuser))(response "You have already been applied for this project. Please check your application statuses.")
    (seq (q/check-rejected-application-query projectname currentuser))(response "You have already been rejected for this project. You cannot apply again")
    (empty? (q/check-user-year-major-exists-query currentuser)) (response "Your year and major do not exist! Please register your year and major on the Edit Profile Page.")
    (seq (q/check-requirements-application-query projectname currentuser)) (response "You do not meet all the requirements for this project.")
    :else (do (q/update-apply-query projectname currentuser)(response "Successful Application!"))
    ))

(defn split-application-info [s]
  (clojure.string/split s #"\|"))

(defn accept-reject-application [{:strs [submission-type application]}]
  (let [[username project] (split-application-info application)]
    (if (= "Accept" submission-type)
      (q/view-applications-accept username project)
      (q/view-applications-reject username project))
    (str [username project])))
