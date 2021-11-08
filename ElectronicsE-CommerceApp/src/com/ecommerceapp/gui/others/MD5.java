/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.gui.others;

import com.ecommerceapp.bll.IUserBLL;
import com.ecommerceapp.bll.impl.UserBLL;
import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.util.BCrypt;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

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
    
    public static boolean isMatch(String email, byte[] password) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {   
        String encryptedPass = encrypt(password.toString());
        System.out.println(encryptedPass);
        UserBLL userBLL = new UserBLL();
        UserDTO user = userBLL.findByEmail(email);
        if (encryptedPass.equals(user.getPassword())) return true;
        return false;
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{
//    String hash = "6ac932c6e015403770d1ce533f2b3cab";
//    byte[] password = "abcdef".getBytes();
//    byte[]  test = "abcdef".getBytes();
//        System.out.println(encrypt(test.toString()));
//   
     
    
   
    
    }
}
