package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;

public class CommandGenerator {
/*
 * list of all available commands 
 */
	private static final List<Command> availableCommands = Arrays.asList(
		new HelpCommand(),
		new MoveCommand(),
		new ExitCommand(),
		new ListCommand(),
		new NoneCommand(),
		new ResetCommand(),
		new ShockwaveCommand(),
		new ShootCommand(),
		new SuperLaserCommand()
		//TODO fill with your code
	);

	public static Command parse(String[] commandWords) throws CommandParseException{
		Command command = null;
		for (Command c: availableCommands) {
			if (command == null)
				command = c.parse(commandWords);
		}
		if (command == null && commandWords.length > 0 && commandWords[0] == "")
			command = new NoneCommand();
		if (command == null)
			throw new CommandParseException(Messages.UNKNOWN_COMMAND);
		return command;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();	
		for (Command c: availableCommands) {
			commands.append(c.helpText());
		}
		return commands.toString();
	}

}
