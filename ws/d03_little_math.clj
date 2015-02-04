;; gorilla-repl.fileformat = 1

;; **
;;; # Little Math
;;; 
;;; Today, I'm exploring the simple math functions in Clojure.
;; **

;; @@
(ns d03-little-math)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Simple math is simple:
;; **

;; @@
[(+ 1 1)
 (- 4 1)
 (* 3 4)
 (/ 10 5)]
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"},{"type":"html","content":"<span class='clj-long'>12</span>","value":"12"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"}],"value":"[2 3 12 2]"}
;; <=

;; **
;;; Then there are helper methods:
;; **

;; @@
[(quot 10 3) ; quotient
 (rem 10 3)  ; remainder
 (inc 4)     ; increment
 (dec 5)     ; decrement
 (mod 10 3)] ; modulus
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>5</span>","value":"5"},{"type":"html","content":"<span class='clj-long'>4</span>","value":"4"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"}],"value":"[3 1 5 4 1]"}
;; <=

;; **
;;; Division has the interesting property that it creates rational numbers when used on integers.
;; **

;; @@
[(/ 1 3)
 (/ 1.0 3.0)]
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-ratio'>1/3</span>","value":"1/3"},{"type":"html","content":"<span class='clj-double'>0.3333333333333333</span>","value":"0.3333333333333333"}],"value":"[1/3 0.3333333333333333]"}
;; <=

;; **
;;; You can even add these numbers and Clojure will do the math for you:
;; **

;; @@
(+ (/ 1 3) (/ 1 6))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-ratio'>1/2</span>","value":"1/2"}
;; <=

;; **
;;; And finally, there's max and min to find, well, the maximum and minimum out of their arguments.
;; **

;; @@
[(max 5 29 1 3)
 (min 5 29 1 3)
 (apply min [5 29 1 3])]  ; flattening out a collection with apply
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>29</span>","value":"29"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"}],"value":"[29 1 1]"}
;; <=
