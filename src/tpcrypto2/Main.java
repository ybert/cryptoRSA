package tpcrypto2;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
            /*  // Test de Exp
		BigInteger b = new BigInteger("5");
                BigInteger c = new BigInteger("3");
                BigInteger m = new BigInteger("13");
                BigInteger r = Exp(b, c, m);
		System.out.println(r.toString());
                */
            
                BigInteger m = new BigInteger("10");
		BigInteger n = new BigInteger("5");
		
		System.out.println(pgcd(m,n));
	}
        
        public static BigInteger Exp(BigInteger b, BigInteger c, BigInteger n) {
            BigInteger r = new BigInteger("1");
            while(!c.equals(new BigInteger("0"))) {
                if(odd(c)) {
                    r = r.multiply(b).mod(n);
                    c = c.shiftRight(1);
                    b = b.multiply(b).mod(n);
                }
            }
            return r;
            
        }
        
        public static boolean odd(BigInteger val) {
           if(!val.mod(new BigInteger("2")).equals(BigInteger.ZERO))
               return true;
           return false;
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
