package tpcrypto2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Decrypt {
    public static String dcryptMSG(String msgCrypt, BigInteger b, BigInteger n) {
        System.out.println("************************ Decrypt message ************************");
        String clair = "";
        String tbMSGCrypt[] = msgCrypt.split(" ");
        for (String strCrypt : tbMSGCrypt) {
            
            BigInteger crypt = new BigInteger(strCrypt);
            crypt = crypt.modPow(b, n);
            
            byte val[] = crypt.toByteArray();
            
            clair += new String(val);
            
        }
        return clair;
    }
    
}
