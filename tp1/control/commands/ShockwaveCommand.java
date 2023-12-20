package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.logic.NoShockWaveException;
import tp1.view.Messages;

public class ShockwaveCommand extends NoParamsCommand{

	@Override
	protected String getName() {
		return Messages.COMMAND_SHOCKWAVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SHOCKWAVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_SHOCKWAVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_SHOCKWAVE_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException{
		boolean shot = false;
		try {
			shot = game.shootShockwave();
		}
		catch (NoShockWaveException e) {
			throw new CommandExecuteException(Messages.SHOCKWAVE_ERROR, null);
		}
		return shot;
	}

	
	
	
	
	
	
}
