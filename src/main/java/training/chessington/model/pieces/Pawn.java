package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    //set pawn direction at start as -1 or 1 for direction
    //set initial row for 1/6

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        ArrayList<Move> pawnMoves = new ArrayList<>();


        if(this.getColour() == PlayerColour.WHITE) {
            //white's allowed moves

            board.get(from);

            if(from.getRow() == 0){
                //if white is at end of board
                return pawnMoves;
            }

            //pawnCapture

            Move pawnCapture = getCapturePawn(from, -1, -1, board);
            addValidMovePawn(pawnMoves, pawnCapture);


            Move pawnCapture2 = getCapturePawn(from, -1, 1, board);
            addValidMovePawn(pawnMoves, pawnCapture2);

            if (from.getRow() == 6) {

                Move pawnMove = getMovePawn(from, -1,+0, board);
                addValidMovePawn(pawnMoves, pawnMove);

                Move pawnMove2 = getMovePawn(from, -2,+0, board);
                addValidMovePawn(pawnMoves, pawnMove2);
            } else {


                Move pawnMove = getMovePawn(from, -1,+0, board);
                addValidMovePawn(pawnMoves, pawnMove);

            }
        }
        else{
            //black's allowed moves
            Move pawnCapture = getCapturePawn(from, 1, -1, board);
            addValidMovePawn(pawnMoves, pawnCapture);


            Move pawnCapture2 = getCapturePawn(from, 1, 1, board);
            addValidMovePawn(pawnMoves, pawnCapture2);


            if(from.getRow() == 7){
                //if black is at end of board
                return pawnMoves;
            }

            if(from.getRow() == 1) {

                Move pawnMove = getMovePawn(from, +1, +0, board);
                addValidMovePawn(pawnMoves, pawnMove);

                Move pawnMove2 = getMovePawn(from, +2,  +0, board);
                addValidMovePawn(pawnMoves, pawnMove2);

            }
            else{

                Move pawnMove = getMovePawn(from, +1,  +0, board);
                addValidMovePawn(pawnMoves, pawnMove);

            }

        }
        return pawnMoves;

    }

    private void addValidMovePawn(ArrayList<Move> pawnMoves, Move pawnMove) {
        if (pawnMove != null) {
            pawnMoves.add(pawnMove);
        }
    }



    private Move getMovePawn(Coordinates from, int numberRows, int numberCols, Board board) {
        int fromRow = from.getRow() + numberRows;
        int fromColumn = from.getCol() + numberCols;

        Coordinates to = new Coordinates(fromRow, fromColumn);

        if(board.get(to) == null ) {
            return new Move(from, to);
        }
        else{
            return null;
        }
    }
    private Move getCapturePawn(Coordinates from, int numberRows, int numberCols, Board board) {

        int fromRow = from.getRow() + numberRows;
        int fromColumn = from.getCol() + numberCols;
        Coordinates to = new Coordinates(fromRow, fromColumn);
        if(board.isValidCoordinate(to)) {


            Piece p = board.get(to);

            if (p != null && p.getColour() != this.getColour()) {
                return new Move(from, to);
            }
            else {
                return null;
            }
        }
        else{
            return null;
        }
    }




}
