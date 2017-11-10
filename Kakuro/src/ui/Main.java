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
		System.out.println(problem.toString());
	}
}
