package model;

public class Problem {
	Cell[][] cells;
	int nRows;
	int nColumns;
	
	public Problem(int nRows, int nColumns) {
		super();
		this.cells = new Cell[nRows][nColumns];
		this.nRows = nRows;
		this.nColumns = nColumns;
	}
	
	public Cell getCell(int row, int column){
		return cells[row][column];
	}
	
	public void setCell(int row, int column, Cell cell){
		cells[row][column] = cell;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		int i;
		for(i=0;i<nRows;i++){
			for(int j=0;j<nColumns;j++){
				sb.append(cells[i][j].toString());
				sb.append(" ");
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
