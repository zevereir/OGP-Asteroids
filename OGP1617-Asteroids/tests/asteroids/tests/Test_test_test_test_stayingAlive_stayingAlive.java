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
	
	
	
	
	
	
//	 @Test
//	  public void testWhileStatement_InsideRecursiveFunction() throws ModelException {
//	    max_score += 1;
//	    String code = 	" def sumfac { " + 
//	    				"  a := $1; " + 
//	    				"  t := 1.0; " + 
//	    				"  while 1.5 < a { " + 
//	    				"    t := t + (a*sumfac(a + -1.0));" + 
//	    				"    a := a + -1.0; " + 
//	    				"  }" + 
//	    				"  return t; " + 
//	    				"} " + 
//	    				"print sumfac(4.0); ";
//	    Program program = ProgramParser.parseProgramFromString(code, programFactory);
//	    facade.loadProgramOnShip(ship1, program);
//	    List<Object> results = facade.executeProgram(ship1, 0.3);
//	    Object[] expecteds = { 60.0 };
//	    assertArrayEquals(expecteds, results.toArray());
//	    score += 1;
//	  }	
	 
//	 @Test
//	  public void testLocalVariableAddition() throws ModelException {
//	    max_score += 1;
//	    String code = 	" def f { " + 
//	    				"  a := $1; " +
//	    				"  return 2 + a; " + 
//	    				"} " + 
//	    				"print f(4.0); ";
//	    Program program = ProgramParser.parseProgramFromString(code, programFactory);
//	    facade.loadProgramOnShip(ship1, program);
//	    List<Object> results = facade.executeProgram(ship1, 0.3);
//	    Object[] expecteds = { 6.0 };
//	    assertArrayEquals(expecteds, results.toArray());
//	    score += 1;
//	  }	
	 
//	 @Test
//	  public void testSimpleWhileStatement() throws ModelException {
//	    max_score += 1;
//	    String code = 	" def f { " + 
//	    				"  a := $1; " +  
//	    				"  while 1.5 < a { " + 
//	    				"    a := a + -1.0; " + 
//	    				"  }" + 
//	    				"  return 60; " + 
//	    				"} " + 
//	    				"print f(2.0); ";
//	    Program program = ProgramParser.parseProgramFromString(code, programFactory);
//	    facade.loadProgramOnShip(ship1, program);
//	    List<Object> results = facade.executeProgram(ship1, 1.0);
//	    Object[] expecteds = { 60.0 };
//	    assertArrayEquals(expecteds, results.toArray());
//	    score += 1;
//	  }
	 
//	 @Test
//	  public void testWhileBreak() throws ModelException {
//	    max_score += 1;
//	    String code = 	" def f { " + 
//	    				"  a := $1; " +  
//	    				"  while 1.5 < a { " + 
//	    				"    break; " + 
//	    				"  }" + 
//	    				"  return 60; " + 
//	    				"} " + 
//	    				"print f(2.0); ";
//	    Program program = ProgramParser.parseProgramFromString(code, programFactory);
//	    facade.loadProgramOnShip(ship1, program);
//	    List<Object> results = facade.executeProgram(ship1, 1.0);
//	    Object[] expecteds = { 60.0 };
//	    assertArrayEquals(expecteds, results.toArray());
//	    score += 1;
//	  }
	 
}
