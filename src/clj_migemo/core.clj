(ns clj-migemo.core
  (:require [clj-migemo.dictionaries :as dictionaries]
            [clojure.core.server :refer :all])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println
  (start-server {:address "localhost"
                 :port 3000
                 :name "foo"
                 :accept "clojure.core.server/repl"})
  )
  (println *session*)
  (println (stop-server "foo"))
  
  ;(println (dictionaries/lookup "wa"))
  ;(println (. (java.io.File. ".") getCanonicalPath))
  )
