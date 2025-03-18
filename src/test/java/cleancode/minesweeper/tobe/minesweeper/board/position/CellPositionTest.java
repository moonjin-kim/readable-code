package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CellPositionTest {

    @DisplayName("rowIndex가 보드의 row보다 크거나 같으면 true를 반환한다")
    @Test
    void isRowIndexMoreThanOrEqual(){
        //given
        CellPosition cellPosition = new CellPosition(5, 0);

        //when
        //then
        assertThat(cellPosition.isRowIndexMoreThanOrEqual(5)).isTrue();
        assertThat(cellPosition.isRowIndexMoreThanOrEqual(4)).isTrue();
    }

    @DisplayName("rowIndex가 보드의 row보다 작으면 false를 반환한다")
    @Test
    void isRowIndexMoreThanOrEqualFalse(){
        //given
        CellPosition cellPosition = new CellPosition(5, 0);

        //when
        //then
        assertThat(cellPosition.isRowIndexMoreThanOrEqual(6)).isFalse();
    }

    @DisplayName("colIndex가 보드의 col보다 크거나 같으면 true를 반환한다")
    @Test
    void isColumnIndexMoreThanOrEqual(){
        //given
        CellPosition cellPosition = new CellPosition(0, 5);

        //when
        //then
        assertThat(cellPosition.isColIndexMoreThanOrEqual(4)).isTrue();
        assertThat(cellPosition.isColIndexMoreThanOrEqual(5)).isTrue();
    }

    @DisplayName("colIndex가 보드의 col보다 작으면 false를 반환한다")
    @Test
    void isColumnIndexMoreThanOrEqualFalse(){
        //given
        CellPosition cellPosition = new CellPosition(0, 5);

        //when
        //then
        assertThat(cellPosition.isColIndexMoreThanOrEqual(6)).isFalse();
    }

    @DisplayName("계산된 다음 좌표가 row,col 모두 양수이면 true를 반환한다.")
    @Test
    void canCalculatePositionBy1(){
        //given
        CellPosition cellPosition = new CellPosition(0, 5);
        RelativePosition nextPosition = new RelativePosition(1, 0);

        //when
        //then
        assertThat(cellPosition.canCalculatePositionBy(nextPosition)).isTrue();
    }

    @DisplayName("계산된 다음 좌표가 row가 음수이면 false를 반환한다.")
    @Test
    void canCalculatePositionBy2(){
        //given
        CellPosition cellPosition = new CellPosition(0, 0);
        RelativePosition nextPosition = new RelativePosition(-1, 0);

        //when
        //then
        assertThat(cellPosition.canCalculatePositionBy(nextPosition)).isFalse();
    }

    @DisplayName("계산된 다음 좌표가 col가 음수이면 false를 반환한다.")
    @Test
    void canCalculatePositionBy3(){
        //given
        CellPosition cellPosition = new CellPosition(0, 0);
        RelativePosition nextPosition = new RelativePosition(0, -1);

        //when
        //then
        assertThat(cellPosition.canCalculatePositionBy(nextPosition)).isFalse();
    }

    @DisplayName("계산된 다음 좌표가 row,col 모두 음수이면 false를 반환한다.")
    @Test
    void canCalculatePositionBy4(){
        //given
        CellPosition cellPosition = new CellPosition(0, 0);
        RelativePosition nextPosition = new RelativePosition(-1, -1);

        //when
        //then
        assertThat(cellPosition.canCalculatePositionBy(nextPosition)).isFalse();
    }

    @DisplayName("RelativePosition에 따라 다음 좌표를 계산한다.")
    @Test
    void calculatePositionBy(){
        //given
        CellPosition cellPosition = new CellPosition(0, 0);
        RelativePosition nextPosition = new RelativePosition(1, 1);

        //when
        CellPosition nextCellPosition = cellPosition.calculatePositionBy(nextPosition);

        //then
        assertThat(nextCellPosition).isEqualTo(new CellPosition(1, 1));
    }

    @DisplayName("RelativePosition에 따라 다음 좌표를 계산시 음수이면 예외를 반환한다.")
    @Test
    void calculatePositionByIllegalArgumentException(){
        //given
        CellPosition cellPosition = new CellPosition(0, 0);
        RelativePosition moveTop = new RelativePosition(-1, 0);
        RelativePosition moveLeft = new RelativePosition(0, -1);
        RelativePosition moveTopLeft = new RelativePosition(-1, -1);

        //when
        //then
        assertThatThrownBy(() -> cellPosition.calculatePositionBy(moveTop))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> cellPosition.calculatePositionBy(moveLeft))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> cellPosition.calculatePositionBy(moveTopLeft))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
