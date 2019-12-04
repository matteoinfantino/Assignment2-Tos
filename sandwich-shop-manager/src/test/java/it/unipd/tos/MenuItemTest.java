package it.unipd.tos;

import static org.junit.Assert.assertEquals;

import it.unipd.tos.model.MenuItem;

public class MenuItemTest {
	
	@org.junit.Test
	public void testGetType() {
		MenuItem item = new MenuItem(MenuItem.itemType.PANINI,"Primavera",5.50);
		assertEquals(MenuItem.itemType.PANINI,item.getType());
	}
	@org.junit.Test
	public void testGetPrice() {
		MenuItem item = new MenuItem(MenuItem.itemType.PANINI,"Primavera",5.50);
		assertEquals(5.50,item.getPrice(),1E-10);
	}
	@org.junit.Test
	public void testGetName() {
		MenuItem item = new MenuItem(MenuItem.itemType.PANINI,"Primavera",5.50);
		assertEquals("Primavera",item.getName());
	}
}