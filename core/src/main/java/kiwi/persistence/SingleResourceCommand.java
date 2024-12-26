package kiwi.persistence;

/**
 * 単一リソースのCommandです。
 *
 * @param <RESOURCE> リソースの型
 */
public interface SingleResourceCommand<RESOURCE> {
  /**
   * リソースを保存します。
   * 
   * @param resource リソース
   */
  RESOURCE saveOrUpdate(RESOURCE resource);

  /**
   * リソースを削除します。
   */
  void delete();
}
