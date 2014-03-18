package tpcrypto2;

import java.math.BigInteger;
import java.util.Scanner;


public class Keygen {
    public static void generateKeys(String filename) throws NumberFormatException {
        int t;
        String str;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bloc size (*8)");
        str = sc.nextLine();
        t = Integer.parseInt(str);
        
        generateKeys(filename, t);
    }

    public static void generateKeys(String filename, int tailleBloc) throws NumberFormatException {
        BigInteger phi;
        BigInteger p, q, n, a, b;
        BigInteger min, max;
        int t = tailleBloc;
        
        System.out.println("************************ Key Generation ************************");
        min = new BigInteger("1");
        max = new BigInteger("1");
        for (int i = 0; i < (t / 2); i++) {
            min = min.multiply(new BigInteger("2"));
            max = max.multiply(new BigInteger("2"));
        }
        for (int i = 0; i < 16; i++) {
            max = max.multiply(new BigInteger("2"));
        }

        do {
            p = CryptoLib.randomBigInteger(min, max);
        } while (!CryptoLib.estPremierRapide(p, 4));
        do {
            q = CryptoLib.randomBigInteger(min, max);
        } while (!CryptoLib.estPremierRapide(q, 4));
        System.out.println("p :" + p);
        System.out.println("q :" + q);

        n = p.multiply(q);
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("n: " + n);
        System.out.println("phi: " + phi);

        do {
            a = CryptoLib.randomBigInteger(min, max);
        } while ((CryptoLib.pgcd(a, phi)).compareTo(BigInteger.ONE) != 0);
        System.out.println("Public key a: " + a);
        b = CryptoLib.EuclideEtendu(a, phi);

        System.out.println("Private key b: " + b);
        String outputStr = t+" "+n.toString()+" "+p.toString()+" "+q.toString()+" "+a.toString()+" "+b.toString();
        File.createFile(filename,outputStr);
    }
 
}
