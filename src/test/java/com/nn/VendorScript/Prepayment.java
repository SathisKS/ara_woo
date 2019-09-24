/* ************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute vendor script for Prepayment
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 **************************************************************************/

package com.nn.VendorScript;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class Prepayment extends TestInputData {
	
	// Method used to execute 'INVOICE_CREDIT'
	@Test
	public void invoiceCredit() throws Exception {
		try {
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or not
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			test = extend.createTest("Vendor script execution for INVOICE_CREDIT");
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Prepayment_Payment_Display.click();
			String PrepaymentCallbackOrderStatus = element.Prepayment_CallBack_Order__Status_Selectbox.getText();			
			try {
				if (!element.Prepayment_Enable_Payment_Method_Checkbox.isSelected()) {
					element.Prepayment_Enable_Payment_Method_Checkbox.click();
				}
				Thread.sleep(3000);
				element.Prepayment_Payment_Save_Changes.click();
				Thread.sleep(3000);

				driver.navigate().to(Constant.shopfrontendurl);
				Thread.sleep(3000);
				Utility.wooCommerceCheckOutProcess();
				Thread.sleep(3000);
				try {
					if (element.Prepayment_Label.isDisplayed()) {
						if (element.Prepayment_Radio_Button.isDisplayed() == true) {
							element.Prepayment_Radio_Button.click();
						}
						Thread.sleep(2000);
						element.Place_Order.click();
						Thread.sleep(50000);						
						String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
						if (thankyoumessage.equals("Thank you. Your order has been received.")) {
							System.out.println("Order placed successfully using Prepayment");
							test.log(Status.INFO, "Order placed successfully using Prepayment");
							Thread.sleep(3000);
								String totalOrderAmount = element.OrderDetails_TotalAmount.getText()
										.replaceAll("[^0-9]", "");
								String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
								System.out.println("Order amount:" + totalOrderAmount);
								System.out.println("TID:" + TID);
								// Go to callback execution site and enter vendor script URL
								driver.navigate().to(Constant.vendorscripturl);
								Thread.sleep(4000);
								element.Vendor_Script_Url
										.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
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
								selectpaymenttype.selectByVisibleText("INVOICE_CREDIT");
								element.Test_Mode.clear();
								element.Test_Mode.sendKeys("1");
								element.Tid_Payment.clear();
								element.Tid_Payment.sendKeys(TID);
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
								String callback_message_updated = callback_message.substring(9,
										callback_message.length());
								System.out.println("Callback execution message: " + callback_message_updated);							
								if (callback_message.contains("Novalnet Callback Script executed successfully for the TID:")) {
										// Go to shop front end and check the order status update
										driver.navigate().to(Constant.shopbackendurl);
										actions.moveToElement(element.WooCommerce).perform();
										Thread.sleep(3000);
										element.WooCommerce_Orders.click();
										String BEOrderStatus = element.Backend_Order_Status.getText();
										element.Backend_Order_Number.click();
										String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();
										System.out.println("Back end order note: " + BEOrderNotes_CallBackMessage);
										// Verify the callback execution message is updated in the order note
										if (BEOrderNotes_CallBackMessage.equalsIgnoreCase(callback_message_updated)) {
											System.out.println(
													"TC PASSED: 'INVOICE_CREDIT' execution message and back end order note message text was matched successfully");
											test.log(Status.PASS,
													"TC PASSED: 'INVOICE_CREDIT' execution message and back end order note message text was matched successfully");
										} else {
											System.out.println(
													"TC FAILED: 'INVOICE_CREDIT' execution message and back end order note message text was not matched successfully");
											test.log(Status.FAIL,
													"TC PASSED: 'INVOICE_CREDIT' execution and back end order note message text was not matched successfully");
										}
										// Verify callback order status is updated in shop back end after the execution
										System.out.println("Callback order status: " + PrepaymentCallbackOrderStatus);
										System.out.println("Back end order status: " + BEOrderStatus);
											if (PrepaymentCallbackOrderStatus.equals(BEOrderStatus)) {
												System.out.println(
														"TC PASSED: Callback order status is updated successfully in shop back end");
												test.log(Status.PASS,
														"TC PASSED: Callback order status is updated successfully in shop back end");
											
										}	else {
											System.out.println(
													"TC FAILED: Callback order status is not updated in shop back end");
											test.log(Status.FAIL,
													"TC FAILED: Callback order status is not updated in shop back end");
										}
										driver.navigate().to(Constant.shopfrontendurl);
										Thread.sleep(1000);
										element.MyAccount_Menu.click();
										element.MyAccount_Orders.click();
										String FEOrdersStatus = element.Frontend_Order_Status.getText();
										System.out.println("Front end order status: " +FEOrdersStatus);
										// Verify callback order status is updated in shop front end after the execution										
											if (PrepaymentCallbackOrderStatus.equals(FEOrdersStatus)) {
												System.out.println(
														"TC PASSED: Callback order status is updated successfully in shop front end");
												test.log(Status.PASS,
														"TC PASSED: Callback order status is updated successfully in shop front end");
											}	else {
											System.out.println(
													"TC FAILED: Callback order status is not updated in shop front end");
											test.log(Status.FAIL,
													"TC FAILED: Callback order status is not updated in shop front end");
										}
									}	else {
									System.out.println("ERROR: Callback response message is displayed wrongly");
									test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
								}
							}	else {
							System.out.println("ERROR: Order was not placed successfully using prepayment");
							test.log(Status.ERROR, "ERROR: Order was not placed successfully using prepayment");
						}
					}
				} catch (Exception e) {
					System.out.println("ERROR: Prepayment payment not available in the checkout page");
					test.log(Status.ERROR, "ERROR: Prepayment payment not available in the checkout page");
				}
			} catch (Exception e) {
				System.out.println("ERROR: Prepayment Enable Payment Method Checkbox was not displayed");
				test.log(Status.ERROR, "ERROR: Prepayment Enable Payment Method Checkbox not not displayed");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'invoiceCredit' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'invoiceCredit' method");
		}
	}
	
	// Method used to execute 'REFUND_BY_BANK_TRANSFER_EU'
	@Test
	public void refundByBankTransferEuPrepayment() throws Exception {
		try {
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or not
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			test = extend.createTest("Vendor script execution for 'REFUND_BY_BANK_TRANSFER_EU'");
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Prepayment_Payment_Display.click();
			try {
				if (!element.Prepayment_Enable_Payment_Method_Checkbox.isSelected()) {
					element.Prepayment_Enable_Payment_Method_Checkbox.click();
				}
				Thread.sleep(3000);
				element.Prepayment_Payment_Save_Changes.click();
				Thread.sleep(3000);

				driver.navigate().to(Constant.shopfrontendurl);
				Thread.sleep(3000);
				Utility.wooCommerceCheckOutProcess();
				Thread.sleep(3000);
				try {
					if (element.Prepayment_Label.isDisplayed()) {
						if (element.Prepayment_Radio_Button.isDisplayed() == true) {
							element.Prepayment_Radio_Button.click();
						}
						Thread.sleep(2000);
						element.Place_Order.click();
						Thread.sleep(50000);						
						String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
						if (thankyoumessage.equals("Thank you. Your order has been received.")) {
							System.out.println("Order placed successfully using Prepayment");
							test.log(Status.INFO, "Order placed successfully using Prepayment");
							Thread.sleep(3000);
								String totalOrderAmount = element.OrderDetails_TotalAmount.getText()
										.replaceAll("[^0-9]", "");
								String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
								System.out.println("Order amount:" + totalOrderAmount);
								System.out.println("TID:" + TID);
								// Go to callback execution site and enter vendor script URL
								driver.navigate().to(Constant.vendorscripturl);
								Thread.sleep(4000);
								element.Vendor_Script_Url
										.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
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
								selectpaymenttype.selectByVisibleText("REFUND_BY_BANK_TRANSFER_EU");
								element.Test_Mode.clear();
								element.Test_Mode.sendKeys("1");
								element.Tid_Payment.clear();
								element.Tid_Payment.sendKeys(TID);
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
								String callback_message_updated = callback_message.substring(9,
										callback_message.length());
								System.out.println("Callback execution message: " + callback_message_updated);							
								if (callback_message.contains("Novalnet callback received. Refund/Bookback executed successfully for the TID:")) {
										// Go to shop front end and check the order status update
										driver.navigate().to(Constant.shopbackendurl);
										actions.moveToElement(element.WooCommerce).perform();
										Thread.sleep(3000);
										element.WooCommerce_Orders.click();										
										element.Backend_Order_Number.click();
										String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();
										System.out.println("Back end order note: " + BEOrderNotes_CallBackMessage);
										// Verify the callback execution message is updated in the order note
										if (BEOrderNotes_CallBackMessage.equalsIgnoreCase(callback_message_updated)) {
											System.out.println(
													"TC PASSED: 'REFUND_BY_BANK_TRANSFER_EU' execution message and back end order note message text was matched successfully");
											test.log(Status.PASS,
													"TC PASSED: 'REFUND_BY_BANK_TRANSFER_EU' execution message and back end order note message text was matched successfully");
										} else {
											System.out.println(
													"TC FAILED: REFUND_BY_BANK_TRANSFER_EU execution message and back end order note message text was not matched successfully");
											test.log(Status.FAIL,
													"TC PASSED: REFUND_BY_BANK_TRANSFER_EU execution and back end order note message text was not matched successfully");
										}
									}	else {
									System.out.println("ERROR: Callback response message is displayed wrongly");
									test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
								}
							}	else {
							System.out.println("ERROR: Order was not placed successfully using prepayment");
							test.log(Status.ERROR, "ERROR: Order was not placed successfully using prepayment");
						}
					}
				} catch (Exception e) {
					System.out.println("ERROR: Prepayment payment not available in the checkout page");
					test.log(Status.ERROR, "ERROR: Prepayment payment not available in the checkout page");
				}
			} catch (Exception e) {
				System.out.println("ERROR: Prepayment Enable Payment Method Checkbox was not displayed");
				test.log(Status.ERROR, "ERROR: Prepayment Enable Payment Method Checkbox not not displayed");
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'refundByBankTransferEuPrepayment' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'refundByBankTransferEuPrepayment' method");
		}
	}
}