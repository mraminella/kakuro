package persistence;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import model.Cell;
import model.Color;
import model.Problem;
import model.Solution;

public class ProblemReader {
	final static String FILE_NAME = "problema.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;

	public static Problem readProblema() {
		Problem result = null;
		Path path = Paths.get(FILE_NAME);
		try ( Scanner scanner = new Scanner(path,ENCODING.name()) ) {
			String line;
			int rows = Integer.parseInt(scanner.nextLine());
			int columns = Integer.parseInt(scanner.nextLine());
			result = new Problem(rows,columns);
			int currentColumn = 0;
			int currentRow = 0;
			while(currentRow < rows) {
				currentColumn = 0;
				line = scanner.nextLine();
				StringTokenizer st = new StringTokenizer(line);
				while(currentColumn < columns){
					String token = st.nextToken(";");
					if(token.contains("W")) {
						result.setCell(currentRow, currentColumn, new Cell()); 
					}

					else if (token.contains("B")) {
						String rule1 = token.split("\\\\")[0];
						rule1 = rule1.replaceAll("[^\\d.]", "");
						String rule2 = token.split("\\\\")[1];
						rule2 = rule2.replaceAll("[^\\d.]", "");
						int vertSum = Integer.parseInt(rule1);
						int horizSum = Integer.parseInt(rule2);
						//result.getCell(currentRow, currentColumn).setColor(Color.black);
						result.setCell(currentRow, currentColumn, new Cell(horizSum,vertSum)); 
					}
					else {
						result.setCell(currentRow, currentColumn, new Cell(0,0)); 
					}

					currentColumn++;
				}
				currentRow++;
			}

			result.initCells();

		} catch (IOException e) {
			System.out.println("File problema.txt inesistente o malformato");
		}
		return result;
	}



}
