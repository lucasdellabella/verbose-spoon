(ns verbose-spoon.model.core
  (:require [verbose-spoon.model.queries :refer [major-query category-query designation-query]]))

(defn fetch-major-list []
 (map :major_name (major-query)))

(defn fetch-category-list []
 (map :category_name (category-query)))

(defn fetch-designation-list []
 (map :designation_name (designation-query)))
