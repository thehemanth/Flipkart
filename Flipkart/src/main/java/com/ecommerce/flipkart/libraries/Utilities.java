package com.ecommerce.flipkart.libraries;
public class Utilities
{
	public static void sleepInSeconds(long seconds)
	{
		long millis = seconds * 1000;
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}