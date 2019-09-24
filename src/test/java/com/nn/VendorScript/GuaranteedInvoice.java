/* ******************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute vendor script for Guaranteed Invoice
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 ********************************************************************************/

package com.nn.VendorScript;

import java.util.HashMap;
import java.util.Map;
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

public class GuaranteedInvoice extends TestInputData {
	static TestInputData Data = new TestInputData();

	// Method used to execute 'GUARANTEED_INVOICE' for B2C customer
	@Test
	public void guaranteedInvoiceB2C() throws Exception {
		try {
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or not
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			test = extend.createTest("Vendor script execution for 'GUARANTEED_INVOICE' for B2C customer");
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
				Thread.sleep(3000);
			}
			// Checking Guarantee payment enabled or disabled
			if (!element.Invoice_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Invoice_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			// Checking Force Non Guarantee payment enabled or disabled
			if (element.Invoice_Force_Non_Guarantee_Checkbox.isSelected()) {
				element.Invoice_Force_Non_Guarantee_Checkbox.click();
				Thread.sleep(3000);
			}
			element.Invoice_Minimum_Order_Amount_Textbox.clear();
			// Enable 'Authorize'
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Invoice_Onhold_Payment_Action_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Authorize", Keys.DOWN, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			String PaymentActionValue = onhold.getText();
			element.Invoice_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(3000);
			// Read excel data
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_GuarateeB2CCustomerDetails();
			element.Checkout_Page_FirstName_TextBox.clear();
			element.Checkout_Page_FirstName_TextBox.sendKeys(UserData.get("Firstname"));
			element.Checkout_Page_LastName_TextBox.clear();
			element.Checkout_Page_LastName_TextBox.sendKeys(UserData.get("Lastname"));
			element.Checkout_Page_Country_Dropdown.click();
			element.Checkout_Page_Country_Dropdown_Textbox.sendKeys("Germany", Keys.ENTER);
			element.Checkout_Page_StreetAddress_TextBox1.clear();
			element.Checkout_Page_StreetAddress_TextBox1.sendKeys(UserData.get("StreetAddress"));
			element.Checkout_Page_Postcode_Zip_TextBox.clear();
			element.Checkout_Page_Postcode_Zip_TextBox.sendKeys(UserData.get("ZIP"));
			element.Checkout_Page_Town_city_Textbox.clear();
			element.Checkout_Page_Town_city_Textbox.sendKeys(UserData.get("City"));
			element.Email_Address_TextBox.clear();
			element.Email_Address_TextBox.sendKeys(UserData.get("Email"));
			Thread.sleep(3000);
			if (element.Invoice_Label.isDisplayed()) {
				if (element.Invoice_Radio_Button.isDisplayed() == true) {
					element.Invoice_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Invoice_DOB_Textbox.clear();
				element.Invoice_DOB_Textbox.sendKeys(UserData.get("DOB"));
				element.Place_Order.click();
				Thread.sleep(50000);
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {			
					Thread.sleep(3000);
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					System.out.println("TID:" + TID);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String StoreTheStatusCode = element.Status_Code.getText();
					String PaymentName = element.Cardportal_Payment_Name.getText();
					// Checking the payment action, order status code 91 and payment name
					if ((PaymentActionValue.equals("Authorize")) && (StoreTheStatusCode.equals("91"))
							&& ((PaymentName.trim()).equals("Rechnung mit Zahlungsgarantie"))) {
						System.out.println(
								"Order placed successfully using Guaranteed Invoice and the transaction yet to be confirmed");
						test.log(Status.INFO,
								"Order placed successfully using Guaranteed Invoice and the transaction yet to be confirmed");
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
						selectpaymenttype.selectByVisibleText("GUARANTEED_INVOICE");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
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
						System.out.println("Callback execution message: " + callback_message_updated);
						if (callback_message
								.contains("Novalnet callback received. The transaction has been confirmed on")) {
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
										"TC PASSED: GUARANTEED_INVOICE execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: GUARANTEED_INVOICE execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: GUARANTEED_INVOICE execution message and back end order note message text was not matched successfully");
								test.log(Status.FAIL,
										"TC PASSED: GUARANTEED_INVOICE execution and back end order note message text was not matched successfully");
							}

							// Verify invoice callback order status is updated in shop back end after the
							// execution
							System.out.println("Invoice callback order status: " + InvoiceCallbackOrderStatus);
							System.out.println("Back end order status: " + BEOrderStatus);
							if (InvoiceCallbackOrderStatus.equals(BEOrderStatus)) {
								System.out.println(
										"TC PASSED: Invoice callback order status is updated successfully in shop back end");
								test.log(Status.PASS,
										"TC PASSED: Invoice callback order status is updated successfully in shop back end");
							} else {
								System.out
										.println("TC FAILED: Invoice callback order status is not updated in shop back end");
								test.log(Status.FAIL,
										"TC FAILED: Invoice callback order status is not updated in shop back end");
							}
							// Verify invoice callback order status is updated in front after the execution
							driver.navigate().to(Constant.shopfrontendurl);
							Thread.sleep(1000);
							element.MyAccount_Menu.click();
							element.MyAccount_Orders.click();
							String FEOrderStatus = element.Frontend_Order_Status.getText();
							System.out.println("Front end order status: " + FEOrderStatus);
							if (InvoiceCallbackOrderStatus.equals(FEOrderStatus)) {
								System.out.println(
										"TC PASSED: Invoice callback order status is updated successfully in shop front end");
								test.log(Status.PASS,
										"TC PASSED: Invoice callback order status is updated successfully in shop front end");
							} else {
								System.out.println(
										"TC FAILED: Invoice callback order status is not updated in shop front end");
								test.log(Status.FAIL,
										"TC FAILED: Invoice callback order status is not updated in shop front end");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println(
								"TC FAILED: Incorrect payment type or the transaction is not in pending status");
						test.log(Status.FAIL,
								"TC FAILED: Incorrect payment type or the transaction is not in pending status");
					}
				} else {
					System.out.println("ERROR: There was an error in the order success page");
					test.log(Status.ERROR, "ERROR: There was an error in the order success page");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'guaranteedInvoiceB2C' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'guaranteedInvoiceB2C' method");
		}
	}

	// Method used to execute 'GUARANTEED_INVOICE' for on-hold test data
	@Test
	public void guaranteedInvoiceWithOnholdTestdata() throws Exception {
		try {
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or not
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			test = extend.createTest("Vendor script execution for 'GUARANTEED_INVOICE' for on-hold test data");
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
				Thread.sleep(3000);
			}
			// Checking Guarantee payment enabled or disabled
			if (!element.Invoice_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Invoice_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			// Checking Force Non Guarantee payment enabled or disabled
			if (element.Invoice_Force_Non_Guarantee_Checkbox.isSelected()) {
				element.Invoice_Force_Non_Guarantee_Checkbox.click();
				Thread.sleep(3000);
			}
			element.Invoice_Minimum_Order_Amount_Textbox.clear();
			element.Invoice_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			// Read excel data
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_GuarateeCustomerDetails();
			element.Checkout_Page_FirstName_TextBox.clear();
			element.Checkout_Page_FirstName_TextBox.sendKeys(UserData.get("Firstname"));
			element.Checkout_Page_LastName_TextBox.clear();
			element.Checkout_Page_LastName_TextBox.sendKeys(UserData.get("Lastname"));
			element.Checkout_Page_Country_Dropdown.click();
			element.Checkout_Page_Country_Dropdown_Textbox.sendKeys("Germany", Keys.ENTER);
			element.Checkout_Page_StreetAddress_TextBox1.clear();
			element.Checkout_Page_StreetAddress_TextBox1.sendKeys(UserData.get("StreetAddress"));
			element.Checkout_Page_Postcode_Zip_TextBox.clear();
			element.Checkout_Page_Postcode_Zip_TextBox.sendKeys(UserData.get("ZIP"));
			element.Checkout_Page_Town_city_Textbox.clear();
			element.Checkout_Page_Town_city_Textbox.sendKeys(UserData.get("City"));
			element.Email_Address_TextBox.clear();
			element.Email_Address_TextBox.sendKeys(UserData.get("Email"));
			Thread.sleep(3000);
			if (element.Invoice_Label.isDisplayed()) {
				if (element.Invoice_Radio_Button.isDisplayed() == true) {
					element.Invoice_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Invoice_DOB_Textbox.clear();
				element.Invoice_DOB_Textbox.sendKeys(UserData.get("DOB"));
				element.Place_Order.click();
				Thread.sleep(50000);
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {					
					Thread.sleep(3000);
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					String TID_new = TID.substring(0, TID.length() - 2);
					System.out.println("TID:" + TID_new);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					element.Cardportal_TID_Textbox.sendKeys(TID_new);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String StoreTheStatusCode = element.Status_Code.getText();
					String PaymentName = element.Cardportal_Payment_Name.getText();
					// Checking the order status code 75 and payment name
					if ((StoreTheStatusCode.equals("75"))
							&& ((PaymentName.trim()).equals("Rechnung mit Zahlungsgarantie"))) {
						System.out.println(
								"Order placed successfully using Guaranteed Invoice and the transaction yet to be confirmed by the partner");
						test.log(Status.INFO,
								"Order placed successfully using Guaranteed Invoice and the transaction yet to be confirmed by the partner");
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
						selectpaymenttype.selectByVisibleText("GUARANTEED_INVOICE");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
						element.Amount.clear();
						element.Amount.sendKeys(totalOrderAmount);
						element.Currency.clear();
						element.Currency.sendKeys("EUR");
						element.Status.clear();
						element.Status.sendKeys("100");
						element.Tid_Status.clear();
						element.Tid_Status.sendKeys("100");
						element.Tid.clear();
						element.Tid.sendKeys(TID_new);
						element.Execute_Button.click();
						String callback_message = element.callback_message.getText();
						String callback_message_updated = callback_message.substring(9, callback_message.length());
						System.out.println("Callback execution message: " + callback_message_updated);
						if (callback_message
								.contains("Novalnet callback received. The transaction has been confirmed on")) {
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
										"TC PASSED: GUARANTEED_INVOICE execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: GUARANTEED_INVOICE execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: GUARANTEED_INVOICE execution message and back end order note message text was not matched successfully");
								test.log(Status.FAIL,
										"TC PASSED: GUARANTEED_INVOICE execution and back end order note message text was not matched successfully");
							}
							// Verify invoice callback order status is updated in shop back end after the
							// execution
							System.out.println("Invoice callback order status: " + InvoiceCallbackOrderStatus);
							System.out.println("Back end order status: " + BEOrderStatus);
							if (InvoiceCallbackOrderStatus.equals(BEOrderStatus)) {
								System.out.println(
										"TC PASSED: Invoice callback order status is updated successfully in shop back end");
								test.log(Status.PASS,
										"TC PASSED: Invoice callback order status is updated successfully in shop back end");
							} else {
								System.out
										.println("TC FAILED: Invoice callback order status is not updated in shop back end");
								test.log(Status.FAIL,
										"TC FAILED: Invoice callback order status is not updated in shop back end");
							}
							// Verify invoice callback order status is updated in front after the execution
							driver.navigate().to(Constant.shopfrontendurl);
							Thread.sleep(1000);
							element.MyAccount_Menu.click();
							element.MyAccount_Orders.click();
							String FEOrderStatus = element.Frontend_Order_Status.getText();
							System.out.println("Front end order status: " + FEOrderStatus);
							if (InvoiceCallbackOrderStatus.equals(FEOrderStatus)) {
								System.out.println(
										"TC PASSED: Invoice callback order status is updated successfully in shop front end");
								test.log(Status.PASS,
										"TC PASSED: Invoice callback order status is updated successfully in shop front end");
							} else {
								System.out.println(
										"TC FAILED: Invoice callback order status is not updated in shop front end");
								test.log(Status.FAIL,
										"TC FAILED: Invoice callback order status is not updated in shop front end");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println(
								"TC FAILED: Incorrect payment type or the transaction is not in pending status");
						test.log(Status.FAIL,
								"TC FAILED: Incorrect payment type or the transaction is not in pending status");
					}
				} else {
					System.out.println("ERROR: There was an error in the order success page");
					test.log(Status.ERROR, "ERROR: There was an error in the order success page");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'guaranteedInvoiceWithOnholdTestdata' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'guaranteedInvoiceWithOnholdTestdata' method");
		}
	}

	// Method used to execute 'TRANSACTION_CANCELLATION' for B2C customer
	@Test
	public void guaranteedInvoiceB2CCancel() throws Exception {
		try {
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or not
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			test = extend.createTest(
					"Vendor script execution for TRANSACTION_CANCELLATION for Guaranteed Invoice B2C customer");
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Novalnet_Global_Config_Tab.click();
			Thread.sleep(2000);
			String CancellationOrderStatus = element.Cancellation_Order_Status.getText();
			Thread.sleep(4000);
			// Go to Payments Tab
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Invoice_Payment_Display.click();
			if (!element.Invoice_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Invoice_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Checking Guarantee payment enabled or disabled
			if (!element.Invoice_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Invoice_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			// Checking Force Non Guarantee payment enabled or disabled
			if (element.Invoice_Force_Non_Guarantee_Checkbox.isSelected()) {
				element.Invoice_Force_Non_Guarantee_Checkbox.click();
				Thread.sleep(3000);
			}
			element.Invoice_Minimum_Order_Amount_Textbox.clear();
			// Enable 'Authorize'
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Invoice_Onhold_Payment_Action_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Authorize", Keys.DOWN, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			String PaymentActionValue = onhold.getText();
			element.Invoice_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(3000);
			// Read excel data
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_GuarateeB2CCustomerDetails();
			element.Checkout_Page_FirstName_TextBox.clear();
			element.Checkout_Page_FirstName_TextBox.sendKeys(UserData.get("Firstname"));
			element.Checkout_Page_LastName_TextBox.clear();
			element.Checkout_Page_LastName_TextBox.sendKeys(UserData.get("Lastname"));
			element.Checkout_Page_Country_Dropdown.click();
			element.Checkout_Page_Country_Dropdown_Textbox.sendKeys("Germany", Keys.ENTER);
			element.Checkout_Page_StreetAddress_TextBox1.clear();
			element.Checkout_Page_StreetAddress_TextBox1.sendKeys(UserData.get("StreetAddress"));
			element.Checkout_Page_Postcode_Zip_TextBox.clear();
			element.Checkout_Page_Postcode_Zip_TextBox.sendKeys(UserData.get("ZIP"));
			element.Checkout_Page_Town_city_Textbox.clear();
			element.Checkout_Page_Town_city_Textbox.sendKeys(UserData.get("City"));
			element.Email_Address_TextBox.clear();
			element.Email_Address_TextBox.sendKeys(UserData.get("Email"));
			Thread.sleep(3000);
			if (element.Invoice_Label.isDisplayed()) {
				if (element.Invoice_Radio_Button.isDisplayed() == true) {
					element.Invoice_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Invoice_DOB_Textbox.clear();
				element.Invoice_DOB_Textbox.sendKeys(UserData.get("DOB"));
				element.Place_Order.click();
				Thread.sleep(50000);
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {					
					Thread.sleep(3000);
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					System.out.println("TID:" + TID);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String StoreTheStatusCode = element.Status_Code.getText();
					String PaymentName = element.Cardportal_Payment_Name.getText();
					// Checking the payment action, order status code 91 and payment name
					if ((PaymentActionValue.equals("Authorize")) && (StoreTheStatusCode.equals("91"))
							&& ((PaymentName.trim()).equals("Rechnung mit Zahlungsgarantie"))) {
						System.out.println(
								"Order placed successfully using Guaranteed Invoice and the transaction yet to be confirmed");
						test.log(Status.INFO,
								"Order placed successfully using Guaranteed Invoice and the transaction yet to be confirmed");
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
						selectpaymenttype.selectByVisibleText("TRANSACTION_CANCELLATION");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
						element.Amount.clear();
						element.Amount.sendKeys(totalOrderAmount);
						element.Currency.clear();
						element.Currency.sendKeys("EUR");
						element.Status.clear();
						element.Status.sendKeys("103");
						element.Tid_Status.clear();
						element.Tid_Status.sendKeys("103");
						element.Tid.clear();
						element.Tid.sendKeys(TID);
						element.Execute_Button.click();
						String callback_message = element.callback_message.getText();
						String callback_message_updated = callback_message.substring(9, callback_message.length());
						System.out.println("Callback execution message: " + callback_message_updated);
						if (callback_message
								.contains("Novalnet callback received. The transaction has been canceled on")) {
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
										"TC PASSED: TRANSACTION_CANCELLATION execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: TRANSACTION_CANCELLATION execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: TRANSACTION_CANCELLATION execution message and back end order note message text was not matched successfully");
								test.log(Status.FAIL,
										"TC FAILED: TRANSACTION_CANCELLATION execution and back end order note message text was not matched successfully");
							}
							// Verify cancellation order status is updated in shop back end after the execution
							System.out.println("Cancellation order status: " + CancellationOrderStatus);
							System.out.println("Back end order status: " + BEOrderStatus);
							if (CancellationOrderStatus.equals(BEOrderStatus)) {
								System.out.println(
										"TC PASSED: Cancellation order status is updated successfully in shop back end");
								test.log(Status.PASS,
										"TC PASSED: Cancellation order status is updated successfully in shop back end");
							} else {
								System.out.println("TC FAILED: Cancellation order status is not updated in shop back end");
								test.log(Status.FAIL,
										"TC FAILED: Cancellation order status is not updated in shop back end");
							}
							// Verify Cancellation order status is updated in front after the execution
							driver.navigate().to(Constant.shopfrontendurl);
							Thread.sleep(1000);
							element.MyAccount_Menu.click();
							element.MyAccount_Orders.click();
							String FEOrderStatus = element.Frontend_Order_Status.getText();
							System.out.println("Front end order status: " + FEOrderStatus);
							if (CancellationOrderStatus.equals(FEOrderStatus)) {
								System.out.println(
										"TC PASSED: Cancellation order status is updated successfully in shop front end");
								test.log(Status.PASS,
										"TC PASSED: Cancellation order status is updated successfully in shop front end");
							} else {
								System.out.println("TC FAILED: Cancellation order status is not updated in shop front end");
								test.log(Status.FAIL,
										"TC FAILED: Cancellation order status is not updated in shop front end");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println(
								"TC FAILED: Incorrect payment type or the transaction is not in pending status");
						test.log(Status.FAIL,
								"TC FAILED: Incorrect payment type or the transaction is not in pending status");
					}
				} else {
					System.out.println("ERROR: There was an error in the order success page");
					test.log(Status.ERROR, "ERROR: There was an error in the order success page");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'guaranteedInvoiceB2CCancel' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'guaranteedInvoiceB2CCancel' method");
		}
	}

	// Method used to execute 'TRANSACTION_CANCELLATION' for on-hold test data
	@Test
	public void guaranteedInvoiceWithOnholdTestdataCancel() throws Exception {
		try {
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or not
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			test = extend.createTest(
					"Vendor script execution for 'TRANSACTION_CANCELLATION' for Guaranteed Invoice on-hold test data");
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Novalnet_Global_Config_Tab.click();
			Thread.sleep(2000);
			String CancellationOrderStatus = element.Cancellation_Order_Status.getText();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Invoice_Payment_Display.click();
			// String PaymentActionValue =
			// element.Invoice_Onhold_Payment_Action_Selectbox.getText();
			if (!element.Invoice_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Invoice_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Checking Guarantee payment enabled or disabled
			if (!element.Invoice_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Invoice_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			// Checking Force Non Guarantee payment enabled or disabled
			if (element.Invoice_Force_Non_Guarantee_Checkbox.isSelected()) {
				element.Invoice_Force_Non_Guarantee_Checkbox.click();
				Thread.sleep(3000);
			}
			element.Invoice_Minimum_Order_Amount_Textbox.clear();
			element.Invoice_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			// Read excel data
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_GuarateeCustomerDetails();
			element.Checkout_Page_FirstName_TextBox.clear();
			element.Checkout_Page_FirstName_TextBox.sendKeys(UserData.get("Firstname"));
			element.Checkout_Page_LastName_TextBox.clear();
			element.Checkout_Page_LastName_TextBox.sendKeys(UserData.get("Lastname"));
			element.Checkout_Page_Country_Dropdown.click();
			element.Checkout_Page_Country_Dropdown_Textbox.sendKeys("Germany", Keys.ENTER);
			element.Checkout_Page_StreetAddress_TextBox1.clear();
			element.Checkout_Page_StreetAddress_TextBox1.sendKeys(UserData.get("StreetAddress"));
			element.Checkout_Page_Postcode_Zip_TextBox.clear();
			element.Checkout_Page_Postcode_Zip_TextBox.sendKeys(UserData.get("ZIP"));
			element.Checkout_Page_Town_city_Textbox.clear();
			element.Checkout_Page_Town_city_Textbox.sendKeys(UserData.get("City"));
			element.Email_Address_TextBox.clear();
			element.Email_Address_TextBox.sendKeys(UserData.get("Email"));
			Thread.sleep(3000);
			if (element.Invoice_Label.isDisplayed()) {
				if (element.Invoice_Radio_Button.isDisplayed() == true) {
					element.Invoice_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Invoice_DOB_Textbox.clear();
				element.Invoice_DOB_Textbox.sendKeys(UserData.get("DOB"));
				element.Place_Order.click();
				Thread.sleep(50000);
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {					
					Thread.sleep(3000);
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					String TID_new = TID.substring(0, TID.length() - 2);
					System.out.println("TID:" + TID_new);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					element.Cardportal_TID_Textbox.sendKeys(TID_new);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String StoreTheStatusCode = element.Status_Code.getText();
					String PaymentName = element.Cardportal_Payment_Name.getText();
					// Checking the order status code 75 and payment name
					if ((StoreTheStatusCode.equals("75"))
							&& ((PaymentName.trim()).equals("Rechnung mit Zahlungsgarantie"))) {
						System.out.println(
								"Order placed successfully using Guaranteed Invoice and the transaction yet to be confirmed by the partner");
						test.log(Status.INFO,
								"Order placed successfully using Guaranteed Invoice and the transaction yet to be confirmed by the partner");
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
						selectpaymenttype.selectByVisibleText("TRANSACTION_CANCELLATION");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
						element.Amount.clear();
						element.Amount.sendKeys(totalOrderAmount);
						element.Currency.clear();
						element.Currency.sendKeys("EUR");
						element.Status.clear();
						element.Status.sendKeys("103");
						element.Tid_Status.clear();
						element.Tid_Status.sendKeys("103");
						element.Tid.clear();
						element.Tid.sendKeys(TID_new);
						element.Execute_Button.click();
						String callback_message = element.callback_message.getText();
						String callback_message_updated = callback_message.substring(9, callback_message.length());
						System.out.println("Callback execution message: " + callback_message_updated);
						if (callback_message
								.contains("Novalnet callback received. The transaction has been canceled on")) {
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
										"TC PASSED: TRANSACTION_CANCELLATION execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: TRANSACTION_CANCELLATION execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: TRANSACTION_CANCELLATION execution message and back end order note message text was not matched successfully");
								test.log(Status.FAIL,
										"TC FAILED: TRANSACTION_CANCELLATION execution and back end order note message text was not matched successfully");
							}
							// Verify cancellation order status is updated in shop back end after the execution
							System.out.println("Cancellation order status: " + CancellationOrderStatus);
							System.out.println("Back end order status: " + BEOrderStatus);
							if (CancellationOrderStatus.equals(BEOrderStatus)) {
								System.out.println(
										"TC PASSED: Cancellation order status is updated successfully in shop back end");
								test.log(Status.PASS,
										"TC PASSED: Cancellation order status is updated successfully in shop back end");
							} else {
								System.out.println("TC FAILED: Cancellation order status is not updated in shop back end");
								test.log(Status.FAIL,
										"TC FAILED: Cancellation order status is not updated in shop back end");
							}
							// Verify cancellation order status is updated in front after the execution
							driver.navigate().to(Constant.shopfrontendurl);
							Thread.sleep(1000);
							element.MyAccount_Menu.click();
							element.MyAccount_Orders.click();
							String FEOrderStatus = element.Frontend_Order_Status.getText();
							System.out.println("Front end order status: " + FEOrderStatus);
							if (CancellationOrderStatus.equals(FEOrderStatus)) {
								System.out.println(
										"TC PASSED: Cancellation order status is updated successfully in shop front end");
								test.log(Status.PASS,
										"TC PASSED: Cancellation order status is updated successfully in shop front end");
							} else {
								System.out.println("TC FAILED: Cancellation order status is not updated in shop front end");
								test.log(Status.FAIL,
										"TC FAILED: Cancellation order status is not updated in shop front end");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println(
								"TC FAILED: Incorrect payment type or the transaction is not in pending status");
						test.log(Status.FAIL,
								"TC FAILED: Incorrect payment type or the transaction is not in pending status");
					}
				} else {
					System.out.println("ERROR: There was an error in the order success page");
					test.log(Status.ERROR, "ERROR: There was an error in the order success page");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'guaranteedInvoiceWithOnholdTestdataCancel' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'guaranteedInvoiceWithOnholdTestdataCancel' method");
		}
	}

	// Method used to execute 'GUARANTEED_INVOICE_BOOKBACK'
	@Test
	public void guaranteedInvoiceBookback() throws Exception {
		try {
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or not
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			test = extend.createTest("Vendor script execution for 'GUARANTEED_INVOICE_BOOKBACK'");
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
			// Checking Guarantee payment enabled or disabled
			if (!element.Invoice_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Invoice_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			// Checking Force Non Guarantee payment enabled or disabled
			if (element.Invoice_Force_Non_Guarantee_Checkbox.isSelected()) {
				element.Invoice_Force_Non_Guarantee_Checkbox.click();
				Thread.sleep(3000);
			}
			element.Invoice_Minimum_Order_Amount_Textbox.clear();
			// Enable 'Capture'
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Invoice_Onhold_Payment_Action_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Capture", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Invoice_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(3000);
			// Read excel data
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_GuarateeB2CCustomerDetails();
			element.Checkout_Page_FirstName_TextBox.clear();
			element.Checkout_Page_FirstName_TextBox.sendKeys(UserData.get("Firstname"));
			element.Checkout_Page_LastName_TextBox.clear();
			element.Checkout_Page_LastName_TextBox.sendKeys(UserData.get("Lastname"));
			element.Checkout_Page_Country_Dropdown.click();
			element.Checkout_Page_Country_Dropdown_Textbox.sendKeys("Germany", Keys.ENTER);
			element.Checkout_Page_StreetAddress_TextBox1.clear();
			element.Checkout_Page_StreetAddress_TextBox1.sendKeys(UserData.get("StreetAddress"));
			element.Checkout_Page_Postcode_Zip_TextBox.clear();
			element.Checkout_Page_Postcode_Zip_TextBox.sendKeys(UserData.get("ZIP"));
			element.Checkout_Page_Town_city_Textbox.clear();
			element.Checkout_Page_Town_city_Textbox.sendKeys(UserData.get("City"));
			element.Email_Address_TextBox.clear();
			element.Email_Address_TextBox.sendKeys(UserData.get("Email"));
			Thread.sleep(3000);
			if (element.Invoice_Label.isDisplayed()) {
				if (element.Invoice_Radio_Button.isDisplayed() == true) {
					element.Invoice_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Invoice_DOB_Textbox.clear();
				element.Invoice_DOB_Textbox.sendKeys(UserData.get("DOB"));
				element.Place_Order.click();
				Thread.sleep(50000);
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {					
					Thread.sleep(3000);
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					System.out.println("TID:" + TID);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String StoreTheStatusCode = element.Status_Code.getText();
					String PaymentName = element.Cardportal_Payment_Name.getText();
					// Checking the order status code 100 and payment name
					if ((StoreTheStatusCode.equals("100"))
							&& ((PaymentName.trim()).equals("Rechnung mit Zahlungsgarantie"))) {
						System.out.println(
								"Order placed successfully using Guaranteed Invoice and the payment is made");
						test.log(Status.INFO,
								"Order placed successfully using Guaranteed Invoice and the payment is made");
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
						selectpaymenttype.selectByVisibleText("GUARANTEED_INVOICE_BOOKBACK");
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
						System.out.println("callback execution message: " + callback_message_updated);
						if (callback_message.contains(
								"Novalnet callback received. Refund/Bookback executed successfully for the TID:")) {
							Thread.sleep(2000);
							driver.navigate().to(Constant.shopbackendurl);
							Thread.sleep(1000);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(1500);
							element.WooCommerce_Orders.click();
							element.Backend_Order_Number.click();
							String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
							System.out.println("Back end order note: " + BEOrderNotesMessage);
							if (callback_message_updated.equals(BEOrderNotesMessage)) {
								System.out.println(
										"TC PASSED: GUARANTEED_INVOICE_BOOKBACK execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: GUARANTEED_INVOICE_BOOKBACK execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: GUARANTEED_INVOICE_BOOKBACK execution message and back end order note message text was not matched");
								test.log(Status.FAIL,
										"TC FAILED: GUARANTEED_INVOICE_BOOKBACK execution message and back end order note message text was not matched");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println("TC FAILED: Incorrect payment type or the payment is not made");
						test.log(Status.FAIL, "TC FAILED: Incorrect payment type or the payment is not made");
					}
				} else {
					System.out.println("ERROR: There was an error in the order success page");
					test.log(Status.ERROR, "ERROR: There was an error in the order success page");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'guaranteedInvoiceBookback' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'guaranteedInvoiceBookback' method");
		}
	}
}
