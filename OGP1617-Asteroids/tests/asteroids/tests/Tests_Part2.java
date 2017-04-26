package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.*;
import asteroids.facade.Facade;
import asteroids.part3.facade.IFacade;
import asteroids.util.ModelException;
/**
 * A test class for the class Ship.
 * 
 * @version 10th of march
 * @authors Sieben Bocklandt and Ruben Broekx
 *
 */
public class Tests_Part2 {

	/// CONSTANTS ///
	
	private final static double SPEED_OF_LIGHT = 300000;
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	
	/// --> SHIP <-- ///
	
	/// CREATE SHIP ///

	public Ship[] createShips() throws ModelException {
		Ship ship1 = facade.createShip(10000, 10000, 0, 0, 10, 0,5E16);
		Ship ship2 = facade.createShip(10000, 10100, 0, -10, 10, 3 * Math.PI / 2,5E16);
		Ship ship3 = facade.createShip(10100, 10000, -10, 0, 10, Math.PI,0);
		Ship ship4 = facade.createShip(10000, 9900, 0, 10, 10, Math.PI / 2,0);
		Ship ship5 = facade.createShip(9900, 10000, 10, 0, 10, 0,0);
		Ship ship6 = facade.createShip(1000, 1000, 10, 0, 100,0,0);
		Ship ship7 = facade.createShip(10000, 10000, SPEED_OF_LIGHT, SPEED_OF_LIGHT, 100, Math.PI / 4,0);
		Ship ship8 = facade.createShip(10001,10001,0,0,100,0,0);
		Ship ship9 = facade.createShip(0,0,0,0,10,0,0);
		Ship ship10 = facade.createShip(29000.0,10000.0,200,0,200,0,0);
		Ship ship11 = facade.createShip(10000,29000,0,200,200,0,0);
		Ship ship12 = facade.createShip(10000,10000,1000,1500,1000,0,0);
		Ship ship13 = facade.createShip(10000,12050,0,0,1000,3*Math.PI/2,0);
		
		Ship[] Total = { ship1, ship2, ship3, ship4, ship5, ship6, ship7,ship8,ship9,ship10,ship11,ship12,ship13};
		return Total;
	}
	
	
	/// CREATE BAD SHIPS ///
	
	// ILLEGAL RADIUS
	@Test(expected = ModelException.class)
	public void createBadShipRadius() throws ModelException{
		Ship ship1 = facade.createShip(0, 0, 0, 0, -10, 0,0);
	}
	
	// ILLEGAL ORIENTATION
	@Test(expected =AssertionError.class)
	public void createBadShipOrientation() throws ModelException{
		Ship ship1 = facade.createShip(0, 0, 0, 0, 10, Math.PI * 10,0);
	}
	
	
	/// OVERLAP ///
	
	public void doNotOverlap() throws ModelException{
		Ship ship1 = createShips()[0];
		Ship ship2 = createShips()[1];
		assert (facade.overlap(ship1, ship2));
	}

	public void doOverlap() throws ModelException{
		Ship ship1 = createShips()[0];
		Ship ship8 = createShips()[7];
		assertFalse (facade.overlap(ship1, ship8));
	}
	
	
	/// TERMINATE ///
	
	@Test
	public void shipTerminate1() throws ModelException {
		Ship ship1 = createShips()[0];
		facade.terminateShip(ship1);
		assert (facade.isTerminatedShip(ship1));
	}

	@Test(expected = ModelException.class)
	public void shipTerminate2() throws ModelException {
		Ship ship7 = createShips()[6];
		facade.terminateShip(ship7);
		assert (facade.isTerminatedShip(ship7));
		Bullet bullet = createBullets()[2];
		facade.loadBulletOnShip(ship7, bullet);
	}
	
	
	/// TURN ///
	
