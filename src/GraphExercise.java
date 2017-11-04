import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;



public class GraphExercise {
	Digraph dg;
	ArrayList<String> words = new ArrayList<String>();
	ArrayList<Integer> number = new ArrayList<Integer>();
	long before, after;
	

	public void readDataFile() throws IOException {
		 before  = System.currentTimeMillis();
//		 BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("resources/words-14-data.txt")));	
		 BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("resources/words-250-data.txt")));	
//		 BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("resources/words-5757-data.txt")));	

		int nbr = 0;
		while (true) {
			
			String word = r.readLine();
			if (word == null) { break; }
			assert word.length() == 5; // indatakoll, om man kör med assertions på
			words.add(word);
			number.add(nbr);
			nbr++;
		}
		createGraph(words);
	}



	private void createGraph(ArrayList<String> words) throws IOException {
		dg = new Digraph(words.size());	
		for(int i = 0; i < words.size(); i++){
			for(int j = 0; j < words.size(); j++){
				String one = words.get(i);
				String two = words.get(j);
		
				char[] arr = one.toCharArray();
				char[] cContain = new char[4];

					cContain[0] = arr[arr.length - 1];
					cContain[1] = arr[arr.length - 2];
					cContain[2] = arr[arr.length - 3];
					cContain[3] = arr[arr.length - 4];
					
					String s = words.get(j);
					char[] proof = s.toCharArray();
					int counter = 0;
					int pcounter = 0;
					while (counter < proof.length) {
						for (int ja = 0; ja < cContain.length; ja++) {
							if (proof[counter] == cContain[ja]) {
								cContain[ja] = '-';
								proof[counter] = '^';  //KOLLAR SÅ EJ DUBLETTER KMR MED. 
								pcounter++;
								
							}

						}
						counter++;
					}
					if (pcounter >= 4 && !s.contains(one)) {
						System.out.println(one +"("+i+") -> " + two +"(" + j + ")");
						dg.addEdge(i, j);
						
					}
				}

			
			}
	
		readTestFile();
		
	}

	public void readTestFile() throws IOException {
//		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("resources/words-14-test.txt")));
		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("resources/words-250-test.txt")));
//		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("resources/words-5757-test.txt")));
		System.out.println("---------------------------------------------------");
		while (true) {
			String line = r.readLine();
			if (line == null) {
				break;
			}
			assert line.length() == 11; // indatakoll, om man kör med assertions på
			String start = line.substring(0, 5);
			String goal = line.substring(6, 11);
			int starter = 0,goaler = 0;
			
			for(int i = 0; i<words.size(); i++){
				if(words.get(i).equals(start)){
					starter=number.get(i);
				}
				if(words.get(i).equals(goal)){
					goaler=number.get(i);
				}
				
			}
			BfsGraph bfdp = new BfsGraph(dg, starter);
			
			System.out.println("From " + start + " to " + goal);
			System.out.println("Path exist:"
					+ " " + bfdp.hasPathTo(goaler));
			if(bfdp.distTo(goaler)>2700000){
				System.out.println("distance: -1");
			}
			else{
				System.out.println("distance: "+ bfdp.distTo(goaler));
			}
			System.out.println("");
			 after  = System.currentTimeMillis();
//			System.out.println("testing - "+ bfdp.pathTo(goaler));					
			 
		}
		  System.out.println("Time it took: " + (after-before) / 1000.0 + " seconds");
	}
	
	public static void main(String[] args) throws IOException {
		GraphExercise gp = new GraphExercise();
		gp.readDataFile();
	}
}
