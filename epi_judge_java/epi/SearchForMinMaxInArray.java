package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchForMinMaxInArray {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class MinMax {
    public Integer smallest;
    public Integer largest;

    public MinMax(Integer smallest, Integer largest) {
      this.smallest = smallest;
      this.largest = largest;
    }

    private static MinMax minMax(Integer a, Integer b) {
      return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      MinMax minMax = (MinMax)o;

      if (!smallest.equals(minMax.smallest)) {
        return false;
      }
      return largest.equals(minMax.largest);
    }

    @Override
    public String toString() {
      return "min: " + smallest + ", max: " + largest;
    }
  }

  @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")

  public static MinMax findMinMax(List<Integer> A) {

    // return approachTwo(A);
    return approachOne(A);
  }

  private static MinMax approachTwo(List<Integer> A) {
    if (A.size() == 1) {
      return new MinMax(A.get(0), A.get(0));
    }

    MinMax minMax = MinMax.minMax(A.get(0), A.get(1));
    for(int i = 2; (i + 1) < A.size(); i++) {
      MinMax localMinMax = MinMax.minMax(A.get(i), A.get(i + 1));
      minMax.smallest = MinMax.minMax(localMinMax.smallest, minMax.smallest).smallest;
      minMax.largest = MinMax.minMax(localMinMax.largest, minMax.largest).largest;
    }

    if((A.size() & 1) == 1) {
      minMax.smallest = MinMax.minMax(minMax.smallest, A.get(A.size() - 1)).smallest;
      minMax.largest = MinMax.minMax(minMax.largest, A.get(A.size() - 1)).largest;
    }

    return minMax;
  }
  
  private static MinMax approachOne(List<Integer> A) {
    if (A.size() == 1) {
      return new MinMax(A.get(0), A.get(0));
    }

    int min = Math.min(A.get(0), A.get(1));
    int max = Math.max(A.get(0), A.get(1));
    for(int i = 2; i + 1 < A.size(); i += 2) {
      if(A.get(i) < A.get(i + 1)) {
        min = Math.min(min, A.get(i));
        max = Math.max(max, A.get(i + 1));
      } else {
        min = Math.min(min, A.get(i + 1));
        max = Math.max(max, A.get(i));
      }
    }

    if((A.size() & 1) == 1) {
      min = Math.min(min, A.get(A.size() - 1));
      max = Math.max(max, A.get(A.size() - 1));
    }

    return new MinMax(min, max);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMinMaxInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
