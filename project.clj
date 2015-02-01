(defproject cljplay "0.1.0-SNAPSHOT"
  :description "Learning by playing with Clojure."
  :url "https://github.com/justone/cljplay"
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot cljplay.core
  :target-path "target/%s"
  :plugins [[lein-gorilla "0.3.4"]]
  :profiles {:uberjar {:aot :all}})
