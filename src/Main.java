package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileName1 = "tokenization-input-part-A.txt";
		String fileName2 = "stopwords.txt";
		File file1 = new File(fileName1);
		File file2 = new File(fileName2);
		Tokenizer tokenizer = new Tokenizer(file1);
		// tokenizer.printTokens();
		Stoper stoper = new Stoper(file2, tokenizer);
		// stoper.printResult();
		PorterStemmer pStem = new PorterStemmer(stoper);
		// pStem.printPS();
		//Output tokenized.txt
		Path fileA = Paths.get("tokenized.txt");
		Files.write(fileA, tokenizer.tokens, Charset.forName("UTF-8"));

		// PART B
		String fileName3 = "tokenization-input-part-B.txt";
		File file3 = new File(fileName3);
		Tokenizer tokenizerB = new Tokenizer(file3);
		Stoper stoperB = new Stoper(file2, tokenizerB);
		PorterStemmer pStemB = new PorterStemmer(stoperB);
		//Output terms.txt
		Path fileB = Paths.get("terms.txt");
		Files.write(fileB, tokenizerB.mostFrequentTerm(), Charset.forName("UTF-8"));
	}

}
