package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RealSquareRoot {
  @EpiTest(testDataFile = "real_square_root.tsv")

  public static double squareRoot(double x) {
    // decide the search range according to x's value relative to 1.0 .
    double left, right;
    if(x < 1.0) {
      left = x;
      right = 1.0;
    } else { // x >= 1.0
      left = 1.0;
      right = x;
    }

    // keeps searching as long as left < right, within tolerance.
    while (compare(left, right) == Ordering.SMALLER) {
      double mid = left + (0.5 * (right - left));
      double sq = mid * mid;
      if(compare(sq, x) == Ordering.SMALLER) {
        left = mid;
      } else if(compare(sq, x) == Ordering.EQUAL) {
        return mid;
      } else {
        right = mid;
      }
    }
    return left;
  }

  private static enum Ordering {
    LARGER,
    SMALLER,
    EQUAL
  }

  private static Ordering compare(double a, double b) {
    double EPSILON = 0.000000000000001;
    double diff = (a - b)/b;
    return (diff < -EPSILON)? Ordering.SMALLER : (diff > EPSILON)? Ordering.LARGER : Ordering.EQUAL;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
