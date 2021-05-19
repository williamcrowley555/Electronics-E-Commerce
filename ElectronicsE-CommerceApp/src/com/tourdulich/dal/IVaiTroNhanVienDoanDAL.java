/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dto.VaiTroNhanVienDoanDTO;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IVaiTroNhanVienDoanDAL {
    List<VaiTroNhanVienDoanDTO> findAll();
    VaiTroNhanVienDoanDTO findById(Long id);
    List<Long> findByIdDsNhanVienDoan(Long idDsNhanVienDoan);
    Long save(VaiTroNhanVienDoanDTO vaiTroNhanVienDoan);  
    void deleteByIdDsNhanVienDoan(Long idDsNhanVienDoan); 
}
