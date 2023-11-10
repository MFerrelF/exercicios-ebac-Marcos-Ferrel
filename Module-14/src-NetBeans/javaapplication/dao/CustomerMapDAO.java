/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javaapplication.domain.Customer;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class CustomerMapDAO implements ICustomerDAO {
    
    private Map<Long, Customer> map;

    public CustomerMapDAO() {
        map = new TreeMap<>();
    }

    
    public Boolean register(Customer customer) {
        if (map.containsKey(customer.getCpf())) {
            return false;
        }
        map.put(customer.getCpf(), customer);
        return true;

    }

    
    public void exclude(Long cpf) {
        Customer registeredCustomer = map.get(cpf);
        map.remove(registeredCustomer.getCpf(), registeredCustomer);

    }

    
    public void alteration(Customer customer) {
        Customer registeredCustomer = map.get(customer.getCpf());
        registeredCustomer.setName(customer.getName());
        registeredCustomer.setpNumber(customer.getpNumber());
        registeredCustomer.setNumber(customer.getNumber());
        registeredCustomer.setAddress(customer.getAddress());
        registeredCustomer.setCity(customer.getCity());
        registeredCustomer.setState(customer.getState());

    }

    
    public Customer consult(Long cpf) {
        return this.map.get(cpf);
    }
    
}
