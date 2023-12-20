package tp1.control.commands;

import tp1.logic.GameModelException;

public class CommandExecuteException extends Exception{
	public CommandExecuteException(String message, GameModelException e) {
		super(message, e);
	}
}
