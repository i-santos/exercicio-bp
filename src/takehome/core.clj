(ns takehome.core
  (:require [java-time :as time]))

(defn can-usuario-access? [content]
  (case (:type content)
    :series true
    false))

(defn can-patriota-access? [content]
  (or (can-usuario-access? content)
      (case (:type content)
        :podcast true
        :debate true
        :interview true
        false)))


(defn can-premium-access? [content]
  (or (can-patriota-access? content)
      (case (:type content)
        :course true
        false)))

(defn can-mecenas-access? [content]
  (or (can-premium-access? content)
      (case (:type content)
        :patron true
        false)))

(defn can-access? [user content]
  (and (time/before? (time/local-date-time (:subscription-start user))
                     (time/local-date-time (:released-at        content))
                     (time/local-date-time (:subscription-end   user)))
       (cond
         (= (:type user) :usuario)  (can-usuario-access?  content)
         (= (:type user) :patriota) (can-patriota-access? content)
         (= (:type user) :premium)  (can-premium-access?  content)
         (= (:type user) :mecenas)  (can-mecenas-access?  content))))

(defn get-content-list [user content]
  (filter (fn [c] (= true (can-access? user c))) content))