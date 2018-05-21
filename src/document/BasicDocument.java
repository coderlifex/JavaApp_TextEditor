package document;

import java.util.List;

/**
 * A naive implementation of the Document abstract class.
 */
public class BasicDocument extends Document {
	/**
	 * Create a new BasicDocument object
	 * 
	 * @param text
	 *            The full text of the Document.
	 */
	public BasicDocument(String text) {
		super(text);
	}

	/**
	 * Get the number of words in the document. A "word" is defined as a contiguous
	 * string of alphabetic characters.
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords() {
		return getTokens("[a-zA-Z]+").size();
	}

	/**
	 * Get the number of sentences in the document. Sentences are defined as
	 * contiguous strings of characters ending in an end of sentence punctuation (.
	 * ! or ?) or the last contiguous set of characters in the document.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences() {
		return getTokens("[^.!?]+").size();
	}

	/**
	 * Get the total number of syllables in the document (the stored text).
	 * 
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables() {
		int count = 0;
		for (String s : getTokens("[a-zA-Z]+"))
			count += countSyllables(s);
		return count;
	}

}
