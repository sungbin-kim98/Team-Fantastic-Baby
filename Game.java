//Team Fantastic Baby -- Leo Au-Yeung, Sungbin Kim
//Game.java

import java.util.ArrayList;
import Pokedex.*;
//Credits to http://www.pokemon.com/ for Pokedex descriptions of all available Pokemon in our game!

public class Game {
	
    private ArrayList<Pokemon> _Pokemon = new ArrayList<Pokemon>();
    private ArrayList<Pokemon> _PokemonEnemy = new ArrayList<Pokemon>(); //Pokemon of enemy; can be reused whenever trainers battle **
    private int selectedPokemon;
    private Pokemon captured, currentPokemon, enemyPokemon;
    //captured: create a new pokemon() whenever a pokemon is captured, and set it equal to this then add captured pokemon to list _Pokemon
    //currentPokemon: _Pokemon.get(selectedPokemon)
    //enemyPokemon: create a new pokemon() whenever user enters a battle. if trainer battle, change enemyPokemon whenever an enemy faints
    Battle battle = new Battle();    
    private boolean battleMode = false;
    private boolean opponentTurn = false; //this boolean will only get changed within a Pokemon battle
	
    public void startupMsg() {
	//startup message from original Pokemon games!
		
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
	//***ADJUST WAIT TIMES LATER***
			
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		
	System.out.println("\nHello there! It's a pleasure to meet you!");
	System.out.println("\nWelcome to the world of Pokemon!");
	System.out.println("\nMy name is Oak, but everyone just calls me the Pokemon Professor.");
	System.out.println("\nThis world is widely inhabited by creatures known as Pokemon.");
	System.out.println("\nWe humans live alongside Pokemon as friends.");
	System.out.println("At times we play together, and at other times we work together.");
	System.out.println("Some people use their Pokemon to battle and develop close bonds with them.\n");
	System.out.println("And what do I do? I conduct research so that we may learn more about Pokemon.");
	System.out.println("Now, why don't you tell me a little bit about yourself?\n");
    }
	
    //method to wait a # of milliseconds; used mainly for recreating feel of actual game
    public void waitMS( int milliseconds ) {
	try { Thread.sleep( milliseconds ); } 
	catch (InterruptedException ex) {Thread.currentThread().interrupt();}
    }
	
    //~~~~~~~~~~~~~~~PROMPTS~~~~~~~~~~~~~~~~~~~~~~~
	
    //method to prompt gender of player
    public String promptGender() {
	String gen = "";
	System.out.println("Tell me, are you a boy? Or are you a girl?");
	//ADD CASE WHERE INPUT IS INVALID
	gen = Keyboard.readString();
	return gen;
    }
	
    //method to prompt name of player
    public String promptName() {
	String name = "";
	System.out.println("All right. Tell me, what is your name?");
	name = Keyboard.readString();
	//restricts name to 12 characters; can change if necessary
	if (name.length() > 15) {
	    name = name.substring(0,16);
	}
	return name;
    }
	
    //method to prompt player to pick his/her starter Pokemon
    public int promptStarter() {
	int pkmn = 0;
	System.out.println("In this world of Pokemon, you'll need a partner Pokemon to be with you.");
	System.out.println("Tell me, which Pokemon would you like to be your partner?");
	System.out.println("1. Bulbasaur\t2. Charmander\t3. Squirtle");
	pkmn = Keyboard.readInt();
	//ADD CASE WHERE INT IS NOT 1-3 OR IS INVALID
	return pkmn;
    }
	
    //method to prompt user for a command
    public String promptControl() {
	String ctrl = "";
	System.out.print("Enter a control: ");
	ctrl = Keyboard.readString();
	ctrl = ctrl.toLowerCase();
	return ctrl;
    }
	
    //~~~~~~~~~~~~~~~DISPLAYS~~~~~~~~~~~~~~~~~~~~~~~
	
    //method to display available commands depending on in battle or not
    public void displayCommands() {
	//LIST OF CONTROLS: http://pokemonessentials.wikia.com/wiki/Controls
	//SETTING POKEMON CONTROLS: http://pokemonessentials.wikia.com/wiki/Tutorial:Set_the_Controls_Screen
	String commands = "";	    	
	if (battleMode == false) {
	    System.out.println("============================================================");            commands = "W:UP " + "S:DOWN " + "A:LEFT " + "D:RIGHT " + "X:INTERACT ";
	}
	else if (battleMode == true) {
	    Battle.reset();
	    Battle.set( 5, 0, "     WHAT WILL " + Player.getName() + " DO? " );
	    Battle.set( 7, 0, "  -----------  -----------  " );
	    Battle.set( 8, 0, "  | 1:FIGHT |  |  2:BAG  |  " );
	    Battle.set( 9, 0, "  -----------  -----------  " );
	    Battle.set( 12, 0, "  -----------  -----------  " );
	    Battle.set( 13, 0, "  |3:POKEMON|  |  4:RUN  |  " );
	    Battle.set( 14, 0, "  -----------  -----------  " );
	}
	System.out.println(commands);
    }
	
