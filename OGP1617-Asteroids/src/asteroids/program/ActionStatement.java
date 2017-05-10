package asteroids.program;

import java.awt.geom.IllegalPathStateException;
import java.util.List;
import java.util.Scanner;

import org.antlr.v4.parse.ANTLRParser.sync_return;

import com.sun.xml.internal.bind.v2.model.core.MaybeElement;

import asteroids.part3.programs.SourceLocation;

abstract class ActionStatement extends MyStatement {

	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs){
		setStatementProgram(program);
		
		if (getStatementProgram().getTimeLeft() >= getDecrementTime()){	
			getStatementProgram().setMayExecute();
			getStatementProgram().addTime(-getDecrementTime());
			execute(program);
		}

		else {
			getStatementProgram().setSourceLocation(getSourceLocation());
			getStatementProgram().setMayNotExecute();
			
			throw new IllegalPathStateException();
		}
	}
	
	@Override
	public Object evaluateInFunction(Program program, List<MyExpression> actualArgs,MyFunction function){
		throw new IllegalArgumentException();
	}
	
	@Override
	public void ignoreUntil(Program program, List<MyExpression> actualArgs, SourceLocation location) {
		if (getSourceLocation().equals(location)) {
			evaluate(program, actualArgs);
		}
		else {
			// nothing
		}
	}
	
	protected abstract void execute(Program program);
	
	protected double getDecrementTime(){
		return decrement_time;
	}
	
	private double decrement_time = 0.2;
	
	private SourceLocation sourcelocation;
	
	protected void setSourceLocation(SourceLocation location){
		sourcelocation = location;
	}
	
	protected SourceLocation getSourceLocation(){
		return sourcelocation;
	}
}
