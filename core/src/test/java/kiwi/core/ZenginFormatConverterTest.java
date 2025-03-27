package kiwi.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ZenginFormatConverterTest {
  @Test
  void testToZenginFormat() {
    final String origin = "英二えいぃじエイィジ１２３123ダだ";
    final String actual = ZenginFormatConverter.toZenginFormat(origin);
    assertThat(actual).isEqualTo("英二ｴｲｲｼﾞｴｲｲｼﾞ123123ﾀﾞﾀﾞ");
  }

  @Test
  void testIsZenginFormat() {
    assertThat(ZenginFormatConverter.isZenginFormat("英二えいぃじエイィジ１２３123ダだ"))
        .isFalse();
  }

  @Test
  void testIsZenginFormat2() {
    assertThat(ZenginFormatConverter.isZenginFormat("ｴｲｲｼﾞｴｲｲｼﾞ123123ﾀﾞﾀﾞ"))
        .isTrue();
  }
}
