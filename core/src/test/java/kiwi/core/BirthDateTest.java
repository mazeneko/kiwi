package kiwi.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.MonthDay;

import org.junit.jupiter.api.Test;

class BirthDateTest {
  @Test
  void testCalcAge() {
    assertThat(BirthDate.calcAge(LocalDate.of(2000, 8, 3), LocalDate.of(2022, 8, 3))).isEqualTo(22);
    assertThat(BirthDate.calcAge(LocalDate.of(2000, 8, 3), LocalDate.of(2022, 8, 2))).isEqualTo(21);
  }

  @Test
  void testCalcAge2() {
    assertThat(new BirthDate(LocalDate.of(2000, 8, 3)).calcAge(LocalDate.of(2022, 8, 3))).isEqualTo(22);
    assertThat(new BirthDate(LocalDate.of(2000, 8, 3)).calcAge(LocalDate.of(2022, 8, 2))).isEqualTo(21);
  }

  @Test
  void testGetDateOfAgeAt() {
    assertThat(BirthDate.getDateOfAgeAt(LocalDate.of(2000, 5, 12), 20)).isEqualTo(LocalDate.of(2020, 5, 12));
  }

  @Test
  void testGetYearOfAgeAt() {
    final LocalDate birthDate = LocalDate.of(2000, 5, 12);
    assertThat(BirthDate.getYearOfAgeAt(birthDate, MonthDay.of(5, 11), 20)).isEqualTo(2021);
    assertThat(BirthDate.getYearOfAgeAt(birthDate, MonthDay.of(5, 12), 20)).isEqualTo(2020);
    assertThat(BirthDate.getYearOfAgeAt(birthDate, MonthDay.of(5, 13), 20)).isEqualTo(2020);
  }
}
