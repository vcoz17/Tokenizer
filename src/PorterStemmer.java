package src;

import java.io.File;
import java.util.ArrayList;

public class PorterStemmer {
	ArrayList<String> stopResult;
	ArrayList<String> psResult;
	Stoper stoper;

	public PorterStemmer(Stoper stoper) {
		this.stoper = stoper;
		stopResult = stoper.stopResult;
		psResult = new ArrayList<>();
		processingChar psChar = new processingChar(stoper);
		stemming(psChar);
	}

	public void stemming(processingChar psChar) {
		for (String s : stopResult) {
			char[] characters = s.toCharArray();
			for (char character : characters) {
				psChar.addChar(character);
			}
			psChar.helperStem();
			psResult.add(new String(psChar.word, 0, psChar.start_end));
		}
	}

	public void printPS() {
		for (String s : psResult) {
			System.out.println(s);
		}
	}
}

// Citing resources:
// Manning, Christopher D., Mihai Surdeanu, John Bauer, Jenny Finkel, Steven J.
// Bethard, and David McClosky. 2014. The Stanford CoreNLP Natural Language
// Processing Toolkit In Proceedings of the 52nd Annual Meeting of the
// Association for Computational Linguistics: System Demonstrations, pp. 55-60.
