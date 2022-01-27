package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class  Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {

    short numSetBits = 0;

    // Approach 1: linear -> O(n) where n is word size, at max 64.
    /*
    while(x != 0) {
      numSetBits += (x & 1);
      x >>>= 1;
    }
    */

    // Approach 2: using Brian's Kernighan's algorithm -> O(k) where k is no. of set bits, at max 64.
    while(x != 0) {
      numSetBits++;
      x &= (x - 1);
    }

    return (short) (numSetBits & 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
