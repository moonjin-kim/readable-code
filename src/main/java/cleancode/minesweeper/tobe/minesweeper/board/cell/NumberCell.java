package cleancode.minesweeper.tobe.minesweeper.board.cell;

public class NumberCell implements Cell {
    private final Integer nearByLandMineCount;
    private final CellState cellState = CellState.initialize();

    public NumberCell(Integer nearByLandMineCount) {
        this.nearByLandMineCount = nearByLandMineCount;
    }

    @Override
    public void flag() {
        cellState.flag();
    }

    @Override
    public boolean isChecked() {
        return cellState.isOpened();
    }

    @Override
    public void open() {
        cellState.open();
    }

    @Override
    public boolean isOpened() {
        return cellState.isOpened();
    }

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public boolean hadLandMineCount() {
        return true;
    }

    @Override
    public CellSnapshot getSnapshot() {
        if(cellState.isOpened()) {
            return CellSnapshot.ofNumber(nearByLandMineCount);
        }
        if(cellState.isFlagged()) {
            return CellSnapshot.ofFlag();
        }
        return CellSnapshot.ofUnChecked();
    }
}
