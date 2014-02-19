package tpcrypto2;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		BigInteger m = new BigInteger("10");
		BigInteger n = new BigInteger("5");
		
		System.out.println(pgcd(m,n));
	}

	
	public static BigInteger pgcd(BigInteger m, BigInteger n) {
		while (! n.equals(new BigInteger("0")) ) {
			BigInteger r = m.mod(n);
			m = n;
			n = r;
		}
		return m;
	}
	
	
	
}
