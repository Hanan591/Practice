public class Rational implements Comparable<Rational> {
    private int numerator;
    private int denominator;

    //-----------------------------------------------------------------
    // Constructor: Sets up the rational number by ensuring a nonzero
    // denominator and making only the numerator signed.
    //-----------------------------------------------------------------
    public Rational(int numer, int denom) {
        if (denom == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }

        // Make the numerator "store" the sign
        if (denom < 0) {
            numer = numer * -1;
            denom = denom * -1;
        }

        int common = gcd(Math.abs(numer), denom);
        numerator = numer / common;
        denominator = denom / common;
    }
    //-----------------------------------------------------------------
    // Returns the numerator of this rational number.
    //-----------------------------------------------------------------
    public int getNumerator() {
        return numerator;
    }

    //-----------------------------------------------------------------
    // Returns the denominator of this rational number.
    //-----------------------------------------------------------------
    public int getDenominator() {
        return denominator;
    }

    //-----------------------------------------------------------------
    // Returns the reciprocal of this rational number.
    //-----------------------------------------------------------------
    public Rational reciprocal() {
        return new Rational(denominator, numerator);
    }

    //-----------------------------------------------------------------
    // Adds this rational number to the one passed as a parameter.
    // A common denominator is found by multiplying the individual
    // denominators.
    //-----------------------------------------------------------------
    public Rational add(Rational op2) {
        int commonDenominator = denominator * op2.getDenominator();
        int numerator1 = numerator * op2.getDenominator();
        int numerator2 = op2.getNumerator() * denominator;
        int sum = numerator1 + numerator2;

        return new Rational(sum, commonDenominator);
    }

    //-----------------------------------------------------------------
    // Subtracts the rational number passed as a parameter from this
    // rational number.
    //-----------------------------------------------------------------
    public Rational subtract(Rational op2) {
        int commonDenominator = denominator * op2.getDenominator();
        int numerator1 = numerator * op2.getDenominator();
        int numerator2 = op2.getNumerator() * denominator;
        int difference = numerator1 - numerator2;

        return new Rational(difference, commonDenominator);
    }

    //-----------------------------------------------------------------
    // Multiplies this rational number by the one passed as a
    // parameter.
    //-----------------------------------------------------------------
    public Rational multiply(Rational op2) {
        int numer = numerator * op2.getNumerator();
        int denom = denominator * op2.getDenominator();

        return new Rational(numer, denom);
    }

    //-----------------------------------------------------------------
    // Divides this rational number by the one passed as a parameter
    // by multiplying by the reciprocal of the second rational.
    //-----------------------------------------------------------------
    public Rational divide(Rational op2) {
        return multiply(op2.reciprocal());
    }

    //-----------------------------------------------------------------
    // Determines if this rational number is equal to the one passed
    // as a parameter. Assumes they are both reduced.
    //-----------------------------------------------------------------
    public boolean isLike(Rational op2) {
        return (numerator == op2.numerator && denominator == op2.denominator);
    }
 
    //-----------------------------------------------------------------
    // Returns this rational number as a string.
    //-----------------------------------------------------------------
    public String toString() {
        String result;

        if (numerator == 0) {
            result = "0";
        } else if (denominator == 1) {
            result = numerator + "";
        } else {
            result = numerator + "/" + denominator;
        }

        return result;
    }

    //-----------------------------------------------------------------
    // Reduces this rational number by dividing both the numerator
    // and the denominator by their greatest common divisor.
    //-----------------------------------------------------------------
    private void reduce() {
        if (numerator != 0) {
            int common = gcd(Math.abs(numerator), denominator);

            numerator = numerator / common;
            denominator = denominator / common;
        }
    }

    //-----------------------------------------------------------------
    // Computes and returns the greatest common divisor of the two
    // positive parameters. Uses Euclid's algorithm.
    //-----------------------------------------------------------------
    private int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return gcd(num2, num1 % num2);
    }
    //-----------------------------------------------------------------
    // Implements the compareTo method for the Comparable interface.
    //-----------------------------------------------------------------
    @Override
    public int compareTo(Rational other) {
        long thisValue = (long) this.numerator * other.denominator;
        long otherValue = (long) other.numerator * this.denominator;

        if (thisValue > otherValue) {
            return 1;
        } else if (thisValue < otherValue) {
            return -1;
        } else {
            return 0;
        }
    }
}