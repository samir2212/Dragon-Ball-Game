package dragonball.view;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import sun.net.www.content.image.jpeg;

import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.player.Player;
import dragonball.model.world.World;

public class WorldView extends JFrame  {
	private int position;
	private ArrayList<JButton> mapCells;
	private JButton moveUp;
	private JButton moveDown;
	private JButton moveRight;
	private JButton moveLeft;
	private JTextArea playerInfo;
	private JTextArea activeFighterInfo;
	private JComboBox chooseactiveFighter;
	private JButton createNewFighter;
	private JButton assignAttack;
	private JButton upgradeFighter;
	private JButton ActiveFighter;
	private JPanel choose;
	private JButton save;
	private JTextArea saveText;

	public JButton getSave() {
		return save;
	}

	public JTextArea getSaveText() {
		return saveText;
	}

	World world = new World();
	Player player = new Player("Player");
	
	
	public WorldView(World world,Player player){
		this.player = player;
		this.world = world;
		this.setTitle("World");
		
		position = (world.getPlayerRow())*10+(world.getPlayerColumn());
		// map
		JPanel map = new JPanel(new GridLayout(10,10));
	    mapCells = new ArrayList<JButton>();
	    for(int i = 0;i<100;i++){
	    	JButton cell = new JButton();
	    	if(i == 0){
	    		cell.setBackground(Color.GREEN);
	    		cell = new ImageButton("foe.png");
	    		mapCells.add(cell);
	    		map.add(cell);
	    	}
	    	else{if(i == position){
	    		cell.setBackground(Color.BLACK);
	    		mapCells.add(cell);
	    		map.add(cell);
	    	}
	    	else{
	    		cell.setBackground(Color.WHITE);
	    		mapCells.add(cell);
	    		map.add(cell);
	    	}
	    	}
	    }
		JPanel control = new JPanel(new GridLayout(0,1));
		//player info 
		playerInfo = new JTextArea();
		playerInfo.setEditable(false);
		playerInfo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        playerInfo.setText(getPlayerInfo(player));
		//active fighter info
		activeFighterInfo = new JTextArea();
		activeFighterInfo.setEditable(false);
        activeFighterInfo.setText(getActiveFighterInfo(player.getActiveFighter()));
		
		// move buttons
		JPanel moveButtons = new JPanel(new GridLayout(0,4));
		moveUp = new JButton("MoveUp");
		moveDown = new JButton("MoveDown");
		moveRight = new JButton("MoveRight");
		moveLeft = new JButton("MoveLeft");
		moveButtons.add(moveUp);
	    moveButtons.add(moveDown);
	    moveButtons.add(moveRight);
	    moveButtons.add(moveLeft);
       
	    //choosing activeFighter
	    chooseactiveFighter = new JComboBox();
	    chooseactiveFighter.addItem(player.getActiveFighter().getName());
	    for(int i = 0;i<player.getFighters().size();i++){
	    	if(player.getActiveFighter() != player.getFighters().get(i)){
	    		chooseactiveFighter.addItem(player.getFighters().get(i).getName());
	    	}
	    }
	    ActiveFighter = new JButton("Active Fighter");
	    choose = new JPanel(new GridLayout(0,2));
	    choose.add(chooseactiveFighter);
	    choose.add(ActiveFighter);
	    
	    // adding to the control panel
        control.add(playerInfo);
        control.add(activeFighterInfo);
        // save
        JPanel savePanel = new JPanel(new GridLayout(1,2));
        save = new JButton("save");
        saveText = new JTextArea();
        savePanel.add(saveText);
        savePanel.add(save);
        //The fighter buttons
        JPanel buttons = new JPanel(new GridLayout(0,1));
        createNewFighter = new JButton("Create New Fighter");
        upgradeFighter = new JButton("Upgrade Fighter");
        assignAttack = new JButton("AssignAttack");
        
        buttons.add(moveButtons);
        buttons.add(savePanel);
        buttons.add(createNewFighter);
        buttons.add(upgradeFighter);
        buttons.add(assignAttack);
        buttons.add(choose);
        buttons.add(savePanel);
        JPanel east = new JPanel(new BorderLayout());
        east.add(buttons,BorderLayout.NORTH);
        east.add(control,BorderLayout.CENTER);
        //adding to the components
        add(east,BorderLayout.EAST);
	    add(map,BorderLayout.CENTER);
	}
	
