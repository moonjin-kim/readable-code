package cleancode.minesweeper.tobe.minesweeper.board.position;

import cleancode.minesweeper.tobe.minesweeper.board.cell.Cell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CellPositionsTest {
    @DisplayName("보드의 크기만큼의 CellPosition을 가진 CellPositions를 생성한다.")
    @Test
    void from(){
        //given
        Cell[][] board = new Cell[5][5];

        //when
        CellPositions cellPositions = CellPositions.from(board);

        //then
        assertThat(cellPositions.getPositions()).hasSize(25);
    }

    @DisplayName("원하는 개수의 CellPosition를 임의로 가져온다")
    @Test
    void extractRandomPositions(){
        //given
        Cell[][] board = new Cell[5][5];
        CellPositions cellPositions = CellPositions.from(board);

        //when
        List<CellPosition> positions = cellPositions.extractRandomPositions(5);

        //then
        assertThat(positions).hasSize(5);
    }

    @DisplayName("전달하는 CellPosition들을 제외한 CellPosition들을 반환받는다.")
    @Test
    void subtract1(){
        //given
        Cell[][] board = new Cell[2][2];
        CellPositions cellPositions = CellPositions.from(board);
        CellPosition cellPosition1 = new CellPosition(0, 0);
        CellPosition cellPosition2 = new CellPosition(1, 1);
        List<CellPosition> positions = Arrays.asList(cellPosition1, cellPosition2);

        //when
        List<CellPosition> subtractedPositions = cellPositions.subtract(positions);

        //then
        assertThat(subtractedPositions).hasSize(2);
        assertThat(subtractedPositions.contains(new CellPosition(0,1))).isTrue();
        assertThat(subtractedPositions.contains(new CellPosition(1,0))).isTrue();
        assertThat(subtractedPositions.contains(cellPosition1)).isFalse();
        assertThat(subtractedPositions.contains(cellPosition2)).isFalse();
    }

    @DisplayName("포함되지 않는 CellPosition리스트를 전달시 제외되지 않는다")
    @Test
    void subtract2(){
        //given
        Cell[][] board = new Cell[2][2];
        CellPositions cellPositions = CellPositions.from(board);
        CellPosition cellPosition1 = new CellPosition(3, 3);
        List<CellPosition> positions = Arrays.asList(cellPosition1);

        //when
        List<CellPosition> subtractPosition = cellPositions.subtract(positions);

        //then
        assertThat(subtractPosition).hasSize(4);
        assertThat(subtractPosition.contains(new CellPosition(0,1))).isTrue();
        assertThat(subtractPosition.contains(new CellPosition(1,0))).isTrue();
        assertThat(subtractPosition.contains(new CellPosition(0,0))).isTrue();
        assertThat(subtractPosition.contains(new CellPosition(1,1))).isTrue();
    }

    @DisplayName("같은 좌표의 CellPosition들은 전달시 1개만 제거된 리스트를 받는다")
    @Test
    void subtract3(){
        //given
        Cell[][] board = new Cell[2][2];
        CellPositions cellPositions = CellPositions.from(board);
        CellPosition cellPosition1 = new CellPosition(0, 0);
        CellPosition cellPosition2 = new CellPosition(0,0 );
        List<CellPosition> positions = Arrays.asList(cellPosition1, cellPosition2);

        //when
        List<CellPosition> subtractPosition = cellPositions.subtract(positions);

        //then
        assertThat(subtractPosition).hasSize(3);
        assertThat(subtractPosition.contains(new CellPosition(0,0))).isFalse();
        assertThat(subtractPosition.contains(new CellPosition(0,1))).isTrue();
        assertThat(subtractPosition.contains(new CellPosition(1,0))).isTrue();
        assertThat(subtractPosition.contains(new CellPosition(1,1))).isTrue();
    }
}
