package kompo;

public class SudokuBoardRepository implements Repository<Object> {
    private final SudokuBoard boardzik;

    public SudokuBoardRepository(SudokuBoard boardzik) {
        this.boardzik = boardzik;
    }


    @Override
    public Object createInstance() {
        return boardzik.clone();
    }
}
