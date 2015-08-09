/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.Arrays;
/**
 *
 * @author KennyW
 */
public class RSA {
    private static int p = 61;
    private static int q = 53;
    private static int n = p*q;
    private static int phi = (p-1)*(q-1);
    private static int e = 17;
    private static int d = modInv(e,phi);
    
    private static int modExp(int base, int exponent, int modulus) {
        int c = 1;
        for (int i = 1; i <= exponent; i++) {
            c = (c*base)%modulus;
        }
        return c;
    }
    
    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a%b;
            a = t;
        }
        return a;
    }
    
    private static int modInv(int a, int b) {
	int b0 = b;
	int t;
	int q;
	int x0 = 0;
	int x1 = 1;
	if (b == 1) return 1;
	while (a > 1) {
            q = a/b;
            t = b;
            b = a%b;
            a = t;
            t = x0;
            x0 = x1 - q*x0;
            x1 = t;
	}
	if (x1 < 0) x1 = x1 + b0;
	return x1;
    }
    
    private static int[] StringToIntArray(String s) {
        int[] r = new int[s.length()];
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            r[i] = (int)(c[i]);
        }
        return r;
    }
    
    private static String IntArrayToString(int[] s) {
        String r = "";
        for (int i = 0; i < s.length; i++) {
            r = r + String.valueOf((char)(s[i]));
        }
        return r;
    }
    
    private static int[] modExp(int[] base, int exponent, int modulus) {
        int[] r = new int[base.length];
        for (int i = 0; i < base.length; i++) {
            r[i] = modExp(base[i],exponent,modulus);
        }
        return r;
    }
    
    private static int[] encrypt(int[] plaintext) {
        return modExp(plaintext,e,n);
    }
    
    private static int[] decrypt(int[] ciphertext) {
        return modExp(ciphertext,d,n);
    }
    
    public static String encrypt(String s) {
        return IntArrayToString(encrypt(StringToIntArray(s)));
    }
    
    public static String decrypt(String s) {
        return IntArrayToString(decrypt(StringToIntArray(s)));
    }
    
    public static void main(String[] args) {
       String s1 = "How are you";
       int[] plaintext = StringToIntArray(s1);
       int[] ciphertext = encrypt(plaintext);
       int[] decipheredtext = decrypt(ciphertext);
       System.out.println(Arrays.toString(plaintext));
       System.out.println(Arrays.toString(decipheredtext));
       
    }
}
