package com.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.core.Game;

@SuppressWarnings("serial")
class PickNamePanel extends JPanel implements ActionListener{
	
	private final Game game;
	private final GameUI gui;
	private JLabel label;
	private JTextField textField;
	
	public PickNamePanel(final Game game, GameUI gui) throws IOException{
		this.game = game;
		this.gui = gui;
		
		this.setBorder(null);
		this.setLayout(new BorderLayout());
		
		label = new JLabel("Type New Player's Name (Press Enter To Submit)");
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
		if (text.length() <= 10 && text.length() > 0){
			if (game.playerExists(text)){
				gui.setError(true, "Cannot have two players with same name");
			}
			else {
				game.addPlayer(text);
				textField.setText("");
				gui.setPicking(false);
			}
		}
		else if (text.length() > 10)
			gui.setError(true, "Name is too long");
		else
			gui.setError(true, "No name entered");
		gui.repaintAll();
		
	}
}