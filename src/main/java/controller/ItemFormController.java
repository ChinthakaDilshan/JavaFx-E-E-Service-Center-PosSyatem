package controller;

import bo.BoFactory;
import bo.custom.ItemBo;
import dao.util.BoType;
import dto.ItemDto;
import dto.tm.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ItemFormController {
    public BorderPane paneItem;
    public TextField txtItemCode;
    public TextField txtItemName;
    public TextField txtCategory;
    public TableView tblItem;
    public TableColumn colItemCode;
    public TableColumn colItemCategory;
    public TableColumn colItemName;
    public TableColumn colOption;
    public TextField txtSearch;
    private ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadItemTable();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((ItemTm) newValue);
        });
    }



    private void setData(ItemTm newValue) {
        if (newValue != null) {
            txtItemCode.setEditable(false);
            txtItemCode.setText(newValue.getItemCode());
            txtItemName.setText(newValue.getItemName());
            txtCategory.setText(String.valueOf(newValue.getCategory()));
        }
    }



    private void loadItemTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

        try {
            List<ItemDto> dtoList  =itemBo.allItems();
            for (ItemDto dto:dtoList) {
                Button btn = new Button("Delete");
                ItemTm c = new ItemTm(
                        dto.getItemCode(),
                        dto.getItemName(),
                        dto.getCategory(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteItem(c.getItemCode());
                });

                tmList.add(c);
            }
            tblItem.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteItem(String code) {

        try {
            boolean isDeleted = itemBo.deleteItem(code);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted!").show();
                loadItemTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        ItemDto dto = new ItemDto(txtItemCode.getText(),
                txtItemName.getText(),
                txtCategory.getText()
        );

        try {
            boolean isSaved = itemBo.saveItem(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
                loadItemTable();
                clearFields();
            }

        } catch (SQLIntegrityConstraintViolationException ex){
            new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void clearFields() {
        tblItem.refresh();
        txtCategory.clear();
        txtItemName.clear();
        txtItemCode.clear();
        txtItemCode.setEditable(true);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        ItemDto dto = new ItemDto(txtItemCode.getText(),
                txtItemName.getText(),
                txtCategory.getText()
        );

        try {
            boolean isUpdated = itemBo.updateItem(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Item "+dto.getItemCode()+" Updated!").show();
                loadItemTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnReportOnAction(ActionEvent actionEvent) {

    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        loadItemTable();
        tblItem.refresh();
        clearFields();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneItem.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
