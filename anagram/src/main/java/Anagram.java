import java.lang.reflect.Array;
import java.util.*;

public class Anagram {

     private Boolean allCaps = false;
     private String word;
     private HashMap<Character, Integer> frequency;

    public Anagram(String referenceWord)
    {
        this.word = referenceWord.toLowerCase();
        this.frequency = getLetterFrequency(this.word);
        this.allCaps = (referenceWord.toUpperCase().equals(referenceWord));
    }

    /**
     * @param words : An array of words that you'd like for which any of those words are an anagram of your "reference"
     *              word.
     * @return A subset of the original list of words that are anagrams of the reference word.
     */
    public List<String> match(List<String> words)
    {
        /*
        *    Strategy:
        *
        *  The trick here is to remember that words are anagrams if and only if the letter frequencies match, which itself is only possible if the length of the words you're checking is the same.
        *
         */
        List<String> validAnagrams = new ArrayList<String>();

        for (String word: words)
        {
            if (word.length() == this.word.length()) {

                /* If the original word was all capitals, and this word is the same as the
                * original word (but without case-matching), then it can't be an anagram
                * per the last test case. */
                if (this.allCaps && (this.word.toLowerCase().equals(word.toLowerCase())))
                {
                    continue;
                }

                HashMap<Character, Integer> letterFrequency = getLetterFrequency(word.toLowerCase());

                if (this.frequency.equals(letterFrequency))
                {
                    validAnagrams.add(word);
                }

            }
        }

        return validAnagrams;
    }

    private HashMap<Character, Integer> getLetterFrequency(String word)
    {
        HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();

        for (int i = 0; i < word.length(); i++)
        {
            char character = word.charAt(i);

            // This adds 1 to the existing value for that key in the hashmap
            // (so if the value was null (meaning the letter was not seen before),
            // then it will be added to 1 which gives 1.
            frequency.merge(character, 1, Integer::sum);
        }

        return frequency;
    }







}
