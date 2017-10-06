package monstersanddungeons.util.dungeon;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DungeonMaze {
	/**
	 * The width of the maze in blocks
	 */
	private int xWidth, yWidth;
	
	private int hallwayWidth = 1;
	
	/*/**
	 * The width of the maze in tunnels
	 */
	//private int xTunnel, yTunnel;
	
	/**
	 * The coordinates of the cell that is currently being visited
	 */
	private int currentX, currentY;
	
	/**
	 * The order in which the cells of the maze has been visited
	 */
	ArrayList<int[]> stack;
	
	/**
	 * For each coordinate decides whether it is a block or air.
	 */
	boolean[][] isTunnel;
	
	/*/**
	 * For each tunnel coordinate decides whether this has been visited or not.
	 */
	//boolean[][] hasBeenVisited;
	
	
	private Random rand = new Random();
	
	private World world;
	
	private BlockPos pos;
	
	private boolean makeWallsAround, areWallsIncludedInWidth;
	
	public DungeonMaze(int x, int y, World world, BlockPos pos){
		this(x, y, true, true, true, world, pos);
	}	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param isBlockWidthIf : If true the width is defined in blocks. If false the width is defined in "rows" in the labyrinth
	 */
	public DungeonMaze(int x, int y, boolean isBlockWidth, boolean makeWallsAround, boolean areWallsIncludedInWidth, World world, BlockPos pos){
		this.world = world;
		this.makeWallsAround = makeWallsAround;
		this.areWallsIncludedInWidth = areWallsIncludedInWidth;
		
		if(makeWallsAround && areWallsIncludedInWidth){
			this.pos = pos.add(1, 0, 1);
			if(isBlockWidth){
				x-=2;
				y-=2;
			}
		}else{
			this.pos = pos;
		}

		if(isBlockWidth){
			if(x < hallwayWidth * 3){
				x = hallwayWidth * 3;
			}
			else{
				if(x%hallwayWidth != 0){
					x -= x%hallwayWidth;
				}
				if((x/hallwayWidth)%2 == 0){
					x -= hallwayWidth;
				}			
			}
			this.xWidth = x/hallwayWidth;
			
			if(y < hallwayWidth * 3){
				y = hallwayWidth * 3;
			}
			else{
				if(y%hallwayWidth != 0){
					y -= y%hallwayWidth;
				}
				if((y/hallwayWidth)%2 == 0){
					y -= hallwayWidth;
				}			
			}
			this.yWidth = y;
			
			//this.xTunnel = (x-(x%2))/2;
			//this.yTunnel = (y-(y%2))/2;
		}else{
			this.xWidth = 2*x + 1;
			this.yWidth = 2*y + 1;
			
			//this.xTunnel = x;
			//this.yTunnel = y;
		}
	}
	
	/**
	 * Generates the maze at the given.
	 * @param pos - the position to start generating the maze.
	 */
	public void generateMaze(World world, BlockPos pos){
		currentX = 0;
		currentY = 0;
		
		stack = new ArrayList<int[]>();
		isTunnel = new boolean[xWidth][yWidth];
		//hasBeenVisited = new boolean[xWidth][yWidth];
		
		markCurrentCellAsVisited();
		int stacksAddedByBranch = 1; //Shouldn't be set to less than 1 from here.
		
		System.out.println("Starting loop");
		while(!isMazeFinished() && stacksAddedByBranch > 0){
			int stackSizeOriginal = stack.size();
			System.out.println("Expanding branch");
			expandBranch();
			stacksAddedByBranch = stack.size() - stackSizeOriginal;
			System.out.println("Stacks added by branch:" + stacksAddedByBranch);
			findLastUnvisitedNeighbours();
		}
		
		int xRow = 0;
		for(boolean[] row : isTunnel){
			int yRow = 0;
			for(boolean isAir : row){
				BlockPos xyPos = this.pos.add(xRow, 0, yRow);
				if(!isAir){
					world.setBlockState(xyPos, Blocks.LOG.getDefaultState());
					world.setBlockState(xyPos.add(0, 1, 0), Blocks.LEAVES.getDefaultState());
				}
				
				yRow++;
			}
			xRow++;
		}
		
		if(this.makeWallsAround){
			BlockPos wallPos = this.pos.add(-1, 0, -1);
			for(int xPos=0;xPos<xWidth+2;xPos++){
				for(int yPos=0;yPos<yWidth+2;yPos++){
					for(int height=0;height<2;height++){
						if(xPos == 0 || xPos == xWidth+1 || yPos == 0 || yPos == yWidth+1){
							world.setBlockState(wallPos.add(xPos, height, yPos), Blocks.STONE.getDefaultState());
						}
					}
				}
			}
		}
	}
	
	private void findLastUnvisitedNeighbours() {
		System.out.println(stack.toString());
		int i=0;
		for(int[] coords : stack){
			i++;
			currentX = coords[0];
			currentY = coords[1];
			if(getHasUnvisitedNeighbour(getIsNeighboursUnvisited())){
				break;
			}
		}
		
		for(int j=0;j<i;j++){
			stack.remove(0);
		}
	}

	private boolean isMazeFinished(){
		//TODO wall stuff
		for(int i=0;i<isTunnel.length;i++){
			if(i % 2 == 1){
				continue;
			}else for(int j=0;j<isTunnel[i].length;j++){
				if(j % 2 == 1){
					continue;
				}else{
					if(!isTunnel[i][j]){
						System.out.println("Maze still not finished. Stopped at " + i + " & " + j + ".");
						return false;
					}
				}	
			}
		}
		return true;
	}
	
	private void expandBranch(){
		while(getHasUnvisitedNeighbour(getIsNeighboursUnvisited())){
			System.out.println("Running expand loop with xy: " + currentX + " & " + currentY);
			ArrayList<EnumMazeDirection> unvisitedNeighbours = new ArrayList<DungeonMaze.EnumMazeDirection>();
			for(int i=0;i<4;i++){
				System.out.println(getIsNeighboursUnvisited()[i]);
				if(getIsNeighboursUnvisited()[i]){
					System.out.println("Added "  + EnumMazeDirection.getDirectionFromID(i) + " from id " + i);
					unvisitedNeighbours.add(EnumMazeDirection.getDirectionFromID(i));
				}else{
					System.out.println("Didn't add " + i);
				}
			}
			
			EnumMazeDirection directionToVisit = unvisitedNeighbours.get(world.rand.nextInt(unvisitedNeighbours.size()));
			
			System.out.println("Expanding " + directionToVisit.toString() + ".");
			System.out.println("With " + directionToVisit.isX + " & " + directionToVisit.isPlus + ".");
			System.out.println("On " + (currentX+1) + " & " + (currentY+1) + ".");
			System.out.println("With max " + xWidth + " & " + yWidth + ".");
			System.out.println(unvisitedNeighbours.toString());
			if(directionToVisit.isX){
				markCellAsVisited(currentX + (directionToVisit.isPlus ? 1 : -1), currentY);
				currentX += directionToVisit.isPlus ? 2 : -2;
			}else{
				markCellAsVisited(currentX, currentY + (directionToVisit.isPlus ? 1 : -1));
				currentY += directionToVisit.isPlus ? 2 : -2;
			}
			
			markCurrentCellAsVisited();
		}
	}
	
	private void markCurrentCellAsVisited(){
		markCellAsVisited(currentX, currentY);
		stack.add(0, new int[]{currentX, currentY});
	}
	
	private void markCellAsVisited(int x, int y){
		isTunnel[x][y] = true;
		for(int i=0;i<hallwayWidth;i++){
			for(int j=0;j<hallwayWidth;j++){
				world.setBlockToAir(pos.add((x*hallwayWidth)+i, 0, (y*hallwayWidth)+j));
			}
		}
	}


	private boolean getHasUnvisitedNeighbour(boolean[] isUnvisitedArray){
		for(boolean isUnvisited : isUnvisitedArray){
			if(isUnvisited){
				return true;
			}
		}
		
		return false;
	}

	private boolean[] getIsNeighboursUnvisited() {
		boolean[] isUnvisited = new boolean[4];
		//UP
		if((yWidth - (currentY + 1)) > 1  && !isTunnel[currentX][currentY+2]){
			System.out.println("y:" + currentY);
			System.out.println("max:" + yWidth);
			System.out.println("Up with:" + (yWidth - currentY));
			isUnvisited[0] = true;
		}
		
		//RIGHT
		if((xWidth - (currentX + 1)) > 1 && !isTunnel[currentX+2][currentY]){
			isUnvisited[1] = true;
		}
		
		//DOWN
		if(currentY > 1 && !isTunnel[currentX][currentY-2]){
			isUnvisited[2] = true;
		}
		
		//LEFT
		if(currentX > 1 && !isTunnel[currentX-2][currentY]){
			isUnvisited[3] = true;
		}
		
		return isUnvisited;
	}
	
	private static enum EnumMazeDirection{
		UP(false, true),
		RIGHT(true, true),
		DOWN(false, false),
		LEFT(true, false);
		
		boolean isX, isPlus;
		
		private EnumMazeDirection(boolean isX, boolean isPositive) {
			this.isX = isX;
			this.isPlus = isPositive;
		}
		
		public static EnumMazeDirection getDirectionFromID(int id){
			return values()[id];
		}
	}
}
