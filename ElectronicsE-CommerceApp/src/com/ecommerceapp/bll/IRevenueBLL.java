/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.RevenueDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public interface IRevenueBLL {
    List<RevenueDTO> findByMonthAndYear(int month, int year);
}
