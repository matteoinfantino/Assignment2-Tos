package it.unipd.tos;

import static org.junit.Assert.assertEquals;

import it.unipd.tos.business.exception.TakeAwayBillException;

public class TakeAwayBillExceptionTest {

	@org.junit.Test
	public void testGetMessage() {
		TakeAwayBillException exc = new TakeAwayBillException("error");
		assertEquals("error",exc.getMessage());
	}
}