package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.core.Enchantment;
import com.core.Enchantment.EnchantmentType;
import com.core.Game;

@SuppressWarnings("serial")
class EstorePanel extends JPanel{
	
	private final Game game;
	private final GameUI gui;
	private Enchantment selected;
	private JButton bankrupt, loseTurn, takeWord;
	private JButton buy;
	private final String bankruptDescription;
	private final String loseTurnDescription;
	private final String takeWordDescription;
	
	public EstorePanel(final Game game, final GameUI gui) throws IOException{
		this.game = game;
		this.gui = gui;
		selected = null;
		this.setLayout(null);
		this.setBackground(new Color(217,221,220));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		bankruptDescription = "Description: When activated\n by another player, " +
				"this tile\n bankrupts that player, but\n the player still " +
				"gets the score\n for the current word being\n played.";
		loseTurnDescription = "Description: When activated\n by another player, " +
				"this tile\n causes that player to lose\n his turn, rendering " +
				"the current\n word void.";
		takeWordDescription = "Description: When activated\n by another player, " +
				"this tile\n allows the owner to take the\n current word from " +
				"the current\n player.";
		
		bankrupt = new JButton("Bankrupt");
		bankrupt.setBounds(165, 15, 120, 30);
		
		bankrupt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (game.getNumBankrupts() > 0)
					selected = new Enchantment(EnchantmentType.BANKRUPT);
				gui.repaintAll();
			}
		});
		
		loseTurn = new JButton("Lose Turn");
		loseTurn.setBounds(165, 145, 120, 30);
		
		loseTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (game.getNumLoseTurns() > 0)
					selected = new Enchantment(EnchantmentType.LOSE_TURN);
				gui.repaintAll();
			}
		});
		
		takeWord = new JButton("Take Word");
		takeWord.setBounds(165, 275, 120, 30);
		
		takeWord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (game.getNumTakeWords() > 0)
					selected = new Enchantment(EnchantmentType.TAKE_WORD);
				gui.repaintAll();
			}
		});
		
		buy = new JButton("Buy");
		buy.setBounds(15, 195, 120, 30);
		
		buy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (selected != null){
					game.buyEnchantment(selected);
					gui.repaintAll();
				}
			}
		});
		
		this.add(bankrupt);
		this.add(loseTurn);
		this.add(takeWord);
		this.add(buy);
		
	}
	
	private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(0,0,10));
		g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		//bankrupt description
		drawString(g, bankruptDescription, 165, 45);
		drawString(g, loseTurnDescription, 165, 175);
		drawString(g, takeWordDescription, 165, 305);
		
		g.setColor(new Color(255,0,0));
		g.drawString("Number left: " + game.getNumBankrupts(), 165, 140);
		g.drawString("Number left: " + game.getNumLoseTurns(), 165, 270);
		g.drawString("Number left: " + game.getNumTakeWords(), 165, 400);
		
		
		g.setColor(new Color(255,255,0));
		if (selected != null){
			if (selected.getType() == EnchantmentType.BANKRUPT){
				g.drawRect(160, 10, 130, 130);
			}
			if (selected.getType() == EnchantmentType.LOSE_TURN){
				g.drawRect(160, 140, 130, 130);
			}
			if (selected.getType() == EnchantmentType.TAKE_WORD){
				g.drawRect(160, 270, 130, 130);
			}
		}
	}
	
	
	
}