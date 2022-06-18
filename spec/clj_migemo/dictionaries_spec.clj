(ns clj-migemo.dictionaries-spec
  ;; speclj の var でワーニングになるため。ただ、一回設定した後でコメントアウトしてもワーニングにならない。 clj-kondo にキャッシュが残るから?
  ;;{:clj-kondo/config '{:linters {:unresolved-symbol {:level :off}}}}
  (:require [speclj.core :refer :all]
            [clj-migemo.dictionaries :as dictionaries]))

(describe "入力文字列をひらがなに変換"
          (it "aiueo => あいうえお"
              (should (= (dictionaries/convert :hiragana "aiueo") "あいうえお")))
          (it "sannma => さんま"
              (should (= (dictionaries/convert :hiragana "sannma") "さんま")))
          (it "arigatou => ありがとう"
              (should (= (dictionaries/convert :hiragana "arigatou") "ありがとう")))
          (it "foo => ふぉお"
              (should (= (dictionaries/convert :hiragana "foo") "ふぉお")))
          (it "qsdrtgl => gsdrtgl"
              (should (= (dictionaries/convert :hiragana "qsdrtgl") "qsdrtgl")))
          (it "kyouha@iitennki => きょうは@いいてんき"
              (should (= (dictionaries/convert :hiragana "kyouha@iitennki") "きょうは@いいてんき"))))

(describe "ひらがなをカタカナに変換"
          (it "あいうえお => アイウエオ"
              (should (= (dictionaries/convert :katakana "あいうえお") "アイウエオ"))))

(describe "全角文字を半角文字に変換"
          (it "＠ => @"
              (should (= (dictionaries/convert :hankaku "＠") "@"))))

(describe "半角文字を全角文字に変換"
          (it "ﾐ => ミ"
              (should (= (dictionaries/convert :zenkaku "ﾐ") "ミ"))))

(describe "入力文字列を検索対象文字列に変換"
          (it "aiueo"
              (should (= (dictionaries/convert :all "aiueo") ["aiueo" "ａｉｕｅｏ" "あいうえお" "アイウエオ" "ｱｲｳｴｵ"])))
          (it "sannma"
              (should (= (dictionaries/convert :all "sannma") ["sannma" "ｓａｎｎｍａ" "さんま" "サンマ" "ｻﾝﾏ"])))
          (it "arigatou"
              (should (= (dictionaries/convert :all "arigatou") ["arigatou" "ａｒｉｇａｔｏｕ" "ありがとう" "アリガトウ" "ｱﾘｶﾞﾄｳ"])))
          (it "foo"
              (should (= (dictionaries/convert :all "foo") ["foo" "ｆｏｏ" "ふぉお" "フォオ" "ﾌｫｵ"])))
          (it "qsdrtgl"
              (should (= (dictionaries/convert :all "qsdrtgl") ["qsdrtgl" "ｑｓｄｒｔｇｌ" "qsdrtgl" "qsdrtgl" "qsdrtgl"])))
          (it "kyouha@iitennki"
              (should (= (dictionaries/convert :all "kyouha@iitennki") ["kyouha@iitennki" "ｋｙｏｕｈａ＠ｉｉｔｅｎｎｋｉ" "きょうは@いいてんき" "キョウハ@イイテンキ" "ｷｮｳﾊ@ｲｲﾃﾝｷ"])))
          (it "＠"
              (should (= (dictionaries/convert :all "＠") ["＠" "＠" "＠" "＠" "@"])))
          (it "ﾐ"
              (should (= (dictionaries/convert :all "ﾐ") ["ﾐ" "ミ" "ﾐ" "ﾐ" "ﾐ"]))))

(run-specs)
