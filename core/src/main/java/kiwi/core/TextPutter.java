package kiwi.core;

/**
 * テキストの一部を置き換えたり埋めたりするためのユーティリティクラスです。
 */
public final class TextPutter {
  private TextPutter() {
    throw new UnsupportedOperationException("インスタンスの生成は禁止されています。");
  }

  /**
   * 対象の文字列を指定した文字列で置き換えます。
   *
   * @param baseText    対象の文字列
   * @param beginIndex  置き換え開始位置(0始まり)
   * @param size        置き換えたい文字数
   * @param puttingText 置き換えに使用する文字列
   * @return 置き換えを行った文字列
   */
  public static String put(String baseText, int beginIndex, int size, String puttingText) {
    final var pre = beginIndex == 0
        ? ""
        : String.format("%-" + beginIndex + "s", TextPicker.pick(baseText, 0, beginIndex));
    final var post = TextPicker.pick(baseText, beginIndex + size, baseText.length());
    final var puttingTextActual = size == 0
        ? ""
        : String.format("%-" + size + "s", TextPicker.pick(puttingText, 0, size));
    return pre + puttingTextActual + post;
  }

  /**
   * 対象の文字列を指定した0埋め数値文字列で置き換えます。
   *
   * @param baseText      対象の文字列
   * @param beginIndex    置き換え開始位置(0始まり)
   * @param size          置き換えたい文字数
   * @param puttingNumber 0 埋めする数値
   * @return 置き換えを行った文字列
   */
  public static String putZeroPaddingNumber(String baseText, int beginIndex, int size, Long puttingNumber) {
    final var puttingText = String.format("%0" + size + "d", puttingNumber);
    return put(baseText, beginIndex, size, puttingText);
  }

  /**
   * 対象の文字列を指定した0埋め数値文字列で置き換えます。
   *
   * @param baseText      対象の文字列
   * @param beginIndex    置き換え開始位置(0始まり)
   * @param size          置き換えたい文字数
   * @param puttingNumber 0 埋めする数値
   * @return 置き換えを行った文字列
   */
  public static String putZeroPaddingNumber(String baseText, int beginIndex, int size, Integer puttingNumber) {
    return putZeroPaddingNumber(baseText, beginIndex, size, puttingNumber.longValue());
  }

  /**
   * {@link #putZeroPaddingNumber(String, int, int, Long)} のエイリアスメソッドです。
   */
  public static String pzn(String baseText, int beginIndex, int size, Long puttingNumber) {
    return putZeroPaddingNumber(baseText, beginIndex, size, puttingNumber);
  }

  /**
   * {@link #putZeroPaddingNumber(String, int, int, Integer)} のエイリアスメソッドです。
   */
  public static String pzn(String baseText, int beginIndex, int size, Integer puttingNumber) {
    return putZeroPaddingNumber(baseText, beginIndex, size, puttingNumber);
  }

  /**
   * 対象の文字列の長さがサイズに満たない場合に、末尾に {@code filler} を繰り返して埋めます。
   *
   * @param baseText 埋め込み対象の文字列
   * @param size     目標とする最小の文字列長
   * @param filler   埋め込みに使用する文字列
   * @return 置き換えを行った文字列
   */
  public static String fill(String baseText, int size, String filler) {
    if (filler.isEmpty()) {
      throw new IllegalArgumentException("filler requires non-empty.");
    }
    if (size <= baseText.length()) {
      return baseText;
    }
    final var fillingSize = size - baseText.length();
    final var repeatCount = (fillingSize + filler.length() - 1) / filler.length();
    final var resolvedFiller = TextPicker.pick(filler.repeat(repeatCount), 0, fillingSize);
    return baseText + resolvedFiller;
  }

  /**
   * {@link #fill(String, int, String)} のエイリアスメソッドです。
   */
  public static String fil(String baseText, int size, String filler) {
    return fill(baseText, size, filler);
  }

  /**
   * {@link #fill(String, int, String)} のあとに余分な文字列をカットします。
   */
  public static String fillAndCutExtra(String baseText, int size, String filler) {
    return TextPicker.pick(fill(baseText, size, filler), 0, size);
  }
}