	@Test
	public void shipTurn() throws ModelException{
		Ship ship = createShips()[0];
		facade.turn(ship, Math.PI);
		assertEquals(Math.PI,facade.getShipOrientation(ship),EPSILON);
	}
	
	
	/// TEST GETTERS ///
	
	@Test
	public void shipGetters() throws ModelException {
		Ship ship1 = createShips()[0];
		Ship ship2 = createShips()[1];
		Ship ship7 = createShips()[6];
		Ship ship9 = createShips()[8];
		World world = createWorlds()[0];
		
		assertNotNull(ship1);
		assertNotNull(ship9);
		
		// POSITION
		double[] position = facade.getShipPosition(ship1);
		assertNotNull(position);
		assertEquals(10000, position[0], EPSILON);
		assertEquals(10000, position[1], EPSILON);
		
		try {
			world.addEntityToWorld(ship9);
		} catch (IllegalArgumentException illegalArgumentException) {
			// Ship9 will not be added into the world
		}

		// VELOCITY
		double[] velocity = facade.getShipVelocity(ship1);
		assertEquals(0, velocity[0], EPSILON);
		assertEquals(0, velocity[1], EPSILON);
		double[] velocity7 = facade.getShipVelocity(ship7);
		assertEquals(SPEED_OF_LIGHT*Math.cos(Math.PI/4), velocity7[0], EPSILON);
		assertEquals(SPEED_OF_LIGHT*Math.sin(Math.PI/4), velocity7[1], EPSILON);

		// RADIUS
		assertEquals(10, facade.getShipRadius(ship1), EPSILON);

		// ORIENTATION
		assertEquals(0, facade.getShipOrientation(ship1), EPSILON);
		
		// MASS
		assertEquals(5E16,facade.getShipMass(ship1),EPSILON );
		assertEquals(5E16,facade.getShipMass(ship2),EPSILON );
		
		//ACCELERATION
		facade.setThrusterActive(ship1, true);
		assertEquals((1.1E18)/(5E16),facade.getShipAcceleration(ship1),EPSILON);
	}
	
	
	/// THRUSTER ///
	
	@Test
	public void thruster() throws ModelException{
		Ship ship = createShips()[0];
		facade.setThrusterActive(ship, true);
		assert(facade.isShipThrusterActive(ship));
	}
		
	
	/// ADDING BULLETS ///

	@Test
	public void addingOneBullet() throws ModelException{
		Ship ship7 = createShips()[6];
		Bullet bullet = createBullets()[2];
		facade.loadBulletOnShip(ship7, bullet);
		assertEquals(1,facade.getNbBulletsOnShip(ship7));
	}

	@Test
	public void addingMoreBullets() throws ModelException{
		World world1 = createWorlds()[0];
		Ship ship7 = createShips()[6];
		facade.addShipToWorld(world1, ship7);
		facade.loadBulletsOnShip(ship7,bulletsInSet());
		assertEquals(5,facade.getNbBulletsOnShip(ship7));
	}


	/// FIRING ///
	
	@Test
	public void firingBullets1() throws ModelException{
		World world1 = createWorlds()[0];
		Ship ship7 = createShips()[6];
		Bullet bullet = createBullets()[2];
		facade.addShipToWorld(world1, ship7);
		facade.loadBulletOnShip(ship7,bullet);
		assertEquals(1,facade.getNbBulletsOnShip(ship7));
		assert(facade.getBulletsOnShip(ship7).contains(bullet));
		facade.fireBullet(ship7);
		assertEquals(0,facade.getNbBulletsOnShip(ship7),EPSILON);
		assertFalse(facade.getBulletsOnShip(ship7).contains(bullet));
	}


	@Test 
	public void firingBullets2() throws ModelException{
		World world = createWorlds()[0];
		Ship ship1 = createShips()[0];
		Ship ship2 = createShips()[1];
		facade.addShipToWorld(world, ship2);
		facade.addShipToWorld(world, ship1);
		Bullet bullet12 = createBullets()[11];
		facade.loadBulletOnShip(ship2, bullet12);
		ship2.fireBullet();
		facade.evolve(world, 2, null);
		assert(facade.isTerminatedShip(ship1));
		assert(facade.isTerminatedBullet(bullet12));	
	}

