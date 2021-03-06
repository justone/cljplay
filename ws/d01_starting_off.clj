;; gorilla-repl.fileformat = 1

;; **
;;; # Starting Off
;;; 
;;; This will (hopefully) be a series of worksheets that I create as I learn Clojure.
;; **

;; @@
(ns d01-starting-off
  (:require [gorilla-plot.core :as plot]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(print "Print value is separate from return value.")
;; @@
;; ->
;;; Print value is separate from return value.
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Trying out the various equality functions.
;; **

;; @@
[(= true true)
 (= true nil)
 (= false false)]
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"},{"type":"html","content":"<span class='clj-unkown'>false</span>","value":"false"},{"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}],"value":"[true false true]"}
;; <=
