package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.board.cell.*;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPositions;
import cleancode.minesweeper.tobe.minesweeper.board.position.RelativePosition;

import java.util.List;
import java.util.Stack;

public class GameBoard {
    private final Cell[][] board;
    private final int landMineCount;
    private GameStatus gameStatus;

    public GameBoard(GameLevel gameLevel) {
        int rowSize = gameLevel.getRowSize();
        int colSize = gameLevel.getColSize();
        this.board = new Cell[rowSize][colSize];
        this.landMineCount = gameLevel.getLandMineCount();
        initializeGameStatus();
    }

    public void openAt(CellPosition cellPosition) {
        // 지뢰 오픈
        if (isLandMineCellAt(cellPosition)) {
            openOneCellAt(cellPosition);
            changeGameStatusToLose();
            return;
        }

        openSurroundedCell2(cellPosition);
        checkIfGameIsOver();
    }

    public void flagAt(CellPosition cellPosition) {
        Cell cell = findBoard(cellPosition);
        checkIfGameIsOver();
        cell.flag();
    }

    public void initializeGame() {
        initializeGameStatus();
        CellPositions cellPositions = CellPositions.from(board);

        initializeEmptyCells(cellPositions);

        List<CellPosition> landMainePositions = cellPositions.extractRandomPositions(landMineCount);
        initializeLandMineCells(landMainePositions);

        List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMainePositions);
        initializeNumberCells(numberPositionCandidates);
    }

    public boolean isLandMineCellAt(CellPosition cellPosition) {
        return findBoard(cellPosition).isLandMine();
    }

    public boolean isInProgress() {
        return gameStatus == GameStatus.IN_PROGRESS;
    }

    public boolean isAllCellChecked() {
        Cells cells = Cells.from(board);
        return cells.isAllChecked();
    }

    public boolean isInvalidCellPosition(CellPosition cellPosition) {
        int rowSize = getRowSize();
        int colSize = getColSize();

        return cellPosition.isRowIndexMoreThanOrEqual(rowSize) || cellPosition.isColIndexMoreThanOrEqual(colSize);
    }

    public boolean isLoseStatus() {
        return gameStatus == GameStatus.LOSE;
    }

    public boolean isWinStatus() {
        return gameStatus == GameStatus.WIN;
    }

    public int getRowSize() {
        return board.length;
    }

    public int getColSize() {
        return board[0].length;
    }

    public CellSnapshot getSnapshot(CellPosition cellPosition) {
        Cell cell = findBoard(cellPosition);
        return cell.getSnapshot();
    }

    public Cell findBoard(CellPosition cellPosition) {
        int rowIndex = cellPosition.getRowIndex();
        int colIndex = cellPosition.getColIndex();
        return board[rowIndex][colIndex];
    }

    private void openSurroundedCell2(CellPosition cellPosition) {
        Stack<CellPosition> stack = new Stack<>();
        stack.push(cellPosition);

        while (!stack.isEmpty()) {
            openAndPushCellAt(stack);
        }
    }

    private void openAndPushCellAt(Stack<CellPosition> stack) {
        CellPosition currentCellPosition = stack.pop();
        if (isOpenedCellAt(currentCellPosition)) {
            return;
        }
        if (isLandMineCellAt(currentCellPosition)) {
            return;
        }

        openOneCellAt(currentCellPosition);

        if (doesCellHaveLandMineCount(currentCellPosition)) {
            return;
        }

        List<CellPosition> surroundedPositions = calculateSurroundedPositions(currentCellPosition);
        for (CellPosition surroundedPosition : surroundedPositions) {
            stack.push(surroundedPosition);
        }
    }

    private void openOneCellAt(CellPosition cellPosition) {
        Cell cell = findBoard(cellPosition);
        cell.open();
    }

    private void updateCellAt(CellPosition position, Cell cell) {
        board[position.getRowIndex()][position.getColIndex()] = cell;
    }


    private boolean isOpenedCellAt(CellPosition cellPosition) {
        return findBoard(cellPosition).isOpened();
    }

    private boolean doesCellHaveLandMineCount(CellPosition cellPosition) {
        return findBoard(cellPosition).hadLandMineCount();
    }

    private List<CellPosition> calculateSurroundedPositions(CellPosition cellPosition) {
        return RelativePosition.SURROUND_POSITIONS.stream().filter(cellPosition::canCalculatePositionBy).map(cellPosition::calculatePositionBy).filter(position -> position.isRowIndexLessThan(getRowSize())).filter(position -> position.isColIndexLessThan(getColSize())).toList();
    }

    private void initializeGameStatus() {
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    private void initializeEmptyCells(CellPositions cellPositions) {
        List<CellPosition> allPosition = cellPositions.getPositions();
        for (CellPosition position : allPosition) {
            updateCellAt(position, new EmptyCell());
        }
    }

    private void initializeLandMineCells(List<CellPosition> landMainePositions) {
        for (CellPosition position : landMainePositions) {
            updateCellAt(position, new LandMineCell());
        }
    }

    private void initializeNumberCells(List<CellPosition> numberPositionCandidates) {
        for (CellPosition candidatePosition : numberPositionCandidates) {
            int count = countNearbyLandMines(candidatePosition);
            if (count != 0) {
                updateCellAt(candidatePosition, new NumberCell(count));
            }
        }
    }

    private void changeGameStatusToWin() {
        gameStatus = GameStatus.WIN;
    }

    private void changeGameStatusToLose() {
        gameStatus = GameStatus.LOSE;
    }

    private void checkIfGameIsOver() {
        if (isAllCellChecked()) {
            changeGameStatusToWin();
        }
    }

    private int countNearbyLandMines(CellPosition cellPosition) {
        long count = calculateSurroundedPositions(cellPosition).stream().filter(this::isLandMineCellAt).count();
        return (int) count;
    }

}