	@Test 
	public void firingBulletIntoBoundary() throws ModelException{
		World world = createWorlds()[0];
		Ship ship1 = createShips()[0];
		facade.addShipToWorld(world, ship1);
		Bullet bullet2 = createBullets()[1];
		facade.loadBulletOnShip(ship1, bullet2);
		facade.fireBullet(ship1);
		assertEquals(ship1,facade.getBulletSource(bullet2));
		facade.evolve(world, 4, null);
		assertEquals(0,facade.getNbBulletsOnShip(ship1),EPSILON);
		facade.evolve(world, 96, null);
		assertEquals(-250,facade.getBulletVelocity(bullet2)[0],EPSILON);
		facade.evolve(world, 70, null);
		assertFalse(facade.isTerminatedShip(ship1));
		assertFalse(facade.isTerminatedBullet(bullet2));
		assertEquals(1,facade.getNbBulletsOnShip(ship1),EPSILON);
	}

	@Test
	public void fireOverlappingBullet() throws ModelException{
		World world = createWorlds()[0];
		Ship ship1 = createShips()[11];
		Ship ship2 = createShips()[12];
		Bullet bullet = createBullets()[12];
		facade.addShipToWorld(world, ship2);
		facade.addShipToWorld(world, ship1);
		facade.loadBulletOnShip(ship2, bullet);
		assertEquals(1,facade.getNbBulletsOnShip(ship2),EPSILON);
		assert(facade.getBulletShip(bullet)== ship2);
		facade.fireBullet(ship2);
		assert(facade.isTerminatedShip(ship1));
		assertFalse(facade.isTerminatedShip(ship2));
		assert(facade.isTerminatedBullet(bullet));
	}
	
	
	/// DISTANCE BETWEEN ///

	@Test
	public void distanceBetween() throws ModelException{
		Ship ship1 = createShips()[0];
		Ship ship2 = createShips()[1];
		assertEquals(80,facade.getDistanceBetween(ship1, ship2),EPSILON);
	}


	/// --> BULLET <-- ///
	
	/// CREATE BULLETS ///
	
	public Bullet[] createBullets() throws ModelException{
		Bullet bullet1 = facade.createBullet(10000, 10000, 0, 0, 3);
		Bullet bullet2 = facade.createBullet(10000, 10000, 10, 0, 3);
		Bullet bullet3 = facade.createBullet(10000, 10000, -10, 0, 3);
		Bullet bullet4 = facade.createBullet(10000, 10000, 10, 10, 3);
		Bullet bullet5 = facade.createBullet(10000, 10000, 10, 10, 3);
		Bullet bullet6 = facade.createBullet(10000, 10000, 10, 10, 3);
		Bullet bullet7 = facade.createBullet(10000, 10000, 10, 10, 3);
		Bullet bullet8 = facade.createBullet(10000, 10000, 4999, 3000, 4);
		Bullet bullet9 = facade.createBullet(29000, 10000, 10000, 0, 3);
		Bullet bullet10 = facade.createBullet(10000, 10000, 10, 10, 3);
		Bullet bullet11 = facade.createBullet(29500, 10000, 0, 0, 3);
		Bullet bullet12 = facade.createBullet(10001, 10100, 0, 0, 3);
		Bullet bullet13 = facade.createBullet(10000, 12050, 0, 0, 80);

		Bullet[] Total ={bullet1, bullet2,bullet3,bullet4,bullet5,bullet6,bullet7,bullet8,bullet9,bullet10,bullet11,bullet12,bullet13};
		
		return Total;
	}
	
