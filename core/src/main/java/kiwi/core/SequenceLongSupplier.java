package kiwi.core;

import java.util.function.LongSupplier;

/**
 * 呼び出しごとに連番を提供するLongSupplierです。
 * {@code long}の最大値まで達した場合もオーバーフローして動作を継続します。
 */
public class SequenceLongSupplier implements LongSupplier {

  /**
   * 次に提供する数
   */
  private long next;

  /**
   * @param initial 最初に提供する数
   */
  private SequenceLongSupplier(long initial) {
    this.next = initial;
  }

  /**
   * 指定された数からの連番を提供するSequenceLongSupplierを生成します。
   * 
   * @param initial 最初に提供する数
   */
  public static SequenceLongSupplier startWith(long initial) {
    return new SequenceLongSupplier(initial);
  }

  @Override
  public long getAsLong() {
    return this.next++;
  }
}
