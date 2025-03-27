package kiwi.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TextPutterTest {

  @Test
  void testPut() {
    assertThat(TextPutter.put("abcde", 1, 5, "qwertyuiop")).isEqualTo("aqwert");
    assertThat(TextPutter.put("abcde", 10, 5, "qwertyuiop")).isEqualTo("abcde     qwert");
  }

  @Test
  void testPutZeroPaddingNumber() {
    assertThat(TextPutter.putZeroPaddingNumber("abcde", 1, 5, 1234567890L)).isEqualTo("a12345");
    assertThat(TextPutter.putZeroPaddingNumber("abcde", 10, 5, 1234567890L)).isEqualTo("abcde     12345");
    assertThat(TextPutter.putZeroPaddingNumber("abcde", 10, 5, 123L)).isEqualTo("abcde     00123");
  }

  @Test
  void testFill() {
    assertThat(TextPutter.fill("abcde", 10, " ")).isEqualTo("abcde     ");
    assertThat(TextPutter.fill("abcde", 10, "fg")).isEqualTo("abcdefgfgf");
    assertThat(TextPutter.fill("abcde", 11, "fg")).isEqualTo("abcdefgfgfg");
    assertThat(TextPutter.fill("abcde", 10, "fg ")).isEqualTo("abcdefg fg");
    assertThat(TextPutter.fill("abcde", 10, "fg h")).isEqualTo("abcdefg hf");
    assertThat(TextPutter.fill("あいうえお", 10, "　")).isEqualTo("あいうえお　　　　　");
    assertThat(TextPutter.fill("あいうえお", 10, "かき")).isEqualTo("あいうえおかきかきか");
    assertThat(TextPutter.fill("あいうえお", 11, "かき")).isEqualTo("あいうえおかきかきかき");
    assertThat(TextPutter.fill("あいうえお", 3, "かき")).isEqualTo("あいうえお");
    assertThat(TextPutter.fill("あいうえお", 4, "かき")).isEqualTo("あいうえお");
    assertThat(TextPutter.fill("あいうえお", 5, "かき")).isEqualTo("あいうえお");
    assertThat(TextPutter.fill("あいうえお", 6, "かき")).isEqualTo("あいうえおか");
  }

  @Test
  void testFillAndCutExtra() {
    assertThat(TextPutter.fillAndCutExtra("abcde", 10, " ")).isEqualTo("abcde     ");
    assertThat(TextPutter.fillAndCutExtra("abcde", 10, "fg")).isEqualTo("abcdefgfgf");
    assertThat(TextPutter.fillAndCutExtra("abcde", 11, "fg")).isEqualTo("abcdefgfgfg");
    assertThat(TextPutter.fillAndCutExtra("abcde", 10, "fg ")).isEqualTo("abcdefg fg");
    assertThat(TextPutter.fillAndCutExtra("abcde", 10, "fg h")).isEqualTo("abcdefg hf");
    assertThat(TextPutter.fillAndCutExtra("あいうえお", 10, "　")).isEqualTo("あいうえお　　　　　");
    assertThat(TextPutter.fillAndCutExtra("あいうえお", 10, "かき")).isEqualTo("あいうえおかきかきか");
    assertThat(TextPutter.fillAndCutExtra("あいうえお", 11, "かき")).isEqualTo("あいうえおかきかきかき");
    assertThat(TextPutter.fillAndCutExtra("あいうえお", 3, "かき")).isEqualTo("あいう");
    assertThat(TextPutter.fillAndCutExtra("あいうえお", 4, "かき")).isEqualTo("あいうえ");
    assertThat(TextPutter.fillAndCutExtra("あいうえお", 5, "かき")).isEqualTo("あいうえお");
    assertThat(TextPutter.fillAndCutExtra("あいうえお", 6, "かき")).isEqualTo("あいうえおか");
  }
}
