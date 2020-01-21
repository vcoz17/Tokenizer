package src;

public class processingChar {
	public char[] word;
	public int start;
	public int start_end;
	public int temp;
	public int index;
	public int size = 100;

	public processingChar(Stoper stoper) {
		word = new char[100];
		start = 0;
		start_end = 0;
	}

	public void addChar(char ch) {
		if (start == word.length) {
			char[] new_b = new char[start + size];
			for (int c = 0; c < start; c++)
				new_b[c] = word[c];
			word = new_b;
		}
		word[start++] = ch;
	}

	public boolean isConsonant(int i) {
		if (word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u')
			return false;
		else if (word[i] == 'y') {
			if (i == 0)
				return true;
			else {
				return !isConsonant(i - 1);
			}
		}
		return true;
	}

	public final int checkVowelNonVowel() {
		int n = 0;
		int i = 0;
		while (true) {
			if (i > index)
				return n;
			if (!isConsonant(i))
				break;
			i++;
		}
		i++;
		while (true) {
			while (true) {
				if (i > index)
					return n;
				if (isConsonant(i))
					break;
				i++;
			}
			i++;
			n++;
			while (true) {
				if (i > index)
					return n;
				if (!isConsonant(i))
					break;
				i++;
			}
			i++;
		}
	}

	public final boolean checkVowel() {
		int i;
		for (i = 0; i <= index; i++)
			if (!isConsonant(i))
				return true;
		return false;
	}

	public final boolean doublec(int j) {
		if (j < 1)
			return false;
		if (word[j] != word[j - 1])
			return false;
		return isConsonant(j);
	}

	public boolean cvc(int i) {
		if (i < 2 || !isConsonant(i) || isConsonant(i - 1) || !isConsonant(i - 2))
			return false;
		{
			int ch = word[i];
			if (ch == 'w' || ch == 'x' || ch == 'y')
				return false;
		}
		return true;
	}

	public final boolean ends(String s) {
		int a = s.length();
		int b = temp - a + 1;
		if (b < 0)
			return false;
		for (int i = 0; i < a; i++)
			if (word[b + i] != s.charAt(i))
				return false;
		index = temp - a;
		return true;
	}

	public final void setChar(String s) {
		int a = s.length();
		int b = index + 1;
		for (int i = 0; i < a; i++)
			word[b + i] = s.charAt(i);
		temp = index + a;
	}

	public void step1ab() {
		if (word[temp] == 's') {
			if (ends("sses"))
				temp -= 2;
			else if (ends("ies"))
				setChar("i");
			else if (word[temp - 1] != 's')
				temp--;
		}
		if (ends("eed")) {
			if (checkVowelNonVowel() > 0)
				temp--;
		} else if ((ends("ed") || ends("ing")) && checkVowel()) {
			temp = index;
			if (ends("at"))
				setChar("ate");
			else if (ends("bl"))
				setChar("ble");
			else if (ends("iz"))
				setChar("ize");
			else if (doublec(temp)) {
				temp--;
				{
					int ch = word[temp];
					if (ch == 'l' || ch == 's' || ch == 'z')
						temp++;
				}
			} else if (checkVowelNonVowel() == 1 && cvc(temp))
				setChar("e");
		}
	}

	void helperStem() {
		temp = start - 1;
		if (temp > 1) {
			step1ab();
		}
		start_end = temp + 1;
		start = 0;
	}
}

//Citing resources:
//Manning, Christopher D., Mihai Surdeanu, John Bauer, Jenny Finkel, Steven J.
//Bethard, and David McClosky. 2014. The Stanford CoreNLP Natural Language
//Processing Toolkit In Proceedings of the 52nd Annual Meeting of the
//Association for Computational Linguistics: System Demonstrations, pp. 55-60.
