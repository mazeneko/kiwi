package kiwi.persistence;

/**
 * 自然キーで一意に識別可能なオブジェクトであることを表します。
 * <p>
 * {@link Identifiable}や{@link AnyKeyIdentifiable}の補助として便利に使うことができます。
 * 
 * @param <NATURAL_KEY> 自然キーの型
 */
public interface NaturalKeyIdentifiable<NATURAL_KEY> {
  /**
   * 自然キーを取得します。
   * 
   * @return 自然キー
   */
  NATURAL_KEY naturalKey();

  /**
   * 自然キーが一致する場合はtrueを返します。
   * 
   * @param other 比較対象のオブジェクト
   */
  default boolean equalsNaturalKey(NaturalKeyIdentifiable<NATURAL_KEY> other) {
    return this.naturalKey().equals(other.naturalKey());
  }
}
