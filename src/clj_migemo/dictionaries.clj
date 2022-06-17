(ns clj-migemo.dictionaries
  (:require [clj-migemo.dictionaries.katakana :as katakana]
            [clj-migemo.dictionaries.hankaku :as hankaku]
            [clj-migemo.dictionaries.hiragana :as hiragana]
            [clj-migemo.dictionaries.zenkaku :as zenkaku]
            [clojure.string :refer [join]]))

(defn convert-hiragana
  "入力文字列をひらがなへ変換する"
  [input]
  ;; 入力文字列を一文字づつ変換
  (loop [output []
         i 0
         x 1]
    (if (>= i (count input))
      (join output)
      (let [sub-dictionary (get hiragana/data x)
            char (subs input i (+ i x))
            converted-char (get sub-dictionary char)]
        (if (nil? converted-char)
          ;; 変換できなかった場合
          (recur output
                 i
                 (inc x))
          ;; 変換できた場合
          (recur (conj output converted-char)
                 (+ i x)
                 1))))))

;; HACK: 関数が冗長なのを解決する
(defn convert-katakana
  "ひらがなをカタカナへ変換する"
  [input]
  ;; 入力文字列を一文字づつ変換
  (loop [position 0  ; 変換対象文字の位置
         output []]  ; 変換後の文字
    (if (>= position (count input))
      (join output)
      (let [char (subs input position (inc position))
            converted-char (get katakana/data char)]
        (recur (inc position)
               (conj output (if (some? converted-char)
                              converted-char
                              char)))))))

(defn convert-hankaku
  "半角文字を全角文字へ変換する"
  [input]
  ;; 入力文字列を一文字づつ変換
  (loop [position 0  ; 変換対象文字の位置
         output []]  ; 変換後の文字
    (if (>= position (count input))
      (join output)
      (let [char (subs input position (inc position))
            converted-char (get hankaku/data char)]
        (recur (inc position)
               (conj output (if (some? converted-char)
                              converted-char
                              char)))))))

(defn convert-zenkaku
  "全角文字を半角文字へ変換する"
  [input]
  ;; 入力文字列を一文字づつ変換
  (loop [position 0  ; 変換対象文字の位置
         output []]  ; 変換後の文字
    (if (>= position (count input))
      (join output)
      (let [char (subs input position (inc position))
            converted-char (get zenkaku/data char)]
        (recur (inc position)
               (conj output (if (some? converted-char)
                              converted-char
                              char)))))))

(defn convert
  "入力文字列を変換する"
  [dictionary input]
  (case dictionary
    :hiragana (convert-hiragana input)
    :katakana (convert-katakana input)
    :hankaku (convert-hankaku input)
    :zenkaku (convert-zenkaku input)
    ;; TODO: エラー処理
    :else "Error"))
