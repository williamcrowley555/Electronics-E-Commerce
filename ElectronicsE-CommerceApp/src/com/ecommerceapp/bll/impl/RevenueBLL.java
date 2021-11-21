/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IRevenueBLL;
import com.ecommerceapp.dal.IRevenueDAL;
import com.ecommerceapp.dal.impl.RevenueDAL;
import com.ecommerceapp.dto.RevenueDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public class RevenueBLL implements IRevenueBLL{
    private IRevenueDAL revenueDAL;

    public RevenueBLL() {
        this.revenueDAL = new RevenueDAL();
    }

    @Override
    public List<RevenueDTO> findByMonthAndYear(int month, int year) {
        return revenueDAL.findByMonthAndYear(month, year);
    }
}
