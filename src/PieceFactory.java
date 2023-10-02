public class PieceFactory {
    public AbstractPiece createPiece(PieceType pieceType, Color color, Location location) {
        switch (pieceType) {
            case KING:
                return new King(color, location);
            case QUEEN:
                return new Queen(color, location);
            case ROOK:
                return new Rook(color, location);
            case BISHOP:
                return new Bishop(color, location);
            case KNIGHT:
                return new Knight(color, location);
            case PAWN:
                return new Pawn(color, location);
            default:
               throw new IllegalArgumentException("Invalid piece type");
        }


    }
}
