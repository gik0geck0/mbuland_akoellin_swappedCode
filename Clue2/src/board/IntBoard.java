package board;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public class IntBoard {
	private static int ROWS = 4;
	private static int COLS =4;
	private static int GRID_PIECES;
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private Set<Integer> targets;
	
	public IntBoard() {
		super();
		// TODO Auto-generated constructor stub
		GRID_PIECES = ROWS*COLS;
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		targets = new HashSet<Integer>();
	}
	public void calcAdjacencies(){
		//for loops
		for(int i = 0; i<GRID_PIECES; ++i){
			adjMtx.put(i, getAdjList(i));
		}
	}
	public void calcTargets(int startLoc, int steps){
		targets.clear();
		calcTargetsRecursion(startLoc, steps);
	}
	private void calcTargetsRecursion(int startLoc, int steps) {
		if(steps == 0) {
			targets.add(startLoc);
			return;
		}
		LinkedList<Integer> start = adjMtx.get(startLoc);
		for(int i = 0; i < start.size(); i++) {
			calcTargetsRecursion(start.get(i), steps-1);
		}
	}
	public Set getTargets(){
		return targets;
	}
	public LinkedList<Integer> getAdjList(int cell){
		LinkedList<Integer> adjList = new LinkedList<Integer>();
		if(cell %COLS ==0){
			//left edge
			if(cell == 0){
				//top
				adjList.add(cell +1);
				adjList.add(cell+COLS);
			}
			else if(cell == GRID_PIECES - COLS){
				//bottom
				adjList.add(cell +1);
				adjList.add(cell-4);
			}
			else {
				// inbetween
				adjList.add(cell+1);
				adjList.add(cell+4);
				adjList.add(cell-4);
			}		
		}
		else if((cell+1)%COLS == GRID_PIECES%COLS){
			//right edge
			if(cell == COLS -1){
				//top
				adjList.add(cell -1);
				adjList.add(cell+COLS);
			}
			else if(cell == GRID_PIECES - 1){
				//bottom
				adjList.add(cell -1);
				adjList.add(cell-4);
			}
			else {
				// inbetween
				adjList.add(cell-1);
				adjList.add(cell+4);
				adjList.add(cell-4);
			}
		}
		else if(cell < COLS){
			//on top
			adjList.add(cell+1);
			adjList.add(cell -1);
			adjList.add(cell+4);
		}
		else if(cell >(GRID_PIECES - COLS)){
			//on bottom
			adjList.add(cell+1);
			adjList.add(cell-1);
			adjList.add(cell-4);
		}
		else{
			//inbetween
			adjList.add(cell+1);
			adjList.add(cell-1);
			adjList.add(cell+4);
			adjList.add(cell-4);
		}
		return adjList;
	}
	public int calcIndex(int row, int column){
		return row*4 + column;
	}
}
