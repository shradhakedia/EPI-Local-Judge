package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {

    int factor = 1;
    if(x < 0) {
      x = -x;
      factor = -1;
    }

    long revNum = 0;
    while (x > 0) {
      int mod = x % 10;
      revNum = (revNum * 10) + mod;
      x /= 10;
    }
    return revNum * factor;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
