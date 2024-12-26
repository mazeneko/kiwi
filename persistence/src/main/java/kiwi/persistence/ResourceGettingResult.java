package kiwi.persistence;

import java.util.Optional;
import java.util.function.Function;

/**
 * リソースの取得結果
 */
public record ResourceGettingResult<ID, RESOURCE extends AnyKeyIdentifiable<ID>>(
    /** 取得しようとしたリソースのID */
    ID requestResourceId,
    /** 見つかったリソース */
    Optional<RESOURCE> foundResource) {

  /**
   * 要求したIDのリソースが見つかった場合はリソースを返し、見つからなかった場合は例外を投げます。
   * 
   * @param <X>                リソースが見つからなかった場合に投げる例外
   * @param exceptionGenerator 例外のジェネレーター
   * @return 見つかったリソース
   * @throws X リソースが見つからなかった場合
   */
  public <X extends Throwable> RESOURCE ensureOrThrow(Function<ID, ? extends X> exceptionGenerator) throws X {
    return this.foundResource.orElseThrow(() -> exceptionGenerator.apply(requestResourceId));
  }

  /**
   * 見つからなかったリソースのIDを返します。
   * 
   * @return 見つからなかったリソースのID
   */
  public Optional<ID> missingResourceId() {
    if (this.foundResource.isPresent()) {
      return Optional.empty();
    }
    return Optional.of(this.requestResourceId);
  }
}
