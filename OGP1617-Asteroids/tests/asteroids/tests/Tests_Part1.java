//package asteroids.tests;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import asteroids.model.*;
//import asteroids.facade.Facade;
//import asteroids.part2.facade.IFacade;
//import asteroids.util.ModelException;
///**
// * A test class for the class Ship.
// * 
// * @version 28th of march
// * @authors Sieben Bocklandt and Ruben Broekx
// *
// */
//public class Tests_Part1 {
//
//	/// CONSTANTS///
//	private final static double SPEED_OF_LIGHT = 300000;
//	private static final double EPSILON = 0.0001;
//
//	IFacade facade;
//
//	@Before
//	public void setUp() {
//		facade = new Facade();
//	}
//	
//	/// CREATE SHIP ///
//
//	public Ship[] create_ships() throws ModelException {
//		Ship ship1 = facade.createShip(0, 0, 0, 0, 10, 0, 0);
//		Ship ship2 = facade.createShip(0, 100, 0, -10, 10, 3 * Math.PI / 2, 0);
//		Ship ship3 = facade.createShip(100, 0, -10, 0, 10, Math.PI, 0);
//		Ship ship4 = facade.createShip(0, -100, 0, 10, 10, Math.PI / 2, 0);
//		Ship ship5 = facade.createShip(-100, 0, 10, 0, 10, 0, 0);
//		Ship ship6 = facade.createShip(0, 100, 10, 0, 10, 3 * Math.PI / 2, 0);
//		Ship ship7 = facade.createShip(10, 10, SPEED_OF_LIGHT, SPEED_OF_LIGHT, 10, Math.PI / 4, 0);
//
//		Ship[] Total = { ship1, ship2, ship3, ship4, ship5, ship6, ship7};
//
//		return Total;
//	}
//	
//	
//	
//	/// TEST GETTERS ///
//
//	@Test
//	public void testShipGetters() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		assertNotNull(ship1);
//
//		// POSITION
//		double[] position = ship1.getEntityPosition();
//		assertNotNull(position);
//		assertEquals(0, position[0], EPSILON);
//		assertEquals(0, position[1], EPSILON);
//
//		// VELOCITY
//		double[] velocity = ship1.getEntityVelocity();
//		assertEquals(0, velocity[0], EPSILON);
//		assertEquals(0, velocity[1], EPSILON);
//
//		// RADIUS
//		assertEquals(10, ship1.getEntityRadius(), EPSILON);
//
//		// ORIENTATION
//		assertEquals(0, ship1.getEntityOrientation(), EPSILON);
//		
//		//MAXVELOCITY
//		assertEquals(SPEED_OF_LIGHT, ship1.getEntityMaxVelocity(), EPSILON);
//	}
//	
//	
//	
//	/// TEST SETTERS ///
//
//	public void testShipSetters() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		assertNotNull(ship1);
//
//		// POSITION
//		double newX = 10;
//		double newY = 10;
//		ship1.setEntityPosition(newX, newY);
//		double[] position = ship1.getEntityPosition();
//		assertNotNull(position);
//		assertNotEquals(0, position[0], EPSILON);
//		assertEquals(newY, position[1], EPSILON);
//
//		// VELOCITY
//		double newVx = -10;
//		double newVy = -10;
//		ship1.setEntityVelocity(newVx, newVy);
//		double[] velocity = ship1.getEntityVelocity();
//		assertEquals(newVx, velocity[0], EPSILON);
//		assertNotEquals(0, velocity[1], EPSILON);
//
//		// RADIUS
//		double newRadius = 100;
//		ship1.setEntityRadius(newRadius);
//		assertEquals(newRadius, ship1.getEntityRadius(), EPSILON);
//
//		// ORIENTATION
//		double newOrientation = Math.PI;
//		ship1.setEntityOrientation(newOrientation);
//		assertEquals(newOrientation, ship1.getEntityOrientation(), EPSILON);
//		
//		//MAXVELOCITY
//		double newMaxVelocity_1 = 280000;
//		ship1.setEntityMaxVelocity(newMaxVelocity_1);
//		assertEquals(newMaxVelocity_1, ship1.getEntityMaxVelocity(), EPSILON);
//		
//	}
//
//	// EXCEPTIONS //
//
//	@Test(expected = ModelException.class)
//	public final void TestNaNPosition() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		ship1.setEntityPosition(Double.NaN, 20);
//	}
//
//	@Test
//	public final void TestVelocityAboveSpeedOfLight() throws ModelException {
//		Ship ship7 = create_ships()[6];
//		double[] velocity = ship7.getEntityVelocity();
//		double orientation = ship7.getEntityOrientation();
//		assertEquals(SPEED_OF_LIGHT * Math.cos(orientation), velocity[0], EPSILON);
//		assertEquals(SPEED_OF_LIGHT * Math.sin(orientation), velocity[1], EPSILON);
//	}
//
//	@Test(expected = ModelException.class)
//	public final void TestNegativeRadius() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		double falseRadius = -1;
//		ship1.setEntityRadius(falseRadius);
//	}
//		
//	@Test
//	public final void TestMaxVelocityAboveSpeedOfLight() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		double newMaxVelocity_1 = 540000;
//		ship1.setEntityMaxVelocity(newMaxVelocity_1);
//		assertEquals(SPEED_OF_LIGHT, ship1.getEntityMaxVelocity(), EPSILON);
//	}
//	
//	/// TEST METHODS ONE SHIP ///
//
//	@Test
//	public void TestMethodsOneShip() throws ModelException {
//		// MOVE
//		Ship ship2 = create_ships()[1];
//		ship2.move(10);
//		double[] position = ship2.getEntityPosition();
//		assertEquals(0, position[0], EPSILON);
//		assertEquals(0, position[1], EPSILON);
//
//		Ship ship3 = create_ships()[2];
//		ship3.move(5);
//		double[] position_3 = ship3.getEntityPosition();
//		assertEquals(50, position_3[0], EPSILON);
//		assertEquals(0, position_3[1], EPSILON);
//
//		Ship ship7 = create_ships()[6];
//		ship7.move(2 * Math.sqrt(2));
//		double[] position_7 = ship7.getEntityPosition();
//		assertEquals(position_7[0], 10 + 2 * SPEED_OF_LIGHT, EPSILON);
//		assertEquals(position_7[1], 10 + 2 * SPEED_OF_LIGHT, EPSILON);
//
//		// TURN
//		ship7.turn(Math.PI);
//		double orientation_7 = ship7.getEntityOrientation();
//		assertEquals(5 * Math.PI / 4, orientation_7, EPSILON);
//		ship7.turn(-3 * Math.PI / 4);
//		double orientation_7_2 = ship7.getEntityOrientation();
//		assertEquals(Math.PI / 2, orientation_7_2, EPSILON);
//
//		/*// THRUST
//		ship2.thrust(5);
//		double[] velocity = ship2.getEntityVelocity();
//		assertEquals(0, velocity[0], EPSILON);
//		assertEquals(-15, velocity[1], EPSILON);
//
//		ship7.setEntityVelocity(100, 100);
//		ship7.setEntityOrientation(Math.PI / 5);
//		ship7.thrust(10);
//		double[] velocity_7 = ship7.getEntityVelocity();
//		assertEquals(100 + 10 * Math.cos(Math.PI / 5), velocity_7[0], EPSILON);
//		assertEquals(100 + 10 * Math.sin(Math.PI / 5), velocity_7[1], EPSILON);
//*/	}
//
//	// EXCEPTIONS//
//
//	@Test(expected = ModelException.class)
//	public void TestNegativeMovingTime() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		double negativeDt = -10;
//		ship1.move(negativeDt);
//	}
///*
//	@Test
//	public void TestNegativeThrustAmount() throws ModelException {
//		Ship ship7 = create_ships()[6];
//		double[] velocityBefore = ship7.getEntityVelocity();
//		double negativeThrustAmount = -10;
//		ship7.thrust(negativeThrustAmount);
//		double[] velocityAfter = ship7.getEntityVelocity();
//		assertEquals(velocityAfter[0], velocityBefore[0], EPSILON);
//		assertEquals(velocityAfter[1], velocityBefore[1], EPSILON);
//	}
//*/	
//	
//	
//	/// TEST METHODES TWO SHIPS ///
//	
//	// DISTANCE BETWEEN 
//	
//	public void PositiveDistanceBetween() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship2 = create_ships()[1];
//
//		assertEquals(90, ship1.getDistanceBetween(ship2), EPSILON);
//	}
//
//	public void NegativeDistanceBetween() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship7 = create_ships()[6];
//
//		assertEquals(-10 * Math.sqrt(2) + 20, ship1.getDistanceBetween(ship7), EPSILON);
//
//	}
//
//	public void SameShip() throws ModelException {
//		Ship ship1 = create_ships()[0];
//
//		assertEquals(0, ship1.getDistanceBetween(ship1), EPSILON);
//	}
//	
//	// OVERLAP
//
//	public void DoNotOverlap() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship2 = create_ships()[1];
//
//		assertFalse(ship1.overlap(ship2));
//	}
//
//	public void Overlap() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship7 = create_ships()[6];
//
//		assertTrue(ship1.overlap(ship7));
//	}
//	
//	// COLLISION
//
//	public void CollisionFromAbove() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship2 = create_ships()[1];
//		// TIME
//		assertEquals(9, ship1.getTimeToCollision(ship2), EPSILON);
//		// COLLISION
//		double[] position = ship1.getCollisionPosition(ship2);
//		assertEquals(0, position[0], EPSILON);
//		assertEquals(10, position[1], EPSILON);
//	}
//
//	public void CollisionFromRight() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship3 = create_ships()[2];
//		// TIME
//		assertEquals(9, ship1.getTimeToCollision(ship3), EPSILON);
//		// COLLISION
//		double[] position = ship1.getCollisionPosition(ship3);
//		assertEquals(10, position[0], EPSILON);
//		assertEquals(0, position[1], EPSILON);
//	}
//
//	public void CollisionFromBelow() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship4 = create_ships()[3];
//		// TIME
//		assertEquals(9, ship1.getTimeToCollision(ship4), EPSILON);
//		// COLLISION
//		double[] position = ship1.getCollisionPosition(ship4);
//		assertEquals(0, position[0], EPSILON);
//		assertEquals(-10, position[1], EPSILON);
//	}
//
//	public void CollisionFromLeft() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship5 = create_ships()[4];
//		// TIME
//		assertEquals(9, ship1.getTimeToCollision(ship5), EPSILON);
//		// COLLISION
//		double[] position = ship1.getCollisionPosition(ship5);
//		assertEquals(-10, position[0], EPSILON);
//		assertEquals(0, position[1], EPSILON);
//	}
//
//	public void NoCollision() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship6 = create_ships()[5];
//		// TIME
//		assertEquals(Double.POSITIVE_INFINITY, ship1.getTimeToCollision(ship6), EPSILON);
//		// COLLISION
//		double[] position = ship1.getCollisionPosition(ship6);
//		assertNull(position[0]);
//	}
//
//	// EXCEPTIONS//
//
//	@Test(expected = ModelException.class)
//	public final void NoTimeBecauseOverlapping() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship7 = create_ships()[6];
//
//		ship1.getTimeToCollision(ship7);
//	}
//
//	@Test(expected = ModelException.class)
//	public final void NoPositionBecauseOverlapping() throws ModelException {
//		Ship ship1 = create_ships()[0];
//		Ship ship7 = create_ships()[6];
//
//		ship1.getCollisionPosition(ship7);
//	}
//
//	@Test(expected = ModelException.class)
//	public final void CollisionTimeSameShip() throws ModelException {
//		Ship ship1 = create_ships()[0];
//
//		ship1.getTimeToCollision(ship1);
//	}
//
//	@Test(expected = ModelException.class)
//	public final void CollisionPositionSameShip() throws ModelException {
//		Ship ship1 = create_ships()[0];
//
//		ship1.getCollisionPosition(ship1);
//	}
//
//}
