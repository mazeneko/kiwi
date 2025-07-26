package kiwi.persistence.cqrs;

import java.time.LocalDateTime;

import kiwi.core.identify.Identifier;

/**
 * イベントを作成するためのメタデータ
 */
public record EventCreationMeta(
    /** イベントID */
    Identifier<Event> eventId,
    /** イベントを作成した日時 */
    LocalDateTime eventCreatedAt,
    /** イベントの作成者 */
    String eventCreatedBy) {
}
