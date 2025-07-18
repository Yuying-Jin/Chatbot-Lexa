package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Chatbot.Chatbot;
import Chatbot.CustomADT.ArrayPriorityQueue;
import Chatbot.KnowledgeBase.KnowledgeBaseIF;

public class Configure {
	
	private static Configure instance = null;

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private ArrayList<String> i0 = new ArrayList<>();
    private ArrayList<String> i1 = new ArrayList<>();
    private ArrayList<String> i2 = new ArrayList<>();
    private ArrayList<String> i3 = new ArrayList<>();
    private ArrayList<String> i4 = new ArrayList<>();
    private ArrayList<String> i5 = new ArrayList<>();
    private ArrayList<String> i6 = new ArrayList<>();
    private ArrayList<String> i7 = new ArrayList<>();
    private ArrayList<String> i8 = new ArrayList<>();
    private ArrayList<String> i9 = new ArrayList<>();
    private ArrayList<String> i10 = new ArrayList<>();
    private ArrayList<String> i11 = new ArrayList<>();
    private ArrayList<String> i12 = new ArrayList<>();
    private ArrayList<String> i13 = new ArrayList<>();
    private ArrayList<String> i14 = new ArrayList<>();
    private ArrayList<String> i15 = new ArrayList<>();
    private ArrayList<String> i16 = new ArrayList<>();
    private ArrayList<String> i17 = new ArrayList<>();
    private ArrayList<String> i18 = new ArrayList<>();
    private ArrayList<String> i19 = new ArrayList<>();
    private ArrayList<String> i20 = new ArrayList<>();
    private ArrayList<String> i21 = new ArrayList<>();
    
    private Set<User> users = new HashSet<>();
    private Set<Admin> admins = new HashSet<>();

    // 6205
    private ArrayPriorityQueue pq;
    private String currentUser;
    //
    
	public Configure() {
		pq = new ArrayPriorityQueue(10);
		initializeData();

	}
	
	public static Configure getInstance() {
        if (instance == null) {
            instance = new Configure();
        }
        return instance;
    }
	
