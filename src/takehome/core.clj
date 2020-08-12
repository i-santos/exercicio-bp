(ns takehome.core
  (:require [java-time :as time]))

(defn can-access? [object purchase]
  (if (= (:type object) :movie)
    (and (= (:type purchase) :patriota)
         (time/before? (:subscription-start purchase)
                       (:released-at object)
                       (:subscription-end purchase)))))
