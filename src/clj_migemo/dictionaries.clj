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
  (loop [head 0      ; 変換対象範囲の先頭位置
         length 1    ; 変換対象範囲の長さ
         output []]  ; 変換後の文字
    (if (>= head (count input))
      (join output)
      (let [sub-dictionary (get hiragana/data length)
            char (subs input head (+ head length))
            converted-char (get sub-dictionary char)]
        (if (some? converted-char)
          (recur (+ head length)
                 1
                 (conj output converted-char))
          (if (>= length (- (count input) head))  ; 変換対象文字列の残りの文字列長
            ;; 変換対象文字列の末尾に到達した場合
            (join (conj output char))
            ;; 変換対象文字列の末尾に到達していない場合
            (if (some? (re-find #"[a-zA-Z]+" char))
              ;; 変換対象文字がアルファベットの場合
              ;;   そのまま変換処理を継続
              (recur head
                     (inc length)
                     output)
              ;; 変換対象文字にアルファベット以外が含まれている場合
              ;;   そのまま出力して次の文字から変換処理を実行
              (recur (+ head length)
                     1
                     (conj output char)))))))))

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