	private void initializeData() {
		i0.add("farfalle pasta");
		i0.add("broccoli");
		i0.add("butter");
		i0.add("Parmesan cheese");
		i0.add("garlic");
		i0.add("black pepper");
	 
        i1.add("olive oil");
        i1.add("bacon");
        i1.add("pineapple rings");
        i1.add("pizza dough");
        i1.add("pizza sauce");
        i1.add("mozzarella cheese");
        i1.add("red onion");
        i2.add("potatoes");
        i2.add("salt");
        i2.add("butter");
        i2.add("cream cheese");
        i2.add("sour cream");
        i2.add("whole milk");
        i2.add("black pepper");
        i3.add("olive oil");
        i3.add("sausage");
        i3.add("onion");
        i3.add("carrots");
        i3.add("potatoes");
        i3.add("salt");
        i3.add("black pepper");
        i3.add("sour cream");
        i4.add("boiling water");
        i4.add("black tea bags");
        i4.add("white sugar");
        i4.add("limes");
        i5.add("tea bags");
        i5.add("sugar");
        i6.add("peppers");
        i6.add("tomatoes");
        i6.add("onion");
        i6.add("white sugar");
        i6.add("salt");
        i6.add("vegetable oil");
        i6.add("water");
        
        i11.add("olive oil");
        i11.add("red bell peppers");
        i11.add("dried red chili pepper");
        i11.add("tomato paste");
        i11.add("white wine");
        i11.add("garlic cloves");
        i11.add("Valencia rice");
        i11.add("jumbo shrimp");
        i11.add("mussels");
        i11.add("clams");
        
        i12.add("dark chocolate");
        i12.add("unsalted butter");
        i12.add("granulated sugar");
        i12.add("eggs");
        i12.add("all-purpose flour");
        i12.add("vanilla extract");
        i12.add("pinch of salt");
        i12.add("powdered sugar");
        i13.add("ladyfingers");
        i13.add("mascarpone cheese");
        i13.add("heavy cream");
        i13.add("granulated sugar");
        i13.add("espresso");
        i13.add("coffee liqueur");
        i13.add("cocoa powder");
        i14.add("pie crust");
        i14.add("lemons");
        i14.add("granulated sugar");
        i14.add("cornstarch");
        i14.add("water");
        i14.add("egg yolks");
        i14.add("egg whites");
        i14.add("cream of tartar");
        i14.add("vanilla extract");
        i15.add("cream cheese");
        i15.add("granulated sugar");
        i15.add("vanilla extract");
        i15.add("eggs");
        i15.add("heavy cream");
        i15.add("graham cracker crumbs");
        i15.add("unsalted butter");
        i15.add("sour cream");
        i16.add("apples");
        i16.add("granulated sugar");
        i16.add("brown sugar");
        i16.add("butter");
        i16.add("oats");
        i16.add("flour");
        i16.add("cinnamon");
        i16.add("nutmeg");
        i16.add("salt");
        i17.add("heavy cream");
        i17.add("whole milk");
        i17.add("granulated sugar");
        i17.add("vanilla bean");
        i17.add("gelatin");
        i17.add("water");
        i18.add("strawberries");
        i18.add("granulated sugar");
        i18.add("heavy cream");
        i18.add("vanilla extract");
        i18.add("flour");
        i18.add("baking powder");
        i18.add("unsalted butter");
        i18.add("milk");
        i19.add("phyllo dough");
        i19.add("walnuts");
        i19.add("pistachios");
        i19.add("honey");
        i19.add("butter");
        i19.add("sugar");
        i19.add("cinnamon");
        i19.add("lemon juice");
        i20.add("heavy cream");
        i20.add("vanilla bean");
        i20.add("granulated sugar");
        i20.add("egg yolks");
        i20.add("brown sugar");
        i21.add("water");
        i21.add("unsalted butter");
        i21.add("granulated sugar");
        i21.add("flour");
        i21.add("eggs");
        i21.add("cinnamon");
        i21.add("vegetable oil");

        
		Recipe recipe1 = new Recipe("Broccoli Alfredo Pasta", "Main dish", i0, 
		"Cook farfalle pasta and broccoli, prepare a creamy cheese sauce, then combine everything and garnish with parsley for a delicious dish.", 
		"image/BroccoliAlfredoPasta.jpg");
		recipes.add(recipe1);
		pq.add(recipe1);
		
		Recipe recipe2 = new Recipe("Hawaiian Pizza", "Main dish", i1, 
		"Prepare and assemble a pizza with bacon, caramelized pineapple, and toppings on rolled dough, then bake at 500°F until golden and bubbly.", 
		"image/HawaiianPizza.jpg");
		recipes.add(recipe2);
		pq.add(recipe2);
		
		Recipe recipe3 = new Recipe("Holiday Potatoes", "Appetizer", i2, 
		"Boil potatoes until tender, then mash with butter, cream cheese, sour cream, and milk until fluffy, garnish with parsley and serve.", 
		"image/HolidayPotatoes.jpg");
		recipes.add(recipe3);
		pq.add(recipe3);
		
		Recipe recipe4 = new Recipe("Polish Potato Soup", "Soup", i3, 
		"Cook kielbasa with vegetables, simmer potatoes with broth, pickles, and dill, mash some potatoes, add reserved slices, and finish with sour cream and dill.", 
		"image/PolishPotatoSoup.jpg");
		recipes.add(recipe4);
		pq.add(recipe4);
		
		Recipe recipe5 = new Recipe("Sweet Lime Iced Tea", "Drinks", i4, 
		"Steep tea bags in boiling water, mix in sugar and lime juice, cool, and refrigerate until cold.", 
		"image/SweetLimeIcedTea.jpg");
		recipes.add(recipe5);
		pq.add(recipe5);
		
		Recipe recipe6 = new Recipe("South Carolina Sweet Tea", "Drinks", i5, 
		"Brew tea in a coffee maker, mix with sugar in a pitcher, fill with tea, cool to room temperature, and refrigerate.", 
		"image/SouthCarolinaSweetTea.jpg");
		recipes.add(recipe6);
		pq.add(recipe6);
		
		Recipe recipe7 = new Recipe("Eggplant Appetizer", "Appetizer", i6, 
		"Cook eggplant, bell pepper, tomato, and onion with a sugar, salt, oil, vinegar, and water mixture for 30 minutes, then serve warm.", 
		"image/EggplantAppetizer.jpg");
		recipes.add(recipe7);
		pq.add(recipe7);
		
		Recipe recipe8 = new Recipe("Paella", "Main dish", i11, 
		"Sauté onion, bell pepper, chorizo, and chili in a paella pan, then cook rice with tomatoes, spices, stock, and seafood, simmering until tender before serving.", 
		"image/paella.jpg");
		recipes.add(recipe8);
		pq.add(recipe8);
		
		Recipe recipe9 = new Recipe("Chocolate Lava Cake", "Dessert", i12, 
		"Melt butter and dark chocolate together, whisk in sugar, eggs, and vanilla, then fold in flour and salt. Pour mixture into greased ramekins and bake at 425°F (220°C) for 12-14 minutes until the edges are set but the center is still soft. Dust with powdered sugar and serve warm.", 
		"image/chocolate_lava_cake.png");
		recipes.add(recipe9);
		pq.add(recipe9);
		
		Recipe recipe10 = new Recipe("Tiramisu", "Dessert", i13, 
		"Whisk mascarpone with sugar and whipped cream. Dip ladyfingers into espresso and coffee liqueur, then layer with mascarpone mixture. Repeat layers and dust with cocoa powder. Refrigerate for at least 4 hours before serving.", 
		"image/tiramisu.png");
		recipes.add(recipe10);
		pq.add(recipe10);
		
		Recipe recipe11 = new Recipe("Lemon Meringue Pie", "Dessert", i14, 
		"Make lemon filling by combining lemon juice, sugar, cornstarch, and water, then cook until thickened. Whisk in egg yolks, pour into pie crust. Beat egg whites with cream of tartar and sugar to stiff peaks, spread over pie, and bake at 350°F (175°C) until meringue is golden.", 
		"image/lemon_meringue_pie.png");
		recipes.add(recipe11);
		pq.add(recipe11);
		
		Recipe recipe12 = new Recipe("Cheesecake", "Dessert", i15, 
		"Mix cream cheese, sugar, and vanilla until smooth. Add eggs and heavy cream, then pour into graham cracker crust. Bake at 325°F (160°C) for 45-50 minutes, then top with sour cream and bake for another 10 minutes. Chill before serving.", 
		"image/cheesecake.png");
		recipes.add(recipe12);
		pq.add(recipe12);
		
		Recipe recipe13 = new Recipe("Apple Crumble", "Dessert", i16, 
		"Toss sliced apples with sugar, cinnamon, and nutmeg, then place in a baking dish. Mix oats, flour, and butter until crumbly, then sprinkle over apples. Bake at 350°F (175°C) for 35-40 minutes until golden and bubbly.", 
		"image/apple_crumble.png");
		recipes.add(recipe13);
		pq.add(recipe13);
		
		Recipe recipe14 = new Recipe("Panna Cotta", "Dessert", i17, 
		"Heat cream, milk, and sugar until sugar dissolves, then add scraped vanilla bean. Dissolve gelatin in water and mix into the cream mixture. Pour into molds and refrigerate for 4 hours or overnight. Serve with fresh berries or fruit sauce.", 
		"image/panna_cotta.png");
		recipes.add(recipe14);
		pq.add(recipe14);
		
		Recipe recipe15 = new Recipe("Strawberry Shortcake", "Dessert", i18, 
		"Toss strawberries with sugar and let sit. Mix flour, baking powder, and butter to form a dough, then add milk. Bake biscuits at 400°F (200°C) for 12-15 minutes. Serve with whipped cream and macerated strawberries.", 
		"image/strawberry_shortcake.png");
		recipes.add(recipe15);
		pq.add(recipe15);
		
		Recipe recipe16 = new Recipe("Baklava", "Dessert", i19, 
		"Layer phyllo dough with chopped nuts and cinnamon, then brush with melted butter. Bake at 350°F (175°C) for 30-40 minutes until golden. Pour hot syrup made of honey, sugar, lemon juice over the baklava and let cool.", 
		"image/baklava.png");
		recipe16.setFavorite(3);
		recipes.add(recipe16);
		pq.add(recipe16);
		
		Recipe recipe17 = new Recipe("Creme Brûlée", "Dessert", i20, 
		"Heat cream and vanilla to a simmer. Whisk together egg yolks and sugar, then temper with hot cream. Pour mixture into ramekins and bake at 325°F (160°C) for 45 minutes. Chill, then caramelize the top with brown sugar using a torch before serving.", 
		"image/creme_brulee.png");
		recipes.add(recipe17);
		pq.add(recipe17);
		
		Recipe recipe18 = new Recipe("Churros", "Dessert", i21, 
		"Boil water, butter, and sugar, then add flour and stir until dough forms. Let cool, then beat in eggs. Pipe dough into hot oil and fry until golden. Toss in cinnamon sugar and serve with chocolate dipping sauce.", 
		"image/churros.png");
		recipes.add(recipe18);
		pq.add(recipe18);

	    pq.displayQueue();
	    pq.getTopThreeRecipes();
        
	    
        users.add(new User("Charlotte", "123"));
        users.add(new User("Joy", "123"));
        users.add(new User("Yuying", "123"));

        admins.add(new Admin("Admin", "123"));
        
    	// 6205 added - Used by ChatBot	
        // add entries to the knowledge base
        Chatbot<Recipe> chatbot = Chatbot.getInstance();
	    for (application.Recipe recipe : getRecipes()) {
	        boolean added = chatbot.getKnowledgeBase().addEntry(recipe);
	        System.out.println((added ? "Added" : "Failed to add") + " entry: " + recipe.getName());
	    }
		// 6205 added - Used by ChatBot	
    }

	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	
	public void addUser(User user) {
        users.add(user);
    }
	
