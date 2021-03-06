 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import java.util.List;
import com.ecommerceapp.dal.IProductDAL;
import com.ecommerceapp.dto.ProductDTO;
import com.ecommerceapp.mapper.impl.ProductMapper;

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
    public List<ProductDTO> findBySupplierId(Long supplierId) {
        String sql = "SELECT * FROM product WHERE supplier_id = ?";
        return query(sql, new ProductMapper(), supplierId);
    }

    @Override
    public ProductDTO findById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<ProductDTO> product = query(sql, new ProductMapper(), id);
        return product.isEmpty() ? null : product.get(0);
    }

    @Override
    public Long save(ProductDTO product) {
        String sql = "INSERT INTO product(description, image, name, price, status, brand_id, quantity, catalog_id, supplier_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, product.getDescription(), product.getBase64Image(), product.getName(), product.getPrice(), product.isStatus(), product.getBrandId(), product.getQuantity(), product.getCatalogId(), product.getSupplierId());
    }

    @Override
    public void update(ProductDTO product) {
        String sql = "UPDATE product SET name = ?, description = ? , image = ?, price = ?, status = ?, brand_id = ?, quantity = ?, catalog_id = ?, supplier_id = ? WHERE id = ?";
        update(sql, product.getName(), product.getDescription(), product.getBase64Image(), product.getPrice(), product.isStatus(), product.getBrandId(), product.getQuantity(), product.getCatalogId(), product.getSupplierId(), product.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        update(sql, id);
    }
}
