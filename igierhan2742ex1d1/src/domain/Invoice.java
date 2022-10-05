package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Invoice {
    private int invoiceId;
    private int status;
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
    private Apartment apartment = null;

    /**
     * @param status
     * @param invoiceDate
     * @param dueDate
     */
    public Invoice(int status, LocalDateTime invoiceDate, LocalDateTime dueDate, Apartment apartment) {
        this.invoiceId = DbContext.getNextInvoiceId();
        this.status = status;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.apartment = apartment;
    }

    /**
     * @param invoice
     */
    public Invoice(Invoice invoice) {
        this.invoiceId = invoice.invoiceId;
        this.status = invoice.status;
        this.invoiceDate = invoice.invoiceDate;
        this.dueDate = invoice.dueDate;
        this.apartment = invoice.apartment;

        ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
        for (LineItem lineItem : invoice.lineItems) {
            this.lineItems.add(lineItem.copy());
        }
    }

    public Invoice copy() {
//        Invoice invoice = new Invoice(this.status, this.invoiceDate, this.dueDate);
//        invoice.invoiceId = this.invoiceId;
//        return invoice;
        return new Invoice(this);
    }

    /**
     * @param lineItem
     */
    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(new LineItem(lineItem));
    }

    public void addLineItem(int index, LineItem lineItem) {
        this.lineItems.add(index, new LineItem(lineItem));
    }

    /**
     * @param index
     */
    public LineItem removeLineItem(int index) {
        LineItem lineItem = null;
        if (index >= 0 && index < this.lineItems.size()) {
            lineItem = this.lineItems.get(index).copy();
            this.lineItems.remove(index);
        }
        return lineItem;
    }

    /**
     * @param lineItem
     */
    public LineItem removeLineItem(LineItem lineItem) {
        LineItem removedLineItem = null;
        int index = this.lineItems.indexOf(lineItem);
        if (lineItems.indexOf(lineItem) != -1) {
            removedLineItem = this.lineItems.get(index).copy();
            this.lineItems.remove(lineItem);
        }
        return removedLineItem;
    }

    public double total() {
        double total = 0.0;
        for (int i = 0; i < lineItems.size(); i++) {
            double amount = lineItems.get(i).getAmount();
            total += amount;
        }
        return total;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Apartment getApartment() {
        return apartment;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", status=" + status +
                ", invoiceDate=" + invoiceDate.format(formatter) +
                ", dueDate=" + dueDate.format(formatter)  +
                '}';
    }
    public String toShortString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return Integer.toString(invoiceId) +
                ", status: " + status +
                ", " + invoiceDate.format(formatter);
    }

    public ArrayList<LineItem> getLineItems() {
        ArrayList<LineItem> lineItemsCopy = new ArrayList<LineItem>();
        for (LineItem lineItem : this.lineItems) {
            lineItemsCopy.add(lineItem.copy());
        }
        return lineItems;
    }

    public LineItem getLineItem(int index) {
        LineItem lineItem = null;
        if (index < this.lineItems.size()) {
            lineItem = this.lineItems.get(index).copy();
        }
            return lineItem;
        }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceId() == invoice.getInvoiceId() &&
                getStatus() == invoice.getStatus() &&
                this.invoiceDate.equals(invoice.invoiceDate) &&
                this.dueDate.equals(invoice.dueDate);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(getInvoiceId(), getStatus(), getInvoiceDate(), getDueDate());
//    }
}
