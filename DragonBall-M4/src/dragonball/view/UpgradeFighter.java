package dragonball.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import dragonball.model.player.Player;

public class UpgradeFighter extends JFrame {
    private JComboBox fighterList;
    private JComboBox attributes;
    private JButton upgradeF;
    private ImagePanel panel;
    
	public ImagePanel getPanel() {
		return panel;
	}
	public JComboBox getFighterList() {
		return fighterList;
	}
	public JComboBox getAttributes() {
		return attributes;
	}
	public JButton getUpgradeFighter() {
		return upgradeF;
	}
	public UpgradeFighter(Player player){
	
	  panel = new ImagePanel("fighter-upgrade.png");
	  panel.setLayout(new FlowLayout());
	  upgradeF = new JButton("Upgrade");
	  String[] attributesList = {"Health Points","Blast Damage","Physical Damage",
			                 "Ki","Stamina"};
	  attributes = new JComboBox(attributesList);
	  fighterList = new JComboBox();
	  fighterList.addItem(player.getActiveFighter().getName());
	  for(int i = 0;i<player.getFighters().size();i++){
	    	if(player.getActiveFighter() != player.getFighters().get(i)){
	    		fighterList.addItem(player.getFighters().get(i).getName());
	    	}
	    }
	  
	  panel.add(fighterList);
	  panel.add(attributes);
	  panel.add(upgradeF);
	  add(panel);
	  
  }
	public static void main(String [] args){
		Player player = new Player("x");
		player.createFighter('C',"");
		player.createFighter('S',"Z");
		new UpgradeFighter(player);
	}
}
