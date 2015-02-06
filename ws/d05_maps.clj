;; gorilla-repl.fileformat = 1

;; **
;;; # Maps
;;; 
;;; Some simple map stuff.
;; **

;; @@
(ns d05-maps)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Maps can have anything for keys and values.
;; **

;; @@
{"foo" "bar" 1 "baz" #{"hank"} 3}
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;foo&quot;</span>","value":"\"foo\""},{"type":"html","content":"<span class='clj-string'>&quot;bar&quot;</span>","value":"\"bar\""}],"value":"[\"foo\" \"bar\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"list-like","open":"<span class='clj-set'>#{</span>","close":"<span class='clj-set'>}</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;hank&quot;</span>","value":"\"hank\""}],"value":"#{\"hank\"}"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"[#{\"hank\"} 3]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-string'>&quot;baz&quot;</span>","value":"\"baz\""}],"value":"[1 \"baz\"]"}],"value":"{\"foo\" \"bar\", #{\"hank\"} 3, 1 \"baz\"}"}
;; <=

;; **
;;; But usually the keys in maps are keywords:
;; **

;; @@
(def map2 {:foo "bar" :1 "baz" :hank 3})
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;d05-maps/map2</span>","value":"#'d05-maps/map2"}
;; <=

;; **
;;; Keywords are particularly useful for accessing values because they double as functions:
;; **

;; @@
[(:foo map2)
 (get map2 :foo)]
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;bar&quot;</span>","value":"\"bar\""},{"type":"html","content":"<span class='clj-string'>&quot;bar&quot;</span>","value":"\"bar\""}],"value":"[\"bar\" \"bar\"]"}
;; <=

;; **
;;; Values can be added by using `assoc` and `dissoc`, among other things:
;; **

;; @@
(assoc map2 :frank 10)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:frank</span>","value":":frank"},{"type":"html","content":"<span class='clj-long'>10</span>","value":"10"}],"value":"[:frank 10]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:hank</span>","value":":hank"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"[:hank 3]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:1</span>","value":":1"},{"type":"html","content":"<span class='clj-string'>&quot;baz&quot;</span>","value":"\"baz\""}],"value":"[:1 \"baz\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:foo</span>","value":":foo"},{"type":"html","content":"<span class='clj-string'>&quot;bar&quot;</span>","value":"\"bar\""}],"value":"[:foo \"bar\"]"}],"value":"{:frank 10, :hank 3, :1 \"baz\", :foo \"bar\"}"}
;; <=

;; @@
(dissoc map2 :hank)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:1</span>","value":":1"},{"type":"html","content":"<span class='clj-string'>&quot;baz&quot;</span>","value":"\"baz\""}],"value":"[:1 \"baz\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:foo</span>","value":":foo"},{"type":"html","content":"<span class='clj-string'>&quot;bar&quot;</span>","value":"\"bar\""}],"value":"[:foo \"bar\"]"}],"value":"{:1 \"baz\", :foo \"bar\"}"}
;; <=

;; @@
(merge map2 {:another "value" :hank 10})
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:hank</span>","value":":hank"},{"type":"html","content":"<span class='clj-long'>10</span>","value":"10"}],"value":"[:hank 10]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:1</span>","value":":1"},{"type":"html","content":"<span class='clj-string'>&quot;baz&quot;</span>","value":"\"baz\""}],"value":"[:1 \"baz\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:foo</span>","value":":foo"},{"type":"html","content":"<span class='clj-string'>&quot;bar&quot;</span>","value":"\"bar\""}],"value":"[:foo \"bar\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:another</span>","value":":another"},{"type":"html","content":"<span class='clj-string'>&quot;value&quot;</span>","value":"\"value\""}],"value":"[:another \"value\"]"}],"value":"{:hank 10, :1 \"baz\", :foo \"bar\", :another \"value\"}"}
;; <=

;; **
;;; `first` and `rest` work, but `nth` doesn't:
;; **

;; @@
(first map2)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:hank</span>","value":":hank"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"[:hank 3]"}
;; <=

;; @@
(nth map2 1)
;; @@

;; @@
(rest map2)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>([:1 &quot;baz&quot;] [:foo &quot;bar&quot;])</span>","value":"([:1 \"baz\"] [:foo \"bar\"])"}
;; <=

;; **
;;; You can also extract a map using `select-keys`:
;; **

;; @@
(select-keys map2 [:foo :hank])
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:hank</span>","value":":hank"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"[:hank 3]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:foo</span>","value":":foo"},{"type":"html","content":"<span class='clj-string'>&quot;bar&quot;</span>","value":"\"bar\""}],"value":"[:foo \"bar\"]"}],"value":"{:hank 3, :foo \"bar\"}"}
;; <=

;; **
;;; Oddly enough, you can do `select-keys` on a vector.  You just get a map back.
;; **

;; @@
(select-keys [1 2 3] [2])
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"[2 3]"}],"value":"{2 3}"}
;; <=
