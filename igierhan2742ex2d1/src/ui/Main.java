package ui;

import dataaccess.PeopleJSONParser;
import domain.HourlyAdministrator;
import domain.Person;
import domain.TimeCard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<Person>();
        Person person = null;
        //Tenant tenant = null;

        try {
            PeopleJSONParser.readFile("igierhan2742ex2d1/resources/people.json");
            people = PeopleJSONParser.getPeople();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        catch (IOException e) {
            System.out.println("I/O error occurred.");
        }

        for (Person p : people) {
            System.out.println(p);
            if (p.getClass() == HourlyAdministrator.class) {
                HourlyAdministrator hourlyAdministrator = (HourlyAdministrator) p;
                ArrayList<TimeCard> timeCards = hourlyAdministrator.getTimeCards();
                for (TimeCard timeCard: timeCards) {
                    System.out.println("\t" + timeCard);
                }
            }
        }

        System.out.println("\nExceptions: ");
        ArrayList<Exception> exceptions = PeopleJSONParser.getExceptions();
        for (Exception e: exceptions) {
            String msg = e.toString();
            if (e.getCause() != null) msg += "\n\t" + e.getCause();
            System.out.println(msg);
        }
    }
}