    //method to turn HP into a bar
    public String displayHP( Pokemon p ) {
	double eachBar = ( (p.getMaxHP())/20 ); // this is how much hp each bar is worth
	int numBar = (int)((p.getCurrentHP())/eachBar); // number of bars displayed
	String bar = "HP[";
	for( int n = 0; n < numBar; n++ ) {
	    bar += "|";
	}
	for( int n = 0; n < (20-numBar); n++ ) {
	    bar += " ";
	}
	bar += "]";
	return bar;
    }
	
    //method to display Player's 6 captured Pokemon
    public void displayPokemon() {
	Battle.reset();
	Battle.set( 0, 0, "          AVAILABLE POKEMON:       //BACK" );
	Battle.set( 1, 0, "-----------------------------------------" );
	Battle.set( 2, 0, "(1) Lv." + _Pokemon.get(0).getLevel() + "/" + _Pokemon.get(0).getName() + "/" + _Pokemon.get(0).getType() );
	Battle.set( 3, 0, displayHP( _Pokemon.get(0) ) );
	Battle.set( 4, 0, "-----------------------------------------" );
	Battle.set( 5, 0, "(2) Lv." + _Pokemon.get(1).getLevel() + "/" + _Pokemon.get(1).getName() + "/" + _Pokemon.get(1).getType() );
	Battle.set( 6, 0, displayHP( _Pokemon.get(1) ) );
	Battle.set( 7, 0, "-----------------------------------------" );
	Battle.set( 8, 0, "(3) Lv." + _Pokemon.get(2).getLevel() + "/" + _Pokemon.get(2).getName() + "/" + _Pokemon.get(2).getType() );
	Battle.set( 9, 0, displayHP( _Pokemon.get(2) ) );
	Battle.set( 10, 0, "-----------------------------------------" );
	Battle.set( 11, 0, "(4) Lv." + _Pokemon.get(3).getLevel() + "/" + _Pokemon.get(3).getName() + "/" + _Pokemon.get(3).getType() );
	Battle.set( 12, 0, displayHP( _Pokemon.get(3) ) );
	Battle.set( 13, 0, "-----------------------------------------" );
	Battle.set( 14, 0, "(5) Lv." + _Pokemon.get(4).getLevel() + "/" + _Pokemon.get(4).getName() + "/" + _Pokemon.get(4).getType() );
	Battle.set( 15, 0, displayHP( _Pokemon.get(4) ) );
	Battle.set( 16, 0, "-----------------------------------------" );
	Battle.set( 17, 0, "(6) Lv." + _Pokemon.get(5).getLevel() + "/" + _Pokemon.get(5).getName() + "/" + _Pokemon.get(5).getType() );
	Battle.set( 18, 0, displayHP( _Pokemon.get(5) ) );
	Battle.set( 19, 0, "-----------------------------------------" );
    } // ends displayPokemon()
	
    //~~~~~~~~~~EXECUTE-CONTROLS~~~~~~~~~~~~~~~
	
