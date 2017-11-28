package model;

import java.util.Iterator;
import java.util.Set;

import ui.Gui;

public class TreeSolver {
	public static Node initTree(Problem problem) {
		Node node = new Node(0,0);
		Cell beginning = problem.getCellWithLessSolutions();
		createChildren(node,beginning);
		return node;
	}
	
	public static void createChildren(Node father, Cell cell) {
		Node child;
		for(Integer domainValue : cell.getDomain()) {
				child = new Node(cell.i,cell.j);
				child.setValue(domainValue);
				child.setFather(father);
				father.getChildren().add(child);
		}
	}
	
	private static boolean isFather(Node node) {
		return (node.getI() == 0 && node.getJ() == 0);
	}
	public static Node explore(Node node, Problem problem) {
			if(problem.isSolutionComplete()) return node;
		Iterator<Node> childrenIterator = node.getChildren().iterator();
		Node nextChild = null;
		while(childrenIterator.hasNext()) {
			//Gui gui = new Gui(problem);
			//gui.setVisible(true);
			nextChild = childrenIterator.next();
			// Metto la soluzione del nodo in analisi nel Kakuro e vedo cosa succede
			Set<Integer> domain = problem.getCell(nextChild.getI(), nextChild.getJ()).getDomain();
			problem.getCell(nextChild.getI(), nextChild.getJ()).getDomain().clear();
			problem.getCell(nextChild.getI(), nextChild.getJ()).getDomain().add(nextChild.getValue());
			problem.cleanOtherSolutions(problem.getCell(nextChild.getI(), nextChild.getJ()));
			problem.getCell(nextChild.getI(), nextChild.getJ()).setColor(Color.solved);
			createChildren(nextChild,problem.getCellWithLessSolutions());

			if(problem.isSolutionOk()) {
				if(explore(nextChild,problem) != null) return nextChild;
			}
			domain.remove(node.getValue());
			problem.getCell(nextChild.getI(), nextChild.getJ()).getDomain().addAll(domain);
			problem.getCell(nextChild.getI(), nextChild.getJ()).setColor(Color.white);
			childrenIterator.remove();
		}
		return null;
	}
}
