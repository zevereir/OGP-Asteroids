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
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;
/**
 * A test class for the class Ship.
 * 
 * @version 28th of march
 * @authors Sieben Bocklandt and Ruben Broekx
 *
 */
public class Tests_Part2 {

	/// CONSTANTS///
	private final static double SPEED_OF_LIGHT = 300000;
	private static final double EPSILON = 0.01;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	/// CREATE SHIP ///

	public Ship[] create_ships() throws ModelException {
		Ship ship1 = facade.createShip(10000, 10000, 0, 0, 10, 0,0);
		Ship ship2 = facade.createShip(10000, 10100, 0, -10, 10, 3 * Math.PI / 2,0);
		Ship ship3 = facade.createShip(10100, 10000, -10, 0, 10, Math.PI,0);
		Ship ship4 = facade.createShip(10000, 9900, 0, 10, 10, Math.PI / 2,0);
		Ship ship5 = facade.createShip(9900, 10000, 10, 0, 10, 0,0);
		Ship ship6 = facade.createShip(1000, 1000, 10, 0, 100,0,0);
		Ship ship7 = facade.createShip(10000, 10000, SPEED_OF_LIGHT, SPEED_OF_LIGHT, 100, Math.PI / 4,0);
		Ship ship8 = facade.createShip(10001,10001,0,0,100,0,0);
		Ship[] Total = { ship1, ship2, ship3, ship4, ship5, ship6, ship7,ship8};

		return Total;
	}
	
	
	///CREATE BAD SHIPS///
	
	//ILLEGAL RADIUS
	@Test(expected = ModelException.class)
	public void create_bad_ship_radius() throws ModelException{
		Ship ship1 = facade.createShip(0, 0, 0, 0, -10, 0,0);
	}
	
	//ILLEGAL ORIENTATION
	@Test(expected =AssertionError.class)
	public void create_bad_ship_orientation() throws ModelException{
		Ship ship1 = facade.createShip(0, 0, 0, 0, 10, Math.PI * 10,0);
	}
	

	///TERMINATE///
	@Test
	public void testShipTerminate1() throws ModelException {
		Ship ship1 = create_ships()[0];
		facade.terminateShip(ship1);
		assert (facade.isTerminatedShip(ship1));
	}

	@Test(expected = ModelException.class)
	public void testShipTerminate2() throws ModelException {
		Ship ship1 = create_ships()[6];
		facade.terminateShip(ship1);
		assert (facade.isTerminatedShip(ship1));
		Bullet bullet = create_bullets()[2];
		facade.loadBulletOnShip(ship1, bullet);
	}
	
	
	/// TEST GETTERS ///
	@Test
	public void testShipGetters() throws ModelException {
		Ship ship1 = create_ships()[0];
		assertNotNull(ship1);

		// POSITION
		double[] position = facade.getShipPosition(ship1);
		assertNotNull(position);
		assertEquals(10000, position[0], EPSILON);
		assertEquals(10000, position[1], EPSILON);

		// VELOCITY
		double[] velocity = facade.getShipVelocity(ship1);
		assertEquals(0, velocity[0], EPSILON);
		assertEquals(0, velocity[1], EPSILON);

		// RADIUS
		assertEquals(10, facade.getShipRadius(ship1), EPSILON);

		// ORIENTATION
		assertEquals(0, facade.getShipOrientation(ship1), EPSILON);
		
		// MASS
		assertTrue(0 != facade.getShipMass(ship1) );
		
	}
	
	///CREATE BULLET///
		public Bullet[] create_bullets() throws ModelException{
			Bullet bullet1 = facade.createBullet(10000, 10000, 0, 0, 3);
			Bullet bullet2 = facade.createBullet(10000, 10000, 10, 10, 3);
			Bullet bullet3 = facade.createBullet(10000, 10000, 10, 10, 3);
			Bullet bullet4 = facade.createBullet(10000, 10000, 10, 10, 3);
			Bullet bullet5 = facade.createBullet(10000, 10000, 10, 10, 3);
			Bullet bullet6 = facade.createBullet(10000, 10000, 10, 10, 3);
			Bullet bullet7 = facade.createBullet(10000, 10000, 10, 10, 3);
			Bullet bullet8 = facade.createBullet(10000, 10000, 10, 10, 3);
			Bullet bullet9 = facade.createBullet(10000, 10000, 10, 10, 3);
			Bullet bullet10 = facade.createBullet(10000, 10000, 10, 10, 3);
			Bullet[] Total ={bullet1, bullet2,bullet3,bullet4,bullet5,bullet6,bullet7,bullet8,bullet9,bullet10};
			return Total;
		}
		public Set<Bullet> bulletsInSet() throws ModelException{
		 Set<Bullet> bulletset = new HashSet<Bullet>();
		 for (Bullet bullet: create_bullets())
			 bulletset.add(bullet);
		 return bulletset;
		}
		
		
		///CREATE BAD BULLETS///
		@Test(expected = ModelException.class)
		public void create_bad_bullet_radius() throws ModelException{
			Bullet bullet1 = facade.createBullet(0, 0, 0, 0, -10);
		}
		
