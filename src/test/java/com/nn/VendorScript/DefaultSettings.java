/* ****************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute defualt settings in vendor script
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 ******************************************************************************/

package com.nn.VendorScript;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class DefaultSettings extends TestInputData {
	TestInputData Data = new TestInputData();

// Method used to configure the deafult settings for executing vendor script in test environment
	@Test
	public void defaultSettings() throws Exception {
		try {
			Thread.sleep(3000);
			Utility.initConfiguration();
			Thread.sleep(3000);
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			test = extend.createTest("Deactivate IP address control");
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			// Go to Novalnet Global Configuration Tab
			element.Novalnet_Global_Config_Tab.click();
			Thread.sleep(2000);
			// Checking Deactivate IP address control is enabled
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement deactivate_ip = element.Deactivate_IP_Address_Control;
			Thread.sleep(5000);
			action.click(deactivate_ip).sendKeys("Yes", Keys.DOWN, Keys.ENTER).build().perform();
			System.out.println("Deactivate IP address control enabled");
			test.log(Status.INFO, "Deactivate IP address control enabled");
			Thread.sleep(2000);
			element.Novalnet_Global_Config_Save_Changes.click();
			// Close browser
			Utility.closeBrowser();			
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'defaultSettings' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'defaultSettings' method");
		}
	}
}