	public ArrayList<JButton> getMapCells() {
		return mapCells;
	}
	public JButton getMoveUp() {
		return moveUp;
	}
    
	public JComboBox getChooseactiveFighter() {
		return chooseactiveFighter;
	}

	public JButton getCreateNewFighter() {
		return createNewFighter;
	}

	public JButton getAssignAttack() {
		return assignAttack;
	}

	public JButton getUpgradeFighter() {
		return upgradeFighter;
	}

	public JButton getMoveDown() {
		return moveDown;
	}

	public JButton getMoveRight() {
		return moveRight;
	}

	public JButton getMoveLeft() {
		return moveLeft;
	}

	public JTextArea getPlayerInfo() {
		return playerInfo;
	}

	public JTextArea getActiveFighterInfo() {
		return activeFighterInfo;
	}

	public JButton getActiveFighter() {
		return ActiveFighter;
	}
    
	public void setFightersList(Player player){
		this.player = player;
		chooseactiveFighter = new JComboBox();
	    chooseactiveFighter.addItem(player.getActiveFighter().getName());
	    for(int i = 0;i<player.getFighters().size();i++){
	    	if(player.getActiveFighter() != player.getFighters().get(i)){
	    		chooseactiveFighter.addItem(player.getFighters().get(i).getName());
	    	}
	    }
	    ActiveFighter = new JButton("Active Fighter");
	    choose = new JPanel();
	    choose.add(chooseactiveFighter);
	    choose.add(ActiveFighter);
	}
	

	public String getActiveFighterInfo(PlayableFighter fighter){
		String info = fighter.getName()+"\n" +
				"------------------------ \n"
				+"Level : "+fighter.getLevel() +"\n";
		info += "Health Points : "+fighter.getMaxHealthPoints() +"\n";
		info += "Stamina : "+fighter.getMaxStamina()+"\n";
		info += "Ki : "+fighter.getMaxKi()+"\n";
		info += "Blast Damage : "+fighter.getBlastDamage()+"\n";
		info += "Physical Damage :"+fighter.getPhysicalDamage()+"\n";
		info += "Super Attacks : ";
		for(int i = 0;i<fighter.getSuperAttacks().size();i++){
			info+= fighter.getSuperAttacks().get(i).getName()+" ";
		}
		info += "\n";
		info += "Ultimate Attacks : ";
		for(int i = 0;i<fighter.getUltimateAttacks().size();i++){
			info+=" "+ fighter.getUltimateAttacks().get(i).getName();
		}
		info += "\n";
		info += "Xp :"+fighter.getXp()+"\n"
				+"TargetXp :"+fighter.getTargetXp()+"\n"+
				"Ability Points :"+fighter.getAbilityPoints();
		return info;
	}
	
	public String getPlayerInfo(Player player){
		String info = "Player :"+"\n"+"------------------------"+"\n";
		info += "Name :"+player.getName()+"\n";
		info += "Fighters :( ";
		for(int i = 0;i<player.getFighters().size()-1;i++){
			info+= player.getFighters().get(i).getName()+" - ";
		}
		
		info +=player.getFighters().get(player.getFighters().size()-1).getName()+" )\n";
		info += "Super Attacks : ";
		for(int i = 0;i<player.getSuperAttacks().size();i++){
			info+= player.getSuperAttacks().get(i).getName()+" ";
		}
		info += "\n";
		info += "Ultimate Attacks : ";
		for(int i = 0;i<player.getUltimateAttacks().size();i++){
			info+=" "+ player.getUltimateAttacks().get(i).getName();
		}
		info += "\n";
		info += "Senzu Beans :"+ player.getSenzuBeans()+"\n";
		info += "Dragon Balls :"+ player.getDragonBalls()+"\n";
		info += "exploredMaps :"+ player.getExploredMaps();
		return info;
	}
	
	public void setTheMap(World world){
		this.world = world;
	    mapCells.get(position).setBackground(Color.WHITE);
	    position = (world.getPlayerRow())*10+(world.getPlayerColumn());
	    mapCells.get(position).setBackground(Color.BLACK);
	}
	
	
}
