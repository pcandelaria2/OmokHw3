package omok.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board;
    Player p1 = new Player("p1");
    Player p2 = new Player("p2");
    Iterable<Board.Place> winner;
    @BeforeEach
    void setUp(){
        board = new Board(25);
        board.placeStone(5,10,p1);
        board.placeStone(6,10,p1);
        board.placeStone(7,10,p1);
        board.placeStone(8,10,p1);
        board.placeStone(9,10,p1);

        board.placeStone(9,11,p2);

        winner = board.winningRow();
        for(Board.Place element : winner){
            System.out.print(element);
        }




    }
    @Test
    void size() {
        assertEquals(25,board.size());

    }


    @Test
    void isFull() {
        assertFalse(board.isFull());


    }

    @Test
    void placeStone() {
        assertTrue(board.isOccupiedBy(5,10,p1));
        board.placeStone(21,21,p2);
        assertTrue(board.isOccupiedBy(21,21,p2));
    }

    @Test
    void isEmpty() {
        assertFalse(board.isEmpty(0,0));
        assertFalse(board.isEmpty(20,20));
        assertTrue(board.isEmpty(9,11));
    }

    @Test
    void isOccupied() {
        assertTrue(board.isOccupied(5,10));
        assertTrue(board.isOccupied(8,10));
        assertFalse(board.isOccupied(14,10));
        assertFalse(board.isOccupied(0,10));

    }

    @Test
    void isOccupiedBy() {
        assertTrue(board.isOccupiedBy(5,10,p1));
        assertTrue(board.isOccupiedBy(9,11,p2));
        assertFalse(board.isOccupiedBy(9,10,p2));
    }

    @Test
    void playerAt() {
        assertTrue(board.playerAt(6,10)==p1);
        assertTrue(board.playerAt(7,10)==p1);
        assertTrue(board.playerAt(8,10)==p1);
        assertTrue(board.playerAt(6,10)==p1);
        assertTrue(board.playerAt(9,11)==p2);
        assertFalse(board.playerAt(6,10)==p2);
    }

    @Test
    void isWonBy() {
        assertTrue(board.isWonBy(p1));
        assertFalse(board.isWonBy(p2));
    }

    @Test
    void winningRow() {

        winner = board.winningRow();
        assertIterableEquals(winner,board.winningRow());
    }
}