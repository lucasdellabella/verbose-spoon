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

(defn insert-course-query [coursename coursenum instructor numstudents designation]
  (j/execute! mysql-db [(format "INSERT INTO Course VALUES(%s, %s, %s, %s, %s)"
                                coursename
                                coursenum
                                instructor
                                numstudents
                                designation)]))

(defn insert-course-is-category-query [coursename category]
  (j/execute! mysql-db [(format "INSERT INTO Course_is_Category VALUES(%s, %s)"
                                coursename
                                category)]))

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
