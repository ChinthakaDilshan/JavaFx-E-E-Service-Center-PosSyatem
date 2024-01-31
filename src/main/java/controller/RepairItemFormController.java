package controller;

import bo.BoFactory;
import bo.custom.ItemBo;
import bo.custom.RepairItemBo;
import com.jfoenix.controls.JFXTextField;
import dao.util.BoType;
import dto.ItemDto;
import dto.RepairItemDto;
import dto.tm.ItemTm;
import dto.tm.RepairItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class RepairItemFormController {
    public BorderPane paneAdditional;
    public JFXTextField txtAdditionalItemCode;
    public JFXTextField txtAdditionalItemName;
    public JFXTextField txtAdditionalItemPrice;
    public TableView tblAdditionalItem;
    public TableColumn colAdditionalItemCode;
    public TableColumn colAdditionalName;
    public TableColumn colAdditionalPrice;
    public TableColumn colOption;
    private RepairItemBo repairItemBo = BoFactory.getInstance().getBo(BoType. REPAIRITEM);

    public void initialize(){
        colAdditionalItemCode.setCellValueFactory(new PropertyValueFactory<>("repairItemCode"));
        colAdditionalName.setCellValueFactory(new PropertyValueFactory<>("repairItemName"));
        colAdditionalPrice.setCellValueFactory(new PropertyValueFactory<>("repairItemPrice"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadRepairItemTable();

        tblAdditionalItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((RepairItemTm) newValue);
        });
    }

    private void setData(RepairItemTm newValue) {
        if (newValue != null) {
            txtAdditionalItemCode.setEditable(false);
            txtAdditionalItemCode.setText(newValue.getRepairItemCode());
            txtAdditionalItemName.setText(newValue.getRepairItemName());
            txtAdditionalItemPrice.setText(String.valueOf(newValue.getRepairItemPrice()));
        }
    }



    private void loadRepairItemTable() {
        ObservableList<RepairItemTm> tmList = FXCollections.observableArrayList();

        try {
            List<RepairItemDto> dtoList  =repairItemBo.allRepairItems();
            for (RepairItemDto dto:dtoList) {
                Button btn = new Button("Delete");
                RepairItemTm c = new RepairItemTm(
                        dto.getRepairItemCode(),
                        dto.getRepairItemName(),
                        dto.getRepairItemPrice(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteRepairItem(c.getRepairItemCode());
                });

                tmList.add(c);
            }
            tblAdditionalItem.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteRepairItem(String code) {

        try {
            boolean isDeleted = repairItemBo.deleteRepairItem(code);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted!").show();
                loadRepairItemTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneAdditional.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        RepairItemDto dto = new RepairItemDto(txtAdditionalItemCode.getText(),
                txtAdditionalItemName.getText(),
                Double.parseDouble(txtAdditionalItemPrice.getText())
        );

        try {
            boolean isSaved = repairItemBo.saveRepairItem(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
                loadRepairItemTable();
                clearFields();
            }

        } catch (SQLIntegrityConstraintViolationException ex){
            new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        RepairItemDto dto = new RepairItemDto(txtAdditionalItemCode.getText(),
                txtAdditionalItemName.getText(),
                Double.parseDouble(txtAdditionalItemPrice.getText())
        );

        try {
            boolean isUpdated = repairItemBo.updateRepairItem(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Item "+dto.getRepairItemCode()+" Updated!").show();
                loadRepairItemTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tblAdditionalItem.refresh();
        txtAdditionalItemPrice.clear();
        txtAdditionalItemName.clear();
        txtAdditionalItemCode.clear();
        txtAdditionalItemCode.setEditable(true);
    }
}
