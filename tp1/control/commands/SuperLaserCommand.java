package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.logic.LaserInFlightException;
import tp1.logic.NotEnoughPointsException;
import tp1.view.Messages;

public class SuperLaserCommand extends NoParamsCommand {
	@Override
	protected String getName() {
		return Messages.COMMAND_SUPERLASER_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SUPERLASER_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_SUPERLASER_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_SUPERLASER_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException{
		boolean valid = false;

		try { 
			valid = game.enableSuperLaser();
		}
		catch(NotEnoughPointsException | LaserInFlightException nep) {
			throw new CommandExecuteException(Messages.LASER_ERROR, nep);
		}
		
		return valid;
	}
}

