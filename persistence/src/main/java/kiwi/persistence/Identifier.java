package kiwi.persistence;

import java.util.Objects;
import java.util.UUID;

/**
 * ジェネリクスによって対象のリソースをチェックしながら安全に扱えるIDです。
 * たとえば、企業のID{@code Identifier<Employer>}が必要な関数に
 * 従業員のID{@code Identifier<Employee>}を渡そうとした時にコンパイルエラーとすることができます。
 * 
 * <p>
 * IDの値にはUUIDが使われています。
 */
public record Identifier<ID_RESOURCE>(UUID value) {
  public Identifier {
    Objects.requireNonNull(value);
  }

  /**
   * IDを作成します。
   * 
   * @param <R>   識別対象のクラス
   * @param value IDの値
   * @return 作成したID
   */
  public static <R> Identifier<R> of(UUID value) {
    return new Identifier<>(value);
  }

  /**
   * ランダムUUIDでIDを生成します。
   * 
   * <p>
   * UUIDのversion4によるランダム生成です。
   * 
   * @param <R> 識別対象のクラス
   * @return 作成したID
   */
  public static <R> Identifier<R> randomUUID() {
    return new Identifier<>(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return value.toString();
  }
}