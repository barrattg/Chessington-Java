package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        int x[] = {-1,-1,-1,0,0,1,1,1};

        int y[] = {-1,0,1,-1,1,1,0,-1};

        ArrayList<Move> kingMoves = new ArrayList<>();

        for(int i=0; i <8; i++){
            Move kingMove = getMoveKing(from, x[i], y[i], board);
            addValidMoveKing(kingMoves, kingMove);
        }

        return kingMoves;
    }

    private void addValidMoveKing(ArrayList<Move>kingMoves, Move kingMove) {
        if (kingMove != null) {
            kingMoves.add(kingMove);
        }
    }

    private Move getMoveKing(Coordinates from, int numberRows, int numberCols, Board board) {
        int fromRow = from.getRow() + numberRows;
        int fromColumn = from.getCol() + numberCols;
        Coordinates to = new Coordinates(fromRow, fromColumn);
        if (board.isValidCoordinate(to)) {
            Piece k = board.get(to);

            if(board.get(to) == null || k.getColour() != this.getColour() ) {
                return new Move(from, to);
            } else {
                return null;
            }

        }
        else{
            return null;
        }
    }
}
