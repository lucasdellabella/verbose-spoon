(ns verbose-spoon.model.core
  (:require [verbose-spoon.model.test-queries :refer [major-test-query]]))

(defn fetch-major-list []
 (map :major_name (major-test-query)))
