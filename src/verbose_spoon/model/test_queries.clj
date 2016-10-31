(ns verbose-spoon.model.test-queries
  (:require [clojure.java.jdbc :as j]))

(def mysql-db {:dbtype "mysql"
               :dbname "Phase3"
               :user "root"
               :password "password"})

;; If there are weird bugs,
;; it might be because of this
(Class/forName "com.mysql.jdbc.Driver")

(defn major-test-query []
  (j/query mysql-db ["SELECT Major_Name FROM MAJOR"]))

(defn major-test-insert [] ;;TODO: change this to EXECUTE func
  (j/query mysql-db ["INSERT INTO MAJOR VALUES (\"Business\",\"College of Business\")"]))
