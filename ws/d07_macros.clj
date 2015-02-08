;; gorilla-repl.fileformat = 1

;; **
;;; # Gorilla REPL
;;; 
;;; Welcome to gorilla :-)
;;; 
;;; Shift + enter evaluates code. Hit ctrl+g twice in quick succession or click the menu icon (upper-right corner) for more commands ...
;;; 
;;; It's a good habit to run each worksheet in its own namespace: feel free to use the declaration we've provided below if you'd like.
;; **

;; @@
(ns d07-macros)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; First off, doing some examples from Clojure for the Brave and True's section on [Writing Macros](http://www.braveclojure.com/writing-macros/).
;; **

;; @@
(macroexpand '(when boolean-expression
                expression-1
                expression-2
                expression-3))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>if</span>","value":"if"},{"type":"html","content":"<span class='clj-symbol'>boolean-expression</span>","value":"boolean-expression"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>do</span>","value":"do"},{"type":"html","content":"<span class='clj-symbol'>expression-1</span>","value":"expression-1"},{"type":"html","content":"<span class='clj-symbol'>expression-2</span>","value":"expression-2"},{"type":"html","content":"<span class='clj-symbol'>expression-3</span>","value":"expression-3"}],"value":"(do expression-1 expression-2 expression-3)"}],"value":"(if boolean-expression (do expression-1 expression-2 expression-3))"}
;; <=

