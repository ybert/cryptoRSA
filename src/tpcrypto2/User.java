package tpcrypto2;

import java.math.BigInteger;

public class User {
    private String name;
    private BigInteger publicKey;
    private BigInteger privateKey;
    private BigInteger mod;
    
    public User(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    public BigInteger getMod() {
        return mod;
    }

    public void setMod(BigInteger mod) {
        this.mod = mod;
    }
    
    
}
