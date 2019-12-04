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
	@org.junit.Test
	public void testNoSconto50() {
		/*Il totale � maggiore di 50, ma lo sconto non deve essere effettuato
		 * perch� panini+fritti<50. Il prezzo delle bevande non deve essere
		 * conteggiato per il raggiungimento dello sconto.
		 */
		List<MenuItem> order = new ArrayList<MenuItem>();
		order.add(new MenuItem(MenuItem.itemType.PANINI,"Primavera",30.0));
		order.add(new MenuItem(MenuItem.itemType.FRITTI,"Olive Ascolane",15.0));
		order.add(new MenuItem(MenuItem.itemType.BEVANDE,"Coca-Cola",10.0));
		TakeAwayBillImpl bill = new TakeAwayBillImpl();
		try {
			assertEquals(55.0,bill.getOrderPrice(order),1E-10);
		}
		catch(TakeAwayBillException e) {
			System.out.println("error4");
		}
	}
}
