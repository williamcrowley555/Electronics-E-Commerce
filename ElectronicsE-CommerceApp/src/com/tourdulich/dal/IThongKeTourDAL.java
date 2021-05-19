/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dto.ThongKeTourDTO;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IThongKeTourDAL extends GenericDAL<ThongKeTourDTO>{
    List<ThongKeTourDTO> findAll();
}
