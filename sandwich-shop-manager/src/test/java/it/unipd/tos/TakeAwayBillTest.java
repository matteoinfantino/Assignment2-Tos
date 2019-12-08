package it.unipd.tos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import it.unipd.tos.business.TakeAwayBillImpl;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

public class TakeAwayBillTest {

	@org.junit.Test
	public void testOverSize() {
		List<MenuItem> order = new ArrayList<MenuItem>();
		for(int i = 0; i < 31; i++) {
			order.add(new MenuItem(MenuItem.itemType.PANINI,"Primavera",5.50));
		}
		TakeAwayBillImpl bill = new TakeAwayBillImpl();
		try {
			bill.getOrderPrice(order);
		}
		catch (TakeAwayBillException e){
			System.out.println("error");
		}
	}
	@org.junit.Test
	public void test5Panini() {
		List<MenuItem> order = new ArrayList<MenuItem>();
		for(int i = 0; i < 5; i++) {
			order.add(new MenuItem(MenuItem.itemType.PANINI,"Primavera",5.50));
		}
		order.add(new MenuItem(MenuItem.itemType.PANINI,"Vegetariano",5.0));
		TakeAwayBillImpl bill = new TakeAwayBillImpl();
		try {
			assertEquals(30,bill.getOrderPrice(order),1E-10);
		}
		catch (TakeAwayBillException e){
			System.out.println("error2");
		}
	}
	@org.junit.Test
	public void testLess10() {
		List<MenuItem> order = new ArrayList<MenuItem>();
		order.add(new MenuItem(MenuItem.itemType.FRITTI,"Olive Ascolane",5.0));
		TakeAwayBillImpl bill = new TakeAwayBillImpl();
		try {
			assertEquals(5.5,bill.getOrderPrice(order),1E-10);
		}
		catch(TakeAwayBillException e) {
			System.out.println("error3");
		}	
	}
	@org.junit.Test
	public void testSconto50() {
		List<MenuItem> order = new ArrayList<MenuItem>();
		for(int i = 0; i < 30; i++) {
			order.add(new MenuItem(MenuItem.itemType.PANINI,"Primavera",5.0));
		}
		TakeAwayBillImpl bill = new TakeAwayBillImpl();
		try {
			assertEquals(132.75,bill.getOrderPrice(order),1E-10);
		}
		catch(TakeAwayBillException e) {
			System.out.println("error4");
		}
	}
}
