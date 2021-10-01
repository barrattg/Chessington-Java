package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        ArrayList<Move> bishopMoves = new ArrayList<>();

        board.get(from);

        if(this.getColour() == PlayerColour.WHITE) {
            //for white Bishops
            Move bishopMove = getMoveBishop(from, -1, -1, board);
            addValidMoveBishop(bishopMoves, bishopMove);
            Move bishopMove2 = getMoveBishop(from, -1, +1, board);
            addValidMoveBishop(bishopMoves, bishopMove2);

        }
        else{
            Move bishopMove = getMoveBishop(from, +1, -1, board);
            addValidMoveBishop(bishopMoves, bishopMove);
            Move bishopMove2 = getMoveBishop(from, +1, +1, board);
            addValidMoveBishop(bishopMoves, bishopMove2);

        }
        return bishopMoves;
    }

    private void addValidMoveBishop(ArrayList<Move> bishopMoves, Move bishopMove) {
        if (bishopMove != null) {
            bishopMoves.add(bishopMove);
        }
    }

    private Move getMoveBishop(Coordinates from, int numberRows, int numberCols, Board board) {
        int fromRow = from.getRow() + numberRows;
        int fromColumn = from.getCol() + numberCols;
        Coordinates to = new Coordinates(fromRow, fromColumn);
        if (board.isValidCoordinate(to) == true) {


            Piece b = board.get(to);

            if (b.getColour() != this.getColour()) {
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
