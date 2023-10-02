public class Knight extends AbstractPiece{
    public Knight(Color color, Location location) {
        super(color, PieceType.KNIGHT, location);
    }
    @Override
    public boolean legalMove(Location currentLocation, Location nextLocation) {
        if (this.sameColor(Board.pieceAt(nextLocation))) {
            return false;
        }

        return Math.abs(currentLocation.getFile() - nextLocation.getFile()) == 1 && Math.abs(currentLocation.getRank() - nextLocation.getRank()) == 2 || Math.abs(currentLocation.getFile() - nextLocation.getFile()) == 2 && Math.abs(currentLocation.getRank() - nextLocation.getRank()) == 1;
    }

    @Override
    public String toString() {
        if (this.getColor() == Color.WHITE) {
            return "WN";
        }
        return "BN";
    }
}
