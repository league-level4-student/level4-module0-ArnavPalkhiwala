package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start

		int r = new Random().nextInt((width) - 1);
		int rTwo = new Random().nextInt((height) - 1);
		Cell selected = maze.getCell(r, rTwo);

		// 5. call selectNextPath method with the randomly selected cell

		selectNextPath(selected);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited

		currentCell.setBeenVisited(true);
		;

		// B. Get an ArrayList of unvisited neighbors using the current cell and the
		// method below

		ArrayList<Cell> unvisited = getUnvisitedNeighbors(currentCell);

		// C. if has unvisited neighbors,

		if (unvisited.size() > 0) {

			// C1. select one at random.

			int r = new Random().nextInt(unvisited.size());

			// C2. push it to the stack

			Cell randNeighbor = unvisited.get(r);
			uncheckedCells.push(unvisited.get(r));

			// C3. remove the wall between the two cells

			if (currentCell.getX() - 1 == randNeighbor.getX()) {

				currentCell.setWestWall(false);
				randNeighbor.setEastWall(false);
			}

			if (currentCell.getX() + 1 == randNeighbor.getX()) {

				currentCell.setEastWall(false);
				randNeighbor.setWestWall(false);
			}

			if (currentCell.getY() - 1 == randNeighbor.getY()) {

				currentCell.setNorthWall(false);
				randNeighbor.setSouthWall(false);
			}

			if (currentCell.getY() + 1 == randNeighbor.getY()) {

				currentCell.setSouthWall(false);
				randNeighbor.setNorthWall(false);
			}

			// C4. make the new cell the current cell and mark it as visited

			randNeighbor = currentCell;
			randNeighbor.hasBeenVisited();

			// C5. call the selectNextPath method with the current cell

			selectNextPath(currentCell);

		}

		// D. if all neighbors are visited

		if (currentCell.hasNorthWall() && currentCell.hasSouthWall() && currentCell.hasEastWall() && currentCell.hasWestWall()) {

			// D1. if the stack is not empty
			
			if (uncheckedCells.size() > 0) {

			// D1a. pop a cell from the stack
				
				Cell popped = uncheckedCells.pop();

			// D1b. make that the current cell
				
				popped = currentCell;

			// D1c. call the selectNextPath method with the current cell
				
				selectNextPath(currentCell);
				
			}
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() - 1 == c2.getX()) {

			c1.setWestWall(false);
			c2.setEastWall(false);
		}

		if (c1.getX() + 1 == c2.getX()) {

			c1.setEastWall(false);
			c2.setWestWall(false);
		}

		if (c1.getY() - 1 == c2.getY()) {

			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}

		if (c1.getY() + 1 == c2.getY()) {

			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}

	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {

		int cellX = c.getX();
		int cellY = c.getY();
		maze.getCell(cellX, cellY);
		ArrayList<Cell> getUnvisitedNeighbors = new ArrayList<Cell>();
		if (!maze.getCell(cellX + 1, cellY).hasBeenVisited()) {
			getUnvisitedNeighbors.add(maze.getCell(cellX + 1, cellY));
		}
		if (!maze.getCell(cellX, cellY + 1).hasBeenVisited()) {
			getUnvisitedNeighbors.add(maze.getCell(cellX, cellY + 1));
		}

		if (!maze.getCell(cellX - 1, cellY).hasBeenVisited()) {
			getUnvisitedNeighbors.add(maze.getCell(cellX + -1, cellY));
		}

		if (!maze.getCell(cellX, cellY - 1).hasBeenVisited()) {
			getUnvisitedNeighbors.add(maze.getCell(cellX, cellY - 1));
		}

		return getUnvisitedNeighbors;
	}
}
