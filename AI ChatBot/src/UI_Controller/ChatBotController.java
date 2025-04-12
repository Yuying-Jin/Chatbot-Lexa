package UI_Controller;


import java.util.Optional;

import Chatbot.Chatbot;
import Chatbot.ChatbotChatIF;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
    
    private ChatbotChatIF chatbot;

    
    @FXML
    public void initialize() {
    	
    	chatbot = Chatbot.getInstance();
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
            Text userMessageText = new Text(config.getCurrentUser() + ": " + userQuery);
            userMessageText.setWrappingWidth(MessageVBox.getWidth() - 10);
            userMessageText.setFont(new Font("Arial", 16));
            StackPane userMessageContainer = new StackPane(userMessageText);
            userMessageContainer.setStyle("-fx-background-color: #FFEBCD; -fx-padding: 2px;");
            MessageVBox.getChildren().add(userMessageContainer);

            // Robot's response
            String botResponse = "Bot: " + chatbot.generateResponse(userQuery);
            Text botMessageText = new Text(botResponse);
            botMessageText.setWrappingWidth(MessageVBox.getWidth() - 10);
            botMessageText.setFont(new Font("Arial", 16));
            StackPane botMessageContainer = new StackPane(botMessageText);
            botMessageContainer.setStyle("-fx-background-color: #87CEFA; -fx-padding: 2px;");
            MessageVBox.getChildren().add(botMessageContainer);

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
            "-fx-background-color: #6495ED; " +
            "-fx-border-color: #4169E1; " +
            "-fx-border-width: 2px; " +
            "-fx-padding: 10px; " +
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px;"
        );

        newSessionButton.setPrefWidth(SessionVBox.getWidth());

        // 點擊時切換背景色
        newSessionButton.setOnAction(e -> {
            newSessionButton.setStyle("-fx-background-color: white; -fx-border-color: #4169E1; -fx-border-width: 2px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            for (javafx.scene.Node node : SessionVBox.getChildren()) {
                if (node instanceof Button && node != newSessionButton) {
                    node.setStyle("-fx-background-color: #6495ED; -fx-border-color: #4169E1; -fx-border-width: 2px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                }
            }
        });

        // 加入「雙擊按鈕名稱來修改」的功能
        newSessionButton.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                TextInputDialog renameDialog = new TextInputDialog(newSessionButton.getText());
                renameDialog.setTitle("Rename Session");
                renameDialog.setHeaderText("Change the session name:");
                renameDialog.setContentText("New name:");

                Optional<String> newName = renameDialog.showAndWait();
                newName.ifPresent(name -> newSessionButton.setText(name));
            }
        });

        SessionVBox.getChildren().add(0, newSessionButton);
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
