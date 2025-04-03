package application;

import java.util.ArrayList;

public class User extends Person{
	
	public User(String name, String pwd) {
		super(name, pwd);
	}
	
	// enabling to implement user.addRecipe()
	public void addRecipe(String name, String type, ArrayList<String> ingredient, String direction, String imgpath) {
		ArrayList<Recipe> recipeArray = Configure.getInstance().getRecipes();
		Recipe myRecipe = new Recipe(name, type, ingredient, direction, imgpath);
		recipeArray.add(myRecipe);
		System.out.println("Recipe added: " + name);
	}
	
	// enabling to implement user.searchRecipe()
	public void searchRecipe(String ingredient) {
		ArrayList<Recipe> recipeArray = Configure.getInstance().getRecipes();
		boolean found = false;
	    for (Recipe recipe : recipeArray) {
	        if (recipe.getIngredient().contains(ingredient)) {
	            System.out.println("Found Recipe: " + recipe.getName());
	            found = true;
	        }
	    }
	    
	    if (!found) {
	        System.out.println("No recipes found containing the ingredient: " + ingredient);
	    }
	    
	}
}
