package document;

/** 
 * A class that represents a text document
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;

	protected Document(String text) {
		this.text = text;
	}

	/*
	 * Returns the tokens that match the regex pattern from the document text
	 * string.
	 */
	protected List<String> getTokens(String pattern) {
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}

	protected int countSyllables(String word) {
		String newWord = word.toLowerCase();
		int count = 0;
		for (int i = 0; i < newWord.length(); i++) {
			if (isVowel(newWord.charAt(i))) {
				if (i < newWord.length() - 1 || newWord.charAt(i) != 'e' || count == 0)
					count++;
				while (i < newWord.length() && isVowel(newWord.charAt(i)))
					i++;
			}
		}
		return count;
	}

	protected boolean isVowel(char a) {
		return a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u' || a == 'y';
	}

	//For testing
	public static boolean testCase(Document doc, int syllables, int words, int sentences) {
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound + ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound + ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound + ", expected " + sentences);
			passed = false;
		}

		if (passed) {
			System.out.println("passed.\n");
		} else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}

	public abstract int getNumWords();

	public abstract int getNumSentences();

	public abstract int getNumSyllables();

	public String getText() {
		return this.text;
	}

	/** return the Flesch readability score of this document */
	public double getFleschScore() {
		double words = (double) getNumWords();
		double sentences = (double) getNumSentences();
		double syllables = (double) getNumSyllables();
		return 206.835 - 1.015 * (words / sentences) - 84.6 * (syllables / words);
	}

}
