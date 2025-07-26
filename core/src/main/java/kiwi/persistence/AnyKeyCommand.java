package kiwi.persistence;

import java.util.List;

import kiwi.core.identify.AnyKeyIdentifiable;

/**
 * 任意の型のIDで使用できるCQSのCommandです。
 * 
 * @param <ID>       IDの型
 * @param <RESOURCE> リソースの型
 */
public interface AnyKeyCommand<ID, RESOURCE extends AnyKeyIdentifiable<ID>> {
  /**
   * リソースを保存します。
   * 
   * @param resource リソース
   * @return 保存したリソース
   */
  default RESOURCE save(RESOURCE resource) {
    return saveAll(List.of(resource)).stream().findAny().orElseThrow();
  }

  /**
   * 複数のリソースをすべて保存します。
   * 
   * @param resources 複数のリソース
   * @return 保存した複数のリソース
   */
  List<RESOURCE> saveAll(List<RESOURCE> resources);

  /**
   * リソースを更新します。
   * 
   * @param resource リソース
   * @return 更新したリソース
   */
  default RESOURCE update(RESOURCE resource) {
    return updateAll(List.of(resource)).stream().findAny().orElseThrow();
  }

  /**
   * 複数のリソースをすべて更新します。
   * 
   * @param resources 複数のリソース
   * @return 更新した複数のリソース
   */
  List<RESOURCE> updateAll(List<RESOURCE> resources);

  /**
   * リソースを削除します。
   * 
   * @param id ID
   * @return 削除した件数
   */
  default long deleteById(ID id) {
    return deleteByIds(List.of(id));
  }

  /**
   * 複数のリソースを削除します。
   * 
   * @param ids 複数のID
   * @return 削除した件数
   */
  long deleteByIds(List<ID> ids);

  /**
   * すべてのリソースを削除します。
   * 
   * @return 削除した件数
   */
  long deleteAll();
}
