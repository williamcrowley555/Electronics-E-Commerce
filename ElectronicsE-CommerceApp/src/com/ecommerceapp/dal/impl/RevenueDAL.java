/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dal.IRevenueDAL;
import com.ecommerceapp.dto.RevenueDTO;
import com.ecommerceapp.mapper.impl.RevenueMapper;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public class RevenueDAL extends AbstractDAL<RevenueDTO> implements IRevenueDAL {
    @Override
    public List<RevenueDTO> findByMonthAndYear(int month, int year) {
       String sql = "CALL usp_invoice_monthlyReport(?, ?)";
       return query(sql, new RevenueMapper(), month, year);
    }
}
