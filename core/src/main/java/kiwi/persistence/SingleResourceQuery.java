package kiwi.persistence;

import java.util.Optional;

/**
 * 単一リソースのQueryです。
 *
 * @param <RESOURCE> リソースの型
 */
public interface SingleResourceQuery<RESOURCE> {
  /**
   * リソースが存在するかどうかを判定します。
   * 
   * @return リソースが存在する場合はtrue
   */
  boolean exists();

  /**
   * リソースが存在しないかどうかを判定します。
   * 
   * @return リソースが存在しない場合はtrue
   */
  default boolean notExists() {
    return !exists();
  }

  /**
   * リソースを取得します。
   * 
   * @return {@code Optional}なリソース
   */
  Optional<RESOURCE> get();
}
