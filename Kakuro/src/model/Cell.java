package model;
import java.util.Set;


public class Cell {
	private Color color;
	private Set<Solution> solutions;
	private int horizSum;
	private int vertSum;
	private int horizSumLength;
	private int vertSumLength;
	
	public int getHorizSumLength() {
		return horizSumLength;
	}
	public void setHorizSumLength(int horizSumLength) {
		this.horizSumLength = horizSumLength;
	}
	public int getVertSumLength() {
		return vertSumLength;
	}
	public void setVertSumLength(int vertSumLength) {
		this.vertSumLength = vertSumLength;
	}
	public Cell(int horizSum, int vertSum) {
		super();
		this.horizSum = horizSum;
		this.vertSum = vertSum;
		this.color = Color.black;
	}
	public Cell(Set<Solution> solutions) {
		super();
		this.solutions = solutions;
		this.color = Color.white;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Set<Solution> getSolutions() {
		return solutions;
	}
	public void setValues(Set<Solution> solutions) {
		this.solutions = solutions;
	}
	public int getHorizSum() {
		return horizSum;
	}
	public void setHorizSum(int horizSum) {
		this.horizSum = horizSum;
	}
	public int getVertSum() {
		return vertSum;
	}
	public void setVertSum(int vertSum) {
		this.vertSum = vertSum;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(this.color.equals(Color.white)){
			sb.append("w");
			for(Solution solution : this.solutions){
				sb.append(solution.toString());
				sb.append(",");
			}
		} else
			{
			sb.append("b");
			sb.append(horizSum);
			sb.append(" l=");
			sb.append(horizSumLength);
			sb.append("/");
			sb.append(vertSum);
			sb.append(" l=");
			sb.append(vertSumLength);
			}
		return sb.toString();
		
	}
	
	
}
