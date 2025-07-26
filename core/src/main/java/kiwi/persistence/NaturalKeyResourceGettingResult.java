package kiwi.persistence;

import java.util.Optional;
import java.util.function.Function;

import kiwi.core.identify.NaturalKeyIdentifiable;

/**
 * リソースの取得結果
 */
public record NaturalKeyResourceGettingResult<NATURAL_KEY, RESOURCE extends NaturalKeyIdentifiable<NATURAL_KEY>>(
    /** 取得しようとしたリソースの自然キー */
    NATURAL_KEY requestResourceNaturalKey,
    /** 見つかったリソース */
    Optional<RESOURCE> foundResource) {

  /**
   * 要求した自然キーのリソースが見つかった場合はリソースを返し、見つからなかった場合は例外を投げます。
   * 
   * @param <X>                リソースが見つからなかった場合に投げる例外
   * @param exceptionGenerator 例外のジェネレーター
   * @return 見つかったリソース
   * @throws X リソースが見つからなかった場合
   */
  public <X extends Throwable> RESOURCE ensureOrThrow(Function<NATURAL_KEY, ? extends X> exceptionGenerator) throws X {
    return this.foundResource.orElseThrow(() -> exceptionGenerator.apply(requestResourceNaturalKey));
  }

  /**
   * 見つからなかったリソースの自然キーを返します。
   * 
   * @return 見つからなかったリソースの自然キー
   */
  public Optional<NATURAL_KEY> missingResourceNaturalKey() {
    if (this.foundResource.isPresent()) {
      return Optional.empty();
    }
    return Optional.of(this.requestResourceNaturalKey);
  }
}
