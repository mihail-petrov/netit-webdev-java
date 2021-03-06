package pieces;

import meta.PiecePosition;

public class King extends Piece {

    public King(String color, int row, int col) {
        super(color,"K", row, col);

        this.power      = 10;
        this.id         = 4;
    }

    @Override
    public boolean isMoveValid(PiecePosition externalPosition) {

        int moveRowCoefficient = this.getCurrentPosition().getRowCoefficient(externalPosition);
        int moveColCoefficient = this.getCurrentPosition().getColCoefficient(externalPosition);

        return  (moveRowCoefficient == 0 && moveColCoefficient == 1) ||
                (moveRowCoefficient == 1 && moveColCoefficient == 1) ||
                (moveColCoefficient == 0 && moveRowCoefficient == 1);
    }

    public void attack(int row, int col) {

    }
}