	public Set<Bullet> bulletsInSet() throws ModelException{
		Set<Bullet> bulletset = new HashSet<Bullet>();
		bulletset.add(createBullets()[0]);
		bulletset.add(createBullets()[1]);
		bulletset.add(createBullets()[2]);
		bulletset.add(createBullets()[3]);
		bulletset.add(createBullets()[4]);
		
		return bulletset;
	}


	/// CREATE BAD BULLETS ///
	
	@Test(expected = ModelException.class)
	public void createBadBulletRadius() throws ModelException{
		Bullet bullet1 = facade.createBullet(0, 0, 0, 0, -10);
	}

	
	/// GETTERS ///
	
	@Test
	public void bulletGetters() throws ModelException {
		Bullet bullet = createBullets()[0];
		assertNotNull(bullet);

		// POSITION
		double[] position = facade.getBulletPosition(bullet);
		assertNotNull(position);
		assertEquals(10000, position[0], EPSILON);
		assertEquals(10000, position[1], EPSILON);

		// RADIUS
		assertEquals(3, facade.getBulletRadius(bullet), EPSILON);

		// VELOCITY
		double[] velocity = facade.getBulletVelocity(bullet);
		assertEquals(0, velocity[0], EPSILON);
		assertEquals(0, velocity[1], EPSILON);

		// MASS
		assertTrue(0 != facade.getBulletMass(bullet) );
	}

	
	/// BULLET AND SHIP ///
	
	@Test
	public void bulletAndShip() throws ModelException{
		Ship ship = createShips()[6];
		Bullet bullet = createBullets()[2];
		facade.loadBulletOnShip(ship, bullet);
		assertEquals(ship,facade.getBulletShip(bullet));
	}
	
	
	/// TERMINATE ///
	
	@Test
	public void terminateBullet() throws ModelException{
		Bullet bullet = createBullets()[0];
		facade.terminateBullet(bullet);
		assert(facade.isTerminatedBullet(bullet));
	}
	
	
	/// --> WORLD <-- ///
	
	/// CREATE WORLDS ///
	
	public World[] createWorlds() throws ModelException{
		World world1 = facade.createWorld(30000, 30000);
		World world2 = facade.createWorld(-40000, 30000);
		World[] Total ={world1, world2};
		return Total;
	}
	
	
	/// TEST WORLD CREATION ///
	
	@Test
	public void creationWorlds() throws ModelException{
		World world1 = createWorlds()[0];
		World world2 = createWorlds()[1];
		assertEquals(30000,facade.getWorldSize(world1)[0],EPSILON);
		assertEquals(30000,facade.getWorldSize(world1)[1],EPSILON);
		assertEquals(40000,facade.getWorldSize(world2)[0],EPSILON);
	}
	
	
	/// ENTITIES IN WORLD ///
	
	@Test 
	public void shipToWorld() throws ModelException{
		World world1 = createWorlds()[0];
		Ship ship7 = createShips()[6];
		facade.addShipToWorld(world1, ship7);
		assert (facade.getShipWorld(ship7) == world1);
		assert (facade.getWorldShips(world1).contains(ship7));
	}
	
	@Test 
	public void bulletToWorld() throws ModelException{
		World world1 = createWorlds()[0];
		Bullet bullet = createBullets()[0];
		facade.addBulletToWorld(world1, bullet);
		assert (facade.getBulletWorld(bullet) == world1);
		assert (facade.getWorldBullets(world1).contains(bullet));
	}
	
	
	/// TERMINATE ///
	
	@Test 
	public void terminateWorld() throws ModelException{
		World world = createWorlds()[0];
		Ship ship = createShips()[6];
		facade.addShipToWorld(world, ship);
		Bullet bullet = createBullets()[10];
		facade.addBulletToWorld(world, bullet);
		facade.terminateWorld(world);
		assert(facade.getBulletWorld(bullet)==null);
		assert(facade.getShipWorld(ship)==null);
		assert(facade.isTerminatedWorld(world));
	}

	
	/// COLLISIONS ///
	
