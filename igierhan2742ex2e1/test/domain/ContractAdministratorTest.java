package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContractAdministratorTest {
    ContractAdministrator contractAdmin;

    @BeforeEach
    void setUp() {
        this.contractAdmin = new ContractAdministrator(104, "Dddd", "Ddddd", "dddd.ddddd",
                LocalDateTime.of(1988, 10, 30, 0, 0, 0),
                "444-44-4444", "444-444-4444",
                LocalDateTime.of(2013, 10, 30, 0, 0, 0),
                400.0);
    }

    @Test
    void testToString() {
        String result = "104 Ddddd, Dddd, birthDate=1988/10/30, ssn='444-44-4444', phone='444-444-4444', employmentStartDate=2013/10/30, monthlyRate=400.0, grossPay= 400.00";
        assertEquals(result, this.contractAdmin.toString());
    }

    @Test
    void calcGrossPay() {
        assertEquals(400.0, this.contractAdmin.calcGrossPay());
    }
}