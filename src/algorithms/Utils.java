package algorithms;

public class Utils {
	// The number which is divisible by itself or by one
	// 1, 3, 5, 7, 11, 13, 17, 19.. so on
	public static boolean isPrime(int n) {
	    for (int i = 2; (2 * i <= n); i++) {
	        if (n % i == 0) 
	            return false;
	    }
	    return true;
	}
}
