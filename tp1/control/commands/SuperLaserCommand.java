package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.GameModel;

public class SuperLaserCommand extends NoParamsCommand {
	@Override
	protected String getName() {
		return "super laser";
	}

	@Override
	protected String getShortcut() {
		return "sl";
	}

	@Override
	protected String getDetails() {
		return "[s]uper[L]aser";
	}

	@Override
	protected String getHelp() {
		return "shoots a super laser when player has enough points";
	}

	@Override
	public ExecutionResult execute(GameModel game) {
		boolean valid = game.enableSuperLaser();

		return new ExecutionResult(valid, valid, "Super laser cannot be shot");
	}
}

