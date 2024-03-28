package mazewands.persistence;

import java.util.List;

/**
 * 識別不能なリソースのQueryです。
 * 
 * @param <RESOURCE> リソースの型
 */
public interface NonIdentifiableQuery<RESOURCE> {
  /**
   * リソース名を取得します。
   *
   * <p>
   * 例外メッセージ等で使用されます。
   * 
   * @return リソース名
   */
  String getResourceName();

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
   * 全てのリソースを取得します。
   * 
   * @return 全てのリソース
   */
  List<RESOURCE> getAll();
}
