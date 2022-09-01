import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class UT3 {

	@Test
	public void test1() {
		List<Integer> list = new RBTList<>();
		//System.out.println(list.toString()); //toString
		assertTrue("toString failed", list.toString().equals("[]"));
	}
	
	@Test
	public void test2() {
		List<Integer> list = new RBTList<>();
		System.out.println(list.toString()); //toString
		list.add(0);
		System.out.println(list); //add
		list.add(0, 123);
		System.out.println(list); //add
		list.add(2, 456);
		assertTrue("add failed", list.get(2) == 456);
		System.out.println(list); //add
	}

}
