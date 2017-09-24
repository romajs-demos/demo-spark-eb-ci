package com.romajs.spark;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelloTest {

	private static Application application;

	@BeforeClass
	public static void setUpClass() {
		application = new Application();
		application.configureRoutes();
		application.start();
	}

	@Test
	public void shouldGetHello() {
		Assert.assertNotNull(application);
	}

}
