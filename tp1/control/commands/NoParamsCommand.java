package tp1.control.commands;

public abstract class NoParamsCommand extends Command {

	@Override
	public Command parse(String[] commandWords) { //Pepe
		Command c = null;
		
		if (commandWords.length > 0 && matchCommandName(commandWords[0]))
			c = this;
		return c;
	}
	
}
