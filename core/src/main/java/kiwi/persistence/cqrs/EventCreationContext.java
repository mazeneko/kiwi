package kiwi.persistence.cqrs;

import java.time.LocalDateTime;

import kiwi.core.identify.Identifier;

/**
 * イベントを作成するときのコンテキスト
 */
public record EventCreationContext(
    /** 現在日時 */
    LocalDateTime currentTimestamp,
    /** ログイン中のユーザー名 */
    String currentUsername) {

  /**
   * イベントを作成するためのメタデータを生成します。
   * イベントIDはランダムに生成されます。
   * 
   * @return イベントを作成するためのメタデータ
   */
  public EventCreationMeta generateMeta() {
    return generateMeta(Identifier.randomUUID());
  }

  /**
   * イベントを作成するためのメタデータを生成します。
   * 
   * @param eventId イベントID
   * @return イベントを作成するためのメタデータ
   */
  public EventCreationMeta generateMeta(Identifier<Event> eventId) {
    return new EventCreationMeta(
        eventId,
        currentTimestamp,
        currentUsername);
  }
}
