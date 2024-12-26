package kiwi.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SequenceIntSupplierTest {
  @Test
  void startWithを0から() {
    final SequenceIntSupplier sequenceIntSupplier = SequenceIntSupplier.startWith(0);
    assertEquals(0, sequenceIntSupplier.getAsInt());
    assertEquals(1, sequenceIntSupplier.getAsInt());
    assertEquals(2, sequenceIntSupplier.getAsInt());
    assertEquals(3, sequenceIntSupplier.getAsInt());
    assertEquals(4, sequenceIntSupplier.getAsInt());
    assertEquals(5, sequenceIntSupplier.getAsInt());
    assertEquals(6, sequenceIntSupplier.getAsInt());
    assertEquals(7, sequenceIntSupplier.getAsInt());
    assertEquals(8, sequenceIntSupplier.getAsInt());
    assertEquals(9, sequenceIntSupplier.getAsInt());
    assertEquals(10, sequenceIntSupplier.getAsInt());
  }

  @Test
  void startWithを半端な数字から() {
    final SequenceIntSupplier sequenceIntSupplier = SequenceIntSupplier.startWith(23);
    assertEquals(23, sequenceIntSupplier.getAsInt());
    assertEquals(24, sequenceIntSupplier.getAsInt());
    assertEquals(25, sequenceIntSupplier.getAsInt());
    assertEquals(26, sequenceIntSupplier.getAsInt());
    assertEquals(27, sequenceIntSupplier.getAsInt());
    assertEquals(28, sequenceIntSupplier.getAsInt());
    assertEquals(29, sequenceIntSupplier.getAsInt());
    assertEquals(30, sequenceIntSupplier.getAsInt());
    assertEquals(31, sequenceIntSupplier.getAsInt());
    assertEquals(32, sequenceIntSupplier.getAsInt());
    assertEquals(33, sequenceIntSupplier.getAsInt());
  }

  @Test
  void オーバーフローしたとき() {
    final SequenceIntSupplier sequenceIntSupplier = SequenceIntSupplier.startWith(Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE + 1, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE + 2, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE + 3, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE + 4, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE + 5, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE + 6, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE + 7, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE + 8, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE + 9, sequenceIntSupplier.getAsInt());
    assertEquals(Integer.MIN_VALUE + 10, sequenceIntSupplier.getAsInt());
  }
}
