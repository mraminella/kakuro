package model;

import java.util.Set;

import solver.CombGen;

public class Problem {
	
	Cell[][] cells;
	int nRows;
	int nColumns;

	public Problem(int nRows, int nColumns) {
		super();
		this.cells = new Cell[nRows][nColumns];
		this.nRows = nRows;
		this.nColumns = nColumns;
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
						while(length < nColumns - 1 && cells[i][length].getColor().equals(Color.white))
							length++;
						cells[i][j].setHorizSumLength(length - j);
					}
					if(cells[i][j].getVertSum() > 0){ // ho un vincolo di somma verticale (parola verticale)
						length = i;
						length++;
						while(length < nRows - 1 && cells[length][j].getColor().equals(Color.white))
							length++;
						cells[i][j].setVertSumLength(length - i);
					}
				}
			}
		}
	}

	public void fillSolutions(){ // riempie tutte le soluzioni del mondo 

		for(int i=0;i<nRows;i++){ // ciclo sulle righe
			for(int j=0;j<nColumns;j++){ // ciclo sulle colonne
				if(cells[i][j].getColor().equals(Color.black)){
					if(cells[i][j].getHorizSum() > 0){ // ho un vincolo di somma orizzontale (parola orizzontale)
						cells[i][j].getHorizSolutions().addAll(CombGen.getPossibleCombinations(cells[i][j].getHorizSum(), cells[i][j].getHorizSumLength()));
					}
				}
				if(cells[i][j].getVertSum() > 0){ // ho un vincolo di somma verticale (parola verticale)
					cells[i][j].getVertSolutions().addAll(CombGen.getPossibleCombinations(cells[i][j].getVertSum(), cells[i][j].getVertSumLength()));


				}
			}
		}
			/*
		int length;
		Set<Solution> solutions;
		for(int i=0;i<nRows;i++){ // ciclo sulle righe
			for(int j=0;j<nColumns;j++){ // ciclo sulle colonne
				if(cells[i][j].getColor().equals(Color.black)){
					if(cells[i][j].getHorizSum() > 0){ // ho un vincolo di somma orizzontale (parola orizzontale)
						solutions = CombGen.getPossibleCombinations(cells[i][j].getHorizSum(), cells[i][j].getHorizSumLength());
						length = cells[i][j].getHorizSumLength();
						while(length > 0){ // vado a inserire la soluzione su tutte le colonne...
							cells[i][length].getSolutions().addAll(solutions);
							length--;
						}
						if(cells[i][j].getVertSum() > 0){ // ho un vincolo di somma verticale (parola verticale)
							length = cells[i][j].getVertSumLength();
							solutions = CombGen.getPossibleCombinations(cells[i][j].getVertSum(), cells[i][j].getVertSumLength());
							while(length > 0){ // vado a inserire la soluzione su  tutte le righe..
								cells[length][j].getSolutions().addAll(solutions);
								length--;
							}
						}
					}
				}
			}
		}
			 */
		}

	public void findSuitableSolutionsAmount() {
		int length;
		for(int i=0;i<nRows;i++){
			for(int j=0;j<nColumns;j++){
				if(cells[i][j].getColor().equals(Color.black)){
					if(cells[i][j].getHorizSum() > 0){ // ho un vincolo di somma orizzontale (parola orizzontale)
						length = j;
						length++;
						while(length < cells[i][j].getHorizSumLength())
							length++;
						cells[i][length].setSolutionsAmount(cells[i][length].getSolutionsAmout() + cells[i][j].getHorizSolutions().size());
					}
					if(cells[i][j].getVertSum() > 0){ // ho un vincolo di somma verticale (parola verticale)
						length = i;
						length++;
						while(length < cells[i][j].getVertSumLength())
							length++;
						cells[length][j].setSolutionsAmount(cells[length][i].getSolutionsAmout() + cells[i][j].getVertSolutions().size());
					}
				}
			}
		}

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
