package com.qaprosoft.carina.core.foundation.utils;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests for {@link R}
 */
public class RTest 
{
	@Test
	public void testOverrideBySystemParam()
	{
		final String BROWSER = "firefox";
		System.setProperty("browser", BROWSER);
		Assert.assertEquals(R.CONFIG.get("browser"), BROWSER);
	}
	
	@Test
	public void testDefaultValue()
	{
		Assert.assertEquals(R.CONFIG.get("browser"), "chrome");
	}
	
	@Test
	public void testOverrideInProperties()
	{
		Assert.assertEquals(R.CONFIG.get("port"), "8081");
	}
	
	@Test
	public void testPlaceholders()
	{
		Assert.assertEquals(R.CONFIG.get("url"), "http://localhost:8081");
	}
	
	@Test
	public void testEncryption()
	{
		Assert.assertEquals(R.CONFIG.get("password"), "EncryptMe");
		Assert.assertEquals(R.CONFIG.getSecured("password"), "{crypt:8O9iA4+f3nMzz85szmvKmQ==}");
	}
	
	@Test
	public void testPlaceholdersWithEncryption()
	{
		Assert.assertEquals(R.CONFIG.get("credentials"), "test@gmail.com/EncryptMe");
	}
}
