package model;
import java.util.HashSet;
import java.util.Set;


public class Cell {
	private Color color;
	private Set<Solution> horizSolutions;
	private Set<Solution> vertSolutions;
	private Set<Solution> solutions;
	private int horizSum;
	private int vertSum;
	private int horizSumLength;
	private int vertSumLength;
	private int solutionsAmount;
	
	public int getHorizSumLength() {
		return horizSumLength;
	}
	public void setSolutions(Set<Solution> solutions) {
		this.solutions = solutions;
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
		horizSolutions = new HashSet<Solution>();
		vertSolutions = new HashSet<Solution>();
		this.color = Color.black;
	}
	public void incSolutionsAmount() {
		solutionsAmount++;
	}
	public void decSolutionsAmount() {
		solutionsAmount--;
	}
	public int getSolutionsAmout() {
		return solutionsAmount;
	}
	public void setSolutionsAmount(int solutionsAmount) {
		this.solutionsAmount = solutionsAmount;
	}
	public Cell() {
		super();
		solutions = new HashSet<Solution>();
		solutionsAmount = 0;
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
	public int getHorizSum() {
		return horizSum;
	}
	public void setHorizSum(int horizSum) {
		this.horizSum = horizSum;
	}
	public Set<Solution> getHorizSolutions() {
		return horizSolutions;
	}
	public Set<Solution> getVertSolutions() {
		return vertSolutions;
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
			sb.append("%");
			sb.append(solutionsAmount);
			for(Solution solution : this.solutions){
				sb.append(solution.toString());
				sb.append(";");
			}
		} else
			{
			sb.append("b");
			sb.append(horizSum);
			sb.append(":");
			for(Solution solution : this.horizSolutions){
				sb.append(solution.toString());
				sb.append(";");
			}
			sb.append("/");
			sb.append(vertSum);
			sb.append(":");
			for(Solution solution : this.vertSolutions){
				sb.append(solution.toString());
				sb.append(";");
			}
			
			}
		return sb.toString();
		
	}
	
	
}
