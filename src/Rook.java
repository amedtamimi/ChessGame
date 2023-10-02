public class Rook extends AbstractPiece {
    public Rook(Color color, Location location) {
        super(color, PieceType.ROOK, location);
    }

    @Override
    public boolean legalMove(Location currentLocation, Location nextLocation) {
        if (this.sameColor(Board.pieceAt(nextLocation))) {
            return false;
        } else if (Math.abs(currentLocation.getFile() - nextLocation.getFile()) != 0 && Math.abs(currentLocation.getRank() - nextLocation.getRank()) != 0) {
            return false;
        }

        return Board.isPathClear(currentLocation, nextLocation);
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "WR";
        }
        return "BR";
    }
}

