package kiwi.persistence;

import java.util.List;

/**
 * 自然キーで使用できるCQSのCommandです。
 * <p>
 * {@link Command}や{@link AnyKeyCommand}の補助として便利に使うことができます。
 * 
 * @param <NATURAL_KEY> 自然キーの型
 * @param <RESOURCE>    リソースの型
 */
public interface NaturalKeyCommand<NATURAL_KEY, RESOURCE extends NaturalKeyIdentifiable<NATURAL_KEY>> {
  /**
   * リソースを削除します。
   * 
   * @param naturalKey 自然キー
   * @return 削除した件数
   */
  default long deleteByNaturalKey(NATURAL_KEY naturalKey) {
    return deleteByNaturalKeys(List.of(naturalKey));
  }

  /**
   * 複数のリソースを削除します。
   * 
   * @param naturalKeys 複数の自然キー
   * @return 削除した件数
   */
  long deleteByNaturalKeys(List<NATURAL_KEY> naturalKeys);
}
