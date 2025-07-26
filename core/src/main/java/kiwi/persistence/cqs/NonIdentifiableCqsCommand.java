package kiwi.persistence.cqs;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 識別不能なリソースで使用できるCQSのCommandです。
 * 
 * @param <RESOURCE> リソースの型
 */

public interface NonIdentifiableCqsCommand<RESOURCE> {
  /**
   * リソースを保存します。
   * 
   * @param resource リソース
   * @return 保存したリソース
   */
  default RESOURCE save(RESOURCE resource) {
    return saveAll(Collections.singleton(resource)).stream().findAny().orElseThrow();
  }

  /**
   * 複数のリソースをすべて保存します。
   * 
   * @param resources 複数のリソース
   * @return 保存した複数のリソース
   */
  List<RESOURCE> saveAll(Collection<RESOURCE> resources);

  /**
   * すべてのリソースを削除します。
   */
  void deleteAll();
}
