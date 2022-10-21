package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContractAdministrator extends Administrator {
    private double monthlyRate;

    public ContractAdministrator(int personId, String firstName, String lastName, String userName,
                                 LocalDateTime birthDate, String ssn, String phone,
                                 LocalDateTime employmentStartDate, double monthlyRate) {
        super(personId, firstName, lastName, userName, birthDate, ssn, phone, employmentStartDate);
            this.monthlyRate = monthlyRate;

    }

    @Override
    public String toString() {
        return super.toString() +
                ", monthlyRate=" + monthlyRate +
                ", grossPay= " + String.format("%.2f" , calcGrossPay());
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    @Override
    public double calcGrossPay() {
        return this.monthlyRate;
    }
}
