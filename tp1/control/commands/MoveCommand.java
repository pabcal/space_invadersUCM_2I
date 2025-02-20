package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.logic.NotAllowedMoveException;
import tp1.logic.OffWorldException;
import tp1.view.Messages;

public class MoveCommand extends Command {
	private String command2 = null;
	private Move move;
	private boolean directionError = false;

	public MoveCommand() {}

	protected MoveCommand(Move move) {
		this.move = move;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException{
		boolean valid = false;
		try {
		valid = game.movePlayer(move);
		}
		catch (OffWorldException owe) {
			throw new CommandExecuteException(Messages.MOVEMENT_ERROR, owe);
		}
		catch (NotAllowedMoveException name) {
			throw new CommandExecuteException(Messages.DIRECTION_ERROR + command2, name);
		}
		
		
		
		return valid;
	}


	@Override
	public Command parse(String[] commandWords) throws CommandParseException
	{
		Command c = null;
        if ( commandWords.length > 0 && this.matchCommandName(commandWords[0])) 
        {
        	if (commandWords.length == 2)
        	{	try {
	            	command2 = commandWords[1].toUpperCase();
	            	move = Move.getMovement(command2);
	            	if (move == null || move == Move.UP || move == Move.DOWN) {
	            		directionError = true;
	            	}
	            	else
	            		directionError = false;
	            	c = this;
        		}
	        	catch (IllegalArgumentException e) {
	        		throw new CommandParseException(Messages.DIRECTION_ERROR + command2);
	        	}

        	}
        	else if (commandWords.length > 2)
        		throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
        }
	    return c;
	}

}
