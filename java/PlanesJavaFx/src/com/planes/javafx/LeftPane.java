package com.planes.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

class LeftPane extends TabPane 
{
	
public void setRightPane(RightPane rp) {
	m_RightPane = rp;
}
	
public LeftPane() {
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
	
	m_ClickedHandler = new EventHandler<ActionEvent>() {
	    @Override 
	    public void handle(ActionEvent e) {
	        m_RightPane.updateBoards();
	    }		
	};
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
    
    Tab tab1 = new Tab();
    tab1.setText("Board Editing");
    tab1.setContent(gridPane);
    this.getTabs().add(tab1);

	Tab tab2 = new Tab();
	tab2.setText("Game");
    this.getTabs().add(tab2);
    
	Tab tab3 = new Tab();
	tab3.setText("Start Round");
    this.getTabs().add(tab3);    
	}

	RightPane m_RightPane;
	EventHandler<ActionEvent> m_ClickedHandler;
}
