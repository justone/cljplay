;; gorilla-repl.fileformat = 1

;; **
;;; # Pretty printing
;;; 
;;; How do we pretty print clojure data structures?
;; **

;; @@
(ns d06-pretty-print)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; There is the [clojure.pprint](https://clojure.github.io/clojure/clojure.pprint-api.html) package and [its documentation](https://clojure.github.io/clojure/doc/clojure/pprint/PrettyPrinting.html).
;; **

;; @@
(require '[clojure.pprint :refer :all])
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(pprint (for [x (range 10)] (range x))) ; example from the docs
;; @@
;; ->
;;; (()
;;;  (0)
;;;  (0 1)
;;;  (0 1 2)
;;;  (0 1 2 3)
;;;  (0 1 2 3 4)
;;;  (0 1 2 3 4 5)
;;;  (0 1 2 3 4 5 6)
;;;  (0 1 2 3 4 5 6 7)
;;;  (0 1 2 3 4 5 6 7 8))
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(pprint {:foo [1 2 3] :bar #{:a :b :c} :baz [4 5 6] :qux #{:d :e :f} :nested {:hank [7 8 9] :frank #{:a :b :c}}})
;; @@
;; ->
;;; {:baz [4 5 6],
;;;  :bar #{:c :b :a},
;;;  :nested {:frank #{:c :b :a}, :hank [7 8 9]},
;;;  :qux #{:e :d :f},
;;;  :foo [1 2 3]}
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Printing out a larger data structure.  In this case, a web request in compojure.
;; **

;; @@
(pprint {:ssl-client-cert nil, :remote-addr "0:0:0:0:0:0:0:1", :params {}, :route-params {}, :headers {"user-agent curl/7.38.0", "accept */*", "host" "localhost:10555"}, :server-port 10555, :content-length nil, :form-params {}, :query-params {}, :content-type nil, :character-encoding nil, :uri "/foo", :server-name "localhost", :query-string nil, :body "foo", :scheme :http, :request-method :get})
;; @@
;; ->
;;; {:ssl-client-cert nil,
;;;  :remote-addr &quot;0:0:0:0:0:0:0:1&quot;,
;;;  :params {},
;;;  :route-params {},
;;;  :headers
;;;  {&quot;host&quot; &quot;localhost:10555&quot;, &quot;user-agent curl/7.38.0&quot; &quot;accept */*&quot;},
;;;  :server-port 10555,
;;;  :content-length nil,
;;;  :form-params {},
;;;  :query-params {},
;;;  :content-type nil,
;;;  :character-encoding nil,
;;;  :uri &quot;/foo&quot;,
;;;  :server-name &quot;localhost&quot;,
;;;  :query-string nil,
;;;  :body &quot;foo&quot;,
;;;  :scheme :http,
;;;  :request-method :get}
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Pretty printing code too!
;; **

;; @@
(pprint '(pprint {:foo [1 2 3] :bar #{:a :b :c} :baz [4 5 6] :qux #{:d :e :f} :nested {:hank [7 8 9] :frank #{:a :b :c}}}))
;; @@
;; ->
;;; (pprint
;;;  {:foo [1 2 3],
;;;   :bar #{:c :b :a},
;;;   :baz [4 5 6],
;;;   :qux #{:e :d :f},
;;;   :nested {:hank [7 8 9], :frank #{:c :b :a}}})
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; This is from my toy [loopcljs](https://github.com/justone/loopcljs) project.  Notice how the function literals are expanded too.
;; **

;; @@
(pprint '(defn load-data! [] (let [jsonp (goog.net.Jsonp. "http://www.reddit.com/r/perfectloops/top.json" "jsonp")] (.send jsonp (clj->js {:sort "top" :t "week"}) (fn [datajs] (let [data    (walk/keywordize-keys (js->clj datajs)) entries (get-in data [:data :children])] (->> entries (map :data) (remove :over_18) (map :url) (filter #(re-matches #".*\.gifv?$" %)) (map #(string/replace % "gifv" "gif")) (swap! app-state assoc :images))))))))
;; @@
;; ->
;;; (defn
;;;  load-data!
;;;  []
;;;  (let
;;;   [jsonp
;;;    (goog.net.Jsonp.
;;;     &quot;http://www.reddit.com/r/perfectloops/top.json&quot;
;;;     &quot;jsonp&quot;)]
;;;   (.send
;;;    jsonp
;;;    (clj-&gt;js {:sort &quot;top&quot;, :t &quot;week&quot;})
;;;    (fn
;;;     [datajs]
;;;     (let
;;;      [data
;;;       (walk/keywordize-keys (js-&gt;clj datajs))
;;;       entries
;;;       (get-in data [:data :children])]
;;;      (-&gt;&gt;
;;;       entries
;;;       (map :data)
;;;       (remove :over_18)
;;;       (map :url)
;;;       (filter (fn* [p1__6293#] (re-matches #&quot;.*\.gifv?$&quot; p1__6293#)))
;;;       (map (fn* [p1__6294#] (string/replace p1__6294# &quot;gifv&quot; &quot;gif&quot;)))
;;;       (swap! app-state assoc :images)))))))
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=
