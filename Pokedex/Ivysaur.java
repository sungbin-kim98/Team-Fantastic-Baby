//Team Fantastic Baby -- Leo Au-Yeung, Sungbin Kim
//Ivysaur.java
package Pokedex;

public class Ivysaur extends Bulbasaur {
	
	//Constructor
	public Ivysaur() {
		super( "Ivysaur", 16, 32 );
		addMove("Magical Leaf");
	}
	
	//Constructor for trainers
	public Ivysaur( int lvl ) {
		this();
		level = lvl;
	}
	
	//Constructor for evolutions
	public Ivysaur( String n, int lvl, int eLvl, int xp) {
		super( n, lvl, eLvl );
		exp = xp;
		addMove("Magical Leaf");
	}
	
	//Pokedex description
	public String about() {
		return "Ivysaur";
	}
	
}