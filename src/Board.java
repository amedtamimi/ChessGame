public class Board {
    private static final AbstractPiece[][] board = new AbstractPiece[8][8];
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final PieceFactory pieceFactory;
    private AbstractPiece whiteKing, blackKing;
    private String winnerName;
    private static final Board INSTANCE = new Board("playerWhite", "playerBlack");

    private Board(String playerWhite,String playerBlack) {
        this.whitePlayer = new Player(playerWhite,Color.WHITE);
        this.blackPlayer = new Player(playerBlack,Color.BLACK);
        pieceFactory = new PieceFactory();
        createBlackPieces(blackPlayer.getColor());
        createWhitePieces(whitePlayer.getColor());
    }

    public static Board getInstance() {
        return INSTANCE;
    }



    public static AbstractPiece pieceAt(Location location) {
        return board[location.getFile()][location.getRank()];
    }

    public static boolean isPathClear(Location currentLocation, Location nextLocation) {
        int x1 = currentLocation.getFile();
        int y1 = currentLocation.getRank();
        int x2 = nextLocation.getFile();
        int y2 = nextLocation.getRank();
        int xDistance = x2 - x1;
        int yDistance = y2 - y1;
        int xDir = 0;
        int yDir = 0;
        int size;

        if (xDistance < 0) {
            xDir = -1;
        } else if (xDistance > 0) {
            xDir = 1;
        }
        if (yDistance < 0) {
            yDir = -1;
        } else if (yDistance > 0) {
            yDir = 1;
        }

        if (xDistance != 0) {
            size = Math.abs(xDistance) - 1;
        } else {
            size = Math.abs(yDistance) - 1;
        }
        for (int i = 0; i < size; i++) {
            x1 += xDir;
            y1 += yDir;
            if (x1 > 7 || y1 > 7) {
                return false;
            }
        }
        return true;

    }

    private void createWhitePieces(Color color) {
        board[7][0] = pieceFactory.createPiece(PieceType.ROOK, color, new Location(7, 0));
        board[7][1] = pieceFactory.createPiece(PieceType.KNIGHT, color, new Location(7, 1));
        board[7][2] = pieceFactory.createPiece(PieceType.BISHOP, color, new Location(7, 2));
        board[7][3] = pieceFactory.createPiece(PieceType.QUEEN, color, new Location(7, 3));
        whiteKing = pieceFactory.createPiece(PieceType.KING, color, new Location(7, 4));
        board[7][4] = whiteKing;
        board[7][5] = pieceFactory.createPiece(PieceType.BISHOP, color, new Location(7, 5));
        board[7][6] = pieceFactory.createPiece(PieceType.KNIGHT, color, new Location(7, 6));
        board[7][7] = pieceFactory.createPiece(PieceType.ROOK, color, new Location(7, 7));
        for (int i = 0; i < 8; i++) {
            board[6][i] = pieceFactory.createPiece(PieceType.PAWN, color, new Location(6, i));
        }
    }

    private void createBlackPieces(Color color) {
        board[0][0] = pieceFactory.createPiece(PieceType.ROOK, color, new Location(0, 0));
        board[0][1] = pieceFactory.createPiece(PieceType.KNIGHT, color, new Location(0, 1));
        board[0][2] = pieceFactory.createPiece(PieceType.BISHOP, color, new Location(0, 2));
        board[0][3] = pieceFactory.createPiece(PieceType.QUEEN, color, new Location(0, 3));
        blackKing = pieceFactory.createPiece(PieceType.KING, color, new Location(0, 4));
        board[0][4] = blackKing;
        board[0][5] = pieceFactory.createPiece(PieceType.BISHOP, color, new Location(0, 5));
        board[0][6] = pieceFactory.createPiece(PieceType.KNIGHT, color, new Location(0, 6));
        board[0][7] = pieceFactory.createPiece(PieceType.ROOK, color, new Location(0, 7));
        for (int i = 0; i < 8; i++) {
            board[1][i] = pieceFactory.createPiece(PieceType.PAWN, color, new Location(1, i));
        }
    }
    public static void displayBoard() {
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print((char) ('a' + i) + "  ");
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {

            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {

                if (board[i][j] != null) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("--" + " ");
                }
            }
            System.out.println();
        }
    }
    public boolean isGameOver()  {
        if ((AbstractPiece.isAllWhitePiecesKilled() && AbstractPiece.isAllBlackPiecesKilled()) || (AbstractPiece.isAllWhitePiecesMaxMoves() && AbstractPiece.isAllBlackPiecesMaxMoves())) {
            System.out.println("Draw");
            Board.displayBoard();
            return true;
        }
        if (whiteKing.isKilled()) {
            winnerName = blackPlayer.getPlayerName();
            Board.displayBoard();
            return true;
        } else if (blackKing.isKilled()) {
            winnerName = whitePlayer.getPlayerName();
            Board.displayBoard();
            return true;
        } else
            return false;
    }

    private boolean isTheKingWillSurvive(Location currentLocation, Location newLocation) {
        for (int i = newLocation.getFile() - 1; i <= newLocation.getFile() + 1; i++)
            for (int j = newLocation.getRank() - 1; j <= newLocation.getRank() + 1; j++) {
                if (i < 0 || i > 7 || j < 0 || j > 8) continue;
                if (board[i][j] == null && !pieceAt(currentLocation).legalMove(currentLocation, new Location(i, j)))
                    return true;

            }
        return false;
    }
    public void Checkmate(Location currentLocation) {
        if (pieceAt(currentLocation).getColor() == Color.WHITE) {
            if (pieceAt(currentLocation).legalMove(currentLocation, blackKing.getLocation())) {
                if (isTheKingWillSurvive(currentLocation, blackKing.getLocation())) {
                    System.out.println("Checkmate");
                }
                else {
                blackKing.setKilled(true);
                }
            }

        }
        else{
            if (pieceAt(currentLocation).legalMove(currentLocation, whiteKing.getLocation())) {
                if (isTheKingWillSurvive(currentLocation, whiteKing.getLocation())) {
                    System.out.println("Checkmate ");
                } else {
                    whiteKing.setKilled(true);
                }
            }


        }

    }
    public boolean move(Location currentLocation, Location newLocation) {
        if (pieceAt(currentLocation).legalMove(currentLocation, newLocation)) {
            if (pieceAt(newLocation) != null) {
                pieceAt(newLocation).setKilled(true);
            }
            Checkmate(currentLocation);
            board[newLocation.getFile()][newLocation.getRank()] = pieceAt(currentLocation);
            board[currentLocation.getFile()][currentLocation.getRank()] = null;
            pieceAt(newLocation).setLocation(newLocation);
            pieceAt(newLocation).setMaxMoves(true);
            return true;
        }
        System.out.println("invalid move, the piece can't move to this Location");
        return false;
    }

    public String getWinnerName() {
        return winnerName;
    }
}
