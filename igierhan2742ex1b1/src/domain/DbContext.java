package domain;

public class DbContext {
    private static int previousInvoiceId = 1000;
    private static int previousLineItemId = 10000;

    public static int getNextInvoiceId() { return ++previousInvoiceId; }

    public static int getNextLineItemId() { return ++previousLineItemId; }
}
