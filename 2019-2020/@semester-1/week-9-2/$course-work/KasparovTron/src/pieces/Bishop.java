package pieces;

import meta.PiecePosition;

public class Bishop extends Piece {

    public Bishop(String color, int row, int col) {

        super(color,"B", row, col);
        this.power      = 5;
        this.id         = 4;
    }

    public boolean isMoveValid(PiecePosition externalPosition) {

        int moveRowCoefficient = this.getCurrentPosition().getRowCoefficient(externalPosition);
        int moveColCoefficient = this.getCurrentPosition().getColCoefficient(externalPosition);

        return moveRowCoefficient == moveColCoefficient;
    }

    public void attack(int row, int col) {

    }
}
