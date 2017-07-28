import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextClient {
	public static List<BoardPiece> boardPieces = new ArrayList<>();
	static Board board = new Board();
	static public Set<String> movement = new HashSet<String>();
	static public Set<Integer> rotations = new HashSet<Integer>();

	/**
	 * Get string from System.in
	 */
	private static String inputString(String msg) {
		System.out.print(msg + " ");
		while (1 == 1) {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			try {
				return input.readLine();
			} catch (IOException e) {
				System.out.println("I/O Error ... please try again!");
			}
		}
	}

	public static void createToken(Player player, String options) {
		if (player == null || options == null) {
			throw new NullPointerException();
		}
		String[] tokens = options.split(" ");
		if (tokens.length != 3) {
			System.out.println("The format is incorrect. It should be create <letter> <0/90/180/270>");
		}
		String createMessage = tokens[0];
		String letter = tokens[1];
		int rotation = Integer.parseInt(tokens[2]);
		if (rotations.contains(rotation)) {
			if (letter.length() == 1) {
				BoardPiece bp = null;
				for (BoardPiece b : boardPieces) {
					if (b.name.equals(letter)) {
						bp = b;
					}
				}
				System.out.println("Attempting to place : " + bp.toString());
				if (player.addToken(bp, player.name, board) == false) {
					return;
				}
				else {
					board.redraw();
				}
			}
		} else {
			System.out.println("error");
		}
	}

	public static void moveToken(Player player, String options) {
		if (player == null || options == null) {
			throw new NullPointerException();
		}
		String s = options;
		String[] tokens = s.split(" ");
		if (tokens.length != 3) {
			// throw an error.....
		}
		String moveMessage = tokens[0];
		String letter = tokens[1];
		String direction = tokens[2];
		if (movement.contains(direction)) {
			if (letter.length() == 1) {
				BoardPiece bp = null;
				for (BoardPiece b : boardPieces) {
					if (b.name.equals(letter)) {
						bp = b;
					}
				}
				System.out.println("Attempting to move : " + bp.toString());
				if(player.moveToken(player, bp, direction, board)) {
					board.redraw();
				}
				else {
					System.out.println("error");
				}

			}
		}
		else {
			System.out.println("Error");
		}

	}

	public static void rotateToken(Player player, String options) {

	}

	public static void playerOptions(Player player) {
		while (1 == 1) {
			try {
				String options = inputString(
						"[create <letter> <0/90/180/270> / rotate <letter> <1-4> / move <letter> <up/right/down/left> / pass");
				if (options.startsWith("create")) {
					createToken(player, options);
				} else if (options.startsWith("rotate")) {
					rotateToken(player, options);
				} else if (options.startsWith("move")) {
					moveToken(player, options);
				} else if (options.startsWith("pass")) {
					System.out.println("Next persons turn");
					return;
				} else {
					System.out.println("Invalid option....");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		// Create players
		Player green = new Player("green");
		Player yellow = new Player("yellow");

		int turn = 0;
		generatePieces();

		board.initialise();
		board.addPlayers(green, yellow);
		board.redraw();
		initialiseStructures();

		System.out.println("~*~*~ Sword & Shield ~*~*~");
		// while(1==1) {//loop forever
		System.out.println("\n********************");
		System.out.println("***** TURN " + turn + " *******");
		System.out.println("********************\n");
		if (turn % 2 == 0) {
			System.out.println("It is yellows turn");
			playerOptions(yellow);
			board.redraw();
			// drawPieces();
		} else {
			// greens turn

		}
		// }

	}

	public static void drawPieces() {
		for (BoardPiece bp : boardPieces) {
			System.out.println(bp.toString());
		}
	}

	public static void generatePieces() {
		boardPieces.add(new BoardPiece("a", 1, 2, 1, 1));
		boardPieces.add(new BoardPiece("b", 1, 0, 1, 1));
		boardPieces.add(new BoardPiece("c", 2, 2, 2, 2));
		boardPieces.add(new BoardPiece("d", 1, 0, 0, 0));
		// empty tile??
		//
		boardPieces.add(new BoardPiece("e", 0, 0, 0, 0));
		boardPieces.add(new BoardPiece("f", 1, 0, 0, 1));
		boardPieces.add(new BoardPiece("g", 1, 1, 1, 1));
		boardPieces.add(new BoardPiece("h", 1, 0, 2, 2));
		boardPieces.add(new BoardPiece("i", 0, 2, 0, 0));
		//
		boardPieces.add(new BoardPiece("j", 1, 2, 1, 2));
		boardPieces.add(new BoardPiece("k", 1, 2, 0, 1));
		boardPieces.add(new BoardPiece("l", 1, 0, 0, 0));
		boardPieces.add(new BoardPiece("m", 1, 2, 2, 0));
		boardPieces.add(new BoardPiece("n", 0, 2, 2, 0));
		//
		boardPieces.add(new BoardPiece("o", 1, 0, 1, 2));
		boardPieces.add(new BoardPiece("p", 1, 0, 2, 1));
		boardPieces.add(new BoardPiece("q", 1, 0, 0, 2));
		boardPieces.add(new BoardPiece("r", 1, 2, 0, 2));
		boardPieces.add(new BoardPiece("s", 0, 2, 0, 2));
		//
		boardPieces.add(new BoardPiece("t", 1, 0, 1, 0));
		boardPieces.add(new BoardPiece("u", 1, 0, 0, 1));
		boardPieces.add(new BoardPiece("v", 1, 2, 0, 0));
		boardPieces.add(new BoardPiece("w", 1, 2, 2, 2));
		boardPieces.add(new BoardPiece("x", 0, 2, 2, 2));
	}

	public static void initialiseStructures() {
		movement.add("up");
		movement.add("right");
		movement.add("down");
		movement.add("left");
		rotations.add(0);
		rotations.add(90);
		rotations.add(180);
		rotations.add(270);
	}

	public static Board getBoard() {
		return board;
	}
}
