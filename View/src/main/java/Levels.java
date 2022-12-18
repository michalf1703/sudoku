import kompo.SudokuBoard;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Levels {

    public static final int BASIC_LEVEL = 5;
    public static final int[] MULTIPLIER_LEVEL_ARRAY = {1, 2, 3, 4};

    private Random rand = new Random();
    private Set<FieldCoordinates> randomPositions = new HashSet<>();


    private void fillRandomPositionsList(int capacity) {
        for (int i = 0; i < capacity; i++) {
            boolean isElementAdded = false;

            while (!isElementAdded) {
                int axisX = rand.nextInt(9);
                int axisY = rand.nextInt(9);
                isElementAdded = randomPositions.add(new FieldCoordinates(axisX, axisY));
            }
        }
    }


    public SudokuBoard chooseLevel(SudokuBoard sudokuBoard, String level)
            throws EmptyBoardException {
        if (!(level.equals("UltraEasy") || level.equals("Easy")
                || level.equals("Medium") || level.equals("Hard"))) {
            throw new UnknownLevelException();
        } else if (!sudokuBoard.checkBoard()) {
            throw new EmptyBoardException("Sudoku board has not been filled");
        }

        switch (level) {
            case "UltraEasy": {
                fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[0]);
                break;
            }
            case "Easy": {
                fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[1]);
                break;
            }
            case "Medium": {
                fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[2]);
                break;
            }
            case "Hard": {
                fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[3]);
                break;
            }
            default: {
                fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[0]);
            }
        }

        for (FieldCoordinates it : randomPositions) {
            sudokuBoard.set(it.getAxisX(), it.getAxisY(), 0);
        }

        return sudokuBoard;
    }
}






