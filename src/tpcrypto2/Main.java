package tpcrypto2;
import java.math.BigInteger;
import java.util.Random;

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
            BigInteger r = BigInteger.ONE;
            while(!c.equals(BigInteger.ZERO)) {
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
		while (! n.equals(BigInteger.ZERO) ) {
			BigInteger r = m.mod(n);
			m = n;
			n = r;
		}
		return m;
	}
        
        public static boolean estProbablementPremier(BigInteger n) {
            BigInteger m = n;
            m = m.subtract(BigInteger.ONE);
            int k = 0;
            while(m.testBit(0)) {
                m = m.shiftRight(1);
                k++;
            }
            BigInteger a = randomBigInteger(n);
            BigInteger b = Exp(a, m, n);
            if(b.mod(n).equals(BigInteger.ONE)) {
                return true;
            }
            else {
                for(int i=0;i<k-1;i++) {
                    if(b.mod(n).equals(new BigInteger("-1"))) {
                        return true;
                    }
                    b = b.multiply(b).mod(n);
                }
            }
            return false;
        }
        
        public static BigInteger randomBigInteger(BigInteger n) {
            Random rnd = new Random();
            int maxNumBitLength = n.bitLength();
            BigInteger aRandomBigInt;
            do {
                aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
                // itère tant que le randomNumber n'est pas entre 1 et n-1
            } while (aRandomBigInt.compareTo(n) > 0 && aRandomBigInt.compareTo(BigInteger.ZERO) < 0); // Nombre d'itérations max : 2
            return aRandomBigInt;
        }
}
