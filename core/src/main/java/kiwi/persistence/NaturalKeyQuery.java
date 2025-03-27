package kiwi.persistence;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toSet;

import java.util.List;

/**
 * 自然キーで使用できるCQSのQueryです。
 * 
 * @param <NATURAL_KEY> 自然キーの型
 * @param <RESOURCE>    リソースの型
 */
public interface NaturalKeyQuery<NATURAL_KEY, RESOURCE extends NaturalKeyIdentifiable<NATURAL_KEY>> {
  /**
   * リソースが存在するかどうかを判定します。
   * 
   * @param naturalKey 自然キー
   * @return リソースが存在する場合はtrue
   */
  default boolean existsNaturalKey(NATURAL_KEY naturalKey) {
    return filterByExistsNaturalKey(List.of(naturalKey)).stream().findAny().isPresent();
  };

  /**
   * リソースが存在しないかどうかを判定します。
   * 
   * @param naturalKey 自然キー
   * @return リソースが存在しない場合はtrue
   */
  default boolean notExistsNaturalKey(NATURAL_KEY naturalKey) {
    return !existsNaturalKey(naturalKey);
  }

  /**
   * 自然キーのコレクションを存在するリソースの自然キーでフィルタします。
   * 
   * @param naturalKeys 自然キーのコレクション
   * @return フィルタ後の自然キー。引数に渡された自然キーのコレクションからリソースが存在しない自然キーを除去したイメージです。
   */
  List<NATURAL_KEY> filterByExistsNaturalKey(List<NATURAL_KEY> naturalKeys);

  /**
   * 自然キーのコレクションを存在しないリソースの自然キーでフィルタします。
   * 
   * @param naturalKeys 自然キーのコレクション
   * @return フィルタ後の自然キー。引数に渡された自然キーのコレクションからリソースが存在する自然キーを除去したイメージです。
   */
  default List<NATURAL_KEY> filterByNotExistsNaturalKey(List<NATURAL_KEY> naturalKeys) {
    final var existsNaturalKeys = filterByExistsNaturalKey(naturalKeys);
    return naturalKeys.stream().filter(not(existsNaturalKeys::contains)).toList();
  }

  /**
   * リソースを取得します。
   * 
   * @param naturalKey 自然キー
   * @return リソースの取得結果
   */
  default NaturalKeyResourceGettingResult<NATURAL_KEY, RESOURCE> getByNaturalKey(NATURAL_KEY naturalKey) {
    final var resource = getAsPossibleByNaturalKeys(List.of(naturalKey)).stream().findAny();
    return new NaturalKeyResourceGettingResult<>(naturalKey, resource);
  }

  /**
   * 複数リソースを取得します。
   * 
   * @param naturalKeys 自然キーのコレクション
   * @return リソースのリスト。
   *         渡された自然キーに重複があった場合は1件のみ含まれます。
   *         渡された自然キーの内、見つからなかった分は含まれません。
   */
  List<RESOURCE> getAsPossibleByNaturalKeys(List<NATURAL_KEY> naturalKeys);

  /**
   * 複数リソースを取得します。
   * 
   * @param naturalKeys 自然キーのコレクション
   * @return リソースの取得結果。
   *         渡された自然キーに重複があった場合は1件のみ含まれます。
   *         渡された自然キーの内、見つからなかった分は含まれません。
   */
  default NaturalKeyResourcesGettingResult<NATURAL_KEY, RESOURCE> getByNaturalKeys(List<NATURAL_KEY> naturalKeys) {
    final var foundResources = getAsPossibleByNaturalKeys(naturalKeys);
    final var foundResourceNaturalKeys = foundResources.stream().map(RESOURCE::naturalKey).collect(toSet());
    final var missingResourceNaturalKeys = naturalKeys.stream().distinct()
        .filter(not(foundResourceNaturalKeys::contains)).toList();
    return new NaturalKeyResourcesGettingResult<>(foundResources, missingResourceNaturalKeys);
  }
}
