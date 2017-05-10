package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.model.*;
import asteroids.part3.facade.IFacade;
import asteroids.util.ModelException;

/**
 * A test file to evaluate the methods on minorPlanets. All the other tests are found in Tests_Part2.
 * 
 * @version 10th of may.
 * @authors Sieben Bocklandt and Ruben Broekx
 *
 */
public class Tests_Part3 {

	
	IFacade facade;
	static int score = 0;
	static int max_score = 0;
	
	@Before
	public void setUp() {
		facade = new Facade();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Score: " + score + "/" + max_score);
	}
	/// CONSTANTS ///

	private final static double SPEED_OF_LIGHT = 300000;
	private static final double EPSILON = 0.0001;


	/// CREATION ENITITIES AND WORLD ///

	// CREATE SHIP
	public Ship[] createShips() throws ModelException {
		Ship ship1 = facade.createShip(10000, 10000, 0, 0, 10, 0, 5E16);
		Ship ship2 = facade.createShip(10000, 10100, 0, -10, 10, 3 * Math.PI / 2, 5E16);
		Ship ship3 = facade.createShip(10100, 10000, -10, 0, 10, Math.PI, 0);
		Ship ship4 = facade.createShip(10000, 9900, 0, 10, 10, Math.PI / 2, 0);
		Ship ship5 = facade.createShip(9900, 10000, 10, 0, 10, 0, 0);
		Ship ship6 = facade.createShip(1000, 1000, 10, 0, 100, 0, 0);
		Ship ship7 = facade.createShip(10000, 10000, SPEED_OF_LIGHT, SPEED_OF_LIGHT, 100, Math.PI / 4, 0);
		Ship ship8 = facade.createShip(10001, 10001, 0, 0, 100, 0, 0);
		Ship ship9 = facade.createShip(0, 0, 0, 0, 10, 0, 0);
		Ship ship10 = facade.createShip(29000.0, 10000.0, 200, 0, 200, 0, 0);
		Ship ship11 = facade.createShip(10000, 29000, 0, 200, 200, 0, 0);
		Ship ship12 = facade.createShip(10000, 10000, 1000, 1500, 1000, 0, 0);
		Ship ship13 = facade.createShip(10000, 12050, 0, 0, 1000, 3 * Math.PI / 2, 0);

		Ship[] Total = { ship1, ship2, ship3, ship4, ship5, ship6, ship7, ship8, ship9, ship10, ship11, ship12,
				ship13 };
		return Total;
	}

	// CREATE BULLETS 
	public Bullet[] createBullets() throws ModelException {
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

		Bullet[] Total = { bullet1, bullet2, bullet3, bullet4, bullet5, bullet6, bullet7, bullet8, bullet9, bullet10,
				bullet11, bullet12, bullet13 };

		return Total;
	}

	// CREATE ASTEROID 
	public Asteroid[] createAsteroids() throws ModelException {
		Asteroid asteroid1 = facade.createAsteroid(10000, 10000, 0, 0, 10);
		Asteroid asteroid2 = facade.createAsteroid(10000, 10100, 0, -10, 10);
		Asteroid asteroid3 = facade.createAsteroid(10100, 10000, -10, 0, 10);
		Asteroid asteroid4 = facade.createAsteroid(10000, 9900, 0, 10, 10);
		Asteroid asteroid5 = facade.createAsteroid(9900, 10000, 10, 0, 10);
		Asteroid asteroid6 = facade.createAsteroid(1000, 1000, 10, 0, 100);
		Asteroid asteroid7 = facade.createAsteroid(10000, 10000, SPEED_OF_LIGHT, SPEED_OF_LIGHT, 100);
		Asteroid asteroid8 = facade.createAsteroid(10001, 10001, 0, 0, 100);
		Asteroid asteroid9 = facade.createAsteroid(0, 0, 0, 0, 10);
		Asteroid asteroid10 = facade.createAsteroid(29000.0, 10000.0, 200, 0, 200);
		Asteroid asteroid11 = facade.createAsteroid(10000, 29000, 0, 200, 200);
		Asteroid asteroid12 = facade.createAsteroid(10000, 10000, 1000, 1500, 1000);
		Asteroid asteroid13 = facade.createAsteroid(10000, 12050, 0, 0, 1000);

		Asteroid[] Total = { asteroid1, asteroid2, asteroid3, asteroid4, asteroid5, asteroid6, asteroid7, asteroid8,
				asteroid9, asteroid10, asteroid11, asteroid12, asteroid13 };
		return Total;
	}

