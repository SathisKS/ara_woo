/* ************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute vendor script for Barzahlen
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 **************************************************************************/

package com.nn.VendorScript;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class Barzahlen extends TestInputData {

	// Method used to execute 'CASHPAYMENT_CREDIT'
	@Test
	public void cashpaymentCredit() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for 'CASHPAYMENT_CREDIT'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Barzahlen_Payment_Display.click();
			// Get the order completion status in the back end payment
			String BarzahlenCallbackOrderStatus = element.Barzahlen_CallBack_Order_Status_Selectbox.getText();
			// Check payment method checkbox is checked or unchecked
			if (!element.Barzahlen_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Barzahlen_Enable_Payment_Method_Checkbox.click();
			}
			Thread.sleep(3000);
			element.Barzahlen_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Check whether payment method is displayed in checkout page
			if (element.Barzahlen_Label.isDisplayed()) {
				if (element.Barzahlen_Radio_Button.isDisplayed() == true) {
					element.Barzahlen_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Place_Order.click();

				Thread.sleep(35000);
				element.Shopadmin_Search.sendKeys(Keys.ESCAPE);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Cash Payment(Barzahlen)");
					test.log(Status.INFO, "Order placed successfully using Cash Payment(Barzahlen)");
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					String TID_new = TID.substring(0, 17);
					System.out.println("Order amount: " + totalOrderAmount);
					System.out.println("TID: " + TID_new);
					Thread.sleep(2000);
					// Go to callback execution site and enter vendor script URL
					driver.navigate().to(Constant.vendorscripturl);
					Thread.sleep(4000);
					element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
					// Enter required parameter
					element.Vendor_Id.clear();
					element.Vendor_Id.sendKeys("4");
					element.Vendor_Auth_Code.clear();
					element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
					element.Product_Id.clear();
					element.Product_Id.sendKeys("14");
					element.Tariff_Id.clear();
					element.Tariff_Id.sendKeys("30");
					element.Payment_Type_Edit_Button.click();
					Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
					selectpaymenttype.selectByVisibleText("CASHPAYMENT_CREDIT");
					element.Test_Mode.clear();
					element.Test_Mode.sendKeys("1");
					element.Tid_Payment.clear();
					element.Tid_Payment.sendKeys(TID_new);
					element.Amount.clear();
					element.Amount.sendKeys(totalOrderAmount);
					element.Currency.clear();
					element.Currency.sendKeys("EUR");
					element.Status.clear();
					element.Status.sendKeys("100");
					element.Tid_Status.clear();
					element.Tid_Status.sendKeys("100");
					element.Tid.clear();
					element.Tid.sendKeys("13245678945612345");
					element.Execute_Button.click();
					String callback_message = element.callback_message.getText();
					String callback_message_updated = callback_message.substring(9, callback_message.length());
					System.out.println("Callback execution message: " + callback_message_updated);
					if (callback_message.contains("Novalnet Callback Script executed successfully for the TID:")) {						
						Thread.sleep(2000);
						// Navigate to shop back end
						driver.navigate().to(Constant.shopbackendurl);
						Thread.sleep(1000);
						actions.moveToElement(element.WooCommerce).perform();
						Thread.sleep(1500);
						element.WooCommerce_Orders.click();
						String BEOrderStatus = element.Backend_Order_Status.getText();
						element.Backend_Order_Number.click();
						String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();
						System.out.println("Back end order note: " + BEOrderNotes_CallBackMessage);
						// Verify the callback execution message is updated in the order note
						if (BEOrderNotes_CallBackMessage.equalsIgnoreCase(callback_message_updated)) {
							System.out.println(
									"TC PASSED: CASHPAYMENT_CREDIT execution message and back end order note message text was matched successfully");
							test.log(Status.PASS,
									"TC PASSED: CASHPAYMENT_CREDIT execution message and back end order note message text was matched successfully");
						} else {
							System.out.println(
									"TC FAILED: CASHPAYMENT_CREDIT execution message and back end order note message text was not matched successfully");
							test.log(Status.FAIL,
									"TC PASSED: CASHPAYMENT_CREDIT execution message and back end order note message text was not matched successfully");
						}

						// Verify callback order status is updated in shop back end after the execution
						// CASHPAYMENT_CREDIT payment
						System.out.println("Callback order status: " + BarzahlenCallbackOrderStatus);
						System.out.println("Back end order status: " + BEOrderStatus);
						if (BarzahlenCallbackOrderStatus.equals(BEOrderStatus)) {
							System.out.println(
									"TC PASSED: Callback order status is updated successfully in shop back end");
							test.log(Status.PASS,
									"TC PASSED:  Callback order status is updated successfully in shop back end");
						} else {
							System.out.println(
									"TC FAILED: Callback order status is not updated successfully in shop back end");
							test.log(Status.FAIL,
									"TC FAILED: Callback order status is not updated successfully in shop back end");

						}
						// Navigate to shop front end
						// Verify callback order status is updated in shop back end after the execution
						// CASHPAYMENT_CREDIT payment
						driver.navigate().to(Constant.shopfrontendurl);
						Thread.sleep(1000);
						element.MyAccount_Menu.click();
						element.MyAccount_Orders.click();
						String FEOrderStatus = element.Frontend_Order_Status.getText();
						System.out.println("Front end order status: " + FEOrderStatus);
						if (BarzahlenCallbackOrderStatus.equals(FEOrderStatus)) {
							System.out.println(
									"TC PASSED: Barzahlen callback order status is updated successfully in shop front end");
							test.log(Status.PASS,
									"TC PASSED:  Barzahlen callback order status is updated successfully in shop front end");
						} else {
							System.out.println(
									"TC FAILED: Barzahlen callback order status is not updated successfully in shop front end");
							test.log(Status.FAIL,
									"TC FAILED: Barzahlen callback order status is not updated successfully in shop front end");
						}
					} else {
						System.out.println("TC FAILED: Callback response message is displayed wrongly");
						test.log(Status.FAIL, "TC FAILED: Callback response message is displayed wrongly");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Cash Payment(Barzahlen)");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Cash Payment(Barzahlen)");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'cashpaymentCredit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'cashpaymentCredit' method");
		}
	}

	// Method used to execute 'CASHPAYMENT_REFUND'
	@Test
	public void cashpaymentRefund() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for 'CASHPAYMENT_REFUND'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Barzahlen_Payment_Display.click();
			// Check payment method checkbox is checked or unchecked
			if (!element.Barzahlen_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Barzahlen_Enable_Payment_Method_Checkbox.click();
			}
			Thread.sleep(3000);
			element.Barzahlen_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Check whether payment method is displayed in checkout page
			if (element.Barzahlen_Label.isDisplayed()) {
				if (element.Barzahlen_Radio_Button.isDisplayed() == true) {
					element.Barzahlen_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Place_Order.click();
				Thread.sleep(35000);
				element.Shopadmin_Search.sendKeys(Keys.ESCAPE);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Cash Payment(Barzahlen)");
					test.log(Status.INFO, "Order placed successfully using Cash Payment(Barzahlen)");
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					String TID_new = TID.substring(0, 17);
					System.out.println("Order amount: " + totalOrderAmount);
					System.out.println("TID: " + TID_new);
					Thread.sleep(2000);
					// Go to callback execution site and enter vendor script URL
					driver.navigate().to(Constant.vendorscripturl);
					Thread.sleep(4000);
					element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
					// Enter required parameter
					element.Vendor_Id.clear();
					element.Vendor_Id.sendKeys("4");
					element.Vendor_Auth_Code.clear();
					element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
					element.Product_Id.clear();
					element.Product_Id.sendKeys("14");
					element.Tariff_Id.clear();
					element.Tariff_Id.sendKeys("30");
					element.Payment_Type_Edit_Button.click();
					Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
					selectpaymenttype.selectByVisibleText("CASHPAYMENT_REFUND");
					element.Test_Mode.clear();
					element.Test_Mode.sendKeys("1");
					element.Tid_Payment.clear();
					element.Tid_Payment.sendKeys(TID_new);
					element.Amount.clear();
					element.Amount.sendKeys(totalOrderAmount);
					element.Currency.clear();
					element.Currency.sendKeys("EUR");
					element.Status.clear();
					element.Status.sendKeys("100");
					element.Tid_Status.clear();
					element.Tid_Status.sendKeys("100");
					element.Tid.clear();
					element.Tid.sendKeys("13245678945612345");
					element.Execute_Button.click();
					String callback_message = element.callback_message.getText();
					String callback_message_updated = callback_message.substring(9, callback_message.length());
					System.out.println("Callback execution message: " + callback_message_updated);
					if (callback_message.contains(
							"Novalnet callback received. Refund/Bookback executed successfully for the TID:")) {						
						Thread.sleep(2000);
						// Navigate to shop back end
						driver.navigate().to(Constant.shopbackendurl);
						Thread.sleep(1000);
						actions.moveToElement(element.WooCommerce).perform();
						Thread.sleep(1500);
						element.WooCommerce_Orders.click();
						// String BEOrderStatus = element.Backend_Order_Status.getText();
						element.Backend_Order_Number.click();
						String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();
						System.out.println("Back end order note: " + BEOrderNotes_CallBackMessage);
						// Verify the callback execution message is updated in the order note
						if (BEOrderNotes_CallBackMessage.equalsIgnoreCase(callback_message_updated)) {
							System.out.println(
									"TC PASSED: CASHPAYMENT_REFUND execution message and back end order note message text was matched successfully");
							test.log(Status.PASS,
									"TC PASSED: CASHPAYMENT_REFUND execution message and back end order note message text was matched successfully");
						} else {
							System.out.println(
									"TC FAILED: CASHPAYMENT_REFUND execution message and back end order note message text was not matched successfully");
							test.log(Status.FAIL,
									"TC PASSED: CASHPAYMENT_REFUND execution message and back end order note message text was not matched successfully");
						}
					} else {
						System.out.println("TC FAILED: Callback response message is displayed wrongly");
						test.log(Status.FAIL, "TC FAILED: Callback response message is displayed wrongly");

					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Cash Payment(Barzahlen)");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Cash Payment(Barzahlen)");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'cashpaymentRefund' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'cashpaymentRefund' method");
		}
	}
}
