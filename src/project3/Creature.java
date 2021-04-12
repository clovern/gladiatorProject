package project3;

/**
 * Creature Class
 * Creatures creature objects which have health, strength, maxHealth, and various
 * spell abilities.
 * 
 * @author Nicole Dudas
 * @version Winter 2021
 * 
 * */

import java.util.ArrayList;
import java.util.Random;

public class Creature {
    private int health;
    private int maxHealth;
    private int strength;
    private boolean spellCaster = false;
    protected ArrayList<SpellType> castType;
    private int freezeTimer = 0;
    private int fireTimer = 0; 

    /**
     * Creature constructor
     * @param strength - the starting strength of the creature
     * @param health - the starting health of the creature. This is also the creature's max health. 
     * @throws IllegalArgumentException- thrown when strength or health parameter are 0 or less
     */
    public Creature(int strength, int health){
        if (strength <= 0 || health <= 0){
            throw new IllegalArgumentException("parameters in Creature constructor must be > 0");
        }
        this.health = health;
        this.strength = strength;
        this.castType = new ArrayList<SpellType>();
        this.maxHealth = health;
    }

    /**
     * sets the strength value of a creature to the parameter value
     * @param strength - the new strength of the creature
     * */
    public void setStrength(int strength){
        this.strength = strength;
    }

    /**
     * changes the health value of a creature
     * @param health - the new health value of the creature
     */
    public void setHealth(int health){
        this.health = health;
    }

    /**
     * returns the current health of the creature
     * @return int health- creature's current health
     */
    public int getHealth(){
        return health;
    }

    /**
     * sets a new Max health value for the creature
     * @param maxHealth - the new maximum health of the creature
     */
    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth; 
    }

    /**
     * returns the creature's current max health value
     * @return maxHealth - the creature's current max health
     */
    public int getMaxHealth(){
        return maxHealth;
    }

    /**
     * returns the creature's current strength
     * @return strength - the creature's current strength
     */
    public int getStrength(){
        return strength;
    }

    /**
     * returns the current freezeTimer value. This indicates that the creature is frozen if it is
     * greater than 0.
     * @return int - freezeTimer
     */
    public int getFreezeTimer(){
        return freezeTimer;
    }

    /**
     * returns the current fireTimer value. This indicates that the creature is on fire if it is 
     * greater than 0. 
     * @return int - fireTimer
     */
    public int getFireTimer(){
        return fireTimer;
    }

    /**
     * changes the value of the freeze timer
     * @param amt- the new value of the freeze timer
     * @throws IllegalArgumentException- thrown if amt parameter is less than 0. 
     */
    public void setFreezeTimer(int amt){
        if (amt < 0){
            throw new IllegalArgumentException("Freeze timer must be 0 or greater");
        }
        freezeTimer = amt;
    }

    /**
     * changes the value of the fireTimer
     * @param amt- the new value of the fireTimer
     * @throws IllegalArgumentException- thrown if amt parameter is less than 0. 
     */
    public void setFireTimer(int amt){
        if (amt < 0){
            throw new IllegalArgumentException("Fire timer must be 0 or greater");
        }
        fireTimer = amt; 
    }

    /**
     * selects a random value between 0 and teh strength of the creature and returns that value. 
     * @return int- the attack strength of the creature's current attack
     */
    public int attack(){
        if (getStrength() == 0){
            return 0;
        }
        else{
            Random ran = new Random();
            int attackStrength = ran.nextInt(strength) + 1;
            return attackStrength;
        }
    }

    /**
     * returns true if the creature is able to cast any spells
     * @return boolean- true is the creature is able to cast any spells, false otherwise
     */
    public boolean getSpellCaster(){
        return spellCaster;
    }

    /**
     * returns the first spellType in the castType array. (Since monster's in Gladiator game are
     * only ever given one SpellType, this returns their only casting type. Player's have a
     * different function which returns all of their casting types, since they can have multiple)
     * @return SpellType - the first spell type in the castType array
     */
    public SpellType getCastType(){
        if (castType.size() > 0){
            return castType.get(0);
        }
        return null;
    }

    /**
     * decrements the damage amount from the creature's health
     * @param damage -the amount to decrement from health
     */
    public void hurt(int damage){
        health -= damage;
    }

    /**
     * returns true if the creatures health is 0 or less
     * @return boolean - true if the creatures health is 0 or less, false otherwise
     */
    public boolean isDead(){
        return health <= 0; 
    }

    /**
     * gives a creature a new spellType that they can cast by adding it to the castType array. 
     * @param SpellType type - the new spell ability to give the creature
     */
    public void giveSpell(SpellType type){
        castType.add(type);
        spellCaster = true;
    }

    /**
     * indicates if the creature can cast a specific SpellType
     * @param SpellType type - the specific SpellType to check
     * @return boolean- true if the creature can cast the given spell Type, false otherwise
     */
    public boolean canCast(SpellType type){
        if (spellCaster == true){
            if (castType.contains(type)){
                return true;
            }
        }
        return false;
    }

    /**
     * keeps the target creature from being able to attack or cast spells for 2-4 turns by changing
     * the freeze timer to value 2-4. 
     * @param Creature target - the creature to freeze
     */
    public void freeze(Creature target){
        Random ran = new Random();
        int numTurns = ran.nextInt(3) + 2;
        target.setFreezeTimer(numTurns);
        castType.remove(SpellType.Frost);
        if (castType.size() == 0){
            spellCaster = false;
        }
    }

    /**
     * returns true if the creature has any turns left in their freezeTimer, indicating that
     * they are frozen
     * @return boolean - true if freezeTimer is greater than 0, false otherwise
     */
    public boolean isFrozen(){
        return freezeTimer > 0;
    }

    /**
     * decreases the freeze timer by 1, indicating that one turn has passed
     */
    public void decFreezeTimer(){
        if (freezeTimer > 0){
            freezeTimer--;
        }
    }

    /**
     * deals 35 instant damage to another target creature
     * @param Creature target - the creature to damage
     */
    public void strikeWithLightning(Creature target){
        target.hurt(35);
        castType.remove(SpellType.Lightning);
        if (castType.size() == 0){
            spellCaster = false;
        }
    }

    /**
     * deals 20 damage to another creature each turn for 2-4 turns
     * @param Creature target -the creature to damage
     */
    public void setOnFire(Creature target){
        Random ran = new Random();
        int numTurns = ran.nextInt(3) + 2;

        target.setFireTimer(numTurns);
        castType.remove(SpellType.Fire);
        if (castType.size() == 0){
            spellCaster = false;
        }
    }

    /**
     * indicates if the creature still takes burn damage from being on fire this turn by telling
     * if the fireTimer is greater than 0 or not
     * @return boolean - fireTimer
     */
    public boolean isOnFire(){
        return fireTimer > 0;
    }

    /**
     * decreases the fireTimer by 1, indicating that another turn has passed with the creature
     * on fire
     */
    public void decFireTimer(){
        if (fireTimer > 0){
            fireTimer--;
        }
    }

    /**
     * increases the creature's own health by the given amt, or up to their max health if the healing
     * would exceed this creature's max health
     * @int amt - the amount of health to add
     * @throws IllegalArgumentException - thrown if amt parameter is less than 0
     */
    public void heal(int amt){
        if (amt < 0){
            throw new IllegalArgumentException("heal amt parameter must be >= 0");
        }
        if ((health + amt) > maxHealth){
            health = maxHealth;
        }
        else{
            health += amt;
        }
        castType.remove(SpellType.Heal);
        if (castType.size() == 0){
            spellCaster = false;
        }
    }
}
