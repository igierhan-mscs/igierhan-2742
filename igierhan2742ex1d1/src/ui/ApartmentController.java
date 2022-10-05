package ui;

import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;

public class ApartmentController {

    private ArrayList<Apartment> apartments = new ArrayList<Apartment>();
    private ArrayList<Person> people = new ArrayList<Person>();

    @FXML
    private ComboBox aptComboBox;
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
    @FXML
    private ComboBox adminComboBox;
    @FXML
    private ComboBox tenantComboBox;

    public ApartmentController() {
        this.apartments = DbContext.getApartments();
        this.people = DbContext.getPeople();
    }

    @FXML
    protected void initialize() {
        for (Apartment apartment : this.apartments) {
            this.aptComboBox.getItems().add(apartment.toShortString());
        }
        aptComboBox.getSelectionModel().selectFirst();
        Apartment apartment = this.apartments.get(0);

        for (Person person : this.people) {
            this.adminComboBox.getItems().add(person.toShortString());
        }
        for (Person person : this.people) {
            this.tenantComboBox.getItems().add(person.toShortString());
        }
        this.displayApartment(apartment);
    }

    private void displayApartment(Apartment apartment) {
        this.apartmentNumTextField.setText(apartment.getApartmentNum());
        this.squareFeetTextField.setText(Integer.toString(apartment.getSquareFeet()));
        this.bathroomsTextField.setText(Integer.toString(apartment.getBathrooms()));
        this.priceTextField.setText(String.format("%.2f", apartment.getPrice()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.updatedTextField.setText(apartment.getUpdated().format(formatter));


        int selectedIndex = -1;
        for (int i = 0; i < this.people.size(); i++) {
            Person person = this.people.get(i);
            if (person.equals(apartment.getAdministrator())) {
                selectedIndex = i;
                break;
            }
        }
        this.adminComboBox.getSelectionModel().select(selectedIndex);

        selectedIndex = -1;
        for (int i = 0; i < this.people.size(); i++) {
            Person person = this.people.get(i);
            if (person.equals(apartment.getTenant())) {
                selectedIndex = i;
                break;
            }
        }
        this.tenantComboBox.getSelectionModel().select(selectedIndex);

    }

    @FXML
    private void saveAptButton_Clicked(ActionEvent actionEvent) {
        int selectedIndex = this.aptComboBox.getSelectionModel().getSelectedIndex();
        Apartment apartment = this.apartments.get(selectedIndex);

        apartment.setApartmentNum(this.apartmentNumTextField.getText());
        apartment.setPrice(Double.parseDouble(this.priceTextField.getText()));
        apartment.setSquareFeet(Integer.parseInt(this.squareFeetTextField.getText()));
        apartment.setBathrooms(Integer.parseInt(this.bathroomsTextField.getText()));
        apartment.setUpdated(LocalDateTime.now());

        int Index = this.aptComboBox.getSelectionModel().getSelectedIndex();
        Apartment apartments = this.apartments.get(Index);

        Index = adminComboBox.getSelectionModel().getSelectedIndex();
        Person people1 = this.people.get(Index);
        apartments.setAdministrator(people1);

        Index = tenantComboBox.getSelectionModel().getSelectedIndex();
        Person people2 = this.people.get(Index);
        apartments.setTenant(people2);

        this.aptComboBox.getItems().remove(selectedIndex);
        this.aptComboBox.getItems().add(selectedIndex, apartment.toShortString());
        this.aptComboBox.getSelectionModel().select(selectedIndex);
    }

    @FXML
    private void viewInvoiceDlgButton_Clicked(ActionEvent actionEvent) {
        int selectedIndex = aptComboBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Apartment apartment = apartments.get(selectedIndex);
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
        int selectedIndex = aptComboBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            displayApartment(apartments.get(selectedIndex));
        }
    }
}
