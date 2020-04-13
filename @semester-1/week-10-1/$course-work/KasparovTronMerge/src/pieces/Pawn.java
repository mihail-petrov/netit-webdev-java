package pieces;

import config.PieceColor;
import meta.PiecePosition;

public class Pawn extends Piece implements IHealable, IBlastable {

    public Pawn(PieceColor color, int row, int col) throws Exception {
        super(color, "P", row, col); // Piece

        this.power      = 1;
        this.id         = 1;
    }
//
//    public boolean isMoveValid(int moveRow, int moveCol) {
//
//        int moveRowCoefficient = Math.abs(this.getCurrentPosition().getRow() - moveRow); // ?
//        int moveColCoefficient = Math.abs(this.getCurrentPosition().getCol() - moveCol); // ?
//
//        return (moveRowCoefficient == 1) &&
//                (moveColCoefficient == 0);
//    }

    @Override
    public boolean isMoveValid(PiecePosition externalPosition) {

        int moveRowCoefficient = this.getCurrentPosition().getRowCoefficient(externalPosition);
        int moveColCoefficient = this.getCurrentPosition().getColCoefficient(externalPosition);

        return (moveRowCoefficient == 1) &&
                (moveColCoefficient == 0);
    }

    public void attack(int row, int col) {

    }

    @Override
    public void heal() {

    }

    @Override
    public void blast() {

    }
}