package kiwi.persistence.cqrs;

import kiwi.core.identify.Identifiable;
import kiwi.core.identify.Identifier;

/**
 * イベントソーシングの集約です。
 * 
 * @param <AGGREGATE> 集約の型
 */
public interface Aggregate<AGGREGATE> extends Identifiable<AGGREGATE> {
  /**
   * 最後に適用されたイベントのIDを返します。
   * 
   * @return 最後に適用されたイベントのID
   */
  Identifier<Event> latestEventId();

  /**
   * 削除されている場合はtrueを返します。
   * 
   * @return 削除されている場合はtrue
   */
  Boolean deleted();
}
