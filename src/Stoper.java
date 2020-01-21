package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stoper {
	ArrayList<String> stoplist;
	ArrayList<String> stopResult;
	ArrayList<String> tokens;

	public Stoper(File file, Tokenizer tokenizer) throws FileNotFoundException {
		tokens = tokenizer.tokens;
		stoplist = new ArrayList<>();
		stopResult = new ArrayList<>();
		readStopFile(file);
		stopFunc();
	}

	private void readStopFile(File file) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			stoplist.add(scanner.nextLine());
		}
	}

	private void stopFunc() {
		for (String s : tokens) {
			if (!stoplist.contains(s)) {
				stopResult.add(s);
			}
		}
	}

	public void printResult() {
		for (String s : stopResult) {
			System.out.println(s);
		}
	}
}

