(ns clj-migemo.dictionaries-test
  (:require [clojure.test :refer :all]
            [clj-migemo.dictionaries :as dictionaries]))

(deftest convert-to-hiragana
  (testing "入力文字列をひらがなに変換"
    (testing "aiueo => あいうえお"
      (is (= (#'dictionaries/convert :hiragana "aiueo") "あいうえお")))
    (testing "sannma => さんま"
      (is (= (#'dictionaries/convert :hiragana "sannma") "さんま")))
    (testing "arigatou => ありがとう"
      (is (= (#'dictionaries/convert :hiragana "arigatou") "ありがとう")))
    (testing "foo => ふぉお"
      (is (= (#'dictionaries/convert :hiragana "foo") "ふぉお")))
    (testing "qsdrtgl => gsdrtgl"
      (is (= (#'dictionaries/convert :hiragana "qsdrtgl") "qsdrtgl")))
    (testing "kyouha@iitennki => きょうは@いいてんき"
      (is (= (#'dictionaries/convert :hiragana "kyouha@iitennki") "きょうは@いいてんき")))))

(deftest convert-to-katakana
  (testing "ひらがなをカタカナに変換"
    (testing "あいうえお => アイウエオ"
      (is (= (#'dictionaries/convert :katakana "あいうえお") "アイウエオ")))))

(deftest convert-to-hankaku
  (testing "全角文字を半角文字に変換"
    (testing "＠ => @"
      (is (= (#'dictionaries/convert :hankaku "＠") "@")))))

(deftest convert-to-zenkaku
  (testing "半角文字を全角文字に変換"
    (testing "ﾐ => ミ"
      (is (= (#'dictionaries/convert :zenkaku "ﾐ") "ミ")))))

(deftest convert-to-search-strings
  (testing "入力文字列を検索対象文字列に変換"
    (testing "aiueo"
      (is (= (#'dictionaries/convert :all "aiueo") {:search-strings ["aiueo" "あいうえお"]
                                                    :non-search-strings ["ａｉｕｅｏ"  "アイウエオ" "ｱｲｳｴｵ"]})))
    (testing "sannma"
      (is (= (#'dictionaries/convert :all "sannma") {:search-strings ["sannma" "さんま"]
                                                     :non-search-strings ["ｓａｎｎｍａ" "サンマ" "ｻﾝﾏ"]})))
    (testing "arigatou"
      (is (= (#'dictionaries/convert :all "arigatou") {:search-strings ["arigatou" "ありがとう"]
                                                       :non-search-strings ["ａｒｉｇａｔｏｕ" "アリガトウ" "ｱﾘｶﾞﾄｳ"]})))
    (testing "foo"
      (is (= (#'dictionaries/convert :all "foo") {:search-strings ["foo" "ふぉお"]
                                                  :non-search-strings ["ｆｏｏ" "フォオ" "ﾌｫｵ"]})))
    (testing "qsdrtgl"
      (is (= (#'dictionaries/convert :all "qsdrtgl") {:search-strings ["qsdrtgl" "qsdrtgl"]
                                                      :non-search-strings ["ｑｓｄｒｔｇｌ" "qsdrtgl" "qsdrtgl"]})))
    (testing "kyouha@iitennki"
      (is (= (#'dictionaries/convert :all "kyouha@iitennki") {:search-strings ["kyouha@iitennki" "きょうは@いいてんき"]
                                                              :non-search-strings ["ｋｙｏｕｈａ＠ｉｉｔｅｎｎｋｉ" "キョウハ@イイテンキ" "ｷｮｳﾊ@ｲｲﾃﾝｷ"]})))
    (testing "＠"
      (is (= (#'dictionaries/convert :all "＠") {:search-strings ["＠" "＠"]
                                                :non-search-strings ["＠" "＠" "@"]})))
    (testing "ﾐ"
      (is (= (#'dictionaries/convert :all "ﾐ") {:search-strings ["ﾐ" "ﾐ"]
                                                :non-search-strings ["ミ" "ﾐ" "ﾐ"]})))))

(run-tests)