	// CREATE PLANETOID 
	public Planetoid[] createPlanetoids() throws ModelException {
		Planetoid planetoid1 = facade.createPlanetoid(10000, 10000, 0, 0, 10, 0);
		Planetoid planetoid2 = facade.createPlanetoid(10000, 10100, 0, -10, 10, 0);
		Planetoid planetoid3 = facade.createPlanetoid(10100, 10000, -10, 0, 10, 0);
		Planetoid planetoid4 = facade.createPlanetoid(10000, 9900, 0, 10, 10, 0);
		Planetoid planetoid5 = facade.createPlanetoid(9900, 10000, 10, 0, 10, 0);
		Planetoid planetoid6 = facade.createPlanetoid(1000, 1000, 10, 0, 100, 0);
		Planetoid planetoid7 = facade.createPlanetoid(10000, 10000, SPEED_OF_LIGHT, SPEED_OF_LIGHT, 100, 0);
		Planetoid planetoid8 = facade.createPlanetoid(10001, 10001, 0, 0, 100, 0);
		Planetoid planetoid9 = facade.createPlanetoid(0, 0, 0, 0, 10, 0);
		Planetoid planetoid10 = facade.createPlanetoid(29000.0, 10000.0, 200, 0, 200, 0);
		Planetoid planetoid11 = facade.createPlanetoid(10000, 29000, 0, 200, 200, 0);
		Planetoid planetoid12 = facade.createPlanetoid(10000, 10000, 1000, 1500, 1000, 0);
		Planetoid planetoid13 = facade.createPlanetoid(10000, 12050, 10, 0, 5.0001, 0);

		Planetoid[] Total = { planetoid1, planetoid2, planetoid3, planetoid4, planetoid5, planetoid6, planetoid7,
				planetoid8, planetoid9, planetoid10, planetoid11, planetoid12, planetoid13 };
		return Total;
	}

	// CREATE WORLD 
	public World[] createWorlds() throws ModelException {
		World world1 = facade.createWorld(30000, 30000);
		World world2 = facade.createWorld(-40000, 30000);
		World[] Total = { world1, world2 };
		return Total;
	}

	
	/// TERMINATION ///

	// ASTEROID
	@Test
	public final void terminateAsteroid() throws ModelException {
		max_score += 1;
		World world = createWorlds()[0];
		
		Asteroid Asteroid = createAsteroids()[0];
		facade.addAsteroidToWorld(world, Asteroid);
		assert (facade.getAsteroidWorld(Asteroid) == world);
		
		facade.terminateAsteroid(Asteroid);
		assert (facade.isTerminatedAsteroid(Asteroid));
		assertEquals(0, facade.getEntities(world).size(), EPSILON);
		score += 1;
	}

	// PLANETOID
	@Test
	public final void terminatePlanetoid() throws ModelException {
		max_score += 1;
		World world = createWorlds()[0];
		
		Planetoid planetoid = createPlanetoids()[9];
		facade.addPlanetoidToWorld(world, planetoid);
		assert (facade.getPlanetoidWorld(planetoid) == world);
		
		facade.terminatePlanetoid(planetoid);
		assert (facade.isTerminatedPlanetoid(planetoid));
		assertEquals(2, facade.getEntities(world).size(), EPSILON);
		score += 1;
	}

	// WORLD
	@Test
	public final void terminateWorld() throws ModelException {
		max_score += 1;
		World world = createWorlds()[0];
		
		Ship Ship = createShips()[0];
		facade.addShipToWorld(world, Ship);
		assert (facade.getShipWorld(Ship) == world);
		
		Bullet Bullet = createBullets()[8];
		facade.addBulletToWorld(world, Bullet);
		assert (facade.getBulletWorld(Bullet) == world);
		
		Asteroid Asteroid = createAsteroids()[2];
		facade.addAsteroidToWorld(world, Asteroid);
		assert (facade.getAsteroidWorld(Asteroid) == world);
	
		Planetoid Planetoid = createPlanetoids()[3];
		facade.addPlanetoidToWorld(world, Planetoid);
		assert (facade.getPlanetoidWorld(Planetoid) == world);
		
		facade.terminateWorld(world);
		assert (facade.isTerminatedWorld(world));
		assertNull(facade.getBulletWorld(Bullet));
		assertNull(facade.getShipWorld(Ship));
		assertNull(facade.getAsteroidWorld(Asteroid));
		assertNull(facade.getPlanetoidWorld(Planetoid));
		score += 1;
	}

