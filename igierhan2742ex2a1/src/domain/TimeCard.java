package domain;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeCard {
    private int timeCardId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimeCard(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.timeCardId = DbContext.getNextTimeCardId();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    private TimeCard(int timeCardId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.timeCardId = timeCardId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public TimeCard(TimeCard timeCard) {
        this.timeCardId = timeCard.timeCardId;
        this.startDateTime = timeCard.startDateTime;
        this.endDateTime = timeCard.endDateTime;
    }

    public TimeCard copy() {
        return new TimeCard(this.timeCardId, this.startDateTime, this.endDateTime);
    }

    public double calcHours() {
        return startDateTime.until(this.endDateTime, ChronoUnit.MINUTES) / 60.0;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");
        return "Id=" + timeCardId +
                ", startDateTime=" + startDateTime.format(formatter) +
                ", endDateTime=" + endDateTime.format(formatter) +
                ", hours=" + String.format("%.2f" , calcHours());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeCard)) return false;
        TimeCard timeCard = (TimeCard) o;
        return timeCardId == timeCard.timeCardId &&
                Objects.equals(startDateTime, timeCard.startDateTime) &&
                Objects.equals(endDateTime, timeCard.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeCardId, startDateTime, endDateTime);
    }
}
