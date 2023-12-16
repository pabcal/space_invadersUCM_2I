package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.GameModel;
import tp1.view.Messages;
import tp1.control.InitialConfiguration;

public class ResetCommand extends Command{
	InitialConfiguration initial = null;
	private boolean wrongInitialConfiguration = false;
	private String parameterString = null;
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
	public ExecutionResult execute(GameModel game) {
		boolean ok = false;
		String message = null;
		if (!wrongInitialConfiguration) {
			game.reset(initial);
			ok = true;
		}
		else
			message = "Invalid initial configuration " + parameterString;
		wrongInitialConfiguration = false;
		return new ExecutionResult(ok, ok, message);
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command c = null;
        if (this.matchCommandName(commandWords[0])) 
        {
        	if (commandWords.length > 1)
        	{
            	initial = InitialConfiguration.valueOfIgnoreCase(commandWords[1]);
            	if (initial == null) {
            		wrongInitialConfiguration = true;
            		parameterString = commandWords[1];
            	}
            		
        	}
        	else
        		initial = null;
        	c = this;
        }
	    return c;
	}
	
	
}
