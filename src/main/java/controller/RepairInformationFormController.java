package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;

public class RepairInformationFormController {
    public BorderPane paneRepairInfo;
    public JFXComboBox txtOrderID;
    public JFXComboBox txtAdditionalItemCode;
    public JFXComboBox txtItemCode;
    public JFXTextField txtServiceCharge;
    public JFXTextField txtStatus;
    public Label lblAdvance;
    public Label lblDescription;
    public Label lblAdditionalItemName;
    public Label lblAdditionalItemPrice;
    public JFXTreeTableView tblRepairInfo;
    public TreeTableColumn colAdditionalItem;
    public TreeTableColumn colAdditionalItemName;
    public TreeTableColumn colAdditionalItemPrice;
    public TreeTableColumn colOption;
    public Label lblTotal;
    public Label lblBalance;

    public void btnBackOnAction(ActionEvent actionEvent) {

    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnBillOnAction(ActionEvent actionEvent) {

    }

    public void btnCloseOrderOnAction(ActionEvent actionEvent) {

    }
}
