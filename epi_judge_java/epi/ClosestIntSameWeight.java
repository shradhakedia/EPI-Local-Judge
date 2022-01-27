package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {

    // Approach 1: Bit Manipulation;  find two different consecutive bits from lsb and swap them. eg 1101 to 1110
    // Time Complexity : O(n) where n is width of the word or bits in the word.
    /*
    int lsb = (int) (x & 1);
    for(int i = 1; i < 63; i++) {
      if(((x >>> i) & 1) != lsb) {
        int bitMask = (1 << i) | (1 << (i - 1));
        return x ^ bitMask;
      }
    }
    throw new IllegalArgumentException("All bits are 0 or 1");
    */

    // Approach 2: Bit Manipulation; Time Complexity: O(1)
    long setBit = findLsbSetBit(x);
    long unSetBit = findLsbUnsetBit(x);
    if(unSetBit > setBit) {
      x |= unSetBit;
      x ^= (unSetBit >>> 1);
    } else {
      x ^= setBit;
      x |= (setBit >> 1);
    }
    return x;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

  public static long findLsbSetBit(long x) {
    return x & -x;
  }

  public static long findLsbUnsetBit(long x) {
    return ~(x | ~(x + 1));
  }
}
