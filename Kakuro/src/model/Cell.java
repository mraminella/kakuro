package model;
import java.util.Set;


public class Cell {
	private Color color;
	private Set<Integer> values;
	private int horizSum;
	private int vertSum;
	
	public Cell(int horizSum, int vertSum) {
		super();
		this.horizSum = horizSum;
		this.vertSum = vertSum;
		this.color = Color.black;
	}
	public Cell(Set<Integer> values) {
		super();
		this.values = values;
		this.color = Color.white;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Set<Integer> getValues() {
		return values;
	}
	public void setValues(Set<Integer> values) {
		this.values = values;
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
			for(int value : values){
				sb.append(value);
				sb.append(",");
			}
		} else
			{
			sb.append("b");
			sb.append(horizSum);
			sb.append("/");
			sb.append(vertSum);
			}
		return sb.toString();
		
	}
	
	
}
