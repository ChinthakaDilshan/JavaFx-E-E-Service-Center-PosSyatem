package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.ItemDao;
import dao.util.DaoType;
import dto.CustomerDto;
import dto.ItemDto;
import entity.Customer;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(
                dto.getCustomerCode(),
                dto.getCustomerName(),
                dto.getCustomerContact(),
                dto.getCustomerEmail()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(
                dto.getCustomerCode(),
                dto.getCustomerName(),
                dto.getCustomerContact(),
                dto.getCustomerEmail()
        ));
    }

    @Override
    public boolean deleteCustomer(String customerCode) throws SQLException, ClassNotFoundException {
        return customerDao.delete(customerCode);
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> entityList = customerDao.getAll();
        List<CustomerDto> list = new ArrayList<>();
        for (Customer customer:entityList) {
            list.add(new CustomerDto(
                    customer.getCustomerCode(),
                    customer.getCustomerName(),
                    customer.getCustomerContact(),
                    customer.getCustomerEmail()
            ));
        }
        return list;
    }
}
