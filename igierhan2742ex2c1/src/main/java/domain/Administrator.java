package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Administrator extends Person{
    private LocalDateTime birthDate;
    private String ssn;
    private String phone;
    private LocalDateTime employmentStartDate;

    public Administrator(int personId, String firstName, String lastName, String userName,
                  LocalDateTime birthDate, String ssn, String phone,
                  LocalDateTime employmentStartDate) {
        super(personId, firstName, lastName, userName);
        this.birthDate = birthDate;
        this.ssn = ssn;
        this.phone = phone;
        this.employmentStartDate = employmentStartDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return  super.toString() +
                ", birthDate=" + birthDate.format(formatter) +
                ", ssn='" + ssn + '\'' +
                ", phone='" + phone + '\'' +
                ", employmentStartDate=" + employmentStartDate.format(formatter);
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(LocalDateTime employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    public abstract double calcGrossPay();
}
