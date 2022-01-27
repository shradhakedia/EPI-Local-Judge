package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {

    // Approach 1: brute force, Time Complexity: O(1)
    /*
    long getBitI = (x >>> i) & 1;
    long getBitJ = (x >>> j) & 1;

    if(getBitI == 1) {
      x |= (1L << j);
    } else {
      x &= ~(1L << j);
    }

    if(getBitJ == 1) {
      x |= (1L << i);
    } else {
      x &= ~(1L << i);
    }

    return x;
    */

    // Approach 2: check if bits at i and j are different
    // and flip them. (if bits are same no need to swap)
    if(((x >>> i) & 1) != ((x >>> j) & 1)) {
      long bitMask = ((1L << i) | (1L << j));
      x ^= bitMask;
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
