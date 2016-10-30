(ns verbose-spoon.model.test-queries
  (:require [clojure.java.jdbc :as j]))

(def mysql-db {:dbtype "mysql"
               :dbname "test"
               :user "root"
               :password ""})

;; If there are weird bugs,
;; it might be because of this
(Class/forName "com.mysql.jdbc.Driver")

(defn major-test-query []
  (j/query mysql-db ["SELECT Major_Name FROM MAJOR"]))

(defn major-test-insert []
  (j/query mysql-db ["INSERT INTO MAJOR VALUES (\"Business\",\"College of Business\")"]))
