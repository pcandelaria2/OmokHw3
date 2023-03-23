package omok.model;

import omok.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction of an Omok board, which consists of n x n intersections
 * or places where players can place their stones. The board can be
 * accessed using a pair of 0-based indices (x, y), where x and y
 * denote the column and row number, respectively. The top-left
 * intersection is represented by the indices (0, 0), and the
 * bottom-right intersection is represented by the indices (n-1, n-1).
 */
public class Board {
    private int size;
    private Player[][] board;


    /** Create a new board of the default size. */
    public Board() {
        board = new Player[15][15];
    }

    /** Create a new board of the specified size. */
    public Board(int size) {
        this.size = size;
        board = new Player[size][size];
    }

    /** Return the size of this board. */
    public int size() {
        return size;
    }

    /** Removes all the stones placed on the board, effectively
     * resetting the board to its original state.
     */
    public void clear() {
        for(int i =0; i <=size;i++){
            for(int j = 0; j <=size;j++){
                board[i][j] = null;
            }
        }
    }

    /** Return a boolean value indicating whether all the places
     * on the board are occupied or not.
     */
    public boolean isFull() {
        for(int i =0; i <=size;i++){
            for(int j = 0; j <=size;j++){
                if(board[i][j] == null){
                    return false;
                }

            }
        }
        return true;
    }

    /**
     * Place a stone for the specified player at a specified
     * intersection (x, y) on the board.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     * @param player Player whose stone is to be placed
     */
    public void placeStone(int x, int y, Player player) {
        board[x][y] = player;
    }

    /**
     * Return a boolean value indicating whether the specified
     * intersection (x, y) on the board is empty or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isEmpty(int x, int y) {
        if(board[x][y]==null){
            return true;
        }
        return false;
    }

    /**
     * Is the specified place on the board occupied?
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupied(int x, int y) {
        if(board[x][y]!=null){
            return true;
        }
        return false;
    }

    /**
     * Rreturn a boolean value indicating whether the specified
     * intersection (x, y) on the board is occupied by the given
     * player or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupiedBy(int x, int y, Player player) {
        if(board[x][y]==player){
            return true;
        }
        return false;
    }

    /**
     * Return the player who occupies the specified intersection (x, y)
     * on the board. If the place is empty, this method returns null.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public Player playerAt(int x, int y) {

        return board[x][y];
    }

    /**
     * Return a boolean value indicating whether the given player
     * has a winning row on the board. A winning row is a consecutive
     * sequence of five or more stones placed by the same player in
     * a horizontal, vertical, or diagonal direction.
     */
    public boolean isWonBy(Player player) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - 4; j++) {
                if (board[i][j] == player && board[i][j + 1] == player && board[i][j + 2] == player && board[i][j + 3] == player && board[i][j + 4] == player) {
                    return true;
                }
            }
        }

        // check columns
        for (int i = 0; i < board.length - 4; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == player && board[i + 1][j] == player && board[i + 2][j] == player && board[i + 3][j] == player && board[i + 4][j] == player) {
                    return true;
                }
            }
        }

        // check diagonal
        for (int i = 0; i < board.length - 4; i++) {
            for (int j = 0; j < board.length - 4; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player && board[i + 2][j + 2] == player && board[i + 3][j + 3] == player && board[i + 4][j + 4] == player) {
                    return true;
                }
            }
        }

        for (int i = 4; i < board.length; i++) {
            for (int j = 0; j < board.length - 4; j++) {
                if (board[i][j] == player && board[i - 1][j + 1] == player && board[i - 2][j + 2] == player && board[i - 3][j + 3] == player && board[i - 4][j + 4] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    /** Return the winning row. For those who are not familiar with
     * the Iterable interface, you may return an object of
     * List<Place>. */
    public Iterable<Place> winningRow() {
        List<Place> winner = new ArrayList<Place>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - 4; j++) {
                if (board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2] && board[i][j + 2] == board[i][j + 3] && board[i][j + 1] == board[i][j + 4]) {
                    winner.add(new Place(i,j));
                    winner.add(new Place(i,j+1));
                    winner.add(new Place(i,j+2));
                    winner.add(new Place(i,j+3));
                    winner.add(new Place(i,j+4));
                    return winner;
                }
            }
        }

        // check columns
        for (int i = 0; i < board.length - 4; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i + 2][j]  && board[i + 2][j] == board[i + 3][j] && board[i + 3][j]== board[i + 4][j]) {
                    winner.add(new Place(i,j));
                    winner.add(new Place(i+1,j));
                    winner.add(new Place(i+2,j));
                    winner.add(new Place(i+3,j));
                    winner.add(new Place(i+4,j));
                    return winner;
                }
            }
        }

        // check diagonal
        for (int i = 0; i < board.length - 4; i++) {
            for (int j = 0; j < board.length - 4; j++) {
                if (board[i][j] ==  board[i + 1][j + 1] && board[i + 1][j + 1] == board[i + 2][j + 2] && board[i + 2][j + 2] == board[i + 3][j + 3] && board[i + 3][j + 3] == board[i + 4][j + 4] ) {
                    winner.add(new Place(i,j));
                    winner.add(new Place(i+1,j+1));
                    winner.add(new Place(i+2,j+2));
                    winner.add(new Place(i+3,j+3));
                    winner.add(new Place(i+4,j+4));
                    return winner;
                }
            }
        }

        for (int i = 4; i < board.length; i++) {
            for (int j = 0; j < board.length - 4; j++) {
                if (board[i][j] == board[i - 1][j + 1]  && board[i - 1][j + 1] == board[i - 2][j + 2]  && board[i - 2][j + 2] == board[i - 3][j + 3]  && board[i - 3][j + 3] == board[i - 4][j + 4] ) {
                    winner.add(new Place(i,j));
                    winner.add(new Place(i-1,j+1));
                    winner.add(new Place(i-2,j+2));
                    winner.add(new Place(i-3,j+3));
                    winner.add(new Place(i-4,j+4));
                    return winner;
                }
            }
        }

        return null;
    }

    /**
     * An intersection on an Omok board identified by its 0-based column
     * index (x) and row index (y). The indices determine the position
     * of a place on the board, with (0, 0) denoting the top-left
     * corner and (n-1, n-1) denoting the bottom-right corner,
     * where n is the size of the board.
     */
    public static class Place {
        /** 0-based column index of this place. */
        public final int x;

        /** 0-based row index of this place. */
        public final int y;

        /** Create a new place of the given indices.
         *
         * @param x 0-based column (vertical) index
         * @param y 0-based row (horizontal) index
         */
        public Place(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // other methods if needed ...
    }
}

