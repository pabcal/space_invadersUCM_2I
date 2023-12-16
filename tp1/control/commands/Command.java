package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.GameModel;

/**
 * Represents a user action in the game.
 *
 */
public abstract class Command {
	  /*
	   * Getter method for the command's name
	   */
	  protected abstract String getName();
	  /*
	   * Getter method for the command's shortcut
	   */
	  protected abstract String getShortcut();
	  /*
	   * Getter method for the command's Details
	   */
	  protected abstract String getDetails();
	  /*
	   * Getter method for the command's help text
	   */
	  protected abstract String getHelp();
	  
	  /**
		 * Execute the command.
		 * 
		 * @param game Where to execute the command.
		 * 
		 * @return {@code ExecutionResult} representing if command was successful and if board must be printed
		 */
	  /*
	   * Checks if the execution was a success, prints the board if told to, and if the execution wasnt a success
	   * it prints the error message
	   */
	  public abstract ExecutionResult execute(GameModel game);	  
	  /*
	   * returns the command that matches the input commandwords
	   * if no such command exists, it returns null
	   */
	  public abstract Command parse(String[] commandWords) throws CommandParseException;
	  /*
	   * returns true if the input name equals a command's shortcut or name
	   * false if otherwise 
	   */
	  protected boolean matchCommandName(String name) {
		    return getShortcut().equalsIgnoreCase(name) || 
		        getName().equalsIgnoreCase(name);
	  }
	  /*
	   * returns help text 
	   */
	  public String helpText(){ 
	    return getDetails() + " : " + getHelp() + "\n";
	  }
}
