package com.julie.tictactoegame;

import java.util.Random;

/**
 * Created by JULIE on 4/17/2018.
 */

public class Board{

        private static final Random RANDOM = new Random();
        private char[] els;
        private char currentPlayer;
        private boolean ended;
        private static int score;

        /*
        constructor for the board class.Initializes the elements of the board and a new game
         */
        public Board() {
            els = new char[9];
            newGame();
        }

        /*
        Method that checks if the game is ended or not
         */
        public boolean isEnded() {
            return ended;
        }

    /*
     method will let you to set the mark of the currentPlayer on the grid at a given (x,y) position
     the user can play at the position if the game is not ended  and if the specific cell is not empty
     */

        public char play(int x, int y) {
            if (!ended && els[3 * y + x] == ' ') {
                els[3 * y + x] = currentPlayer;
                changePlayer();
            }

            return checkEnd();
        }

        /*
        method that allows the players to switch places
        */

        public void changePlayer() {
            currentPlayer = (currentPlayer == 'X' ? 'O' : 'X');
        }

        /*
        returns the element at the position  specified by the x and y co-ordinates
         */
        public char getElt(int x, int y) {
            return els[3 * y + x];
        }

        /*
        method that starts a new game
         */
        public void newGame() {
            for (int i = 0; i < els.length; i++) {
                els[i] = ' ';
            }

            currentPlayer = 'X';
            ended = false;
        }

        /*
        checks both vertically and horizontally if all cells are filled
         */
        public char checkEnd() {
            for (int i = 0; i < 3; i++) {
                if (getElt(i, 0) != ' ' &&
                        getElt(i, 0) == getElt(i, 1) &&
                        getElt(i, 1) == getElt(i, 2)) {
                    ended = true;
                    return getElt(i, 0);
                }

                if (getElt(0, i) != ' ' &&
                        getElt(0, i) == getElt(1, i) &&
                        getElt(1, i) == getElt(2, i)) {
                    ended = true;
                    return getElt(0, i);
                }
            }

            if (getElt(0, 0) != ' ' &&
                    getElt(0, 0) == getElt(1, 1) &&
                    getElt(1, 1) == getElt(2, 2)) {
                ended = true;
                return getElt(0, 0);
            }

            if (getElt(2, 0) != ' ' &&
                    getElt(2, 0) == getElt(1, 1) &&
                    getElt(1, 1) == getElt(0, 2)) {
                ended = true;
                return getElt(2, 0);
            }

            for (int i = 0; i < 9; i++) {
                if (els[i] == ' ')
                    return ' ';
            }

            return 'T';
        }

        /*
         method lets the computer to randomly place a mark on the grid.
         */
        public char computer() {
            if (!ended) {
                int position = -1;

                do {
                    position = RANDOM.nextInt(9);
                } while (els[position] != ' ');

                els[position] = currentPlayer;
                changePlayer();
            }

            return checkEnd();
        }
}
