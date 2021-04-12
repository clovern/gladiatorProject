package project3;

/**
 * Player class
 * A player is a type of creature which can gain XP and levels. 
 * 
 * @author Nicole Dudas
 * @version Winter 2021
 * 
 * */

import java.util.ArrayList;

public class Player extends Creature {
    private int XP;

    /**
     * Constructor for player. Gives starting values for strength, health, XP. 
     * @param int strength - the starting strength value of the player
     * @param int health - the starting health value of the player. This will be their starting
     * max health as well
     * @throws IllegalArgumentException- thrown through the super if strength or health are less than 0
     */
    public Player(int strength, int health){
        super(strength, health);
        XP = 0;
    }

    /**
     * add an item into the linked list at a specified index
     * @param int amt- the amount of XP to add to the player's current XP
     * @throws IllegalArgumentException- thrown when amt is negative
     */
    public void addXP(int amt){
        if (amt < 0){
            throw new IllegalArgumentException("addXP amt parameter must be greater than or equal to 0");
        }
        XP += amt;
    }

    /**
     * The current XP amount held by the player
     * @return XP
     */
    public int getXP(){
        return XP;
    }

    /**
     * returns true if the players XP is 100 or more (at which point they can level up)
     * @return boolean - true if XP is 100 or more, otherwise false
     */
    public boolean canLevelUp(){
        if (XP >= 100){
            return true;
        }
        return false;
    }

    /**
     * Uses 100 XP to "level up" a player. This increases strength by 5, health by 10, and gives a new
     * spell
     * @param type - the spell type to give the player
     */
    public void levelUp(SpellType type){
        XP -= 100;
        setStrength(getStrength() + 5);
        setMaxHealth(getMaxHealth() + 10);
        setHealth(getMaxHealth());
        giveSpell(type);
    }

    /**
     * returns an arrayList holding the spellTypes that a player can cast. 
     * @return ArrayList<SpellType> castType - the types of spells that a player can cast
     */
    public ArrayList<SpellType> getCastTypes(){
        return castType;
    }
}
