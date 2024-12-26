package kiwi.persistence;

import java.util.Optional;

/**
 * 操作ユーザーのサイン付きのエンティティです。
 */
public interface Signed<ENTITY extends Signed<ENTITY>> {
  /**
   * 作成者を返します。
   * 
   * @return 作成者
   */
  Optional<String> createdBy();

  /**
   * 更新者を返します。
   * 
   * @return 更新者
   */
  Optional<String> updatedBy();

  /**
   * 作成者を変更したエンティティを返します。
   * 
   * @param newValue 新しい作成者の値
   * @return 変更したエンティティ
   */
  default ENTITY withCreatedBy(String newValue) {
    return withCreatedBy(Optional.ofNullable(newValue));
  };

  /**
   * 作成者を変更したエンティティを返します。
   * 
   * @param newValue 新しい作成者の値
   * @return 変更したエンティティ
   */
  ENTITY withCreatedBy(Optional<String> newValue);

  /**
   * 更新者を変更したエンティティを返します。
   * 
   * @param newValue 新しい更新者の値
   * @return 新しい更新者の値
   */
  default ENTITY withUpdatedBy(String newValue) {
    return withUpdatedBy(Optional.ofNullable(newValue));
  };

  /**
   * 更新者を変更したエンティティを返します。
   * 
   * @param newValue 新しい更新者の値
   * @return 新しい更新者の値
   */
  ENTITY withUpdatedBy(Optional<String> newValue);
}
