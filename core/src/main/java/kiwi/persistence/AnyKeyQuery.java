package kiwi.persistence;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toSet;

import java.util.List;

import kiwi.core.identify.AnyKeyIdentifiable;

/**
 * 任意の型のIDで使用できるCQSのQueryです。
 * 
 * @param <ID>       IDの型
 * @param <RESOURCE> リソースの型
 */
public interface AnyKeyQuery<ID, RESOURCE extends AnyKeyIdentifiable<ID>> {
  /**
   * リソースの件数を取得します。
   * <p>
   * 0以上の値を返します。
   */
  long count();

  /**
   * リソースが存在しないかどうかを判定します。
   * 
   * @return リソースが存在しない場合はtrue
   */
  default boolean isEmpty() {
    final var count = count();
    if (count < 0) {
      throw new IllegalStateException("件数が負の値となっています。");
    }
    return count == 0;
  }

  /**
   * リソースが存在するかどうかを判定します。
   * 
   * @return リソースが存在する場合はtrue
   */
  default boolean isNotEmpty() {
    return !isEmpty();
  }

  /**
   * リソースが存在するかどうかを判定します。
   * 
   * @param id ID
   * @return リソースが存在する場合はtrue
   */
  default boolean exists(ID id) {
    return filterByExists(List.of(id)).stream().findAny().isPresent();
  };

  /**
   * リソースが存在しないかどうかを判定します。
   * 
   * @param id ID
   * @return リソースが存在しない場合はtrue
   */
  default boolean notExists(ID id) {
    return !exists(id);
  }

  /**
   * IDのコレクションを存在するリソースのIDでフィルタします。
   * 
   * @param ids IDのコレクション
   * @return フィルタ後のID。引数に渡されたIDのコレクションからリソースが存在しないIDを除去したイメージです。
   */
  List<ID> filterByExists(List<ID> ids);

  /**
   * IDのコレクションを存在しないリソースのIDでフィルタします。
   * 
   * @param ids IDのコレクション
   * @return フィルタ後のID。引数に渡されたIDのコレクションからリソースが存在するIDを除去したイメージです。
   */
  default List<ID> filterByNotExists(List<ID> ids) {
    final var existsIds = filterByExists(ids);
    return ids.stream().filter(not(existsIds::contains)).toList();
  }

  /**
   * リソースを取得します。
   * 
   * @param id ID
   * @return リソースの取得結果
   */
  default ResourceGettingResult<ID, RESOURCE> getById(ID id) {
    final var resource = getAsPossibleByIds(List.of(id)).stream().findAny();
    return new ResourceGettingResult<>(id, resource);
  }

  /**
   * 複数リソースを取得します。
   * 
   * @param ids IDのコレクション
   * @return リソースのリスト。
   *         渡されたIDに重複があった場合は1件のみ含まれます。
   *         渡されたIDの内、見つからなかった分は含まれません。
   */
  List<RESOURCE> getAsPossibleByIds(List<ID> ids);

  /**
   * 複数リソースを取得します。
   * 
   * @param ids IDのコレクション
   * @return リソースの取得結果。
   *         渡されたIDに重複があった場合は1件のみ含まれます。
   *         渡されたIDの内、見つからなかった分は含まれません。
   */
  default ResourcesGettingResult<ID, RESOURCE> getByIds(List<ID> ids) {
    final var foundResources = getAsPossibleByIds(ids);
    final var foundResourceIds = foundResources.stream().map(RESOURCE::id).collect(toSet());
    final var missingResourceIds = ids.stream().distinct().filter(not(foundResourceIds::contains)).toList();
    return new ResourcesGettingResult<>(foundResources, missingResourceIds);
  }

  /**
   * 全てのリソースを取得します。
   * 
   * @return 全てのリソース
   */
  List<RESOURCE> getAll();
}
