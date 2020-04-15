package dragonball.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javafx.scene.control.ComboBox;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.player.Player;

public class AssignAttackView extends JFrame{
   private JComboBox PlayerSuperAttacks;
   private JComboBox PlayerUltimateAttacks;
   private JComboBox FighterSuperAttacks;
   private JComboBox FighterUltimateAttacks;
   private JButton SuperAttack;
   private JButton UltimateAttack;
   public AssignAttackView(Player player,PlayableFighter fighter){
	 JPanel superAttackPanel = new JPanel(new FlowLayout());
	 PlayerSuperAttacks = new JComboBox();  
	 for(int i =0;i<player.getSuperAttacks().size();i++){
		 PlayerSuperAttacks.addItem(player.getSuperAttacks().get(i).getName());
	 }
	 FighterSuperAttacks = new JComboBox();
	 FighterSuperAttacks.addItem("None");
	 for(int i =0;i<fighter.getSuperAttacks().size();i++){
		 FighterSuperAttacks.addItem(fighter.getSuperAttacks().get(i).getName());
	 }
	 SuperAttack = new JButton("Super Attack");
	 superAttackPanel.add(PlayerSuperAttacks);
	 superAttackPanel.add(FighterSuperAttacks);
	 superAttackPanel.add(SuperAttack);
	 superAttackPanel.setBackground(Color.BLACK);
	 //ultimate Attack
	 JPanel UltimateAttackPanel = new JPanel(new FlowLayout());
	 PlayerUltimateAttacks = new JComboBox();
	 for(int i =0;i<player.getUltimateAttacks().size();i++){
		 PlayerUltimateAttacks.addItem(player.getUltimateAttacks().get(i).getName());
	 }
	 FighterUltimateAttacks = new JComboBox();
	 FighterUltimateAttacks.addItem("None");
	 for(int i =0;i<fighter.getUltimateAttacks().size();i++){
		 FighterUltimateAttacks.addItem(fighter.getUltimateAttacks().get(i).getName());
	 }
	 UltimateAttack = new JButton("Ultimate Attack");
	 UltimateAttackPanel.add(PlayerUltimateAttacks);
	 UltimateAttackPanel.add(FighterUltimateAttacks);
	 UltimateAttackPanel.add(UltimateAttack);
	 UltimateAttackPanel.setBackground(Color.BLACK);
	 ImagePanel panel = new ImagePanel("AssignAttack.png");
	 panel.setLayout(new FlowLayout());
	 panel.add(superAttackPanel);
	 panel.add(UltimateAttackPanel);
	 add(panel);
   }

   
  public JComboBox getPlayerSuperAttacks() {
	  return PlayerSuperAttacks;
  }
  public JComboBox getPlayerUltimateAttacks() {
	  return PlayerUltimateAttacks;
  }
  public JComboBox getFighterSuperAttacks() {
	  return FighterSuperAttacks;
  }
  public JComboBox getFighterUltimateAttacks() {
	  return FighterUltimateAttacks;
  }
  public JButton getSuperAttack() {
	  return SuperAttack;
  }
  public JButton getUltimateAttack() {
	  return UltimateAttack;
  }
   
}
