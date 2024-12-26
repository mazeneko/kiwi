package kiwi.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SequenceLongSupplierTest {
  @Test
  void startWithを0から() {
    final SequenceLongSupplier sequenceLongSupplier = SequenceLongSupplier.startWith(0);
    assertEquals(0, sequenceLongSupplier.getAsLong());
    assertEquals(1, sequenceLongSupplier.getAsLong());
    assertEquals(2, sequenceLongSupplier.getAsLong());
    assertEquals(3, sequenceLongSupplier.getAsLong());
    assertEquals(4, sequenceLongSupplier.getAsLong());
    assertEquals(5, sequenceLongSupplier.getAsLong());
    assertEquals(6, sequenceLongSupplier.getAsLong());
    assertEquals(7, sequenceLongSupplier.getAsLong());
    assertEquals(8, sequenceLongSupplier.getAsLong());
    assertEquals(9, sequenceLongSupplier.getAsLong());
    assertEquals(10, sequenceLongSupplier.getAsLong());
  }

  @Test
  void startWithを半端な数字から() {
    final SequenceLongSupplier sequenceLongSupplier = SequenceLongSupplier.startWith(23);
    assertEquals(23, sequenceLongSupplier.getAsLong());
    assertEquals(24, sequenceLongSupplier.getAsLong());
    assertEquals(25, sequenceLongSupplier.getAsLong());
    assertEquals(26, sequenceLongSupplier.getAsLong());
    assertEquals(27, sequenceLongSupplier.getAsLong());
    assertEquals(28, sequenceLongSupplier.getAsLong());
    assertEquals(29, sequenceLongSupplier.getAsLong());
    assertEquals(30, sequenceLongSupplier.getAsLong());
    assertEquals(31, sequenceLongSupplier.getAsLong());
    assertEquals(32, sequenceLongSupplier.getAsLong());
    assertEquals(33, sequenceLongSupplier.getAsLong());
  }

  @Test
  void オーバーフローしたとき() {
    final SequenceLongSupplier sequenceLongSupplier = SequenceLongSupplier.startWith(Long.MAX_VALUE);
    assertEquals(Long.MAX_VALUE, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE + 1, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE + 2, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE + 3, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE + 4, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE + 5, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE + 6, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE + 7, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE + 8, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE + 9, sequenceLongSupplier.getAsLong());
    assertEquals(Long.MIN_VALUE + 10, sequenceLongSupplier.getAsLong());
  }
}
