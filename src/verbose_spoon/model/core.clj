(ns verbose-spoon.model.core
  (:require [verbose-spoon.model.queries :refer [major-test-query category-test-query designation-test-query]]))

(defn fetch-major-list []
 (map :major_name (major-test-query)))

(defn fetch-category-list []
 (map :category_name (category-test-query)))

(defn fetch-designation-list []
 (map :designation_name (designation-test-query)))
