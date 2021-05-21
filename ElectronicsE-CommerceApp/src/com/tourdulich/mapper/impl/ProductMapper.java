/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.DichVuDTO;
import com.tourdulich.dto.ProductDTO;
import com.tourdulich.mapper.RowMapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ProductMapper implements RowMapper<ProductDTO>{

    @Override
    public ProductDTO mapRow(ResultSet rs) {
        try {
            ProductDTO product = new ProductDTO();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            
            Blob blob = rs.getBlob("image");
            if(blob != null)  
            {
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
                product.setBase64Image(base64Image);
              // product.setImage(imageBytes); 
            } else product.setBase64Image(null);
            product.setImage(rs.getBytes("image"));
            product.setPrice(rs.getLong("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setBrandId(rs.getLong("brand_id"));
            return product;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
         
        } catch (IOException ex) {
            Logger.getLogger(ProductMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
