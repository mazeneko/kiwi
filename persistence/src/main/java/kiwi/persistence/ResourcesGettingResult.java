package kiwi.persistence;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * リソースの取得結果
 */
public record ResourcesGettingResult<ID, RESOURCE extends AnyKeyIdentifiable<ID>>(
    /** 見つかったリソース */
    List<RESOURCE> foundResources,
    /** 見つからなかったリソースのID */
    List<ID> missingResourceIds) {

  /**
   * 要求したIDのリソースがすべて見つかった場合はリソースを返し、不足している場合は例外を投げます。
   * 
   * @param <X>                リソースが不足している場合に投げる例外
   * @param exceptionGenerator 例外のジェネレーター
   * @return 見つかったリソース
   * @throws X リソースが不足している場合
   */
  public <X extends Throwable> List<RESOURCE> ensureOrThrow(Function<List<ID>, ? extends X> exceptionGenerator)
      throws X {
    if (this.missingResourceIds.isEmpty()) {
      return this.foundResources;
    }
    throw exceptionGenerator.apply(missingResourceIds);
  }

  /**
   * 取得しようとしたリソースのIDを返します。
   * 
   * @return 取得しようとしたリソースのID
   */
  public List<ID> requestResourceIds() {
    return Stream
        .concat(
            this.foundResources.stream().map(RESOURCE::id),
            this.missingResourceIds.stream())
        .toList();
  }
}
