package generator.parts.method;

public class BaseOperator implements Operator {

    private final String symbol;
    private final boolean rightAssociative;
    private final int precedence;

    public BaseOperator(String symbol, boolean rightAssociative, int precedence) {
        this.symbol = symbol;
        this.rightAssociative = rightAssociative;
        this.precedence = precedence;
    }

    @Override
    public boolean isRightAssociative() {
        return rightAssociative;
    }

    @Override
    public int comparePrecedence(Operator o) {
        if (o instanceof BaseOperator) {
            BaseOperator other = (BaseOperator) o;
            return Integer.compare(precedence, other.precedence);
        } else {
            // Defer the comparison to the second operator reflectively
            return -o.comparePrecedence(this);
        }
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}