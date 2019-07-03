package controller.dialogs;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.PlayerFunction;
import model.Player;
import view.ViewModel;

public class RemovePlayerDialog extends AddBetRemoveDialog{

	private final static PlayerFunction ENGINE_FUNCTION = PlayerFunction.REMOVE;
	private JTextField idInput;
	private ViewModel model;
	private int counter = 0;
	private Player plyr;

	public RemovePlayerDialog(ViewModel model)
	{
		super(model, ENGINE_FUNCTION);
		idInput = new JTextField(10);
		
		this.model = model;
	}
	
	
	
	
	
	@Override
	void modelCall() throws EmptyInputException, EngineException
	{
		if (showConfirmDialog(model.getFrame(), "Are your sure you want to remove " + retrievePlayer().getName()
		+ "from the game?",
		"Confirm removal", JOptionPane.OK_CANCEL_OPTION) == OK_OPTION)
		model.removePlayer(plyr);
	}
	
	
	public void display()
	{
		Object[] message = {
				"Player id: ", 		idInput
		};
		super.displayDialog(message, "Remove Player");
	}

	
	
	
	

	
	
	
	private String retrieveId() throws EmptyInputException 
	{
		String idInput = this.idInput.getText();
		if (idInput.equals("")) 
			throw new EmptyInputException("ID");				
		return idInput;
	}
	private Player retrievePlayer() throws EmptyInputException, EngineException
	{
		String id = retrieveId();
		plyr = model.getGameEngine().getPlayer(id);
		if (model.getGameEngine().getAllPlayers().isEmpty())
			throw new EngineException(noPlayerMsg());
		else if (plyr == null)
			throw new EngineException("Player with id " + id + " not found");	
		
		return plyr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String noPlayerMsg()
	{
		switch(counter)
		{
			case 0: return "There are no players to remove. Resistance is futile";							
			case 1: return "Stop resisting";																
			case 2:	return "This is your last chance. Stop otherwise this application will self-destruct";
			default: System.exit(0);
		}
		counter++;
		return null;
	}

	
}
