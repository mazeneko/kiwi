# kiwi

これは Java でアプリケーションを作るときに便利なクラスライブラリです。

## 使い方

### build.gradle

```kt:
dependencies {
    implementation("io.github.mazeneko:kiwi:3.0.0")
}
```

## 機能

### core

- ID を扱う型やインターフェース
  - Identifier
  - Identifiable
  - AnyKeyIdentifiable
  - NaturalKeyIdentifiable
- 生年月日を扱う型
  - BirthDate
- 相互に変換できることを表すインターフェース
  - MutualConverter
コレクション内の重複を扱えるユーティリティ
  - Duplicates
- 連番を生成するユーティリティ
  - SequenceIntSupplier
  - SequenceLongSupplier
- かなや全銀フォーマットのためのユーティリティ
  - KanaConverter
  - ZenginFormatConverter
- テキスト操作のためのユーティリティ
  - TextPicker
  - TextPutter

### persistence

- リソースを操作したユーザーや時刻を扱うためのインターフェース
  - Signed
  - Timestamped
- リソースを読み込むインターフェース
  - Query
  - AnyKeyQuery
  - NaturalKeyQuery
  - NonIdentifiableQuery
  - SingleResourceQuery
  - ResourceGettingResult
  - ResourcesGettingResult
  - NaturalKeyResourceGettingResult
  - NaturalKeyResourcesGettingResult
- CQSのCommand
  - CqsCommand
  - AnyKeyCqsCommand
  - NaturalKeyCqsCommand
  - NonIdentifiableCqsCommand
  - SingleResourceCqsCommand
- CQRSのEventやAggregate
  - Event
  - EventCreationContext
  - EventCreationContextFactory
  - EventCreationMeta
  - EventStore
  - Aggregate
  - AggregateEvent
  - AggregateEventStore
  - AggregateReplayer
  - AggregateProjector

## How to release

1. バージョンのタグを付ける
1. ./gradlew jreleaserConfig で内容を確認する
1. ./gradlew clean でクリーンしておく
1. ./gradlew publish でビルドしたり POM を生成したりしてローカルリポジトリにリリースする
1. ./gradlew jreleaserFullRelease でリリースする
