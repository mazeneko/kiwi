package kiwi.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TextPickerTest {
  @Test
  void testPick() {
    assertThat(TextPicker.pick("abcde", 0, 5)).isEqualTo("abcde");
    assertThat(TextPicker.pick("abcde", 0, 4)).isEqualTo("abcd");
    assertThat(TextPicker.pick("abcde", 1, 4)).isEqualTo("bcde");
    assertThat(TextPicker.pick("abcde", 1, 5)).isEqualTo("bcde");
    assertThat(TextPicker.pick("abcde", -1, 5)).isEqualTo("abcd");
    assertThat(TextPicker.pick("abcde", 5, 5)).isEqualTo("");
    assertThat(TextPicker.pick("abcde", 0, 0)).isEqualTo("");
    assertThat(TextPicker.pick("abcde", 3, -2)).isEqualTo("");
  }

  @Test
  void testPickHead() {
    assertThat(TextPicker.pickHead("abcde", -1)).isEqualTo("");
    assertThat(TextPicker.pickHead("abcde", 0)).isEqualTo("");
    assertThat(TextPicker.pickHead("abcde", 1)).isEqualTo("a");
    assertThat(TextPicker.pickHead("abcde", 2)).isEqualTo("ab");
    assertThat(TextPicker.pickHead("abcde", 3)).isEqualTo("abc");
    assertThat(TextPicker.pickHead("abcde", 4)).isEqualTo("abcd");
    assertThat(TextPicker.pickHead("abcde", 5)).isEqualTo("abcde");
    assertThat(TextPicker.pickHead("abcde", 6)).isEqualTo("abcde");
  }

  @Test
  void testPickTail() {
    assertThat(TextPicker.pickTail("abcde", -1)).isEqualTo("");
    assertThat(TextPicker.pickTail("abcde", 0)).isEqualTo("");
    assertThat(TextPicker.pickTail("abcde", 1)).isEqualTo("e");
    assertThat(TextPicker.pickTail("abcde", 2)).isEqualTo("de");
    assertThat(TextPicker.pickTail("abcde", 3)).isEqualTo("cde");
    assertThat(TextPicker.pickTail("abcde", 4)).isEqualTo("bcde");
    assertThat(TextPicker.pickTail("abcde", 5)).isEqualTo("abcde");
    assertThat(TextPicker.pickTail("abcde", 6)).isEqualTo("abcde");
  }

  @Test
  void testPickTailFrom() {
    assertThat(TextPicker.pickTailFrom("abcde", -1)).isEqualTo("abcde");
    assertThat(TextPicker.pickTailFrom("abcde", 0)).isEqualTo("abcde");
    assertThat(TextPicker.pickTailFrom("abcde", 1)).isEqualTo("bcde");
    assertThat(TextPicker.pickTailFrom("abcde", 2)).isEqualTo("cde");
    assertThat(TextPicker.pickTailFrom("abcde", 3)).isEqualTo("de");
    assertThat(TextPicker.pickTailFrom("abcde", 4)).isEqualTo("e");
    assertThat(TextPicker.pickTailFrom("abcde", 5)).isEqualTo("");
    assertThat(TextPicker.pickTailFrom("abcde", 6)).isEqualTo("");
  }

  @Test
  void testPickLong() {
    assertThat(TextPicker.pickLong("abcde1234fg", 5, 3)).isPresent().contains(123L);
    assertThat(TextPicker.pickLong("abcde1234fg", 5, 0)).isEmpty();
    assertThat(TextPicker.pickLong("abcde1234fg", 2, 3)).isEmpty();
  }
}
