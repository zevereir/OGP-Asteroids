package asteroids.part3.programs.internal.example;

import java.io.IOException;

import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.internal.ParseOutcome;
import asteroids.part3.programs.internal.ProgramParser;

public class ExamplePrinter {
	public static void main(String[] args) throws IOException {
		IProgramFactory<PrintingObject, PrintingObject, PrintingObject, PrintingProgram> factory = PrintingObjectFactory.create();
		ProgramParser<?, ?, ?, PrintingProgram> parser = ProgramParser.create(factory);

		//ParseOutcome<PrintingProgram> outcome = parser.parseFile("src-provided/asteroids/resources/programs/syntax_test.txt");
		//ParseOutcome<PrintingProgram> outcome = parser.parseFile("src-provided/asteroids/resources/programs/program_simple.txt");
		//ParseOutcome<PrintingProgram> outcome = parser.parseFile("src-provided/asteroids/resources/programs/program.txt");
		//ParseOutcome<PrintingProgram> outcome = parser.parseFile("src-provided/asteroids/resources/programs/program_assignment.txt");
		ParseOutcome<PrintingProgram> outcome = parser.parseString("turn 0.0001; "+ "print getdir;" + " turn 0.001; " + "print getdir; " + "turn 0.01;" + "print getdir;");

		System.out.println(outcome);	
		
		
	
		
	}

}
