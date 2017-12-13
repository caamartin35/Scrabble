package com.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.core.Game;
import com.core.Tile;
import com.util.Location;

@SuppressWarnings("serial")
class BlankTileChoicePanel extends JPanel implements ActionListener{
	
	private final Game game;
	private final GameUI gui;
	private JLabel label;
	private JTextField textField;
	private Location release;
	
	public BlankTileChoicePanel(final Game game, GameUI gui) throws IOException{
		this.game = game;
		this.gui = gui;
		
		this.setBorder(null);
		this.setLayout(new BorderLayout());
		
		label = new JLabel("Type the letter you wish for this tile to be (lowercase).");
		label.setVisible(true);
		this.add(label, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setVisible(true);
		this.add(textField, BorderLayout.CENTER);
		
		// add functionality to the step button.
		textField.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		if (text.length() == 1){
			Tile tile = new Tile(findAppropriateValue(text.charAt(0)), text.charAt(0), Tile.TileType.TYPE_BLANK);
			game.addToWorkingSet(tile, release);
			gui.setChoosing(false);
			gui.repaintAll();
		}
		
	}

	private int findAppropriateValue(char letter) {
		int aAscii = 97;
		int[] values = game.getTileValues();
		int index = letter-aAscii;
		return values[index];
	}

	public void saveReleaseLoc(Location coordsToLoc) {
		release = coordsToLoc;
		
	}

}