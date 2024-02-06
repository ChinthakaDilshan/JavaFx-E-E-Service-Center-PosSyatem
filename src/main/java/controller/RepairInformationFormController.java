package controller;


import bo.BoFactory;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import bo.custom.OrderDetailBo;
import bo.custom.RepairItemBo;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dao.util.BoType;
import dto.*;
import dto.tm.OrderTm;
import dto.tm.RepairsTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Integer.parseInt;

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
    public Label lblIssue;
    public JFXTextField txtDescription;
    public TreeTableColumn colQty;
    public JFXTextField txtQty;
    public JFXTextField txtAmount;
    public TreeTableColumn colAmount;


    private ItemBo itemBo = new ItemBoImpl();
    private OrderBo orderBo = new OrderBoImpl();
    private ItemDao itemDao = new ItemDaoImpl();

    private double total = 0;
    private OrderDetailBo orderDetailBo = BoFactory.getInstance().getBo(BoType.ORDERDETAIL);

    private RepairItemBo repairItemBo = BoFactory.getInstance().getBo(BoType.REPAIRITEM);
    private List<RepairItemDto> repairItems;

    private List<OrderDto> order;
    private List<ItemDto> items;
    private List<OrderDetailDto> orderDetail;
    private ObservableList<RepairsTm> tmList = FXCollections.observableArrayList();

    public void initialize() {
        colAdditionalItem.setCellValueFactory(new TreeItemPropertyValueFactory<>("repairItemCode"));
        colAdditionalItemName.setCellValueFactory(new TreeItemPropertyValueFactory<>("repairItemName"));
        colAdditionalItemPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("repairItemPrice"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colAmount.setCellValueFactory(new TreeItemPropertyValueFactory<>("amount"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));


        try {
            orderDetail = orderDetailBo.allOrderDetails();
            repairItems = repairItemBo.allRepairItems();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadOrderIds();
        loadRepairItemCodes();
        loadDescription();


        txtAdditionalItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (RepairItemDto dto : repairItems) {
                if (dto.getRepairItemCode().equals(newValue.toString())) {
                    lblAdditionalItemName.setText(dto.getRepairItemName());
                    lblAdditionalItemPrice.setText(String.valueOf(dto.getRepairItemPrice()));
                }
            }
        });

        txtOrderID.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            ObservableList list = FXCollections.observableArrayList();
            for (OrderDetailDto dto : orderDetail) {
                if (dto.getOrderId().equals(newValue.toString())) {
                    list.add(dto.getItemCode());
                    txtStatus.setText(dto.getStatus());
                    lblAdvance.setText(String.valueOf(dto.getAdvancePrice()));
                    txtItemCode.setItems(list);
                    lblIssue.setText(dto.getIssue());


                }
            }
        });


    }

    private void loadDescription() {
        ObservableList list = FXCollections.observableArrayList();
        if (items != null && !items.isEmpty()) {
            for (ItemDto dto : items) {
                if (dto.getItemCode().equals(txtItemCode.getValue())) {
                    txtDescription.setText(dto.getItemName());
                }
            }
        }
    }


    private void loadRepairItemCodes() {
        ObservableList list = FXCollections.observableArrayList();

        for (RepairItemDto dto : repairItems) {
            list.add(dto.getRepairItemCode());
        }

        txtAdditionalItemCode.setItems(list);
    }

    private void loadOrderIds() {
        ObservableList listOrderID = FXCollections.observableArrayList();
        for (OrderDetailDto dto : orderDetail) {
            listOrderID.add(dto.getOrderId());

        }


        txtOrderID.setItems(listOrderID);

    }


    public void btnBackOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneRepairInfo.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddToCartOnAction(ActionEvent event) {
        JFXButton btn = new JFXButton("Delete");

        RepairsTm tm = new RepairsTm(
                txtAdditionalItemCode.getValue().toString(),
                lblAdditionalItemName.getText(),
                Double.parseDouble(lblAdditionalItemPrice.getText()),
                parseInt(txtQty.getText()),
                Double.parseDouble(lblAdditionalItemPrice.getText())* parseInt(txtQty.getText()),
                btn
        );
        btn.setOnAction(actionEvent -> {
            tmList.remove(tm);
            total-=tm.getAmount();
            lblTotal.setText(String.format("%.2f",total));
            tblRepairInfo.refresh();
        });
        boolean isExist = false;
        for (RepairsTm repairs : tmList) {
            if (repairs.getRepairItemCode().equals(tm.getRepairItemCode())) {
                repairs.setQty(repairs.getQty()+tm.getQty());
                repairs.setAmount(repairs.getAmount()+tm.getAmount());
                isExist = true;
                total+= tm.getAmount();

            }
        }
        if (!isExist) {
            tmList.add(tm);
            total+=tm.getAmount();
        }

        lblTotal.setText(String.format("%.2f",total));
        RecursiveTreeItem<RepairsTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
        tblRepairInfo.setRoot(treeItem);
        tblRepairInfo.setShowRoot(false);

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnBillOnAction(ActionEvent actionEvent) {

    }

    public void btnCloseOrderOnAction(ActionEvent actionEvent) {

    }
}
