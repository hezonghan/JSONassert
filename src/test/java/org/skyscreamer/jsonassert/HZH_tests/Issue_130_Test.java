
package org.skyscreamer.jsonassert.comparator;  // not .HZH_tests

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.MessageFormat;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class Issue_130_Test{


	/* tool functions, copied and modified from [comparator/ArraySizeComparatorTest.java] */

	
	private void doTest
	(String expectedJSON, String actualJSON) 
	throws JSONException
	{
		JSONAssert.assertEquals(
			expectedJSON, 
			actualJSON, 
			new ArraySizeComparator(JSONCompareMode.STRICT_ORDER)
		);
	}

	private void doFailingMatchTest
	(String expectedJSON, String actualJSON, String expectedMessagePattern) 
	throws JSONException {
		try {
			doTest(expectedJSON, actualJSON);
		}catch (AssertionError e) {
			String failureMessage = MessageFormat.format(
				"Exception message ''{0}'', " + 
				"does not match expected pattern ''{1}''", 
				e.getMessage(), 
				expectedMessagePattern
			);
			assertTrue(
				failureMessage, 
				e.getMessage().matches(expectedMessagePattern)
			);
			return;
		}
		fail("AssertionError not thrown");
	}



	/* HZH's tests */

	@Test
	public void failsWhenArrayEndingWithComma() 
	throws JSONException{
		System.out.println("\n\n\n\nHZH\n\n\n\n");
		try{
			JSONAssert.assertEquals( 
				"[ {id:1} , ]" , 
				"[ {id:1} , {} ]" , 
				true
			);
		//}catch(AssertionError e){
		//}catch(Exception e){
		}catch(JSONException e){
			//System.out.println(e.getMessage());
			//e.printStackTrace();

			assertTrue("" , e.getMessage().equals("Value at 1 is null."));
			return;
		}
		fail("JSONException not thrown");
	}

}
