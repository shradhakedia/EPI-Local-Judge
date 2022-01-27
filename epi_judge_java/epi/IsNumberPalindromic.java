package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    if(x < 0) {
      return false;
    }

    int numOfDigits = (int) Math.log10(x);
    int msdMask = (int) Math.pow(10, numOfDigits);
    while(x > 0) {
      if(x/msdMask == x % 10) {
        x %= msdMask;
        x /= 10;
        msdMask /= 100;
      } else {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
