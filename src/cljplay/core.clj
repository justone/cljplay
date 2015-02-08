(ns cljplay.core
  (:gen-class))

(defmacro show-example
  [code]
  `(println (str "> " (quote ~code) "\n" (eval ~code))))

(defmacro show-sub-example
  [code]
  `(println (str "> " ~code "\n" (eval ~code))))

(defmacro show-examples
  [& rest]
  `(do ~@(map #(show-sub-example %) rest)))

(defn -main [& args]
  (println "Don't run me, use 'lein gorilla'."))
