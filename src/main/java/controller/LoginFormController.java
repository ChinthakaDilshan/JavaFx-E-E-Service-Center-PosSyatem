package controller;

import bo.BoFactory;
import dao.util.BoType;
import dto.ItemDto;
import dto.OrderDto;
import dto.UsersDto;
import bo.custom.UsersBo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {
    public BorderPane paneLogin;
    public TextField txtEmail;
    public TextField txtPassword;
    public ChoiceBox txtJobRole;
    private UsersBo usersBo = BoFactory.getInstance().getBo(BoType.USERS);
    private List<UsersDto> users;
    public void initialize(){
        try {
            users = usersBo.allUsers();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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

    public void btnFogotPasswordOnAction(ActionEvent actionEvent) throws IOException {
        if (!txtEmail.getText().isEmpty() && txtEmail.getText()!=null) {
            try {
                ForgetPasswordFormController.userName = txtEmail.getText();
                for (UsersDto dto : users) {
                    if (dto.getEmail().equals(txtEmail.getText())) {
                        ForgetPasswordFormController.jobRole = dto.getJobRole();
                    }
                }
                Stage mainStage = (Stage) paneLogin.getScene().getWindow();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(mainStage.getScene().getWindow());
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FogotPasswordForm.fxml"))));
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Enter Username..!").show();
        }
    }
}
