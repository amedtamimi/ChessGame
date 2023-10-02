import static java.lang.Math.abs;

public class Pawn extends AbstractPiece {
    boolean firstMove = true;

    public Pawn(Color color, Location location) {
        super(color, PieceType.PAWN, location);
    }

    @Override
    public boolean legalMove(Location currentLocation, Location nextLocation) {
        if (this.getColor() == Color.WHITE && currentLocation.getFile() - nextLocation.getFile() < 0 || this.getColor() == Color.BLACK && currentLocation.getFile() - nextLocation.getFile() > 0) {
            return false;
        } else if (this.firstMove && (currentLocation.getRank() - nextLocation.getRank() == 0 && abs(currentLocation.getFile() - nextLocation.getFile()) == 2) && (Board.isPathClear(currentLocation, nextLocation) && Board.pieceAt(nextLocation) == null)) {
            this.firstMove = false;
            return true;
        } else if (currentLocation.getRank() - nextLocation.getRank() == 0 && abs(currentLocation.getFile() - nextLocation.getFile()) == 1 && Board.pieceAt(nextLocation) == null) {
            this.firstMove = false;
            return true;
        }

        return abs(currentLocation.getRank() - nextLocation.getRank()) == 1 && abs(currentLocation.getFile() - nextLocation.getFile()) == 1 && Board.pieceAt(nextLocation) != null && !this.sameColor(Board.pieceAt(nextLocation));
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "WP";
        }
        return "BP";
    }
}
