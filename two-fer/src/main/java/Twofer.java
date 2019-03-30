class Twofer {
    String twofer(String name) {
        // Use the specific concatenation method instead of the addition operator to avoid type ambiguity.
        return ("One for ".concat((name == null) ? "you" : name)  + "," + " one for me.");
    }
}
