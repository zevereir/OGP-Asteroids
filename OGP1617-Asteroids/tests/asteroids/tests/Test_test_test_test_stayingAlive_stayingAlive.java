package asteroids.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import asteroids.model.*;
import asteroids.facade.Facade;
import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.internal.ProgramParser;
import asteroids.program.Program;
import asteroids.program.ProgramFactory;
import asteroids.util.ModelException;


/**
 * DO NOT COMMIT THIS TEST-FILE
 * 
 * @version 5th of may
 * @authors Sieben Bocklandt and Ruben Broekx
 *
 */
public class Test_test_test_test_stayingAlive_stayingAlive {

	
	private static final double EPSILON = 0.0001;
	private static final double BIG_EPSILON = 1.0E14;
	private static final double VERY_BIG_EPSILON = 1.0E34;
		
	static int nbStudentsInTeam;
	IFacade facade;
	IProgramFactory<?, ?, ?, Program> programFactory = new ProgramFactory();
	World filledWorld;
	Ship ship1, ship2, ship3;
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
		filledWorld = facade.createWorld(2000, 2000);
		ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
		for (int i = 1; i < 10; i++) {
			Bullet bulletToLoad = facade.createBullet(100, 120, 0, 0, 10);
			facade.loadBulletOnShip(ship1, bulletToLoad);
		}
		facade.addShipToWorld(filledWorld, ship1);
		ship2 = facade.createShip(200, 220, 10, 5, 50, 0, 1.0E20);
		facade.addShipToWorld(filledWorld, ship2);
		bullet1 = facade.createBullet(300, 320, 10, 5, 50);
		facade.addBulletToWorld(filledWorld, bullet1);
	}
	
 
	@Test
	  public void testSkipStatement_NotEnoughTimeLeft() throws ModelException {
	    max_score += 3;
	    String code = "skip; " + "print 0.4;";
	    Program program = ProgramParser.parseProgramFromString(code, programFactory);
	    facade.loadProgramOnShip(ship1, program);
	    List<Object> results = facade.executeProgram(ship1, 0.1);
	    assertNull(results);
	    score += 3;
	  }
	
//	@Test
//	  public void testThrusterOffStatement_EnoughTimeLeft() throws ModelException {
//	    if (nbStudentsInTeam > 1) {
//	      max_score += 3;
//	      String code = "thrust; " + "print 0.4; " + "thrust_off; " + "print 0.8; ";
//	      Program program = ProgramParser.parseProgramFromString(code, programFactory);
//	      facade.loadProgramOnShip(ship1, program);
//	      facade.executeProgram(ship1, 0.3);
//	      assertTrue(facade.isShipThrusterActive(ship1));
//	      List<Object> results = facade.executeProgram(ship1, 0.35);
//	      assertFalse(facade.isShipThrusterActive(ship1));
//	      Object[] expecteds = { 0.4, 0.8 };
//	      System.out.println("A: "+results);
//	      assertArrayEquals(expecteds, results.toArray());
//	      score += 3;
//	    }
//	  }
//
//	  @Test
//	  public void testThrusterOffStatement_NotEnoughTimeLeft() throws ModelException {
//	    if (nbStudentsInTeam > 1) {
//	      max_score += 3;
//	      String code = "thrust; " + "print 0.4; " + "thrust_off; " + "print 0.8;";
//	      Program program = ProgramParser.parseProgramFromString(code, programFactory);
//	      facade.loadProgramOnShip(ship1, program);
//	      facade.executeProgram(ship1, 0.2);
//	      assertTrue(facade.isShipThrusterActive(ship1));
//	      List<Object> results = facade.executeProgram(ship1, 0.15);
//	      assertTrue(facade.isShipThrusterActive(ship1));
//	      System.out.println(results);
//	      assertNull("B: "+results);
//	      score += 3;
//	    }
//	  }

}
