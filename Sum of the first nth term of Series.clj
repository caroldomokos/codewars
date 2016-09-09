(defn series-sum
[n]
(format "%.2f" (float (reduce + (map (fn [ranger] (/ 1 ranger)) (take n (iterate #(+ 3 %) 1)))))))
;;; the code above passses the tests but times out...So I need to make it faster

(defn series-sum [n]
  (format "%.2f" (float (reduce +(take n (map #(/ 1 %) (iterate #(+ 3 %) 1)))))) 
)
;;; also times out - same overall logic
