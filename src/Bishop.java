public class Bishop extends AbstractPiece {
    public Bishop(Color color, Location location) {
        super(color, PieceType.BISHOP, location);
    }

    @Override
    public boolean legalMove(Location currentLocation, Location nextLocation) {
        if (this.sameColor(Board.pieceAt(nextLocation))) {

            return false;
        } else if (Math.abs(currentLocation.getFile() - nextLocation.getFile()) != Math.abs(currentLocation.getRank() - nextLocation.getRank())) {
            return false;
        } else {
            return Board.isPathClear(currentLocation, nextLocation);

        }
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "WB";
        }
        return "BB";
    }
}



