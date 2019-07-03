package controller.dialogs;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.PlayerFunction;
import view.ViewModel;

public abstract class AddBetRemoveDialog extends JOptionPane{

	private ViewModel model;
	private PlayerFunction function;
	
	public AddBetRemoveDialog(ViewModel model, PlayerFunction function)
	{
		this.model = model;
		this.function = function;
	}
	
	
	
	
	
	
	// model functions such as add/bet player
	abstract void modelCall() throws EmptyInputException, EngineException;
	
	
	// function that allows all classes to display the required dialog
	// called from outside the dialog classes
	public abstract void display();
	void displayDialog(Object[] message, String title)
	{
		int dialog = showConfirmDialog(model.getFrame(), message, title, JOptionPane.OK_CANCEL_OPTION);
		if (dialog == CANCEL_OPTION)
			return;
		handle();
	}
	
	
	private void handle() 						/*compact the language*/
	{
		AddBetRemoveDialog dialog;
		switch (function)
		{
			case ADD : 		dialog = model.getAddPlayerDialog(); 	break;
			case BET : 		dialog = model.getBetPlayerDialog(); 	break;
			case REMOVE :	dialog = model.getRemovePlayerDialog(); break;
			default : 		dialog = null;
		}
		try 
		{
			dialog.modelCall();
		}
		catch (NumberFormatException e) 
		{
			showErrorDialog("Please enter a valid number greater than 0", "Invalid " + function);
			dialog.display();
		}
		catch (EngineException e) 
		{
			showErrorDialog(e.getMessage(), "Invalid " + function);
			dialog.display();
		}
		catch (EmptyInputException e) 
		{
			showErrorDialog("Please enter " + e.getMessage(), "Invalid " + e.getMessage());
			dialog.display();
		}
	}
	
	
	private void showErrorDialog(String message, String title)
	{
		JOptionPane.showMessageDialog(model.getFrame(), message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	class EngineException extends Exception { 
	    public EngineException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	class EmptyInputException extends Exception { 
	    public EmptyInputException(String errorMessage) {
	        super(errorMessage);
	    }
	}

}
