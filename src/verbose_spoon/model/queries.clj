(ns verbose-spoon.model.queries
  (:require [clojure.java.jdbc :as j]))

(def mysql-db {:dbtype "mysql"
               :dbname "Phase3"
               :user "root"
               :password "password"})

;; If there are weird bugs,
;; it might be because of this
(Class/forName "com.mysql.jdbc.Driver")

(defn today []
  (.format (java.text.SimpleDateFormat. "yyyy-MM-dd") (new java.util.Date)))

(defn major-query []
  (j/query mysql-db ["SELECT Major_Name FROM MAJOR"]))

(defn major-department-query []
  (j/query mysql-db ["SELECT Major_Name, Dept_Name FROM MAJOR"]))

(defn major-test-insert [] ;;TODO: change this to EXECUTE func
  (j/execute! mysql-db ["INSERT INTO MAJOR VALUES (\"Business\",\"College of Business\")"]))

(defn designation-query []
  (j/query mysql-db ["SELECT Name FROM DESIGNATION"]))

(defn category-query []
  (j/query mysql-db ["SELECT Name FROM CATEGORY"]))

(defn user-password-query [username]
  (j/query mysql-db [(format "SELECT Username, Password FROM User WHERE Username='%s'"
                             username)]))

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
;view-project-report
(defn view-project-report []
  (j/query mysql-db ["SELECT Project_name, COUNT(Project_name) as Applicants FROM Apply GROUP BY Project_name ORDER BY COUNT(Project_name) DESC LIMIT 10"]))

;view-course view
(defn view-course-query [course]
  (j/query mysql-db [(format "SELECT * FROM Course NATURAL JOIN Course_is_category WHERE Course_Num = '%s'" course)]))

;view-apply-project view
(defn view-project-query [project]
  (j/query mysql-db [(format "select * from project where name = '%s'" project)]))

(defn view-project-category-query [project_name]
  (j/query mysql-db [(format "SELECT Category_name FROM Project_is_category WHERE Project_name = '%s'" project_name)]))

(defn view-project-requirement-query [project_name]
  (j/query mysql-db [(format "SELECT Requirement_type FROM Requirement WHERE Project_name = '%s'" project_name)]))

;view-applications
(defn view-applications-query []
  (j/query mysql-db ["SELECT User.Username, Apply.Project_name, User.Major, User.Year, Apply.Status FROM Apply JOIN User ON Apply.Username = User.Username"]))
(defn view-applications-accept[user project]
  (j/execute! mysql-db [(format "UPDATE Apply SET Status = 'Accepted' WHERE Username = '%s' AND Project_Name = '%s'" user project)]))
(defn view-applications-reject[user project]
  (j/execute! mysql-db [(format "UPDATE Apply SET Status = 'Rejected' WHERE Username = '%s' AND Project_Name = '%s'" user project)]))

;my-application
(defn my-application-query [user]
  (j/query mysql-db [(format "SELECT Date, Project_name, Status FROM Apply WHERE Username = '%s'" user)]))

(defn application-status-query [user]
  (j/query mysql-db [(format "SELECT Project_Name, Date, Status FROM APPLY where Username = %s" user)]))

;view-application-report
(defn view-application-report-query []
  (j/query mysql-db ["SELECT * FROM major_concat_view NATURAL JOIN apply_percent_view"]))

; (defn view-application-report-num-applications-query [name]
;   (j/query mysql-db [(format "SELECT COUNT(*) as Count FROM Apply WHERE Project_name = '%s'" name)]))
;
; (defn view-application-report-num-accepted-query [name]
;   (j/query mysql-db [(format "SELECT COUNT(*) as Count FROM Apply WHERE Project_name = '%s' AND Status = 'Accepted'" name)]))
;
; (defn view-application-report-top-majors-query [name]
;   (j/query mysql-db [(format "SELECT Major, COUNT(Major) as Count FROM Apply NATURAL JOIN User WHERE Project_name = '%s' GROUP BY Major HAVING COUNT(Major) > 0 ORDER BY 2 DESC LIMIT 3" name)]))


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

(defn update-profile-query [major year username]
  (j/execute! mysql-db [(format "UPDATE User SET Major='%s', Year='%s' WHERE Username='%s'" major year username)]))

(defn check-rejected-application-query [projectname username]
  (j/query mysql-db [(format "SELECT * FROM Apply WHERE Project_name='%s' AND Username='%s' AND Status='Rejected'"
                             projectname
                             username)]))

(defn update-apply-query [projectname username]
  (j/query mysql-db [(format "INSERT INTO Apply VALUES('%s', '%s', %s, 'Pending')"
                             projectname
                             username
                             (today))]))

(defn insert-register-user [username password email]
  (j/execute! mysql-db [(format "INSERT INTO User VALUES('%s', '%s', 'USER', '%s', NULL, NULL)"
                                username
                                password
                                email)]))

(defn check-user-email-exists-query [email]
  (j/query mysql-db [(format "SELECT * FROM User WHERE Gt_Email='%s'" email)]))

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
