package kiwi.core;

import java.util.function.IntSupplier;

/**
 * 呼び出しごとに連番を提供するIntSupplierです。
 * {@code int}の最大値まで達した場合もオーバーフローして動作を継続します。
 */
public class SequenceIntSupplier implements IntSupplier {

  /**
   * 次に提供する数
   */
  private int next;

  /**
   * @param initial 最初に提供する数
   */
  private SequenceIntSupplier(int initial) {
    this.next = initial;
  }

  /**
   * 指定された数からの連番を提供するSequenceIntSupplierを生成します。
   * 
   * @param initial 最初に提供する数
   */
  public static SequenceIntSupplier startWith(int initial) {
    return new SequenceIntSupplier(initial);
  }

  @Override
  public int getAsInt() {
    return this.next++;
  }
}
