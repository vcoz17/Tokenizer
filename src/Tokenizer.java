package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Tokenizer {
	ArrayList<String> tokens;

	public Tokenizer(File file) throws FileNotFoundException {
		tokens = new ArrayList<>();
		readData(file);
	}

	private void readData(File file) throws FileNotFoundException {
		if (!file.exists())
			System.err.println("NO TRAINING FILE EXISTS!");
		else {
			Scanner scanner1 = new Scanner(file);
			while (scanner1.hasNextLine()) {
				Scanner scanner2 = new Scanner(scanner1.nextLine()).useDelimiter("[^a-zA-Z\\d\\s:^.]");
				while (scanner2.hasNext()) {
					Scanner scanner3 = new Scanner(scanner2.next());
					while (scanner3.hasNext()) {
						String s = scanner3.next();
						if (!Pattern.matches("([a-zA-Z]\\.)+", s)) {
							Scanner scanner4 = new Scanner(s).useDelimiter("[^a-zA-Z\\d\\s]");
							while (scanner4.hasNext())
								tokens.add(scanner4.next().toLowerCase());
						} else {
							Scanner scanner5 = new Scanner(s).useDelimiter("\\.$");
							while (scanner5.hasNext()) {
								String s2 = scanner5.next();
								String temp = "";
								for (int i = 0; i < s2.length(); i++) {
									if (s2.charAt(i) == '.') {
										continue;
									} else {
										temp += s.charAt(i);
									}
								}
								tokens.add(temp.toLowerCase());
							}
						}
					}
				}
			}

		}
	}

	//FOR PART B
	public ArrayList<String> mostFrequentTerm() {
		ArrayList<String> res = new ArrayList<String>();
		ArrayList<WordFrequency> wf = new ArrayList<WordFrequency>();
		Map<String, Long> occurrences = tokens.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
		Comparator<WordFrequency> comparator = new Comparator<WordFrequency>() {

			@Override
			public int compare(WordFrequency o1, WordFrequency o2) {
				// TODO Auto-generated method stub
				return (int) (o2.occurence - o1.occurence);
			}
		};
		PriorityQueue<WordFrequency> queue = new PriorityQueue<>(comparator);
		for (Map.Entry<String, Long> entry : occurrences.entrySet()) {
			queue.add(new WordFrequency(entry.getKey(), entry.getValue()));
		}
		int count = 0;
		for (WordFrequency w : queue) {
			if (count < 200) {
				res.add(w.word + " " + String.valueOf(w.occurence));
				count++;
			} else {
				break;
			}
		}
		return res;
	}

	public void printTokens() {
		for (String a : tokens) {
			System.out.println(a);
		}
	}
}
