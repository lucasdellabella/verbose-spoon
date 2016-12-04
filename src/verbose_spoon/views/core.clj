(ns verbose-spoon.views.core)

(defn application-row [date project status]
  [:tr
    [:td date]
    [:td project]
    [:td status]])

(defn commacat [list]
  (if (empty? list)
    list
    (reduce (fn [cur next] ()(str cur ", " next)) list)))
