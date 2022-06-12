(ns clj-migemo.core
  (:require [clj-migemo.dictionaries :as dictionaries])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (dictionaries/convert)
  )
