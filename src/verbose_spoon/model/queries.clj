(ns verbose-spoon.model.queries
  (:require [clojure.java.jdbc :as j]))

(def mysql-db {:dbtype "mysql"
               :dbname "Phase3"
               :user "root"
               :password "password"})

;; If there are weird bugs,
;; it might be because of this
(Class/forName "com.mysql.jdbc.Driver")

(defn major-query []
  (j/query mysql-db ["SELECT Major_Name FROM MAJOR"]))

(defn major-department []
  (j/query mysql-db ["SELECT Major_Name, Dept_Name FROM MAJOR"]))

(defn major-test-insert [] ;;TODO: change this to EXECUTE func
  (j/execute! mysql-db ["INSERT INTO MAJOR VALUES (\"Business\",\"College of Business\")"]))

(defn designation-query []
  (j/query mysql-db ["SELECT Name FROM DESIGNATION"]))

(defn category-query []
  (j/query mysql-db ["SELECT Name FROM CATEGORY"]))

;(defn department-for-major-query [major]
;  (j/query mysql-db [(format "SELECT Dept_Name FROM Major WHERE Major_Name = '%s'" major)]))

(defn department-query []
  (j/query mysql-db ["SELECT Dept_Name FROM DEPARTMENT"]))

(defn insert-course-query [coursename coursenum instructor numstudents designation]
  (j/execute! mysql-db [(format "INSERT INTO Course VALUES('%s', '%s', '%s', '%s', '%s')"
                                coursename
                                coursenum
                                instructor
                                numstudents
                                designation)]))

(defn insert-course-is-category-query [coursenum category]
  (j/execute! mysql-db [(format "INSERT INTO Course_is_Category VALUES('%s', '%s')"
                                coursenum
                                category)]))

(defn view-project-report []
  (j/query mysql-db ["SELECT Project_name, COUNT(Project_name) as Applicants FROM Apply GROUP BY Project_name ORDER BY COUNT(Project_name) DESC LIMIT 10"]))

;view-course view
(defn view-course-query [course]
  (j/query mysql-db [(format "SELECT * FROM Course NATURAL JOIN Course_is_category WHERE Course_Num = '%s'" course)]))

;view-apply-project view
(defn view-project-query [project]
  (j/query mysql-db [(format "SELECT * FROM Project WHERE Name = '%s'" project)]))

(defn view-project-category-query [project_name]
  (j/query mysql-db [(format "SELECT Category_name FROM Project_is_category WHERE Project_name = '%s'" project_name)]))

(defn view-project-requirement-query [project_name]
  (j/query mysql-db [(format "SELECT Requirement_type FROM Requirement WHERE Project_name = '%s'" project_name)]))

(defn application-status-query [user]
  (j/query mysql-db [(format "SELECT Project_Name, Date, Status FROM APPLY where Username = %s" user)]))

(defn insert-project-query [projectname description advisoremail advisorname numstudents designation]
  (j/execute! mysql-db [(format "INSERT INTO Project VALUES('%s', '%s', '%s', '%s', '%s', '%s')"
                                projectname
                                description
                                advisoremail
                                advisorname
                                numstudents
                                designation)]))

(defn insert-project-is-category-query [projectname category]
  (j/execute! mysql-db [(format "INSERT INTO Project_is_Category VALUES('%s', '%s')"
                                category
                                projectname)]))

(defn insert-project-requirement-query [projectname req]
  (j/execute! mysql-db [(format "INSERT INTO Requirement VALUES('%s', '%s')"
                                projectname
                                req)]))

(defn wrap-quotes [variable]
  (if (= "NULL" variable)
    variable
    (str \" variable \")))

(defn insert-users [user-data-vector]
  (j/execute! mysql-db [(apply (partial format "INSERT INTO USER VALUES (%s, %s, %s, %s, %s, %s)") (map wrap-quotes user-data-vector))]))

;; Generates 25 Users
(defn gen-users []
  (let [USER-NUM 25
        username-pieces ["madeon" "brain" "yummy" "jacob" "l4p4ance"
                         "mrgoogoo" "olivia" "drake" "ava" "future"]
        password-pieces ["mom" "acow" "thiccaf" "stupid" "dumb"
                         "epidermisisshowing" "passwordissoobvi"]
        year-pieces ["freshman" "sophomore" "junior" "senior"]
        usernames (map #(str (rand-nth username-pieces) %) (range 3 (+ 3 USER-NUM)))
        passwords (map (fn [_] (str "ur" (rand-nth password-pieces))) (range 0 USER-NUM))
        user-types (map #(if (= 0 (mod % 5)) "ADMIN" "USER") (range 0 USER-NUM))
        emails (map #(str % "@gatech.edu") usernames)
        years  (map (fn [_] (rand-nth year-pieces)) (range 0 USER-NUM))
        majors (repeat USER-NUM "NULL")
        users (map vector usernames passwords user-types emails years majors)]
    users))
