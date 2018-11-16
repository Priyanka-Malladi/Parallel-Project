package com.capg.paymentwallet.dao;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.exception.CustomerException;

public interface IAccountDao {


    public boolean createAccount(AccountBean accountBean) throws CustomerException ;
    public boolean updateAccount(AccountBean accountBean) throws Exception;
    public AccountBean findAccount(int accountId) throws Exception;
  
  
	
	 
    
}
