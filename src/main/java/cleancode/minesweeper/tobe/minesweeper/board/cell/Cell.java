package cleancode.minesweeper.tobe.minesweeper.board.cell;

public interface Cell {

    void flag();
    boolean isChecked();
    void open();
    boolean isOpened();
    boolean isLandMine();
    boolean hadLandMineCount();
    CellSnapshot getSnapshot();
}
