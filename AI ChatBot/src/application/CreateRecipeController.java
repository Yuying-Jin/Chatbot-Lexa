package application;

import java.io.File;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateRecipeController {
	
 	@FXML
  	private ImageView lblImage;
  	@FXML
    private TextField txtName;  
    @FXML
    private TextField txtType; 
    @FXML
    private TextArea txtIngredient;
    @FXML
    private TextArea txtDirection;
    @FXML
    private File selectedImageFile; 
    @FXML
    private Configure configure = Configure.getInstance();  // get Configure object
    
    
    // In recipeShow, it allows to add image to the recipe
    public void AddImageToRecipe(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
        
    	FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
    	fileChooser.getExtensionFilters().add(filter);

    	File selectedFile = fileChooser.showOpenDialog(new Stage());

    	if (selectedFile != null) {
    		Image image = new Image(selectedFile.toURI().toString());
    		lblImage.setImage(image);
    		this.selectedImageFile = selectedFile;
    	}
    }
  
    // In RecipeShow, it enables removeImage attached when creating a recipe
    public void RemoveImage(ActionEvent event) {
    	lblImage.setImage(null);
    	selectedImageFile = null;
    }
  
    // Ensure every content of the recipe is input
    public void handleSaveRecipe(ActionEvent event) {
    	String dishName = txtName.getText();
        String type = txtType.getText();
        String ingredientsText = txtIngredient.getText();
        String directionsText = txtDirection.getText();

        if (dishName.isEmpty() || type.isEmpty() || ingredientsText.isEmpty() || directionsText.isEmpty()) {
        	showAlert("Error", "Please fill in all fields!");
        	return;
        }
        
        ArrayList<String> ingredients = new ArrayList<>();
        for (String ingredient : ingredientsText.split(",")) {
            ingredients.add(ingredient.trim());
        }
        
        String imagePath = selectedImageFile != null ? selectedImageFile.toURI().toString() : null;
        Recipe newRecipe = new Recipe(dishName, type, ingredients, directionsText, imagePath);
        
        // After created recipe, the TextFields in CreateRecipe will be removed
        configure.getRecipes().add(newRecipe);
        txtName.clear();
        txtType.clear();
        txtIngredient.clear();
        txtDirection.clear();
        lblImage.setImage(null);
        selectedImageFile = null;
        
        showAlert("Success", "Recipe added successfully!");
    }
    
    private void showAlert(String title, String message) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
    
    public void CreateRecipetoUser(ActionEvent event) {
        try {
        	MainController.switchScene(event, "User_home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
