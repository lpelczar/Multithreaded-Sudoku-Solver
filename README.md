# Multithreaded-Sudoku-Solver

Java application that solves given sudoku using multithreading.

## Description

Algorithm consists of two phases:

Phase 1 - Filling every cell with possibilities

![c1](https://raw.github.com/lpelczar/Multithreaded-Sudoku-Solver/master/docs/phase1.png)


Phase 2 - Updating solved cells for every column, row and box

![c1](https://raw.github.com/lpelczar/Multithreaded-Sudoku-Solver/master/docs/phase2.png)

![c1](https://raw.github.com/lpelczar/Multithreaded-Sudoku-Solver/master/docs/phase3-2.png)


However not every sudoku can be solved by this approach alone. For more advanced sudoku's when there are no more sole candidates algorithm finds a cell with least number of possibilities and start new thread for each possibility.


![c1](https://raw.github.com/lpelczar/Multithreaded-Sudoku-Solver/master/docs/phase4.png)

![c1](https://raw.github.com/lpelczar/Multithreaded-Sudoku-Solver/master/docs/phase5.png)


Finally, one thread will find a solution and make callback to main thread.

## Screenshots

![c1](https://raw.github.com/lpelczar/Multithreaded-Sudoku-Solver/master/docs/sudoku1-2.png)

![c1](https://raw.github.com/lpelczar/Multithreaded-Sudoku-Solver/master/docs/sudoku2.png)

![c1](https://raw.github.com/lpelczar/Multithreaded-Sudoku-Solver/master/docs/sudoku3-2.png)

## More info

Project made for [Codecool](https://codecool.com/) programming course.
