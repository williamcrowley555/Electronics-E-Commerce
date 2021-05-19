/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll;

import com.tourdulich.dto.ChiPhiDoanDTO;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IChiPhiDoanBLL {
    List<ChiPhiDoanDTO> findAll();
    ChiPhiDoanDTO findById(Long id);
    List<ChiPhiDoanDTO> findByIdDoan(Long idDoan);
    Long save(ChiPhiDoanDTO chiPhiDoan);
    void update(ChiPhiDoanDTO chiPhiDoan);
    void delete(Long id);    
}
