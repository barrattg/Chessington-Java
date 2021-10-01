package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        ArrayList<Move> rookMoves = new ArrayList<>();

        boolean rightBlocked = false;
        boolean leftBlocked = false;
        boolean upBlocked = false;
        boolean downBlocked = false;

        for (int i  = 1, j = 0; i < 8; i ++) {


            upBlocked= rookBlockCheck(from,board, rookMoves, upBlocked, -i, j);
            downBlocked = rookBlockCheck(from,board, rookMoves, downBlocked,+i, j);



        }
        for (int j = 1, i =0; j<8; j ++){

            rightBlocked = rookBlockCheck(from,board, rookMoves, rightBlocked,i, +j );
            leftBlocked = rookBlockCheck(from, board, rookMoves, leftBlocked , i, -j);
        }
        return rookMoves;
    }

    private void addValidMoveRook(ArrayList<Move>rookMoves, Move rookMove) {
        if (rookMove != null) {
            rookMoves.add(rookMove);
        }
    }

    private Move getMoveRook(Coordinates from, int numberRows, int numberCols, Board board) {
        int fromRow = from.getRow() + numberRows;
        int fromColumn = from.getCol() + numberCols;
        Coordinates to = new Coordinates(fromRow, fromColumn);
        if (board.isValidCoordinate(to)) {
            Piece r = board.get(to);

            if(board.get(to) == null || r.getColour() != this.getColour() ) {
                return new Move(from, to);
            } else {
                return null;
            }

        }
        else{
            return null;
        }
    }

    private boolean rookBlockCheck(Coordinates from, Board board, ArrayList<Move> rookMoves, boolean blocked, int i, int j) {
        if(!blocked) {
            {

                Coordinates to = new Coordinates(from.getRow() + i, from.getCol() + j);
                if(!board.isValidCoordinate(to)){
                    return blocked;
                }
                Piece piece = board.get(to);
                if (piece == null) {
                    Move rookMove = getMoveRook(from, +i, +j, board);
                    addValidMoveRook(rookMoves, rookMove);
                } else if (piece.getColour().equals(colour)) {
                    blocked = true;
                } else {
                    Move rookMove = getMoveRook(from, +i, +j, board);
                    addValidMoveRook(rookMoves, rookMove);
                    blocked = true;
                }

            }
        }
        return blocked;
    }

    }



