package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KingTest {
    @Test
    public void whiteKingCanCaptureDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Piece enemyPiece = new Rook(PlayerColour.BLACK);
        Coordinates kingCoords = new Coordinates(4, 4);
        board.placePiece(kingCoords, king);

        Coordinates enemyCoords = kingCoords.plus(-1, 1);
        board.placePiece(enemyCoords, enemyPiece);

        // Act
        List<Move> moves = king.getAllowedMoves(kingCoords, board);

        // Assert
        assertThat(moves).contains(new Move(kingCoords, enemyCoords));
    }
}
