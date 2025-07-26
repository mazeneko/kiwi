package kiwi.core;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * コレクション内の重複を扱うクラスです。
 * <p>
 * たとえば会員IDのリスト内で重複している要素を探したい場合は
 * 
 * <pre>
 * Duplicates
 *     .of(memberIds)
 *     .getDuplicatedElements();
 * </pre>
 * 
 * のようにできます。
 * <p>
 * また、Stream APIのCollectorを提供しているため、
 * 
 * <pre>
 * members
 *     .stream()
 *     .map(Member::memberId)
 *     .collect(Duplicates.collector())
 *     .getDuplicatedElements()
 * </pre>
 * 
 * のようにすることもできます。
 * 
 * @param <T> 要素の型
 */
public class Duplicates<T> {
  /** 元となるコレクション */
  private final Collection<T> sourceCollection;

  private Duplicates(Collection<T> sourceCollection) {
    Objects.requireNonNull(sourceCollection);
    this.sourceCollection = sourceCollection;
  }

  /**
   * 元となるコレクションを指定してインスタンスを作成します。
   * 
   * @param <T>              要素の型
   * @param sourceCollection 元となるコレクション
   * @return 作成したインスタンス
   */
  public static <T> Duplicates<T> of(Collection<T> sourceCollection) {
    return new Duplicates<>(sourceCollection);
  }

  /**
   * Stream APIで利用できるCollectorを返します。
   * 
   * @param <T> 要素の型
   * @return Stream APIで利用できるCollector
   */
  public static <T> Collector<T, ?, Duplicates<T>> collector() {
    return Collectors.collectingAndThen(
        Collectors.toList(),
        Duplicates::of);
  }

  /**
   * それぞれの要素が何回現れるかを返します。
   * 
   * @return 要素から現れた回数へのMap
   */
  public Map<T, Long> getValueCounts() {
    return sourceCollection
        .stream()
        .collect(Collectors.groupingBy(
            Function.identity(),
            LinkedHashMap::new,
            Collectors.counting()));
  }

  /**
   * 重複している要素のリストを返します。
   * 
   * @return 重複している要素のリスト。
   */
  public List<T> getDuplicatedElements() {
    final var duplicatedElements = getDuplicatedElementsAsSet();
    return sourceCollection
        .stream()
        .filter(duplicatedElements::contains)
        .toList();
  }

  /**
   * 重複している要素のセットを返します。
   * 
   * @return 重複している要素のセット。
   */
  public Set<T> getDuplicatedElementsAsSet() {
    return getValueCounts()
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .map(Entry::getKey)
        .collect(Collectors.toSet());
  }
}
