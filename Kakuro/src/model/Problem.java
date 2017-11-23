package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import solver.CombGen;

public class Problem {
	
	Cell[][] cells;
	int nRows;
	int nColumns;

	public int getnRows() {
		return nRows;
	}

	public int getnColumns() {
		return nColumns;
	}

	public Problem(int nRows, int nColumns) {
		super();
		this.cells = new Cell[nRows][nColumns];
		this.nRows = nRows;
		this.nColumns = nColumns;
		
	}

	public void initCells() {
		for(int i=0;i<nRows;i++){
			for(int j=0;j<nColumns;j++){
				if(j + 1 < nColumns) cells[i][j].right = cells[i][j+1];
				if(i + 1 < nRows) cells[i][j].down = cells[i+1][j];
			}
		}
	}
	public Cell getCell(int row, int column){
		return cells[row][column];
	}

	public void setCell(int row, int column, Cell cell){
		cells[row][column] = cell;
	}

	public void obtainLengthForCells(){ // controlla la lunghezza delle parole
		int length;
		for(int i=0;i<nRows;i++){
			for(int j=0;j<nColumns;j++){
				if(cells[i][j].getColor().equals(Color.black)){
					if(cells[i][j].getHorizSum() > 0){ // ho un vincolo di somma orizzontale (parola orizzontale)
						length = j;
						length++;
						while(length < nColumns  && cells[i][length].getColor().equals(Color.white)) {
						
							cells[i][length].horizRule = cells[i][j];
							length++;
						}
						cells[i][j].setHorizSumLength(length - j - 1);
						
					}
					if(cells[i][j].getVertSum() > 0){ // ho un vincolo di somma verticale (parola verticale)
						length = i;
						length++;
						while(length < nRows && cells[length][j].getColor().equals(Color.white)) {
						
							cells[length][j].vertRule = cells[i][j];
							length++;
						}
						cells[i][j].setVertSumLength(length - i - 1);
						
					}
				}
			}
		}
	}

	public void fillBlackSolutions(){ // riempie tutte le soluzioni del mondo 

		for(int i=0;i<nRows;i++){ // ciclo sulle righe
			for(int j=0;j<nColumns;j++){ // ciclo sulle colonne
				if(cells[i][j].getColor().equals(Color.black)){
					if(cells[i][j].getHorizSum() > 0){ // ho un vincolo di somma orizzontale (parola orizzontale)
						cells[i][j].getHorizSolutions().addAll(CombGen.getPossibleCombinations(cells[i][j].getHorizSum(), cells[i][j].getHorizSumLength()));
					}

					if(cells[i][j].getVertSum() > 0){ // ho un vincolo di somma verticale (parola verticale)
						cells[i][j].getVertSolutions().addAll(CombGen.getPossibleCombinations(cells[i][j].getVertSum(), cells[i][j].getVertSumLength()));

					}


				}
			}
		}

	}
	
	public void fillWhiteSolutions(){ // riempie tutte le soluzioni del mondo 
		for(int i=0;i<nRows;i++){ // ciclo sulle righe
			for(int j=0;j<nColumns;j++){ // ciclo sulle colonne
				if(cells[i][j].getColor().equals(Color.white)){
					cells[i][j].setSolutions(intersectSolutions(cells[i][j].horizRule.getHorizSolutions(),cells[i][j].vertRule.getVertSolutions()));
				}
			}
		}
		
	}

	public Cell getCellWithLessSolutions() {
		int min = 10;
		Cell result = null;
		for(int i=0;i<nRows;i++){ // ciclo sulle righe
			for(int j=0;j<nColumns;j++){ // ciclo sulle colonne
				if(cells[i][j].getColor().equals(Color.white)){
					if(cells[i][j].getSolutions().size() < min) {
						min = cells[i][j].getSolutions().size();
						result = cells[i][j];
					}
				}
			}
		}
		return result;

	}
	
	public void solve() {
		Cell curr = getCellWithLessSolutions();
		if (curr.getSolutions().size() == 1) {
			cleanOtherSolutions(curr);
			 curr.setColor(Color.solved);
		}
		else {
			iterativeSampling(curr);
		}
	}
	
