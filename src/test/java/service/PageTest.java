package service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import model.Page;

public class PageTest extends TestCase {

	Page<String> page;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		page = new Page<String>();
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		this.page = null;
		super.tearDown();
	}
	
	public final void testPaginateSubListElement() {
		List<String> list = new ArrayList<String> ();
		list.add("First");		
		assertEquals("First", this.page.paginate(list,0, 1).get(0));
	}

	public final void testPaginateSubListSize() {
		List<String> list = new ArrayList<String> ();
		list.add("First");	
		list.add("Seconde");
		assertTrue(this.page.paginate(list,0, 2).size()==2); 
	}

}
