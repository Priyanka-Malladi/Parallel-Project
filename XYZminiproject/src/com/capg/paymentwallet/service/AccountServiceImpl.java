package com.capg.paymentwallet.service;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.dao.AccountDAOImpl;
import com.capg.paymentwallet.dao.IAccountDao;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;


public class AccountServiceImpl implements IAccountService{

	@Override
	public boolean createAccount(AccountBean accountBean)
			throws CustomerException {
		boolean result=false;
		IAccountDao dao=new AccountDAOImpl();
		if(validate(accountBean)){
		 result=dao.createAccount(accountBean);
		}
		 return result;
	}

	

	private boolean validate(AccountBean accountBean) throws CustomerException {
		// TODO Auto-generated method stub
		boolean valid=true;
		if(!(accountBean.getCustomerBean().getFirstName().matches("[A-Za-z]{4,}"))) {
			throw new CustomerException(CustomerExceptionMessage.ERROR1);
		}
		if(!(accountBean.getCustomerBean().getLastName().matches("[A-Za-z]{4,}"))) {
			throw new CustomerException(CustomerExceptionMessage.ERROR2);
		}
		if(!(accountBean.getCustomerBean().getPhoneNo().matches("^[6-9][0-9]{9}$"))) {
			throw new CustomerException(CustomerExceptionMessage.ERROR5);
		}
		if(!(accountBean.getCustomerBean().getEmailId().matches("[a-zA-Z0-9]+@+[a-z]+\\.com"))) {
			throw new CustomerException(CustomerExceptionMessage.ERROR3);
		}
		if(!(accountBean.getBalance()>500)) {
			throw new CustomerException(CustomerExceptionMessage.ERROR6);
		}
		if(!(accountBean.getCustomerBean().getAddress()!=null)) {
			throw new CustomerException(CustomerExceptionMessage.ERROR7);
		}
		if(!(accountBean.getCustomerBean().getPanNum().length()==10)) {
			throw new CustomerException(CustomerExceptionMessage.ERROR4);
		}
		return valid;
	}



	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception {
		boolean result=false;
		if(depositAmount>=0){
		accountBean.setBalance(accountBean.getBalance()+depositAmount);
		IAccountDao dao=new AccountDAOImpl();
		result=dao.updateAccount(accountBean);
		}
		return result;
	}

	@Override
	public boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception {
		boolean result = false;
		if(withdrawAmount>0 && withdrawAmount<=accountBean.getBalance()){
		accountBean.setBalance(accountBean.getBalance()-withdrawAmount);
		IAccountDao dao=new AccountDAOImpl();
		result=dao.updateAccount(accountBean);
		}
		return result;
	}

	@Override
	public boolean fundTransfer(AccountBean transferingAccountBean,
			AccountBean beneficiaryAccountBean, double transferAmount) throws Exception {
		
		transferingAccountBean.setBalance(transferingAccountBean.getBalance()-transferAmount);
		beneficiaryAccountBean.setBalance(beneficiaryAccountBean.getBalance()+transferAmount);
		
		IAccountDao dao=new AccountDAOImpl();
		boolean result1=dao.updateAccount(transferingAccountBean);
		boolean result2=dao.updateAccount(beneficiaryAccountBean);
		return result1 && result2;
	}

	



	@Override
	public AccountBean findAccount(int accountId) throws Exception {
		IAccountDao dao=new AccountDAOImpl();
		AccountBean bean=dao.findAccount(accountId);
		return bean;
	}

}
