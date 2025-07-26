package kiwi.persistence.cqrs;

import java.util.List;

import kiwi.core.identify.Identifier;

/**
 * {@link AggregateEvent}を記録/取得できる{@link EventStore}です。
 */
public interface AggregateEventStore<EVENT extends AggregateEvent<AGGREGATE>, AGGREGATE extends Aggregate<AGGREGATE>>
    extends EventStore<EVENT> {
  /**
   * 指定した集約に関係するイベントを返します。
   * 
   * @param aggregateId 集約のID
   * @return 指定した集約に関係するイベント
   */
  default List<EVENT> getByAggregateId(Identifier<AGGREGATE> aggregateId) {
    return getByAggregateIds(List.of(aggregateId));
  }

  /**
   * 指定した複数の集約に関係するイベントを返します。
   * 
   * @param aggregateIds 複数の集約のID
   * @return 指定した複数の集約に関係するイベント
   */
  List<EVENT> getByAggregateIds(List<Identifier<AGGREGATE>> aggregateIds);

  /**
   * スナップショットより後の指定した集約に関係するイベントを返します。
   * 
   * @param aggregateId     集約のID
   * @param snapshotVersion スナップショットのバージョン
   * @return スナップショットより後の指定した集約に関係するイベント
   */
  List<EVENT> getByAggregateIdAfterSnapshot(Identifier<AGGREGATE> aggregateId, Identifier<Event> snapshotVersion);
}