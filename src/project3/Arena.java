package project3;

/**
 * Arena Class
 * Provides functionality of Gladiator game and text interface for User to play. 
 * 
 * @author Nicole Dudas
 * @version Winter 2021
 * 
 * */

import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Arena {
    private static int highScore = 0;
    private Queue<Creature> q1;
    private Queue<Creature> q2;
    private Queue<Creature> q3;
    private Queue<Creature> q4;
    private Player p1;
    private int turnCounter;

    /***********************************************************************************
     * Constructor for Arena Class. Initializes creatures and player. 
     */
    public Arena(){
        q1 = new Queue<Creature>();
        q2 = new Queue<Creature>();
        q3 = new Queue<Creature>();
        q4 = new Queue<Creature>();

        p1 = new Player(45, 130);
        turnCounter = 0;

        //starts the game out with one random monster in each queue
        Random ran = new Random();
        Creature c1 = new Creature(ran.nextInt(20) + 10, ran.nextInt(35) + 10);
        Creature c2 = new Creature(ran.nextInt(20) + 10, ran.nextInt(35) + 10);
        Creature c3 = new Creature(ran.nextInt(20) + 10, ran.nextInt(35) + 10);
        Creature c4 = new Creature(ran.nextInt(20) + 10, ran.nextInt(35) + 10);

        q1.enqueue(c1);
        q2.enqueue(c2);
        q3.enqueue(c3);
        q4.enqueue(c4);
    }

    /***********************************************************************************
     * Runs the game until the player either loses or wins, and keeps track of how many turns they survived. 
     */
    public void gameLoop(){

        GameInstructions();

        do{
            createMonster();
            playersTurn();
            monstersTurn();
            turnCounter++;
        }
        while (isGameOver() == false); 

        System.out.println("---------------------------------------------");
        System.out.println("Your Score: " + turnCounter);
        System.out.println("---------------------------------------------");
    }

    public void GameInstructions(){
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("WELCOME TO GLADIATOR!");
        System.out.println("A team of monsters is coming to attack from every direction!");
        System.out.println("Killing monsters helps you level up to reset health, increase max health and attack strength, and gain a spell ability");
        System.out.println("Spells are: ");
        System.out.println("\tLightning: deal 35 damage to an opponent");
        System.out.println("\tFreezing: keep an opponents from attacking for 2-4 turns");
        System.out.println("\tFire: burn an opponent for 20 damage for 2-4 turns");
        System.out.println("\tHealing: heals you for 100 health");
        System.out.println("Your spells can only be used once, so use them wisely!");
        System.out.println("A new monster is added nearly every round, and monsters get stronger as the game goes on");
        System.out.println("You will gain points for each round survived. How long will you last?? Good luck!");
        System.out.println("---------------------------------------------------------------------------------");
    }

    /***********************************************************************************
     * Returns true if the game is over. The game is over if all the monster queues are empty (meaning
     * there are no more enemies), or if the player has no health left. 
     * @return boolean - false if the game is over, true otherwise
     */
    public boolean isGameOver(){

        if (q1.isEmpty() == true && q2.isEmpty() == true && q3.isEmpty() == true && q4.isEmpty() == true){
            System.out.println("Congratulations, you beat all the monsters! You win!");
            return true;
        }
        if (p1.getHealth() <= 0){
            System.out.println("You died!");
            return true;
        }
        return false;
    }

    /***********************************************************************************
     * Creates a new monster with random power and health and assigns it to a random queue. 
     * Assigns a small percentage of monsters with a spell. 
     */
    public void createMonster(){

        //random number used to determine what the strength of the new monster will be
        Random ran = new Random();
        Creature c1 = new Creature(ran.nextInt(20) + 10 + 5*turnCounter, ran.nextInt(35) + 10 + 5*turnCounter);

        //a fraction of the created creatures are given a spell
        if ((ran.nextInt(100) + 1) < 15){
            SpellType type = SpellType.Frost;
            int x = ran.nextInt(4) + 1;

            switch (x){
                case 1:
                    type = SpellType.Frost;
                    break;
                case 2: 
                    type = SpellType.Fire;
                    break;
                case 3:
                    type = SpellType.Lightning;
                    break;
                case 4:
                    type = SpellType.Heal;
                    break;
            }
            c1.giveSpell(type);
        } 

        //the monster is randomly added to one of the 4 queues. There is a 20% chance a new monster is not added to any queues. 
        int x = ran.nextInt(5) + 1;
        switch(x){
            case 1:
            q1.enqueue(c1);
            break;
        case 2: 
            q2.enqueue(c1);
            break;
        case 3:
            q3.enqueue(c1);
            break;
        case 4:
            q4.enqueue(c1);
            break;
        case 5:
            break;
        }

        return;
    }

    /***********************************************************************************
     * Gives the player information on their current situation in the game, gathers information on the player's
     * next move, and then carries out the player's intended spell or attack. 
     */
    public void playersTurn(){

        if (!p1.isFrozen()){

            //initializes starting variables
            Scanner scn = new Scanner(System.in);
            System.out.println("\n----------------TURN " + (turnCounter + 1) + "-----------------");
            System.out.println("The enemies around you are:");
            Queue[] queues = {q1, q2, q3, q4};
            ArrayList<String> directions = new ArrayList<String>(Arrays.asList("n", "e", "s", "w"));
            ArrayList<String> directionsValid = new ArrayList<String>();

            //tells the player which direction enemies are attacking from
            for (int i = 0; i < queues.length; ++i){
                if (queues[i].isEmpty() == false){
                    directionsValid.add(directions.get(i));
                    Creature currentCreature = ((Creature) queues[i].peek());
                    System.out.print("\t" + directions.get(i).toUpperCase() + ": strength " + currentCreature.getStrength() + " monster w/ " + currentCreature.getHealth() + " health");
                    if (((Creature) queues[i].peek()).getSpellCaster() == true){
                        System.out.print(" that can cast");
                    }
                    System.out.print("\n");
                }
            }

            //determines if the player wants to attack or use a spell, and ensures a valid answer is input
            String answer1 = "a";
            if (p1.getSpellCaster() == true){
                System.out.println("\nWould you like to do a normal attack or a spell? Type a or s");
                ArrayList<String> validAnswers = new ArrayList<String>(Arrays.asList("s", "a"));
                answer1 = validateAnswers(validAnswers);
            }

            //if the player chooses a spell, determines which spell they will cast
            String answer3 = "";
            if (answer1.equals("s")){
                System.out.print("Current spells: ");
                ArrayList<String> possibleSpells = castTypeToString();
                System.out.println("\nType the name of the spell you would like to use.");
                answer3 = validateAnswers(possibleSpells);
            }

            if (!answer3.equals("healing")){
                //determines which direction the player wants to attack in
                System.out.println("\nWhich direction would you like to attack in? Direction options are: ");
                for (String i: directionsValid){
                    System.out.print(i.toUpperCase() + " ");
                }
                String answer2 = validateAnswers(directionsValid);

                //carries out the attack
                int directionIndex = directions.indexOf(answer2);
                
                Queue<Creature> queueToAttack = queues[directionIndex];
                Creature damagedCreature = queueToAttack.get(0);
                if (answer1.equals("a")){
                    int damageAmount = p1.attack();
                    damagedCreature.hurt(damageAmount);
                    System.out.println("You attacked to the " + answer2.toUpperCase() + " for " + damageAmount + " damage.");
                }
                else if (answer1.equals("s")){
                    SpellType spell = stringToSpellType(answer3);
                    creatureSpellCast(spell, damagedCreature, p1);
                    System.out.println("You used a " + spell + " spell on the monster to the " + answer2.toUpperCase() + ".");
                }

                //remove the target creature from the queue if the player's attack killed it
                monsterKillCheck(queueToAttack, damagedCreature);
            }
            else{
                creatureSpellCast(SpellType.Heal, p1, p1);
                System.out.println("You healed for 100 health. Current health: " + p1.getHealth());
            }
        }
        else{
            System.out.println("You can't attack, you are frozen!");
        }

        if (p1.isOnFire()){
            System.out.println("You are on fire! You take 20 damage");
            p1.setHealth(p1.getHealth() - 20);
        }
        
        p1.decFreezeTimer();
        p1.decFireTimer();
        if (p1.canLevelUp()){
            levelUpQuestions();
        }
    }

    /**
     * Checks if a creature has died, and adds XP to the player & removes it from it's queue if so. 
     * @param queue - the queue that the creature is from
     * @param creature - the creature to check
     */
    public void monsterKillCheck(Queue<Creature> queue, Creature creature){
        if (creature.isDead()){
            int XPgain = creature.getStrength() + creature.getMaxHealth();
            p1.addXP(XPgain);
            queue.dequeue();
            System.out.println("Monster dead - you gain " + XPgain  + " XP.");

            if (!queue.isEmpty()){
                Creature currentCreature = ((Creature) queue.peek());
                System.out.println("A monster has taken its place with stats: strength: " + currentCreature.getStrength() + ", health: " + currentCreature.getHealth());
                if (currentCreature.getSpellCaster() == true){
                    System.out.print(", can cast");
                }
            }
        }
    }

    /**
     * dialogue given when a player is ready to level up. 
     */
    public void levelUpQuestions(){
        System.out.println("--------------------------------------------------------------");
        System.out.println("You leveled up! Max health: " + (p1.getMaxHealth() + 10) + " Max Strength: " + (p1.getStrength() + 5) + ". Full health restored.");
        System.out.println("What spell would you like to gain? Options are: lightning, fire, frost, and healing.");
        String answer4 = validateAnswers(new ArrayList<String>(Arrays.asList("lightning", "fire", "frost", "healing")));
        p1.levelUp(stringToSpellType(answer4));
        System.out.println("--------------------------------------------------------------");
    }

    /***********************************************************************************
     * Ensures that all player provided inputs are valid, and asks for new input if they are not
     * @param ArrayList<String> validAnswers - valid inputs
     * @return String - a player input string that matched a valid input
     */
    public String validateAnswers(ArrayList<String> validAnswers){
        Scanner scn = new Scanner(System.in);
        String answer = scn.next().toLowerCase();
        boolean valid = false;
        while(valid == false){
            if (validAnswers.contains(answer)){
                valid = true;
            }
            else{
                System.out.print("Please enter a valid answer. Valid answers are: "); 
                for (String i: validAnswers){
                    System.out.print(i + ", ");
                }
                answer = scn.next().toLowerCase();
            }
        }
        return answer;
    }

    /***********************************************************************************
     * helper method for playerTurn which converts string to SpellType enum
     * @param spellString - player input of spell to cast
     * @return spellString - SpellType enum of spell to cast
     */
    public SpellType stringToSpellType(String spellString){
        switch(spellString){
            case "fire":
                return SpellType.Fire;
            case "lightning":
                return SpellType.Lightning;
            case "healing":
                return SpellType.Heal;
            case "frost":
                return SpellType.Frost;
        }
        return SpellType.Fire;
    }

    /***********************************************************************************
     * helper method for playerTurn which makes a String list representation of the spells
     * that a player currently has unlocked. Helps with determining valid vs. invalid spell input.
     * @return ArrayList<String> - a String list representation of the spells that a player currently
     * has unlocked. 
     */
    public ArrayList<String> castTypeToString(){
        ArrayList<String> possibleSpells = new ArrayList<String>();
        ArrayList<SpellType> availableSpells = p1.getCastTypes();
        for (SpellType type: availableSpells){
            switch(type){
                case Fire:
                    possibleSpells.add("fire");
                    System.out.print("\t" + "Fire");
                    break;
                case Lightning:
                    possibleSpells.add("lightning");
                    System.out.print("\t" + "Lightning");
                    break;
                case Heal:
                    possibleSpells.add("healing");
                    System.out.print("\t" + "Healing");
                    break;
                case Frost:
                    possibleSpells.add("frost");
                    System.out.print("\t" + "Frost");
                    break;
                default:
                    break;
            }
        }
        return possibleSpells;
    }

    /***********************************************************************************
     * Deals damage from attacking creature (player or monster) to target creature based on spell type. 
     * @param type - the type of spell to be cast
     * @param damaged - the creature to be damaged by a spell, if applicable
     * @param attacker - the creature casting the spell
     * @return String - a string representation of the spell name 
     */
    public String creatureSpellCast(SpellType type, Creature damaged, Creature attacker){
        switch (type){
            case Fire:
                attacker.setOnFire(damaged);
                return "fire";
            case Lightning:
                attacker.strikeWithLightning(damaged);
                return "lightning";
            case Heal:
                attacker.heal(50);
                return "healing";
            case Frost:
                attacker.freeze(damaged);
                return "frost";
            default:
                return "";
        }
    }


    /***********************************************************************************
     * carries out attacks and spells for all monsters, and decrements freeze or fire timers. 
     */
    public void monstersTurn(){
        Queue[] queues = {q1, q2, q3, q4};
        String[] directions = {"N", "E", "S", "W"};

        /*Attacks or casts a spell from the monster at the front of each queue if it is not frozen. If a monster 
        has a spell then it has a 50% change to cast it each turn, unless it has a healing spell, 
        in which case it waits until it takes damage before healing*/
        for (int i = 0; i < queues.length; ++i){
            if (queues[i].isEmpty() == false){
                Creature currentCreature = (Creature) queues[i].peek();
                if (currentCreature.isOnFire()){
                    System.out.println("The monster to the " + directions[i] + " is on fire. It takes 20 damage");
                    currentCreature.setHealth(currentCreature.getHealth() - 20);
                    monsterKillCheck(queues[i], currentCreature);
                    if (p1.canLevelUp()){
                        levelUpQuestions();
                    }
                }
                if (!currentCreature.isFrozen()){
                    boolean spellCast = false;
                    if (currentCreature.getSpellCaster() == true){
                        if (currentCreature.getCastType() == SpellType.Heal){
                            if (currentCreature.getHealth() <= (currentCreature.getMaxHealth() * 0.6)){
                                creatureSpellCast(currentCreature.getCastType(), p1, currentCreature);
                                System.out.println("The monster to the " + directions[i] + " healed itself");
                                spellCast = true;
                            }
                        }
                        Random rand = new Random();
                        int spellDetermination = rand.nextInt(100) + 1;
                        if (spellDetermination <=50){
                            String castString = creatureSpellCast(currentCreature.getCastType(), p1, currentCreature);
                            System.out.println("The monster to the " + directions[i] + " attacks you with a " + castString + " spell");
                            spellCast = true;
                        }
                    }
                    if (spellCast == false){
                        int damageAmount = currentCreature.attack();
                        p1.hurt(damageAmount);
                        System.out.println("The monster from the " + directions[i] + " attacks you for " + damageAmount + " damage.");
                    }
                }
                else{
                    System.out.println("The monster to the " + directions[i] + " is frozen");
                }
                currentCreature.decFreezeTimer();
                currentCreature.decFireTimer();
            }
        }
        if (p1.getHealth() > 0){
            System.out.println("Your current health is: " + p1.getHealth());
            System.out.println();
        }
    }

    /***********************************************************************************
     * Starts the game
     */
    public static void main(String[] args){
        Arena arena1 = new Arena();
        arena1.gameLoop(); 
    }
}
