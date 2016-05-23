/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Emilie
 */
public class Crypto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Elgamal e = new Elgamal(20,100,new Random());
        System.out.println("p = "+e.getP());
        System.out.println("p'= "+e.getP_prime());
        do{
            e.entierAlea();
        }while(e.ordre(e.getG()).compareTo(e.getP_prime())!=0);
        System.out.println("g = "+ e.getG());
        //e.calculPuissance();
        //System.out.println("g2_modp = "+ e.getG2_modp());
        //System.out.println("g_p_prime_modp = "+ e.getG_p_prime_modp());
        //System.out.println("g_2p_prime_modp = "+ e.getG_2p_prime_modp());
        /*for(int i=1; e.getP().compareTo(new BigInteger(Integer.toString(i)))==1; i++)
        {
            System.out.println("ordre de "+i+" : " + e.ordre_p(new BigInteger(Integer.toString(i))));
        }*/
        /*System.out.println("---------------");
        Elgamal e2 = new Elgamal(new BigInteger("21"));
        for(int i=1; e2.getP().compareTo(new BigInteger(Integer.toString(i)))==1; i++)
        {
            System.out.println("ordre de "+i+" : " + e2.ordre(new BigInteger(Integer.toString(i))));
        }*/
        
        System.out.println("ordre de g = "+ e.ordre_p(e.getG()));
        BigInteger x = e.randNum(e.getP_prime(), new Random());
        System.out.println("x = "+ x);
        BigInteger h = e.getG().modPow(x, e.getP());
        System.out.println("h = "+ h);
        System.out.println("cle secrete : ("+ e.getP() +", "+x+")");
        System.out.println("cle publique : ("+e.getP()+", "+e.getG()+", "+h+")");
        BigInteger m = new BigInteger("3");
        BigInteger r = e.randNum(e.getP_prime(), new Random());
        BigInteger haut = h.modPow(r,e.getP()).multiply(m).mod(e.getP());
        BigInteger gr = e.getG().modPow(r, e.getP());
        BigInteger bas =  gr.modPow(x, e.getP());
        System.out.println("chiffré : ("+gr+", "+haut+")");
        BigInteger res = e.division(haut, bas);
        System.out.println("déchiffré : "+res);
    }
