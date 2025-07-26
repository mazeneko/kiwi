package kiwi.persistence.cqs;

/**
 * 単一リソースで使用できるCQSのCommandです。
 *
 * @param <RESOURCE> リソースの型
 */
public interface SingleResourceCqsCommand<RESOURCE> {
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
