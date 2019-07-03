package controller.dialogs;

import javax.swing.JTextField;

import controller.PlayerFunction;
import model.Player;
import view.ViewModel;

public class AddPlayerDialog extends AddBetRemoveDialog
{
	

	private final static PlayerFunction ENGINE_FUNCTION = PlayerFunction.ADD;
	private JTextField nameInput;
	private JTextField idInput;
	private JTextField pointsInput;
	private ViewModel model;
	

	public AddPlayerDialog(ViewModel model)
	{
		super(model, ENGINE_FUNCTION);
		nameInput = new JTextField(10);
		idInput = new JTextField(10);
		pointsInput = new JTextField(10);
		
		this.model = model;
	}
	
	
	
	
	
	@Override
	void modelCall() throws EmptyInputException
	{
		model.addPlayer(new Player(retrieveId(),retrieveName(),retrievePoints()));
	}
	
	
	public void display()
	{
		Object[] message = {
				"Player name: ", 	nameInput,
				"Player id: ", 		idInput,
				"Initial points: ", pointsInput
		};
		super.displayDialog(message, "Add Player");
	}

	
	
	
	
	
	
	
	
	
	
	
	// responsible for displaying error message
	// if a valid name can't be found
	private String retrieveName() throws EmptyInputException 
	{
		String nameInput = this.nameInput.getText();
		if (nameInput.equals(""))
			throw new EmptyInputException("name");
		return nameInput;
	}
	
	private String retrieveId() throws EmptyInputException 
	{
		String idInput = this.idInput.getText();
		if (idInput.equals("")) 
			throw new EmptyInputException("ID");				
		return idInput;
	}
	
	private int retrievePoints() 
	{
		int points = Integer.parseInt(pointsInput.getText());
		if (points <= 0)
			throw new NumberFormatException();
		return points;
	}

	
}
