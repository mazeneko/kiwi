package kiwi.persistence;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import kiwi.core.identify.NaturalKeyIdentifiable;

/**
 * リソースの取得結果
 */
public record NaturalKeyResourcesGettingResult<NATURAL_KEY, RESOURCE extends NaturalKeyIdentifiable<NATURAL_KEY>>(
    /** 見つかったリソース */
    List<RESOURCE> foundResources,
    /** 見つからなかったリソースの自然キー */
    List<NATURAL_KEY> missingResourceNaturalKeys) {

  /**
   * 要求した自然キーのリソースがすべて見つかった場合はリソースを返し、不足している場合は例外を投げます。
   * 
   * @param <X>                リソースが不足している場合に投げる例外
   * @param exceptionGenerator 例外のジェネレーター
   * @return 見つかったリソース
   * @throws X リソースが不足している場合
   */
  public <X extends Throwable> List<RESOURCE> ensureOrThrow(Function<List<NATURAL_KEY>, ? extends X> exceptionGenerator)
      throws X {
    if (this.missingResourceNaturalKeys.isEmpty()) {
      return this.foundResources;
    }
    throw exceptionGenerator.apply(missingResourceNaturalKeys);
  }

  /**
   * 取得しようとしたリソースの自然キーを返します。
   * 
   * @return 取得しようとしたリソースの自然キー
   */
  public List<NATURAL_KEY> requestResourceNaturalKeys() {
    return Stream
        .concat(
            this.foundResources.stream().map(RESOURCE::naturalKey),
            this.missingResourceNaturalKeys.stream())
        .toList();
  }
}
