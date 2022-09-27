package domain;

import java.util.Objects;

public class GDate {
    private int julianDay = 0;

    public GDate() {
        this.julianDay = 2451545;   // Default date = 2000.01.01
    }

    public GDate(int year, int month, int day) {
        this.julianDay = ( 1461 * ( year + 4800 + ( month - 14 ) / 12 ) ) / 4 +
                ( 367 * ( month - 2 - 12 * ( ( month - 14 ) / 12 ) ) ) / 12 -
                ( 3 * ( ( year + 4900 + ( month - 14 ) / 12 ) / 100 ) ) / 4 +
                day - 32075;
    }

    /**
     *
     * @param date
     */
    public GDate(GDate date) {
        this.julianDay = date.julianDay;
    }

    /**
     *
     * @param julianDay
     */
    public GDate(int julianDay) {
        this.julianDay = julianDay;
    }

    public GDate copy() {
        return new GDate(this.julianDay);
    }

    /**
     *
     * @param date
     */
    public int diff(GDate date) {
        return this.julianDay - date.julianDay;
    }

    /**
     *
     * @param days
     */
    public GDate add(int days) {
        return new GDate(this.julianDay + days);
    }

    /**
     *
     * @param date
     */
    public boolean greaterThan(GDate date) {
        return this.julianDay > date.julianDay;
    }


    public int julianDay() {
        return this.julianDay = julianDay;
    }


    public String toString() {
        return String.format("%04d" + "/" + "%02d" + "/" + "%02d", year(), month(), day());
    }

    public int year() {
        int l = this.julianDay + 68569;
        int n = ( 4 * l ) / 146097;
        l = l - ( 146097 * n + 3 ) / 4;
        int i = ( 4000 * ( l + 1 ) ) / 1461001;
        l = l - ( 1461 * i ) / 4 + 31;
        int j = ( 80 * l ) / 2447;
        int d = l - ( 2447 * j ) / 80;
        l = j / 11;
        int m = j + 2 - ( 12 * l );
        int y = 100 * ( n - 49 ) + i + l;
        return y;
    }

    public int month() {
        int l = this.julianDay + 68569;
        int n = ( 4 * l ) / 146097;
        l = l - ( 146097 * n + 3 ) / 4;
        int i = ( 4000 * ( l + 1 ) ) / 1461001;
        l = l - ( 1461 * i ) / 4 + 31;
        int j = ( 80 * l ) / 2447;
        int d = l - ( 2447 * j ) / 80;
        l = j / 11;
        int m = j + 2 - ( 12 * l );
        return m;
    }

    public int day() {
        int l = this.julianDay + 68569;
        int n = ( 4 * l ) / 146097;
        l = l - ( 146097 * n + 3 ) / 4;
        int i = ( 4000 * ( l + 1 ) ) / 1461001;
        l = l - ( 1461 * i ) / 4 + 31;
        int j = ( 80 * l ) / 2447;
        int d = l - ( 2447 * j ) / 80;
        l = j / 11;
        return d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GDate gDate = (GDate) o;
        return julianDay == gDate.julianDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(julianDay);
    }
}

