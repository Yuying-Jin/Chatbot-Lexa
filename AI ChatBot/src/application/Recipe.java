package application;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class Recipe {
	
	ArrayList<Recipe> allrecipe = new ArrayList<>();
	ArrayList<String> ingredientArray;
	private SimpleStringProperty name;
    private SimpleStringProperty type;
    private SimpleStringProperty ingredient;
	private SimpleStringProperty direction;
    private SimpleIntegerProperty favorite;
    private transient Image image;
    private String imagePath;
	
	public Recipe(String name, String type, ArrayList<String> ingredients, String directions, String imgpath) {
		this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.ingredient = new SimpleStringProperty(String.join(", ", ingredients));
        this.direction = new SimpleStringProperty(directions);
        this.favorite = new SimpleIntegerProperty(0);
        this.ingredientArray = ingredients;
        this.imagePath = imgpath;
	}

	public ArrayList<Recipe> getAllrecipe() {
		return allrecipe;
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
        this.name.set(name);
    }

	public String getType() {
		return type.get();
	}
	
	public void setType(String type) {
        this.type.set(type);
    }

	public String getIngredient() {
		return ingredient.get();
	}
	
	public ArrayList<String> getIngredientArray() {
		System.out.println("Ingredients for recipe " + name + ": " + ingredientArray);
		return ingredientArray;
	}
	
	public void setIngredient(String ingredient) {
        this.ingredient.set(ingredient);
    }
	
    public String getDirection() {
		return direction.get();
	}

	public void setDirection(SimpleStringProperty direction) {
		this.direction = direction;
	}

	
    public IntegerProperty favoriteProperty() {
        return favorite;
    }

	public int getFavorite() {
		return favorite.get();
	}

    public void setFavorite(int favorite) {
        this.favorite.set(favorite);
    }

    public void incrementFavorite() {
        this.favorite.set(this.favorite.get() + 1);
    }
    
    public void setImage(Image image) {
    	this.image = image;
        if (image != null) {
            this.imagePath = image.getUrl();
        }
    }

    public Image getImage() {
    	if (image == null && imagePath != null) {
            image = new Image(imagePath);
        }
        return image;
    }

}
