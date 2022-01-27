package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    long sum = 0;
    int shift = 0;
    while(x != 0) {
      if((x & 1) != 0) {
        sum = add(sum, y << shift);
      }
      shift++;
      x >>>= 1;
    }
    return sum;
  }

  private static long add(long a, long b) {

    int carryin = 0;
    long sum = 0;
    for(int i = 0; i < 63; i++) {
      int bit1 = (int) ((a >>> i) & 1);
      int bit2 = (int) ((b >>> i) & 1);
      if(bit1 != bit2) {
        if(carryin == 0) {
          sum |= (1L << i);
        }
      } else {
        if(carryin == 1) {
          sum |= (1L << i);
          if(bit1 == 0) {
            carryin = 0;
          }
        } else {
          if(bit1 == 1) {
            carryin = 1;
          }
        }
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
