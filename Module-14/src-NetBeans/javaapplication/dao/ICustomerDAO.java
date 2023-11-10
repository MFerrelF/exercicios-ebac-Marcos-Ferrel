/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package javaapplication.dao;

import javaapplication.domain.Customer;

/**
 *
 * @author marco
 */
public interface ICustomerDAO {
    
    public Boolean register (Customer customer);

    public void exclude(Long cpf);

    public void alteration (Customer customer);

    public Customer consult (Long cpf);   
    
}
