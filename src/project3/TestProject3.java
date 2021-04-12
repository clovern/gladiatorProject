/**
 * Project 3 Part 1 Tests
 * Tests SLL Class
 * 
 * @author Nicole Dudas
 * @partners Jaqueline Poland, Kyle Knapp - partners for Part 1 of the project
 * @version Winter 2021
 * 
 * */
package project3;

import static org.junit.Assert.assertEquals;
import java.util.NoSuchElementException;

import org.junit.Test;

public class TestProject3 {

    //-------------------------------------------------------------------------
    //Tests for SLL
    SLL<Integer> nums = new SLL<Integer>();
    SLL<String> names = new SLL<String>();

    public TestProject3(){
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(1);
        nums.add(4);
        nums.add(42);
        nums.add(1701);

        names.add("Mr. W.");
        names.add("Joel");
        names.add("Jacque");
        names.add("Aaron");
        names.add("Mariah");
        names.add("Kyle");
        names.add("Connor");
        names.add("Phillip");
        names.add("Nicole");
    }

    @Test
    public void testAddAtIndex(){
        SLL<Integer> numsCopy = new SLL<Integer>();
        for(int i=0; i<nums.size(); ++i){
            numsCopy.add(i, nums.get(i));
        }

        numsCopy.add(4, 7654);
        assertEquals(numsCopy.get(3), (Integer)1);
        assertEquals(numsCopy.get(4), (Integer)7654);
        assertEquals(numsCopy.get(5), (Integer)4);

        numsCopy.add(8, 8000);
        assertEquals(numsCopy.get(7), (Integer)1701);
        assertEquals(numsCopy.get(8), (Integer)8000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddAtIndexException(){
        nums.add(-1, 7654);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddAtIndexException2(){
        nums.add(8, 7654);
    }

    @Test
    public void testAdd(){
        SLL<Integer> numsCopy = new SLL<Integer>();
        for(int i=0; i<nums.size(); ++i){
            numsCopy.add(i, nums.get(i));
        }

        numsCopy.add(17);
        assertEquals(numsCopy.get(0), (Integer)1);
        assertEquals(numsCopy.get(7), (Integer)17);
        numsCopy.add(35);
        assertEquals(numsCopy.get(8), (Integer)35);
        numsCopy.add(67);
        assertEquals(numsCopy.get(9), (Integer)67);
        assertEquals(numsCopy.size(), 10);

        SLL<String> namesCopy = new SLL<String>();
        for(int i=0; i<names.size(); ++i){
            namesCopy.add(i, names.get(i));
        }

        namesCopy.add("Shelly");
        assertEquals(namesCopy.get(0), "Mr. W.");
        assertEquals(namesCopy.get(9), "Shelly");
        assertEquals(namesCopy.size(), 10);
        namesCopy.add("Dylan");
        assertEquals(namesCopy.size(), 11);
        assertEquals(namesCopy.get(10), "Dylan");
        namesCopy.add("Cindy");
        assertEquals(namesCopy.get(11), "Cindy");
        assertEquals(namesCopy.size(), 12);
    }

    @Test
    public void testClear(){
        SLL<Integer> numsCopy = new SLL<Integer>();
        for(int i=0; i<nums.size(); ++i){
            numsCopy.add(i, nums.get(i));
        }
        assertEquals(numsCopy.size(), nums.size());
        numsCopy.clear();
        assertEquals(numsCopy.size(), 0);

        SLL<String> namesCopy = new SLL<String>();
        for(int i=0; i<names.size(); ++i){
            namesCopy.add(i, names.get(i));
        }
        assertEquals(namesCopy.size(), names.size());
        namesCopy.clear();
        assertEquals(namesCopy.size(), 0);

        SLL<String> empty = new SLL<String>();
        empty.clear();
        assertEquals(namesCopy.size(), 0);
    }


    @Test
    public void testRemove(){
        SLL<Integer> numsCopy = new SLL<Integer>();
            for(int i=0; i<nums.size(); ++i){
                numsCopy.add(i, nums.get(i));
            }
        SLL<String> namesCopy = new SLL<String>();
            for(int i=0; i<names.size(); ++i){
                namesCopy.add(i, names.get(i));
            }
            
        namesCopy.remove(4);
        assertEquals(namesCopy.get(4), "Kyle");
        assertEquals(namesCopy.get(3), "Aaron");

        namesCopy.remove(4);
        assertEquals(namesCopy.get(4), "Connor");
        assertEquals(namesCopy.get(3), "Aaron");

        assertEquals(namesCopy.remove(0), "Mr. W.");
        assertEquals(namesCopy.get(0), "Joel");
        assertEquals(namesCopy.get(1), "Jacque");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveLastElement(){
        SLL<Integer> numsCopy = new SLL<Integer>();
        for(int i=0; i<nums.size(); ++i){
            numsCopy.add(i, nums.get(i));
        }    

        numsCopy.remove(6);
        numsCopy.get(6); 
    }

    @Test (expected = NoSuchElementException.class)
    public void testRemoveException(){
        nums.remove(7);
    }

    
    @Test (expected = NoSuchElementException.class)
    public void testRemoveException2(){
        nums.remove(-1);
    }

    @Test (expected = NoSuchElementException.class)
    public void testRemoveException3(){
        SLL<Integer> numsCopy = new SLL<Integer>();
        numsCopy.remove(0);
    }




    @Test
    public void testContains(){
        SLL<Integer> numsCopy = new SLL<Integer>();
        for(int i=0; i<nums.size(); ++i){
            numsCopy.add(i, nums.get(i));
        }
        SLL<String> namesCopy = new SLL<String>();
            for(int i=0; i<names.size(); ++i){
                namesCopy.add(i, names.get(i));
            }
        
        assertEquals(numsCopy.contains(1), true);
        numsCopy.remove(3);
        assertEquals(numsCopy.contains(1), true);
        numsCopy.remove(0);
        assertEquals(numsCopy.contains(1), false);
        
        assertEquals(namesCopy.contains("Jacque"), true);
        assertEquals(namesCopy.contains("jacque"), false);
        assertEquals(namesCopy.contains("Jacqu"), false);

        namesCopy.add(4, "Micah");
        assertEquals(namesCopy.contains("Micah"), true);

        namesCopy.set(4, "Patrick");
        assertEquals(namesCopy.contains("Micah"), false);
        assertEquals(namesCopy.contains("Patrick"), true);
        
        namesCopy.clear();
        assertEquals(namesCopy.contains("Mr. W."), false);
    }

    @Test
    public void testToArray(){
        Object[] nodeArray = names.toArray();
        for (int i = 0; i < nodeArray.length; ++i){
            assertEquals(nodeArray[i], names.get(i));
        }

        nodeArray = nums.toArray();
        for (int i = 0; i < nodeArray.length; ++i){
            assertEquals(nodeArray[i], nums.get(i));
        }

        SLL<Integer> empty = new SLL<Integer>();
        nodeArray = empty.toArray();
        for (int i = 0; i < nodeArray.length; ++i){
            assertEquals(nodeArray[i], empty.get(i));
        }
    }

    @Test
    public void testSet(){
        
        SLL<Integer> numsCopy = new SLL<Integer>();
            for(int i=0; i<nums.size(); ++i){
                numsCopy.add(i, nums.get(i));
            }
        SLL<String> namesCopy = new SLL<String>();
            for(int i=0; i<names.size(); ++i){
                namesCopy.add(i, names.get(i));
            }
            
        namesCopy.set(4, "Stanley");
        for(int i=0; i<names.size(); ++i){
            if (i != 4){
                assertEquals(names.get(i), namesCopy.get(i));
            } else{
                assertEquals(namesCopy.get(i), "Stanley");
            }
        }

        assertEquals(namesCopy.set(0, "Julia"), "Mr. W.");
        assertEquals(namesCopy.get(0), "Julia");
        
        namesCopy.set(8, "Miranda");
        assertEquals(namesCopy.get(8), "Miranda");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetException1(){
        names.set(-1, "Chad");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetException2(){
        names.set(9, "Jerod");
    }

    //----------------------------------------------------------------------
    //Tests for Queue
    @Test
    public void testEnqueueDequeue(){
        Queue<Integer> q1 = new Queue<Integer>();
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);
        q1.enqueue(4);
        q1.enqueue(5);
        assertEquals(q1.size(), 5); 
        assertEquals(q1.peek(), (Integer)1); 
        assertEquals(q1.dequeue(), (Integer)1);
        assertEquals(q1.dequeue(), (Integer)2);
        assertEquals(q1.dequeue(), (Integer)3);
        assertEquals(q1.dequeue(), (Integer)4);
        assertEquals(q1.isEmpty(), false);
        assertEquals(q1.dequeue(), (Integer)5);
        assertEquals(q1.isEmpty(),true);
    }

    @Test
    public void testPeek(){
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(5);
        q.enqueue(2);
        q.enqueue(4);
        q.enqueue(9);
        q.enqueue(3);
        assertEquals((int) q.peek(), 5);
        assertEquals(q.size(), 5);
        q.dequeue();
        q.dequeue();
        assertEquals((int) q.peek(), 4);
        assertEquals(q.size(), 3);
    }

    @Test (expected = NoSuchElementException.class)
    public void testDequeueInvalid(){
        Queue<Integer> q = new Queue<Integer>();
        q.dequeue();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPeekInvalid(){
        Queue<Integer> q = new Queue<Integer>();
        q.peek();
    }

    @Test
    public void testSizeQueue(){
        Queue<Integer> q = new Queue<Integer>();
        assertEquals(q.size(), 0); 
        q.enqueue(5);
        q.enqueue(4);
        q.enqueue(4);
        q.enqueue(9);
        q.enqueue(3);
        assertEquals(q.size(), 5); 
        q.dequeue();
        assertEquals(q.size(), 4); 
    }

    @Test
    public void testIsEmpty(){
        Queue<Integer> q = new Queue<Integer>();
        assertEquals(q.isEmpty(), true);
        q.enqueue(5); 
        q.enqueue(5); 
        q.enqueue(5); 
        q.enqueue(4); 
        q.dequeue();
        q.dequeue();
        assertEquals(q.isEmpty(), false);
        q.dequeue();
        assertEquals(q.isEmpty(), false);
        q.dequeue();
        assertEquals(q.isEmpty(), true);
    }

    @Test
    public void testConstructor(){
        Queue q = new Queue();
        assertEquals(q.size(), 0); 
    }
    

    //---------------------------------------------------------------------
    //TESTS FOR CREATURE CLASS
    @Test
    public void testCreatureConstructor(){
        Creature c = new Creature(40, 500);
        Creature d = new Creature(1, 1);
        assertEquals(c.getHealth(), 500);
        assertEquals(c.getMaxHealth(), 500);
        assertEquals(c.getCastType(), null);
        assertEquals(c.getSpellCaster(), false);
        assertEquals(c.getStrength(), 40);
        assertEquals(c.getFreezeTimer(), 0);
        assertEquals(c.getFireTimer(), 0);

        assertEquals(d.getHealth(), 1);
        assertEquals(d.getMaxHealth(), 1);
        assertEquals(d.getCastType(), null);
        assertEquals(d.getSpellCaster(), false);
        assertEquals(d.getStrength(), 1);
        assertEquals(d.getFreezeTimer(), 0);
        assertEquals(d.getFireTimer(), 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreatureConstructorInvalid(){
        Creature c = new Creature(-1, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreatureConstructorInvalid2(){
        Creature c = new Creature(1, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreatureConstructorInvalid3(){
        Creature c = new Creature(0, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreatureConstructorInvalid4(){
        Creature c = new Creature(1, 0);
    }

    @Test
    public void testAttack(){
        Creature c = new Creature(20, 30);
        Creature d = new Creature(70, 80);
        for(int i=0; i<100; ++i){
            int attackValue = c.attack();
            assert(attackValue <= 20 && attackValue >= 1);
        }
        for(int i=0; i<100; ++i){
            int attackValue = d.attack();
            assert(attackValue <= 70 && attackValue >= 1);
        }
    }

    @Test
    public void testSpellCaster(){
        Creature c = new Creature(20, 30);
        Creature d = new Creature(70, 80);
        assertEquals(c.getSpellCaster(), false); 
        c.giveSpell(SpellType.Frost);
        assertEquals(c.getSpellCaster(), true); 
        c.freeze(d);
        assertEquals(c.getSpellCaster(), false); 
        c.giveSpell(SpellType.Fire);
        c.giveSpell(SpellType.Heal);
        assertEquals(c.getSpellCaster(), true);
        c.setOnFire(d);
        assertEquals(c.getSpellCaster(), true);
        c.heal(30);
        assertEquals(c.getSpellCaster(), false); 
    }

    @Test
    public void testGetCastTypeGiveSpell(){
        Creature c = new Creature(20, 30);
        Creature d = new Creature(70, 80);
        assertEquals(c.getCastType(), null);
        c.giveSpell(SpellType.Fire);
        assertEquals(c.getCastType(), SpellType.Fire);
        c.setOnFire(d);
        assertEquals(c.getCastType(), null);
        c.giveSpell(SpellType.Heal);
        assertEquals(c.getCastType(), SpellType.Heal);
        c.heal(100);
        assertEquals(c.getCastType(), null);
        c.giveSpell(SpellType.Lightning);
        c.giveSpell(SpellType.Frost);
        assertEquals(c.getCastType(), SpellType.Lightning);
    }

    @Test
    public void testHurt(){
        Creature c = new Creature(20, 30);
        c.hurt(5);
        assertEquals(c.getHealth(), 25);
        c.hurt(10);
        assertEquals(c.getHealth(), 15);
        c.hurt(44);
        assertEquals(c.getHealth(), -29);
        Creature d = new Creature(70, 80);
        d.hurt(80);
        assertEquals(d.getHealth(), 0);
    }

    @Test
    public void testIsDead(){
        Creature c = new Creature(20, 30);
        Creature d = new Creature(70, 80);

        c.hurt(40);
        assertEquals(c.isDead(), true);
        d.hurt(79);
        assertEquals(d.isDead(), false);
        d.hurt(1);
        assertEquals(d.isDead(), true);
        c = new Creature(2, 1000);
        c.hurt(1001);
        assertEquals(c.isDead(), true);
    }

    @Test
    public void testCanCast(){
        Creature c = new Creature(20, 30);
        Creature d = new Creature(70, 80);
        assertEquals(c.canCast(SpellType.Fire), false);
        assertEquals(c.canCast(SpellType.Heal), false);
        assertEquals(c.canCast(SpellType.Lightning), false);
        assertEquals(c.canCast(SpellType.Frost), false);
        c.giveSpell(SpellType.Fire);
        assertEquals(c.canCast(SpellType.Fire), true);
        assertEquals(c.canCast(SpellType.Heal), false);
        assertEquals(c.canCast(SpellType.Lightning), false);
        assertEquals(c.canCast(SpellType.Frost), false);
        c.setOnFire(d);
        assertEquals(c.canCast(SpellType.Fire), false);
        c.giveSpell(SpellType.Heal);
        c.giveSpell(SpellType.Frost);
        assertEquals(c.canCast(SpellType.Heal), true);
        assertEquals(c.canCast(SpellType.Lightning), false);
        assertEquals(c.canCast(SpellType.Frost), true);
        c.freeze(d);
        assertEquals(c.canCast(SpellType.Heal), true);
        assertEquals(c.canCast(SpellType.Frost), false);
        c.heal(40);
        assertEquals(c.canCast(SpellType.Heal), false);
        assertEquals(c.canCast(SpellType.Frost), false);
    }

    @Test
    public void testFreeze(){
        Creature c = new Creature(20, 30);
        Creature d = new Creature(20, 30);
        assertEquals(d.isFrozen(), false);
        c.freeze(d);
        assert(d.getFreezeTimer() <=4);
        assert(d.getFreezeTimer() >= 2);
    }

    @Test
    public void testIsFrozenIsOnFire(){
        Creature c = new Creature(20, 30);
        Creature d = new Creature(20, 30);
        c.setOnFire(d);
        assertEquals(d.isOnFire(), true);
        int fireTimer = d.getFireTimer();
        for (int i = 1; i < fireTimer; ++i){
            d.decFireTimer();
        }
        assertEquals(d.isOnFire(), true);
        d.decFireTimer();
        assertEquals(d.isOnFire(), false);
        d.decFireTimer();
        assertEquals(d.isOnFire(), false);

        c = new Creature(20, 30);
        d = new Creature(20, 30);
        c.freeze(d);
        assertEquals(d.isFrozen(), true);
        int freezeTimer = d.getFreezeTimer();
        for (int i = 1; i < freezeTimer; ++i){
            d.decFreezeTimer();
        }
        assertEquals(d.isFrozen(), true);
        assertEquals(c.isFrozen(), false);
        d.decFreezeTimer();
        assertEquals(d.isFrozen(), false);
        d.decFreezeTimer();
        assertEquals(d.getFreezeTimer(), 0);
    }

    @Test
    public void testStrikeWithLightning(){
        Creature c = new Creature(20, 30);
        Creature d = new Creature(20, 30);
        c.strikeWithLightning(d);
        assertEquals(d.isDead(), true);
        d = new Creature(20, 45);
        c.strikeWithLightning(d);
        assertEquals(d.isDead(), false);
        assertEquals(d.getHealth(), 10);
        assertEquals(d.getMaxHealth(), 45);
    }

    @Test
    public void testSetOnFire(){
        Creature c = new Creature(20, 30);
        Creature d = new Creature(20, 30);
        c.setOnFire(d);
        assertEquals(d.isOnFire(), true);
        assert(d.getFireTimer() >=2);
        assert(d.getFireTimer() <=4);
    }

    @Test
    public void testHeal(){
        Creature c = new Creature(20, 30);
        c.heal(0);
        assertEquals(c.getHealth(), 30);

        c.hurt(15);
        assertEquals(c.getHealth(), 15);
        c.heal(20);
        assertEquals(c.getHealth(), 30);

        c = new Creature(20, 300);
        assertEquals(c.getHealth(), 300);
        c.hurt(200);
        assertEquals(c.getHealth(), 100);
        c.heal(55);
        assertEquals(c.getHealth(), 155);
        
    }

    @Test (expected = IllegalArgumentException.class)
    public void testHealInvalid(){
        Creature c = new Creature(20, 30);
        c.heal(-1);
    }


    //---------------------------------------------------------------------
    //TESTS FOR PLAYER

    @Test
    public void testPlayerConstructor(){
        Player p = new Player(1, 1);
        assertEquals(p.getStrength(), 1);
        assertEquals(p.getHealth(), 1);
        assertEquals(p.getMaxHealth(), 1);
        assertEquals(p.getXP(), 0);
        assertEquals(p.getCastTypes().size(), 0);

        p = new Player(20000, 20000);
        assertEquals(p.getStrength(), 20000);
        assertEquals(p.getHealth(), 20000);
        assertEquals(p.getMaxHealth(), 20000);
        assertEquals(p.getXP(), 0);
        assertEquals(p.getCastTypes().size(), 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPlayerConstructorInvalid(){
        Player p = new Player(-3, 4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPlayerConstructorInvalid2(){
        Player p = new Player(40, -4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPlayerConstructorInvalid3(){
        Player p = new Player(0, 0);
    }

    @Test
    public void testAddXP(){
        Player p = new Player(40, 100);
        assertEquals(p.getXP(), 0);
        p.addXP(50);
        assertEquals(p.getXP(), 50);
        p.addXP(60);
        assertEquals(p.getXP(), 110);
        p.addXP(0);
        assertEquals(p.getXP(), 110);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddXPInvalid(){
        Player p = new Player(0, 0);
        p.addXP(-1);
    }

    @Test
    public void testCanLevelUpLevelUp(){
        Player p = new Player(40, 100);
        assertEquals(p.canLevelUp(), false);
        p.addXP(40);
        p.addXP(70);
        assertEquals(p.canLevelUp(), true);
        p.levelUp(SpellType.Fire);
        assertEquals(p.getXP(), 10);
        assertEquals(p.getHealth(), 110);
        assertEquals(p.getMaxHealth(), 110);
        assertEquals(p.getStrength(), 45);
        assertEquals(p.getCastTypes().size(), 1);
        assertEquals(p.getCastTypes().get(0), SpellType.Fire);

        p.addXP(300);
        assertEquals(p.canLevelUp(), true);
        p.levelUp(SpellType.Fire);
        assertEquals(p.canLevelUp(), true);
        p.levelUp(SpellType.Fire);
        assertEquals(p.canLevelUp(), true);
        p.levelUp(SpellType.Fire);
        assertEquals(p.canLevelUp(), false);
        assertEquals(p.getXP(), 10);
        assertEquals(p.getCastTypes().size(), 4);
        assertEquals(p.getCastTypes().get(3), SpellType.Fire);
    }

    @Test
    public void testGetCastTypes(){
        Player p = new Player(40, 100);
        p.giveSpell(SpellType.Frost);
        p.giveSpell(SpellType.Heal);
        p.giveSpell(SpellType.Lightning);
        p.giveSpell(SpellType.Lightning);
        p.giveSpell(SpellType.Fire);
        assertEquals(p.getCastTypes().size(), 5);
        assertEquals(p.getCastTypes().get(0), SpellType.Frost);
        assertEquals(p.getCastTypes().get(1), SpellType.Heal);
        assertEquals(p.getCastTypes().get(2), SpellType.Lightning);
        assertEquals(p.getCastTypes().get(3), SpellType.Lightning);
        assertEquals(p.getCastTypes().get(4), SpellType.Fire);
        
        Creature c = new Creature(20, 30);
        p.freeze(c);
        assertEquals(p.getCastTypes().contains(SpellType.Frost), false);
        assertEquals(p.getCastTypes().get(0), SpellType.Heal);
        p.strikeWithLightning(c);
        assertEquals(p.getCastTypes().contains(SpellType.Lightning), true);
        p.strikeWithLightning(c);
        assertEquals(p.getCastTypes().contains(SpellType.Lightning), false);
        assertEquals(p.getCastTypes().size(), 2);
    }
}
