package kiwi.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class KanaConverterTest {
  @Test
  void testToLowerCase() {
    final String origin = "あアぁァかカがガaAｋＫ";
    final String actual = KanaConverter.toLowerCase(origin);
    assertThat(actual).isEqualTo("ぁァぁァかカがガaAｋＫ");
  }

  @Test
  void testToUpperCase() {
    final String origin = "あアぁァかカがガaAｋＫ";
    final String actual = KanaConverter.toUpperCase(origin);
    assertThat(actual).isEqualTo("あアあアかカがガaAｋＫ");
  }

  @Test
  void testIsHalfWidth() {
    assertThat(KanaConverter.isHalfWidth("ｱｲｳｴｵ")).isTrue();
    assertThat(KanaConverter.isHalfWidth("abcde")).isTrue();
    assertThat(KanaConverter.isHalfWidth("01234")).isTrue();
    assertThat(KanaConverter.isHalfWidth("ｱb3dｵ")).isTrue();

    assertThat(KanaConverter.isHalfWidth("ｱｲｳえｵ")).isFalse();
    assertThat(KanaConverter.isHalfWidth("abcｄe")).isFalse();
    assertThat(KanaConverter.isHalfWidth("012３4")).isFalse();
  }
}
