;; gorilla-repl.fileformat = 1

;; **
;;; # More Little Math
;;; 
;;; Today, more math exploration.
;; **

;; @@
(ns d04-more-little-math)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Numbers are Java objects.
;; **

;; @@
[(class 1.0)
 (class 1)
 (class 1M)
 (class 1N)
 (class (/ 1 3))]
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-class'>java.lang.Double</span>","value":"java.lang.Double"},{"type":"html","content":"<span class='clj-class'>java.lang.Long</span>","value":"java.lang.Long"},{"type":"html","content":"<span class='clj-class'>java.math.BigDecimal</span>","value":"java.math.BigDecimal"},{"type":"html","content":"<span class='clj-class'>clojure.lang.BigInt</span>","value":"clojure.lang.BigInt"},{"type":"html","content":"<span class='clj-class'>clojure.lang.Ratio</span>","value":"clojure.lang.Ratio"}],"value":"[java.lang.Double java.lang.Long java.math.BigDecimal clojure.lang.BigInt clojure.lang.Ratio]"}
;; <=

;; **
;;; Operations are flexible, not requiring an explicit cast.  The result is upgraded to the type with more precision.
;; **

;; @@
[(+ 1 1.0)
 (+ 1 (/ 1 3))
 (+ 1 1M)]
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>2.0</span>","value":"2.0"},{"type":"html","content":"<span class='clj-ratio'>4/3</span>","value":"4/3"},{"type":"html","content":"<span class='clj-bigdecimal'>2M</span>","value":"2M"}],"value":"[2.0 4/3 2M]"}
;; <=

;; **
;;; When performance is important, you can add type hints.  This slightly modified example comes from [the java interop page on clojure.org](http://clojure.org/java_interop):
;; **

;; @@
(defn foo [n]
  (loop [i 0]
    (if (< i n)
      (recur (inc i))
      i)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;d04-more-little-math/foo</span>","value":"#'d04-more-little-math/foo"}
;; <=

;; @@
(defn foo2 [^long n]
  (loop [i (long 0)]
    (if (< i n)
      (recur (inc i))
      i)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;d04-more-little-math/foo2</span>","value":"#'d04-more-little-math/foo2"}
;; <=

;; @@
[(time (foo 100000))
 (time (foo2 100000))]
;; @@
;; ->
;;; &quot;Elapsed time: 11.674575 msecs&quot;
;;; &quot;Elapsed time: 5.101363 msecs&quot;
;;; 
;; <-
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>100000</span>","value":"100000"},{"type":"html","content":"<span class='clj-long'>100000</span>","value":"100000"}],"value":"[100000 100000]"}
;; <=
