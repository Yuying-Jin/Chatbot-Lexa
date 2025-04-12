package application;

import Chatbot.CustomADT.ArrayPriorityQueue;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RecipeShowController {

    @FXML
    private Label dishName;
    @FXML
    private Label type;
    @FXML
    private Label ingredient;
    @FXML
    private Label direction;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblfav;
    @FXML
    private Button btnFavorite;
    @FXML
    private ImageView recipeImageView;

    private Recipe recipe;
    
    //
    private ArrayPriorityQueue pq;
    
    
    public void initialize() {
        // Retrieve the instance of Configure and the PriorityQueue
        Configure configure = Configure.getInstance();
        this.pq = configure.getPriorityQueue(); // Assuming you have a getter for PriorityQueue in Configure
        // Now you can use the PriorityQueue for sorting or displaying recipes
    }
    
    
    //
   

    // 用於顯示食譜的詳細資料
    public void showRecipeDetails(Recipe recipe) {
    	this.recipe = recipe;
        dishName.setText(recipe.getName());
        type.setText(recipe.getType());
        ingredient.setText(String.join(", ", recipe.getIngredient()));
        direction.setText(recipe.getDirection());
        lblfav.textProperty().bind(Bindings.format("Favorites: \t\t%d", recipe.favoriteProperty()));
        
        if (recipeImageView == null) {
        	System.out.println("recipeImageView is null");
        } else if (recipe.getImage() != null) {
        	System.out.println("Success " + recipe.getImage().getUrl());
            recipeImageView.setImage(new Image(recipe.getImage().getUrl()));
        } else {
        	System.out.println("The picture is empty");
        }
    }
    
    @FXML
    private void handleFavoriteClick(ActionEvent event) {
    	recipe.incrementFavorite();
    	btnFavorite.setDisable(true);
    	btnFavorite.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
    	
    	
    	//
    	Configure configure = Configure.getInstance();
        configure.updateRecipeFavorite(recipe);
//        System.out.println("Updated recipe: " + recipe.getName() + " - Favorites: " + recipe.getFavorite());
    	pq.displayQueue();  	
    	//
    }
    
    public void ShowrecipetoUser(ActionEvent event) {
        try {
        	MainController.switchScene(event, "User_home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