	// OVERLAP
	@Test
	public void DoNotOverlap() throws ModelException {
		Ship ship1 = createShips()[0];
		Ship ship2 = createShips()[1];

		assertFalse(facade.overlap(ship1,ship2));
	}
	
	@Test
	public void Overlap() throws ModelException {
		Ship ship1 = createShips()[0];
		Ship ship7 = createShips()[6];

		assertTrue(facade.overlap(ship1,ship7));
	}
	
	// GET TIME NEXT COLLISION 
	@Test
	public void timeTillCollision()throws  ModelException{
		World world = createWorlds()[0];
		Ship ship = createShips()[0];
		facade.addShipToWorld(world, ship);
		double time = facade.getTimeNextCollision(world);
		assertEquals(Double.POSITIVE_INFINITY,time,EPSILON);
	}

	// COLLISION
	@Test
	public void collisionBoundary() throws ModelException{
		Ship ship = createShips()[5];
		World world = createWorlds()[0];
		facade.addShipToWorld(world, ship);
		double time = facade.getTimeCollisionBoundary(ship);
		assertEquals(2890,time,EPSILON);
		double[] position = facade.getPositionCollisionBoundary(ship);
		assertEquals(30000,position[0],EPSILON);
		assertEquals(1000,position[1],EPSILON);
	}

	public void collisionFromAbove() throws ModelException {
		Ship ship1 = createShips()[0];
		Ship ship2 = createShips()[1];
		World world = createWorlds()[0];
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		// TIME
		assertEquals(8, facade.getTimeToCollision(ship1,ship2), EPSILON);
		// COLLISION
		double[] position = facade.getCollisionPosition(ship1,ship2);
		assertEquals(10000, position[0], EPSILON);
		assertEquals(10010, position[1], EPSILON);
	}

	public void collisionFromRight() throws ModelException {
		Ship ship1 = createShips()[0];
		Ship ship3 = createShips()[2];
		// TIME
		assertEquals(8, facade.getTimeToCollision(ship1,ship3), EPSILON);
		// COLLISION
		double[] position = facade.getCollisionPosition(ship1,ship3);
		assertEquals(10010, position[0], EPSILON);
		assertEquals(10000, position[1], EPSILON);
	}

	public void collisionFromBelow() throws ModelException {
		Ship ship1 = createShips()[0];
		Ship ship4 = createShips()[3];
		// TIME
		assertEquals(8, facade.getTimeToCollision(ship1,ship4), EPSILON);
		// COLLISION
		double[] position = facade.getCollisionPosition(ship1,ship4);
		assertEquals(10000, position[0], EPSILON);
		assertEquals(9990, position[1], EPSILON);
	}

	public void collisionFromLeft() throws ModelException {
		Ship ship1 = createShips()[0];
		Ship ship5 = createShips()[4];
		// TIME
		assertEquals(8, facade.getTimeToCollision(ship1,ship5), EPSILON);
		// COLLISION
		double[] position = facade.getCollisionPosition(ship1,ship5);
		assertEquals(9990, position[0], EPSILON);
		assertEquals(10000, position[1], EPSILON);
	}

	public void noCollision() throws ModelException {
		Ship ship1 = createShips()[0];
		Ship ship6 = createShips()[5];
		// TIME
		assertEquals(Double.POSITIVE_INFINITY,facade.getTimeToCollision(ship1,ship6), EPSILON);
		// COLLISION
		double[] position = facade.getCollisionPosition(ship1,ship6);
		assertNull(position[0]);
	}

	@Test(expected = ModelException.class)
	public final void noTimeBecauseOverlapping() throws ModelException {
		Ship ship1 = createShips()[0];
		Ship ship7 = createShips()[6];

		facade.getTimeToCollision(ship1,ship7);
	}

