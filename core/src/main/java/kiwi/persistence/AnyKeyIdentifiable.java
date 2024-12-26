package kiwi.persistence;

/**
 * 任意の型のIDで一意に識別可能なオブジェクトであることを表します。
 * 
 * @param <ID> IDの型
 */
public interface AnyKeyIdentifiable<ID> {
  /**
   * IDを取得します。
   * 
   * @return ID
   */
  ID id();

  /**
   * IDが一致する場合はtrueを返します。
   * 
   * @param other 比較対象のオブジェクト
   */
  default boolean equalsId(AnyKeyIdentifiable<ID> other) {
    return this.id().equals(other.id());
  }
}
