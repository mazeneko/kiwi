package kiwi.core;

import java.time.Clock;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;

/**
 * 生年月日
 */
public record BirthDate(LocalDate value) {
  /**
   * 対象日時点の年齢を返します。
   * 
   * @param birthDate  生年月日
   * @param targetDate 対象日
   */
  public static int calcAge(LocalDate birthDate, LocalDate targetDate) {
    return Math.toIntExact(ChronoUnit.YEARS.between(birthDate, targetDate));
  }

  /**
   * 現在の年齢を返します。
   * 
   * @param birthDate 生年月日
   */
  public static int calcCurrentAge(LocalDate birthDate) {
    return calcAge(birthDate, LocalDate.now());
  }

  /**
   * 現在の年齢を返します。
   * 
   * @param birthDate 生年月日
   * @param clock     clock
   */
  public static int calcCurrentAge(LocalDate birthDate, Clock clock) {
    return calcAge(birthDate, LocalDate.now(clock));
  }

  /**
   * 指定した年齢になる日付を返します。
   * 
   * @param birthDate 生年月日
   * @param age       年齢
   */
  public static LocalDate getDateOfAgeAt(LocalDate birthDate, int age) {
    return birthDate.plusYears(age);
  }

  /**
   * 指定した月日時点で指定した年齢になる年を返します。
   * 
   * @param birthDate 生年月日
   * @param monthDay  月日
   * @param age       年齢
   */
  public static int getYearOfAgeAt(LocalDate birthDate, MonthDay monthDay, int age) {
    final var yearOfAgeAt = getDateOfAgeAt(birthDate, age).getYear();
    return MonthDay.from(birthDate).isAfter(monthDay)
        ? yearOfAgeAt + 1
        : yearOfAgeAt;
  }

  /**
   * 対象日時点の年齢を返します。
   * 
   * @param targetDate 対象日
   */
  public int calcAge(LocalDate targetDate) {
    return calcAge(this.value, targetDate);
  }

  /**
   * 現在の年齢を返します。
   */
  public int getCurrentAge() {
    return calcAge(LocalDate.now());
  }

  /**
   * 現在の年齢を返します。
   * 
   * @param clock clock
   */
  public int getCurrentAge(Clock clock) {
    return calcAge(LocalDate.now(clock));
  }

  /**
   * 指定した年齢になる日付を返します。
   * 
   * @param age 年齢
   */
  public LocalDate getDateOfAgeAt(int age) {
    return getDateOfAgeAt(this.value, age);
  }

  /**
   * 指定した月日時点で指定した年齢になる年を返します。
   * 
   * @param monthDay 月日
   * @param age      年齢
   */
  public int getYearOfAgeAt(MonthDay monthDay, int age) {
    return getYearOfAgeAt(this.value, monthDay, age);
  }
}