	/// GET MINOR PLANETS ///
	@Test
	public final void getMinorPlanets() throws ModelException {
		max_score += 1;
		World world = createWorlds()[0];
		Planetoid planetoid = facade.createPlanetoid(10000, 10000, 10, 10, 100, 0);
		Asteroid asteroid = facade.createAsteroid(20000, 20000, 0, 0, 50);
		facade.addPlanetoidToWorld(world, planetoid);
		facade.addAsteroidToWorld(world, asteroid);
		assert (2 == world.getWorldSpecificEntities("MinorPlanet").size());
		score += 1;
	}
	
	/// MOVE A PLANETOID ///
	@Test
	public final void moveAPlanetoid() throws ModelException{
		max_score+=1;
		World world = createWorlds()[0];
		Planetoid planetoid = createPlanetoids()[4];
		facade.addPlanetoidToWorld(world, planetoid);
		double radius = facade.getPlanetoidRadius(planetoid);
		assertEquals(10,radius,EPSILON);
		facade.evolve(world, 10, null);
		assertEquals(9.9999, facade.getPlanetoidRadius(planetoid),EPSILON);
		score +=1;
	}

	@Test
	public final void terminatePlanetoidByMoving() throws ModelException{
		max_score+=1;
		World world = createWorlds()[0];
		Planetoid planetoid = createPlanetoids()[12];
		facade.addPlanetoidToWorld(world, planetoid);
		facade.evolve(world, 20, null);
		assertTrue(facade.isTerminatedPlanetoid(planetoid));
		score +=1;
	}
	
	/// COLLISION BETWEEN SHIP AND ASTEROID ///
	
	@Test 
	public final void shipAndAsteroidCollide() throws ModelException{
		max_score +=1;
		World world = createWorlds()[0];
		Ship ship = createShips()[0];
		Asteroid asteroid = createAsteroids()[3];
		facade.addAsteroidToWorld(world, asteroid);
		facade.addShipToWorld(world, ship);
		facade.evolve(world, 10, null);
		assertFalse(facade.isTerminatedAsteroid(asteroid));
		assertTrue(facade.isTerminatedShip(ship));
		score +=1;
	}
	
	// COLLISION BETWEEN TWO MINOR PLANETS
	@Test
	public final void evolveCollisionPlanetoidPlanetoid() throws ModelException{
		max_score +=1;
		Planetoid planetoid1 = createPlanetoids()[0];
		Planetoid planetoid2 = createPlanetoids()[4];
		World world = createWorlds()[0];
		facade.addPlanetoidToWorld(world, planetoid1);
		facade.addPlanetoidToWorld(world, planetoid2);
		assertEquals(2,facade.getEntities(world).size(),EPSILON);
		facade.evolve(world, 9,null);
		assertFalse(facade.isTerminatedPlanetoid(planetoid1));
		assertFalse(facade.isTerminatedPlanetoid(planetoid2));
		score += 1;
	}
	
	@Test
	public final void evolveCollisionPlanetoidAsteroid() throws ModelException{
		max_score +=1;
		Planetoid planetoid = createPlanetoids()[0];
		Asteroid asteroid = createAsteroids()[4];
		World world = createWorlds()[0];
		facade.addPlanetoidToWorld(world, planetoid);
		facade.addAsteroidToWorld(world, asteroid);
		assertEquals(2,facade.getEntities(world).size(),EPSILON);
		facade.evolve(world, 9,null);
		assertFalse(facade.isTerminatedPlanetoid(planetoid));
		assertFalse(facade.isTerminatedAsteroid(asteroid));
		score += 1;
	}
	
	@Test
	public final void evolveCollisionAsteroidAsteroid() throws ModelException{
		max_score +=1;
		Asteroid asteroid1 = createAsteroids()[0];
		Asteroid asteroid2 = createAsteroids()[4];
		World world = createWorlds()[0];
		facade.addAsteroidToWorld(world, asteroid1);
		facade.addAsteroidToWorld(world, asteroid2);
		assertEquals(2,facade.getEntities(world).size(),EPSILON);
		facade.evolve(world, 9,null);
		assertFalse(facade.isTerminatedAsteroid(asteroid1));
		assertFalse(facade.isTerminatedAsteroid(asteroid2));
		score += 1;
	}
	
	
	/// 


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}