package domain;

import exceptions.TimeCardIllegalArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeCardTest {
    TimeCard timeCard;

    @BeforeEach
    void setUp() {
        this.timeCard = new TimeCard(
                LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));
    }

    @Test
    void copy() {
        TimeCard timeCard1 = this.timeCard.copy();
        assertEquals(timeCard1, this.timeCard.copy());
    }

    @Test
    void calcHours() {
        assertEquals(10.0,this.timeCard.calcHours());
    }

    @Test
    void testToString() {
        String strTimeCard = this.timeCard.toString();
        int i = strTimeCard.indexOf("startDateTime");
        strTimeCard = strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 06:00PM, hours=10.00";
        assertEquals(result, strTimeCard);
    }

    @Test
    void startTimeEqualsEndTimeTest() {
        assertThrows(TimeCardIllegalArgumentException.class,
                () -> new TimeCard(111, LocalDateTime.of(2019, 11, 13, 8, 0),
                        LocalDateTime.of(2019, 11, 13, 8, 0)));
    }

    @Test
    void startTimeAfterEndTimeTest() {
        assertThrows(TimeCardIllegalArgumentException.class,
                () -> new TimeCard(112, LocalDateTime.of(2019, 11, 13, 20, 0),
                        LocalDateTime.of(2019, 11, 13, 18, 0)));
    }

    @Test
    void jsonStringifyTest() {
        assertEquals("{\"id\":10001,\"startDateTime\":\"2018/10/22 08:00AM\",\"endDateTime\":\"2018/10/22 06:00PM\"}",
                this.timeCard.jsonStringify());
    }
}