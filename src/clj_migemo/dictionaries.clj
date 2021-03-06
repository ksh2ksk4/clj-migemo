(ns clj-migemo.dictionaries
  (:require [clj-migemo.dictionaries.katakana :as katakana]
            [clj-migemo.dictionaries.hankaku :as hankaku]
            [clj-migemo.dictionaries.hiragana :as hiragana]
            [clj-migemo.dictionaries.zenkaku :as zenkaku]
            [clojure.string :refer [join split split-lines]]))

(defonce ^:private migemo-dictionary "./resources/migemo-dict")

(defrecord ^:private Item [key values])

(defn- load-migemo-dictionary
  "migemoの辞書ファイルからデータをロードする"
  []
  (loop [lines (split-lines (slurp migemo-dictionary))
         current-line (first lines)
         items []]
    (if (nil? current-line)
      items
      (let [words (split current-line #"\s+")
            item (Item. (first words) (rest words))
            rest-of-lines (rest lines)]
        (recur rest-of-lines
               (first rest-of-lines)
               (conj items item))))))

(defn- convert-hiragana
  "入力文字列をひらがなへ変換する"
  [input]
  ;; 入力文字列を一文字づつ変換
  (loop [head 0      ; 変換対象範囲の先頭位置
         length 1    ; 変換対象範囲の長さ
         output []]  ; 変換後の文字
    (if (>= head (count input))
      (join output)
      (let [sub-dictionary (get hiragana/data length)
            chars (subs input head (+ head length))
            converted-char (get sub-dictionary chars)]
        (if (some? converted-char)
          (recur (+ head length)
                 1
                 (conj output converted-char))
          (if (>= length (- (count input) head))  ; 変換対象文字列の残りの文字列長
            ;; 変換対象文字列の末尾に到達した場合
            (join (conj output chars))
            ;; 変換対象文字列の末尾に到達していない場合
            (if (some? (re-find #"[a-zA-Z]+" chars))
              ;; 変換対象文字がアルファベットの場合
              ;;   そのまま変換処理を継続
              (recur head
                     (inc length)
                     output)
              ;; 変換対象文字にアルファベット以外が含まれている場合
              ;;   そのまま出力して次の文字から変換処理を実行
              (recur (+ head length)
                     1
                     (conj output chars)))))))))

;; HACK: 関数が冗長なのを解決する
(defn- convert-katakana
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

(defn- convert-hankaku
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

(defn- convert-zenkaku
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

(defn- convert
  "入力文字列を検索対象文字列に変換する"
  [dictionary input]
  (case dictionary
    :hiragana (convert-hiragana input)
    :katakana (convert-katakana input)
    :hankaku (convert-hankaku input)
    :zenkaku (convert-zenkaku input)
    :all (let [zenkaku (convert-zenkaku input)
               hiragana (convert-hiragana input)
               katakana (convert-katakana hiragana)
               hankaku-katakana (convert-hankaku katakana)]
           {:search-strings (conj [] input hiragana)
            :non-search-strings (conj [] zenkaku katakana hankaku-katakana)})
    ;; TODO: エラー処理
    :else "Error"))

(defn lookup
  "辞書を引く"
  [string]
  (let [items (load-migemo-dictionary)
        converted-string (convert :all string)
        search-strings (:search-strings converted-string)
        non-search-strings (:non-search-strings converted-string)]
    (flatten (cons (cons search-strings non-search-strings)
                   (map (fn [x]
                          (:values x))
                        (filter (fn [x]
                                  ;; contains?は配列に対して期待した動作をしない
                                  (.contains search-strings (:key x)))
                                items))))))


(comment
def
defn
defn-

defonce

defmacro
defmethod
defmulti
definterface
defprotocol
defrecord
defstruct obsoletedなのでdefrecordを使う
deftype

definline
)
