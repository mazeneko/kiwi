package kiwi.persistence.cqrs;

import java.util.List;

import kiwi.persistence.Query;

/**
 * {@link Event}を記録/取得できるイベントストアです。
 */
public interface EventStore<EVENT extends Event> extends Query<Event, EVENT> {
  /**
   * イベントを保存します。
   * 
   * @param event イベント
   */
  default void save(EVENT event) {
    save(List.of(event));
  }

  /**
   * 複数のイベントを保存します。
   * 
   * @param events 複数のイベント
   */
  void save(List<? extends EVENT> events);
}