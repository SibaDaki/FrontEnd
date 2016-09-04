package za.ac.cput.sibadaki.Factory;

import za.ac.cput.sibadaki.Domain.Account;

/**
 * Created by User on 2016/09/03.
 */
public class AccountFactory {

    public static Account getAccount(Long id, String acc_holder)
    {
        Account myAccount = new Account.Builder()
                .acc_holder(acc_holder)
                .build();

        return myAccount;

    }
}
