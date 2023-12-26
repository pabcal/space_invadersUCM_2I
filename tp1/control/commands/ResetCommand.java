package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.logic.InitializationException;
import tp1.view.Messages;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp1.control.InitialConfiguration;

public class ResetCommand extends Command{
	InitialConfiguration initial = null;
	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException {
		boolean ok = false;
			try {
				game.reset(initial);
				ok = true;
			}
			catch (InitializationException ie){
				throw new CommandExecuteException("Invalid initial configuration" , ie);
			}
		return ok;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command c = null;
        if (this.matchCommandName(commandWords[0])) 
        {
        	if (commandWords.length == 2)
        	{
            	try {
					initial = InitialConfiguration.readFromFile(commandWords[1] + ".txt");
				} catch (FileNotFoundException e) {
					throw new CommandParseException("File not found: " + "'" + commandWords[1] + "'");					
				} catch (IOException e) {
					throw new CommandParseException("IO Exception");				
				}
            		
        	}
        	else if (commandWords.length > 2)
        		throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
        	else
        		initial = InitialConfiguration.NONE;
        	c = this;
        }
	    return c;
	}
	
	
}
