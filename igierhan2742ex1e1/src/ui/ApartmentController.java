package ui;

import domain.Apartment;
import domain.DbContext;
import domain.Invoice;
import domain.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ApartmentController {

//    private ArrayList<Apartment> apartments = new ArrayList<Apartment>();     //ex1e
//    private ArrayList<Person> people = new ArrayList<Person>();       //ex1e

//    @FXML
//    private ComboBox aptComboBox;
    @FXML
    private ComboBox<Apartment> aptComboBox;
    @FXML
    private TextField apartmentNumTextField;
    @FXML
    private TextField squareFeetTextField;
    @FXML
    private TextField bathroomsTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField updatedTextField;
//    @FXML
//    private ComboBox adminComboBox;
    @FXML
    private ComboBox<Person> adminComboBox;     //ex1e
    @FXML
    private ComboBox<Person> tenantComboBox;    //ex1e

    public ApartmentController() {
//        this.apartments = DbContext.getApartments();
//        this.people = DbContext.getPeople();
    }

    @FXML
    protected void initialize() {
        ArrayList<Apartment> apartments = DbContext.getApartments();

        for (Apartment apartment : apartments) {
//            this.aptComboBox.getItems().add(apartment.toShortString());       //ex1e
            this.aptComboBox.getItems().add(apartment);
        }
        this.aptComboBox.getSelectionModel().selectFirst();
//        Apartment apartment = this.aptComboBox.getItems().get(0);

        ArrayList<Person> people = DbContext.getPeople();

        for (Person person : people) {
//            this.adminComboBox.getItems().add(person.toShortString());
            this.adminComboBox.getItems().add(person);      //ex1e
        }
        for (Person person : people) {
//            this.tenantComboBox.getItems().add(person.toShortString());
            this.tenantComboBox.getItems().add(person);     //ex1e

        }
        this.displayApartment(this.aptComboBox.getSelectionModel().getSelectedItem());
    }

    private void displayApartment(Apartment apartment) {
        this.apartmentNumTextField.setText(apartment.getApartmentNum());
        this.squareFeetTextField.setText(Integer.toString(apartment.getSquareFeet()));
        this.bathroomsTextField.setText(Integer.toString(apartment.getBathrooms()));
        this.priceTextField.setText(String.format("%.2f", apartment.getPrice()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.updatedTextField.setText(apartment.getUpdated().format(formatter));


        int selectedIndex = -1;
//        for (int i = 0; i < this.people.size(); i++) {        //ex1e
        for (int i = 0; i < this.adminComboBox.getItems().size(); i++) {
//            Person person = this.people.get(i);
            Person person = this.adminComboBox.getItems().get(i);
            if (person.equals(apartment.getAdministrator())) {
                selectedIndex = i;
                break;
            }
        }
        this.adminComboBox.getSelectionModel().select(selectedIndex);

        selectedIndex = -1;
//        for (int i = 0; i < this.people.size(); i++) {        //ex1e
        for (int i = 0; i < this.tenantComboBox.getItems().size(); i++) {
//            Person person = this.people.get(i);
            Person person = this.tenantComboBox.getItems().get(i);
            if (person.equals(apartment.getTenant())) {
                selectedIndex = i;
                break;
            }
        }
        this.tenantComboBox.getSelectionModel().select(selectedIndex);

    }

    @FXML
    private void saveAptButton_Clicked(ActionEvent actionEvent) {
//        int selectedIndex = this.aptComboBox.getSelectionModel().getSelectedIndex();
        Apartment apartment = this.aptComboBox.getSelectionModel().getSelectedItem();

        apartment.setApartmentNum(this.apartmentNumTextField.getText());
        apartment.setPrice(Double.parseDouble(this.priceTextField.getText()));
        apartment.setSquareFeet(Integer.parseInt(this.squareFeetTextField.getText()));
        apartment.setBathrooms(Integer.parseInt(this.bathroomsTextField.getText()));
        apartment.setUpdated(LocalDateTime.now());

//        int Index = this.aptComboBox.getSelectionModel().getSelectedIndex();
//        Apartment apartments = this.aptComboBox.getItems().get(Index);

//        Index = adminComboBox.getSelectionModel().getSelectedIndex();     //ex1e
//        Person people1 = this.people.get(Index);
//        apartments.setAdministrator(people1);

        apartment.setAdministrator(
            this.adminComboBox.getSelectionModel().getSelectedItem());

//        Index = tenantComboBox.getSelectionModel().getSelectedIndex();        //ex1e
//        Person people2 = this.people.get(Index);
//        apartments.setTenant(people2);

        apartment.setTenant (
                this.tenantComboBox.getSelectionModel().getSelectedItem());

        int selectedIndex = this.aptComboBox.getSelectionModel().getSelectedIndex();
        this.aptComboBox.getItems().remove(selectedIndex);
        this.aptComboBox.getItems().add(selectedIndex, apartment);
        this.aptComboBox.getSelectionModel().select(selectedIndex);
    }

    @FXML
    private void viewInvoiceDlgButton_Clicked(ActionEvent actionEvent) {
//        int selectedIndex = aptComboBox.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0) {
//            Apartment apartment = aptComboBox.getItems().get(selectedIndex);
        Apartment apartment = aptComboBox.getSelectionModel().getSelectedItem();
        if (apartment != null) {
            ArrayList<Invoice> invoices = apartment.getInvoices();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InvoiceView.fxml"));
                InvoiceController invoiceController = new InvoiceController();
                invoiceController.initData(invoices);
                fxmlLoader.setController(invoiceController);
                Pane pane = (Pane) fxmlLoader.load();
//                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Invoice");
                stage.setScene(new Scene(pane, 400, 400));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void aptComboBox_ItemSelected(ActionEvent actionEvent) {
//        int selectedIndex = aptComboBox.getSelectionModel().getSelectedIndex();       //ex1e
//        if (selectedIndex >= 0) {
        Apartment apartment = aptComboBox.getSelectionModel().getSelectedItem();
        if (apartment != null) {
//            displayApartment(aptComboBox.getItems().get(selectedIndex));
            this.displayApartment(this.aptComboBox.getSelectionModel().getSelectedItem());
        }
    }
}