	public Set<Admin> CreateAdmin() {
		Set<Admin> admins = new HashSet<>();
		
		Admin a1 = new Admin("Admin", "123");
		admins.add(a1);
		
		return admins;
	}
	
	// check if the username & pwd corresponds to user
	public boolean isUser(String username, String password) {
        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
            	setCurrentUser(username);
                return true;
            }
        }
        return false;
    }
	
	// check if the username & pwd corresponds to admin
	public boolean isAdmin(String username, String password) {
        for (Admin admin : admins) {
            if (admin.getName().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
	// check if the username is already registered
	public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }
	
	
	// 6205 added - Used by ChatBot	
	public void setCurrentUser(String username) {
        this.currentUser = username;
    }
	
    public String getCurrentUser() {
        return currentUser;
    }
	    
    // Transfer the priorityQueue to other class
    public ArrayPriorityQueue getPriorityQueue() {
        return pq;
    }
    
    public void updateRecipeFavorite(Recipe recipe) {
    	  pq.updateFavorite(recipe); // update the priorityQueue

    }
    
	public void addKnowledgeToKnowledgeBase(Recipe recipe) {
		// Add the recipe to the knowledge base
		Chatbot<Recipe> chatbot = Chatbot.getInstance();
		KnowledgeBaseIF<Recipe> knowledgeBase = chatbot.getKnowledgeBase();
		knowledgeBase.addEntry(recipe);
	}
	// 6205 added end
}
