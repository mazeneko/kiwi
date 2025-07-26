package kiwi.persistence.cqrs;

import java.util.List;
import java.util.stream.Collectors;

import kiwi.core.identify.Identifiable;
import kiwi.core.identify.Identifier;

/**
 * 集約をリードモデルに投影するProjectorです。
 * 
 * @param <AGGREGATE> 集約の型
 */
public interface AggregateProjector<AGGREGATE extends Aggregate<AGGREGATE>, READ_MODEL extends Identifiable<AGGREGATE>> {
  /**
   * 集約をリードモデルに投影します。
   * 
   * @param aggregate 集約
   */
  default void project(AGGREGATE aggregate) {
    project(List.of(aggregate));
  }

  /**
   * 複数の集約をリードモデルに投影します。
   * 
   * @param aggregates 複数の集約
   */
  default void project(List<AGGREGATE> aggregates) {
    final var deletedPartition = aggregates.stream().collect(Collectors.partitioningBy(Aggregate::deleted));
    final var deletedAggregates = deletedPartition.get(true);
    final var activeAggregates = deletedPartition.get(false);
    if (!deletedAggregates.isEmpty()) {
      final var ids = deletedAggregates.stream().map(Aggregate::id).toList();
      projectDeleted(ids);
    }
    if (!activeAggregates.isEmpty()) {
      final var activeReadModels = activeAggregates.stream().map(this::generateReadModel).toList();
      projectActive(activeReadModels);
    }
  }

  /**
   * 集約からリードモデルを生成します。
   * 
   * @param aggregate 集約
   * @return リードモデル
   */
  READ_MODEL generateReadModel(AGGREGATE aggregate);

  /**
   * 集約の削除を投影します。
   * 
   * @param deletedAggregateIds 削除された集約のID
   */
  void projectDeleted(List<Identifier<AGGREGATE>> deletedAggregateIds);

  /**
   * アクティブなリードモデルの追加や更新を反映します。
   * 
   * @param activeReadModels アクティブなリードモデル
   */
  void projectActive(List<READ_MODEL> activeReadModels);
}
