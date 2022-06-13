(ns clj-migemo.core
  (:require [clj-migemo.dictionaries :as dictionaries])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (dictionaries/convert :hiragana "ai"))
  (println (dictionaries/convert :katakana "あいうえお"))
  (println (dictionaries/convert :hankaku "＠"))
  (println (dictionaries/convert :zenkaku "ﾐ")))
