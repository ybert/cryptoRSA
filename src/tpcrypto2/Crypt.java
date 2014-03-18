package tpcrypto2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Crypt {
    public static String cryptMsg(String msg, int t, BigInteger key, BigInteger n) {
        System.out.println("**************************** CRYPTAGE MESSAGE ****************************");
        int cpt = 0;
        byte[] bytes = msg.getBytes();
        byte[] temp = new byte[t/8];
        String messCrypte ="";
        int i = 0;
        
        while (i < bytes.length) {
            if (cpt == t/8) {
                BigInteger bloc = new BigInteger(temp);
                messCrypte += bloc.modPow(key, n)+" ";
                cpt = 0;
                temp = new byte[t/8];
            } else {
                temp[cpt] = bytes[i];
                cpt++;
                i++;
            }
        }
        
        if (cpt != 0) {
            BigInteger mess = new BigInteger(temp);
            messCrypte += mess.modPow(key, n);
        }
        
        System.out.println("-------- Message crypté: " + messCrypte);
        
        return messCrypte;
    }

}
