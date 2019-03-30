import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashSet;
import java.util.Set;

class IsogramChecker {

    boolean isIsogram(String phrase) {

        // Couldn't find a built-in method to do this, but I'm sure one exists.

        // Strategy:
        // Remove non-alphabetic characters from phrase, and force it to be case-insensitive.
        // Create an empty Hashset for storing letters.
        // Iterate over every letter in the phrase, and attempt to put it into the
        // set. Because the set can't contain duplicates, if it fails when appending
        // to the set, then you know that letter you tried to add was a duplicate.

        if (phrase.equals("") || phrase == null)
        {
            return true;
        }

        phrase = phrase.toLowerCase().replaceAll("\\W", "");

        Set<Character> duplicates = new HashSet<>();

        final CharacterIterator characterIterator = new StringCharacterIterator(phrase);
        for (char character = characterIterator.first(); character != CharacterIterator.DONE; character = characterIterator.next())
        {
            if (!duplicates.add(character))
            {
                return false;
            }
        }

        return true;
    }

}
