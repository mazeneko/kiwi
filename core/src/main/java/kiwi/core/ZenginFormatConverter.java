package kiwi.core;

/**
 * 全銀フォーマットのConverter
 */
public final class ZenginFormatConverter {
  private ZenginFormatConverter() {
    throw new UnsupportedOperationException("インスタンスの生成は禁止されています。");
  };

  /**
   * 全銀フォーマットの文字列かどうかを返します。
   * 
   * @param value 判定する文字列
   * @return 全銀フォーマットの文字列であればtrue
   */
  public static boolean isZenginFormat(String value) {
    return value
        .matches("[ 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾝﾞﾟ\\(\\)\\-\\.]*");
  }

  /**
   * 全銀フォーマット向けに文字列を変換します。変換不能な部分は元の文字列のままとなります。
   * 
   * @param value 変換する文字列
   * @return 変換後の文字列
   */
  public static String toZenginFormat(String value) {
    final var upperKana = KanaConverter.toUpperCase(value);
    final var halfKatakana = KanaConverter.toHalfWidth(upperKana);
    return halfKatakana;
  }
}
