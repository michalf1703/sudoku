

public class SudokuBoard {

    private int[][] board = new int[9][9];

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        this.board[x][y] = value;
    }

    private boolean checkValid() {

        //SPRAWDZANIE RZEDU
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (board[i][j] == board[i][k]) {
                        return false;
                    }
                }
            }
        }
        //SPRAWDZANIE KOLUMNY
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int k = i + 1; k < 9; k++) {
                    if (board[i][j] == board[k][j]) {
                        return false;
                    }
                }
            }
        }
        //SPRAWDZANIE MALYCH KWADRATOW
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //mały kwadrat (i, j)
                for (int checked = 0; checked < 9; checked++) {
                    for (int compared = checked + 1; compared < 9; compared++) {
                        if (board[i * 3 + (checked / 3)][j * 3 + (checked % 3)]
                                == board[i * 3 + (compared / 3)][j * 3 + (compared % 3)]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(final Object object) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (((SudokuBoard) object).get(i, j) != get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result += get(i, j) + " ";
            }
            result += "\n";
        }
        return result;
    }


    public void print() {
        System.out.println(" ____________________");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    System.out.print("|");
                }

                System.out.print(board[i][j] + " ");
                if (j == 2 || j == 5) {
                    System.out.print("|");
                }
                if (j == 8) {
                    System.out.print("|");
                }

                if (j == 8 && i == 2) {
                    System.out.print("\n");
                    System.out.print(" --------------------");
                }

                if (j == 8 && i == 5) {
                    System.out.print("\n");
                    System.out.print(" --------------------");
                }


            }
            System.out.println();
        }
        System.out.println(" --------------------");
    }

}

