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
		//TODO fill with your code
		return null;
	}


	@Override
	public Command parse(String[] commandWords) { // Pepe
		Command c = null;
        if ( commandWords.length > 1 && this.matchCommandName(commandWords[0])) {
        	c = this;
        	String command2 = commandWords[1].toUpperCase();
        	if (command2.equals("RIGHT") || command2.equals("LEFT") ||
					command2.equals("LLEFT") || command2.equals("RRIGHT") ||
					command2.equals("DOWN") || command2.equals("UP") ||
					command2.equals("NONE")) { 
				move = Move.valueOf(command2);
				c = this;
        	}
        }
	    return c;
	}

}
