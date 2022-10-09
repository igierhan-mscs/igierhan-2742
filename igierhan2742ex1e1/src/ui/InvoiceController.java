package ui;

import domain.Invoice;
import domain.LineItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
//    @FXML
//    private ComboBox invoicesComboBox;
    @FXML
    private ComboBox<Invoice> invoicesComboBox;
//    @FXML
//    private ListView lineItemsListView;       ex1e
    @FXML
    private ListView<LineItem> lineItemsListView;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField totalTextField;

//    public InvoiceController() {
//        this.invoices = DbContext.getInvoices();
//    }

    public InvoiceController() { }

    public void initData(ArrayList<Invoice> invoices) { this.invoices = invoices; }

    private void displayInvoice(Invoice invoice) {
        this.invoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
        this.statusTextField.setText(Integer.toString(invoice.getStatus()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.invoiceDateTextField.setText(invoice.getInvoiceDate().format(formatter));
        this.dueDateTextField.setText(invoice.getDueDate().format(formatter));
    }

    @FXML
    protected void initialize() {
        if (this.invoices.size() > 0) {
            for (Invoice invoice : this.invoices) {
                invoicesComboBox.getItems().add(invoice);
            }
            this.invoices = null;
            invoicesComboBox.getSelectionModel().selectFirst();
//            Invoice invoice = this.invoices.get(0);       //ex1e
            Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();
            this.displayInvoice(invoice);
            this.displayLineItems(invoice);
        }
    }

    @FXML
    private void invoiceComboBoxItemSelected(ActionEvent actionEvent) {
        Invoice invoice = invoicesComboBox.getSelectionModel().getSelectedItem();
        if (invoice != null) {
//            Invoice invoice = this.invoices.get(index);
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

//        int index = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
//        ArrayList<LineItem> lineItems = invoices.get(index).getLineItems();
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();
        ArrayList<LineItem> lineItems = invoice.getLineItems();

        LineItem lineItem = this.lineItemsListView.getSelectionModel().getSelectedItem();
//        LineItem lineItem = lineItems.get(index2);        //ex1e

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
            lineItemsListView.getItems().add(lineItem);
        }
        this.descriptionTextField.clear();
        this.amountTextField.clear();
        lineItemsListView.getSelectionModel().selectFirst();
//        if (lineItems.size() > 0) {
//            displayLineItem(lineItems.get(0));        //ex1e
//        }
        LineItem lineItem = lineItemsListView.getSelectionModel().getSelectedItem();
        if (lineItem != null)
            displayLineItem(lineItem);

//        invoice.total();
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

//        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();      //ex1e
//        Invoice invoice = this.invoices.get(index);

        Invoice invoice = invoicesComboBox.getSelectionModel().getSelectedItem();

        invoice.setStatus(Integer.parseInt(this.statusTextField.getText()));
//
//        GDate invoiceDate = new GDate(
//                Integer.parseInt(this.invoiceDateTextField.getText().substring(0, 4)),
//                Integer.parseInt(this.invoiceDateTextField.getText().substring(5, 7)),
//                Integer.parseInt(this.invoiceDateTextField.getText().substring(8, 10)));
//        invoice.setInvoiceDate(invoiceDate);
//        GDate dueDate = new GDate(
//                Integer.parseInt(this.dueDateTextField.getText().substring(0, 4)),
//                Integer.parseInt(this.dueDateTextField.getText().substring(5, 7)),
//                Integer.parseInt(this.dueDateTextField.getText().substring(8, 10)));
//        invoice.setDueDate(dueDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        invoice.setInvoiceDate(LocalDateTime.parse(this.invoiceDateTextField.getText(), formatter));
        LocalDate date;
        date = LocalDate.parse(this.invoiceDateTextField.getText(), formatter);
        invoice.setInvoiceDate(date.atStartOfDay());
//        invoice.setDueDate(LocalDateTime.parse(this.dueDateTextField.getText(), formatter));
        date = LocalDate.parse(this.dueDateTextField.getText(), formatter);
        invoice.setDueDate(date.atStartOfDay());

        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();
        this.invoicesComboBox.getItems().remove(index);
        this.invoicesComboBox.getItems().add(index, invoice);
        this.invoicesComboBox.getSelectionModel().select(index);

    }

    @FXML
    private void saveLineItemButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
        // get selected invoice

//        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();      //ex1e
//        Invoice invoice = this.invoices.get(index);
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();
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
        this.lineItemsListView.getItems().add(lineItem);
        this.lineItemsListView.getSelectionModel().select(index2);

        invoice.total();
        this.totalTextField.setText(String.format("%.2f", invoice.total()));
    }

    @FXML
    private void addLineItemButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
        // get selected invoice

//        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();      //ex1e
//        Invoice invoice = this.invoices.get(index);
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();
        // create new LineItem(0.0, "")
        LineItem lineItem= new LineItem(0.0,"");
        // add new LineItem to invoice
        invoice.addLineItem(lineItem);
        // add LineItem shortString to ListView
        this.lineItemsListView.getItems().add(lineItem);
        // select last item in ListView
        this.lineItemsListView.getSelectionModel().selectLast();
        // scroll to last item in ListView
//        this.lineItemsListView.scrollTo(this.lineItemsListView.getItems().size()-1);  //ex1e
        this.lineItemsListView.scrollTo(lineItem);
        // displayLineItem()
        displayLineItem(lineItem);
        // set focus to description
        this.descriptionTextField.requestFocus();
    }

    @FXML
    private void deleteLineItemButtonClicked(ActionEvent actionEvent) {
        // clear text from amountTextField and descriptionTextField
        this.amountTextField.clear();
        this.descriptionTextField.clear();
        // get index of selected invoice
        // get selected invoice
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();
        if (invoice != null) {
//            Invoice invoice = this.invoices.get(index);
            int index2 = this.lineItemsListView.getSelectionModel().getSelectedIndex();
            // if lineItemIndex > 0
            if (index2 >= 0) {
                invoice.removeLineItem(index2);
                this.lineItemsListView.getItems().remove(index2);
                this.lineItemsListView.getSelectionModel().selectLast();
//                index2 = this.lineItemsListView.getSelectionModel().getSelectedIndex();
//                if (index2 >= 0) {
//                    this.lineItemsListView.scrollTo(index2);
//                    LineItem lineItem = invoice.getLineItem(index2);
//                    displayLineItem(lineItem);
//                }
                LineItem lineItem = this.lineItemsListView.getSelectionModel().getSelectedItem();
                if (lineItem != null) {
                    this.lineItemsListView.scrollTo(lineItem);
                    displayLineItem(lineItem);
                }
                this.totalTextField.setText(String.format("%.2f", invoice.total()));
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
