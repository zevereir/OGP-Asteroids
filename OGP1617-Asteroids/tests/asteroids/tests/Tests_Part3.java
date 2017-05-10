package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import asteroids.model.*;
import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.program.Program;
import asteroids.program.ProgramFactory;
import asteroids.util.ModelException;

/**
 * A test class for the class Ship.
 * 
 * @version 10th of march
 * @authors Sieben Bocklandt and Ruben Broekx
 *
 */
public class Tests_Part3 {

	static int nbStudentsInTeam;
	IFacade facade;
	IProgramFactory<?, ?, ?, Program> programFactory = new ProgramFactory();
	World filledWorld;
	Bullet bullet1;
	static int score = 0;
	static int max_score = 0;

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Score: " + score + "/" + max_score);
	}

	@Before
	public void setUp() throws ModelException {
		facade = new asteroids.facade.Facade();
		nbStudentsInTeam = facade.getNbStudentsInTeam();
	}

	/// CONSTANTS ///

	private final static double SPEED_OF_LIGHT = 300000;
	private static final double EPSILON = 0.0001;
	  private static final double BIG_EPSILON = 1.0E14;
	  private static final double VERY_BIG_EPSILON = 1.0E34;

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
		Planetoid planetoid13 = facade.createPlanetoid(10000, 12050, 0, 0, 1000, 0);

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
	
	// SHIP
	@Test
	public final void terminateShip() throws ModelException {
		max_score += 1;
		World world = createWorlds()[0];
		
		Ship Ship = createShips()[0];
		facade.addShipToWorld(world, Ship);
		assert (facade.getShipWorld(Ship) == world);
		
		facade.terminateShip(Ship);
		assert (facade.isTerminatedShip(Ship));
		assertEquals(0, facade.getEntities(world).size(), EPSILON);
		score += 1;
	}

	// BULLET
	@Test
	public final void terminateBullet() throws ModelException {
		max_score += 1;
		World world = createWorlds()[0];
		
		Bullet Bullet = createBullets()[0];
		facade.addBulletToWorld(world, Bullet);
		assert (facade.getBulletWorld(Bullet) == world);
		
		facade.terminateBullet(Bullet);
		assert (facade.isTerminatedBullet(Bullet));
		assertEquals(0, facade.getEntities(world).size(), EPSILON);
		score += 1;
	}

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
		
		Planetoid planetoid = createPlanetoids()[0];
		facade.addPlanetoidToWorld(world, planetoid);
		assert (facade.getPlanetoidWorld(planetoid) == world);
		
		facade.terminatePlanetoid(planetoid);
		assert (facade.isTerminatedPlanetoid(planetoid));
		assertEquals(0, facade.getEntities(world).size(), EPSILON);
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
		
		Bullet Bullet = createBullets()[0];
		facade.addBulletToWorld(world, Bullet);
		assert (facade.getBulletWorld(Bullet) == world);
		
		Asteroid Asteroid = createAsteroids()[2];
		facade.addAsteroidToWorld(world, Asteroid);
		assert (facade.getAsteroidWorld(Asteroid) == world);
	
		Planetoid planetoid = createPlanetoids()[3];
		facade.addPlanetoidToWorld(world, planetoid);
		assert (facade.getPlanetoidWorld(planetoid) == world);
		
		assert (facade.isTerminatedWorld(world));
		assert (facade.isTerminatedShip(Ship));
		assert (facade.isTerminatedBullet(Bullet));
		assert (facade.isTerminatedAsteroid(Asteroid));
		assert (facade.isTerminatedPlanetoid(planetoid));
		score += 1;
	}

	/// GET MINOR PLANETS ///
	@Test
	public final void getPlanets() throws ModelException {
		World world = createWorlds()[0];
		Planetoid planetoid = facade.createPlanetoid(10000, 10000, 10, 10, 100, 0);
		Asteroid asteroid = facade.createAsteroid(20000, 20000, 0, 0, 50);
		facade.addPlanetoidToWorld(world, planetoid);
		facade.addAsteroidToWorld(world, asteroid);
		assert (2 == world.getWorldSpecificEntities("MinorPlanet").size());
	}

}