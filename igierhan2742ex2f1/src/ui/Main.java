package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import dataaccess.SensorReadingJSONParser;
import domain.Sensor;
import domain.SensorReading;

public class Main {
    public static void main(String[] args) {
        ArrayList<SensorReading> sensorReadings = new ArrayList<SensorReading>();
        Sensor sensor = new Sensor(2, 1, 1,"Heat register");

        try {
            SensorReadingJSONParser.readFile("igierhan2742ex2f1/resources/readings.json");
            sensor.setSensorReadings(sensorReadings = SensorReadingJSONParser.getSensorReadings());
        } catch (Exception e) {
            System.out.println(e);
        }
//        for (SensorReading r : sensorReadings) {
//            System.out.println(r.toString());
//        }
        int index = sensor.findMinReadingIndex();
        System.out.println("\nMin: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex();
        System.out.println("Max: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());

        index = sensor.findMinReadingIndex(84, 95);
        System.out.println("Min between index 84 and 95: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex(8, 18);
        System.out.println("Max between index 8 and 18: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());

        // Invalid indexes
        index = sensor.findMinReadingIndex(84, 95);
        System.out.println("Min between index 84 and 95: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex(8, 18);
        System.out.println("Max between index 8 and 18: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());

        try {
            index = sensor.findMinReadingIndex(-1, 10);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            index = sensor.findMinReadingIndex(1, 1000);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            index = sensor.findMinReadingIndex(1, 1);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            index = sensor.findMaxReadingIndex(1, -1);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            index = sensor.findMaxReadingIndex(1, 1000);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            index = sensor.findMaxReadingIndex(1, 1);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        index = sensor.findNextCycleMaxIndex(0);
        System.out.println("First max after index 0: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());

        index = sensor.findNextCycleMinIndex(5);
        System.out.println("First min after index 5: index = " + index + ", " +
                sensor.getSensorReadings().get(index).toString());

        int prevIndex = 0;
        index = sensor.findNextCycleMaxIndex(0);
        while (index > prevIndex) {
            prevIndex = index;
            System.out.println("Index: " + index + ", Max: " + sensor.getSensorReading(index));
            index = sensor.findNextCycleMinIndex(index);
            if (index > prevIndex)
                System.out.println("Index: " + index + ", Min: " + sensor.getSensorReading(index));
            index = sensor.findNextCycleMaxIndex(index);
        }
    }
}