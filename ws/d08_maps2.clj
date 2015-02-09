;; gorilla-repl.fileformat = 1

;; **
;;; # Maps 2
;;; 
;;; More on maps, focusing on nested maps.
;; **

;; @@
(ns d08-maps2
  (:require [cljplay.core :refer [show-examples]]
            [clojure.pprint :refer [pprint] :rename {pprint pp}]
            [clojure.string :refer [upper-case]]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def bob {:first-name "Bob" :last-name "Smith" :age 44 :children [{:age 10, :name "Anna"} {:age 6, :name "Zach"}] :jobs {:main "Barrista"} :address {:street1 "123 Anystreet" :city "Anytown" :state "CA"}})
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;d08-maps2/bob</span>","value":"#'d08-maps2/bob"}
;; <=

;; @@
(pp bob)
;; @@
;; ->
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;},
;;;  :children [{:age 10, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;}],
;;;  :last-name &quot;Smith&quot;,
;;;  :age 44,
;;;  :first-name &quot;Bob&quot;,
;;;  :jobs {:main &quot;Barrista&quot;}}
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; [assoc-in](http://clojuredocs.org/clojure.core/assoc-in) is used to set an arbitrarily nested field.
;; **

;; @@
(show-examples
  (assoc-in bob [:last-name] "Jones") ; change a field, just like assoc
  (assoc-in bob [:address :zip] "91029") ; change nested map
  (assoc-in bob [:prefs :browser] "Firefox")) ; makes paths that don't exist
;; @@
;; ->
;;; &gt; (assoc-in bob [:last-name] &quot;Jones&quot;)
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;}, :children [{:age 10, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;}], :last-name &quot;Jones&quot;, :age 44, :first-name &quot;Bob&quot;, :jobs {:main &quot;Barrista&quot;}}
;;; &gt; (assoc-in bob [:address :zip] &quot;91029&quot;)
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;, :zip &quot;91029&quot;}, :children [{:age 10, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;}], :last-name &quot;Smith&quot;, :age 44, :first-name &quot;Bob&quot;, :jobs {:main &quot;Barrista&quot;}}
;;; &gt; (assoc-in bob [:prefs :browser] &quot;Firefox&quot;)
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;}, :children [{:age 10, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;}], :last-name &quot;Smith&quot;, :age 44, :first-name &quot;Bob&quot;, :jobs {:main &quot;Barrista&quot;}, :prefs {:browser &quot;Firefox&quot;}}
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; `assoc-in` also works with vectors.
;; **

;; @@
(show-examples
  (assoc-in bob [:children 2] {:name "Jimmy" :age 0}) ; when vector already exists, an element is added
  (assoc-in bob [:friends 0] {:name "Jerry"})) ; when auto-creating paths, uses a map instead of a vector
;; @@
;; ->
;;; &gt; (assoc-in bob [:children 2] {:name &quot;Jimmy&quot;, :age 0})
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;}, :children [{:age 10, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;} {:age 0, :name &quot;Jimmy&quot;}], :last-name &quot;Smith&quot;, :age 44, :first-name &quot;Bob&quot;, :jobs {:main &quot;Barrista&quot;}}
;;; &gt; (assoc-in bob [:friends 0] {:name &quot;Jerry&quot;})
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;}, :children [{:age 10, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;}], :last-name &quot;Smith&quot;, :age 44, :first-name &quot;Bob&quot;, :jobs {:main &quot;Barrista&quot;}, :friends {0 {:name &quot;Jerry&quot;}}}
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; To update an element with a function, use [update-in](http://clojuredocs.org/clojure.core/update-in):
;; **

;; @@
(show-examples
  (update-in bob [:first-name] upper-case)
  (update-in bob [:age] inc)
  (update-in bob [:children 0 :age] inc)
  )
;; @@
;; ->
;;; &gt; (update-in bob [:first-name] upper-case)
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;}, :children [{:age 10, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;}], :last-name &quot;Smith&quot;, :age 44, :first-name &quot;BOB&quot;, :jobs {:main &quot;Barrista&quot;}}
;;; &gt; (update-in bob [:age] inc)
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;}, :children [{:age 10, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;}], :last-name &quot;Smith&quot;, :age 45, :first-name &quot;Bob&quot;, :jobs {:main &quot;Barrista&quot;}}
;;; &gt; (update-in bob [:children 0 :age] inc)
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;}, :children [{:age 11, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;}], :last-name &quot;Smith&quot;, :age 44, :first-name &quot;Bob&quot;, :jobs {:main &quot;Barrista&quot;}}
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Curiously, there is no `dissoc-in` in the standard library.  I found [one](https://github.com/clojure/core.incubator/blob/master/src/main/clojure/clojure/core/incubator.clj#L62) over in [core.incubator](https://github.com/clojure/core.incubator), which seems to be a place where experimental features are implemented and then merged into core when they mature.
;; **

;; @@
(defn dissoc-in
  "Dissociates an entry from a nested associative structure returning a new
  nested structure. keys is a sequence of keys. Any empty maps that result
  will not be present in the new structure."
  [m [k & ks :as keys]]
  (if ks
    (if-let [nextmap (get m k)]
      (let [newmap (dissoc-in nextmap ks)]
        (if (seq newmap)
          (assoc m k newmap)
          (dissoc m k)))
      m)
    (dissoc m k)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;d08-maps2/dissoc-in</span>","value":"#'d08-maps2/dissoc-in"}
;; <=

;; @@
(show-examples
  (dissoc-in bob [:first-name])
  (dissoc-in bob [:jobs :main]))
;; @@
;; ->
;;; &gt; (dissoc-in bob [:first-name])
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;}, :children [{:age 10, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;}], :last-name &quot;Smith&quot;, :age 44, :jobs {:main &quot;Barrista&quot;}}
;;; &gt; (dissoc-in bob [:jobs :main])
;;; {:address {:street1 &quot;123 Anystreet&quot;, :city &quot;Anytown&quot;, :state &quot;CA&quot;}, :children [{:age 10, :name &quot;Anna&quot;} {:age 6, :name &quot;Zach&quot;}], :last-name &quot;Smith&quot;, :age 44, :first-name &quot;Bob&quot;}
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@

;; @@
