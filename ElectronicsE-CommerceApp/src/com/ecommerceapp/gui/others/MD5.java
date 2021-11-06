/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.others;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Khoa Nguyen
 */
public class MD5 {
    public static String encrypt(String srcText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String enrText;
        MessageDigest msd = MessageDigest.getInstance("md5");
        byte[] srcTextBytes = srcText.getBytes("UTF-8");
        byte[] enrTextBytes = msd.digest(srcTextBytes);
        
        BigInteger bigInt = new BigInteger(1, enrTextBytes);
        enrText = bigInt.toString(16);
        return enrText;
    }
}
