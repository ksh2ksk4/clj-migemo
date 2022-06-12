(ns clj-migemo.dictionaries
  (:require [clj-migemo.dictionaries.katakana :as katakana]
            [clj-migemo.dictionaries.hankaku :as hankaku]
            [clj-migemo.dictionaries.hiragana :as hiragana]
            [clj-migemo.dictionaries.zenkaku :as zenkaku]))

(defn convert
  []
  (println (get katakana/data "あ"))
  (println (get hankaku/data "＠"))
  (println (get (get hiragana/data 2) "fa"))
  (println (get zenkaku/data "ﾐ")))