		///GETTERS///
		@Test
		public void testBulletGetters() throws ModelException {
			Bullet bullet = create_bullets()[0];
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
	///CREATE WORLD///
	public World[] create_Worlds() throws ModelException{
		World world1 = facade.createWorld(30000, 30000);
		World world2 = facade.createWorld(-40000, 30000);
		World[] Total ={world1, world2};
		return Total;
	}
	
	///WORLD_CREATION_TEST///
	@Test
	public void test_creation_worlds() throws ModelException{
		World world1 = create_Worlds()[0];
		World world2 = create_Worlds()[1];
		assertEquals(30000,facade.getWorldSize(world1)[0],EPSILON);
		assertEquals(30000,facade.getWorldSize(world1)[1],EPSILON);
		assertEquals(40000,facade.getWorldSize(world2)[0],EPSILON);
		
	}
	
	///SHIP IN WORLD///
	@Test 
	public void test_ship_to_world() throws ModelException{
		World world1 = create_Worlds()[0];
		Ship ship7 = create_ships()[6];
		facade.addShipToWorld(world1, ship7);
		assert (facade.getShipWorld(ship7) == world1);
		assert (facade.getWorldShips(world1).contains(ship7));
	}
	
	
	
	///FIRING///
	@Test
	public void firing_bullets() throws ModelException{
		World world1 = create_Worlds()[0];
		Ship ship7 = create_ships()[6];
		facade.addShipToWorld(world1, ship7);
		facade.loadBulletOnShip(ship7,create_bullets()[2]);
		assertEquals(1,facade.getNbBulletsOnShip(ship7));
		facade.fireBullet(ship7);
		assertEquals(0,facade.getNbBulletsOnShip(ship7),EPSILON);
	}
	@Test
	public void adding_more_bullets() throws ModelException{
		World world1 = create_Worlds()[0];
		Ship ship7 = create_ships()[6];
		facade.addShipToWorld(world1, ship7);
		facade.loadBulletsOnShip(ship7,bulletsInSet());
		assertEquals(10,facade.getNbBulletsOnShip(ship7));
	}
	

	///COLLISIONS ETC///
	
	// OVERLAP
	@Test
	public void DoNotOverlap() throws ModelException {
		Ship ship1 = create_ships()[0];
		Ship ship2 = create_ships()[1];

		assertFalse(facade.overlap(ship1,ship2));
	}
	@Test
	public void Overlap() throws ModelException {
		Ship ship1 = create_ships()[0];
		Ship ship7 = create_ships()[6];

		assertTrue(facade.overlap(ship1,ship7));
	}
	
	// COLLISION
	@Test
	public void CollisionBoundary() throws ModelException{
		Ship ship = create_ships()[5];
		World world = create_Worlds()[0];
		facade.addShipToWorld(world, ship);
		double time = facade.getTimeCollisionBoundary(ship);
		assertEquals(2890,time,EPSILON);
		double[] position = facade.getPositionCollisionBoundary(ship);
		assertEquals(30000,position[0],EPSILON);
		assertEquals(1000,position[1],EPSILON);
	}

	public void CollisionFromAbove() throws ModelException {
		Ship ship1 = create_ships()[0];
		Ship ship2 = create_ships()[1];
		World world = create_Worlds()[0];
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		
		
		// TIME
		assertEquals(8, facade.getTimeToCollision(ship1,ship2), EPSILON);
		// COLLISION
		double[] position = facade.getCollisionPosition(ship1,ship2);
		assertEquals(10000, position[0], EPSILON);
		assertEquals(10010, position[1], EPSILON);
	}

	public void CollisionFromRight() throws ModelException {
		Ship ship1 = create_ships()[0];
		Ship ship3 = create_ships()[2];
		// TIME
		assertEquals(8, facade.getTimeToCollision(ship1,ship3), EPSILON);
		// COLLISION
		double[] position = facade.getCollisionPosition(ship1,ship3);
		assertEquals(10010, position[0], EPSILON);
		assertEquals(10000, position[1], EPSILON);
	}

	public void CollisionFromBelow() throws ModelException {
		Ship ship1 = create_ships()[0];
		Ship ship4 = create_ships()[3];
		// TIME
		assertEquals(8, facade.getTimeToCollision(ship1,ship4), EPSILON);
		// COLLISION
		double[] position = facade.getCollisionPosition(ship1,ship4);
		assertEquals(10000, position[0], EPSILON);
		assertEquals(9990, position[1], EPSILON);
	}

	public void CollisionFromLeft() throws ModelException {
		Ship ship1 = create_ships()[0];
		Ship ship5 = create_ships()[4];
		// TIME
		assertEquals(8, facade.getTimeToCollision(ship1,ship5), EPSILON);
		// COLLISION
		double[] position = facade.getCollisionPosition(ship1,ship5);
		assertEquals(9990, position[0], EPSILON);
		assertEquals(10000, position[1], EPSILON);
	}

	public void NoCollision() throws ModelException {
		Ship ship1 = create_ships()[0];
		Ship ship6 = create_ships()[5];
		// TIME
		assertEquals(Double.POSITIVE_INFINITY,facade.getTimeToCollision(ship1,ship6), EPSILON);
		// COLLISION
		double[] position = facade.getCollisionPosition(ship1,ship6);
		assertNull(position[0]);
	}

	// EXCEPTIONS//

	@Test(expected = ModelException.class)
	public final void NoTimeBecauseOverlapping() throws ModelException {
		Ship ship1 = create_ships()[0];
		Ship ship7 = create_ships()[6];

		facade.getTimeToCollision(ship1,ship7);
	}

	@Test(expected = ModelException.class)
	public final void NoPositionBecauseOverlapping() throws ModelException {
		Ship ship1 = create_ships()[0];
		Ship ship7 = create_ships()[6];

		facade.getCollisionPosition(ship1,ship7);
	}

	@Test(expected = ModelException.class)
	public final void CollisionTimeSameShip() throws ModelException {
		Ship ship1 = create_ships()[0];

		facade.getTimeToCollision(ship1,ship1);
	}

	@Test(expected = ModelException.class)
	public final void CollisionPositionSameShip() throws ModelException {
		Ship ship1 = create_ships()[0];

		facade.getCollisionPosition(ship1,ship1);
	}

	
	
	///EVOLVE///
	//-----> moeten we nu doen <-----//
	
	
	
}