package com.capg.paymentwallet.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class AccountServiceImplTest {
	
	static IAccountService service = null;
	static AccountBean accBean = null;
	static CustomerBean custBean = null;
	@BeforeClass
	public static void createInstance(){
		service= new AccountServiceImpl();
		accBean = new AccountBean();
		custBean = new CustomerBean();
	}
	
	@Test(expected = CustomerException.class)
	public void testForValidFirstName() throws CustomerException{
		custBean.setFirstName("ppp");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setCustomerBean(custBean);
		accBean.setBalance(10000);
		boolean result = service.createAccount(accBean);
		assertFalse(result);
	}
	@Test(expected = CustomerException.class)
	public void testForValidLastName() throws CustomerException{
		custBean.setFirstName("Priya");
		custBean.setLastName("Jo");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setCustomerBean(custBean);
		accBean.setBalance(10000);
		boolean result = service.createAccount(accBean);
		assertFalse(result);
	}
	@Test(expected = CustomerException.class)
	public void testForValidPhoneNumber() throws CustomerException{
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("122333");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setCustomerBean(custBean);
		accBean.setBalance(10000);
		boolean result = service.createAccount(accBean);
		assertFalse(result);
	}
	@Test(expected = CustomerException.class)
	public void testForValidEmail() throws CustomerException{
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priya");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setCustomerBean(custBean);
		accBean.setBalance(10000);
		boolean result = service.createAccount(accBean);
		assertFalse(result);
	}
	@Test(expected = CustomerException.class)
	public void testForValidAddress() throws CustomerException{
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("122333");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress(null);
		accBean.setCustomerBean(custBean);
		accBean.setBalance(10000);
		boolean result = service.createAccount(accBean);
		assertFalse(result);
	}
	@Test(expected = CustomerException.class)
	public void testForValidBalance() throws CustomerException{
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("122333");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setCustomerBean(custBean);
		accBean.setBalance(100);
		boolean result = service.createAccount(accBean);
		assertFalse(result);
	}
	
	@Test
	public void testDeposit() throws Exception{
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setCustomerBean(custBean);
		accBean.setBalance(10000);
		service.deposit(accBean, 2000);
		assertEquals(12000,accBean.getBalance(),0);
	
}

	@Test
	public void testWithdraw() throws Exception {
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setCustomerBean(custBean);
		accBean.setBalance(10000);
		service.withdraw(accBean, 2000);
		assertEquals(8000,accBean.getBalance(),0);
	}
	
	@Test
	public void testWithdrawForNegativeCase() throws Exception {
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setCustomerBean(custBean);
		accBean.setBalance(10000);
		service.withdraw(accBean, 12000);
		assertEquals(10000,accBean.getBalance(),0);
	}

	@Test
	public void testFundTransfer() throws Exception {
		custBean.setFirstName("Priya");
		custBean.setLastName("Joseph");
		custBean.setPhoneNo("9988776655");
		custBean.setEmailId("priyajoseph@gmail.com");
		custBean.setPanNum("EXR7890654");
		custBean.setAddress("Hyderabad");
		accBean.setCustomerBean(custBean);
		accBean.setBalance(10000);
		CustomerBean custBean1 = new CustomerBean();
		AccountBean accBean1 = new AccountBean();
		custBean1.setFirstName("John");
		custBean1.setLastName("Joseph");
		custBean1.setPhoneNo("9988776688");
		custBean1.setEmailId("johnjoseph@gmail.com");
		custBean1.setPanNum("KLM7890654");
		custBean1.setAddress("Chennai");
		accBean1.setCustomerBean(custBean);
		accBean1.setBalance(10000);
		service.fundTransfer(accBean, accBean1, 5000);
		assertEquals(15000,accBean1.getBalance(),0);
	}

	

}
