package model;
import java.util.HashSet;
import java.util.Set;


public class Solution {
	Set<Integer> values;

	public Set<Integer> getValues() {
		return values;
	}
	public Solution() {
	 values = new HashSet<Integer>();
	}
	public void addSolution(int solution){
		values.add(solution);
	}
	
	@Override
	public int hashCode(){
		return values.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof Solution))return false;
	    Solution otherSolution = (Solution) o;
	   return this.values.equals(otherSolution.getValues());
	}
}
