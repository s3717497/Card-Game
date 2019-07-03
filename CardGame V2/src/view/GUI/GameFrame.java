package view.GUI;

import java.awt.*;

import javax.swing.JFrame;

import view.ViewModel;



public class GameFrame extends JFrame {

	private ViewModel model;
	// used as a factor for resizing components
	public final int INITIAL_HEIGHT;
	
	public GameFrame(ViewModel model)						
	{
		super("Card Game - BlackJack Simplified");
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//get screen size
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
	    Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
	    int screenWidth  = (int) rect.getMaxX();
	    int screenHeight = (int) (rect.getMaxY());
	    INITIAL_HEIGHT = screenHeight/2;
		setMinimumSize(new Dimension(screenWidth/2, screenHeight/2));
	}
	
	
	// since all components need a reference to the model
	// only add them AFTER the model is instantiated
	public void populate()
	{
		add(model.getGameSummary());
		add(model.getGameTool());
		add(model.getStatus());
		add(model.getCardPanel());
		setJMenuBar(model.getGameBar());
//		//avoid a constructor function
//		//so that addDialog can be set in a setter method
//		// + dialog remembers previous input
		model.getAddPlayerDialog().display();
		pack();
		setVisible(true);
	}
	
	
	
	
	
	
	public int getResizeConstant()
	{
		return getHeight()/INITIAL_HEIGHT;
	}
	
	// remote add method to set default positions
	public void add(GameSummary panel)
	{
		add(panel, BorderLayout.WEST);
	}
	public void add(GameTool bar)
	{
		add(bar, BorderLayout.NORTH);
	}
	public void add(GameStatus panel)
	{
		add(panel, BorderLayout.SOUTH);
	}
}
