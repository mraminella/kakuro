package solver;
import java.util.HashSet;
import java.util.Set;

import model.Solution;


public class CombGen {
	

	public static Set<Solution> getPossibleCombinations(int num, int cells) {
		Set<Solution> result = new HashSet<Solution>();
		if(cells > 2){ // esplorazione delle soluzioni
			int tempFactor = num / cells;
			for(int firstFactor = 1; firstFactor < tempFactor; firstFactor++){
				Set<Solution> innerSolutions = getPossibleCombinations(num - firstFactor, cells - 1);
				for(Solution solNew : innerSolutions){
				Solution sol = new Solution();
				boolean validSolution = true;
				sol.addSolution(firstFactor);			
					for(int value : solNew.getValues()){
						if(value == firstFactor){
							validSolution = false;
							break;
						}
						sol.addSolution(value);
					}
					if(validSolution) result.add(sol);
				}
				
			}
		}
		else // cells == 2, caso radice
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
