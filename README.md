# kiwi

これは Java でアプリケーションを作るときに便利なクラスライブラリです。

## 使い方

### build.gradle

```kt:
dependencies {
    implementation("io.github.mazeneko:kiwi:1.0.0")
}
```

## 機能

### core

- 生年月日を扱う型
  - BirthDate
- 相互に変換できることを表すインターフェース
  - MutualConverter
- 連番を生成するユーティリティ
  - SequenceIntSupplier
  - SequenceLongSupplier

### persistence

- ID を扱う型やインターフェース
  - Identifier
  - Identifiable
  - AnyKeyIdentifiable
- リソースを読み書きする CQS のインターフェース
  - Query
  - Command
  - AnyKeyQuery
  - AnyKeyCommand
  - NonIdentifiableQuery
  - NonIdentifiableCommand
  - SingleResourceQuery
  - SingleResourceCommand
  - ResourceGettingResult
  - ResourcesGettingResult
- リソースを操作したユーザーや時刻を扱うためのインターフェース
  - Signed
  - Timestamped

## How to release

1. バージョンのタグを付ける
1. ./gradlew jreleaserConfig で内容を確認する
1. ./gradlew clean でクリーンしておく
1. ./gradlew publish でビルドしたり POM を生成したりしてローカルリポジトリにリリースする
1. ./gradlew jreleaserFullRelease でリリースする
