package view.GUI.aesthetics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

//a common button aethetic for all classes
public class GameButton extends JButton {

		public GameButton(String name)
		{
			setText(name);
			Color unselected = new Color(0,99,228);
			Color selected = new Color(0,79,182);
			Color clicked = new Color(0,69,160);
			
			setForeground(Color.WHITE);
			setBackground(unselected);
			setBorderPainted(false);
			
			// this is simply an aethetic setting for hover
			addMouseListener(new MouseListener()
		    {
				public void mouseClicked(MouseEvent arg0) 	{}
				public void mouseEntered(MouseEvent e) 		{setBackground(selected);}
				public void mouseExited (MouseEvent e) 		{setBackground(unselected);}
				public void mousePressed(MouseEvent e) 		{setBackground(clicked);}
				public void mouseReleased(MouseEvent e) 	{}
		    });
		}
}
