package view.GUI.aesthetics;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.interfaces.PlayingCard;
import view.ViewModel;
import view.GUI.GameFrame;


// class which has specialized methods for resizing
// + setting up card display
public class GameLabel extends JLabel {

	private Integer size;
	private Integer style;
	private String name;
	private PlayingCard card;
	
	private ViewModel model;
	// JLabel but automatically resizes
	public GameLabel(ViewModel model)
	{
		super();
		this.model = model;
		
		this.addComponentListener(new ResizeAdapter());
	}
	
	public GameLabel(ViewModel model, String text)
	{
		this(model);
		setText(text);
	}
	
	public GameLabel(ViewModel model, String text, int horizontalAlignment)
	{
		this(model);
		setText(text);
		setHorizontalAlignment(horizontalAlignment);
	}
	
	public GameLabel(ViewModel model, PlayingCard card, int size)
	{
		this(model);
		this.card = card;
		this.size = size;
	}
	
	
	
	
	
	
	
	
	
	
	
	public void setFont(String fontName, Integer fontStyle, Integer fontSize)
	{
		this.size = fontSize;
		this.style = fontStyle;
		this.name = fontName;
		// size cannot be altered by the height constant
		if (size == null)	size = getFont().getSize();
		if (style == null)	style = getFont().getStyle();
		if (name == null)	name = getFont().getName();
		
		setFont(new Font(name, style, getResizedSize()));
	}
	
	public void setFont(String name, Integer style)
	{
		setFont(name, style, size);
	}
	
	
	
	public void setIcon(PlayingCard card, int size)
	{
		this.size = size;
		this.card = card;
		setIcon(getCardIcon(card, getResizedSize()));
	}
	
	private ImageIcon getCardIcon(PlayingCard card, int scaleHeight)
	{
		// "cards/10_of_hearts.jpg"
		String valueString	= card.getValue().getImageString();
		String suitString 	= card.getSuit().getImageString();
		Image img = new ImageIcon(String.format("cards%s%s_of_%s.png",File.separator,valueString,suitString)).getImage();
		return 		new ImageIcon(img.getScaledInstance(scaleHeight*2/3, scaleHeight,  Image.SCALE_FAST));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private int getResizedSize()
	{
		GameFrame frame = model.getFrame();
		return size*frame.getHeight()/frame.INITIAL_HEIGHT;
	}
	
	private class ResizeAdapter extends ComponentAdapter
	{
		@Override
		public void componentResized(ComponentEvent arg0) 
		{
			if (card != null)
				setIcon(card, size);
			else
				setFont(name, style, size);
		}


		@Override
		public void componentMoved(ComponentEvent e) 
		{
			if (card != null)
				setIcon(card, size);
			else
				setFont(name, style, size);
		}

	}
}
