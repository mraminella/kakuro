package model;
import java.util.HashSet;
import java.util.Set;


public class Cell {
	private Color color;
	private Set<Solution> horizSolutions;
	private Set<Solution> vertSolutions;
	private Set<Integer> solutions;
	private int horizSum;
	private int vertSum;
	private int horizSumLength; // non serve
	private int vertSumLength; // non serve
	private int solutionsAmount; // non serve
	public Cell horizRule;
	public Cell vertRule;
	public Cell right;
	public Cell down;
	
	public int getHorizSumLength() {
		return horizSumLength;
	}
	public void setSolutions(Set<Integer> solutions) {
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
		solutions = new HashSet<Integer>();
		solutionsAmount = 0;
		this.color = Color.white;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Set<Integer> getSolutions() {
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
			sb.append(" ");
			// sb.append(this.horizRule != null ? this.horizRule.toString() : "null");
			// sb.append(this.vertRule != null ? this.vertRule.toString() : "null");
			//sb.append(solutionsAmount);
			for(Integer solution : this.solutions){
				sb.append(solution.toString());
				sb.append(";");
			}
		} else if(this.color.equals(Color.black))
			{
			sb.append("b");
			sb.append(horizSum);
			
			sb.append(":");/*
			for(Solution solution : this.horizSolutions){
				sb.append(solution.toString());
				sb.append(";");
			} */
			sb.append(this.horizSolutions.size());
			//sb.append('\n');
			sb.append("/");
			sb.append(vertSum);
			
			sb.append(":");
			/*
			for(Solution solution : this.vertSolutions){
				sb.append(solution.toString());
				sb.append(";");
			}
			*/
			sb.append(this.vertSolutions.size());
			
			}
		else if(this.color.equals(Color.solved)) {
			sb.append("S ");
			sb.append(this.solutions.iterator().next());
		}
		return sb.toString();
		
	}
	
	
}
