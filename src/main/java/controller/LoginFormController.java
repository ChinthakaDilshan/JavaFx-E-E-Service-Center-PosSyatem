package controller;

import bo.custom.BoFactory;
import dao.util.BoType;
import dto.UsersDto;
import bo.custom.UsersBo;
import dto.tm.UsersTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginFormController {
    public BorderPane paneLogin;
    public TextField txtEmail;
    public TextField txtPassword;
    public ChoiceBox txtJobRole;
    private UsersBo usersBo = BoFactory.getInstance().getBo(BoType.USERS);
    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        List<UsersDto> dtoList  = usersBo.allUsers();
        for (UsersDto dto:dtoList) {

            if(dto.getEmail().equals(txtEmail.getText())){
               if (dto.getPassword().equals(txtPassword.getText())) {
                   Stage stage = (Stage) paneLogin.getScene().getWindow();
                   try {
                       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));

                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
                   stage.setTitle("DashBoard Form");
                   stage.show();
                    }
               }
            }

        }




    public void btnRegisterOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneLogin.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterForm.fxml"))));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void btnFogotPasswordOnAction(ActionEvent actionEvent) {

    }
}
