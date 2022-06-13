(ns clj-migemo.dictionaries
  (:require [clj-migemo.dictionaries.katakana :as katakana]
            [clj-migemo.dictionaries.hankaku :as hankaku]
            [clj-migemo.dictionaries.hiragana :as hiragana]
            [clj-migemo.dictionaries.zenkaku :as zenkaku]))

;;todo 関数が冗長なのを解決する
(defn convert-katakana
  "ひらがなをカタカナへ変換する"
  [input]
  ;; 入力文字列を一文字づつ変換
  (loop [output []
         i 0]
    (if (>= i (count input))
      output
      (let [char (subs input i (inc i))
            converted-char (get katakana/data char)]
        (recur (conj output converted-char)
               (inc i))))))

(defn convert-hankaku
  "半角文字を全角文字へ変換する"
  [input]
  ;; 入力文字列を一文字づつ変換
  (loop [output []
         i 0]
    (if (>= i (count input))
      output
      (let [char (subs input i (inc i))
            converted-char (get hankaku/data char)]
        (recur (conj output converted-char)
               (inc i))))))

(defn convert-zenkaku
  "全角文字を半角文字へ変換する"
  [input]
  ;; 入力文字列を一文字づつ変換
  (loop [output []
         i 0]
    (if (>= i (count input))
      output
      (let [char (subs input i (inc i))
            converted-char (get zenkaku/data char)]
        (recur (conj output converted-char)
               (inc i))))))

(defn convert
  "入力文字列を変換する"
  [dictionary input]
  (case dictionary
    :hiragana "Not implemented"
    :katakana (convert-katakana input)
    :hankaku (convert-hankaku input)
    :zenkaku (convert-zenkaku input)
    ;;todo エラー処理
    :else "Error"))
