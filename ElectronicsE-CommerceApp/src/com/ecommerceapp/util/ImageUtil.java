/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.util;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author HP
 */
public class ImageUtil {
    
    public static File showJFileChooser(JLabel thumbnailLbl){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.addChoosableFileFilter(fileFilter);
        fileChooser.setMultiSelectionEnabled(false);
        
        int fileState = fileChooser.showSaveDialog(null);
        if(fileState == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            thumbnailLbl.setIcon(resizeImg(filePath, thumbnailLbl));
            return selectedFile;
        } else if(fileState == JFileChooser.CANCEL_OPTION) {
            System.out.println("No Image Selected!");
        }
        return null;
    }
    
    public static ImageIcon resizeImg(String imgPath, JLabel thumbnaiLbl) {
        ImageIcon imgIcon = new ImageIcon(imgPath);
        Image img = imgIcon.getImage().getScaledInstance(thumbnaiLbl.getWidth(), thumbnaiLbl.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
    
    public static ImageIcon resizeImg(byte[] imgage, JLabel thumbnaiLbl) {
        ImageIcon imgIcon = new ImageIcon(imgage);
        Image img = imgIcon.getImage().getScaledInstance(thumbnaiLbl.getWidth(), thumbnaiLbl.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
    
    public static byte[] getByteArray(File file) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }
        return bos.toByteArray();
    }
}
