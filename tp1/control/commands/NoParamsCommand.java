package tp1.control.commands;

import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		Command c = null;
		
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == 1)
				c = this;
			else
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}

		return c;
	}
	
}
