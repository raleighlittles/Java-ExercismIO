import java.util.Arrays;

class ResistorColor {
    int colorCode(String color) {
        return Arrays.asList(colors()).indexOf(color);
    }

    /* See: https://en.wikipedia.org/wiki/Electronic_color_code
    *
    */
    String[] colors() {
        return new String[] {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};
    }
}
