package dragonball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JOptionPane;



import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.model.player.Player;
import dragonball.view.AssignAttackView;
import dragonball.view.BattleView;
import dragonball.view.DragonView;
import dragonball.view.Gameview;
import dragonball.view.NewFighterPanel;
import dragonball.view.UpgradeFighter;
import dragonball.view.Username;
import dragonball.view.WorldView;


public class GameGui implements GameListener, ActionListener {
	private Game game;
	private Gameview gameview;
	private Username window;
	private WorldView world;
    private UpgradeFighter upgrade;
    private NewFighterPanel newFighter;
    private AssignAttackView assignAttackView;
    private BattleView battleView;
    private Battle battle;
    private DragonView dragonView ;
    private Dragon dragon;
    
	public GameGui() throws MissingFieldException, UnknownAttackTypeException {
		game = new Game();
		System.out.println(game.getWorld());
		game.getPlayer().setDragonBalls(6);
		game.setListener(this);
		gameview = new Gameview();
		gameview.getStartButton().addActionListener(this);
		gameview.getLoadButton().addActionListener(this);
		gameview.setVisible(true);
		
		play();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton test = (JButton) (e.getSource());
		//StartWindow
		if(test == gameview.getStartButton()){
			window = new Username();
			window.getWorldButton().addActionListener(this);
			gameview.setContentPane(window.getContentPane());
			gameview.validate();
			gameview.repaint();
		}
		if(test == gameview.getLoadButton()){
			try {
				game.load(gameview.getLoadArea().getText());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			world = new WorldView(game.getWorld(),game.getPlayer());
		    world.getCreateNewFighter().addActionListener(this);
		    world.getUpgradeFighter().addActionListener(this);
		    world.getActiveFighter().addActionListener(this);
		    world.getCreateNewFighter().addActionListener(this);
		    world.getAssignAttack().addActionListener(this);
		    world.getMoveUp().addActionListener(this);
		    world.getMoveDown().addActionListener(this);
		    world.getMoveRight().addActionListener(this);
		    world.getMoveLeft().addActionListener(this);
		    gameview.setContentPane(world.getContentPane());
		    gameview.validate();
		}
		//
	   if(window !=null){
		if(test == window.getWorldButton()){
		      game.getPlayer().setName(window.getNamee().getText());
		      String race = (String) window.getBox().getSelectedItem();
		      game.getPlayer().createFighter(race.charAt(0),window.getFighterName().getText());
		      game.getPlayer().getActiveFighter().setAbilityPoints(4);
		      world = new WorldView(game.getWorld(),game.getPlayer());
		      world.getCreateNewFighter().addActionListener(this);
		      world.getUpgradeFighter().addActionListener(this);
		      world.getActiveFighter().addActionListener(this);
		      world.getCreateNewFighter().addActionListener(this);
		      world.getAssignAttack().addActionListener(this);
		      world.getMoveUp().addActionListener(this);
		      world.getMoveDown().addActionListener(this);
		      world.getMoveRight().addActionListener(this);
		      world.getMoveLeft().addActionListener(this);
		      gameview.setContentPane(world.getContentPane());
		      gameview.validate();
		}
		}
		
		// controlling world
	  if(world != null)	{
		 // save
		  if(test == world.getSave()){
		      try {
				game.save(world.getSaveText().getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }  
		 // controlling move 
		 if(test == world.getMoveUp()){
			try{ game.getWorld().moveUp();
			     world.setTheMap(game.getWorld());
			     world.validate();
			     world.repaint();
			}
			catch(MapIndexOutOfBoundsException ex){
				
			}	
		}
		if(test == world.getMoveDown()){
			try{ game.getWorld().moveDown();
			     world.setTheMap(game.getWorld());
			     world.validate();
			     world.repaint();
			}
			catch(MapIndexOutOfBoundsException ex){
				
			}	
		}
		if(test == world.getMoveLeft()){
			try{ game.getWorld().moveLeft();
			     world.setTheMap(game.getWorld());
			     world.validate();
			     world.repaint();
			}
			catch(MapIndexOutOfBoundsException ex){
				
			}	
		}
		if(test == world.getMoveRight()){
			try{ game.getWorld().moveRight();
			     world.setTheMap(game.getWorld());
			     world.validate();
			     world.repaint();
			}
			catch(MapIndexOutOfBoundsException ex){
				
			}	
		}
		// controlling map buttons
		if(test == world.getUpgradeFighter()){
			upgrade = new UpgradeFighter(game.getPlayer());
			upgrade.getUpgradeFighter().addActionListener(this);
			gameview.setContentPane(upgrade.getContentPane());
			gameview.validate();
		}
		
		if(upgrade != null &&test == upgrade.getUpgradeFighter()){
			PlayableFighter fighter = game.getPlayer().getActiveFighter();
			Player player = game.getPlayer();
			for(int i =0;i<game.getPlayer().getFighters().size();i++){
				if(player.getFighters().get(i).getName()==(String)upgrade.getFighterList().getSelectedItem()){
					fighter = player.getFighters().get(i);
				}
			}
			String attribute = (String) upgrade.getAttributes().getSelectedItem();
			try {
				player.upgradeFighter(fighter, attribute.charAt(0));
			} catch (NotEnoughAbilityPointsException e1) {
				JOptionPane.showMessageDialog(upgrade, e1.getMessage());
			}
			world.getActiveFighterInfo().setText(world.getActiveFighterInfo(game.getPlayer().getActiveFighter()));
			gameview.setContentPane(world.getContentPane());
		}
		
		
		// choose active fighter fighter
			if(test == world.getActiveFighter()){
				PlayableFighter fighter2 = game.getPlayer().getActiveFighter();
				for(int i =0;i<game.getPlayer().getFighters().size();i++){
					if(game.getPlayer().getFighters().get(i).getName()==(String)world.getChooseactiveFighter().getSelectedItem()){
						fighter2 = game.getPlayer().getFighters().get(i);
					}
				}
				game.getPlayer().setActiveFighter(fighter2);
				world.getActiveFighterInfo().setText(world.getActiveFighterInfo(fighter2));
			}
			
			//create new fighter
			if(test == world.getCreateNewFighter()){
				newFighter = new NewFighterPanel(game.getPlayer());
				newFighter.getCreate().addActionListener(this);
				gameview.setContentPane(newFighter.getContentPane());
				gameview.validate();
			}
			
			if( newFighter != null &&test == newFighter.getCreate()){
				String race = (String)newFighter.getRace().getSelectedItem();
				String name = newFighter.getFighterName().getText();
				Player player = game.getPlayer();
				game.getPlayer().createFighter(race.charAt(0), name);
				world.getChooseactiveFighter().addItem(player.getFighters().get(player.getFighters().size()-1).getName());
				world.getPlayerInfo().setText(world.getPlayerInfo(player));
				gameview.setContentPane(world.getContentPane());
				gameview.validate();
			}
			// controlling assignAttack
			if(test == world.getAssignAttack()){
				if(game.getPlayer().getUltimateAttacks().size()!=0 || game.getPlayer().getSuperAttacks().size()!=0){
				  assignAttackView = new AssignAttackView(game.getPlayer(),game.getPlayer().getActiveFighter());
				  assignAttackView.getSuperAttack().addActionListener(this);
				  assignAttackView.getUltimateAttack().addActionListener(this);
				  gameview.setContentPane(assignAttackView.getContentPane());
				  gameview.validate();
				  gameview.repaint();}
				else{
					JOptionPane.showMessageDialog(world, "The Player did not learn Ultimate Attacks or Super Attacks yet");
				}
			}
			// assign super attack
			if(assignAttackView !=null &&test == assignAttackView.getSuperAttack()){
			    if(game.getPlayer().getSuperAttacks().size() !=0)	
				  {PlayableFighter fighter = game.getPlayer().getActiveFighter();
				    String oldAttack = (String) assignAttackView.getFighterSuperAttacks().getSelectedItem();
			    	String newAttack = (String) assignAttackView.getPlayerSuperAttacks().getSelectedItem();
			    	// Old Attack
			    	SuperAttack oldAttackX = (SuperAttack) game.getAttacks().get(0);
			    	if(oldAttack != "None")
			    	   {for(int i =0;i<fighter.getSuperAttacks().size();i++){
			    		  if(oldAttack == fighter.getSuperAttacks().get(i).getName())
			    			  oldAttackX = fighter.getSuperAttacks().get(i);
			    	   }
			    	   }
			    	else
			    		oldAttackX = null;
			    	
			    	// new Attack
			    	SuperAttack newAttackX = (SuperAttack) game.getAttacks().get(0);
			    	for(int i = 0;i<game.getPlayer().getSuperAttacks().size();i++){
			    		if(newAttack == game.getPlayer().getSuperAttacks().get(i).getName())
			    			newAttackX = game.getPlayer().getSuperAttacks().get(i); 
			    	}
			    	
			    	try {
						game.getPlayer().assignAttack(fighter, newAttackX, oldAttackX);
					} catch (MaximumAttacksLearnedException e1) {
						JOptionPane.showMessageDialog(assignAttackView, e1.getMessage());
					} catch (DuplicateAttackException e1) {
						JOptionPane.showMessageDialog(assignAttackView, e1.getMessage());
					} catch (NotASaiyanException e1) {
						JOptionPane.showMessageDialog(assignAttackView, e1.getMessage());
					}
			    	world.getActiveFighterInfo().setText(world.getActiveFighterInfo(game.getPlayer().getActiveFighter()));
					gameview.setContentPane(world.getContentPane());}
			    else
			    	JOptionPane.showMessageDialog(assignAttackView, "The player has not Super Attacks");
			}
			
			if(assignAttackView !=null &&test == assignAttackView.getUltimateAttack()){
			   if(game.getPlayer().getUltimateAttacks().size() !=0)
			    {PlayableFighter fighter = game.getPlayer().getActiveFighter();
			     String oldAttack = (String) assignAttackView.getFighterUltimateAttacks().getSelectedItem();
				 String newAttack = (String) assignAttackView.getPlayerUltimateAttacks().getSelectedItem();
				 // old attack
				 UltimateAttack oldAttackX = (UltimateAttack) game.getAttacks().get(52);
				 if(oldAttack != "None"){
				   for(int i =0;i< fighter.getUltimateAttacks().size();i++){
					   if(oldAttack == fighter.getUltimateAttacks().get(i).getName())
					    	  oldAttackX = fighter.getUltimateAttacks().get(i);
				    }
			     }
				 else
				    	oldAttackX = null;
				
				// new attack
				UltimateAttack newAttackX = (UltimateAttack) game.getAttacks().get(52);
				for(int i =0;i<game.getPlayer().getUltimateAttacks().size();i++){
					if(newAttack == game.getPlayer().getUltimateAttacks().get(i).getName())
						newAttackX = game.getPlayer().getUltimateAttacks().get(i);
				}
				
		    	try {
					game.getPlayer().assignAttack(fighter, newAttackX, oldAttackX);
				} catch (MaximumAttacksLearnedException e1) {
					JOptionPane.showMessageDialog(assignAttackView, e1.getMessage());
				} catch (DuplicateAttackException e1) {
					JOptionPane.showMessageDialog(assignAttackView, e1.getMessage());
				} catch (NotASaiyanException e1) {
					JOptionPane.showMessageDialog(assignAttackView, e1.getMessage());
				}
		    	world.getActiveFighterInfo().setText(world.getActiveFighterInfo(game.getPlayer().getActiveFighter()));
				gameview.setContentPane(world.getContentPane());}
			   
			   else
					JOptionPane.showMessageDialog(assignAttackView, "The Player did not learn Ultimate Attacks yet");
			}
			
			
	   }
			// Battle View
			if(battleView != null)
			{if(test == battleView.getBlock()){
				battle.block();
				battleView.getMeInfo().setText(battleView.MeInfo((PlayableFighter) battle.getMe()));
				battleView.getFoeInfo().setText(battleView.foeInfo((NonPlayableFighter) battle.getFoe()));
				gameview.validate();
				gameview.repaint();
			}
			if(test == battleView.getUse()){
				try {
					battle.use(game.getPlayer(), Collectible.SENZU_BEAN);
					battleView.getMeInfo().setText(battleView.MeInfo((PlayableFighter) battle.getMe()));
					battleView.getFoeInfo().setText(battleView.foeInfo((NonPlayableFighter) battle.getFoe()));
					gameview.validate();
					gameview.repaint();
				} catch (NotEnoughSenzuBeansException e1) {
					JOptionPane.showMessageDialog(battleView, e1.getMessage());
				}
			}
			
			//Physical Attack
			if(test == battleView.getPhysicalAttack()){
				try {
					battle.attack(new PhysicalAttack());
					battleView.getMeInfo().setText(battleView.MeInfo((PlayableFighter) battle.getMe()));
					battleView.getFoeInfo().setText(battleView.foeInfo((NonPlayableFighter) battle.getFoe()));
					gameview.validate();
					gameview.repaint();
				} catch (NotEnoughKiException e1) {
					JOptionPane.showMessageDialog(battleView, e1.getMessage());
				}
			}
			
			//SuperAttack
			if(test == battleView.getSuperAttack()){
			  try{
				ArrayList<SuperAttack> superAttacks = ((Fighter) battle.getMe()).getSuperAttacks();
				String attackName = (String) battleView.getSuperAttackList().getSelectedItem();
				for(int i = 0;i<superAttacks.size();i++){
					if(attackName == superAttacks.get(i).getName()){
						battle.attack(superAttacks.get(i));
					}
				}
				battleView.getMeInfo().setText(battleView.MeInfo((PlayableFighter) battle.getMe()));
				battleView.getFoeInfo().setText(battleView.foeInfo((NonPlayableFighter) battle.getFoe()));
				battleView.validate();
				battleView.repaint();
				
			}
			  catch(NotEnoughKiException e1){
				  JOptionPane.showMessageDialog(battleView, e1.getMessage());
			  }
			}
			
			// Ultimate Attack
			if(test == battleView.getUltimateAttack()){
			  try{	
				ArrayList<UltimateAttack> ultimateAttacks = ((Fighter) battle.getMe()).getUltimateAttacks();
				String attackName =(String) battleView.getUltimateAttackList().getSelectedItem();
				for(int i=0;i<ultimateAttacks.size();i++){
                    if(attackName == ultimateAttacks.get(i).getName()){
                    	battle.attack(ultimateAttacks.get(i));
                    }
				}
				battleView.getMeInfo().setText(battleView.MeInfo((PlayableFighter) battle.getMe()));
				battleView.getFoeInfo().setText(battleView.foeInfo((NonPlayableFighter) battle.getFoe()));
				gameview.validate();
				gameview.repaint();
				
			  }
			  catch(NotEnoughKiException e1){
				  JOptionPane.showMessageDialog(battleView, e1.getMessage());
			  }
			  
			}
			  
			}
			
			//dragon control
			  if(dragonView != null)
			  {if(test == dragonView.getSenzuBean()){
				  game.getPlayer().chooseWish(new DragonWish(dragon,DragonWishType.SENZU_BEANS,dragon.getSenzuBeans()));
				  world.getPlayerInfo().setText(world.getPlayerInfo(game.getPlayer()));
				  world.getActiveFighterInfo().setText(world.getActiveFighterInfo(game.getPlayer().getActiveFighter()));
				  gameview.setContentPane(world.getContentPane());
				  gameview.validate();
				  gameview.repaint();
			  }
			  
			  if(test == dragonView.getAbilityPoints()){
				  game.getPlayer().chooseWish(new DragonWish(dragon,DragonWishType.ABILITY_POINTS,dragon.getAbilityPoints()));
				  world.getPlayerInfo().setText(world.getPlayerInfo(game.getPlayer()));
				  world.getActiveFighterInfo().setText(world.getActiveFighterInfo(game.getPlayer().getActiveFighter()));
				  gameview.setContentPane(world.getContentPane());
				  gameview.validate();
				  gameview.repaint();
			  }
			  
			  if(test == dragonView.getSuperAttack()){
				 int randomAttack = new Random().nextInt(dragon.getSuperAttacks().size());
				 SuperAttack attack = dragon.getSuperAttacks().get(randomAttack);
				 game.getPlayer().chooseWish(new DragonWish(dragon,DragonWishType.SUPER_ATTACK,attack));
				 world.getPlayerInfo().setText(world.getPlayerInfo(game.getPlayer()));
				 world.getActiveFighterInfo().setText(world.getActiveFighterInfo(game.getPlayer().getActiveFighter()));
				 gameview.setContentPane(world.getContentPane());
				 gameview.validate();
				 gameview.repaint();
			  }
			  
			  if(test == dragonView.getUltimateAttack()){
				  int randomAttack = new Random().nextInt(dragon.getUltimateAttacks().size());
				  UltimateAttack attack = dragon.getUltimateAttacks().get(randomAttack);
				  game.getPlayer().chooseWish(new DragonWish(dragon, DragonWishType.ULTIMATE_ATTACK,attack));
				  world.getPlayerInfo().setText(world.getPlayerInfo(game.getPlayer()));
				  world.getActiveFighterInfo().setText(world.getActiveFighterInfo(game.getPlayer().getActiveFighter()));
				  gameview.setContentPane(world.getContentPane());
				  gameview.validate();
				  gameview.repaint();
			  
			  }}
	}
		
	

	@Override
	public void onDragonCalled(Dragon dragon) {
     this.dragon = dragon;
	 dragonView = new DragonView(dragon);
     dragonView.getAbilityPoints().addActionListener(this);
     dragonView.getSenzuBean().addActionListener(this);
     dragonView.getSuperAttack().addActionListener(this);
     dragonView.getUltimateAttack().addActionListener(this);
	 gameview.setContentPane(dragonView.getContentPane());	
	 gameview.repaint();
	 gameview.validate();
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
	
        if(collectible == Collectible.SENZU_BEAN)
        	JOptionPane.showMessageDialog(world, "You collected Senzu Bean");
        else
        	JOptionPane.showMessageDialog(world, "You collected Dragon Ball");
	   
    	world.getPlayerInfo().setText(world.getPlayerInfo(game.getPlayer()));
        gameview.validate();
        gameview.repaint();
	
	}

	@Override
	public void onBattleEvent(BattleEvent e) {
		battle = (Battle) e.getSource();
		
		if(e.getType() == BattleEventType.STARTED){
		  battleView = new BattleView(battle,game.getPlayer());
		  gameview.setContentPane(battleView.getContentPane());
		  battleView.getPhysicalAttack().addActionListener(this);
		  battleView.getSuperAttack().addActionListener(this);
		  battleView.getUltimateAttack().addActionListener(this);
		  battleView.getUse().addActionListener(this);
		  battleView.getBlock().addActionListener(this);
		  gameview.validate();
		  gameview.repaint();}
		if(e.getType() == BattleEventType.ENDED){
		      System.out.println(game.getWorld());
			  world = new WorldView(game.getWorld(),game.getPlayer());
		      world.getCreateNewFighter().addActionListener(this);
		      world.getUpgradeFighter().addActionListener(this);
		      world.getActiveFighter().addActionListener(this);
		      world.getCreateNewFighter().addActionListener(this);
		      world.getAssignAttack().addActionListener(this);
		      world.getMoveUp().addActionListener(this);
		      world.getMoveDown().addActionListener(this);
		      world.getMoveRight().addActionListener(this);
		      world.getMoveLeft().addActionListener(this);
		      gameview.setContentPane(world.getContentPane());
		      gameview.validate();
		}
		if(e.getType() == BattleEventType.NEW_TURN){
			battleView.newTurn(battle);
			battleView.validate();
			gameview.validate();
			gameview.repaint();
		}

	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Gameview getGameview() {
		return gameview;
	}

	public void setGameview(Gameview gameview) {
		this.gameview = gameview;
	}
	public static void play() {
		  try {
		   File file = new File("Game-soundtrack.wav");
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.loop(100000);
		   clip.start();
		   Thread.sleep(clip.getMicrosecondLength());
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
		 }

	public static void main(String[] args) throws MissingFieldException,
			UnknownAttackTypeException {
		GameGui game = new GameGui();
		

	}

}