	private void iterativeSampling(Cell cell) {
		Set<Integer> solutions = new HashSet<Integer>();
		solutions.addAll(cell.getSolutions());
		Iterator<Integer> possibleSolutions = solutions.iterator();
		do{
			cell.getSolutions().addAll(solutions);
			cell.getSolutions().clear();
			cell.getSolutions().add(possibleSolutions.next());
			solve();
		}while(!isSolutionOk(cell));
	}

	
	private boolean isSolutionOk(Cell cell) {
		Cell toCheck;
		toCheck = cell.horizRule.right;
		while(toCheck != null && toCheck.getColor().equals(Color.white)) {
			if(toCheck.getSolutions().isEmpty()) 
				return false;
			toCheck = toCheck.right;
		}
		toCheck = cell.vertRule.down;
		while(toCheck != null && toCheck.getColor().equals(Color.white)) {
			if(toCheck.getSolutions().isEmpty()) 
				return false;
			toCheck = toCheck.down;
		}
		return true;
	}
	
	private void cleanOtherSolutions(Cell cell) {
		
		// PREAMBOLO: questo metodo viene chiamato per una cella che ha una sola soluzione
		int solution = cell.getSolutions().iterator().next();

		Cell toClean; 
		// adesso devo togliere le soluzioni che non comprendono questo numero
		// Tolgo le soluzioni senza il numero della soluzione dalla regola orrizzontale
		Iterator<Solution> iterator = cell.horizRule.getHorizSolutions().iterator();
		while(iterator.hasNext()) {
			Solution horizRuleSolution = iterator.next();
			if(!horizRuleSolution.getValues().contains(solution)) {
				iterator.remove();
			}
		}
		
		// Tolgo le soluzioni senza il 6 dalla regola verticale
		iterator = cell.vertRule.getVertSolutions().iterator();
		while(iterator.hasNext()) {
			Solution vertRuleSolution = iterator.next();
			if(!vertRuleSolution.getValues().contains(solution)) {
				iterator.remove();
			}
		}
		
		// Ricalcolo tutte le soluzioni possibili a partire dalle combinazioni rimanenti
		toClean = cell.horizRule.right; // scorro sugli elementi a destra della cella nera col vincolo orizzontale
		while(toClean != null && toClean.getColor().equals(Color.white) && toClean != cell) {
			toClean.getSolutions().clear();
			toClean.getSolutions().addAll(intersectSolutions(toClean.horizRule.getHorizSolutions(),toClean.vertRule.getVertSolutions()));
			toClean = toClean.right;
		}
		toClean = cell.vertRule.down; // scorro sugli elementi sotto della cella nera col vincolo verticale
		while(toClean != null && toClean.getColor().equals(Color.white) && toClean != cell) {
			toClean.getSolutions().clear();
			toClean.getSolutions().addAll(intersectSolutions(toClean.horizRule.getHorizSolutions(),toClean.vertRule.getVertSolutions()));
			toClean = toClean.down;
		}
		
		// Dato che le nuove combinazioni includeranno anche il numero della soluzione
		// che ho appena trovato, dovrò togliere tale numero dalle altre celle,
		// che non possono avere tale soluzione
		
		toClean = cell.horizRule.right;
		while(toClean != null && toClean.getColor().equals(Color.white)  ) {
			if(toClean != cell){
				toClean.getSolutions().remove(solution);
		
			}
			toClean = toClean.right;
		}
		toClean = cell.vertRule.down;
		while(toClean != null && toClean.getColor().equals(Color.white)) {
			if(toClean != cell){
			toClean.getSolutions().remove(solution);
			}
			toClean = toClean.down;
		}
		
	}

	public Set<Integer> intersectSolutions(Set<Solution> set1, Set<Solution> set2)
	{
		Set<Integer> result = new HashSet<Integer>();
		Set<Integer> temp = new HashSet<Integer>();
		for(Solution solution : set1) {
			result.addAll(solution.getValues());
		}
		for(Solution solution : set2) {
			temp.addAll(solution.getValues());
		}
		result.retainAll(temp);
		return result;
	}
	
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<nRows;i++){
				for(int j=0;j<nColumns;j++){
					sb.append(cells[i][j].toString());
					sb.append("||\t");
				}
				sb.append('\n');
			}
			return sb.toString();
		}
	}
