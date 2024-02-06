package controller;

import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDetailDto;
import dto.OrderDto;
import dto.tm.OrderTm;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFormController {
    public BorderPane panePlaceOrder;
    @FXML
    private JFXTreeTableView<OrderTm> tblPlaceOrder;

    @FXML
    private TreeTableColumn<?, ?> colItemCode;

    @FXML
    private TreeTableColumn<?, ?> colDescription;

    @FXML
    private TreeTableColumn<?, ?> colDate;

    @FXML
    private TreeTableColumn<?, ?> colIssue;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    public JFXComboBox txtCustomerID;
    public JFXTextField txtCustomerName;
    public JFXComboBox txtItemCode;
    public JFXTextField txtDescription;
    public Label lblOrderID;
    public JFXTextField txtCategory;
    public JFXTextField txtIssue;
    public Label lblDate;
    public JFXTextField txtAdvance;

    @FXML
    private JFXTextField txtStatus;
    private CustomerBo customerBo = new CustomerBoImpl();

    private ItemBo itemBo = new ItemBoImpl();
    private OrderBo orderBo= new OrderBoImpl();
    private ItemDao itemDao = new ItemDaoImpl();
    private List<CustomerDto> customers;
    private List<ItemDto> items;
    private ObservableList<OrderTm> tmList = FXCollections.observableArrayList();


    public void initialize(){

        Timeline date = new Timeline((new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        )), new KeyFrame(Duration.seconds(1)));
        date.setCycleCount(Animation.INDEFINITE);
        date.play();
        colItemCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new TreeItemPropertyValueFactory<>("date"));
        colIssue.setCellValueFactory(new TreeItemPropertyValueFactory<>("issue"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

        try {
            customers = customerBo.allCustomers();
            items = itemBo.allItems();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadCustomerIds();
        loadItemCodes();

        txtCustomerID.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (CustomerDto dto:customers) {
                if (dto.getCustomerCode().equals(newValue.toString())){
                    txtCustomerName.setText(dto.getCustomerName());
                }
            }
        });

        txtItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (ItemDto dto:items) {
                if (dto.getItemCode().equals(newValue.toString())){
                    txtDescription.setText(dto.getCategory());
                }
            }
        });

        setOrderId();
    }
    private void loadItemCodes() {
        ObservableList list = FXCollections.observableArrayList();

        for (ItemDto dto:items) {
            list.add(dto.getItemCode());
        }

        txtItemCode.setItems(list);
    }

    private void loadCustomerIds() {
        ObservableList list = FXCollections.observableArrayList();

        for (CustomerDto dto:customers) {
            list.add(dto.getCustomerCode());
        }

        txtCustomerID.setItems(list);
    }


    private void setOrderId() {
        try {
            lblOrderID.setText(orderBo.generateId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnAddCartOnAction(ActionEvent actionEvent) {
        JFXButton btn = new JFXButton("Delete");

        OrderTm tm = new OrderTm(
                txtItemCode.getValue().toString(),
                txtDescription.getText(),
                lblDate.getText(),
                txtIssue.getText(),
                btn
        );
        btn.setOnAction(action -> {
            tmList.remove(tm);
            tblPlaceOrder.refresh();
        });
        boolean isExist = false;
        for (OrderTm order:tmList) {
            if (order.getItemCode().equals(tm.getItemCode())){

                isExist = true;

            }
        }
        if (!isExist){
            tmList.add(tm);

        }


        RecursiveTreeItem<OrderTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
        tblPlaceOrder.setRoot(treeItem);
        tblPlaceOrder.setShowRoot(false);



    }


    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        List<OrderDetailDto> list = new ArrayList<>();
        for (OrderTm tm:tmList) {
            list.add(new OrderDetailDto(
                    lblOrderID.getText(),
                    tm.getItemCode(),
                    Double.parseDouble(txtAdvance.getText()),
                    txtStatus.getText(),
                    txtIssue.getText()


            ));
        }

        OrderDto dto = new OrderDto(
                lblOrderID.getText(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")),
                txtCustomerID.getValue().toString(),
                list
        );


        try {
            boolean isSaved = orderBo.saveOrder(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Order Saved!").show();
                setOrderId();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnBillOnAction(ActionEvent actionEvent) {

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) panePlaceOrder.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
