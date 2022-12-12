public class SudokuBoardRepository implements Repository<SudokuBoard> {
    private final SudokuBoard boardzik;

    public SudokuBoardRepository(SudokuBoard boardzik) {
        this.boardzik = boardzik;
    }


    @Override
    public SudokuBoard createInstance() {
        return boardzik.clone();
    }
}
