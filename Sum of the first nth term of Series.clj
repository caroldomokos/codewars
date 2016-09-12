;;; Task:
;;Your task is to write a function which returns the sum of following series upto nth term(parameter).
;;Series: 1 + 1/4 + 1/7 + 1/10 + 1/13 + 1/16 +...
;;Rules:
;;    You need to round the answer upto 2 decimal places and return it as String.
;;    If the given value is 0 then it should return 0.00
;;    You will only be given Natural Numbers as arguments.
;;Examples:
;;SeriesSum(1) => 1 = "1"
;;SeriesSum(2) => 1 + 1/4 = "1.25"
;;SeriesSum(5) => 1 + 1/4 + 1/7 + 1/10 + 1/13 = "1.57"
;;+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
;; basic algorithm: create a seq of 1 4 7 etc..., run map to get 1/X and reduce to sum them all up. Than use float to get
;; floating point and format to have 2 decimal places
;;1)
(defn series-sum
[n]
(format "%.2f" (float (reduce + (map (fn [ranger] (/ 1 ranger)) (take n (iterate #(+ 3 %) 1)))))))
;;; the code above passses the tests but times out...So I need to make it faster
;;;  Time: 2102ms Passed: 3 
;;2)
(defn series-sum [n]
  (format "%.2f" (float (reduce +(take n (map #(/ 1 %) (iterate #(+ 3 %) 1)))))))
;;; also times out - same overall logic
;; Time: 2419ms Passed: 3 
;;3)
;;; with range instead of iterate
;;; Time: 2477ms Passed: 3 
(defn series-sum [n]
   (format "%.2f" (float (reduce + (map #(/ 1 %) (range 1 (* 3 n) 3))))))
;;;slower!!!!
;;; actually times are mixed but they all are around 2s. I also tried using fold and recducers/map but not faster...
;;4)
;; recursive option
(defn series-sum
  ([n] (series-sum n 0))
  ([n acc] 
    (if (= n 0)
      (format "%.2f" (float acc))
      (recur (- n 1) (+ acc (/ 1 (+ 1 (* 3 (- n 1)))))))))
;; about the same
;;  Time: 2430ms Passed: 3 
;;; I am afraid we need a mathematical solutio
;;5) list comprehensions
(defn series-sum [n]
  (format "%.2f" (float (reduce + (for [x (range 1 (* 3 n) 3) :let [y (/ 1 x)]]  y)))))
;;; same result. For large number it takes a lot of time to calculate...so based on this approximation that was good
;;for Euler I will try some code https://en.wikipedia.org/wiki/Euler%E2%80%93Mascheroni_constant
