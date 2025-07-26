package kiwi.persistence.cqrs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kiwi.core.identify.Identifier;

/**
 * 集約のイベントから集約をリプレイするReplayer
 */
public interface AggregateReplayer<EVENT extends AggregateEvent<AGGREGATE>, AGGREGATE extends Aggregate<AGGREGATE>> {
  /**
   * 集約のイベントストアを提供します。
   * <p>
   * リプレイのためにイベントを取得するデフォルトメソッドで利用されます。
   * 
   * @return イベントストア
   */
  AggregateEventStore<EVENT, AGGREGATE> provideAggregateEventStore();

  /**
   * スナップショットを返します。
   * <p>
   * リプレイのためにスナップショットを取得するデフォルトメソッドで利用されます。
   * 
   * @param aggregateId 集約ID
   * @return 存在する場合はスナップショット
   */
  Optional<AGGREGATE> getSnapshot(Identifier<AGGREGATE> aggregateId);

  /**
   * 指定した集約をリプレイします。
   * 
   * @param aggregateId 集約ID
   * @return リプレイした集約
   */
  default AGGREGATE replay(Identifier<AGGREGATE> aggregateId) {
    final var events = provideAggregateEventStore().getByAggregateId(aggregateId);
    return replay(events);
  }

  /**
   * 集約をリプレイします。
   * 
   * @param events リプレイするイベント
   * @return リプレイした集約
   */
  AGGREGATE replay(List<EVENT> events);

  /**
   * 指定した集約をリプレイします。
   * <p>
   * スナップショットがある場合はスナップショットからリプレイされます。
   * 
   * @param aggregateId 集約ID
   * @return リプレイした集約
   */
  default AGGREGATE replayWithSnapshot(Identifier<AGGREGATE> aggregateId) {
    final var optSnapshot = getSnapshot(aggregateId);
    if (optSnapshot.isEmpty()) {
      return replay(aggregateId);
    }
    final var snapshot = optSnapshot.orElseThrow();
    final var events = provideAggregateEventStore()
        .getByAggregateIdAfterSnapshot(aggregateId, snapshot.latestEventId());
    return replayWithSnapshot(snapshot, events);
  }

  /**
   * 集約をリプレイします。
   * 
   * @param snapshot スナップショット
   * @param events   リプレイするイベント
   * @return リプレイした集約
   */
  AGGREGATE replayWithSnapshot(AGGREGATE snapshot, List<EVENT> events);

  /**
   * 集約にイベントを適用し、その都度の状態を記録して返します。
   * 監査ログやUIでの履歴表示などに利用できます。
   * 
   * @param aggregateId 集約のID
   * @return 履歴のリスト
   */
  default List<ReplayedSnapshot<EVENT, AGGREGATE>> replayWithHistory(Identifier<AGGREGATE> aggregateId) {
    final var events = provideAggregateEventStore().getByAggregateId(aggregateId);
    final var firstEvent = events.stream().limit(1).toList();
    final var otherEvents = events.stream().skip(1).toList();
    // 最初のスナップショットを作ります。
    final var firstSnapshot = replay(firstEvent);
    // 最初のスナップショットを履歴に記録します。
    final var history = new ArrayList<ReplayedSnapshot<EVENT, AGGREGATE>>();
    history.add(new ReplayedSnapshot<>(firstEvent.getFirst(), firstSnapshot));
    // 最初のスナップショットをもとにイベントを再生しつつ記録します。
    var snapshot = firstSnapshot;
    for (final var event : otherEvents) {
      snapshot = replayWithSnapshot(snapshot, List.of(event));
      history.add(new ReplayedSnapshot<>(event, snapshot));
    }
    return history;
  }

  /**
   * イベントとそれを適用した時点の集約です。
   */
  record ReplayedSnapshot<EVENT extends AggregateEvent<AGGREGATE>, AGGREGATE extends Aggregate<AGGREGATE>>(
      /** イベント */
      EVENT event,
      /** イベントを適用した時点の集約 */
      AGGREGATE aggregate) {

    public ReplayedSnapshot {
      // 集約のIDが一致しているかチェックします。
      if (!event.aggregateId().equals(aggregate.id())) {
        throw new IllegalArgumentException();
      }
      // イベントを適用した時点になっているかチェックします。
      if (!event.id().equals(aggregate.latestEventId())) {
        throw new IllegalArgumentException();
      }
    }
  }
}