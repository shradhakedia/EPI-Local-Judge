package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchForMissingElement {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class DuplicateAndMissing {
    public Integer duplicate;
    public Integer missing;

    public DuplicateAndMissing(Integer duplicate, Integer missing) {
      this.duplicate = duplicate;
      this.missing = missing;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      DuplicateAndMissing that = (DuplicateAndMissing)o;

      if (!duplicate.equals(that.duplicate)) {
        return false;
      }
      return missing.equals(that.missing);
    }

    @Override
    public int hashCode() {
      int result = duplicate.hashCode();
      result = 31 * result + missing.hashCode();
      return result;
    }

    @Override
    public String toString() {
      return "duplicate: " + duplicate + ", missing: " + missing;
    }
  }

  @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")

  public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {

    int missXorDup = 0;
    for (int i = 0; i < A.size(); i++) {
      missXorDup ^= i ^ A.get(i); // get xor of missing and duplicate elements
    }

    int missOrDup = 0; // one of the values
    int mask = missXorDup & (- missXorDup); // get lowest set bit

    for(int i = 0; i < A.size(); i++) {
      if((i & mask) == mask) {
        missOrDup ^= i;
      }
      if((A.get(i) & mask) == mask) {
        missOrDup ^= A.get(i);
      }
    }

    for (Integer integer : A) {
      if(integer.equals(missOrDup)) { // means we have got duplicate num in missOrDup
        return new DuplicateAndMissing(missOrDup, missOrDup ^ missXorDup);
      }
    }

    return new DuplicateAndMissing(missOrDup ^ missXorDup, missOrDup);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMissingElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