    //executes command
    public void executeControl( String command ) {
		
	//NON-BATTLE COMMANDS
		
	if ( battleMode == false ) {
	    if ( command.toLowerCase().equals("w") ) { 
		if( Player.getY() != 0 ) {
		    Map.get( Player.getY(), Player.getX() ).setIsPlayerHere(); //resets old location
		    Map.get( Player.getY(), Player.getX() ).setImage();
		    Player.setY(Player.getY() - 1);
		    Map.get( Player.getY(), Player.getX() ).setIsPlayerHere(); //moves to new location
		    Map.get( Player.getY(), Player.getX() ).setImage();		    
		}
		//user.setX, user.setY, change map's player coordinates
		if( (int)(Math.random()*15) == 1 ) {
		    battleMode = true;
		    enemyPokemon = new Rattata();
		    enemyPokemon.setWild( true );		    
		}
	    }
	    if ( command.toLowerCase().equals("a") ) {
		if( Player.getX() != 0 ) {
		    Map.get( Player.getY(), Player.getX() ).setIsPlayerHere(); //resets old location
		    Map.get( Player.getY(), Player.getX() ).setImage();
		    Player.setX(Player.getX() - 1);
		    Map.get( Player.getY(), Player.getX() ).setIsPlayerHere(); //moves to new location
		    Map.get( Player.getY(), Player.getX() ).setImage();		    
		}
		//user.setX, user.setY, change map's player coordinates
		if( (int)(Math.random()*15) == 1 ) {
		    battleMode = true;
		    enemyPokemon = new Rattata();
		    enemyPokemon.setWild( true );		    
		}
	    }
	    if ( command.toLowerCase().equals("s") ) {
		if( Player.getY() != 19 ) {
		    Map.get( Player.getY(), Player.getX() ).setIsPlayerHere(); //resets old location
		    Map.get( Player.getY(), Player.getX() ).setImage();
		    Player.setY(Player.getY() + 1);
		    Map.get( Player.getY(), Player.getX() ).setIsPlayerHere(); //moves to new location
		    Map.get( Player.getY(), Player.getX() ).setImage();		    
		}
		//user.setX, user.setY, change map's player coordinates
		if( (int)(Math.random()*15) == 1 ) {
		    battleMode = true;
		    enemyPokemon = new Rattata();
		    enemyPokemon.setWild( true );		    
		}
	    }
	    if ( command.toLowerCase().equals("d") ) {
		if( Player.getX() != 19 ) {
		    Map.get( Player.getY(), Player.getX() ).setIsPlayerHere(); //resets old location
		    Map.get( Player.getY(), Player.getX() ).setImage();
		    Player.setX(Player.getX() + 1);
		    Map.get( Player.getY(), Player.getX() ).setIsPlayerHere(); //moves to new location
		    Map.get( Player.getY(), Player.getX() ).setImage();		    
		}
		//user.setX, user.setY, change map's player coordinates
		if( (int)(Math.random()*15) == 1 ) {
		    battleMode = true;
		    enemyPokemon = new Rattata();
		    enemyPokemon.setWild( true );
		}
	    }
	    if ( command.toLowerCase().equals("x") ) {
		//check block in front using player.getDirection()
		//we will have: trees, rocks, walls, enemy trainers
	    }
	}//ends NON-BATTLE COMMANDS
		
	//BATTLE COMMANDS
		
	else if ( battleMode == true ) {
		
	    // *** FOR ALL EXCEPT RUN, HAVE A "B: Back" IN THE CORNER OF DISPLAY TO ALLOW USER TO GO BACK ***
			
	    if ( command.toLowerCase().equals("1") ) { //FIGHT
		//DISPLAY SELECTED POKEMON'S MOVES
		String temp = promptControl(); //prompt user to choose move (1-4)
		int move = Integer.parseInt( temp ); //converts string input to int
		//make selected pokemon from _pokemon use method attack on enemy pokemon
	    }
	    if ( command.toLowerCase().equals("2") ) { //BAG
		//DISPLAY BAG: Pkballs, potions, battle items (NO KEY ITEMS)
		//prompt user for another input to choose what to do
	    }
	    if ( command.toLowerCase().equals("3") ) { //POKEMON
		displayPokemon(); //displays pkmn 1-6 with name + health
	        System.out.println( battle );
		String temp = promptControl(); //prompt user to pick which to choose (1-6)
		selectedPokemon = Integer.parseInt( temp ); // converts the string to the int
		//can also type "BACK" to go back
	    }
	    if ( command.toLowerCase().equals("4") ) { //RUN
		if ( enemyPokemon.getWild() == false ) { System.out.println("You cannot run from a trainer battle..."); }
		else if (Math.random() * 100 < 50) {
		    //if wild pkmn, chance of running = http://bulbapedia.bulbagarden.net/wiki/Escape#Success_conditions
		    System.out.println( "You have successfully escaped!" );
		    waitMS(1000);
		    battleMode = false;
		}
		else {
		    System.out.println( "You have failed to run away..." );
		    waitMS(1000);
		    opponentTurn = true;
		}
	    }
	}//ends BATTLE COMMANDS
		
    }
	
    public void opponentBattle() {
	enemyPokemon.attack( currentPokemon, 0 );
	opponentTurn = false;
    }

    /**
       public boolean battleStart() {
       //check if player is on grass; chance to start battle
       }
    **/
	
    //~~~~~~~~~~~~~~PLAYING POKEMON!!!~~~~~~~~~~~~~~~
	
    public void play() {
	//startup prompts; only prompts user once
	startupMsg();
	String gender = promptGender();
	String name = promptName();
		
	//instantiation of classes
	Player user = new Player(gender, name);
	Map userMap = new Map();


	//sets up _Pokemon ArrayList
	for( int i = 0; i < 6; i++ ) {
	    _Pokemon.add( new Default() );
	}
		
	//prompts for starter Pokemon, and creates one accordingly
	int starter = promptStarter();
	if (starter == 1) { captured = new Bulbasaur(); }
	else if (starter == 2) { captured = new Charmander(); }
	else if (starter == 3) { captured = new Squirtle(); }
	_Pokemon.set( 0, captured ); //adds starter to list _Pokemon
	selectedPokemon = 0;
	currentPokemon = _Pokemon.get(selectedPokemon);
		
	//RUNS GAME:
	while ( user.getQuest() != 20 ) {
			
	    if ( battleMode == false ) { //not in battle -> show map + map commands
		System.out.println( userMap );
		displayCommands();
	    }
	    else if ( battleMode == true ) { 
		displayCommands();
		System.out.println( battle ); 
	    } //in battle -> show battle
			
	    String control = promptControl(); //prompts user for a command
	    executeControl( control );
			
	    
	    //   if (battleMode == false) { //after a player walks
	    //  battleMode = battleStart(); //checks if player is on grass & chance to start battle
	    //  // shouldnt this be a part of battleStart() that modifies _EnemyPokemon
	    //  }
	    //made it like this so that when a battle starts, enemy gets instantiated
	    
			
	    //if ( opponentTurn == true) { //opponentBattle(); }
			
	}
    }
	
    public static void main (String[] args) {
	Game POKEMON = new Game();
	POKEMON.play();
    }
	
}
