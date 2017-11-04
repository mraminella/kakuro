import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CombGen {
	public static void main(String[] args) {
		
		if(args.length == 2){
			Set<Solution> solutions;
		solutions = getPossibleCombinations(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		for(Solution solution : solutions){
			for(int value : solution.getValues())
				System.out.print(value + " ");
			System.out.print("; ");
		}
		System.out.println();
		}
	
		
	}

	private static Set<Solution> getPossibleCombinations(int num, int cells) {
		Set<Solution> result = new HashSet<Solution>();
		if(cells > 2){
			int tempFactor = num / cells;
			for(int i = 1; i < tempFactor; i++){
				Set<Solution> innerSolutions = getPossibleCombinations(num - i, cells - 1);
				for(Solution solNew : innerSolutions){
				Solution sol = new Solution();
				boolean validSolution = true;
				sol.addSolution(i);			
					for(int value : solNew.getValues()){
						if(value == i) validSolution = false;
						sol.addSolution(value);
					}
					if(validSolution) result.add(sol);
				}
				
			}
		}
		else // cells == 2
		{
			int firstFactor = num / 2 + 1; // l'addendo più grande
			int secondFactor = num - firstFactor; // l'addendo più piccolo
			while (firstFactor <= 9 && firstFactor < num) {
				Solution sol = new Solution();
				sol.addSolution(firstFactor); 
				sol.addSolution(secondFactor);
				result.add(sol);
				firstFactor++;
				secondFactor--;
			}
		}
		
		return result;
	}
	
	
}
