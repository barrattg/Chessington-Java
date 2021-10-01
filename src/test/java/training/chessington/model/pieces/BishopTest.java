package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BishopTest {

    @Test
    public void blackBishopCanCaptureDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Piece enemyPiece = new Rook(PlayerColour.WHITE);
        Coordinates bishopCoords = new Coordinates(3, 4);
        board.placePiece( bishopCoords, bishop);

        Coordinates enemyCoords =  bishopCoords.plus(1, 1);
        board.placePiece(enemyCoords, enemyPiece);

        // Act
        List<Move> moves = bishop.getAllowedMoves( bishopCoords, board);

        // Assert
        assertThat(moves).contains(new Move( bishopCoords, enemyCoords));
    }
}
