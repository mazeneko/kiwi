package kiwi.core;

/**
 * 相互変換が可能なコンバーターです。
 */
public interface MutualConverter<L, R> {
  /**
   * {@link L}から{@link R}へ変換します。
   */
  R convert(L value);

  /**
   * {@link R}から{@link L}へ変換します。
   */
  L revert(R value);
}