	@Test(expected = ModelException.class)
	public final void noPositionBecauseOverlapping() throws ModelException {
		Ship ship1 = createShips()[0];
		Ship ship7 = createShips()[6];

		facade.getCollisionPosition(ship1,ship7);
	}

	@Test(expected = ModelException.class)
	public final void collisionTimeSameShip() throws ModelException {
		Ship ship1 = createShips()[0];

		facade.getTimeToCollision(ship1,ship1);
	}

	@Test(expected = ModelException.class)
	public final void collisionPositionSameShip() throws ModelException {
		Ship ship1 = createShips()[0];

		facade.getCollisionPosition(ship1,ship1);
	}
	

	/// EVOLVE ///

	@Test
	public final void testEvolve() throws ModelException{
		Ship ship1 = createShips()[0];
		Ship ship2 = createShips()[1];
		Ship ship6 = createShips()[5];
		World world = createWorlds()[0];
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		facade.addShipToWorld(world, ship6);
		assertEquals(3,facade.getEntities(world).size());
		facade.evolve(world, 11,null);
		assertEquals(10,facade.getShipVelocity(ship6)[0],EPSILON);
		assertEquals(1110,facade.getShipPosition(ship6)[0],EPSILON);
	}

	// SHIP TO RIGHT BOUNDARY
	@Test
	public final void evolveCollisionRightBoundary() throws ModelException{
		Ship ship10 = createShips()[9];	
		World world = createWorlds()[0];
		facade.addShipToWorld(world, ship10);
		assertEquals(world, facade.getShipWorld(ship10));
		assertEquals(200,facade.getShipVelocity(ship10)[0],EPSILON);
		assertEquals(4.0,facade.getTimeNextCollision(world),EPSILON);
		assertEquals(30000,facade.getPositionNextCollision(world)[0],EPSILON);
		assertEquals(10000,facade.getPositionNextCollision(world)[1],EPSILON);
		facade.evolve(world, 6,null);
		assertEquals(-200,facade.getShipVelocity(ship10)[0],EPSILON);	
	}

	// SHIP TO UPPER BOUNDARY
	@Test
	public final void evolveCollisionUpperBoundary() throws ModelException{
		Ship ship11 = createShips()[10];	
		World world = createWorlds()[0];
		facade.addShipToWorld(world, ship11);
		assertEquals(world, facade.getShipWorld(ship11));
		assertEquals(200,facade.getShipVelocity(ship11)[1],EPSILON);
		assertEquals(4.0,facade.getTimeNextCollision(world),EPSILON);
		assertEquals(10000,facade.getPositionNextCollision(world)[0],EPSILON);
		assertEquals(30000,facade.getPositionNextCollision(world)[1],EPSILON);
		facade.evolve(world, 6,null);
		assertEquals(-200,facade.getShipVelocity(ship11)[1],EPSILON);
	}
	
	// COLLISION SHIP WITH BOUNARY FLYING ON A DIAGONAL
	@Test
	public final void evolveCollisionShipDiagonal() throws ModelException{
		Ship ship12 = createShips()[11];	
		World world = createWorlds()[0];
		facade.addShipToWorld(world, ship12);
		assertEquals(world, facade.getShipWorld(ship12));
		facade.evolve(world, 14,null);
		assertEquals(-1500,facade.getShipVelocity(ship12)[1],EPSILON);	
		facade.evolve(world,7,null);
		assertEquals(-1000,facade.getShipVelocity(ship12)[0],EPSILON);	

	}

	// BULLET BOUNCES TWICE
	@Test
	public final void evolveBulletBouncesTwice() throws ModelException{
		Bullet bullet = createBullets()[8];
		World world = createWorlds()[0];
		facade.addBulletToWorld(world, bullet);
		assertEquals(world,facade.getBulletWorld(bullet));
		facade.evolve(world, 1,null);
		assertEquals(-10000,facade.getBulletVelocity(bullet)[0],EPSILON);
		assertEquals(0,facade.getBulletVelocity(bullet)[1],EPSILON);
		assertEquals(21000,facade.getBulletPosition(bullet)[0],120);
		assertEquals(10000,facade.getBulletPosition(bullet)[1],EPSILON);
	}
	
