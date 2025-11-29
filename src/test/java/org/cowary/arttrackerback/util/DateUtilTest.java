package org.cowary.arttrackerback.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class DateUtilTest {

    @Test
    void now_ShouldReturnCurrentDate() {
        Date before = new Date(System.currentTimeMillis() - 10);
        Date now = DateUtil.now();
        Date after = new Date(System.currentTimeMillis() + 10);

        assertThat(now).isBetween(before, after);
    }

    @Test
    void then_WithPositiveDifference_ShouldReturnFutureDate() {
        Date now = new Date();
        Date future = DateUtil.then(1, TimeUnit.HOURS);

        assertThat(future).isAfter(now);
        long diff = future.getTime() - now.getTime();
        long expectedDiff = TimeUnit.HOURS.toMillis(1);
        assertThat(diff).isCloseTo(expectedDiff, within(50L));
    }

    @Test
    void then_WithNegativeDifference_ShouldReturnPastDate() {
        Date now = new Date();
        Date past = DateUtil.then(-1, TimeUnit.HOURS);

        assertThat(past).isBefore(now);
        long diff = now.getTime() - past.getTime();
        long expectedDiff = TimeUnit.HOURS.toMillis(1);
        assertThat(diff).isCloseTo(expectedDiff, within(50L));
    }

    @Test
    void difference_InHours_ShouldReturnCorrectValue() {
        Date start = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(5));
        Date end = new Date();

        long diff = DateUtil.difference(start, end, TimeUnit.HOURS);

        assertThat(diff).isEqualTo(5);
    }

    @Test
    void difference_InMinutes_ShouldReturnCorrectValue() {
        Date start = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(30));
        Date end = new Date();

        long diff = DateUtil.difference(start, end, TimeUnit.MINUTES);

        assertThat(diff).isEqualTo(30);
    }

    @Test
    void difference_InSeconds_ShouldReturnCorrectValue() {
        Date start = new Date(System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(45));
        Date end = new Date();

        long diff = DateUtil.difference(start, end, TimeUnit.SECONDS);

        assertThat(diff).isEqualTo(45);
    }

    @Test
    void difference_WhenFromAfterTo_ShouldReturnNegativeValue() {
        Date from = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2));
        Date to = new Date();

        long diff = DateUtil.difference(from, to, TimeUnit.HOURS);

        assertThat(diff).isEqualTo(-2);
    }

    @Test
    void def_ShouldReturnDefaultDate() {
        Date defaultDate = DateUtil.def();
        Date expected = new Date(-5364673200000L);

        assertThat(defaultDate).isEqualTo(expected);
    }

    @Test
    void getYear_WithValidLocalDate_ShouldReturnCorrectYear() {
        LocalDate date = LocalDate.of(2023, 5, 15);
        int year = DateUtil.getYear(date);

        assertThat(year).isEqualTo(2023);
    }

    @Test
    void getYear_WithNullLocalDate_ShouldThrowException() {
        assertThatThrownBy(() -> DateUtil.getYear(null))
                .isInstanceOf(NullPointerException.class);
    }
}