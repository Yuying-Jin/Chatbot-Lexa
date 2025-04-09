package Chatbot.KnowledgeBase;

import java.util.ArrayList;

public class Recipe {

    ArrayList<Recipe> allrecipe = new ArrayList<>();
    ArrayList<String> ingredientArray;
    private String name;
    private String type;
    private String ingredient;
    private String direction;
    private int favorite;
    private String imagePath;

    public Recipe(String name, String type, ArrayList<String> ingredients, String directions, String imgpath) {
        this.name = name;
        this.type = type;
        this.ingredient = String.join(", ", ingredients);
        this.direction = directions;
        this.favorite = 0;
        this.ingredientArray = ingredients;
        this.imagePath = imgpath;
    }

    public ArrayList<Recipe> getAllrecipe() {
        return allrecipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIngredient() {
        return ingredient;
    }

    public ArrayList<String> getIngredientArray() {
        System.out.println("Ingredients for recipe " + name + ": " + ingredientArray);
        return ingredientArray;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public void incrementFavorite() {
        this.favorite++;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
