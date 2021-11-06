/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IBrandBLL;
import com.ecommerceapp.dal.IBrandDAL;
import java.util.List;
import com.ecommerceapp.dal.impl.BrandDAL;
import com.ecommerceapp.dto.BrandDTO;

/**
 *
 * @author HP
 */
public class BrandBLL implements IBrandBLL {

    private IBrandDAL brandDAL;

    public BrandBLL() {
        this.brandDAL = new BrandDAL();
    }
    
    @Override
    public List<BrandDTO> findAll() {
        return brandDAL.findAll();
    }

    @Override
    public BrandDTO findById(Long id) {
        return brandDAL.findById(id);
    }

    @Override
    public Long save(BrandDTO brand) {
        return brandDAL.save(brand);
    }

    @Override
    public void update(BrandDTO brand) {
        brandDAL.update(brand);
    }

    @Override
    public void delete(Long id) {
        brandDAL.delete(id);
    }
}
