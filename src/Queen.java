public class Queen extends AbstractPiece {
    public Queen(Color color, Location location) {
        super(color, PieceType.QUEEN, location);
    }

    @Override
    public boolean legalMove(Location currentLocation, Location nextLocation) {
        if (this.sameColor(Board.pieceAt(nextLocation))) {
            return false;
        } else if (Math.abs(currentLocation.getFile() - nextLocation.getFile()) != Math.abs(currentLocation.getRank() - nextLocation.getRank())) {
            return true;
        } else if (Math.abs(currentLocation.getFile() - nextLocation.getFile()) != 0 && Math.abs(currentLocation.getRank() - nextLocation.getRank()) != 0) {
            return true;
        }

        return Board.isPathClear(currentLocation, nextLocation);
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "WQ";
        }
        return "BQ";
    }
}

