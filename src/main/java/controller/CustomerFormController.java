package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import dao.util.BoType;
import dto.CustomerDto;
import dto.tm.CustomerTm;
import dto.tm.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class CustomerFormController {
    public BorderPane paneCustomer;
    public TextField txtCustomerID;
    public TextField txtCustomerName;
    public TextField txtCustomerContact;
    public TextField txtCustomerEmail;
    public TableView tblCustomer;
    public TableColumn colCustomerID;
    public TableColumn colCustomerName;
    public TableColumn colCustomerContact;
    public TableColumn colCustomerEmail;
    public TableColumn colOption;
    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void initialize(){
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerCode"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<>("customerContact"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomerTable();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((CustomerTm) newValue);
        });
    }



    private void setData(CustomerTm newValue) {
        if (newValue != null) {
            txtCustomerID.setEditable(false);
            txtCustomerID.setText(newValue.getCustomerCode());
            txtCustomerName.setText(newValue.getCustomerName());
            txtCustomerContact.setText(String.valueOf(newValue.getCustomerContact()));
            txtCustomerEmail.setText(newValue.getCustomerEmail());
        }
    }

    private void loadCustomerTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList  = customerBo.allCustomers();
            for (CustomerDto dto:dtoList) {
                Button btn = new Button("Delete");
                CustomerTm c = new CustomerTm(
                        dto.getCustomerCode(),
                        dto.getCustomerName(),
                        dto.getCustomerContact(),
                        dto.getCustomerEmail(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteCustomer(c.getCustomerCode());
                });

                tmList.add(c);
            }
            tblCustomer.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteCustomer(String id) {

        try {
            boolean isDeleted = customerBo.deleteCustomer(id);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                loadCustomerTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }



    public void btnBackOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneCustomer.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        tblCustomer.refresh();
        txtCustomerEmail.clear();
        txtCustomerContact.clear();
        txtCustomerName.clear();
        txtCustomerID.clear();
        txtCustomerID.setEditable(true);
    }
    public void txtSearchOnKeyTyped(KeyEvent keyEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        CustomerDto dto = new CustomerDto(txtCustomerID.getText(),
                txtCustomerName.getText(),
                Integer.parseInt(txtCustomerContact.getText()),
                txtCustomerEmail.getText()
        );

        try {
            boolean isSaved = customerBo.saveCustomer(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Customer Saved!").show();
                loadCustomerTable();
                clearFields();
            }

        } catch (SQLIntegrityConstraintViolationException ex){
            new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        CustomerDto dto = new CustomerDto(txtCustomerID.getText(),
                txtCustomerName.getText(),
                Integer.parseInt(txtCustomerContact.getText()),
                txtCustomerEmail.getText()
        );

        try {
            boolean isUpdated = customerBo.updateCustomer(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Customer "+dto.getCustomerCode()+" Updated!").show();
                loadCustomerTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnReportsOnAction(ActionEvent actionEvent) {

    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        loadCustomerTable();
        tblCustomer.refresh();
        clearFields();
    }
}
