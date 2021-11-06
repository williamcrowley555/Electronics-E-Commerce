/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dto.VaiTroDTO;
import com.ecommerceapp.mapper.impl.VaiTroMapper;
import java.util.List;
import com.ecommerceapp.dal.IVaiTroDAL;

/**
 *
 * @author HP
 */
public class VaiTroDAL extends AbstractDAL<VaiTroDTO> implements IVaiTroDAL {

    @Override
    public List<VaiTroDTO> findAll() {
        String sql = "SELECT * FROM vai_tro";
        return query(sql, new VaiTroMapper());
    }

    @Override
    public VaiTroDTO findById(Long id) {
        String sql = "SELECT * FROM vai_tro WHERE id = ?";
        List<VaiTroDTO> vaiTro = query(sql, new VaiTroMapper(), id);
        return vaiTro.isEmpty() ? null : vaiTro.get(0);
    }

    @Override
    public Long save(VaiTroDTO vaiTro) {
        String sql = "INSERT INTO vai_tro(ten_vai_tro) VALUES(?)";
        return insert(sql, vaiTro.getTenVaiTro());
    }

    @Override
    public void update(VaiTroDTO vaiTro) {
        String sql = "UPDATE vai_tro SET ten_vai_tro = ? WHERE id = ?";
        update(sql, vaiTro.getTenVaiTro(), vaiTro.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM vai_tro WHERE id = ?";
        update(sql, id);
    }
}
