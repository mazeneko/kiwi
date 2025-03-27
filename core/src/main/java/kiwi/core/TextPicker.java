package kiwi.core;

import java.util.Optional;

/**
 * テキストの一部分を取り出すためのユーティリティクラスです。
 */
public final class TextPicker {
  private TextPicker() {
    throw new UnsupportedOperationException("インスタンスの生成は禁止されています。");
  }

  /**
   * 対象の文字列から文字列を取り出します。
   * <p>
   * beginIndex が負の値の場合は 0 に補正します。
   * <p>
   * 取り出すサイズが文字列の長さより大きい場合は末尾までを取り出します。
   *
   * @param text       対象の文字列
   * @param beginIndex 取り出し開始位置(0始まり)
   * @param size       取り出したい文字数
   * @return 取り出した文字列
   */
  public static String pick(String text, int beginIndex, int size) {
    int endIndex = beginIndex + size;
    if (beginIndex < 0) {
      beginIndex = 0;
    }
    if (text.length() < endIndex) {
      endIndex = text.length();
    }
    if (endIndex < beginIndex) {
      return "";
    }
    return text.substring(beginIndex, endIndex);
  }

  /**
   * 対象の文字列の先頭から文字を取り出します。
   *
   * @param text 対象の文字列
   * @param size 取り出したい文字数
   * @return 取り出した文字列
   */
  public static String pickHead(String text, int size) {
    return pick(text, 0, size);
  }

  /**
   * 対象の文字列の末尾から文字列を取り出します。
   *
   * @param text 対象の文字列
   * @param size 取り出したい文字数
   * @return 取り出した文字列
   */
  public static String pickTail(String text, int size) {
    return pick(text, text.length() - size, size);
  }

  /**
   * 対象の文字列の末尾まで文字列を取り出します。
   *
   * @param text       対象の文字列
   * @param beginIndex 取り出し開始位置(0始まり)
   * @return 取り出した文字列
   */
  public static String pickTailFrom(String text, int beginIndex) {
    return pick(text, beginIndex, text.length() - beginIndex);
  }

  private static Optional<Long> tryParseLong(String text) {
    try {
      return Optional.of(Long.valueOf(text));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  /**
   * 対象の文字列から文字列を数値として取り出します。
   *
   * @param text       対象の文字列
   * @param beginIndex 取り出し開始位置(0始まり)
   * @param size       取り出したい文字数
   * @return 数値として取り出せた場合は取り出した数値
   */
  public static Optional<Long> pickLong(String text, int beginIndex, int size) {
    return tryParseLong(pick(text, beginIndex, size));
  }

  /**
   * 対象の文字列の先頭から文字列を数値として取り出します。
   *
   * @param text 対象の文字列
   * @param size 取り出したい文字数
   * @return 数値として取り出せた場合は取り出した数値
   */
  public static Optional<Long> pickLongHead(String text, int size) {
    return tryParseLong(pickHead(text, size));
  }

  /**
   * 対象の文字列の末尾から文字列を数値として取り出します。
   *
   * @param text 対象の文字列
   * @param size 取り出したい文字数
   * @return 数値として取り出せた場合は取り出した数値
   */
  public static Optional<Long> pickLongTail(String text, int size) {
    return tryParseLong(pickTail(text, size));
  }

  /**
   * 対象の文字列の末尾まで文字列を数値として取り出します。
   *
   * @param text       対象の文字列
   * @param beginIndex 取り出し開始位置(0始まり)
   * @return 数値として取り出せた場合は取り出した数値
   */
  public static Optional<Long> pickLongTailFrom(String text, int beginIndex) {
    return tryParseLong(pickTailFrom(text, beginIndex));
  }
}
