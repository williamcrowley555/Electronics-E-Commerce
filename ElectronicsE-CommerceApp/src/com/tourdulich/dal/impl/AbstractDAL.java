/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.mapper.RowMapper;
import com.tourdulich.util.DBConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.tourdulich.dal.GenericDAL;

/**
 *
 * @author HP
 */
public class AbstractDAL<T> implements GenericDAL<T>{
    
    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    private void setParameters(PreparedStatement statement, Object... parameters) {
        try {
            int index;
            for(int i = 0; i < parameters.length; ++i) {
                index = i + 1; 
                Object parameter = parameters[i];
                if(parameter instanceof Long) {
                        statement.setLong(index, (Long) parameter);
                } else if(parameter instanceof String) {
                        statement.setString(index, (String) parameter);
                } else if(parameter instanceof Integer) {
                        statement.setInt(index, (Integer) parameter);
                }else if(parameter instanceof Boolean) {
                        statement.setBoolean(index, (Boolean) parameter);
                } else if(parameter instanceof Date) {
                        statement.setDate(index, (Date) parameter);
                } else if(parameter instanceof LocalDate) {
                        statement.setObject(index, (LocalDate) parameter);
                } else if(parameter instanceof byte[]) {
                        statement.setBytes(index, (byte[]) parameter);
                } else if(parameter == null) {
                        statement.setNull(index, Types.NULL);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        try {
            connection = DBConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            setParameters(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {                
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (connection != null) {
                connection.close();
                }
            
                if (statement != null) {
                    statement.close();
                }
            
                if (resultSet != null) {
                    resultSet.close();
                }     
            } catch (SQLException e) {
                return null;
            }
        }
    }

    @Override
    public Long insert(String sql, Object... parameters) {
        try {
            Long id = null;
            connection = DBConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            setParameters(statement, parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
                e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                connection.close();
                }
            
                if (statement != null) {
                    statement.close();
                }
            
                if (resultSet != null) {
                    resultSet.close();
                } 
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public void update(String sql, Object... parameters) {
        try {
            connection = DBConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameters(statement, parameters);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                connection.close();
                }
            
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
