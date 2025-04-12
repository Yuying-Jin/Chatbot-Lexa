package UI_Controller;


import java.util.Optional;

import Chatbot.Chatbot;
import Chatbot.ChatbotChatIF;
import Chatbot.ChatHistory.ChatSession;
import Chatbot.ChatHistory.Message;
import Chatbot.CustomADT.ArrayPriorityQueue;
import application.Configure;
import application.MainController;
import application.Recipe;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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

    private ArrayPriorityQueue pq;
    
    private ChatbotChatIF chatbot;
    
    private final String greetingText = "Hi " + config.getCurrentUser() + ", how can I help you today?";

    
    @FXML
    public void initialize() {
    	
    	chatbot = Chatbot.getInstance();
    	Configure configure = Configure.getInstance();
        this.pq = configure.getPriorityQueue();
    	chatbot.getChatHistory().setCurrentSession(null); // Initialize the current session to null
        
    	// disable the button until there's text in the TextArea
    	btnGo.setDisable(true);
    	txtQuery.textProperty().addListener((observable, oldValue, newValue) -> {
    	    btnGo.setDisable(newValue.trim().isEmpty()); // after delete blank, if there's content -> enable btnGo
    	});
    	
    	// welcome greeting
        displayMessage(greetingText, false);
        
        // load the session buttons
		if (!chatbot.getChatHistory().isEmpty()) {
			loadSessions(); 
		}
        
    }
    
    
    @FXML
    public void handleQuery(ActionEvent event) {
        String userQuery = txtQuery.getText();
        
        ensureCurrentSessionExists(); // check if a session exists, if not, create one

        if (userQuery != null && !userQuery.isEmpty()) {
            
            displayMessage(userQuery, true); // show user's query to MessageVBox
            String LLMResponse = chatbot.generateResponse(userQuery); // get the response from Chatbot
            displayMessage(LLMResponse, false); // show robot's response to MessageVBox
            txtQuery.clear(); // clear TextArea
            
            // save the conversation to the current session
            ChatSession currentSession = chatbot.getChatHistory().getCurrentSession();
            currentSession.add(new Message(Message.SenderType.USER, userQuery));
            currentSession.add(new Message(Message.SenderType.BOT, LLMResponse));
        }
    }


    // Method to create a new session button when 'New' is clicked
    @FXML
    public void createNewSession(ActionEvent event) {
    	
    	// clear the MessageVBox and show the greeting message
    	MessageVBox.getChildren().clear();
    	displayMessage(greetingText, false);
    	
    	// Create a new session
    	ChatSession newSession = new ChatSession();
    	chatbot.getChatHistory().addChatSession(newSession); // Add the new session to the chat history
        chatbot.getChatHistory().setCurrentSession(newSession); // Set the current session to the new session
        chatbot.getChatHistory().getCurrentSession().add(new Message(Message.SenderType.BOT, greetingText));
        
    	// Create a new session button
        Button newSessionButton = new Button(newSession.getName());
        SessionVBox.getChildren().add(0, newSessionButton);
        newSessionButton.setFont(new Font("Arial", 14));
        // Set the button's style
        newSessionButton.setStyle(
        		"-fx-background-color: white; " +
                "-fx-border-color: #4169E1; " +
                "-fx-border-width: 2px; " +
                "-fx-padding: 10px; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 5px;");
        newSessionButton.setPrefWidth(SessionVBox.getWidth());
        newSessionButton.setUserData(newSession); // Store the session in the button's user data
        
        newSessionButton.setOnAction(e -> {
        	// 點擊時切換背景色
            newSessionButton.setStyle("-fx-background-color: white; -fx-border-color: #4169E1; -fx-border-width: 2px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            for (javafx.scene.Node node : SessionVBox.getChildren()) {
                if (node instanceof Button && node != newSessionButton) {
                    node.setStyle("-fx-background-color: #6495ED; -fx-border-color: #4169E1; -fx-border-width: 2px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                }
            }
            MessageVBox.getChildren().clear();
            
            // Show the chat history for the selected session
            ChatSession session = (ChatSession) newSessionButton.getUserData();
            chatbot.getChatHistory().setCurrentSession(session);
 			
 			// Display the messages in the selected session
 			for (Message message : session) {
 				// Display the message in the MessageVBox
 			    displayMessage(message.getContent(), message.getSender() == Message.SenderType.USER);
 			}

        });

        // double-click to edit name
        newSessionButton.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                TextInputDialog renameDialog = new TextInputDialog(newSessionButton.getText());
                renameDialog.setTitle("Rename Session");
                renameDialog.setHeaderText("Change the session name:");
                renameDialog.setContentText("New name:");

                Optional<String> newName = renameDialog.showAndWait();
                newName.ifPresent(name -> {
                    newSessionButton.setText(name);
                    ((ChatSession) newSessionButton.getUserData()).setName(name); // Update the session name
                });
            }
        });

        for (javafx.scene.Node node : SessionVBox.getChildren()) {
            if (node instanceof Button && node != newSessionButton) {
                node.setStyle("-fx-background-color: #6495ED; " +
                              "-fx-border-color: #4169E1; " +
                              "-fx-border-width: 2px; " +
                              "-fx-padding: 10px; " +
                              "-fx-border-radius: 5px; " +
                              "-fx-background-radius: 5px;");
            }
        }
    }

    
    @FXML
    public void getTop3() {
    	
    	ensureCurrentSessionExists();
    	
        Recipe[] top = pq.getTopThreeRecipes();
        
        // Check if the queue has enough recipes
        if (top == null || top.length < 3) {
            displayMessage("Sorry, not enough recipes in the queue.", false);
            return;
        }
        
        System.out.println(top.length);
        String top1 = top[0].getName();
        String top2 = top[1].getName();
        String top3 = top[2].getName();
        String topRecipes = "The top 3 recipes are: " + top1 + ", " + top2 + ", " + top3;
        displayMessage(topRecipes, false); // show the top 3 recipes to MessageVBox
        
        chatbot.getChatHistory().getCurrentSession().add(new Message(Message.SenderType.BOT, topRecipes));
    }
    
    
    public void QuerytoUser(ActionEvent event) {
        try {
        	MainController.switchScene(event, "User_home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void displayMessage(String content, boolean isUser) {
    	
    	String contentFormated = isUser 
    			? config.getCurrentUser() + ": " + content
    			: "Bot: " + content;
					
        Text messageText = new Text(contentFormated);
        messageText.setWrappingWidth(MessageVBox.getWidth() - 10);
        messageText.setFont(new Font("Arial", 16));
        StackPane messageContainer = new StackPane(messageText);
        StackPane.setAlignment(messageText, Pos.CENTER_LEFT);

        String style = isUser 
            ? "-fx-background-color: #FFEBCD; -fx-padding: 2px;" 
            : "-fx-background-color: #87CEFA; -fx-padding: 2px;";
        messageContainer.setStyle(style);
        MessageVBox.getChildren().add(messageContainer);
    }
    
    private void ensureCurrentSessionExists() {
        if (chatbot.getChatHistory().getCurrentSession() == null) {
            createNewSession(null);
        }
    }
    
    private void loadSessions() {
    	for (ChatSession s : chatbot.getChatHistory().getAllChatSessions()) {
    		// Create a new session button
            Button sessionButton = new Button(s.getName());
           
            sessionButton.setFont(new Font("Arial", 14));
            sessionButton.setStyle(
                "-fx-background-color: #6495ED; " +
                "-fx-border-color: #4169E1; " +
                "-fx-border-width: 2px; " +
                "-fx-padding: 10px; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 5px;"
            );
            // Store the session in the button's user data
            sessionButton.setUserData(s); 
            
            Platform.runLater(() -> {
                // Set width after the layout has been computed
                sessionButton.setPrefWidth(SessionVBox.getWidth());
            });
            
            SessionVBox.getChildren().add(0, sessionButton); // Add the button to the top of the VBox
            
            
            sessionButton.setOnAction(e -> {
            	// 點擊時切換背景色
            	sessionButton.setStyle("-fx-background-color: white; -fx-border-color: #4169E1; -fx-border-width: 2px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                for (javafx.scene.Node node : SessionVBox.getChildren()) {
                    if (node instanceof Button && node != sessionButton) {
                        node.setStyle("-fx-background-color: #6495ED; -fx-border-color: #4169E1; -fx-border-width: 2px; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                    }
                }
                MessageVBox.getChildren().clear();
                
                // Show the chat history for the selected session
                ChatSession session = (ChatSession) sessionButton.getUserData();
                chatbot.getChatHistory().setCurrentSession(session);
     			
     			// Display the messages in the selected session
     			for (Message message : session) {
     				// Display the message in the MessageVBox
     			    displayMessage(message.getContent(), message.getSender() == Message.SenderType.USER);
     			}

            });

            // 加入「雙擊按鈕名稱來修改」的功能
            sessionButton.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2) {
                    TextInputDialog renameDialog = new TextInputDialog(sessionButton.getText());
                    renameDialog.setTitle("Rename Session");
                    renameDialog.setHeaderText("Change the session name:");
                    renameDialog.setContentText("New name:");

                    Optional<String> newName = renameDialog.showAndWait();
                    newName.ifPresent(name -> {
                    	sessionButton.setText(name);
                        ((ChatSession) sessionButton.getUserData()).setName(name); // Update the session name
                    });
                }
            });
    	}
    }
}
