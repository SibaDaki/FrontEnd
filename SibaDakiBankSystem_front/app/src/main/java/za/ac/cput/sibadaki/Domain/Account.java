package za.ac.cput.sibadaki.Domain;

import java.io.Serializable;

/**
 * Created by User on 2016/09/03.
 */
public class Account implements Serializable {

    private Long id;
    private String acc_holder;


    public Account(Builder builder) {


        this.acc_holder = builder.acc_holder;

    }


    public Long getId() {
        return id;
    }

    public String getAcc_holder() {
        return acc_holder;
    }



    public static class Builder {

        //Equivalent to setters
        private Long id;
        private String acc_holder;



        public Builder id(Long value) {
            this.id = value;
            return this;
        }


        public Builder acc_holder(String acc_holder) {
            this.acc_holder = acc_holder;
            return this;
        }


        public Builder copy(Account account) {

            this.acc_holder = account.acc_holder;
            ;
            return this;
        }


        public Account build() {
            return new Account(this);
        }
    }
}