;; @@
(defmacro postfix-notation
  "I'm too indie for prefix notation"
  [expression]
  (conj (butlast expression) (last expression)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;d07-macros/postfix-notation</span>","value":"#'d07-macros/postfix-notation"}
;; <=

;; @@
(postfix-notation (1 1 +))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>2</span>","value":"2"}
;; <=

;; @@
(macroexpand '(postfix-notation (1 1 +)))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>+</span>","value":"+"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"}],"value":"(+ 1 1)"}
;; <=

;; @@
(defn criticize-code
  [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-critic
  [{:keys [good bad]}]
  `(do ~@(map #(apply criticize-code %)
              [["Sweet lion of Zion, this is bad code:" bad]
               ["Great cow of Moscow, this is good code:" good]])))

(code-critic {:good (+ 1 1) :bad (1 + 1)})
;; @@
;; ->
;;; Sweet lion of Zion, this is bad code: (1 + 1)
;;; Great cow of Moscow, this is good code: (+ 1 1)
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Now to try and make a macro that I can use in my worksheets to show several examples at once.  I've done this previously by just wrapping all the forms in a vector, but then it's hard to see which result goes with each piece of code:
;; **

;; @@
[(+ 1 1)
 (* 4 6)
 (/ 1 4)]
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>24</span>","value":"24"},{"type":"html","content":"<span class='clj-ratio'>1/4</span>","value":"1/4"}],"value":"[2 24 1/4]"}
;; <=

;; **
;;; First, a macro to just do one expression:
;; **

;; @@
(defmacro show-example1
  [code]
  `(println (str "> " (quote ~code) "\n" (eval ~code))))

(show-example1 (+ 1 1))
;; @@
;; ->
;;; &gt; (+ 1 1)
;;; 2
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; That seems to work pretty well, now how about showing several examples:
;; **

;; @@
(defmacro show-examples1
  [& rest]
  `(do ~@(map #(show-example1 %) rest)))

(show-examples1
  (+ 1 1)
  (* 4 6)
  (if true
    "true"
    "false"))
;; @@
;; ->
;;; &gt; p1__6631#
;;; 2
;;; &gt; p1__6631#
;;; 24
;;; &gt; p1__6631#
;;; true
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Hm... That seems to get the right results, but the code isn't printing out correctly.  It seems the `(quote ~code)` is going to far.  Replacing it with just `~code` makes it all work.
;; **

;; @@
(defmacro show-example2
  [code]
  `(println (str "> " ~code "\n" (eval ~code))))

(defmacro show-examples2
  [& rest]
  `(do ~@(map #(show-example %) rest)))

(show-examples2
  (+ 1 1)
  (* 4 6)
  (if true
    "true"
    "false"))
;; @@
;; ->
;;; &gt; (+ 1 1)
;;; 2
;;; &gt; (* 4 6)
;;; 24
;;; &gt; (if true &quot;true&quot; &quot;false&quot;)
;;; true
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; The problem is that now `show-example2` doesn't work:
;; **

;; @@
(show-example2 (+ 1 1))
;; @@
;; ->
;;; &gt; 2
;;; 2
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Macro expanding shows that the example code in the middle isn't quoted.
;; **

;; @@
(macroexpand '(show-example2 (+ 1 1)))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/println</span>","value":"clojure.core/println"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/str</span>","value":"clojure.core/str"},{"type":"html","content":"<span class='clj-string'>&quot;&gt; &quot;</span>","value":"\"> \""},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>+</span>","value":"+"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"}],"value":"(+ 1 1)"},{"type":"html","content":"<span class='clj-string'>&quot;\\n&quot;</span>","value":"\"\\n\""},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/eval</span>","value":"clojure.core/eval"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>+</span>","value":"+"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"}],"value":"(+ 1 1)"}],"value":"(clojure.core/eval (+ 1 1))"}],"value":"(clojure.core/str \"> \" (+ 1 1) \"\\n\" (clojure.core/eval (+ 1 1)))"}],"value":"(clojure.core/println (clojure.core/str \"> \" (+ 1 1) \"\\n\" (clojure.core/eval (+ 1 1))))"}
;; <=

;; **
;;; Tried making the singular one make a list from it's arguments, but that doesn't work either:
;; **

;; @@
(defmacro show-example3
  [& rest]
  `(println (str "> " (quote ~rest) "\n") (eval ~rest)))

(defmacro show-examples3
  [& rest]
  `(do ~@(map #(show-example3 %) rest)))

(show-example3 + 1 1)
(show-examples3
  (+ 1 1)
  (* 4 6))
;; @@
;; ->
;;; &gt; (+ 1 1)
;;;  2
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; It has something to do with how the macro is expanded.  Calling it with a list makes the eval try to call a list as a function.
;; **

;; @@
(macroexpand '(show-example3 + 1 1))
(macroexpand '(show-example3 (+ 1 1)))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/println</span>","value":"clojure.core/println"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/str</span>","value":"clojure.core/str"},{"type":"html","content":"<span class='clj-string'>&quot;&gt; &quot;</span>","value":"\"> \""},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>quote</span>","value":"quote"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>+</span>","value":"+"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"}],"value":"(+ 1 1)"}],"value":"((+ 1 1))"}],"value":"(quote ((+ 1 1)))"},{"type":"html","content":"<span class='clj-string'>&quot;\\n&quot;</span>","value":"\"\\n\""}],"value":"(clojure.core/str \"> \" (quote ((+ 1 1))) \"\\n\")"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/eval</span>","value":"clojure.core/eval"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>+</span>","value":"+"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"}],"value":"(+ 1 1)"}],"value":"((+ 1 1))"}],"value":"(clojure.core/eval ((+ 1 1)))"}],"value":"(clojure.core/println (clojure.core/str \"> \" (quote ((+ 1 1))) \"\\n\") (clojure.core/eval ((+ 1 1))))"}
;; <=

;; @@
;(show-examples3
;  (macroexpand '(show-example3 + 1 1))
;  (macroexpand '(show-example2 (+ 1 1)))
;  (macroexpand '(show-example2 (+ 1 1))))
;; @@

;; **
;;; For now, I'll just use a different sub-macro:
;; **

;; @@
(defmacro show-example
  [code]
  `(println (str "> " (quote ~code) "\n" (eval ~code))))

(defmacro show-sub-example
  [code]
  `(println (str "> " ~code "\n" (eval ~code))))

(defmacro show-examples
  [& rest]
  `(do ~@(map #(show-sub-example %) rest)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;user/show-examples</span>","value":"#'user/show-examples"}
;; <=

;; @@
(show-example (+ 1 1))
(show-examples
  (+ 1 1)
  (- 2 3)
  (== 1 1.0))
;; @@
;; ->
;;; &gt; (+ 1 1)
;;; 2
;;; &gt; (+ 1 1)
;;; 2
;;; &gt; (- 2 3)
;;; -1
;;; &gt; (== 1 1.0)
;;; true
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Odd, quoting the arg to show-sub-example seems to make it work.  Maybe this is a clue to unifying the two:
;; **

;; @@
(show-sub-example '(+ 1 1))
(show-example (+ 1 1))
;; @@
;; ->
;;; &gt; (+ 1 1)
;;; 2
;;; &gt; (+ 1 1)
;;; 2
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=
