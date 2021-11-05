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


