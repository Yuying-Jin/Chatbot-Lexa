package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpPageController {

    @FXML
    private TextField signUpUsernameField;
    @FXML
    private PasswordField signUpPasswordField;

    
	public void handleSignUp(ActionEvent event) {
        String name = signUpUsernameField.getText();
        String password = signUpPasswordField.getText();
        if (!name.matches("^[a-zA-Z0-9]+$")) {
            showErrorDialog("Username can only contain letters and numbers!");
            return;
        }

        Configure configure = Configure.getInstance();
        if (configure.isUsernameTaken(name)) {
            showErrorDialog("Username is already taken!");
        } else {
        User newUser = new User(name, password);
        configure.addUser(newUser);  
   
        MainController.switchScene(event, "LoginPage.fxml");
        
        showSuccessDialog("Account created successfully!");
        
        }
    }

    public void showSuccessDialog(String message) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Success");
        dialog.setContentText(message);
        
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButton);
        dialog.showAndWait();
    }
      
    public void showErrorDialog(String message) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("SignUp Error");
        dialog.setContentText(message);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButton);
        
        dialog.showAndWait();
    }
    
    public void BacktoLogin(ActionEvent event) {
        try {
        	MainController.switchScene(event, "LoginPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
