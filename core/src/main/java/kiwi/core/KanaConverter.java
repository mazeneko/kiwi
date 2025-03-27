package kiwi.core;

import java.util.List;
import java.util.Map;

import com.ibm.icu.lang.CharacterProperties;
import com.ibm.icu.lang.UCharacter.EastAsianWidth;
import com.ibm.icu.lang.UProperty;
import com.ibm.icu.text.Transliterator;

/**
 * かなのConverter
 */
public final class KanaConverter {
  private KanaConverter() {
    throw new UnsupportedOperationException("インスタンスの生成は禁止されています。");
  };

  private static final Map<String, String> lowerUpperMap = Map.ofEntries(
      Map.entry("ぁ", "あ"),
      Map.entry("ぃ", "い"),
      Map.entry("ぅ", "う"),
      Map.entry("ぇ", "え"),
      Map.entry("ぉ", "お"),
      Map.entry("ゃ", "や"),
      Map.entry("ゅ", "ゆ"),
      Map.entry("ょ", "よ"),
      Map.entry("っ", "つ"),
      Map.entry("ァ", "ア"),
      Map.entry("ィ", "イ"),
      Map.entry("ゥ", "ウ"),
      Map.entry("ェ", "エ"),
      Map.entry("ォ", "オ"),
      Map.entry("ャ", "ヤ"),
      Map.entry("ュ", "ユ"),
      Map.entry("ョ", "ヨ"),
      Map.entry("ッ", "ツ"),
      Map.entry("ｧ", "ｱ"),
      Map.entry("ｨ", "ｲ"),
      Map.entry("ｩ", "ｳ"),
      Map.entry("ｪ", "ｴ"),
      Map.entry("ｫ", "ｵ"),
      Map.entry("ｬ", "ﾔ"),
      Map.entry("ｭ", "ﾕ"),
      Map.entry("ｮ", "ﾖ"),
      Map.entry("ｯ", "ﾂ"));

  /**
   * 小書き文字(ぁ,ゃ)を通常の大きさの文字(あ,や)に変換します。
   * 
   * @param value 変換する文字列
   * @return 変換後の文字列
   */
  public static String toUpperCase(String value) {
    return lowerUpperMap
        .entrySet()
        .stream()
        .reduce(
            value,
            (acc, entry) -> acc.replaceAll(entry.getKey(), entry.getValue()),
            (s1, s2) -> {
              throw new UnsupportedOperationException();
            });
  }

  /**
   * 通常の大きさの文字(あ,や)を小書き文字(ぁ,ゃ)に変換します。
   * 
   * @param value 変換する文字列
   * @return 変換後の文字列
   */
  public static String toLowerCase(String value) {
    return lowerUpperMap
        .entrySet()
        .stream()
        .reduce(
            value,
            (acc, entry) -> acc.replaceAll(entry.getValue(), entry.getKey()),
            (s1, s2) -> {
              throw new UnsupportedOperationException();
            });
  }

  /**
   * 全角ひらがな/全角カタカナを半角カタカナへ変換します。
   * 
   * @param value 変換する文字列
   * @return 変換後の文字列
   */
  public static String toHalfWidth(String value) {
    final var katakana = Transliterator.getInstance("Hiragana-Katakana").transliterate(value);
    final var halfKatakana = Transliterator.getInstance("Fullwidth-Halfwidth").transliterate(katakana);
    return halfKatakana;
  }

  /**
   * 文字がすべて半角カタカナかどうかを返します。
   * 
   * @param value 判定する文字列
   * @return 文字がすべて半角カタカナであればtrue
   */
  public static boolean isHalfWidth(String value) {
    final var intPropertyMap = CharacterProperties.getIntPropertyMap(UProperty.EAST_ASIAN_WIDTH);
    return value
        .codePoints()
        .allMatch(codePoint -> {
          final var intProperty = intPropertyMap.get(codePoint);
          final var halfWidth = List.of(EastAsianWidth.NARROW, EastAsianWidth.HALFWIDTH).contains(intProperty);
          return halfWidth;
        });
  }
}
