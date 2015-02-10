(ns cljplay.core
  (:gen-class)
  (:require [clojure.pprint :refer [pprint]]))

(defmacro show-example
  [code]
  `(do
     (println (str "> " (quote ~code)))
     (pprint (eval ~code))))

(defmacro show-sub-example
  [code]
  `(do
     (println (str "> " ~code))
     (pprint (eval ~code))))

(defmacro show-examples
  [& rest]
  `(do ~@(map #(show-sub-example %) rest)))

(defn -main [& args]
  (println "Don't run me, use 'lein gorilla'."))
