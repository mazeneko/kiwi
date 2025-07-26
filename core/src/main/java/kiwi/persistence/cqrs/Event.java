package kiwi.persistence.cqrs;

import java.time.LocalDateTime;

import kiwi.core.identify.Identifiable;

/**
 * イベントです。
 */
public interface Event extends Identifiable<Event> {
  /**
   * 作成した日時を返します。
   * 
   * @return 作成した日時
   */
  LocalDateTime createdAt();

  /**
   * 作成者を返します。
   * 
   * @return 作成者
   */
  String createdBy();

  /**
   * 指定した他のイベントより新しければtrueを返します。
   * 
   * @param other 対象のイベント
   * @return 指定した他のイベントより新しければtrue
   */
  default boolean isNewerThan(Event other) {
    return other.createdAt().isBefore(this.createdAt());
  }

  /**
   * 指定した日時より新しければtrueを返します。
   * 
   * @param dateTime 日時
   * @return 指定した日時より新しければtrue
   */
  default boolean isNewerThan(LocalDateTime dateTime) {
    return dateTime.isBefore(this.createdAt());
  }
}
