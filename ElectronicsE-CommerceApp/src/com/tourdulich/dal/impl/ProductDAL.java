 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.DichVuDTO;
import com.tourdulich.mapper.impl.DichVuMapper;
import java.util.List;
import com.tourdulich.dal.IProductDAL;
import com.tourdulich.dto.ProductDTO;
import com.tourdulich.mapper.impl.ProductMapper;

/**
 *
 * @author HP
 */
public class ProductDAL extends AbstractDAL<ProductDTO> implements IProductDAL {

    @Override
    public List<ProductDTO> findAll() {
        String sql = "SELECT * FROM product";
        return query(sql, new ProductMapper());
    }

    @Override
    public ProductDTO findById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<ProductDTO> product = query(sql, new ProductMapper(), id);
        return product.isEmpty() ? null : product.get(0);
    }

    @Override
    public Long save(ProductDTO product) {
        String sql = "INSERT INTO product(description, image, name, price, status, brand_id, quantity, catalog_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, product.getDescription(), product.getBase64Image(), product.getName(), product.getPrice(), product.isStatus(), product.getBrandId(), product.getQuantity(), product.getCatalogId());
    }

    @Override
    public void update(ProductDTO product) {
        String sql = "UPDATE product SET description = ? , image = ?, price = ?, status = ?, brand_id = ?, quantity = 0, catalog_id = ? WHERE id = ?";
        update(sql, product.getDescription(), product.getBase64Image(), product.getPrice(), product.isStatus(), product.getBrandId(), product.getCatalogId(), product.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        update(sql, id);
    }
}
