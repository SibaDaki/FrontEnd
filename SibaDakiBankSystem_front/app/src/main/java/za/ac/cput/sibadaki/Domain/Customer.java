package za.ac.cput.sibadaki.Domain;

import java.io.Serializable;

/**
 * Created by User on 2016/09/03.
 */
public class Customer implements Serializable {

    private Long id;
    private String idNo;
    private String name;
    private String surName;


    /*
    @Embedded
    private Address address // Valued Object
    */

    public Customer(Builder builder) {

        this.id = builder.id;
        this.idNo = builder.idNo;
        this.surName = builder.surName;
        this.name = builder.name;
    }


    public Long getId()
    {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getIdNo() {
        return idNo;
    }


    public static class Builder {

        //Equivalent to setters
        private Long id;
        private String name;
        private String surName;
        private String idNo;


        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder idNo (String idNo) {
            this.idNo = idNo;
            return this;
        }


        /*
        public Builder (String idNo) {
            this.idNo = idNo; //compalsury
        }
         */

        public Builder surName(String Surname) {
            this.surName = Surname;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }



        public Builder copy(Customer customer){
            this.id = customer.id;
            this.idNo = customer.idNo;
            this.name = customer.name;
            this.surName = customer.surName;
            return this;
        }



        public Customer build() {
            return new Customer(this);
        }
    }



}
