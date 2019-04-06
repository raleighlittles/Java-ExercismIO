
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Yacht {
    List<Integer> dice;
    YachtCategory category;
    Map<Integer, Long> frequencies;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        /* Ugly way of converting an int[] array to a nice Integer list, so that we
         * can use the handy Java8 "Streams" functionality later on.
         */
        this.dice = Arrays.stream(dice).boxed().collect(Collectors.toList());
        this.category = yachtCategory;
        this.frequencies = this.dice.stream().collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));
    }

    int score() {
        /* For categories: Ones, Twos, Threes, Fours, Fives, and Sixes, the
        score is just the number of dices (?) in that array of dice whose value is
        equivalent to the category.
         */

        if (this.category.ordinal() > YachtCategory.YACHT.ordinal() && (category.ordinal() < YachtCategory.FULL_HOUSE.ordinal())) {
            return this.dice.stream().mapToInt(Integer::intValue).filter(i -> (i == this.category.ordinal())).sum();
        }


        switch (this.category) {

            case FULL_HOUSE:
                /* Get a Map of the results. The hash map should..
                 0) Have only two entries.
                 1) Have a minimum value count of 2
                 2) Have a maximum value count of 3 */
                if ((frequencies.values().size() == 2) &&
                        (Collections.max(frequencies.values()) == 3) &&
                        (Collections.min(frequencies.values()) == 2)) {
                    return this.dice.stream().mapToInt(Integer::intValue).sum();
                }

                break;


            case FOUR_OF_A_KIND:
                /* Make sure there are only two distinct values in the dice,
                 * and that the same value shows up exactly 4 times.
                 * Then, take the element that shows up 4 times, multiply it by 4,
                 *  and return that.
                 */

                if ((this.dice.stream().distinct().count() <= 2) && (Collections.max(frequencies.values()) >= 4)) {
                    // Very, very, very, very ugly way of getting the key with the smallest value
                    Integer fouredValue = frequencies.entrySet().stream().max((a, b) ->
                            a.getValue().compareTo(b.getValue())).get().getKey();

                    return fouredValue * 4;

                }
                break;


            case LITTLE_STRAIGHT:
                /* Must be reorderable to (1,2,3,4,5). The easiest way to detect this is
                 * to check that the list has 5 distinct elements, and that the max is 5
                 * and the minimum is 1.
                 */

                if ((this.dice.stream().distinct().count() == this.dice.size()) &&
                        (Collections.max(this.dice) == 5) &&
                        Collections.min(this.dice) == 1) {
                    return 30;
                }

                break;


            case BIG_STRAIGHT:
                /* Must be reorderable to (2,3,4,5,6).
                 * Check that there are 5 distinct elements in the list, and that the max
                 * is 6 and the minimum is 5.
                 */
                if ((this.dice.stream().distinct().count() == this.dice.size()) &&
                        (Collections.max(this.dice) == 6) &&
                        (Collections.min(this.dice) == 2)) {
                    return 30;
                }

                break;


            case CHOICE:
                /*
                 * Easiest case: take the sum of the entire array.
                 */
                return this.dice.stream().mapToInt(Integer::intValue).sum();


            case YACHT:
                /*
                 * If all five dice are the same, return 50.
                 */

                if (this.dice.stream().distinct().count() == 1L) {
                    return 50;
                }


            default:
                break;


        }

        return 0;


    }

}
