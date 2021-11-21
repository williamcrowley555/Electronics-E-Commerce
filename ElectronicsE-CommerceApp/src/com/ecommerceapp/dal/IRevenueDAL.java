/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.RevenueDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public interface IRevenueDAL {
    List<RevenueDTO> findByMonthAndYear(int month, int year);
}
