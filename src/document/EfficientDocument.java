package document;

import java.util.List;

/**
 * A class that represents a text document
 */
public class EfficientDocument extends Document {

	private int numWords; // The number of words in the document
	private int numSentences; // The number of sentences in the document
	private int numSyllables; // The number of syllables in the document

	public EfficientDocument(String text) {
		super(text);
		processText();
	}

	private boolean isWord(String tok) {
		return !(tok.indexOf("!") >= 0 || tok.indexOf(".") >= 0 || tok.indexOf("?") >= 0);
	}

	private void processText() {
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		numSentences = 0;
		numWords = 0;
		numSyllables = 0;
		for (int i = 0; i < tokens.size(); i++) {
			String s = tokens.get(i);
			if (s.charAt(0) == '!' || s.charAt(0) == '?' || s.charAt(0) == '.') {
				numSentences++;
			} else {
				numWords++;
				numSyllables += countSyllables(s);
				if (i == tokens.size() - 1)
					numSentences++;
			}
		}
	}

	@Override
	public int getNumSentences() {
		return numSentences;
	}

	@Override
	public int getNumWords() {
		return numWords;
	}

	/**
	 * Get the total number of syllables in the document (the stored text).
	 */
	@Override
	public int getNumSyllables() {
		return numSyllables;
	}
}
