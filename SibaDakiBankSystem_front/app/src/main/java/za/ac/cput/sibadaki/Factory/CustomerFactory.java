package za.ac.cput.sibadaki.Factory;

import za.ac.cput.sibadaki.Domain.Customer;

/**
 * Created by User on 2016/09/03.
 */
public class CustomerFactory {

    public static Customer getCustmer(String idNo, String name, String surName)
    {
        Customer myCustomer = new Customer.Builder()
                .idNo(idNo)
                .name(name)
                .surName(surName)
                .build();

        return myCustomer;

    }
}
