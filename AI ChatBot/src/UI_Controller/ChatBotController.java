package UI_Controller;


import Chatbot.CustomADT.ArrayQueue;
import application.Configure;
import application.MainController;
import application.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ChatBotController {

	
	private Configure config = Configure.getInstance(); // for getting current username
	
    @FXML
    private TextArea txtQuery; // User's query

    @FXML
    private VBox MessageVBox; // The container to store messages
    
    @FXML
    private VBox SessionVBox; // The container to store sessions
    
    @FXML
    private Button btnGo;

    private ArrayQueue pq;

    
    @FXML
    public void initialize() {
    	
    	
    	Configure configure = Configure.getInstance();
        this.pq = configure.getPriorityQueue();
    	
    	// disable the button until there's text in the TextArea
    	btnGo.setDisable(true);
    	txtQuery.textProperty().addListener((observable, oldValue, newValue) -> {
    	    btnGo.setDisable(newValue.trim().isEmpty()); // after delete blank, if there's content -> enable btnGo
    	});
    	
    	// welcome greeting
        Label greeting = new Label("Hi " + config.getCurrentUser() + ", how can I help you today?");
        greeting.setFont(new Font("Arial", 16));
        MessageVBox.getChildren().add(greeting);
    }
    
    @FXML
    public void handleQuery(ActionEvent event) {
        String userQuery = txtQuery.getText();

        if (userQuery != null && !userQuery.isEmpty()) {
            // show user's query to MessageVBox
            Label userMessageLabel = new Label(config.getCurrentUser() + ": " + userQuery);
            userMessageLabel.setFont(new Font("Arial", 16));
            userMessageLabel.setStyle("-fx-background-color: #FFEBCD; -fx-padding: 3px;");
            MessageVBox.getChildren().add(userMessageLabel);

            // Robot's response

            String botResponse = "Bot: xxxxx";
            

            // 將bot回應顯示在UI上
            Label botMessageLabel = new Label(botResponse);
            botMessageLabel.setFont(new Font("Arial", 16));
            botMessageLabel.setStyle("-fx-background-color: #87CEFA; -fx-padding: 3px;");
            MessageVBox.getChildren().add(botMessageLabel);

            // clear TextArea
            txtQuery.clear();
        }
    }

    // Method to create a new session button when 'New' is clicked
    @FXML
    public void createNewSession(ActionEvent event) {
        Button newSessionButton = new Button("Session " + (SessionVBox.getChildren().size() + 1));
        newSessionButton.setFont(new Font("Arial", 14));
        newSessionButton.setStyle(
                "-fx-background-color: #6495ED; " + // background color
                "-fx-border-color: #4169E1; " +    // border color (black)
                "-fx-border-width: 2px; " +        // border width
                "-fx-padding: 10px; " +             // padding inside the button
                "-fx-border-radius: 5px; " +       // rounded corners for the border
                "-fx-background-radius: 5px;"      // rounded corners for the background
            );

            // Set the width of the button to match the SessionVBox width
            newSessionButton.setPrefWidth(SessionVBox.getWidth());

        
        // Add the new session button to the SessionVBox
        SessionVBox.getChildren().add(newSessionButton);
    }
    
    @FXML
    public void getTop3() {
        Recipe[] top = pq.getTopThreeRecipes();
        System.out.println(top.length);
        String top1 = top[0].getName();
        String top2 = top[1].getName();
        String top3 = top[2].getName();
        String topRecipes = "Bot: The top 3 recipes are: " + top1 + ", " + top2 + ", " + top3;
        Label autoRecipe = new Label(topRecipes);
        autoRecipe.setFont(new Font("Arial", 16));
        autoRecipe.setStyle("-fx-background-color: #87CEFA; -fx-padding: 3px;");
        MessageVBox.getChildren().add(autoRecipe);
    }
    
    


    public void QuerytoUser(ActionEvent event) {
        try {
        	MainController.switchScene(event, "User_home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
