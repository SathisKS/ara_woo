/* ************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute vendor script for Invoice
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 **************************************************************************/

package com.nn.VendorScript;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class Invoice extends TestInputData {
	static TestInputData Data = new TestInputData();

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
			test = extend.createTest("Vendor script execution for 'INVOICE_CREDIT'");
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Invoice_Payment_Display.click();
			String InvoiceCallbackOrderStatus = element.Invoice_CallBack_Order_Status_Selectbox.getText();
			if (!element.Invoice_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Invoice_Enable_Payment_Method_Checkbox.click();
			}
			Thread.sleep(3000);
			element.Invoice_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(3000);
			if (element.Invoice_Label.isDisplayed()) {
				if (element.Invoice_Radio_Button.isDisplayed() == true) {
					element.Invoice_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Place_Order.click();
				Thread.sleep(50000);
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Invoice");
					test.log(Status.INFO, "Order placed successfully using Invoice");
					Thread.sleep(3000);
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					System.out.println("TID:" + TID);
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
					String callback_message_updated = callback_message.substring(9, callback_message.length());
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
									"TC PASSED: INVOICE_CREDIT execution message and back end order note message text was matched successfully");
							test.log(Status.PASS,
									"TC PASSED: INVOICE_CREDIT execution message and back end order note message text was matched successfully");
						} else {
							System.out.println(
									"TC FAILED: INVOICE_CREDIT execution message and back end order note message text was not matched successfully");
							test.log(Status.FAIL,
									"TC PASSED: INVOICE_CREDIT execution and back end order note message text was not matched successfully");
						}
						// Verify callback order status is updated in shop back end after the execution
						System.out.println("Callback order status: " + InvoiceCallbackOrderStatus);
						System.out.println("Back end order status: " + BEOrderStatus);
						if (InvoiceCallbackOrderStatus.equals(BEOrderStatus)) {
							System.out.println("TC PASSED: Callback order status is updated successfully in shop back end");
							test.log(Status.PASS,
									"TC PASSED: Callback order status is updated successfully in shop back end");

						} else {
							System.out.println("TC FAILED: Callback order status is not updated in shop back end");
							test.log(Status.FAIL, "TC FAILED: Callback order status is not updated in shop back end");
						}
						driver.navigate().to(Constant.shopfrontendurl);
						Thread.sleep(1000);
						element.MyAccount_Menu.click();
						element.MyAccount_Orders.click();
						String FEOrdersStatus = element.Frontend_Order_Status.getText();
						System.out.println("Front end order status: " + FEOrdersStatus);
						// Verify callback order status is updated in shop front end after the execution
						if (InvoiceCallbackOrderStatus.equals(FEOrdersStatus)) {
							System.out.println("TC PASSED: Callback order status is updated successfully in shop front end");
							test.log(Status.PASS,
									"TC PASSED: Callback order status is updated successfully in shop front end");
						} else {
							System.out.println("TC FAILED: Callback order status is not updated in shop front end");
							test.log(Status.FAIL, "TC FAILED: Callback order status is not updated in shop front end");
						}
					} else {
						System.out.println("ERROR: Callback response message is displayed wrongly");
						test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
					}
				} else {
					System.out.println("ERROR: Order was not placed successfully using Invoice");
					test.log(Status.ERROR, "ERROR: Order was not placed successfully using Invoice");
				}
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
	public void refundByBankTransferEuInvoice() throws Exception {
		try {
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or not
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			test = extend.createTest("Vendor script execution for invoice 'REFUND_BY_BANK_TRANSFER_EU'");
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Invoice_Payment_Display.click();
			if (!element.Invoice_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Invoice_Enable_Payment_Method_Checkbox.click();
			}
			Thread.sleep(3000);
			element.Invoice_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(3000);
			if (element.Invoice_Label.isDisplayed()) {
				if (element.Invoice_Radio_Button.isDisplayed() == true) {
					element.Invoice_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Place_Order.click();
				Thread.sleep(50000);
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Invoice");
					test.log(Status.INFO, "Order placed successfully using Invoice");
					Thread.sleep(3000);
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					System.out.println("TID:" + TID);
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
					String callback_message_updated = callback_message.substring(9, callback_message.length());
					System.out.println("Callback execution message: " + callback_message_updated);
					if (callback_message.contains(
							"Novalnet callback received. Refund/Bookback executed successfully for the TID:")) {
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
									"TC PASSED: REFUND_BY_BANK_TRANSFER_EU execution message and back end order note message text was matched successfully");
							test.log(Status.PASS,
									"TC PASSED: REFUND_BY_BANK_TRANSFER_EU execution message and back end order note message text was matched successfully");
						} else {
							System.out.println(
									"TC FAILED: REFUND_BY_BANK_TRANSFER_EU execution message and back end order note message text was not matched successfully");
							test.log(Status.FAIL,
									"TC PASSED: REFUND_BY_BANK_TRANSFER_EU execution and back end order note message text was not matched successfully");
						}
					} else {
						System.out.println("ERROR: Callback response message is displayed wrongly");
						test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
					}
				} else {
					System.out.println("ERROR: Order was not placed successfully using Invoice");
					test.log(Status.ERROR, "ERROR: Order was not placed successfully using Invoice");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'refundByBankTransferEu' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'refundByBankTransferEu' method");
		}
	}

	// Method used to execute 'INVOICE_START' onhold to confirm
	@Test
	public void invoiceOnholdToConfirm() throws Exception {
		try {
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or not
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			test = extend.createTest("Vendor script execution for 'INVOICE_START' onhold to confirm");
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Invoice_Payment_Display.click();
			if (!element.Invoice_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Invoice_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Read the Order completion Status
			String InvoiceOrderCompletionStatus = element.Invoice_Order_Completion_Status_Selectbox.getText();
			Thread.sleep(3000);
			// On-hold enabled
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Invoice_Onhold_Payment_Action_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Authorize", Keys.DOWN, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Invoice_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Check whether payment method is displayed in checkout page
			if (element.Invoice_Label.isDisplayed() == true) {
				if (element.Invoice_Radio_Button.isDisplayed()) {
					element.Invoice_Radio_Button.click();
				}
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Invoice");
					test.log(Status.INFO, "Order placed successfully using Invoice");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					Thread.sleep(5000);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String tid_status_value = element.Status_Code.getText();
					int tid_status = Integer.parseInt(tid_status_value);
					// Check whether the tid status (status code = 91)
					if (tid_status == 91) {
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
						selectpaymenttype.selectByVisibleText("INVOICE_START");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
						element.Tid_Payment.clear();
						element.Amount.clear();
						element.Amount.sendKeys(totalOrderAmount);
						element.Currency.clear();
						element.Currency.sendKeys("EUR");
						element.Status.clear();
						element.Status.sendKeys("100");
						element.Tid_Status.clear();
						element.Tid_Status.sendKeys("100");
						element.Tid.clear();
						element.Tid.sendKeys(TID);
						element.Execute_Button.click();
						String callback_message = element.callback_message.getText();
						String callback_message_updated = callback_message.substring(9, callback_message.length());
						System.out.println("callback message: " + callback_message_updated);
						if (callback_message
								.contains("Novalnet callback received. The transaction has been confirmed")) {
							Thread.sleep(2000);
							driver.navigate().to(Constant.shopbackendurl);
							Thread.sleep(1000);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(1500);
							element.WooCommerce_Orders.click();
							String BEOrderStatus = element.Backend_Order_Status.getText();
							element.Backend_Order_Number.click();
							String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
							System.out.println("Back end order note: " + BEOrderNotesMessage);
							// Verify the callback execution message is updated in the order note
							if (callback_message_updated.equals(BEOrderNotesMessage)) {
								System.out.println(
										"TC PASSED: INVOICE_START execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: INVOICE_START execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: INVOICE_START execution message and back end order note message text was not matched");
								test.log(Status.FAIL,
										"TC FAILED: INVOICE_START execution message and back end order note message text was not matched");
							}
							// Verify order completion status is updated in shop back end after the execution
							System.out.println("Invoice order completion status: " + InvoiceOrderCompletionStatus);
							System.out.println("Back end order status: " + BEOrderStatus);
							if (InvoiceOrderCompletionStatus.equals(BEOrderStatus)) {
								System.out.println(
										"TC PASSED: Invoice order completion status is updated successfully in shop back end");
								test.log(Status.PASS,
										"TC PASSED: Invoice order completion status is updated successfully in shop back end");
							} else {
								System.out.println(
										"TC FAILED:Invoice order completion status is not updated in shop back end");
								test.log(Status.FAIL,
										"TC FAILED: Invoice order completion status is not updated in shop back end");
							}
							// Verify order completion status is updated in front after the execution
							driver.navigate().to(Constant.shopfrontendurl);
							Thread.sleep(1000);
							element.MyAccount_Menu.click();
							element.MyAccount_Orders.click();
							String FEOrderStatus = element.Frontend_Order_Status.getText();
							System.out.println("Front end order status: " + FEOrderStatus);
							if (InvoiceOrderCompletionStatus.equals(FEOrderStatus)) {
								System.out.println(
										"TC PASSED: Invoice order completion status is updated successfully in shop front end");
								test.log(Status.PASS,
										"TC PASSED: Invoice order completion status is updated successfully in shop front end");
							} else {
								System.out.println(
										"TC FAILED: Invoice order completion status is not updated in shop front end");
								test.log(Status.FAIL,
										"TC FAILED: Invoice order completion status is not updated in shop front end");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println("TC FAILED: Transaction is already confirmed or not in the pending status");
						test.log(Status.FAIL,
								"TC FAILED: Transaction is already confirmed or not in the pending status");
					}

				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Invoice");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Invoice");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'invoiceOnholdToConfirm' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'invoiceOnholdToConfirm' method");
		}
	}
}