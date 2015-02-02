;; gorilla-repl.fileformat = 1

;; **
;;; # REPL Helpers
;;; 
;;; Yesterday, I tried to use the repl helpers (like doc and source) but they weren't found.  Then I realized that they are part of the `clojure.repl` namespace and had to be included manually.
;; **

;; @@
(ns d02-repl-helpers)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(doc print)
;; @@

;; **
;;; Requiring `clojure.repl` manually.
;; **

;; @@
(require `[clojure.repl :refer :all])
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Now the helpers work.
;; **

;; @@
(doc print)
;; @@
;; ->
;;; -------------------------
;;; clojure.core/print
;;; ([&amp; more])
;;;   Prints the object(s) to the output stream that is the current value
;;;   of *out*.  print and println produce output for human consumption.
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(source doc)
;; @@
;; ->
;;; (defmacro doc
;;;   &quot;Prints documentation for a var or special form given its name&quot;
;;;   {:added &quot;1.0&quot;}
;;;   [name]
;;;   (if-let [special-name (&#x27;{&amp; fn catch try finally try} name)]
;;;     (#&#x27;print-doc (#&#x27;special-doc special-name))
;;;     (cond
;;;       (special-doc-map name) `(#&#x27;print-doc (#&#x27;special-doc &#x27;~name))
;;;       (find-ns name) `(#&#x27;print-doc (#&#x27;namespace-doc (find-ns &#x27;~name)))
;;;       (resolve name) `(#&#x27;print-doc (meta (var ~name))))))
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(apropos "get")
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>get-possibly-unbound-var</span>","value":"get-possibly-unbound-var"},{"type":"html","content":"<span class='clj-symbol'>get-scope-and-prefix</span>","value":"get-scope-and-prefix"},{"type":"html","content":"<span class='clj-symbol'>try-get-ns-from-context</span>","value":"try-get-ns-from-context"},{"type":"html","content":"<span class='clj-symbol'>get-pretty-writer</span>","value":"get-pretty-writer"},{"type":"html","content":"<span class='clj-symbol'>get-thread-bindings</span>","value":"get-thread-bindings"},{"type":"html","content":"<span class='clj-symbol'>aget</span>","value":"aget"},{"type":"html","content":"<span class='clj-symbol'>get</span>","value":"get"},{"type":"html","content":"<span class='clj-symbol'>get-method</span>","value":"get-method"},{"type":"html","content":"<span class='clj-symbol'>get-proxy-class</span>","value":"get-proxy-class"},{"type":"html","content":"<span class='clj-symbol'>get-validator</span>","value":"get-validator"},{"type":"html","content":"<span class='clj-symbol'>var-get</span>","value":"var-get"},{"type":"html","content":"<span class='clj-symbol'>get-in</span>","value":"get-in"},{"type":"html","content":"<span class='clj-symbol'>get</span>","value":"get"},{"type":"html","content":"<span class='clj-symbol'>get-header</span>","value":"get-header"},{"type":"html","content":"<span class='clj-symbol'>get-all-members</span>","value":"get-all-members"},{"type":"html","content":"<span class='clj-symbol'>try-get-object-class</span>","value":"try-get-object-class"}],"value":"(get-possibly-unbound-var get-scope-and-prefix try-get-ns-from-context get-pretty-writer get-thread-bindings aget get get-method get-proxy-class get-validator var-get get-in get get-header get-all-members try-get-object-class)"}
;; <=

;; @@
(find-doc "nested associative")
;; @@
;; ->
;;; -------------------------
;;; clojure.core/assoc-in
;;; ([m [k &amp; ks] v])
;;;   Associates a value in a nested associative structure, where ks is a
;;;   sequence of keys and v is the new value and returns a new nested structure.
;;;   If any levels do not exist, hash-maps will be created.
;;; -------------------------
;;; clojure.core/get-in
;;; ([m ks] [m ks not-found])
;;;   Returns the value in a nested associative structure,
;;;   where ks is a sequence of keys. Returns nil if the key
;;;   is not present, or the not-found value if supplied.
;;; -------------------------
;;; clojure.core/update-in
;;; ([m [k &amp; ks] f &amp; args])
;;;   &#x27;Updates&#x27; a value in a nested associative structure, where ks is a
;;;   sequence of keys and f is a function that will take the old value
;;;   and any supplied args and return the new value, and returns a new
;;;   nested structure.  If any levels do not exist, hash-maps will be
;;;   created.
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Except for [javadoc](http://clojuredocs.org/clojure.java.javadoc/javadoc), that's over in `clojure.java.javadoc`.
;; **

;; @@
(javadoc String)
;; @@

;; @@
(require `[clojure.java.javadoc :refer :all])
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(javadoc String)
;; @@

;; **
;;; Guess that's not going to work either.  Oh well.
;; **
