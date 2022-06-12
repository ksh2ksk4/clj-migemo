(ns clj-migemo.dictionaries.hankaku)

(def data
  "全角文字から半角文字への変換データ"
  {"！" "!"
   "”" "\""
   "＃" "#"
   "＄" "$"
   "％" "%"
   "＆" "&"
   "’" "'"
   "（" "("
   "）" ")"
   "＊" "*"
   "＋" "+"
   "，" ","
   "−" "-"
   "．" "."
   "／" "/"
   "０" "0"
   "１" "1"
   "２" "2"
   "３" "3"
   "４" "4"
   "５" "5"
   "６" "6"
   "７" "7"
   "８" "8"
   "９" "9"
   "：" ":"
   "；" ";"
   "＜" "<"
   "＝" "="
   "＞" ">"
   "？" "?"
   "＠" "@"
   "Ａ" "A"
   "Ｂ" "B"
   "Ｃ" "C"
   "Ｄ" "D"
   "Ｅ" "E"
   "Ｆ" "F"
   "Ｇ" "G"
   "Ｈ" "H"
   "Ｉ" "I"
   "Ｊ" "J"
   "Ｋ" "K"
   "Ｌ" "L"
   "Ｍ" "M"
   "Ｎ" "N"
   "Ｏ" "O"
   "Ｐ" "P"
   "Ｑ" "Q"
   "Ｒ" "R"
   "Ｓ" "S"
   "Ｔ" "T"
   "Ｕ" "U"
   "Ｖ" "V"
   "Ｗ" "W"
   "Ｘ" "X"
   "Ｙ" "Y"
   "Ｚ" "Z"
   "［" "["
   "＼" "\\"
   "］" "]"
   "＾" "^"
   "＿" "_"
   "‘" "`"
   "ａ" "a"
   "ｂ" "b"
   "ｃ" "c"
   "ｄ" "d"
   "ｅ" "e"
   "ｆ" "f"
   "ｇ" "g"
   "ｈ" "h"
   "ｉ" "i"
   "ｊ" "j"
   "ｋ" "k"
   "ｌ" "l"
   "ｍ" "m"
   "ｎ" "n"
   "ｏ" "o"
   "ｐ" "p"
   "ｑ" "q"
   "ｒ" "r"
   "ｓ" "s"
   "ｔ" "t"
   "ｕ" "u"
   "ｖ" "v"
   "ｗ" "w"
   "ｘ" "x"
   "ｙ" "y"
   "ｚ" "z"
   "｛" "{"
   "｜" "|"
   "｝" "}"
   "〜" "~"
   "。" "｡"
   "「" "｢"
   "」" "｣"
   "、" "､"
   "・" "･"
   "ヲ" "ｦ"
   "ァ" "ｧ"
   "ィ" "ｨ"
   "ゥ" "ｩ"
   "ェ" "ｪ"
   "ォ" "ｫ"
   "ャ" "ｬ"
   "ュ" "ｭ"
   "ョ" "ｮ"
   "ッ" "ｯ"
   "ー" "ｰ"
   "ア" "ｱ"
   "イ" "ｲ"
   "ウ" "ｳ"
   "エ" "ｴ"
   "オ" "ｵ"
   "カ" "ｶ"
   "キ" "ｷ"
   "ク" "ｸ"
   "ケ" "ｹ"
   "コ" "ｺ"
   "サ" "ｻ"
   "シ" "ｼ"
   "ス" "ｽ"
   "セ" "ｾ"
   "ソ" "ｿ"
   "タ" "ﾀ"
   "チ" "ﾁ"
   "ツ" "ﾂ"
   "テ" "ﾃ"
   "ト" "ﾄ"
   "ナ" "ﾅ"
   "ニ" "ﾆ"
   "ヌ" "ﾇ"
   "ネ" "ﾈ"
   "ノ" "ﾉ"
   "ハ" "ﾊ"
   "ヒ" "ﾋ"
   "フ" "ﾌ"
   "ヘ" "ﾍ"
   "ホ" "ﾎ"
   "マ" "ﾏ"
   "ミ" "ﾐ"
   "ム" "ﾑ"
   "メ" "ﾒ"
   "モ" "ﾓ"
   "ヤ" "ﾔ"
   "ユ" "ﾕ"
   "ヨ" "ﾖ"
   "ラ" "ﾗ"
   "リ" "ﾘ"
   "ル" "ﾙ"
   "レ" "ﾚ"
   "ロ" "ﾛ"
   "ワ" "ﾜ"
   "ン" "ﾝ"
   "゛" "ﾞ"
   "゜" "ﾟ"
   "ヴ" "ｳﾞ"
   "ガ" "ｶﾞ"
   "ギ" "ｷﾞ"
   "グ" "ｸﾞ"
   "ゲ" "ｹﾞ"
   "ゴ" "ｺﾞ"
   "ザ" "ｻﾞ"
   "ジ" "ｼﾞ"
   "ズ" "ｽﾞ"
   "ゼ" "ｾﾞ"
   "ゾ" "ｿﾞ"
   "ダ" "ﾀﾞ"
   "ヂ" "ﾁﾞ"
   "ヅ" "ﾂﾞ"
   "デ" "ﾃﾞ"
   "ド" "ﾄﾞ"
   "バ" "ﾊﾞ"
   "ビ" "ﾋﾞ"
   "ブ" "ﾌﾞ"
   "ベ" "ﾍﾞ"
   "ボ" "ﾎﾞ"
   "パ" "ﾊﾟ"
   "ピ" "ﾋﾟ"
   "プ" "ﾌﾟ"
   "ペ" "ﾍﾟ"
   "ポ" "ﾎﾟ"})
