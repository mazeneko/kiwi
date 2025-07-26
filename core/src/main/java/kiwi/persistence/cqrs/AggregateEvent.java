package kiwi.persistence.cqrs;

import kiwi.core.identify.Identifier;

/**
 * {@link Aggregate}に変更を与えるイベントです。
 * 
 * @param <AGGREGATE> 集約の型
 */
public interface AggregateEvent<AGGREGATE extends Aggregate<AGGREGATE>> extends Event {
  /**
   * 集約のIDを返します。
   * 
   * @return 集約ID
   */
  Identifier<AGGREGATE> aggregateId();
}
