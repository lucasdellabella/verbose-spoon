(ns verbose-spoon.model.core
  (:require [ring.util.response :refer [response]]
            [ring.util.response :refer [response redirect]]
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
    (seq (q/check-user-email-exists-query email)) (response "This email already exists!")
    (= password "") (response "Password cannot be empty!")
    (= username "") (response "Username cannot be empty!")
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
        reqs (set (remove nil? [(when-not (empty? majorreq) (str "M:" majorreq))
                          (when-not (empty? yearreq) (str "Y:" yearreq))
                          (when-not (empty? departmentreq) (str "D:" departmentreq))]))]
    (q/insert-project-query projectname description numstudents advisorname advisoremail designation)
    (dorun (map (partial q/insert-project-is-category-query projectname) categories))
    (dorun (map (partial q/insert-project-requirement-query projectname) reqs))))

(defn update-profile [{:strs [year major]} username]
  (if (or (= year "") (= major "")) (response "You must fill out a year and a major")
    (do (q/update-profile-query major year username) (redirect "/edit-profile"))))

;view apply project
(defn req-to-kvp [s]
  (condp = (first s)
    \Y {:year (subs s 2)}
    \D {:dept_name (subs s 2)}
    \M {:major (subs s 2)}))

(defn map-for-requirements [requirements]
  (reduce (fn [agg cur] (merge agg (req-to-kvp cur))) {} requirements))

(defn check-requirements? [projectname currentuser]
  (let [{:keys [major year dept_name]} (first (q/get-major-year-dept-application-query currentuser))
        {pmajor :major, pyear :year, pdept_name :dept_name} (map-for-requirements (map :requirement_type (q/view-project-requirement-query projectname)))]
        (cond (and pyear (not= pyear year)) false
              (and (and pmajor pdept_name) (and (not= pmajor major) (not= pdept_name dept_name))) false
              (and (and pmajor (nil? pdept_name)) (and (not= pmajor major))) false
              (and (and pdept_name (nil? pmajor)) (and (not= pdept_name dept_name))) false
        :else true)))

(defn update-apply-project [projectname currentuser]
  (cond
    (seq (q/check-already-applied-application-query projectname currentuser))(response "You have already been applied for this project. Please check your application statuses.")
    (seq (q/check-rejected-application-query projectname currentuser))(response "You have already been rejected for this project. You cannot apply again")
    (empty? (q/check-user-year-major-exists-query currentuser)) (response "Your year and major do not exist! Please register your year and major on the Edit Profile Page.")
    (not (check-requirements? projectname currentuser)) (response "You do not meet all the requirements for this project.")
    :else (do (q/update-apply-query projectname currentuser)(response "Successful Application!"))
    ))

;end view apply project

(defn split-application-info [s]
  (clojure.string/split s #"\|"))

(defn accept-reject-application [{:strs [submission-type application]}]
  (let [[username project status] (split-application-info application)]
    (cond
      (or (= "Accepted" status) (= "Rejected" status)) "You can only accept or reject pending applications"
      (= "Accept" submission-type) (q/view-applications-accept username project)
      :else (q/view-applications-reject username project))))
