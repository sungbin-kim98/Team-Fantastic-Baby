//Team Fantastic Baby -- Leo Au-Yeung, Sungbin Kim
//Vulpix.java
package Pokedex;

public class Vulpix extends Pokemon {
	
	//Constructor
    public Vulpix() {
		super("Vulpix", 5, 16,"FIRE");
		addMove("Ember");
		addMove("Fire Fang");
	}
	
	//Constructor for evolutions
	public Vulpix( String n, int lvl, int eLvl ) {
		super( n, lvl, eLvl, "FIRE" );
		addMove("Ember");
		addMove("Fire Fang");
	}
	
    public String about() {
		return "Made by Leo Au-Yeung and Sungbin Kim";
	}
	
}