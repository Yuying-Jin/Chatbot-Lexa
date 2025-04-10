package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private FlowPane recipeButtonsContainer;
    @FXML
    private Configure configure = Configure.getInstance();  // get Configure object
 
    // initialize recipe button
    @FXML
    private void initialize() {
    	if (recipeButtonsContainer == null) {
    		System.out.println("recipeButtonsContainer is null");
    	} else {
	        loadRecipeButtons();	// create button for each recipe
	    }
    }
    
    private void loadRecipeButtons() {
    	recipeButtonsContainer.getChildren().clear();  
        ArrayList<Recipe> recipes = configure.getRecipes();
        
        recipes.sort((r1, r2) -> Integer.compare(r2.getFavorite(), r1.getFavorite()));

        for (Recipe recipe : recipes) {
        	Button recipeButton = new Button(recipe.getName());
            recipeButton.setMinWidth(125);  
            //recipeButton.setMaxWidth(125);  
            recipeButton.setPrefWidth(125); 
            recipeButton.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2;");
            recipeButton.setOnAction(event -> portalToRecipeDetails(recipe));  
            recipeButtonsContainer.getChildren().add(recipeButton);  
        }
    }    

    // Login page function
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
//        System.out.print("username = " + usernameField.getText() + "  password =" + passwordField.getText() );
        
        Configure configure = Configure.getInstance();
        
        if (configure.isAdmin(username, password)) {
        	switchScene(event, "Admin_home.fxml");
        } else if (configure.isUser(username, password)) {
        	switchScene(event, "User_home.fxml");
        } else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login Error");
            dialog.setContentText("Invalid username or password!");

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(okButton);   
            dialog.showAndWait();
        }
    }
    
    // Transfer recipe data
	private void portalToRecipeDetails(Recipe recipe) {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/RecipeShow.fxml"));
            Parent root = loader.load();

            RecipeShowController controller = loader.getController();
            controller.showRecipeDetails(recipe);

            Stage stage = (Stage) recipeButtonsContainer.getScene().getWindow(); 
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    // Search Recipe function
    @FXML
    private TextField txtSearch;
    
    public void showRecipe(ActionEvent event) {
        Button srcbtn = (Button) event.getSource();
        String btntxt = srcbtn.getText();

        ArrayList<Recipe> recipes = configure.getRecipes();
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(btntxt)) {
                // 找到相應的食譜，並進行頁面切換
            	portalToRecipeDetails(recipe);
                break;
            }
        }
    }
    
    public void showSearchTable() {
    	String searchIngredient = txtSearch.getText();
    	if (searchIngredient.isEmpty()) {
            showAlert("Warning", "Search field cannot be empty!");
            return;
        }
    	ArrayList<Recipe> filteredRecipes = searchRecipes(searchIngredient);
    	
    	if (filteredRecipes.isEmpty()) {
            showAlert("Info", "No recipes found for the given ingredient.");
            return;
        }
    	
    	filteredRecipes.sort((r1, r2) -> Integer.compare(r2.getFavorite(), r1.getFavorite()));
    	
    	recipeButtonsContainer.getChildren().clear();  

        for (Recipe recipe : filteredRecipes) {
            Button recipeButton = new Button(recipe.getName());
            recipeButton.setMinWidth(125);  
            //recipeButton.setMaxWidth(125);  
            recipeButton.setPrefWidth(125); 
            recipeButton.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2;");
            recipeButton.setOnAction(event -> portalToRecipeDetails(recipe));  
            recipeButtonsContainer.getChildren().add(recipeButton);  
        }
    }
    
    public ArrayList<Recipe> searchRecipes(String ingredient) {

        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : Configure.getInstance().getRecipes()) {
            for (String recipeIngredient : recipe.getIngredientArray()) {
                if (recipeIngredient.toLowerCase().contains(ingredient)) {
                    filteredRecipes.add(recipe);
                    break;
                }
            }
        }
        return filteredRecipes;
    } 
    
    private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
    
    // SceneSwitch function
	public static void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent newPage = FXMLLoader.load(MainController.class.getResource(fxmlFile));
            Scene newScene = new Scene(newPage);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MaintoAdmin(ActionEvent event) {
        switchScene(event, "Admin_home.fxml");
    }
    
    public void AdmintoMngAcc(ActionEvent event) {
        switchScene(event, "ManageAccount.fxml");
    }

    public void AdmintoMngRecipe(ActionEvent event) {
        switchScene(event, "ManageRecipe.fxml");
    }
    
    public void MaintoUser(ActionEvent event) {
        switchScene(event, "User_home.fxml");
    }

    public void UsertoCreateRecipe(ActionEvent event) {
        switchScene(event, "CreateRecipe.fxml");
    }
    
    public void UsertoSearch(ActionEvent event) {
    	showSearchTable();
    }
    
    public void MaintoUserSignUp(ActionEvent event) {
        switchScene(event, "SignUpPage.fxml");
    }
    
    public void Logout(ActionEvent event) {
    	switchScene(event, "LoginPage.fxml");
    }
    
    //6205 added
    public void UsertoQuery(ActionEvent event) {
    	switchScene(event, "/UI_Controller/ChatBot.fxml");
    }
    
}
