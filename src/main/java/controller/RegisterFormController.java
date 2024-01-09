package controller;


import bo.custom.BoFactory;
import bo.custom.UsersBo;
import dao.util.BoType;
import dto.UsersDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {
    public BorderPane paneRegister;
    public TextField txtEmailReg;
    public TextField txtPasswordReg;
    public ChoiceBox txtJobRoleReg;
    private UsersBo usersBo = BoFactory.getInstance().getBo(BoType.USERS);
    public void btnRegisterOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UsersDto dto = new UsersDto(txtEmailReg.getText(),
                txtPasswordReg.getText(),
                txtJobRoleReg.getValue().toString()
        );
        boolean isSaved = usersBo.saveUsers(dto);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"User Registered!").show();

        }



    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) paneRegister.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> jobRoles = FXCollections.observableArrayList(
                "Owner",
                "Manager",
                "Employee"

        );


        txtJobRoleReg.setItems(jobRoles);


    }
}
