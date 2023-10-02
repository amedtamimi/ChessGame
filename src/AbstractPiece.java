public abstract class AbstractPiece implements Movable {
    private final Color color;
    private Location location;
    private final PieceType pieceType;
    private boolean killed;

    private boolean move;
    private static int whitePiecesDied = 0;
    private static int blackPiecesDied = 0;

    private static int whiteMaxMoves = 0;
    private static int blackMaxMoves = 0;

    public AbstractPiece(Color color, PieceType pieceType, Location location) {
        this.color = color;
        this.pieceType = pieceType;
        this.location = location;
        this.killed = false;
    }

    public Color getColor() {
        return color;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public boolean isKilled() {
        return killed;
    }
    public boolean isMove(){
        return move;
    }
    public boolean sameColor(AbstractPiece otherPiece) {
        if (otherPiece == null) {
            return false;
        }
        return (this.color == otherPiece.getColor());
    }

    public void setKilled(boolean killed) {
        if (this.color == Color.WHITE)
            whitePiecesDied++;
        else blackPiecesDied++;
        this.killed = killed;
    }
    public void setMaxMoves(boolean move) {
        if (this.color == Color.WHITE)
            whiteMaxMoves++;
        else blackMaxMoves++;
        this.move =  move;
    }

    public static boolean isAllWhitePiecesKilled() {
        return whitePiecesDied == 15;
    }

    public static boolean isAllBlackPiecesKilled() {
        return blackPiecesDied == 15;
    }

    public static boolean isAllWhitePiecesMaxMoves() {
        return whiteMaxMoves == 25;
    }

    public static boolean isAllBlackPiecesMaxMoves() {
        return blackMaxMoves == 25;
    }


}
