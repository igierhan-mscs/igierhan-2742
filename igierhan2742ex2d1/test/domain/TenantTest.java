package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TenantTest {
    Tenant tenant = null;

    @BeforeEach
    void setUp() {
        this.tenant = new Tenant(102, "Bbbb", "Bbbbb", "bbbb.bbbbb",
                LocalDateTime.of(1988, 10, 30, 0, 0, 0),
                "222-22-2222", "222-222-2222", "Minnesota State College Southeast",
                "Instructor", 2222.22,
                LocalDateTime.of(2013, 10, 30, 0, 0, 0));
    }

    @Test
    void testToString() {
        String result = "102 Bbbbb, Bbbb, birthDate=1988/10/30, ssn='222-22-2222', phone='222-222-2222', employer='Minnesota State College Southeast', occupation='Instructor', grossPay=2222.22, employmentStartDate=2013/10/30";
        assertEquals(result, this.tenant.toString());
    }
}