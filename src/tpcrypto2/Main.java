package tpcrypto2;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    
    public static final String FILENAME_USER_A = "userAKey.priv";
    public static final String FILENAME_USER_B = "userBKey.priv";
    
    public static void main(String[] args) {
        
        int t;
        String str;
        String wordToSend;
        Scanner sc = new Scanner(System.in);
        System.out.println("bloc size (*8)");
        str = sc.nextLine();
        t = Integer.parseInt(str);
        
        Keygen.generateKeys(FILENAME_USER_A, t);
        Keygen.generateKeys(FILENAME_USER_B, t);
        
        System.out.println("****************************************** CREATE USERS ******************************************");
        User userA = createUser( "Yohann", FILENAME_USER_A);
        User userB = createUser( "Alex", FILENAME_USER_B);
        
        System.out.println("Type the word to send:");
        wordToSend = sc.nextLine();
        t = Integer.parseInt(str);
        
        String wordToSendSha1 = "";
        // Faire Sha1 du mot
        try {
            wordToSendSha1 = CryptoLib.encodeToSHA1(wordToSend);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("****************************************** SEND ******************************************");
        System.out.println("-------- Crypt the word");
        String wordToSendCrypte = Crypt.cryptMsg(wordToSend, t, userB.getPublicKey(), userB.getMod());
        // Crypter Sha1 avec cle privé de A
        System.out.println("-------- Cryptage du Sha1");
        String sha1crypte = Crypt.cryptMsg(wordToSendSha1, t, userA.getPrivateKey(), userA.getMod());
        

        System.out.println("****************************************** RECEIVE ******************************************");
        System.out.println("-------- Decrypt the word");
        String messageDecrypt = Decrypt.dcryptMSG(wordToSendCrypte, userB.getPrivateKey(), userB.getMod());
        System.out.println("-------- word received: "+messageDecrypt);
        // Crypter Sha1 avec cle public B
        System.out.println("-------- Decrypt Sha1");
        String sha1Decrypte = Decrypt.dcryptMSG(sha1crypte, userA.getPublicKey(), userA.getMod());
        

        /////////////////////////////////////////
        // Verification
        /////////////////////////////////////////
        System.out.println("****************************************** VERIFICATION ******************************************");
        if(CryptoLib.compareSHA1(messageDecrypt.trim(), sha1Decrypte))
        {
            System.out.println("-------- Decrypted! :)");
        }
        else
        {
            System.out.println("-------- Not decrypted! :(");
        }
        
        
    }

    public static User createUser(String userName, String fileName)
    {
        User user = new User(userName);
        
        String retour = "";
        try {
            retour = File.readFile(fileName);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        String var[] = retour.split(" ");
        
        user.setPrivateKey(new BigInteger(var[5]));
        user.setPublicKey(new BigInteger(var[4]));
        user.setMod(new BigInteger(var[1]));
        
        return user;
    }
}
