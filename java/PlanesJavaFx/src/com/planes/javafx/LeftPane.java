package com.planes.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

class LeftPane extends TabPane 
{
	
public void setRightPane(RightPane rp) {
	m_RightPane = rp;
}
	
public void doneClicked() {
	activateGameTab();
}

public void activateGameTab() {
	SingleSelectionModel<Tab> selectionModel = this.getSelectionModel();	
	selectionModel.select(m_GameTab);
    m_EditorTab.setDisable(true);
    m_GameTab.setDisable(false);
    m_StartRoundTab.setDisable(true);
}

public void activateEditorTab() {
	SingleSelectionModel<Tab> selectionModel = this.getSelectionModel();	
	selectionModel.select(m_EditorTab);	
    m_EditorTab.setDisable(false);
    m_GameTab.setDisable(true);
    m_StartRoundTab.setDisable(true);
}

public void activateStartGameTab() {
	SingleSelectionModel<Tab> selectionModel = this.getSelectionModel();	
	selectionModel.select(m_StartRoundTab);		
    m_EditorTab.setDisable(true);
    m_GameTab.setDisable(true);
    m_StartRoundTab.setDisable(false);
}

public void updateStats(int playerWins, int playerMoves, int playerHits, int playerMisses, int playerDead, 
		int computerWins, int computerMoves, int computerHits, int computerMisses, int computerDead) {
	m_GspPlayer.updateStats(playerMoves, playerHits, playerMisses, playerDead);
	m_GspComputer.updateStats(computerMoves, computerHits, computerMisses, computerDead);
	m_ScorePane.updateStats(playerWins, computerWins);
}

public void roundEnds() {
	activateStartGameTab();
}

public void startNewRound() {
	m_PlaneRound.initRound();
	activateEditorTab();
	
	//update the statistics 
	int playerWins = m_PlaneRound.playerGuess_StatNoPlayerWins();
	int playerMoves = m_PlaneRound.playerGuess_StatNoPlayerMoves();
	int playerHits = m_PlaneRound.playerGuess_StatNoPlayerHits();
	int playerMisses = m_PlaneRound.playerGuess_StatNoPlayerMisses();
	int playerDead = m_PlaneRound.playerGuess_StatNoPlayerDead();
	int computerWins = m_PlaneRound.playerGuess_StatNoComputerWins();
	int computerMoves = m_PlaneRound.playerGuess_StatNoComputerMoves();
	int computerHits = m_PlaneRound.playerGuess_StatNoComputerHits();
	int computerMisses = m_PlaneRound.playerGuess_StatNoComputerMisses();
	int computerDead = m_PlaneRound.playerGuess_StatNoComputerDead();
	updateStats(playerWins, playerMoves, playerHits, playerMisses, playerDead, 
			computerWins, computerMoves, computerHits, computerMisses, computerDead);
	
	m_RightPane.startNewRound();
}

public LeftPane(PlaneRoundJavaFx planeRound) {
	
	m_PlaneRound = planeRound;
	final GridPane gridPane = new GridPane();
	
	ColumnConstraints col1 = new ColumnConstraints();
	col1.setPercentWidth(33);
	ColumnConstraints col2 = new ColumnConstraints();
	col2.setPercentWidth(33);
	ColumnConstraints col3 = new ColumnConstraints();
	col3.setPercentWidth(33);
	gridPane.getColumnConstraints().addAll(col1, col2, col3);
	
	Button selectButton = new Button("Select");
	Button rotateButton = new Button("Rotate");			
	Button upButton = new Button("Up");
	Button leftButton = new Button("Left");			
	Button rightButton = new Button("Right");
	Button downButton = new Button("Down");			
	Button doneButton = new Button("Done");	
	
	selectButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override 
	    public void handle(ActionEvent e) {
	        m_RightPane.selectPlane();
	        m_RightPane.updateBoards();	        
	    }		
	});
	rotateButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override 
	    public void handle(ActionEvent e) {
	        m_RightPane.rotatePlane();
	        m_RightPane.updateBoards();	        
	    }		
	});		
	upButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override 
	    public void handle(ActionEvent e) {
	        m_RightPane.movePlaneUpwards();
	        m_RightPane.updateBoards();	        
	    }		
	});
	leftButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override 
	    public void handle(ActionEvent e) {
	        m_RightPane.movePlaneLeft();
	        m_RightPane.updateBoards();	        
	    }		
	});				
	rightButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override 
	    public void handle(ActionEvent e) {
	        m_RightPane.movePlaneRight();
	        m_RightPane.updateBoards();	        
	    }		
	});
	downButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override 
	    public void handle(ActionEvent e) {
	        m_RightPane.movePlaneDownwards();
	        m_RightPane.updateBoards();	        
	    }		
	});		
	doneButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override 
	    public void handle(ActionEvent e) {
	        m_RightPane.doneClicked();
	        m_RightPane.updateBoards();	
	        m_PlaneRound.doneClicked();
	        doneClicked();
	    }		
	});	
	
	
    // In order to see the GridPane extends with the LeftPane, remove it further
    gridPane.setGridLinesVisible(true);
    // Those 2 following lines enable the gridpane to stretch/shrink according the LeftPane
    gridPane.prefWidthProperty().bind(this.widthProperty());
    gridPane.prefHeightProperty().bind(this.heightProperty());

	gridPane.add(selectButton, 1, 0);
    gridPane.add(rotateButton, 1, 1);
    gridPane.add(upButton, 1, 2);
    gridPane.add(leftButton, 0, 3);
    gridPane.add(rightButton, 2, 3);
    gridPane.add(downButton, 1, 4);
    gridPane.add(doneButton, 1, 5);	
    
	GridPane.setHalignment(selectButton, HPos.CENTER);
    GridPane.setHalignment(rotateButton, HPos.CENTER);
    GridPane.setHalignment(upButton, HPos.CENTER);
    GridPane.setHalignment(leftButton, HPos.CENTER);
    GridPane.setHalignment(rightButton, HPos.CENTER);
    GridPane.setHalignment(downButton, HPos.CENTER);
    GridPane.setHalignment(doneButton, HPos.CENTER);	
    
    //gridPane.prefWidth(300);
    //this.getChildren().add(gridPane);
    
    m_EditorTab = new Tab();
    m_EditorTab.setText("Board Editing");
    m_EditorTab.setContent(gridPane);
    this.getTabs().add(m_EditorTab);

    m_GspPlayer = new GameStatsPane("Player");
    m_GspComputer = new GameStatsPane("Computer");
    
    VBox vbox1 = new VBox();
    vbox1.getChildren().add(m_GspPlayer);
    vbox1.getChildren().add(m_GspComputer);
    
	m_GameTab = new Tab();
	m_GameTab.setText("Game");
	m_GameTab.setContent(vbox1);
    this.getTabs().add(m_GameTab);
    
    m_ScorePane = new ScorePane(this);
    
    VBox vbox2 = new VBox();
    vbox2.getChildren().add(m_ScorePane);
    
	m_StartRoundTab = new Tab();
	m_StartRoundTab.setText("Start Round");
	m_StartRoundTab.setContent(vbox2);
    this.getTabs().add(m_StartRoundTab);    
	}

	RightPane m_RightPane;
	PlaneRoundJavaFx m_PlaneRound;
	
	GameStatsPane m_GspPlayer;
	GameStatsPane m_GspComputer;
	ScorePane m_ScorePane;
	
    Tab m_EditorTab;
    Tab m_GameTab;
    Tab m_StartRoundTab;	
}
