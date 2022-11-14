package ui;

import domain.DbContext;
import domain.GDate;
import domain.Invoice;
import domain.LineItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;

public class InvoiceController {

    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

    @FXML
    private TextField invoiceIdTextField;
    @FXML
    private TextField statusTextField;
    @FXML
    private TextField invoiceDateTextField;
    @FXML
    private TextField dueDateTextField;
    @FXML
    private ComboBox invoicesComboBox;
    @FXML
    private ListView lineItemsListView;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField totalTextField;

    public InvoiceController() {
        this.invoices = DbContext.getInvoices();
    }

    private void displayInvoice(Invoice invoice) {
        this.invoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
        this.statusTextField.setText(Integer.toString(invoice.getStatus()));
        this.invoiceDateTextField.setText(invoice.getInvoiceDate().toString());
        this.dueDateTextField.setText(invoice.getDueDate().toString());
    }

    @FXML
    protected void initialize() {
        for (Invoice invoice : this.invoices) {
            invoicesComboBox.getItems().add(invoice.toShortString());
        }
        invoicesComboBox.getSelectionModel().selectFirst();
        Invoice invoice = this.invoices.get(0);
        this.displayInvoice(invoice);
        this.displayLineItems(invoice);
    }

    @FXML
    private void invoiceComboBoxItemSelected(ActionEvent actionEvent) {
        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();
        if ( index >= 0) {
            Invoice invoice = this.invoices.get(index);
            this.displayInvoice(invoice);
            this.displayLineItems(invoice);
        }
    }

    @FXML
    private void lineItemsListViewClicked(MouseEvent mouseEvent) {
        // 1) get index of selected invoice
        // 2) get selected invoice from this.invoice
        // 3) get lineItems from selected invoice
        // 4) get index of selected lineItem
        // 5) get selected lineItem
        // 6) displayLineItem()

        int index = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        ArrayList<LineItem> lineItems = invoices.get(index).getLineItems();
        int index2 = this.lineItemsListView.getSelectionModel().getSelectedIndex();
        LineItem lineItem = lineItems.get(index2);
        displayLineItem(lineItem);
    }

    private void displayLineItems(Invoice invoice) {
        // 1) clear lineItemsListView
        // 2) lineItems = invoice.getLineItems()
        // 3) copy each LineItem to lineItemsListView
        // 4) clear description and amount TextFields
        // 5) select first item in lineItemsListView
        // 6) if lineItems has an item, displayLineItem()

        this.lineItemsListView.getItems().clear();
        ArrayList<LineItem> lineItems = invoice.getLineItems();
        for (LineItem lineItem : lineItems) {
            lineItemsListView.getItems().add(lineItem.toShortString());
        }
        this.descriptionTextField.clear();
        this.amountTextField.clear();
        lineItemsListView.getSelectionModel().selectFirst();
        if (lineItems.size() > 0) {
            displayLineItem(lineItems.get(0));
        }
        invoice.total();
        this.totalTextField.setText(String.format("%.2f", invoice.total()));
    }

    private void displayLineItem(LineItem lineItem) {
        // set TextFields using lineItem

        this.descriptionTextField.setText(lineItem.getDescription());
        this.amountTextField.setText(String.format("%.2f", lineItem.getAmount()));
    }

    @FXML
    private void saveInvoiceButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
        // get selected invoice
        // copy from form controls to invoice
        // GDate invoiceDate = new GDate(
        //       Integer.parseInt(this.invoiceDateTextField.getText().substring(0, 4)),
        // remove selected item from invoicesComboBox
        // add invoice.toShortString to invoicesComboBox
        // select item in invoicesComboBox

        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(index);

        invoice.setStatus(Integer.parseInt(this.statusTextField.getText()));

        GDate invoiceDate = new GDate(
                Integer.parseInt(this.invoiceDateTextField.getText().substring(0, 4)),
                Integer.parseInt(this.invoiceDateTextField.getText().substring(5, 7)),
                Integer.parseInt(this.invoiceDateTextField.getText().substring(8, 10)));
        invoice.setInvoiceDate(invoiceDate);
        GDate dueDate = new GDate(
                Integer.parseInt(this.dueDateTextField.getText().substring(0, 4)),
                Integer.parseInt(this.dueDateTextField.getText().substring(5, 7)),
                Integer.parseInt(this.dueDateTextField.getText().substring(8, 10)));
        invoice.setDueDate(dueDate);
        this.invoicesComboBox.getItems().remove(index);
        this.invoicesComboBox.getItems().add(index, invoice.toShortString());
        this.invoicesComboBox.getSelectionModel().select(index);

    }

    @FXML
    private void saveLineItemButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
        // get selected invoice
        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(index);
        // get index of selected LineItem
        // remove selected LineItem from invoice
        int index2 = this.lineItemsListView.getSelectionModel().getSelectedIndex();
        invoice.removeLineItem(index2);
        // create new LineItem
        LineItem lineItem= new LineItem(Double.parseDouble(amountTextField.getText()),
                (descriptionTextField.getText()));
        // add LineItem to invoice
        invoice.addLineItem(index2, lineItem);
        // remove selected LineItem from ListView
        // add new LineItem to ListView
        // select current LineItem in ListView
        this.lineItemsListView.getItems().remove(index2);
        this.lineItemsListView.getItems().add(lineItem.toShortString());
        this.lineItemsListView.getSelectionModel().select(index2);

        invoice.total();
        this.totalTextField.setText(String.format("%.2f", invoice.total()));
    }

    @FXML
    private void addLineItemButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
        // get selected invoice
        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(index);
        // create new LineItem(0.0, "")
        LineItem lineItem= new LineItem(0.0,"");
        // add new LineItem to invoice
        invoice.addLineItem(lineItem);
        // add LineItem shortString to ListView
        this.lineItemsListView.getItems().add(lineItem.toShortString());
        // select last item in ListView
        this.lineItemsListView.getSelectionModel().selectLast();
        // scroll to last item in ListView
        this.lineItemsListView.scrollTo(this.lineItemsListView.getItems().size()-1);
        // displayLineItem()
        displayLineItem(lineItem);
        // set focus to description
        descriptionTextField.requestFocus();
    }

    @FXML
    private void deleteLineItemButtonClicked(ActionEvent actionEvent) {
        // clear text from amountTextField and descriptionTextField
        this.amountTextField.clear();
        this.descriptionTextField.clear();
        // get index of selected invoice
        // get selected invoice
        int index = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(index);
        int index2 = this.lineItemsListView.getSelectionModel().getSelectedIndex();
        // if lineItemIndex > 0
        if (index2 >= 0) {
            invoice.removeLineItem(index);
            this.lineItemsListView.getItems().remove(index);
            this.lineItemsListView.getSelectionModel().selectLast();
            index2 = this.lineItemsListView.getSelectionModel().getSelectedIndex();
            if (index2 >= 0) {
                this.lineItemsListView.scrollTo(index2);
                LineItem lineItem = invoice.getLineItem(index);
                displayLineItem(lineItem);
            }
        }
        //  remove lineItem from invoice
        //  remove item from ListView
        //  select last item in ListView
        //  scroll to last item in ListView
        //  get last lineItem in invoice
        //  displayLineItem()
    }
}
