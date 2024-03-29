package marcos.ferrel;

import marcos.ferrel.dao.CustomerMapDAO;
import marcos.ferrel.dao.ICustomerDAO;
import marcos.ferrel.dao.exception.KeyNotFoundException;
import marcos.ferrel.domain.Customer;

import javax.swing.*;

/**
 * @author marcos.ferrel
 */
public class App {
    
    private static ICustomerDAO iCustomerDAO;

    public static void main(String[] args) {
        iCustomerDAO = new CustomerMapDAO();
        
        String option = JOptionPane.showInputDialog(null, 
                "1- Register, 2- Consult, 3- Exclusion, 4- Update, 5- Exit",
                "Register", JOptionPane.INFORMATION_MESSAGE);
        
        while (!isValidOption(option)) {
            if ("".equals(option)) {
                exit();
            }
            option = JOptionPane.showInputDialog(null, "Invalid option!! Input 1- Register, 2- Consult, 3- Exclusion, 4- Update, 5- Exit",
                    "Register", JOptionPane.INFORMATION_MESSAGE);
        }
        
        while (isValidOption(option)) {
            if (isExitOption(option)) {
                exit();
            } else if (isRegister(option)) {
                String data = JOptionPane.showInputDialog(null, "Insert customer data split by comma, e.g: Name, CPF, Phone, Address, Number, City, State",
                        "Register", JOptionPane.INFORMATION_MESSAGE);
                register(data);
            } else if (isConsult(option)) {
                String data = JOptionPane.showInputDialog(null, "Input Customer Registration CPF!", "Consult Customer!",
                        JOptionPane.INFORMATION_MESSAGE);
                consult(data);
            } else if (isExclusion(option)) {
                String data = JOptionPane.showInputDialog(null, "Input Customer Registration CPF!", "Consult Customer!",
                        JOptionPane.INFORMATION_MESSAGE);
                remove(data);
            } else {
                String data = JOptionPane.showInputDialog(null,
                        "Input Customer Information separated by comma, e.g: Name,CPF,Mobile Number,Address,Number,City,State",
                        "Update", JOptionPane.INFORMATION_MESSAGE);
                try {
                    update(data);
                } catch (KeyNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            option = JOptionPane.showInputDialog(null, "1- Register, 2- Consult, 3- Exclusion, 4- Alteration, 5- Exit",
                    "Register", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    private static boolean isValidOption(String option) {
        if ("1".equals(option) || "2".equals(option) || "3".equals(option) || "4".equals(option)
                || "5".equals(option)) {
            return true;
        }
        return false;
    }

    private static void update(String data) throws KeyNotFoundException {
        String[] sepData = data.split(",");
        Customer customer = new Customer(sepData[0], sepData[1], sepData[2], sepData[3], sepData[4], sepData[5],
                sepData[6]);
        iCustomerDAO.update(customer);
    }

    private static boolean isRegister(String option) {
        if ("1".equals(option)) {
            return true;
        }
        return false;
    }

    private static void register(String data)  {
        String[] sepData = data.split(",");
        Customer customer = new Customer(sepData[0], sepData[1], sepData[2], sepData[3], sepData[4], sepData[5],
                sepData[6]);
        Boolean isRegistered = null;
        try {
            isRegistered = iCustomerDAO.register(customer);
            if (isRegistered) {
                JOptionPane.showMessageDialog(null, "Success!! Customer Registered.", "Success!!",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Customer ALready Exists!!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (KeyNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Updated with success", "Success!!", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static boolean isConsult(String option) {
        if ("2".equals(option)) {
            return true;
        }
        return false;
    }

    private static void consult(String data) {
        Customer customer = iCustomerDAO.consult(Long.parseLong(data));
        if (customer != null) {
            JOptionPane.showMessageDialog(null, "Customer Found: " + customer.toString(), "Success!!",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Customer not Found", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isExclusion(String option) {
        if ("3".equals(option)) {
            return true;
        }
        return false;
    }

    private static void remove(String data) {
        iCustomerDAO.remove(Long.parseLong(data));
        JOptionPane.showMessageDialog(null, "Customer Excluded!!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private static boolean isExitOption(String option) {
        if ("5".equals(option)) {
            return true;
        }
        return false;
    }

    private static void exit() {
        String registeredCustomer = "";
        for (Customer customer : iCustomerDAO.findsAll()) {
            registeredCustomer += customer.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, "Customers Registered: " + registeredCustomer, "Thank You!!",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

}
