package battleship;

public enum BoardSymbol {

    FOG_OF_WAR('~'),
    SHIP_CELL('O'),
    HIT_CELL('X'),
    MISS_CELL('M');

    private final char symbol;

    BoardSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
