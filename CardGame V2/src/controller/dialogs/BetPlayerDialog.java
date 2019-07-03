package controller.dialogs;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.PlayerFunction;
import model.Player;
import view.ViewModel;

public class BetPlayerDialog extends AddBetRemoveDialog{

	private final static PlayerFunction ENGINE_FUNCTION = PlayerFunction.BET;
	private JTextField betInput;
	private ViewModel model;
	
	public BetPlayerDialog(ViewModel model)
	{
		super(model, ENGINE_FUNCTION);
		betInput = new JTextField(10);
		this.model = model;
		
		
//		super.handle();
//		int dialog = showConfirmDialog(model, message, "Bet Player", JOptionPane.OK_CANCEL_OPTION);
//		if (dialog == OK_OPTION) 
//		{
//			
//		}
	}

	@Override
	void modelCall() throws EmptyInputException, EngineException 
	{
		model.placeBet(retrieveBet());
	}
	
	public void display()
	{
		Object[] message = {
				"Enter the amount to bet: ", betInput
		};
		super.displayDialog(message, "Bet Player");
	}
	
	//calculates the bet value from input field
	private int retrieveBet() throws EngineException 
	{
		Player plyr = model.getCurrentPlayer();
		int bet = Integer.parseInt(betInput.getText());
		if (plyr.getBet() > 0)
			throw new EngineException("You have already betted");
		if (bet <= 0)
			throw new NumberFormatException();					
		// instead of creating engine deep copy
		// do manual checks
		if (bet > plyr.getPoints())
			throw new EngineException("You need to have enough points to make this bet");
		//GameEngine testEngine = engine.clone();
		//if (testEngine.placeBet(plyr, bet))
		return bet;
	}
	
	
}
