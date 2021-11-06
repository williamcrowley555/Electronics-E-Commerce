/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.ThongKeTourDTO;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IThongKeTourBLL {
    List<ThongKeTourDTO> findAll();
}
