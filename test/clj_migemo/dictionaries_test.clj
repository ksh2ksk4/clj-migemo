(ns clj-migemo.dictionaries-test
  (:require [clojure.test :refer :all]
            [clj-migemo.dictionaries :as dictionaries]))

(deftest convert-to-hiragana
  (testing "入力文字列をひらがなに変換"
    (testing "aiueo => あいうえお"
      (is (= (dictionaries/convert :hiragana "aiueo") "あいうえお")))
    (testing "sannma => さんま"
      (is (= (dictionaries/convert :hiragana "sannma") "さんま")))
    (testing "arigatou => ありがとう"
      (is (= (dictionaries/convert :hiragana "arigatou") "ありがとう")))
    (testing "foo => ふぉお"
      (is (= (dictionaries/convert :hiragana "foo") "ふぉお")))
    (testing "qsdrtgl => gsdrtgl"
      (is (= (dictionaries/convert :hiragana "qsdrtgl") "qsdrtgl")))
    (testing "kyouha@iitennki => きょうは@いいてんき"
      (is (= (dictionaries/convert :hiragana "kyouha@iitennki") "きょうは@いいてんき")))))

(deftest convert-to-katakana
  (testing "ひらがなをカタカナに変換"
    (testing "あいうえお => アイウエオ"
      (is (= (dictionaries/convert :katakana "あいうえお") "アイウエオ")))))

(deftest convert-to-hankaku
  (testing "全角文字を半角文字に変換"
    (testing "＠ => @"
      (is (= (dictionaries/convert :hankaku "＠") "@")))))

(deftest convert-to-zenkaku
  (testing "半角文字を全角文字に変換"
    (testing "ﾐ => ミ"
      (is (= (dictionaries/convert :zenkaku "ﾐ") "ミ")))))

(deftest convert-to-st
  (testing "入力文字列を検索対象文字列に変換"
    (testing "aiueo"
      (is (= (dictionaries/convert :all "aiueo") ["aiueo" "ａｉｕｅｏ" "あいうえお" "アイウエオ" "ｱｲｳｴｵ"])))
    (testing "sannma"
      (is (= (dictionaries/convert :all "sannma") ["sannma" "ｓａｎｎｍａ" "さんま" "サンマ" "ｻﾝﾏ"])))
    (testing "arigatou"
      (is (= (dictionaries/convert :all "arigatou") ["arigatou" "ａｒｉｇａｔｏｕ" "ありがとう" "アリガトウ" "ｱﾘｶﾞﾄｳ"])))
    (testing "foo"
      (is (= (dictionaries/convert :all "foo") ["foo" "ｆｏｏ" "ふぉお" "フォオ" "ﾌｫｵ"])))
    (testing "qsdrtgl"
      (is (= (dictionaries/convert :all "qsdrtgl") ["qsdrtgl" "ｑｓｄｒｔｇｌ" "qsdrtgl" "qsdrtgl" "qsdrtgl"])))
    (testing "kyouha@iitennki"
      (is (= (dictionaries/convert :all "kyouha@iitennki") ["kyouha@iitennki" "ｋｙｏｕｈａ＠ｉｉｔｅｎｎｋｉ" "きょうは@いいてんき" "キョウハ@イイテンキ" "ｷｮｳﾊ@ｲｲﾃﾝｷ"])))
    (testing "＠"
      (is (= (dictionaries/convert :all "＠") ["＠" "＠" "＠" "＠" "@"])))
    (testing "ﾐ"
      (is (= (dictionaries/convert :all "ﾐ") ["ﾐ" "ミ" "ﾐ" "ﾐ" "ﾐ"])))))

(run-tests)
