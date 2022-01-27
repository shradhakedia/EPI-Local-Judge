package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class ReverseBits {

  static int[] precomputedReverse = preComputeReverseBitsOf16bitWord();

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {

    // Approach 1: using Cache;
    // Time Complexity: O(n/L) where n is no. of bits in number and L is 16 here i.e. L-bit cache keys

    int wordSize = 16;
    int bitMask = 0xFFFF;
    return (long) precomputedReverse[(int) (x & bitMask)] << (3 * wordSize)
            | (long) precomputedReverse[(int) ((x >>> wordSize) & bitMask)] << (2 * wordSize)
            | (long) precomputedReverse[(int) ((x >>> (2 * wordSize)) & bitMask)] << wordSize
            | (long) precomputedReverse[(int) ((x >>> (3 * wordSize)) & bitMask)];

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

  private static int[] preComputeReverseBitsOf16bitWord() {
    int[] precomputedReverse = new int[65536];
    for(int num = 0; num < precomputedReverse.length; num++) {

      precomputedReverse[num] = num;
      for (int i = 0, j = 15; i < j; i++, j--) {
        if ((num >>> i & 1) != (num >>> j & 1)) {
          int bitmask = (1 << i) | (1 << j);
          precomputedReverse[num] ^= bitmask;
        }
      }
    }
    return precomputedReverse;
  }

}
