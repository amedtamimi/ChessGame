import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ChessGame {
    static Scanner scanner = new Scanner(System.in);
    static String whitePlayerName;
    static String blackPlayerName;
    private final Board board;
    private boolean isWhiteTurn;
    private static ChessGame game;

    public ChessGame(String whitePlayerName, String blackPlayerName) {
        board = Board.getInstance();
        isWhiteTurn = true;
    }
    public static void startGame() throws UnsupportedEncodingException {
        System.out.println("welcome to Chess Game");
        System.out.print("Enter the White player Name :");
        whitePlayerName = scanner.nextLine();
        System.out.print("Enter the Black player Name :");
        blackPlayerName = scanner.nextLine();
        game = new ChessGame(whitePlayerName, blackPlayerName);

        while (!game.isFinish()) {
            Board.displayBoard();
            String move = readMoveFromConsole();
            if (game.isWhiteTurn())

                game.playWhite(move, Color.WHITE);
            else
                game.playBlack(move, Color.BLACK);
        }
        game.printWinnerName();
    }
    public boolean isFinish()  {
        return board.isGameOver();
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    private static boolean isLegalMove(String move) {
        return Pattern.matches("move\\s+([a-h]\\d)\\s+([a-h]\\d)", move.toLowerCase());
    }
    private boolean isLegalMove(String move, Color color) {
        String currentLoc, newLoc;
        currentLoc = move.split(" ")[1];
        newLoc = move.split(" ")[2];
        Location currentLocation = new Location(currentLoc.toUpperCase());
        Location newLocation = new Location(newLoc.toUpperCase());
        if (Board.pieceAt(currentLocation) == null) {
            System.out.println("there is no piece here ");
            return false;
        }
        if (Board.pieceAt(currentLocation).getColor() != color) return false;
        return board.move(currentLocation, newLocation);
    }

    private static String readMoveFromConsole() {

        while (true) {
            if (game.isWhiteTurn()) {
                System.out.print("your turn " + whitePlayerName + ": ");

            } else {
                System.out.print("your turn " + blackPlayerName + ": ");
            }
           String  move = scanner.nextLine();
            if (move.isEmpty()) {
                continue;
            }
            if (isLegalMove(move)) {
                return move;
            }
            System.out.println("invalid move, please try again ");
        }
    }


    public void playBlack(String move, Color color) {
        if (isLegalMove(move, color))

            isWhiteTurn = true;
        else
            System.out.println("invalid move");
    }
    public void playWhite(String move, Color color) {
        if (isLegalMove(move, color))
            isWhiteTurn = false;
        else
            System.out.println("invalid move");
    }

    public void printWinnerName()
    {
        System.out.println("the winner is " + board.getWinnerName());
    }
}
