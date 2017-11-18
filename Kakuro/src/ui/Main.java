package ui;

import java.util.Set;

import persistence.ProblemReader;
import solver.CombGen;
import model.Problem;
import model.Solution;

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
	Gui gui = new Gui(problem);
	gui.setVisible(true);
	while(true) {
		problem.solve();
		gui = new Gui(problem);
		gui.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	}
}
