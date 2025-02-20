package tp1.control;

import static tp1.view.Messages.debug;

import java.util.Scanner;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.view.GamePrinter;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;
	private Move move;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new GamePrinter(game);
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic
	 */
	public void run() {
		this.printGame();
//		this.game.listND();
		String[] arrayString;
		String command = "";
		String command2 = "";
		int prevCycle = 0;
		int nSubString;
		boolean end = false;
		boolean reset = false;
		while (!command.equals("e") && !end) {
			
			arrayString = prompt();
			if (arrayString.length > 0) {
				command = arrayString[0].toLowerCase();
				if (command.length() > 1 && command.equals("move") || command.equals("none") ||
						command.equals("help") || command.equals("list") ||
						command.equals("shoot") || command.equals("reset") ||
						command.equals("exit") || command.equals("shockwave")) {
					nSubString = (command.equals("shockwave") ? 6: 1);
					command = command.substring(nSubString - 1, nSubString);
				}
				else if (command.length() > 1)
					System.out.println(Messages.UNKNOWN_COMMAND);
				//else error message 
				if (command.length() <= 1) {
					switch (command) { 
						case "m":
							if (arrayString.length > 1) { //Checks if the prompt() returned more than 1 string 
								command2 = arrayString[1].toUpperCase();
								if (command2.equals("RIGHT") || command2.equals("LEFT") ||
										command2.equals("LLEFT") || command2.equals("RRIGHT") ||
										command2.equals("DOWN") || command2.equals("UP") ||
										command2.equals("NONE")) { 
									move = Move.valueOf(command2);
									if(this.game.movePlayer(move)) { 
										this.game.incrCycle();
									}
									else
										System.out.println(Messages.MOVEMENT_ERROR);
								}
								else {
									System.out.println(Messages.DIRECTION_ERROR); 
								}	                                                                                 
								
	
							}
							else
								System.out.println(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER); 
							break;
						case "n": 
							this.game.incrCycle();
							break;
						case "s":
							if (this.game.enableLaser())
								this.game.incrCycle();
							else
								System.out.println(Messages.LASER_ERROR);
							break;
						case "h":
							//System.out.println(Messages.HELP);
							
							break;
						case "l":
							this.game.listCommand();
							break;
						case "r":
							this.game.reset();
							prevCycle = -1;
							reset = true;
							break;
						case "w":
							this.game.shootShockwave();
							this.game.incrCycle();
						case "":
							this.game.incrCycle();
							break;
						default:
							System.out.println(Messages.UNKNOWN_COMMAND);
							break;
							
					}
				}
			}
			if (prevCycle != this.game.getCycle()) {
				if (!reset) {
					this.game.update();
				}
				else
					reset = false;
				if (this.game.playerWin() || this.game.aliensWins())
					end = true;
				this.printGame();
				
				
				prevCycle = this.game.getCycle();
			}
				
		}
		printEndMessage();
		System.out.println("Exit program");
	}

	/**
	 * Draw / paint the game
	 */
	private void printGame() {
		System.out.println(printer);
	}
	
	/**
	 * Prints the final message once the game is finished
	 */
	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}
	
}
