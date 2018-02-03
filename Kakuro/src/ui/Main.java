package ui;


import input.InputParser;
import input.KakuroOutputBuilder;
import input.OutputBuilder;
import input.SerialAdapter;
import persistence.ProblemReader;
import model.Problem;

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
	InputParser kakuroInputParser = new input.KakuroInputParser(problem);
	SerialAdapter serialAdapter = new SerialAdapter(kakuroInputParser);
	
	serialAdapter.Listen();
	
	OutputBuilder kakuroOutputBuilder = new KakuroOutputBuilder(serialAdapter, kakuroInputParser);
	
	kakuroOutputBuilder.sendProblem(problem);
	
	kakuroOutputBuilder.requestKakuro();
	
	Gui gui = new Gui(problem);
	gui.setVisible(true);
	/*
	 * 
	 * Modalità java: fa tutto in java
	 *
	problem.obtainLengthForCells();
	problem.fillBlackSolutions();
	problem.fillWhiteSolutions();
	
	Node father = TreeSolver.initTree(problem);
	TreeSolver.explore(father, problem);
	Gui gui = new Gui(problem);
	gui.setVisible(true);
	*/

	
	
		
	}
}
