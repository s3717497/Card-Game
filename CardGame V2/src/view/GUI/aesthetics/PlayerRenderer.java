package view.GUI.aesthetics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Player;
import view.ViewModel;


public class PlayerRenderer extends JLabel implements ListCellRenderer<Player>
{
	
	ViewModel model;
	public PlayerRenderer(ViewModel model)
	{
		setOpaque(true);
		this.model = model;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list, 
	Player plyr, int index, boolean isSelected, boolean cellHasFocus)
	{
		setText((model.getCurrentPlayer() != null) ? "  " +plyr.getName() : "Select player");
		
		setBackground(isSelected ?  Color.DARK_GRAY : new Color(50,50,50));
		setForeground(Color.LIGHT_GRAY);
		setFont(isSelected ? (new Font(getFont().getFamily(), Font.BOLD, 20)) : (new Font(getFont().getFamily(), Font.PLAIN, 15)));
		
		return this;
	}

}
