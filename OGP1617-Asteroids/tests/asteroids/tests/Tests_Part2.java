package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	/// CREATE SHIP ///

	public Ship[] create_ships() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 0, 0, 10, 0,0);
		Ship ship2 = facade.createShip(0, 100, 0, -10, 10, 3 * Math.PI / 2,0);
		Ship ship3 = facade.createShip(100, 0, -10, 0, 10, Math.PI,0);
		Ship ship4 = facade.createShip(0, -100, 0, 10, 10, Math.PI / 2,0);
		Ship ship5 = facade.createShip(-100, 0, 10, 0, 10, 0,0);
		Ship ship6 = facade.createShip(0, 100, 10, 0, 10, 3 * Math.PI / 2,0);
		Ship ship7 = facade.createShip(10000, 10000, SPEED_OF_LIGHT, SPEED_OF_LIGHT, 100, Math.PI / 4,0);

		Ship[] Total = { ship1, ship2, ship3, ship4, ship5, ship6, ship7};

		return Total;
	}
	///CREATE BAD SHIPS///
	
	//ILLEGAL RADIUS//
	@Test(expected = ModelException.class)
	public void create_bad_ship_radius() throws ModelException{
		Ship ship1 = facade.createShip(0, 0, 0, 0, -10, 0,0);
	}
	
	//ILLEGAL ORIENTATION//
	@Test(expected =AssertionError.class)
	public void create_bad_ship_orientation() throws ModelException{
		Ship ship1 = facade.createShip(0, 0, 0, 0, 10, Math.PI * 10,0);
	}
	

	///CREATE BULLET///
	public Bullet[] create_bullets() throws ModelException{
		Bullet bullet1 = facade.createBullet(0, 0, 0, 0, 3);
		Bullet bullet2 = facade.createBullet(10, 10, 10, 10, 3);
		
		Bullet[] Total ={bullet1, bullet2};
		return Total;
	}
	///CREATE BAD BULLETS///
	@Test(expected = ModelException.class)
	public void create_bad_bullet_radius() throws ModelException{
		Bullet bullet1 = facade.createBullet(0, 0, 0, 0, -10);
	}
	
 
	///TERMINATE///
	@Test
	public void testShipTerminate() throws ModelException {
		Ship ship1 = create_ships()[0];
		facade.terminateShip(ship1);
		assert (facade.isTerminatedShip(ship1));
	}

	
	
	/// TEST GETTERS ///

	@Test
	public void testShipGetters() throws ModelException {
		Ship ship1 = create_ships()[0];
		assertNotNull(ship1);

		// POSITION
		double[] position = ship1.getEntityPosition();
		assertNotNull(position);
		assertEquals(0, position[0], EPSILON);
		assertEquals(0, position[1], EPSILON);

		// VELOCITY
		double[] velocity = ship1.getEntityVelocity();
		assertEquals(0, velocity[0], EPSILON);
		assertEquals(0, velocity[1], EPSILON);

		// RADIUS
		assertEquals(10, ship1.getEntityRadius(), EPSILON);

		// ORIENTATION
		assertEquals(0, ship1.getEntityOrientation(), EPSILON);
		
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
		assertEquals(15,facade.getNbBulletsOnShip(ship7));
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		assertEquals(10,facade.getNbBulletsOnShip(ship7));
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		facade.fireBullet(ship7);
		assertEquals(0,facade.getNbBulletsOnShip(ship7),EPSILON);
	//MASS BEKIJKEN//
	}
}