package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellSnapshotTest {

    @DisplayName("셀 초기화시 모든 상태가 false이다.")
    @Test
    void initialize(){
        //given
        //when
        CellState cell = CellState.initialize();

        //then
        assertFalse(cell.isFlagged());
        assertFalse(cell.isOpened());
        assertFalse(cell.isChecked());
    }

    @DisplayName("flag 호출 시 isFlagged가 true로 변경된다.")
    @Test
    void flag(){
        //given
        CellState cell = CellState.initialize();
        //when
        cell.flag();

        //then
        assertTrue(cell.isFlagged());
    }

    @DisplayName("open 호출 시 isOpenned true로 변경된다.")
    @Test
    void open(){
        //given
        CellState cell = CellState.initialize();
        //when
        cell.open();

        //then
        assertTrue(cell.isOpened());
    }
}
