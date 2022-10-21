package ui;

import domain.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(101, "Aaaa", "Aaaaa", "aaaa.aaaaa");
//        System.out.println(person);
        Tenant tenant = new Tenant(102, "Bbbb", "Bbbbb", "bbbb.bbbbb",
                LocalDateTime.now(), "222-22-2222", "222-222-2222", "Minnesota State College Southeast",
                "Instructor", 2222.22, LocalDateTime.now());
        tenant.setUserName("BBBB.BBBBB");
        tenant.setPhone("222-222-2223");
//        System.out.println(tenant);

//        Administrator admin = new Administrator(103, "Cccc", "Ccccc", "cccc.ccccc",
//                LocalDateTime.now(), "333-33-3333", "333-333-3333", LocalDateTime.now());
//        System.out.println(admin);

        ContractAdministrator contractAdmin = new ContractAdministrator(104, "Dddd", "Ddddd", "dddd.ddddd",
                LocalDateTime.now(), "444-44-4444", "444-444-4444", LocalDateTime.now(), 400.0);
//        System.out.println(contractAdmin);

        HourlyAdministrator hourlyAdmin = new HourlyAdministrator( 105, "Eeee", "Eeeee", "eeee.eeeee",
                LocalDateTime.now(), "555-55-5555", "555-555-5555", LocalDateTime.now(), 50.00);

        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 23, 8, 0),
                LocalDateTime.of(2018, 10, 23, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 24, 8, 0),
                LocalDateTime.of(2018, 10, 24, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 25, 8, 0),
                LocalDateTime.of(2018, 10, 25, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 26, 8, 0),
                LocalDateTime.of(2018, 10, 26, 18, 0));

//        System.out.println(hourlyAdmin);

        ArrayList<Person> people = new ArrayList<Person>();
        people.add(person);
        people.add(tenant);
        people.add(contractAdmin);
        people.add(hourlyAdmin);

        double totalGrossPay = 0.0;
        for (Person p : people) {
            System.out.println(p);
            if (p instanceof Administrator)
                totalGrossPay += ((Administrator) p).calcGrossPay();
        }

        ArrayList<TimeCard> timeCards = hourlyAdmin.getTimeCards();
        for (TimeCard timeCard: timeCards) {
            System.out.println("\t" + timeCard);
        }

//        ArrayList<Administrator> administrators = new ArrayList<Administrator>();
//        administrators.add(contractAdmin);
//        administrators.add(hourlyAdmin);

//        double totalGrossPay = 0.0;
//        for (Administrator a : administrators) {
//            totalGrossPay += a.calcGrossPay();
//        }
        System.out.println("Total payroll: " + String.format("%.2f", totalGrossPay));
    }
}
