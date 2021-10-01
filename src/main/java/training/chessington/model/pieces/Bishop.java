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


        boolean upAndLeftBlocked = false;
        boolean upAndRightBlocked = false;
        boolean downAndLeftBlocked = false;
        boolean downAndRightBlocked= false;
            for (int i = 1, j = 1; i < 8 && j < 8; i++, j++) {
                upAndLeftBlocked = diagonalBlockCheck(from, board, bishopMoves, upAndLeftBlocked, -i, -j);
                upAndRightBlocked = diagonalBlockCheck(from,board, bishopMoves,upAndRightBlocked,-i, +j );
                downAndLeftBlocked = diagonalBlockCheck(from,board, bishopMoves, downAndLeftBlocked, i, -j);
                downAndRightBlocked= diagonalBlockCheck(from,board, bishopMoves, downAndRightBlocked, i, j);

                }
        return bishopMoves;
        }

    private boolean diagonalBlockCheck(Coordinates from, Board board, ArrayList<Move> bishopMoves, boolean blocked, int i, int j) {
        if(!blocked) {
            {

                Coordinates to = new Coordinates(from.getRow() + i, from.getCol() + j);
                if(!board.isValidCoordinate(to)){
                    return blocked;
                }
                Piece piece = board.get(to);
                if (piece == null) {
                    Move bishopMove = getMoveBishop(from, +i, +j, board);
                    addValidMoveBishop(bishopMoves, bishopMove);
                } else if (piece.getColour().equals(colour)) {
                    blocked = true;
                } else {
                    Move bishopMove = getMoveBishop(from, +i, +j, board);
                    addValidMoveBishop(bishopMoves, bishopMove);
                    blocked = true;
                }

            }
        }
        return blocked;
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
        if (board.isValidCoordinate(to)) {
            Piece p = board.get(to);

            if(board.get(to) == null || p.getColour() != this.getColour() ) {
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
