package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    Invoice invoice1;

    @BeforeEach
    void setUp() {
        this.invoice1 = new Invoice(1, new GDate(), new GDate());
    }

    @Test
    void copyConstructor() {
        Invoice invoice2 = new Invoice(this.invoice1);
        assertEquals(this.invoice1.getInvoiceId(), invoice2.getInvoiceId());
        assertEquals(this.invoice1.getStatus(), invoice2.getStatus());
        assertEquals(this.invoice1.getInvoiceDate().julianDay(), invoice2.getInvoiceDate().julianDay());
        assertEquals(this.invoice1.getDueDate().julianDay(), invoice2.getDueDate().julianDay());
//        assertNotEquals(this.invoice1.getInvoiceDate(), invoice2.getInvoiceDate());
//        assertNotEquals(this.invoice1.getDueDate(), invoice2.getDueDate());
        assertFalse(this.invoice1.getInvoiceDate() == invoice2.getInvoiceDate());
        assertFalse(this.invoice1.getDueDate() == invoice2.getDueDate());
    }

    @Test
    void copy() {
        Invoice invoice2 = this.invoice1.copy();
        assertEquals(this.invoice1.getInvoiceId(), invoice2.getInvoiceId());
        assertEquals(this.invoice1.getStatus(), invoice2.getStatus());
        assertEquals(this.invoice1.getInvoiceDate().julianDay(), invoice2.getInvoiceDate().julianDay());
        assertEquals(this.invoice1.getDueDate().julianDay(), invoice2.getDueDate().julianDay());
//        assertNotEquals(this.invoice1.getInvoiceDate(), invoice2.getInvoiceDate());
//        assertNotEquals(this.invoice1.getDueDate(), invoice2.getDueDate());
        assertFalse(this.invoice1.getInvoiceDate() == invoice2.getInvoiceDate());
        assertFalse(this.invoice1.getDueDate() == invoice2.getDueDate());
    }

//    @Test
//    void addLineItem() {
//    }

    @Test
    void removeLineItem() {
        LineItem lineItem1 = new LineItem(1.0, "description1");

        // test Invoice.removeLineItem(int index)
        this.invoice1.addLineItem(lineItem1);
        LineItem removedLineItem = this.invoice1.removeLineItem(0);
        assertEquals(lineItem1, removedLineItem);
        assertFalse(lineItem1 == removedLineItem);
        // test removing from empty ArrayList
        removedLineItem = this.invoice1.removeLineItem(0);
        assertEquals(null, removedLineItem);

//         test Invoice.removeLineItem(LineItem lineItem)
        this.invoice1.addLineItem(lineItem1);
        removedLineItem = this.invoice1.removeLineItem(lineItem1);
        assertEquals(lineItem1, removedLineItem);
        assertFalse(lineItem1 == removedLineItem);
        // test removing from empty ArrayList
        removedLineItem = this.invoice1.removeLineItem(lineItem1);
        assertEquals(null, removedLineItem);
    }

    @Test
    void total() {
        //Invoice invoice1 = new Invoice(1, new GDate(2019,9,1), new GDate(2019,9,1));
        assertEquals(0, invoice1.total());
        LineItem lineItem5 = new LineItem(10, "Sept 2022");
        invoice1.addLineItem(lineItem5);
        assertEquals(10, invoice1.total());
        LineItem lineItem6 = new LineItem(11, "Sept 2022");
        invoice1.addLineItem(lineItem6);
        assertEquals(21, invoice1.total());
        LineItem lineItem7 = new LineItem(12, "Sept 2022");
        invoice1.addLineItem(lineItem7);
        assertEquals(33, invoice1.total());
        invoice1.removeLineItem(lineItem5);
        assertEquals(23, invoice1.total());
        invoice1.removeLineItem(lineItem6);
        assertEquals(12, invoice1.total());
        invoice1.removeLineItem(lineItem7);
        assertEquals(0, invoice1.total());
    }
}