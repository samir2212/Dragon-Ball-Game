package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dragonball.model.attack.PhysicalAttack;
import dragonball.model.battle.Battle;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.player.Player;

public class BattleView extends JFrame {
   private JTextArea meInfo;
   private JTextArea foeInfo;
   private JButton block;
   private JComboBox superAttackList;
   private JButton superAttack;
   private JComboBox ultimateAttackList;
   private JButton ultimateAttack;
   private JButton physicalAttack;
   private JButton use;
   private Battle battle;
   private Player player;
   private JTextField turnDisplay;
public JTextField getTurnDisplay() {
	return turnDisplay;
}
public JTextArea getMeInfo() {
	return meInfo;
}
public JTextArea getFoeInfo() {
	return foeInfo;
}
public JButton getBlock() {
	return block;
}
public JComboBox getSuperAttackList() {
	return superAttackList;
}
public JButton getSuperAttack() {
	return superAttack;
}
public JComboBox getUltimateAttackList() {
	return ultimateAttackList;
}
public JButton getUltimateAttack() {
	return ultimateAttack;
}
public JButton getPhysicalAttack() {
	return physicalAttack;
}
public JButton getUse() {
	return use;
}
public Battle getBattle() {
	return battle;
}
public Player getPlayer() {
	return player;
}

public BattleView(Battle battle,Player player){
	   setTitle("Battle");
	   this.battle = battle;
	   this.player = player;
	   //battle image
	   ImagePanel panel = new ImagePanel("Battle.jpg");
	   add(panel,BorderLayout.CENTER);
	   //getting me fighter info
	   meInfo = new JTextArea();
	   PlayableFighter meFighter = (PlayableFighter) battle.getMe();
	   meInfo.setText(this.MeInfo(meFighter));
	   meInfo.setEditable(false);
	   //getting foe info
	   foeInfo = new JTextArea();
	   NonPlayableFighter foeFighter = (NonPlayableFighter) battle.getFoe();
	   foeInfo.setText(foeInfo(foeFighter));
	   foeInfo.setEditable(false);
	   //control panel
	   //superAttack
	   JPanel controlSuperAttack = new JPanel(new GridLayout(0,2));
	   superAttackList = new JComboBox();
	   for(int i =0;i<meFighter.getSuperAttacks().size();i++){
		   superAttackList.addItem(meFighter.getSuperAttacks().get(i).getName());
	   }
	   superAttack = new JButton("SuperAttack");
	   controlSuperAttack.add(superAttackList);
	   controlSuperAttack.add(superAttack);
	   //ultimateAttack
	   JPanel controlUltimateAttack = new JPanel(new GridLayout(0,2));
	   ultimateAttackList = new JComboBox();
	   for(int i =0;i<player.getUltimateAttacks().size();i++){
		   ultimateAttackList.addItem(meFighter.getUltimateAttacks().get(i).getName());
	   }
	   ultimateAttack = new JButton("Ultimate Attack");
	   controlUltimateAttack.add(ultimateAttackList);
	   controlUltimateAttack.add(ultimateAttack);
	   //Block & use
	   block = new JButton("Block");
	   use = new JButton("USE");
	   physicalAttack = new JButton("Physical Attack");
	   JPanel control = new JPanel(new GridLayout(0,1));
	   turnDisplay = new JTextField();
	   turnDisplay.setEditable(false);
	   control.add(turnDisplay);
	   control.add(physicalAttack);
	   control.add(controlSuperAttack);
	   control.add(controlUltimateAttack);
	   control.add(block);
	   control.add(use);
	   if(player.getActiveFighter().getSuperAttacks().size() == 0){
		   superAttack.setEnabled(false);
	   }
	   if(player.getActiveFighter().getUltimateAttacks().size() ==0){
		   ultimateAttack.setEnabled(false);
	   }
	   // east panel
	   JPanel east= new JPanel(new GridLayout(0,1));
	   east.add(control);
	   east.add(meInfo);
	   east.add(foeInfo);
	   add(east,BorderLayout.EAST);
	   
}

// me fighter info
public String MeInfo(PlayableFighter fighter){
		String info = fighter.getName()+"\n" +
				"------------------------ \n"
				+"Level : "+fighter.getLevel() +"\n"
				+"Health Points :"+fighter.getHealthPoints()+"\n";
		info += "Max Health Points : "+fighter.getMaxHealthPoints() +"\n"
				+"Stamina :" + fighter.getStamina()+"\n";
		info += "Max Stamina : "+fighter.getMaxStamina()+"\n"
				+"Ki :"+fighter.getKi()+"\n";
		info += "Max Ki : "+fighter.getMaxKi();
		if(fighter instanceof Saiyan)
			info+= "Transformed: "+((Saiyan) fighter).isTransformed();
			
		return info;
}
// foe info
public String foeInfo(NonPlayableFighter fighter){
	   String info = fighter.getName()+"\n" +
				"------------------------ \n"
				+"Level : "+fighter.getLevel() +"\n"
				+"Health Points :"+fighter.getHealthPoints()+"\n";
		info += "Max Health Points : "+fighter.getMaxHealthPoints() +"\n"
				+"Stamina :" + fighter.getStamina()+"\n";
		info += "Max Stamina : "+fighter.getMaxStamina()+"\n"
				+"Ki :"+fighter.getKi()+"\n";
		info += "Max Ki : "+fighter.getMaxKi();
		
		return info;
}
  public void newTurn(Battle battle) {
	  this.battle = battle;
	  if(battle.getMe() == battle.getAttacker()){
		  turnDisplay.setText(((PlayableFighter) battle.getMe()).getName()+" turn");
	  }
	  if(battle.getFoe() == battle.getAttacker()){
		  turnDisplay.setText(((NonPlayableFighter) battle.getFoe()).getName()+" turn");
		  try {
				battle.play();
			} catch (NotEnoughKiException e1) {
				try {
					battle.play();
				} catch (NotEnoughKiException e2) {
					try {
						battle.play();
					} catch (NotEnoughKiException e) {
						try {
							battle.play();
						} catch (NotEnoughKiException e3) {
							try {
								battle.attack(new PhysicalAttack());
							} catch (NotEnoughKiException e4) {
								JOptionPane.showMessageDialog(this,e4.getMessage());
							}
						}
					}
				}
			}
	  }
  }
}
