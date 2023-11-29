package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {

	private Move move;

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
	public ExecutionResult execute(Game game) {
		boolean valid = game.movePlayer(move);
		
		
		
		return new ExecutionResult(valid, valid, Messages.MOVEMENT_ERROR);
	}


	@Override
	public Command parse(String[] commandWords) 
	{
		Command c = null;
        if ( commandWords.length > 0 && this.matchCommandName(commandWords[0])) 
        {
        	if (commandWords.length > 1)
        	{
            	String command2 = commandWords[1].toUpperCase();
            	move = Move.getMovement(command2); //preguntar
            	if (move == null)
            		System.out.println(Messages.DIRECTION_ERROR);
            	c = this;

        	}
        }
	    return c;
	}

}
