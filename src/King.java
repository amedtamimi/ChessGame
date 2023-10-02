public class King extends AbstractPiece{
public King(Color color, Location location) {
        super(color, PieceType.KING, location);
    }


    @Override
    public boolean legalMove(Location currentLocation, Location nextLocation) {
        if (this.sameColor(Board.pieceAt(nextLocation))) {
            return false;
        }

        else if (Math.abs(currentLocation.getFile() - nextLocation.getFile()) == 1 && (Math.abs(currentLocation.getRank() - nextLocation.getRank()) == 1)) {
            return true;
        }

        return Math.abs(currentLocation.getFile() - nextLocation.getFile()) == 1 && Math.abs(currentLocation.getRank() - nextLocation.getRank()) == 0 || Math.abs(currentLocation.getFile() - nextLocation.getFile()) == 0 && Math.abs(currentLocation.getRank() - nextLocation.getRank()) == 1;
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "WK";
        }
        return "BK";
    }
}
