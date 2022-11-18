package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HourlyAdministratorTest {
    HourlyAdministrator hourlyAdmin;

    @BeforeEach
    void setUp() {
        this.hourlyAdmin = new HourlyAdministrator( 105, "Eeee", "Eeeee", "eeee.eeeee",
                LocalDateTime.of(1988, 10, 30, 0, 0, 0),
                "555-55-5555", "555-555-5555",
                LocalDateTime.of(2013, 10, 30, 0, 0, 0),
                50.00);
    }

    // this.hourlyAdmin.addTimeCard(...)
    // retrieve using getTimeCard()
    // use indexOf() and substring() to test string starting at "startDateTime"
    // assertEquals(...)
    @Test
    void addTimeCard() {
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));

        String strTimeCard = this.hourlyAdmin.getTimeCard(0).toString();
        int i = strTimeCard.indexOf("startDateTime");
        strTimeCard = strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 06:00PM, hours=10.00";
        assertEquals(result, strTimeCard);
    }

    // attempt to getTimeCard() using invalid index
    // assert return value is null
    // this.hourlyAdmin.addTimeCard(...)
    // use getTimeCard() to retrieve element 0
    // use indexOf() and substring() to test string starting at "startDateTime"
    // assertEquals(...)
    @Test
    void getTimeCard() {
        this.hourlyAdmin.getTimeCard(0);
        assertEquals(null, this.hourlyAdmin.getTimeCard(0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));
        String strTimeCard = this.hourlyAdmin.getTimeCard(0).toString();
        int i = strTimeCard.indexOf("startDateTime");
        strTimeCard = strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 06:00PM, hours=10.00";
        assertEquals(result, strTimeCard);
    }

    // attempt to removeTimeCard() using invalid index
    // assert return value is null
    // this.hourlyAdmin.addTimeCard(...)
    // use removeTimeCard() to retrieve element 0
    // use indexOf() and substring() to test string starting at "startDateTime"
    // assertEquals(...)
    // retrieve ArrayList<TimeCard> using getTimeCards()
    // test size using AssertEquals(...)
    @Test
    void removeTimeCard() {
        this.hourlyAdmin.removeTimeCard(0);
        assertEquals(null, this.hourlyAdmin.removeTimeCard(0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));
        String strTimeCard = this.hourlyAdmin.removeTimeCard(0).toString();
        int i = strTimeCard.indexOf("startDateTime");
        strTimeCard = strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 06:00PM, hours=10.00";
        assertEquals(result, strTimeCard);
        assertEquals(0, this.hourlyAdmin.getTimeCards().size());
    }

    // add several TimeCards using addTimeCards(...)
    // this.hourlyAdmin.getTimeCards()
    // for each TimeCards in ArrayList
    // assertEquals(this.hourlyAdmin.getTimeCard(...), TimeCard from ArrayList
    // assert this.hourlyAdmin.getTimeCard(...) is a copy of TimeCard from ArrayList
    @Test
    void getTimeCards() {
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 23, 8, 0),
                LocalDateTime.of(2018, 10, 23, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 24, 8, 0),
                LocalDateTime.of(2018, 10, 24, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 25, 8, 0),
                LocalDateTime.of(2018, 10, 25, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 26, 8, 0),
                LocalDateTime.of(2018, 10, 26, 18, 0));
        for (TimeCard timeCard : this.hourlyAdmin.getTimeCards())

        for (int i = 0; i < this.hourlyAdmin.getTimeCards().size(); i++) {
            TimeCard timeCards = this.hourlyAdmin.getTimeCard(i);
            assertEquals(timeCards, this.hourlyAdmin.getTimeCard(i));
            assertFalse(timeCards == this.hourlyAdmin.getTimeCard(i));
        }
    }

    @Test
    void testToString() {
        String result = "105 Eeeee, Eeee, birthDate=1988/10/30, ssn='555-55-5555', phone='555-555-5555', employmentStartDate=2013/10/30, hourlyRate=50.0, totalHours= 0.00, grossPay= 0.00";
        assertEquals(result, this.hourlyAdmin.toString());
    }

    @Test
    void calcTotalHours() {
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 23, 8, 0),
                LocalDateTime.of(2018, 10, 23, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 24, 8, 0),
                LocalDateTime.of(2018, 10, 24, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 25, 8, 0),
                LocalDateTime.of(2018, 10, 25, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 26, 8, 0),
                LocalDateTime.of(2018, 10, 26, 18, 0));
        assertEquals(50.0, this.hourlyAdmin.calcTotalHours());
    }

    @Test
    void calcGrossPay() {
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 23, 8, 0),
                LocalDateTime.of(2018, 10, 23, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 24, 8, 0),
                LocalDateTime.of(2018, 10, 24, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 25, 8, 0),
                LocalDateTime.of(2018, 10, 25, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 26, 8, 0),
                LocalDateTime.of(2018, 10, 26, 18, 0));
        assertEquals(2500.00, this.hourlyAdmin.calcGrossPay());
    }
}