package domain;

import java.util.Objects;

public class LineItem {
    private int lineItemId;
    private double amount;
    private String description;

    /**
     *
     * @param amount
     * @param description
     */
    public LineItem(double amount, String description) {
        this.lineItemId = DbContext.getNextLineItemId();
        this.amount = amount;
        this.description = description;
    }

    /**
     *
     * @param lineItem
     */
    public LineItem(LineItem lineItem) {
        this.lineItemId = lineItem.lineItemId;
        this.amount = lineItem.amount;
        this.description = lineItem.description;
    }

    public LineItem copy() {
        return new LineItem(this);
    }

    public int getLineItemId() {
        return lineItemId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "lineItemId=" + lineItemId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return getLineItemId() == lineItem.getLineItemId() &&
                Double.compare(lineItem.getAmount(), getAmount()) == 0 &&
                Objects.equals(getDescription(), lineItem.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLineItemId(), getAmount(), getDescription());
    }
}
