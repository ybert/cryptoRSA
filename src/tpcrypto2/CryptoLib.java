package tpcrypto2;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class CryptoLib {

    public static void main(String[] args) {
        /*  // Test de Exp
            BigInteger b = new BigInteger("5");
            BigInteger c = new BigInteger("3");
            BigInteger m = new BigInteger("13");
            BigInteger r = Exp(b, c, m);
            System.out.println(r.toString());
            */
        /* Test pgcd
            BigInteger m = new BigInteger("10");
            BigInteger n = new BigInteger("5");

            System.out.println(pgcd(m,n));
            */
        /* Test estPremierRapide
        BigInteger premier = new BigInteger("234846468351588648613");
        System.out.println(estPremierRapide(premier, 10));
        */
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
        BigInteger varI = new BigInteger("0");
        BigInteger vark = new BigInteger("0");
        BigInteger varM = n.subtract(BigInteger.ONE);
        
        
        while ((varM.mod(new BigInteger("2"))).compareTo(BigInteger.ZERO) == 0) {
            varM = varM.shiftRight(1);
            vark = vark.add(BigInteger.ONE);
        }
        
        BigInteger a = randomBigInteger(n);
        BigInteger b = a.modPow(varM, n);
        
        if ((b.mod(n)).compareTo(BigInteger.ZERO) == 0) {
            return true;
        } else {
            while (varI.compareTo(vark.subtract(BigInteger.ONE)) <= 0 ) {
                if (((b.subtract(BigInteger.ONE)).mod(n)).compareTo(BigInteger.ZERO) == 0) {
                    return true;
                } else {
                    b = b.modPow(new BigInteger("2"), n);
                }
                varI = varI.add(BigInteger.ONE);
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
        
    public static boolean estPremierRapide(BigInteger n, int k) {
        while ( k > 0 ) {
          if (estProbablementPremier( n) ) 
            return true;
          k--;
        }
        return false;
    }
    
    public static BigInteger EuclideEtendu (BigInteger a, BigInteger b)
    {
        BigInteger q;
        
        ArrayList<BigInteger> r = new ArrayList<>();
        ArrayList<BigInteger> s = new ArrayList<>();
        ArrayList<BigInteger> t = new ArrayList<>();
        
        r.add(a); r.add(b);
        s.add(new BigInteger("1")); s.add(new BigInteger("0"));
        t.add(new BigInteger("0")); t.add(new BigInteger("1"));

        q = r.get(0).divide(r.get(1)); 
        
        r.add(r.get(0).subtract(q.multiply(r.get(1))));
        
        int i = 2;
        while(r.get(i).compareTo(BigInteger.ZERO) > 0)
        {
            s.add(s.get(i-2).subtract(q.multiply(s.get(i-1))));
            t.add(t.get(i-2).subtract(q.multiply(t.get(i-1))));
            q = r.get(i-1).divide(r.get(i));
            
            i = i+1;
            
            r.add(r.get(i-2).subtract(q.multiply(r.get(i-1))));
        }
        
        BigInteger retour = (s.get(i-1).compareTo(BigInteger.ZERO) >= 0)?s.get(i-1):s.get(i-1).add(b);
        
        return retour;
    }
}
