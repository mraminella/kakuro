package ui;

import java.util.Set;

import persistence.ProblemReader;
import solver.CombGen;
import model.Node;
import model.Problem;
import model.Solution;
import model.TreeSolver;

public class Main {
public static void main(String[] args) {
		/*
		if(args.length == 2){
			Set<Solution> solutions;
		solutions = CombGen.getPossibleCombinations(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		for(Solution solution : solutions){
			for(int value : solution.getValues())
				System.out.print(value + " ");
			System.out.print("; ");
		}
		System.out.println();
		}
	*/
	
	Problem problem = ProblemReader.readProblema();
	problem.obtainLengthForCells();
	problem.fillBlackSolutions();
	problem.fillWhiteSolutions();
	// problem.findSuitableSolutionsAmount();
	//System.out.println(problem.toString());
	
	int i = 0;
	Node father = TreeSolver.initTree(problem);
	father = TreeSolver.explore(father, problem);
	Gui gui = new Gui(problem);
	gui.setVisible(true);
	
		
	}
}
