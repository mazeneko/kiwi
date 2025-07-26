package kiwi.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DuplicatesTest {
  @Test
  @DisplayName("空のリストからは重複要素が見つからない")
  void testEmptyList() {
    var duplicates = Duplicates.of(List.of());

    assertThat(duplicates.getDuplicatedElements())
        .isEmpty();

    assertThat(duplicates.getDuplicatedElementsAsSet())
        .isEmpty();
  }

  @Test
  @DisplayName("重複のないリストからは重複要素が見つからない")
  void testNoDuplicates() {
    var duplicates = Duplicates.of(List.of("a", "b", "c"));

    assertThat(duplicates.getDuplicatedElements())
        .isEmpty();

    assertThat(duplicates.getDuplicatedElementsAsSet())
        .isEmpty();
  }

  @Test
  @DisplayName("一部の要素が重複している場合、それらのみを検出する")
  void testSomeDuplicates() {
    var duplicates = Duplicates.of(List.of("a", "b", "a", "c", "b", "d", "a"));

    assertThat(duplicates.getDuplicatedElements())
        .containsExactly("a", "b", "a", "b", "a");

    assertThat(duplicates.getDuplicatedElementsAsSet())
        .containsExactlyInAnyOrder("a", "b");
  }

  @Test
  @DisplayName("すべての要素が同じ場合、その要素だけが重複と判断される")
  void testAllSame() {
    var duplicates = Duplicates.of(List.of("x", "x", "x"));

    assertThat(duplicates.getDuplicatedElements())
        .containsExactly("x", "x", "x");

    assertThat(duplicates.getDuplicatedElementsAsSet())
        .containsExactly("x");
  }

  @Test
  @DisplayName("null を渡すと NullPointerException が発生する")
  void testNullInput() {
    assertThatThrownBy(() -> Duplicates.of(null))
        .isInstanceOf(NullPointerException.class);
  }
}
