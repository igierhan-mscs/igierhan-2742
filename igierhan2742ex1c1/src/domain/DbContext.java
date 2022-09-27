package domain;

import java.util.ArrayList;

public class DbContext {
    private static int previousInvoiceId = 1000;
    private static int previousLineItemId = 10000;

    public static int getNextInvoiceId() { return ++previousInvoiceId; }

    public static int getNextLineItemId() { return ++previousLineItemId; }

    public static ArrayList<Invoice> getInvoices() {
        ArrayList<Invoice> invoices = new ArrayList<Invoice>();

        GDate date1 = new GDate(2019, 9, 1);
        GDate date2 = new GDate(2019, 9, 11);

        Invoice invoice1 = new Invoice(1,date1,date2);
        invoice1.addLineItem( new LineItem(1.0, "description1"));
        invoice1.addLineItem( new LineItem(2.0, "description2"));
        invoice1.addLineItem( new LineItem(3.0, "description3"));
        invoice1.addLineItem( new LineItem(4.0, "description4"));
        invoices.add(invoice1.copy());

        Invoice invoice2 = new Invoice(2,
                new GDate(2019, 10, 15),
                new GDate(2019, 11, 1));
        invoice2.addLineItem( new LineItem(5.0, "description5"));
        invoice2.addLineItem( new LineItem(6.0, "description6"));
        invoice2.addLineItem( new LineItem(7.0, "description7"));
        invoice2.addLineItem( new LineItem(8.0, "description8"));
        invoices.add(invoice2.copy());

        return invoices;
    }
}
