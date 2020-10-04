package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		if (this.getColor() == Color.WHITE) {
			// Salto de 1
			p.setValeus(position.getRow() - 1, position.getColumn());
			if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// Salto de 2
			p.setValeus(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)
					&& this.getBoard().positionExists(p2) && !this.getBoard().thereIsAPiece(p2)
					&& this.getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// Diagonal 1
			p.setValeus(position.getRow() - 1, position.getColumn() - 1);
			if (this.getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// Diagonal 2
			p.setValeus(position.getRow() - 1, position.getColumn() + 1);
			if (this.getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// #specialmove en passant white
			if (this.position.getRow() == 3) {
				Position left = new Position(this.position.getRow(), this.position.getColumn() - 1);
				if (this.getBoard().positionExists(left) && this.isThereOpponentPiece(left)
						&& this.getBoard().piece(left) == this.chessMatch.getenPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(this.position.getRow(), this.position.getColumn() + 1);
				if (this.getBoard().positionExists(right) && this.isThereOpponentPiece(right)
						&& this.getBoard().piece(right) == this.chessMatch.getenPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}

		} else { // Peão preto
			// Salto de 1
			p.setValeus(position.getRow() + 1, position.getColumn());
			if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// Salto de 2
			p.setValeus(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)
					&& this.getBoard().positionExists(p2) && !this.getBoard().thereIsAPiece(p2)
					&& this.getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// Diagonal 1
			p.setValeus(position.getRow() + 1, position.getColumn() - 1);
			if (this.getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// Diagonal 2
			p.setValeus(position.getRow() + 1, position.getColumn() + 1);
			if (this.getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// #specialmove en passant black
			if (this.position.getRow() == 4) {
				Position left = new Position(this.position.getRow(), this.position.getColumn() - 1);
				if (this.getBoard().positionExists(left) && this.isThereOpponentPiece(left)
						&& this.getBoard().piece(left) == this.chessMatch.getenPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(this.position.getRow(), this.position.getColumn() + 1);
				if (this.getBoard().positionExists(right) && this.isThereOpponentPiece(right)
						&& this.getBoard().piece(right) == this.chessMatch.getenPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}

		return mat;
	}
}
