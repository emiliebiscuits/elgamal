package crypto;


import java.math.BigInteger;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Emilie
 */
public class Elgamal {
    private BigInteger p, p_prime, g;
    private BigInteger g2_modp, g_p_prime_modp, g_2p_prime_modp;

    public BigInteger getP() {
        return p;
    }

    public BigInteger getP_prime() {
        return p_prime;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getG2_modp() {
        return g2_modp;
    }

    public BigInteger getG_2p_prime_modp() {
        return g_2p_prime_modp;
    }
    
    public BigInteger getG_p_prime_modp() {
        return g_p_prime_modp;
    }
    
   public Elgamal(int nb_bits, int certainty, Random prg)
   {
       getPrime(nb_bits, certainty,prg);
   }
   public Elgamal(BigInteger bi)
   {
       p = bi;
   }
    
    
    public void getPrime(int nb_bits, int certainty, Random prg)
    {     
        BigInteger tmp, res;
        do{
            tmp = new BigInteger(nb_bits-1, certainty, prg);
            res = tmp.multiply(new BigInteger("2")).add(new BigInteger("1"));
        }while(!res.isProbablePrime(100));
        p_prime = tmp;
        p = res;
    }
    
    public void entierAlea()
    {  
        do{
            g = new BigInteger(p.bitLength(), new Random());
        }while(g.compareTo(p) != -1 || g.compareTo(BigInteger.ZERO)==0 || g.compareTo(BigInteger.ONE)==0);
    }
    public void calculPuissance()
    {
        g2_modp = g.modPow(new BigInteger("2"), p);
        g_p_prime_modp = g.modPow(p_prime, p);
        g_2p_prime_modp= g.modPow(p_prime.multiply(new BigInteger("2")), p);
    }
    public BigInteger ordre(BigInteger b)
    {
        if(b.compareTo(p)!=-1)
        {
            return null;
        }
        else
        {
            for(BigInteger bi = BigInteger.valueOf(1);bi.compareTo(p) < 1; bi = bi.add(BigInteger.ONE))
            {
                if(b.modPow(bi, p).intValue()==1)
                {
                    return bi;
                }
            }
        }    
        return null;
    }
    public BigInteger ordre_p(BigInteger b)
    {
        if(p.isProbablePrime(100))
        {
            if(b.modPow(p_prime, p).intValue()==1)
            {
                return p_prime;
            }
            else if(b.modPow(p_prime.multiply(new BigInteger("2")), p).intValue()==1)
            {
                return p_prime.multiply(new BigInteger("2"));
            }
            else if(b.modPow(new BigInteger("2"), p).intValue()==1)
            {
                return new BigInteger("2");
            }    
        }
        return null;
    }
    
    BigInteger randNum(BigInteger N, Random prg)
    {
        int deuxK = N.bitLength();
        BigInteger res;
        do{
            res = new BigInteger(deuxK-1, prg);
        }while(res.compareTo(N)==1);
        return res;
    }
    
    BigInteger division(BigInteger haut, BigInteger bas)
    {
        BigInteger res;
        BigInteger tmp = haut;
        do{
            haut = tmp;
            res = haut.divide(bas);
            tmp = haut.add(p);
        }while (res.multiply(bas).compareTo(haut)!=0);
        return res;
    }
}