	// BULLET BOUNCES THREE TIMES AND GET TERMINATED
	@Test
	public final void evolveBulletBouncesThreeTimes() throws ModelException{
		Bullet bullet = createBullets()[8];
		World world = createWorlds()[0];
		facade.addBulletToWorld(world, bullet);
		assertEquals(world,facade.getBulletWorld(bullet));
		facade.evolve(world, 6,null);
		assertEquals(10000,facade.getBulletVelocity(bullet)[0],EPSILON);
		facade.evolve(world,1,null);
		assert(facade.isTerminatedBullet(bullet));
	}
	
	// COLLISION BULLET WITH BOUNARY FLYING ON A DIAGONAL 
	@Test
	public final void evolveCollisionBulletDiagonal() throws ModelException{
		Bullet bullet = createBullets()[7];
		World world = createWorlds()[0];
		facade.addBulletToWorld(world, bullet);
		facade.evolve(world, 8, null);
		assertEquals(-4999,facade.getBulletVelocity(bullet)[0],EPSILON);
		assertEquals(-3000,facade.getBulletVelocity(bullet)[1],EPSILON);
		facade.evolve(world, 5, null);
		assert (facade.isTerminatedBullet(bullet));
	}
	
	// COLLISION BETWEEN TWO BULLETS
	@Test
	public final void evolveCollisionBullets() throws ModelException{
		Bullet bullet1 = createBullets()[8];
		Bullet bullet2 = createBullets()[10];
		World world = createWorlds()[0];
		facade.addBulletToWorld(world, bullet1);
		facade.addBulletToWorld(world, bullet2);
		facade.evolve(world, 4, null);
		assert(facade.isTerminatedBullet(bullet1));
		assert(facade.isTerminatedBullet(bullet2));
	}
	
	// COLLISION BETWEEN SHIP AND BULLET
	@Test
	public final void evolveCollisionShipBullet() throws ModelException{
		Bullet bullet = createBullets()[8];
		Ship ship = createShips()[0];
		World world = createWorlds()[0];
		facade.addBulletToWorld(world, bullet);
		facade.addShipToWorld(world, ship);
		assertEquals(2,facade.getEntities(world).size(),EPSILON);
		facade.evolve(world, 4,null);
		assert(facade.isTerminatedBullet(bullet));
		assert(facade.isTerminatedShip(ship));
		assertEquals(0,facade.getEntities(world).size(),EPSILON);
	}

	
	/// GETTERS ///
	
	@Test
	public final void getEntityAt() throws ModelException{
		World world = createWorlds()[0];
		Ship ship = createShips()[0];
		Bullet bullet = createBullets()[8];
		facade.addBulletToWorld(world, bullet);
		facade.addShipToWorld(world, ship);
		assertEquals(ship,facade.getEntityAt(world, 10000.0, 10000.0));
		assertEquals(bullet,facade.getEntityAt(world, 29000.0, 10000.0));
		assertEquals(null,facade.getEntityAt(world, 5000, 5000));
		facade.evolve(world, 4,null);
		assertEquals(null,facade.getEntityAt(world, 10000, 10000));
	}
	
	///PLANETOIDS///
	@Test
	public final void terminatePlanetoids() throws ModelException{
		World world = createWorlds()[0];
		Planetoid planetoid = facade.createPlanetoid(10000, 10000,10, 10, 100, 0);
		facade.addPlanetoidToWorld(world, planetoid);
		assert(facade.getPlanetoidWorld(planetoid) == world);
		facade.terminatePlanetoid(planetoid);
		assert(facade.isTerminatedPlanetoid(planetoid));
		assertEquals(2,facade.getEntities(world).size(),EPSILON);
		
	}
}