(ns clj-migemo.core
  (:require [clj-migemo.dictionaries :as dictionaries])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (dictionaries/convert :all "aiueo"))
  (println (dictionaries/convert :all "sannma"))
  (println (dictionaries/convert :all "arigatou"))
  (println (dictionaries/convert :all "foo"))
  (println (dictionaries/convert :all "qsdrtgl"))
  (println (dictionaries/convert :all "kyouha@iitennki"))
  (println (dictionaries/convert :all "＠"))
  (println (dictionaries/convert :all "ﾐ")))
