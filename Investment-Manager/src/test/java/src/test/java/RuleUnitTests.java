package src.test.java;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import groupone.java.investment.Item;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RuleUnitTests {

	@Test
	public void TestRule() {
		System.out.println("Boostrapping the Rule Engine ...");
		KieServices ks= KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		
		KieSession kSession = kContainer.newKieSession();
		Item item = new Item ("A", 123.0, 234.0);
		System.out.println("Item Cateory: " + item.getCategory());
		kSession.insert(item);
		int fired = kSession.fireAllRules();
		System.out.println("Number of Rules executed = " + fired);
		System.out.println("Item Category: = " + item.getCategory());
		assertTrue(true);
	}
	
}