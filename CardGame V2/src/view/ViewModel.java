package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.SwingUtilities;

import controller.PlayerFunction;
import controller.dialogs.*;
import model.GameEngineImpl;
import model.Holder;
import model.Player;
import model.interfaces.*;
import view.GUI.CardPanel;
import view.GUI.GameBar;
import view.GUI.GameFrame;
import view.GUI.GameStatus;
import view.GUI.GameSummary;
import view.GUI.GameTool;
import view.interfaces.ViewCallback;






// stores state of all panels
public class ViewModel {

	private GameBar bar;
	private GameSummary summary;
	private GameTool tool;
	private GameStatus status;
	private CardPanel cardPanel;
	
	private AddPlayerDialog addDialog;
	private BetPlayerDialog betDialog;								
	private RemovePlayerDialog removeDialog;						
																	
																	
																	
	private Player currentPlayer;
	private Holder currentHolder;
	private GameEngine engine;
	private GameFrame frame;
	public static final int DELAY = 2000;
	public final Color BACKGROUND_COLOR = new Color(40,40,40);
	private Collection<ViewCallback> viewCallbacks;
	// because only the view remembers the last card, not the model
	private Map<Holder, List<PlayingCard>> lastDealtCard;
	
	//ViewCallback must be an Impl since
	//only class handling all other viewCallbacks
	public ViewModel(GameEngine engine)						
	{
		this.engine = engine;
		// to avoid ConcurrentModificationException
		// a requirement to reinstantiate an updatee while running a loop
		viewCallbacks = new CopyOnWriteArrayList<>();
		lastDealtCard = new HashMap<>();
		
		// so components have a reference
		// if instantiated later, components can't refer to eg: frame height
		frame = new GameFrame(this);
		
		// facilitate one time instantiation
		// store previous input
		addDialog = new AddPlayerDialog(this);
		betDialog = new BetPlayerDialog(this);
		removeDialog = new RemovePlayerDialog(this);											
		
		//"no player/all players" store state
		bar = new GameBar(this);
		summary = new GameSummary(this);
		tool = new GameTool(this);
		status = new GameStatus(this);
		cardPanel = new CardPanel(this);
		
		frame.populate();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void addViewCallback(ViewCallback a) 
	{
		viewCallbacks.add(a);
	}
	
	//presets individual state for all panels
	// from view callback only
	public void addPlayer(Player plyr) 
	{
		engine.addPlayer(plyr);
		setCurrentPlayer(plyr);		
		updateViewCallbacks(PlayerFunction.ADD);				
	}
	
	// you cannot place a bet on a player other than current player
	public void placeBet(int bet) 
	{
		engine.placeBet(currentPlayer, bet);
		updateViewCallbacks(PlayerFunction.BET);
	}
	
	public void removePlayer(Player plyr)
	{
		engine.removePlayer(plyr);
		List<Player> players = new ArrayList<>(getGameEngine().getAllPlayers());
		setCurrentPlayer(players.isEmpty() ? null : players.get(players.size()-1));
		updateViewCallbacks(PlayerFunction.REMOVE);
	}
	
	public void switchPlayer(Player plyr)
	{
		setCurrentPlayer(plyr);
		updateViewCallbacks(PlayerFunction.SWITCH);
	}
	
	public void switchHouse(Holder holder)
	{
		currentHolder = holder;
		for (ViewCallback u : viewCallbacks)
			u.switchHouse(); 	 
	}
	
	// simple notification function
	// notify panels that a deal has begun
	public void deal() 
	{
		new Thread() 
		{
			public void run()
			{
				engine.deal(currentHolder, DELAY);		
			}
		}.start();
		updateViewCallbacks(PlayerFunction.DEAL);
	}
	
	private void updateViewCallbacks(PlayerFunction function)
	{
		for (ViewCallback u : viewCallbacks)
			switch (function)
			{
				case ADD :		u.addPlayer(); 		break;
				case SWITCH : 	u.switchPlayer(); 	break;
				case BET : 		u.placeBet(); 		break;
				case REMOVE :	u.removePlayer(); 	break;
				case DEAL :		u.deal();			break; 
			}
		frame.revalidate();
		frame.repaint();
	}
	
	
	
	
	
	
	
	
	public boolean readyToDealHouse()
	{
		int resultCount = 0;
		for (Player plyr : engine.getAllPlayers())
			if (plyr.getResult() > 0)
				resultCount++;
		return resultCount == engine.getAllPlayers().size();
	}
	
	
	
	
	
	public GameFrame getFrame()
	{
		return frame;
	}
	
	public GameBar getGameBar()
	{
		return bar;
	}
	
	public GameEngine getGameEngine()
	{
		return engine;
	}
	
	public GameStatus getStatus()
	{
		return status;
	}
	
	public GameSummary getGameSummary()
	{
		return summary;
	}

	public GameTool getGameTool() 
	{
		return tool;
	}
	
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
	private void setCurrentPlayer(Player plyr)
	{
		currentPlayer = plyr;
		currentHolder = plyr;
	}
	public Holder getCurrentHolder()
	{
		return currentHolder;
	}
	
	public CardPanel getCardPanel()
	{
		return cardPanel;
	}
	
	
	
	public PlayingCard getLastDealtCard(Holder holder)
	{
		List<PlayingCard> cards = lastDealtCard.get(holder);
		return (cards != null) ? cards.get(cards.size()-1) : null;
	}
	public List<PlayingCard> getAllDealtCards(Holder holder)
	{
		// to prevent modification of the viewModel deck
		if (lastDealtCard.get(holder) != null)
			return Collections.unmodifiableList(lastDealtCard.get(holder));
		else
			return null;
	}
	
	
	public boolean hasDealt(Player plyr)
	{
		return lastDealtCard.get(plyr) != null;
	}
	
	public void setLastDealtCard(Holder holder, PlayingCard card)
	{
		// If not instantiated yet, create a new cards arraylist
		List<PlayingCard> cards = (lastDealtCard.get(holder) != null) ? lastDealtCard.get(holder) : new ArrayList<PlayingCard>();
		cards.add(card);
		lastDealtCard.put(holder, cards);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void setRemovePlayerDialog(RemovePlayerDialog dialog)
	{
		removeDialog = dialog;
	}
	
	public void setGameBar(GameBar gameBar) 
	{
		this.bar = gameBar;
	}
	
	public void setGameSummary(GameSummary summary)
	{
		this.summary = summary;
	}
	
	public void setGameTool(GameTool tool) 
	{
		this.tool = tool;
	}
	
	public void setGameStatus(GameStatus status)
	{
		this.status = status;
	}
	
	public void setCardPanel(CardPanel cardPanel) 
	{
		this.cardPanel = cardPanel;
	}

	
	
	
	
	
	
	
	public AddPlayerDialog getAddPlayerDialog()
	{
		return addDialog;
	}
	
	public BetPlayerDialog getBetPlayerDialog()
	{
		return betDialog;
	}
	public RemovePlayerDialog getRemovePlayerDialog()
	{
		return removeDialog;
	}
	
	public void setAddPlayerDialog(AddPlayerDialog dialog)
	{
		addDialog = dialog;
	}
	public void setBetPlayerDialog(BetPlayerDialog dialog)
	{
		betDialog = dialog;
	}

}
