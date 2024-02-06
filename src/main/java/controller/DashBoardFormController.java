package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashBoardFormController {
    public BorderPane paneDashBoard;
    public Label lblTime;
    public Label lblDate;

    public void initialize(){
        calculateTime();
    }

    private void calculateTime() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline date = new Timeline((new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        )), new KeyFrame(Duration.seconds(1)));
        date.setCycleCount(Animation.INDEFINITE);
        date.play();

    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneDashBoard.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnReportsOnAction(ActionEvent actionEvent) {

    }

    public void btnStatusOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneDashBoard.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RepairInfomationForm.fxml"))));
            stage.setTitle("Repair Infomation Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneDashBoard.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"))));
            stage.setTitle("Place Order Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnItemOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneDashBoard.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ItemForm.fxml"))));
            stage.setTitle("Item Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Stage mainStage = (Stage) paneDashBoard.getScene().getWindow();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainStage.getScene().getWindow());
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterForm.fxml"))));
        stage.centerOnScreen();
        stage.setTitle("Register Form");
        stage.setResizable(false);
        stage.show();
    }

    public void btnRepairItemOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneDashBoard.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RepairItemForm.fxml"))));
            stage.setTitle("Repair Item Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
