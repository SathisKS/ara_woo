/* *************************************************************************
 * Project Name: Seamless Payment Form
 * Description:  This file used to store the WebElement
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 ***************************************************************************/

package com.nn.Repository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class ElementLocators {

	// Shop Backend Login WebElement

	@FindBy(id = "user_login")
	@CacheLookup
	public WebElement Username;

	@FindBy(id = "user_pass")
	@CacheLookup
	public WebElement Password;

	@FindBy(id = "wp-submit")
	@CacheLookup
	public WebElement Login_Button;

	// ***************************************************************************

	// Shop Backend WebElement
	@FindAll({ @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/ul/li[8]/a/div[3]"),
			@FindBy(css = "a.menu-icon-generic > div:nth-child(3)") })
	@CacheLookup
	public WebElement Woocommerce_Tab;

	@FindAll({ @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/ul/li[8]/ul/li[7]/a"), @FindBy(css = "a.current") })
	@CacheLookup
	public WebElement Settings_Tab;

	@FindAll({ @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/nav/a[4]"),
			@FindBy(css = "a.nav-tab:nth-child(4)") })
	//@CacheLookup
	public WebElement Payment_Tab;

	// ***************************************************************************

	// Novalnet Global Configuration WebElement
	/* @FindAll({ @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/nav/a[10]"),
			@FindBy(css = "a.nav-tab:nth-child(10)") })
	@CacheLookup
	public WebElement Novalnet_Global_Config_Tab;*/

	@FindAll({ @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2[1]"),
			@FindBy(css = "#mainform > h2:nth-child(5)") })
	@CacheLookup
	public WebElement Novalnet_Global_Config_Title;

	@FindAll({ @FindBy(css = "#novalnet_global_settings-description > p:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/div[3]/p") })
	@CacheLookup
	public WebElement Novalnet_Global_Config_Description;

	// Product Activation Key WebElement
	@FindAll({
			@FindBy(css = "table.form-table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Product_Activation_Key_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[1]/th/label/span") })
	@CacheLookup
	public WebElement Product_Activation_Key_Help_Tip;

	@FindBy(id = "novalnet_public_key")
	@CacheLookup
	public WebElement Product_Activation_Key;

	@FindAll({ @FindBy(css = "span.description:nth-child(2)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[1]/td/span") })
	@CacheLookup
	public WebElement Product_Activation_Key_Description;

	// Tariff ID WebElement
	@FindAll({
			@FindBy(css = "html.wp-toolbar body.wp-admin.wp-core-ui.js.woocommerce_page_wc-settings.auto-fold.admin-bar.branch-5-2.version-5-2-2.admin-color-fresh.locale-en-us.customize-support.svg div#wpwrap div#wpcontent div#wpbody div#wpbody-content div.wrap.woocommerce form#mainform table.form-table tbody tr th.titledesc label"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Tariff_ID_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[5]/th/label/span") })
	@CacheLookup
	public WebElement Tariff_ID_Help_Tip;

	@FindBy(id = "select2-novalnet_tariff_id-container")
	public WebElement Tariff_ID_Selectbox;

	// Payment Method Logo WebElement
	@FindAll({
			@FindBy(css = "html.wp-toolbar body.wp-admin.wp-core-ui.js.woocommerce_page_wc-settings.auto-fold.admin-bar.branch-5-2.version-5-2-2.admin-color-fresh.locale-en-us.customize-support.svg div#wpwrap div#wpcontent div#wpbody div#wpbody-content div.wrap.woocommerce form#mainform table.form-table tbody tr th.titledesc label"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Display_Payment_Method_Logo_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[7]/th/label/span") })
	@CacheLookup
	public WebElement Display_Payment_Method_Logo_Help_Tip;

	@FindBy(id = "select2-novalnet_payment_logo-container")
	@CacheLookup
	public WebElement Display_Payment_Method_Logo;

	// Gateway Timeout WebElement
	@FindAll({
			@FindBy(css = "table.form-table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Gateway_Timeout_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[8]/th/label/span") })
	@CacheLookup
	public WebElement Gateway_Timeout_Help_Tip;

	@FindBy(id = "novalnet_gateway_timeout")
	@CacheLookup
	public WebElement Gateway_Timeout;

	// Debug WebElement
	@FindAll({
			@FindBy(css = "table.form-table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[9]/th/label") })
	@CacheLookup
	public WebElement Debug_Log_Label;

	@FindBy(id = "select2-novalnet_debug_log-container")
	@CacheLookup
	public WebElement Debug_Log;

	@FindAll({ @FindBy(css = "span.description:nth-child(3)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[9]/td/span[2]") })
	@CacheLookup
	public WebElement Debug_Log_Description;

	// Referrer ID WebElement
	@FindAll({
			@FindBy(css = "table.form-table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[10]/th/label") })
	@CacheLookup
	public WebElement Referrer_ID_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[10]/th/label/span") })
	@CacheLookup
	public WebElement Referrer_ID_Help_Tip;

	@FindBy(id = "novalnet_referrer_id")
	@CacheLookup
	public WebElement Referrer_ID;

	// Order status management for on-hold transaction(-s)
	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(8)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2[2]") })
	@CacheLookup
	public WebElement On_hold_Transaction_Title;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(9) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement On_hold_Order_Status_Label;

	@FindBy(id = "select2-novalnet_onhold_success_status-container")
	@CacheLookup
	public WebElement On_hold_Order_Status;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(9) > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Cancellation_Order_Status_Label;

	@FindBy(id = "select2-novalnet_onhold_cancel_status-container")
	@CacheLookup
	public WebElement Cancellation_Order_Status;

	// Dynamic subscription management
	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(10)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2[3]") })
	@CacheLookup
	public WebElement Dynamic_Subscription_Management_Title;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(11) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[3]/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Enable_Subscription_Label;

	@FindBy(id = "select2-novalnet_enable_subs-container")
	@CacheLookup
	public WebElement Enable_Subscription;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(11) > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[3]/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Subscription_Payments_Label;

	@FindAll({ @FindBy(css = ".select2-selection--multiple"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[3]/tbody/tr[2]/td/span/span[1]/span") })
	@CacheLookup
	public WebElement Subscription_Payments;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(11) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[3]/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Subscription_Tariff_ID_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(11) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[3]/tbody/tr[3]/th/label/span") })
	@CacheLookup
	public WebElement Subscription_Tariff_ID_Help_Tip;

	@FindBy(id = "select2-novalnet_subs_tariff_id-container")
	@CacheLookup
	public WebElement Subscription_Tariff_ID_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(11) > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[3]/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Display_Subscription_Cancel_Label;

	@FindBy(id = "select2-novalnet_usr_subcl-container")
	@CacheLookup
	public WebElement Display_Subscription_Cancel_Selectbox;

	// Merchant script management
	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(12)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2[4]") })
	@CacheLookup
	public WebElement Merchant_Script_Management_Title;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(13) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[4]/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Deactivate_IP_Address_Control_Title;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(13) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[4]/tbody/tr[1]/th/label/span") })
	@CacheLookup
	public WebElement Deactivate_IP_Address_Control_Help_Tip;

	@FindBy(id = "select2-novalnet_callback_test_mode-container")
	@CacheLookup
	public WebElement Deactivate_IP_Address_Control;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(13) > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[4]/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Enable_Callback_Email_Label;

	@FindBy(id = "select2-novalnet_callback_mail_send_option-container")
	@CacheLookup
	public WebElement Enable_Callback_Email;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(13) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[4]/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Notification_URL_Label;

	@FindBy(id = "novalnet_callback_notify_url")
	@CacheLookup
	public WebElement Notification_URL;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(13) > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[4]/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Email_Address_To_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(13) > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[4]/tbody/tr[4]/th/label/span") })
	@CacheLookup
	public WebElement Email_Address_To_Help_Tip;

	@FindBy(id = "novalnet_callback_emailtoaddr")
	@CacheLookup
	public WebElement Email_Address_To;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(13) > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[4]/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Email_Address_Bcc_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(13) > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[4]/tbody/tr[5]/th/label/span") })
	@CacheLookup
	public WebElement Email_Address_Bcc_Help_Tip;

	@FindBy(id = "novalnet_callback_emailbccaddr")
	@CacheLookup
	public WebElement Email_Address_Bcc;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p/button") })
	@CacheLookup
	public WebElement Novalnet_Global_Config_Save_Changes;

	// ***************************************************************************
	// Novalnet(Backend-Configuration)

	// Novalnet Creditcard WebElement
	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(5) > td:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[5]/td[2]/a") })
	@CacheLookup
	public WebElement Creditcard_Payment_Display;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(5) > td:nth-child(3) > a:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[5]/td[3]/a/span") })
	@CacheLookup
	public WebElement Creditcard_Payment_Enable_Button;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(5) > td:nth-child(6) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[5]/td[6]/a") })
	@CacheLookup
	public WebElement Creditcard_Payment_Manage;

	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2") })
	@CacheLookup
	public WebElement Creditcard_Payment_Page_Title;

	@FindAll({ @FindBy(css = ".emoji"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2/small/a/img") })
	@CacheLookup
	public WebElement Creditcard_Return_To_Payment_Button;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Enable_Payment_Method_Label;

	@FindBy(id = "woocommerce_novalnet_cc_enabled")
	@CacheLookup
	public WebElement Creditcard_Enable_Payment_Method_Checkbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Payment_Title_EN_Label;

	@FindBy(id = "woocommerce_novalnet_cc_title_en")
	@CacheLookup
	public WebElement Creditcard_Payment_Title_EN;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Payment_Desc_EN_Label;

	@FindBy(id = "woocommerce_novalnet_cc_description_en")
	@CacheLookup
	public WebElement Creditcard_Payment_Desc_EN;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_cc_title_de")
	@CacheLookup
	public WebElement Creditcard_Payment_Title_DE;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_cc_description_de")
	@CacheLookup
	public WebElement Creditcard_Payment_Desc_DE;

	@FindAll({ @FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[6]/th") })
	@CacheLookup
	public WebElement Creditcard_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[6]/th/label/span") })
	@CacheLookup
	public WebElement Creditcard_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_cc_test_mode-container")
	@CacheLookup
	public WebElement Creditcard_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Enable_3d_Secure_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[7]/th/label/span") })
	@CacheLookup
	public WebElement Creditcard_Enable_3d_Secure_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_cc_cc_secure_enabled-container")
	@CacheLookup
	public WebElement Creditcard_Enable_3d_Secure_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Force_3d_Secure_Condition_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[8]/th/label/span") })
	@CacheLookup
	public WebElement Creditcard_Force_3d_Secure_Condition_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_cc_cc_secure_enabled-container")
	@CacheLookup
	public WebElement Creditcard_Force_3d_Secure_Condition_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[9]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Amex_Logo_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[9]/th/label/span") })
	@CacheLookup
	public WebElement Creditcard_Amex_Logo_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_cc_enable_amex_type-container")
	@CacheLookup
	public WebElement Creditcard_Amex_Logo_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[10]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Maestro_Logo_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[10]/th/label/span") })
	@CacheLookup
	public WebElement Creditcard_Maestro_Logo_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_cc_enable_maestro_type-container")
	@CacheLookup
	public WebElement Creditcard_Maestro_Logo_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[11]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Shopping_Type_Label;

	@FindBy(id = "select2-woocommerce_novalnet_cc_payment_process-container")
	@CacheLookup
	public WebElement Creditcard_Shopping_Types_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[12]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Onhold_Payment_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_cc_limit_control-container")
	@CacheLookup
	public WebElement Creditcard_Pending_Payment_Status_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(14) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[14]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_cc_order_success_status-container")
	@CacheLookup
	public WebElement Creditcard_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(15) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[15]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Notification_Message_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(15) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[15]/th/label/span") })
	@CacheLookup
	public WebElement Creditcard_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_cc_payment_instruction")
	@CacheLookup
	public WebElement Creditcard_Notification_Message;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(16) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[16]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_cc_instructions")
	@CacheLookup
	public WebElement Creditcard_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(17) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[17]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_cc_email_notes")
	@CacheLookup
	public WebElement Creditcard_Email_Instruction;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(18) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[18]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Min_Value_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(18) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[18]/th/label/span") })
	@CacheLookup
	public WebElement Creditcard_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_cc_min_amount")
	@CacheLookup
	public WebElement Creditcard_Min_Value;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(18) > td:nth-child(2) > fieldset:nth-child(1) > p:nth-child(3)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[18]/td/fieldset/p") })
	@CacheLookup
	public WebElement Creditcard_Min_Value_Desc;

	@FindBy(id = "woocommerce_novalnet_cc_standard_style_configuration_heading")
	@CacheLookup
	public WebElement Creditcard_Custom_CSS_Label;

	@FindAll({ @FindBy(css = "#mainform > p:nth-child(7) > strong:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p[1]/strong") })
	@CacheLookup
	public WebElement Creditcard_Custom_CSS_Iframe_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(8) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Custom_CSS_Label_Textfield; // label name is provided as default text

	@FindBy(id = "woocommerce_novalnet_cc_standard_label")
	@CacheLookup
	public WebElement Creditcard_Custom_CSS_Label_Textfield_Textbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(8) > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Creditcard_Input_Label;

	@FindBy(id = "woocommerce_novalnet_cc_standard_input")
	@CacheLookup
	public WebElement Creditcard_Input_TextBox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(8) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Creditcard_CSSText_Label;

	@FindBy(id = "woocommerce_novalnet_cc_standard_css")
	@CacheLookup
	public WebElement Creditcard_CSSText_TextBox;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p[2]/button") })
	@CacheLookup
	public WebElement Creditcard_Payment_Save_Changes;

	// Direct debit sepa

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(6) > td:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[6]/td[2]/a") })
	//@CacheLookup
	public WebElement Sepa_Payment_Display;

	/*@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(6) > td:nth-child(6) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[6]/td[6]/a") })
	@CacheLookup
	public WebElement Sepa_Payment_Manage;*/
	
	// Buvana code starts
	@FindAll({ @FindBy(css = "#mainform > table > tbody > tr > td > table > tbody > tr:nth-child(6) > td.action > a"),
			@FindBy(xpath = "//*[@id=\"mainform\"]/table/tbody/tr/td/table/tbody/tr[6]/td[5]/a") })
	//@CacheLookup
	public WebElement Sepa_Payment_Manage;
	// Buvana code ends 

	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2") })
	//@CacheLookup
	public WebElement Sepa_Payment_Page_Title;

	@FindAll({ @FindBy(css = ".emoji"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2/small/a/img") })
	//@CacheLookup
	public WebElement Sepa_Return_To_Payment_Button;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[1]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Enable_Payment_Method_Label;

	@FindBy(id = "woocommerce_novalnet_sepa_enabled")
	//@CacheLookup
	public WebElement Sepa_Enable_Payment_Method_Checkbox;

	/* @FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Sepa_Payment_Title_EN_Label;*/
	
	/* ********Buvana code starts******/
	@FindAll({
		@FindBy(css = "#payment > ul > li > label"),
		@FindBy(xpath = "//*[@id=\"payment\"]/ul/li/label") })
//@CacheLookup
public WebElement Sepa_Payment_Title_EN_Label;	
	/* ********Buvana code ends******/


	@FindBy(id = "woocommerce_novalnet_sepa_title_en")
	//@CacheLookup
	public WebElement Sepa_Payment_Title_EN;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[3]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Payment_Desc_EN_Label;

	@FindBy(id = "woocommerce_novalnet_sepa_description_en")
	//@CacheLookup
	public WebElement Sepa_Payment_Desc_EN;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[4]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_sepa_title_de")
	//@CacheLookup
	public WebElement Sepa_Payment_Title_DE;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "woocommerce_novalnet_sepa_description_de") })
	//@CacheLookup
	public WebElement Sepa_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_sepa_description_de")
	//@CacheLookup
	public WebElement Sepa_Payment_Desc_DE;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[6]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[6]/th/label/span") })
	//@CacheLookup
	public WebElement Sepa_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_sepa_test_mode-container")
	//@CacheLookup
	public WebElement Sepa_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[7]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Shopping_Type_Label;

	@FindBy(xpath = "select2-woocommerce_novalnet_sepa_payment_process-container")
	public WebElement Sepa_Shopping_Type_Select_Button;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[8]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Duration_In_Days_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[8]/th/label/span") })
	//@CacheLookup
	public WebElement Sepa_Duration_In_Days_Help_Tip;

	/* @FindBy(id = "woocommerce_novalnet_sepa_sepa_payment_duration")
	@CacheLookup
	public WebElement Sepa_Duration_In_Days_Selectbox;*/
	
	// Buvana code starts
	@FindBy(id = "woocommerce_novalnet_sepa_sepa_payment_duration")
	//@CacheLookup
	public WebElement Sepa_Duration_In_Days;
	// Buvana code ends
	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[9]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Enable_Fraud_Prevention_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[7]/th/label/span") })
	//@CacheLookup
	public WebElement Sepa_Enable_Fraud_Prevention_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_sepa_fraud_module-container")
	//@CacheLookup
	public WebElement Sepa_Enable_Fraud_Prevention_Selectbox;

	@FindAll({ @FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[11]/th") })
	//@CacheLookup
	public WebElement Sepa_Onhold_Payment_Action_Label;

	/*@FindBy(id = "select2-woocommerce_novalnet_sepa_limit_control-container")
	//@CacheLookup
	public WebElement Sepa_Onhold_Payment_Action__Selectbox;*/
	@FindBy(id = "select2-woocommerce_novalnet_sepa_limit_control-container")
	//@CacheLookup
	public WebElement Sepa_Onhold_Payment_Action_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[12]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Onhold_Transaction_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[12]/th/label/span") })
	//@CacheLookup
	public WebElement Sepa_Onhold_Transaction_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_sepa_limit")
	//@CacheLookup
	public WebElement Sepa_Onhold_Transaction_Textbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(13) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[13]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_sepa_order_success_status-container")
	//@CacheLookup
	public WebElement Sepa_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(14) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[14]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Notification_Message_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(14) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[14]/th/label/span") })
	//@CacheLookup
	public WebElement Sepa_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_sepa_payment_instruction")
	//@CacheLookup
	public WebElement Sepa_Notification_Message;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(15) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[15]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_sepa_instructions")
	//@CacheLookup
	public WebElement Sepa_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(16) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[16]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_sepa_email_notes")
	//@CacheLookup
	public WebElement Sepa_Email_Instruction;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(17) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[17]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Min_Value_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(17) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[17]/th/label/span") })
	//@CacheLookup
	public WebElement Sepa_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_sepa_min_amount")
	//@CacheLookup
	public WebElement Sepa_Min_Value;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(17) > td:nth-child(2) > fieldset:nth-child(1) > p:nth-child(3)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[17]/td/fieldset/p") })
	//@CacheLookup
	public WebElement Sepa_Min_Value_Desc;

	// Guarantee Payment

	@FindBy(id = "woocommerce_novalnet_sepa_guarantee_payment_title")
	//@CacheLookup
	public WebElement Sepa_Payment_Guarantee_Configuration_Label;

	@FindAll({ @FindBy(css = "#mainform > p:nth-child(7) > strong:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p[1]/strong") })
	//@CacheLookup
	public WebElement Sepa_Basic_Reqiurement_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[1]") })
	//@CacheLookup
	public WebElement Sepa_Allow_Countries_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(2)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[2]") })
	//@CacheLookup
	public WebElement Sepa_Allow_Currencies_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(3)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[3]") })
	//@CacheLookup
	public WebElement Sepa_Minimum_Amount_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[4]") })
	//@CacheLookup
	public WebElement Sepa_Minimum_Age_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(5)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[5]") })
	//@CacheLookup
	public WebElement Sepa_Billing_Address_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(6)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[6]") })
	//@CacheLookup
	public WebElement Sepa_Gift_Certificates_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[1]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Enable_Payment_Guarantee_Label;

	/*@FindBy(id = "woocommerce_novalnet_sepa_guarantee_payment")
	@CacheLookup
	public WebElement Sepa_Enable_Payment_Guarantee_TextBox;*/
	
	//Buvana
	@FindBy(id = "woocommerce_novalnet_sepa_guarantee_payment")
	//@CacheLookup
	public WebElement Sepa_Enable_Payment_Guarantee_CheckBox;
	//Buvana

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[2]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Order_Status_Pending_Payment_Label;

	@FindBy(id = "select2-woocommerce_novalnet_sepa_guarantee_pending_status-container")
	//@CacheLookup
	public WebElement Sepa_Order_Status_Pending_Payment_Select_Box;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[3]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Minimum_Order_Amount_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[3]/th/label/span") })
	//@CacheLookup
	public WebElement Sepa_Minimum_Order_Amount_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_sepa_guarantee_payment_minimum_order_amount")
	//@CacheLookup
	public WebElement Sepa_Minimum_Order_Amount_Textbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > fieldset:nth-child(1) > p:nth-child(3)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[3]/td/fieldset/p") })
	//@CacheLookup
	public WebElement Sepa_Minimum_Order_Amount_Desc;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[4]/th/label") })
	//@CacheLookup
	public WebElement Sepa_Force_Non_Guarantee_Label;

	@FindBy(id = "woocommerce_novalnet_sepa_force_normal_payment")
	//@CacheLookup
	public WebElement Sepa_Force_Non_Guarantee_Textbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > fieldset:nth-child(1) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[4]/td/fieldset/label") })
	//@CacheLookup
	public WebElement Sepa_Force_Non_Guarantee_Desc;

	/* @FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p[3]/button") })
	@CacheLookup
	public WebElement Sepa_Payment_Save_Changes;*/
	
	//Buvana code starts
	
	@FindAll({ @FindBy(css = "#mainform > p.submit > button"),
		@FindBy(xpath = "//*[@id=\"mainform\"]/p[3]/button") })
//@CacheLookup
public WebElement Sepa_Payment_Save_Changes;
	
	// Buvana code ends

	// Invoice
 
	/* @FindAll({ @FindBy(css = "#mainform > table > tbody > tr > td > table > tbody > tr:nth-child(7) > td.name"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[10]/td[2]/a") })
	@CacheLookup
	public WebElement Invoice_Payment_Display;*/
	
	//Buvana code starts
	
	@FindAll({ @FindBy(css = "#mainform > table > tbody > tr > td > table > tbody > tr:nth-child(7) > td.name > a"),
		@FindBy(xpath = "//*[@id=\"mainform\"]/table/tbody/tr/td/table/tbody/tr[7]/td[2]/a")})
@CacheLookup
public WebElement Invoice_Payment_Display;
	
	//Buvana code ends

	@FindAll({ @FindBy(css = "#mainform > table > tbody > tr > td > table > tbody > tr:nth-child(7) > td.action > a"),
			@FindBy(xpath = "//*[@id=\"mainform\"]/table/tbody/tr/td/table/tbody/tr[7]/td[6]/a") })
	//@CacheLookup
	public WebElement Invoice_Payment_Manage;

	@FindAll({ @FindBy(css = "#mainform > h2"), @FindBy(xpath = "//*[@id=\"mainform\"]/h2") })
	@CacheLookup
	public WebElement Invoice_Payment_Page_Title;

	@FindAll({ @FindBy(css = "#mainform > h2 > small > a"), @FindBy(xpath = "//*[@id=\"mainform\"]/h2/small/a") })
	@CacheLookup
	public WebElement Invoice_Return_To_Payment_Button;

	@FindAll({ @FindBy(css = "#mainform > table:nth-child(6) > tbody > tr:nth-child(1) > th > label"),
			@FindBy(xpath = "//*[@id=\"mainform\"]/table[1]/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Invoice_Enable_Payment_Method_Label;

	@FindBy(id = "woocommerce_novalnet_invoice_enabled")
	@CacheLookup
	public WebElement Invoice_Enable_Payment_Method_Checkbox;

	@FindAll({ @FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[2]/th") })
	@CacheLookup
	public WebElement Invoice_Payment_Title_EN_Label;

	@FindBy(id = "woocommerce_novalnet_invoice_title_en")
	@CacheLookup
	public WebElement Invoice_Payment_Title_EN;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Invoice_Payment_Desc_EN_Label;

	@FindBy(id = "woocommerce_novalnet_invoice_description_en")
	@CacheLookup
	public WebElement Invoice_Payment_Desc_EN;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Invoice_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_invoice_title_de")
	@CacheLookup
	public WebElement Invoice_Payment_Title_DE;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Invoice_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_invoice_description_de")
	@CacheLookup
	public WebElement Invoice_Payment_Desc_DE;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[6]/th/label") })
	@CacheLookup
	public WebElement Invoice_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[6]/th/label/span") })
	@CacheLookup
	public WebElement Invoice_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_invoice_test_mode-container")
	@CacheLookup
	public WebElement Invoice_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Invoice_Enable_Fraud_Prevention_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[7]/th/label/span") })
	@CacheLookup
	public WebElement Invoice_Enable_Fraud_Prevention_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_invoice_fraud_module-container")
	@CacheLookup
	public WebElement Invoice_Enable_Fraud_Prevention_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[9]/th/label") })
	@CacheLookup
	public WebElement Invoice_Onhold_Payment_Action_Label;

	/* @FindBy(id = "select2-woocommerce_novalnet_invoice_limit_control-container")
	@CacheLookup
	public WebElement Invoice_Onhold_Payment_Action__Selectbox;*/
	
	/* Buvana code starts*/

	@FindBy(id = "select2-woocommerce_novalnet_invoice_limit_control-container")
	@CacheLookup
	public WebElement Invoice_Onhold_Payment_Action_Selectbox;
	
	/* Buvana code ends*/

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[11]/th/label") })
	@CacheLookup
	public WebElement Invoice_Payment_Due_Date_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[11]/th/label/span") })
	@CacheLookup
	public WebElement Invoice_Payment_Due_Date_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_invoice_payment_duration")
	@CacheLookup
	public WebElement Invoice_Payment_Due_Date_Textbox;

	@FindAll({ @FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[12]/th") })
	@CacheLookup
	public WebElement Invoice_CallBack_Order__Status_Label;

	/* @FindBy(id = "select2-woocommerce_novalnet_invoice_callback_status-container")
	@CacheLookup
	public WebElement Invoice_CallBack_Order_Status_Selectbox;*/
	
	/* ***Buvana code starts***/
	
	@FindBy(id = "select2-woocommerce_novalnet_invoice_callback_status-container")
	@CacheLookup
	public WebElement Invoice_CallBack_Order_Status_Selectbox;
	/* ***Buvana code ends***/

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(13) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[13]/th/label") })
	@CacheLookup
	public WebElement Invoice_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_invoice_order_success_status-container")
	@CacheLookup
	public WebElement Invoice_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(14) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[14]/th/label") })
	@CacheLookup
	public WebElement Invoice_Notification_Message_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(14) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[14]/th/label/span") })
	@CacheLookup
	public WebElement Invoice_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_invoice_payment_instruction")
	@CacheLookup
	public WebElement Invoice_Notification_Message;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(15) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[15]/th/label") })
	@CacheLookup
	public WebElement Invoicer_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_invoice_instructions")
	@CacheLookup
	public WebElement Invoice_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(16) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[16]/th/label") })
	@CacheLookup
	public WebElement Invoice_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_invoice_email_notes")
	@CacheLookup
	public WebElement Invoice_Email_Instruction;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(17) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[17]/th/label") })
	@CacheLookup
	public WebElement Invoice_Min_Value_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(17) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[17]/th/label/span") })
	@CacheLookup
	public WebElement Invoice_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_invoice_min_amount")
	@CacheLookup
	public WebElement Invoice_Min_Value;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(17) > td:nth-child(2) > fieldset:nth-child(1) > p:nth-child(3)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[1]/tbody/tr[17]/td/fieldset/p") })
	@CacheLookup
	public WebElement Invoice_Min_Value_Desc;

	// Guarantee Payment

	@FindBy(id = "woocommerce_novalnet_invoice_guarantee_payment_title")
	@CacheLookup
	public WebElement Invoice_Payment_Guarantee_Configuration_Label;

	@FindAll({ @FindBy(css = "#mainform > p:nth-child(7) > strong:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p[1]/strong") })
	@CacheLookup
	public WebElement Invoice_Basic_Reqiurement_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[1]") })
	@CacheLookup
	public WebElement Invoice_Allow_Countries_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(2)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[2]") })
	@CacheLookup
	public WebElement Invoice_Allow_Currencies_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(3)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[3]") })
	@CacheLookup
	public WebElement Invoice_Minimum_Amount_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[4]") })
	@CacheLookup
	public WebElement Invoice_Minimum_Age_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(5)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[5]") })
	@CacheLookup
	public WebElement Invoice_Billing_Address_Label;

	@FindAll({ @FindBy(css = "#mainform > ul:nth-child(8) > li:nth-child(6)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/ul/li[6]") })
	@CacheLookup
	public WebElement Invoice_Gift_Certificates_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Invoice_Enable_Payment_Guarantee_Label;

	/*@FindBy(id = "woocommerce_novalnet_invoice_guarantee_payment")
	@CacheLookup
	public WebElement Invoice_Enable_Payment_Guarantee_TextBox;*/
	@FindBy(id = "woocommerce_novalnet_invoice_guarantee_payment")
	//@CacheLookup
	public WebElement Invoice_Enable_Payment_Guarantee_CheckBox;
	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Invoice_Order_Status_Pending_Payment_Label;

	@FindBy(id = "select2-woocommerce_novalnet_invoice_guarantee_pending_status-container")
	@CacheLookup
	public WebElement Invoice_Order_Status_Pending_Payment_Select_Box;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Invoice_Minimum_Order_Amount_Label;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[3]/th/label/span") })
	@CacheLookup
	public WebElement Invoice_Minimum_Order_Amount_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_invoice_guarantee_payment_minimum_order_amount")
	//@CacheLookup
	public WebElement Invoice_Minimum_Order_Amount_Textbox;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > fieldset:nth-child(1) > p:nth-child(3)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[3]/td/fieldset/p") })
	@CacheLookup
	public WebElement Invoice_Minimum_Order_Amount_Desc;

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Invoice_Force_Non_Guarantee_Label;

	/*@FindBy(id = "woocommerce_novalnet_invoice_force_normal_payment")
	@CacheLookup
	public WebElement Invoice_Force_Non_Guarantee_Textbox;*/
	@FindBy(id = "woocommerce_novalnet_invoice_force_normal_payment")
	@CacheLookup
	public WebElement Invoice_Force_Non_Guarantee_Checkbox;
	

	@FindAll({
			@FindBy(css = "table.form-table:nth-child(10) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > fieldset:nth-child(1) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[2]/tbody/tr[4]/td/fieldset/label") })
	@CacheLookup
	public WebElement Invoice_Force_Non_Guarantee_Desc;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p[3]/button") })
	//@CacheLookup
	public WebElement Invoice_Payment_Save_Changes;

	// Prepayment

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(8) > td:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[8]/td[2]/a") })
	//@CacheLookup
	public WebElement Prepayment_Payment_Display;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(8) > td:nth-child(6) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[8]/td[6]/a") })
	@CacheLookup
	public WebElement Prepayment_Payment_Manage;

	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2") })
	@CacheLookup
	public WebElement Prepayment_Payment_Page_Title;

	@FindAll({ @FindBy(css = ".emoji"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2/small/a/img") })
	@CacheLookup
	public WebElement Prepayment_Return_To_Payment_Button;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Enable_Payment_Method_Label;

	/* @FindBy(id = "woocommerce_novalnet_prepayment_title_en")
	@CacheLookup
	public WebElement Prepayment_Enable_Payment_Method_Checkbox;*/
	
	/* *** Buvana code starts***/
	@FindBy(id = "woocommerce_novalnet_prepayment_enabled")
	@CacheLookup
	public WebElement Prepayment_Enable_Payment_Method_Checkbox;
	/* *** Buvana code ends***/


	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Payment_Title_EN_Label;

	@FindBy(id = "woocommerce_novalnet_prepayment_title_en")
	@CacheLookup
	public WebElement Prepayment_Payment_Title_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Payment_Desc_EN_Label;

	@FindBy(id = "woocommerce_novalnet_prepayment_description_en")
	@CacheLookup
	public WebElement Prepayment_Payment_Desc_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_prepayment_title_de")
	@CacheLookup
	public WebElement Prepayment_Payment_Title_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_prepayment_description_de")
	@CacheLookup
	public WebElement Prepayment_Payment_Desc_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label/span") })
	@CacheLookup
	public WebElement Prepayment_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_prepayment_test_mode-container")
	@CacheLookup
	public WebElement Prepayment_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Prepayment_CallBack_Order__Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_prepayment_callback_status-container")
	@CacheLookup
	public WebElement Prepayment_CallBack_Order__Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_prepayment_order_success_status-container")
	@CacheLookup
	public WebElement Prepayment_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[9]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Notification_Message_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[9]/th/label/span") })
	@CacheLookup
	public WebElement Prepayment_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_prepayment_payment_instruction")
	@CacheLookup
	public WebElement Prepayment_Notification_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[10]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_prepayment_instructions")
	@CacheLookup
	public WebElement Prepayment_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_prepayment_email_notes")
	@CacheLookup
	public WebElement Prepayment_Email_Instruction;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[12]/th/label") })
	@CacheLookup
	public WebElement Prepayment_Min_Value_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[12]/th/label/span") })
	@CacheLookup
	public WebElement Prepayment_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_prepayment_min_amount")
	@CacheLookup
	public WebElement Prepayment_Min_Value;

	@FindAll({ @FindBy(css = ".description"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[12]/td/fieldset/p") })
	@CacheLookup
	public WebElement Prepayment_Min_Value_Desc;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p/button") })
	@CacheLookup
	public WebElement Prepayment_Payment_Save_Changes;

	// Barzahlen

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(15) > td:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[15]/td[2]/a") })
	@CacheLookup
	public WebElement Barzahlen_Payment_Display;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(15) > td:nth-child(6) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[15]/td[6]/a") })
	@CacheLookup
	public WebElement Barzahlen_Payment_Manage;

	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2") })
	@CacheLookup
	public WebElement Barzahlen_Payment_Page_Title;

	@FindAll({ @FindBy(css = ".emoji"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2/small/a/img") })
	@CacheLookup
	public WebElement Barzahlen_Return_To_Payment_Button;

	@FindAll({ @FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[2]/th") })
	@CacheLookup
	public WebElement Barzahlen_Enable_Payment_Method_Label;

	@FindBy(id = "woocommerce_novalnet_barzahlen_title_en")
	@CacheLookup
	public WebElement Barzahlen_Enable_Payment_Method_Checkbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_Payment_Title_EN_Label;

	@FindBy(id = "woocommerce_novalnet_barzahlen_description_en")
	@CacheLookup
	public WebElement Barzahlen_Payment_Title_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_barzahlen_description_de")
	@CacheLookup
	public WebElement Barzahlen_Payment_Title_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_barzahlen_description_de")
	@CacheLookup
	public WebElement Barzahlen_Payment_Desc_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label/span") })
	@CacheLookup
	public WebElement Barzahlen_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_barzahlen_test_mode-container")
	@CacheLookup
	public WebElement Barzahlen_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_Slip_Expiry_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[7]/th/label/span") })
	@CacheLookup
	public WebElement Barzahlen_Slip_Expiry_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_barzahlen_barzahlen_payment_duration")
	@CacheLookup
	public WebElement Barzahlen_Slip_Expiry_Textbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_CallBack_Order__Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_barzahlen_callback_status-container")
	@CacheLookup
	public WebElement Barzahlen_CallBack_Order_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_prepayment_order_success_status-container")
	@CacheLookup
	public WebElement Barzahlen_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[10]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_Notification_Message_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[10]/th/label/span") })
	@CacheLookup
	public WebElement Barzahlen_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_barzahlen_payment_instruction")
	@CacheLookup
	public WebElement Barzahlen_Notification_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_barzahlen_instructions")
	@CacheLookup
	public WebElement Barzahlen_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[12]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_barzahlen_email_notes")
	@CacheLookup
	public WebElement Barzahlen_Email_Instruction;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(13) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[13]/th/label") })
	@CacheLookup
	public WebElement Barzahlen_Min_Value_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(13) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[13]/th/label/span") })
	@CacheLookup
	public WebElement Barzahlen_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_barzahlen_min_amount")
	@CacheLookup
	public WebElement Barzahlen_Min_Value;

	@FindAll({ @FindBy(css = ".description"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[13]/td/fieldset/p") })
	@CacheLookup
	public WebElement Barzahlen_Min_Value_Desc;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p/button") })
	@CacheLookup
	public WebElement Barzahlen_Payment_Save_Changes;

	// Novalnet Instant Bank Transfer WebElement
	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(10) > td:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[10]/td[2]/a") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Display;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(10) > td:nth-child(3) > a:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[10]/td[3]/a/span") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Enable_Button;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(10) > td:nth-child(6) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[10]/td[6]/a") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Manage;

	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Page_Title;

	@FindAll({ @FindBy(css = ".emoji"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2/small/a/img") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Return_To_Payment_Button;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Enable_Payment_Method_Label;

	@FindBy(id = "woocommerce_novalnet_instantbank_enabled")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Enable_Payment_Method_Checkbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Title_EN_Label;

	@FindBy(id = "woocommerce_novalnet_instantbank_title_en")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Title_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Desc_EN_Label;

	@FindBy(id = "woocommerce_novalnet_instantbank_description_en")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Desc_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_instantbank_title_de")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Title_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_instantbank_description_de")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Desc_DE;

	@FindAll({ @FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label/span") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_instantbank_test_mode-container")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_instantbank_order_success_status-container")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Notification_Message_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label/span") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_instantbank_payment_instruction")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Notification_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[9]/th/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_instantbank_instructions")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[10]/th/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_instantbank_email_notes")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Email_Instruction;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Min_Value_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label/span") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_instantbank_min_amount")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Min_Value;

	@FindAll({ @FindBy(css = ".description"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/td/fieldset/p") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Min_Value_Desc;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p/button") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Payment_Save_Changes;

	// Novalnet IDEAL WebElement

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(11) > td:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[11]/td[2]/a") })
	@CacheLookup
	public WebElement Ideal_Payment_Display;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(11) > td:nth-child(3) > a:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[11]/td[3]/a/span") })
	@CacheLookup
	public WebElement Ideal_Payment_Enable_Button;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(11) > td:nth-child(6)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[11]/td[6]") })
	@CacheLookup
	public WebElement Ideal_Payment_Manage;

	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2") })
	@CacheLookup
	public WebElement Ideal_Payment_Page_Title;

	@FindAll({ @FindBy(css = ".emoji"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2/small/a/img") })
	@CacheLookup
	public WebElement Ideal_Return_To_Payment_Button;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Ideal_Enable_Payment_Method_Label;

	@FindBy(id = "woocommerce_novalnet_ideal_enabled")
	@CacheLookup
	public WebElement Ideal_Enable_Payment_Method_Checkbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Ideal_Payment_Title_EN_Label;

	@FindBy(id = "woocommerce_novalnet_ideal_title_en")
	@CacheLookup
	public WebElement Ideal_Payment_Title_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Ideal_Payment_Desc_EN_Label;

	@FindBy(id = "woocommerce_novalnet_ideal_description_en")
	@CacheLookup
	public WebElement Ideal_Payment_Desc_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Ideal_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_ideal_title_de")
	@CacheLookup
	public WebElement Ideal_Payment_Title_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Ideal_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_ideal_description_de")
	@CacheLookup
	public WebElement Ideal_Payment_Desc_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label") })
	@CacheLookup
	public WebElement Ideal_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label/span") })
	@CacheLookup
	public WebElement Ideal_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_ideal_test_mode-container")
	@CacheLookup
	public WebElement Ideal_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Ideal_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_ideal_order_success_status-container")
	@CacheLookup
	public WebElement Ideal_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Ideal_Notification_Message_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label/span") })
	@CacheLookup
	public WebElement Ideal_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_ideal_payment_instruction")
	@CacheLookup
	public WebElement Ideal_Notification_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[9]/th/label") })
	@CacheLookup
	public WebElement Ideal_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_ideal_instructions")
	@CacheLookup
	public WebElement Ideal_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[10]/th/label") })
	@CacheLookup
	public WebElement Ideal_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_ideal_email_notes")
	@CacheLookup
	public WebElement Ideal_Email_Instruction;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label") })
	@CacheLookup
	public WebElement Ideal_Min_Value_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label/span") })
	@CacheLookup
	public WebElement Ideal_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_ideal_min_amount")
	@CacheLookup
	public WebElement Ideal_Min_Value;

	@FindAll({ @FindBy(css = ".description"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/td/fieldset/p") })
	@CacheLookup
	public WebElement Ideal_Min_Value_Desc;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p/button") })
	@CacheLookup
	public WebElement Ideal_Payment_Save_Changes;

	// Novalnet PayPal WebElement

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(9) > td:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[9]/td[2]/a") })
	@CacheLookup
	public WebElement Paypal_Payment_Display;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(9) > td:nth-child(3) > a:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[9]/td[3]/a/span") })
	@CacheLookup
	public WebElement Paypal_Payment_Enable_Button;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(9) > td:nth-child(6) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[9]/td[6]/a") })
	@CacheLookup
	public WebElement Paypal_Payment_Manage;

	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2") })
	@CacheLookup
	public WebElement Paypal_Payment_Page_Title;

	@FindAll({ @FindBy(css = ".emoji"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2/small/a/img") })
	@CacheLookup
	public WebElement Paypal_Return_To_Payment_Button;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Paypal_Enable_Payment_Method_Label;

	@FindBy(id = "woocommerce_novalnet_paypal_enabled")
	@CacheLookup
	public WebElement Paypal_Enable_Payment_Method_Checkbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Paypal_Payment_Title_EN_Label;

	@FindBy(id = "woocommerce_novalnet_paypal_title_en")
	@CacheLookup
	public WebElement Paypal_Payment_Title_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Paypal_Payment_Desc_EN_Label;

	@FindBy(id = "woocommerce_novalnet_paypal_description_en")
	@CacheLookup
	public WebElement Paypal_Payment_Desc_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Paypal_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_paypal_title_de")
	@CacheLookup
	public WebElement Paypal_Payment_Title_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Paypal_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_paypal_description_de")
	@CacheLookup
	public WebElement Paypal_Payment_Desc_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label") })
	@CacheLookup
	public WebElement Paypal_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label/span") })
	@CacheLookup
	public WebElement Paypal_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_paypal_test_mode-container")
	@CacheLookup
	public WebElement Paypal_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Paypal_Shopping_Type_Label;

	@FindBy(id = "select2-woocommerce_novalnet_paypal_payment_process-container")
	@CacheLookup
	public WebElement Paypal_Shopping_Type_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Paypal_Onhold_Payment_Action_Label;

	@FindBy(id = "select2-woocommerce_novalnet_paypal_limit_control-container")
	@CacheLookup
	public WebElement Paypal_Onhold_Payment_Action_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[10]/th/label") })
	@CacheLookup
	public WebElement Paypal_Pending_Payment_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_paypal_pending_status-container")
	@CacheLookup
	public WebElement Paypal_Pending_Payment_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label") })
	@CacheLookup
	public WebElement Paypal_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_paypal_order_success_status-container")
	@CacheLookup
	public WebElement Paypal_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[12]/th/label") })
	@CacheLookup
	public WebElement Paypal_Notification_Message_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[12]/th/label/span") })
	@CacheLookup
	public WebElement Paypal_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_paypal_payment_instruction")
	@CacheLookup
	public WebElement Paypal_Notification_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(13) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[13]/th/label") })
	@CacheLookup
	public WebElement Paypal_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_paypal_instructions")
	@CacheLookup
	public WebElement Paypal_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(14) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[14]/th/label") })
	@CacheLookup
	public WebElement Paypal_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_paypal_email_notes")
	@CacheLookup
	public WebElement Paypal_Email_Instruction;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(15) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[15]/th/label") })
	@CacheLookup
	public WebElement Paypal_Min_Value_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(15) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[15]/th/label/span") })
	@CacheLookup
	public WebElement Paypal_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_paypal_min_amount")
	@CacheLookup
	public WebElement Paypal_Min_Value;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(15) > td:nth-child(2) > fieldset:nth-child(1) > p:nth-child(3)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[15]/td/fieldset/p") })
	@CacheLookup
	public WebElement Paypal_Min_Value_Desc;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p/button") })
	@CacheLookup
	public WebElement Paypal_Payment_Save_Changes;

	// Novalnet eps WebElement

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(12) > td:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[12]/td[2]/a") })
	@CacheLookup
	public WebElement Eps_Payment_Display;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(12) > td:nth-child(3) > a:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[12]/td[3]/a/span") })
	@CacheLookup
	public WebElement Eps_Payment_Enable_Button;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(12) > td:nth-child(6) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[12]/td[6]/a") })
	@CacheLookup
	public WebElement Eps_Payment_Manage;

	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2") })
	@CacheLookup
	public WebElement Eps_Payment_Page_Title;

	@FindAll({ @FindBy(css = ".emoji"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2/small/a/img") })
	@CacheLookup
	public WebElement Eps_Return_To_Payment_Button;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Eps_Enable_Payment_Method_Label;

	@FindBy(id = "woocommerce_novalnet_eps_enabled")
	@CacheLookup
	public WebElement Eps_Enable_Payment_Method_Checkbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Eps_Payment_Title_EN_Label;

	@FindBy(id = "woocommerce_novalnet_eps_title_en")
	@CacheLookup
	public WebElement Eps_Payment_Title_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Eps_Payment_Desc_EN_Label;

	@FindBy(id = "woocommerce_novalnet_eps_description_en")
	@CacheLookup
	public WebElement Eps_Payment_Desc_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Eps_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_eps_title_de")
	@CacheLookup
	public WebElement Eps_Payment_Title_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Eps_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_eps_description_de")
	@CacheLookup
	public WebElement Eps_Payment_Desc_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label") })
	@CacheLookup
	public WebElement Eps_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label/span") })
	@CacheLookup
	public WebElement Eps_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_eps_test_mode-container")
	@CacheLookup
	public WebElement Eps_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Eps_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_eps_order_success_status-container")
	@CacheLookup
	public WebElement Eps_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Eps_Notification_Message_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label/span") })
	@CacheLookup
	public WebElement Eps_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_eps_payment_instruction")
	@CacheLookup
	public WebElement Eps_Notification_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[9]/th/label") })
	@CacheLookup
	public WebElement Eps_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_eps_instructions")
	@CacheLookup
	public WebElement Eps_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[10]/th/label") })
	@CacheLookup
	public WebElement Eps_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_eps_email_notes")
	@CacheLookup
	public WebElement Eps_Email_Instruction;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th") })
	@CacheLookup
	public WebElement Eps_Min_Value_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label/span") })
	@CacheLookup
	public WebElement Eps_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_eps_min_amount")
	@CacheLookup
	public WebElement Eps_Min_Value;

	@FindAll({ @FindBy(css = ".description"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/td/fieldset/p") })
	@CacheLookup
	public WebElement Eps_Min_Value_Desc;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p/button") })
	@CacheLookup
	public WebElement Eps_Payment_Save_Changes;

	// Novalnet Giropay WebElement

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(13) > td:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[13]/td[2]/a") })
	@CacheLookup
	public WebElement Giropay_Payment_Display;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(13) > td:nth-child(3) > a:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[13]/td[3]/a/span") })
	@CacheLookup
	public WebElement Giropay_Payment_Enable_Button;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(13) > td:nth-child(6) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[13]/td[6]/a") })
	@CacheLookup
	public WebElement Giropay_Payment_Manage;

	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2") })
	@CacheLookup
	public WebElement Giropay_Payment_Page_Title;

	@FindAll({ @FindBy(css = ".emoji"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2/small/a/img") })
	@CacheLookup
	public WebElement Giropay_Return_To_Payment_Button;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Giropay_Enable_Payment_Method_Label;

	@FindBy(id = "woocommerce_novalnet_giropay_enabled")
	@CacheLookup
	public WebElement Giropay_Enable_Payment_Method_Checkbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Giropay_Payment_Title_EN_Label;

	@FindBy(id = "woocommerce_novalnet_giropay_title_en")
	@CacheLookup
	public WebElement Giropay_Payment_Title_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Giropay_Payment_Desc_EN_Label;

	@FindBy(id = "woocommerce_novalnet_giropay_description_en")
	@CacheLookup
	public WebElement Giropay_Payment_Desc_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Giropay_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_giropay_title_de")
	@CacheLookup
	public WebElement Giropay_Payment_Title_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Giropay_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_giropay_description_de")
	@CacheLookup
	public WebElement Giropay_Payment_Desc_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label") })
	@CacheLookup
	public WebElement Giropay_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label/span") })
	@CacheLookup
	public WebElement Giropay_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_giropay_test_mode-container")
	@CacheLookup
	public WebElement Giropay_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Giropay_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_giropay_order_success_status-container")
	@CacheLookup
	public WebElement Giropay_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Giropay_Notification_Message_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label/span") })
	@CacheLookup
	public WebElement Giropay_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_giropay_payment_instruction")
	@CacheLookup
	public WebElement Giropay_Notification_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[9]/th/label") })
	@CacheLookup
	public WebElement Giropay_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_giropay_instructions")
	@CacheLookup
	public WebElement Giropay_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[10]/th/label") })
	@CacheLookup
	public WebElement Giropay_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_giropay_email_notes")
	@CacheLookup
	public WebElement Giropay_Email_Instruction;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label") })
	@CacheLookup
	public WebElement Giropay_Min_Value_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label/span") })
	@CacheLookup
	public WebElement Giropay_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_giropay_min_amount")
	@CacheLookup
	public WebElement Giropay_Min_Value;

	@FindAll({ @FindBy(css = ".description"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/td/fieldset/p") })
	@CacheLookup
	public WebElement Giropay_Min_Value_Desc;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p/button") })
	@CacheLookup
	public WebElement Giropay_Payment_Save_Changes;

	// Novalnet Przelewy24 WebElement

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(14) > td:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[14]/td[2]/a") })
	@CacheLookup
	public WebElement Przelewy24_Payment_Display;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(14) > td:nth-child(3) > a:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[14]/td[3]/a/span") })
	@CacheLookup
	public WebElement Przelewy24_Payment_Enable_Button;

	@FindAll({ @FindBy(css = ".ui-sortable > tr:nth-child(14) > td:nth-child(6) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr/td/table/tbody/tr[14]/td[6]/a") })
	@CacheLookup
	public WebElement Przelewy24_Payment_Manage;

	@FindAll({ @FindBy(css = "#mainform > h2:nth-child(4)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2") })
	@CacheLookup
	public WebElement Przelewy24_Payment_Page_Title;

	@FindAll({ @FindBy(css = ".emoji"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/h2/small/a/img") })
	@CacheLookup
	public WebElement Przelewy24_Return_To_Payment_Button;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[1]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Enable_Payment_Method_Label;

	@FindBy(id = "woocommerce_novalnet_przelewy24_enabled")
	@CacheLookup
	public WebElement Przelewy24_Enable_Payment_Method_Checkbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(2) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[2]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Payment_Title_EN_Label;

	@FindBy(id = "woocommerce_novalnet_przelewy24_title_en")
	@CacheLookup
	public WebElement Przelewy24_Payment_Title_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[3]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Payment_Desc_EN_Label;

	@FindBy(id = "woocommerce_novalnet_przelewy24_description_en")
	@CacheLookup
	public WebElement Przelewy24_Payment_Desc_EN;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(4) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[4]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Payment_Title_DE_Label;

	@FindBy(id = "woocommerce_novalnet_przelewy24_title_de")
	@CacheLookup
	public WebElement Przelewy24_Payment_Title_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(5) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[5]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Payment_Desc_DE_Label;

	@FindBy(id = "woocommerce_novalnet_przelewy24_description_de")
	@CacheLookup
	public WebElement Przelewy24_Payment_Desc_DE;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Enable_Test_Mode_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(6) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[6]/th/label/span") })
	@CacheLookup
	public WebElement Przelewy24_Enable_Test_Mode_Help_Tip;

	@FindBy(id = "select2-woocommerce_novalnet_przelewy24_test_mode-container")
	@CacheLookup
	public WebElement Przelewy24_Enable_Test_Mode_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(7) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[7]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Pending_Payment_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_przelewy24_pending_status-container")
	@CacheLookup
	public WebElement Przelewy24_Pending_Payment_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(8) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[8]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Order_Completion_Status_Label;

	@FindBy(id = "select2-woocommerce_novalnet_przelewy24_order_success_status-container")
	@CacheLookup
	public WebElement Przelewy24_Order_Completion_Status_Selectbox;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[9]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Notification_Message_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(9) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[9]/th/label/span") })
	@CacheLookup
	public WebElement Przelewy24_Notification_Message_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_przelewy24_payment_instruction")
	@CacheLookup
	public WebElement Przelewy24_Notification_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(10) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[10]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Thankyou_Page_Message_Label;

	@FindBy(id = "woocommerce_novalnet_przelewy24_instructions")
	@CacheLookup
	public WebElement Przelewy24_Thankyou_Page_Message;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(11) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[11]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Email_Instruction_Label;

	@FindBy(id = "woocommerce_novalnet_przelewy24_email_notes")
	@CacheLookup
	public WebElement Przelewy24_Email_Instruction;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1) > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[12]/th/label") })
	@CacheLookup
	public WebElement Przelewy24_Min_Value_Label;

	@FindAll({
			@FindBy(css = ".form-table > tbody:nth-child(1) > tr:nth-child(12) > th:nth-child(1) > label:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[12]/th/label/span") })
	@CacheLookup
	public WebElement Przelewy24_Min_Value_Help_Tip;

	@FindBy(id = "woocommerce_novalnet_przelewy24_min_amount")
	@CacheLookup
	public WebElement Przelewy24_Min_Value;

	@FindAll({ @FindBy(css = ".description"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table/tbody/tr[12]/td/fieldset/p") })
	@CacheLookup
	public WebElement Przelewy24_Min_Value_Desc;

	@FindAll({ @FindBy(css = "button.button-primary"),
			@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/p/button") })
	@CacheLookup
	public WebElement Przelewy24_Payment_Save_Changes;

	// ****************(Front-END)
	// Woocommerce shop menus
	@FindAll({ @FindBy(css = "li.page_item:nth-child(1) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/header/div/nav/div/ul/li[1]/a") })
	//*[@id="primary-menu"]/ul/li[1]/a
	@CacheLookup
	public WebElement Cart_Menu;

	@FindAll({ @FindBy(css = "li.page_item:nth-child(2) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/header/div/nav/div/ul/li[2]/a") })
	@CacheLookup
	public WebElement Checkout_Menu;

	@FindAll({ @FindBy(css = "li.page_item:nth-child(3) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/header/div/nav/div/ul/li[3]/a") })
	@CacheLookup
	public WebElement MyAccount_Menu;

	@FindAll({ @FindBy(id = "user_login"), @FindBy(css = "li.page_item:nth-child(4) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/header/div/nav/div/ul/li[4]/a") })
	@CacheLookup
	public WebElement SamplePages_Menu;

	@FindAll({ @FindBy(css = "#primary-menu > ul > li.page_item.page-item-6 > a"),
			@FindBy(xpath = "/html/body/div/header/div/nav/div/ul/li[5]/a") })
	@CacheLookup
	public WebElement Shop_Menu;
	
	@FindAll({ @FindBy(css = "#post-9 > div > div > nav > ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--customer-logout > a"),
		@FindBy(xpath = "//*[@id=\"post-9\"]/div/div/nav/ul/li[6]/a"), @FindBy(linkText = "Logout") })
public WebElement Shop_Logout;

	// Shop menu selecting products

	@FindAll({ @FindBy(css = "li.product:nth-child(2) > a:nth-child(4)"),
			@FindBy(xpath = "/html/body/div/div/div/div/ul/li[2]/a[2]") })
	@CacheLookup
	public WebElement Shop_Menu_Product1;

	@FindAll({ @FindBy(css = "li.product:nth-child(6) > a:nth-child(3)"),
			@FindBy(xpath = "/html/body/div/div/div/div/ul/li[6]/a[2]") })
	@CacheLookup
	public WebElement Shop_Menu_Product2;

	@FindAll({ @FindBy(css = ".added_to_cart"), @FindBy(xpath = "/html/body/div/div/div/div/ul/li[2]/a[3]") })
	@CacheLookup
	public WebElement ViewCart;

	/*@FindAll({ @FindBy(linkText = "http://woocommerce12-test.projects4.che/index.php/checkout/"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div[2]/div[2]/div/a") })
	@CacheLookup
	public WebElement ViewCart_Proceed_To_Checkout;*/
	
	/* *******Buvana code starts **********/
	@FindAll({ @FindBy(css = "#post-7 > div > div > div.cart-collaterals > div > div > a"),
		@FindBy(xpath = "//*[@id=\"post-7\"]/div/div/div[2]/div/div/a") })
	@CacheLookup
	public WebElement ViewCart_Proceed_To_Checkout;
	/* *******Buvana code ends **********/

	// Wordpress Checkout process_

	@FindAll({ @FindBy(css = ".woocommerce-billing-fields > h3:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[1]/div/h3") })
	@CacheLookup
	public WebElement Checkout_Page_Billing_details_label;

	@FindAll({ @FindBy(css = ".woocommerce-form__label > span:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[2]/div[1]/h3/label/span") })
	@CacheLookup
	public WebElement Checkout_Page_Ship_to_a_different_address_label;

	@FindBy(id = "ship-to-different-address-checkbox")
	@CacheLookup
	public WebElement Checkout_Page_Ship_to_a_different_address_Checkbox;

	@FindAll({ @FindBy(css = "#order_comments_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[2]/div[2]/div/p/label") })
	@CacheLookup
	public WebElement Checkout_Page_OrderNotes_label;

	@FindBy(id = "order_comments")
	@CacheLookup
	public WebElement Checkout_Page_Data_OrderNotes_Comment_Box;

	@FindAll({ @FindBy(css = "#billing_first_name_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[1]/div/div/p[1]/label") })
	@CacheLookup
	public WebElement Checkout_Page_FirstName_label;

	@FindBy(id = "billing_first_name")
	@CacheLookup
	public WebElement Checkout_Page_FirstName_TextBox;

	@FindAll({ @FindBy(css = "#billing_last_name_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[1]/div[1]/div[1]/div/p[3]/label") })
	@CacheLookup
	public WebElement Checkout_Page_LastName_label;

	/* @FindBy(id = "billing_last_name")
	@CacheLookup
	public WebElement Checkout_Menu_Personal_Data_LastName_TextBox;*/
	
	@FindBy(id = "billing_last_name")
	@CacheLookup
	public WebElement Checkout_Page_LastName_TextBox;

	@FindAll({ @FindBy(css = "#billing_company_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[1]/div/div/p[3]/label") })
	@CacheLookup
	public WebElement Checkout_Page_Company_Name_Label;

	@FindBy(id = "billing_company")
	@CacheLookup
	public WebElement Checkout_Menu_Personal_Data_Company_Name_Textbox;

	@FindAll({ @FindBy(css = "#billing_country_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[1]/div/div/p[4]/label") })
	@CacheLookup
	public WebElement Checkout_Page_Country_Label;

	@FindBy(id = "select2-billing_country-container")
	@CacheLookup
	public WebElement Checkout_Page_Country_Dropdown;

	@FindAll({ @FindBy(css = "#billing_address_1_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[1]/div/div/p[5]/label") })
	@CacheLookup
	public WebElement Checkout_Page_StreetAddress_Label;

	@FindBy(id = "billing_address_1")
	@CacheLookup
	public WebElement Checkout_Page_StreetAddress_TextBox1;

	@FindBy(id = "billing_address_2_field")
	@CacheLookup
	public WebElement Checkout_Page_StreetAddress_TextBox2;

	@FindAll({ @FindBy(css = "#billing_city_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[1]/div/div/p[8]/label") })
	@CacheLookup
	public WebElement Checkout_Page_Town_city_label;

	@FindBy(id = "billing_city")
	@CacheLookup
	public WebElement Checkout_Page_Town_city_Textbox;

	@FindAll({ @FindBy(css = "#billing_country_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[1]/div[1]/div[1]/div/p[9]/label") })
	@CacheLookup
	public WebElement Checkout_Page_State_Country_label;

	@FindBy(id = "select2-billing_country-container")
	@CacheLookup
	public WebElement Checkout_Page_State_Country_Dropdown;

	@FindAll({ @FindBy(css = "#billing_postcode_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[1]/div[1]/div/div/p[7]/label") })
	@CacheLookup
	public WebElement Checkout_Page_Postcode_Zip_label;

	@FindBy(id = "billing_postcode")
	@CacheLookup
	public WebElement Checkout_Page_Postcode_Zip_TextBox;

	@FindAll({ @FindBy(css = "#billing_phone_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div/div/div/article/div/div/form[2]/div[1]/div[1]/div/div/p[10]/label") })
	@CacheLookup
	public WebElement Checkout_Page_Phone_Optional_label;

	@FindBy(id = "billing_phone")
	@CacheLookup
	public WebElement Checkout_Page_Phone_Optional_TextBox;

	@FindAll({ @FindBy(css = "#billing_phone_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[1]/div/div/div/article/div/div/form[2]/div[1]/div[1]/div/div/p[10]/label") })
	@CacheLookup
	public WebElement Email_Address_Label;

	@FindBy(id = "billing_email")
	@CacheLookup
	public WebElement Email_Address_TextBox;

	// ****************(Front-END)

	// Credit card(With_User_Logged_In)

	@FindBy(id = "payment_method_novalnet_cc")
	//@CacheLookup
	public WebElement Creditcard_Radio_Button;

	@FindAll({
			@FindBy(css = "html body.page-template-default.page.page-id-13.logged-in.wp-embed-responsive.woocommerce-checkout.woocommerce-page.woocommerce-js.single-author div#page.hfeed.site div#main.site-main div#primary.content-area div#content.site-content article#post-13.post-13.page.type-page.status-publish.hentry div.entry-content div.woocommerce form.checkout.woocommerce-checkout div#order_review.woocommerce-checkout-review-order div#payment.woocommerce-checkout-payment ul.wc_payment_methods.payment_methods.methods li.wc_payment_method.payment_method_novalnet_cc label"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[1]/label") })
	//@CacheLookup
	public WebElement Creditcard_Title;

	@FindAll({ @FindBy(css = "div.payment_method_novalnet_cc > p:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[1]/div/p") })
	//@CacheLookup
	public WebElement Creditcard_Text;

	@FindAll({ @FindBy(css = "#card_holder_lbl"), @FindBy(xpath = "//*[@id=\"card_holder_lbl\"]") })
	//@CacheLookup
	public WebElement Credit_Holder_Name_Label;

	@FindBy(id = "card_holder")
	//@CacheLookup
	public WebElement Credit_Holder_Name_TextBox;

	@FindAll({ @FindBy(css = "#card_number_lbl"), @FindBy(xpath = "//*[@id=\"card_number_lbl\"]") })
	//@CacheLookup
	public WebElement Credit_Number;

	@FindBy(id = "card_number")
	//@CacheLookup
	public WebElement Credit_Number_TextBox;

	@FindBy(id = "expiry_date_lbl")
	//@CacheLookup
	public WebElement Expiry_Date_Label;

	@FindBy(id = "expiry_date")
	//@CacheLookup
	public WebElement Expiry_Date_TextBox;

	@FindBy(id = "cvc_lbl")
	//@CacheLookup
	public WebElement CVC_Label;

	@FindBy(id = "cvc")
	//@CacheLookup
	public WebElement CVC_TextBox;

	@FindBy(id = "cvc_hint_span")
	//@CacheLookup
	public WebElement What_Is_This_Link;

	// Checkout page information

	@FindAll({ @FindBy(css = ".woocommerce-order-overview__order > strong:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/ul/li[1]/strong") })
	@CacheLookup
	public WebElement Checkout_Page_Order_no;

	@FindAll({ @FindBy(css = ".woocommerce-order-overview__date > strong:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/ul/li[2]/strong") })
	@CacheLookup
	public WebElement Date;

	@FindAll({ @FindBy(css = ".woocommerce-order-overview__email > strong:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/ul/li[3]/strong") })
	@CacheLookup
	public WebElement Email;

	@FindAll({ @FindBy(css = ".woocommerce-order-overview__total > strong:nth-child(1) > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/ul/li[4]/strong/span") })
	@CacheLookup
	public WebElement Total;

	@FindAll({ @FindBy(css = ".woocommerce-order-overview__payment-method > strong:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/ul/li[5]/strong") })
	@CacheLookup
	public WebElement Payment_Method;

	// Order details

	@FindAll({ @FindBy(css = ".woocommerce-table__product-total > span:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[1]/table/tbody/tr/td[2]/span") })
	@CacheLookup
	public WebElement Product_Total;

	@FindAll({ @FindBy(css = ".woocommerce-table > tfoot:nth-child(3) > tr:nth-child(1) > td:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[1]/table/tfoot/tr[1]/td") })
	@CacheLookup
	public WebElement Order_Details_SubTotal;

	@FindAll({ @FindBy(css = ".woocommerce-table > tfoot:nth-child(3) > tr:nth-child(2) > td:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[1]/table/tfoot/tr[2]/td") })
	@CacheLookup
	public WebElement Shipping;

	@FindAll({ @FindBy(css = ".woocommerce-table > tfoot:nth-child(3) > tr:nth-child(3) > td:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[1]/table/tfoot/tr[3]/td") })
	@CacheLookup
	public WebElement Order_Details_Payment_Method;

	@FindAll({ @FindBy(css = ".woocommerce-table > tfoot:nth-child(3) > tr:nth-child(4) > td:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[1]/table/tfoot/tr[4]/td") })
	@CacheLookup
	public WebElement Order_Details_Total;

	@FindAll({ @FindBy(css = ".woocommerce-table > tfoot:nth-child(3) > tr:nth-child(5) > td:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[1]/table/tfoot/tr[5]/td") })
	@CacheLookup
	public WebElement Note;

	@FindAll({ @FindBy(css = "div.woocommerce-column:nth-child(1) > h2:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[2]/section/div[1]/h2") })
	@CacheLookup
	public WebElement Billing_Adress_label;

	@FindAll({ @FindBy(css = "div.woocommerce-column:nth-child(2) > h2:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[2]/section/div[2]/h2") })
	@CacheLookup
	public WebElement Shipping_Adress_label;

	@FindAll({ @FindBy(css = "div.woocommerce-column:nth-child(1) > h2:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[2]/section/div[1]/address") })
	@CacheLookup
	public WebElement Billing_Adress_Template;

	@FindAll({ @FindBy(css = "div.woocommerce-column:nth-child(2) > address:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[2]/section/div[2]/address") })
	@CacheLookup
	public WebElement Shipping_Adress_Template;

	// Direct Debit Sepa

	@FindBy(id = "payment_method_novalnet_sepa")
	//@CacheLookup
	public WebElement Sepa_Radio_button;

	@FindAll({ @FindBy(css = "li.wc_payment_method:nth-child(2) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[2]/label") })
	//@CacheLookup
	public WebElement Sepa_Label;

	@FindAll({ @FindBy(css = "div.payment_method_novalnet_sepa > p:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[2]/div/p") })
	//@CacheLookup
	public WebElement Sepa_Account_holder_Label;

	@FindBy(id = "novalnet_sepa_account_holder")
	//@CacheLookup
	public WebElement Sepa_Account_holder_TextBox;

	@FindAll({ @FindBy(css = "#novalnet_sepa_iban_field > label:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[2]/div/div[1]/p[2]/label") })
	//@CacheLookup
	public WebElement Iban_Label;

	/* @FindBy(id = "novalnet_sepa_iban")
	@CacheLookup
	public WebElement Iban_TextBox;*/
	
/* **Buvana code starts**/
	@FindBy(id = "novalnet_sepa_iban")
	public WebElement Sepa_Iban_TextBox;
	
	@FindBy(id = "novalnet_sepa_dob")
	public WebElement Sepa_DOB_Textbox;
	
	@FindAll({ @FindBy(css = "#novalnet_sepa_iban_field > label:nth-child(1)"),
		@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[2]/div/div[1]/p[2]/label") })
	public WebElement Sepa_Telephone_Number_Label;	
	
	@FindBy(id = "novalnet_sepa_pin_by_tel")
	public WebElement Sepa_Telephone_Number_Textbox;
	
	@FindAll({ @FindBy(css = "#novalnet_sepa_pin_field > label"),
		@FindBy(xpath = "//*[@id=\"novalnet_sepa_pin_field\"]/label") })
	public WebElement Sepa_Transaction_Pin_Number_Label;
	
	@FindAll({	@FindBy(id = "novalnet_sepa_pin"),
		@FindBy(name = "novalnet_sepa_pin"),
		@FindBy(css = "#novalnet_sepa_pin"),
		@FindBy(xpath = "//*[@id=\"novalnet_sepa_pin\"]")
		
	})
	public WebElement Sepa_Transaction_Pin_Number_Textbox;
	
	
/* **Buvana code ends**/


	@FindAll({ @FindBy(css = "#novalnet-sepa-mandate > strong:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[2]/div/a/strong") })
	//@CacheLookup
	public WebElement Sepa_Notification;

	@FindBy(id = "place_order")
	//@CacheLookup
	public WebElement Place_Order;

	// Invoice

	/* @FindAll({ @FindBy(css = "li.wc_payment_method:nth-child(3) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[3]/label") })
	@CacheLookup
	public WebElement Invoice_Label; */
	
	/* ***** Buvana code starts****/
	@FindAll({ @FindBy(css = "#payment > ul > li > label"),
		@FindBy(xpath = "//*[@id=\"payment\"]/ul/li/label") })
@CacheLookup
public WebElement Invoice_Label;	

	/* ***** Buvana code ends****/
	
	@FindBy(id = "payment_method_novalnet_invoice")
	@CacheLookup
	public WebElement Invoice_Radio_Button;

	@FindAll({ @FindBy(css = "div.payment_method_novalnet_invoice > p:nth-child(1)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[3]/div/p") })
	@CacheLookup
	public WebElement Invoice_Notification;

	// Prepayment

	@FindBy(id = "payment_method_novalnet_prepayment")
	@CacheLookup
	public WebElement Prepayment_Radio_Button;

	/* @FindAll({ @FindBy(css = "li.wc_payment_method:nth-child(4) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[4]/label") })
	@CacheLookup
	public WebElement Prepayment_Label;*/
	
	/* ***** Buvana code starts****/
	@FindAll({ @FindBy(css = "#payment > ul > li.wc_payment_method.payment_method_novalnet_prepayment > label"),
		@FindBy(xpath = "//*[@id=\"payment\"]/ul/li[3]/label") })
@CacheLookup
public WebElement Prepayment_Label;
	/* ***** Buvana code ends****/

	@FindAll({ @FindBy(css = "div.payment_method_novalnet_prepayment"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[4]/div") })
	@CacheLookup
	public WebElement Prepayment_Notification;

	@FindAll({ @FindBy(css = ".button"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/div/section[1]/p/a") })
	@CacheLookup
	public WebElement Prepayment_Orderagain_Button;

	// Paypal

	@FindBy(id = "payment_method_novalnet_paypal")
	@CacheLookup
	public WebElement Paypal_Radio_Button;

	/* @FindAll({ @FindBy(css = "li.wc_payment_method:nth-child(5) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[5]/label") })
	@CacheLookup
	public WebElement Paypal_Label;*/

	/* ********* Buvana code starts*********/
	
	@FindAll({ @FindBy(css = "#payment > ul > li.wc_payment_method.payment_method_novalnet_paypal > label"),
		@FindBy(xpath = "//*[@id=\"payment\"]/ul/li[2]/label") })
@CacheLookup
public WebElement Paypal_Label;

	/* ********* Buvana code ends*********/


	@FindAll({ @FindBy(css = "div.payment_method_novalnet_paypal"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[5]/div") })
	@CacheLookup
	public WebElement Paypal_Notification;

	// 3rd Pary Moved

	@FindBy(id = "email")
	@CacheLookup
	public WebElement Paypal_Email;

	@FindBy(id = "password")
	@CacheLookup
	public WebElement Paypal_Password;

	@FindBy(id = "btnLogin")
	@CacheLookup
	public WebElement Paypal_Login_Button;

	@FindBy(id = "confirmButtonTop")
	@CacheLookup
	public WebElement Paypal_Confirm_Button;

	// Alert

	// Instant bank transfer

	@FindBy(id = "payment_method_novalnet_instantbank")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Radio_Button;

	@FindAll({ @FindBy(css = "li.wc_payment_method:nth-child(6) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[6]/label") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Label;

	@FindAll({ @FindBy(css = "div.payment_method_novalnet_instantbank"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[6]/div") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Notification;

	// Redirected to 3rd party

	@FindBy(id = "BankCodeSearch")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_BankName;

	@FindAll({ @FindBy(css = ".button-right"), @FindBy(xpath = "/html/body/main/section/form/div[3]/button") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Next_Button;

	@FindBy(id = "BackendFormLOGINNAMEUSERID")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Account_Number;

	@FindBy(id = "BackendFormUSERPIN")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_PIN;

	@FindAll({ @FindBy(css = ".button-right"), @FindBy(xpath = "/html/body/main/section/form/div[3]/button") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Demo_Page_Next_Button;

	@FindBy(id = "account-1")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Select_Account;

	@FindAll({ @FindBy(css = ".button-right"), @FindBy(xpath = "/html/body/main/section/form/div[3]/button") })
	@CacheLookup
	public WebElement Instant_Bank_Transfer_Account_Select_Next_Button;

	@FindBy(id = "BackendFormTan")
	@CacheLookup
	public WebElement Instant_Bank_Transfer_TAN;

	@FindAll({ @FindBy(css = ".button-right"), @FindBy(xpath = "/html/body/main/section/form/div[3]/button") })
	@CacheLookup
	public WebElement Instant_Bank_TAN_Next_Button;

	// Alert

	// IDEAL

	@FindBy(id = "payment_method_novalnet_ideal")
	@CacheLookup
	public WebElement Ideal_Radio_Button;

	@FindAll({ @FindBy(css = "li.wc_payment_method:nth-child(7) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[7]/label") })
	@CacheLookup
	public WebElement Ideal_Label;

	@FindAll({ @FindBy(css = "div.payment_method_novalnet_ideal"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[7]/div") })
	@CacheLookup
	public WebElement Ideal_Description;

	// Redirected to 3rd party

	/* @FindAll({ @FindBy(css = ".btn-primary"),
			@FindBy(xpath = "/html/body/form[2]/div[1]/div[1]/div/div[2]/div/div[1]/div/div[2]/div[2]/div[2]/button") })
	@CacheLookup
	public WebElement Ideal_Choose_The_Bank_Next_Button;

	@FindAll({ @FindBy(css = ".btn-primary"),
			@FindBy(xpath = "/html/body/form[2]/div[1]/div[1]/div/div[2]/div/div[1]/div/div[2]/div[2]/div[2]/button") })
	@CacheLookup
	public WebElement Ideal_Login_Page_Next_Button;

	@FindAll({ @FindBy(css = ".btn-primary"),
			@FindBy(xpath = "/html/body/form[2]/div[1]/div[1]/div/div[2]/div/div[1]/div/div[2]/div[2]/div[2]/button") })
	@CacheLookup
	public WebElement Ideal_TAN_Page_Next_Button;

	@FindAll({ @FindBy(css = ".btn-primary"),
			@FindBy(xpath = "/html/body/form[2]/div[1]/div[1]/div/div[2]/div/div[1]/div/div[2]/div[2]/div[2]/button") })
	@CacheLookup
	public WebElement Ideal_Payment_Successful_Button;*/
	

/* **Vachan code starts*/
	@FindAll({
		@FindBy(css = "#col-transaction-payment > div > div.panel-body > div:nth-child(2) > div:nth-child(2) > button"),
		@FindBy(xpath = "//*[@id=\"col-transaction-payment\"]/div/div[2]/div[2]/div[2]/button") })
public WebElement Ideal_Choose_The_Bank_Next_Button;

@FindAll({
		@FindBy(css = "#col-transaction-payment > div > div.panel-body > div:nth-child(2) > div:nth-child(2) > button"),
		@FindBy(xpath = "//*[@id=\"col-transaction-payment\"]/div/div[2]/div[2]/div[2]/button") })
public WebElement Ideal_Login_Page_Next_Button;

@FindAll({
		@FindBy(css = "#col-transaction-payment > div > div.panel-body > div:nth-child(2) > div:nth-child(2) > button"),
		@FindBy(xpath = "//*[@id=\"col-transaction-payment\"]/div/div[2]/div[2]/div[2]/button") })
public WebElement Ideal_TAN_Page_Next_Button;

@FindAll({ @FindBy(css = "#sim-container > div > div.container > div > div:nth-child(3) > button"),
		@FindBy(xpath = "//*[@id=\"sim-container\"]/div/div[2]/div/div[2]/button") })
public WebElement Ideal_Payment_Successful_Button;

/* **Vachan code ends*/


/* **Buvana code starts*/

@FindAll({ @FindBy(className = "btn btn-secondary"),
	@FindBy(css ="#col-transaction-payment > div > div.panel-body > div:nth-child(2) > div:nth-child(1) > button"),
	@FindBy(xpath = "//*[@id=\"col-transaction-payment\"]/div/div[2]/div[2]/div[1]/button") })
public WebElement Ideal_Payment_Abort_Button;

/* **Buvana code ends*/

	// Alert

	// EPS

	@FindBy(id = "payment_method_novalnet_eps")
	@CacheLookup
	public WebElement EPS_Radio_Button;

	@FindAll({ @FindBy(css = "li.wc_payment_method:nth-child(8) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[8]/label") })
	@CacheLookup
	public WebElement EPS_Title;

	@FindAll({ @FindBy(css = "div.payment_method_novalnet_eps"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[8]/div") })
	@CacheLookup
	public WebElement EPS_Description;

	// 3rd party redirected

	/*@FindBy(id = "selected_bank_url")
	@CacheLookup
	public WebElement EPS_Enter_The_Bic;*/
	
	@FindBy(id = "tags")
	@CacheLookup
	public WebElement EPS_Enter_The_Bic;

	@FindAll({
			@FindBy(css = "body > center:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(3) > th:nth-child(1) > form:nth-child(2) > input:nth-child(52)"),
			@FindBy(xpath = "/html/body/center/table/tbody/tr[3]/th/form/input[44]") })
	@CacheLookup
	public WebElement EPS_Proceed_To_Payment;

	@FindBy(id = "sbtnLogin")
	@CacheLookup
	public WebElement EPS_Login_Page_Login_Button;

	/* @FindBy(id = "sbtnLogin")
	@CacheLookup
	public WebElement EPS_Confirmation_Page_Login_Button;*/

	@FindBy(id = "sbtnSignSingle")
	@CacheLookup
	public WebElement EPS_TAN_Confirmation_Button;

	@FindBy(id = "mobileTan")
	@CacheLookup
	public WebElement EPS_TAN_Confirmation_TestBox;

	@FindBy(id = "sbtnOk")
	@CacheLookup
	public WebElement EPS_TAN_Confirmation_OK_Button;

	@FindAll({ @FindBy(css = ".actions > input:nth-child(1)"),
			@FindBy(xpath = "/html/body/div[3]/div[2]/div/div[3]/div[4]/div/form/div[2]/input") })
	@CacheLookup
	public WebElement EPS_OK_Button;

	// Giropay

	@FindBy(id = "payment_method_novalnet_giropay")
	@CacheLookup
	public WebElement Giropay_Radio_Button;

	@FindAll({ @FindBy(css = "li.wc_payment_method:nth-child(9) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[9]/label") })
	@CacheLookup
	public WebElement Giropay_Title;

	@FindAll({ @FindBy(css = "div.payment_method_novalnet_giropay"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[9]/div") })
	@CacheLookup
	public WebElement Giropay_Description;

	// Redirecting to 3rd Party Server

	@FindBy(id = "giropay_autocomplete")
	@CacheLookup
	public WebElement Giropay_Select_your_Bank;

	@FindBy(id = "submitButton")
	@CacheLookup
	public WebElement Giropay_Select_your_Bank_Continue_Button;

	@FindAll({ @FindBy(css = "input.btn-primary"),
			@FindBy(xpath = "/html/body/section/div/div/div[2]/div[1]/div/form/div/div[2]/div/input") })
	@CacheLookup
	public WebElement Giropay_Ok_Button;

	@FindAll({ @FindBy(css = ".osppbuttonlinklast"),
			@FindBy(xpath = "/html/body/section/div/div/div/div[2]/div/form/div[6]/div[2]/input") })
	@CacheLookup
	public WebElement Giropay_Confirmation_Ok_Button;

	@FindBy(id = "lhC70FCud")
	@CacheLookup
	public WebElement Giropay_TAN;

	@FindAll({ @FindBy(css = "div.buttonWrap:nth-child(5) > input:nth-child(1)"),
			@FindBy(xpath = "/html/body/section/div/div/div/div[2]/div[2]/div[3]/input") })
	@CacheLookup
	public WebElement Giropay_Confirmation_Button;

	// Barzahlen

	@FindBy(id = "payment_method_novalnet_barzahlen")
	@CacheLookup
	public WebElement Barzahlen_Radio_Button;

	/* @FindAll({ @FindBy(css = "li.wc_payment_method:nth-child(11) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[11]/label") })
	@CacheLookup
	public WebElement Barzahlen_Label;*/
	
	/* ********Buvana code starts******/
	
	@FindAll({ @FindBy(css = "#payment > ul > li.wc_payment_method.payment_method_novalnet_barzahlen > label"),
		@FindBy(xpath = "//*[@id=\"payment\"]/ul/li[5]/label") })
@CacheLookup
public WebElement Barzahlen_Label;
	
	/* ********Buvana code ends******/


	@FindAll({ @FindBy(css = "div.payment_method_novalnet_barzahlen"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[11]/div") })
	@CacheLookup
	public WebElement Barzahlen_Description;

	// Przelewy24

	@FindBy(id = "payment_method_novalnet_przelewy24")
	@CacheLookup
	public WebElement Przelewy24_Radio_Button;

	/* @FindAll({ @FindBy(css = "li.wc_payment_method:nth-child(10) > label:nth-child(2)"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[10]/label") })
	@CacheLookup
	public WebElement Przelewy24_Label;*/
	
	/* ********* Buvana code starts*********/
	@FindAll({ @FindBy(css = "#payment > ul > li.wc_payment_method.payment_method_novalnet_przelewy24 > label"),
			@FindBy(xpath = "//*[@id=\"payment\"]/ul/li[4]/label") })
	public WebElement Przelewy24_Label;
	
	/* ********* Buvana code ends*********/

	@FindAll({ @FindBy(css = "div.payment_method_novalnet_przelewy24"),
			@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[10]/div") })
	@CacheLookup
	public WebElement Przelewy24_Description;

	// Redirected to 3rd party server

	@FindAll({
			@FindBy(css = "div.container:nth-child(3) > div:nth-child(3) > section:nth-child(1) > div:nth-child(1) > a:nth-child(1)"),
			@FindBy(xpath = "/html/body/main/div[2]/div[3]/section/div/a[1]") })
	@CacheLookup
	public WebElement Przelewy24_Mbank_Tranfer;

	@FindAll({ @FindBy(css = ".btn"), @FindBy(xpath = "/html/body/div/div/div/main/div/div/form/button") })
	@CacheLookup
	public WebElement Przelewy24_SandBox_Login_button;

	@FindBy(id = "pay_by_link_pay")
	@CacheLookup
	public WebElement Przelewy24_Pay_button;
	
	//Front-end login
	@FindBy(id = "username")
	@CacheLookup
	public WebElement FE_Login_Username;
	
	@FindBy(id = "password")
	@CacheLookup
	public WebElement FE_Login_Password;
	
	@FindAll({
		@FindBy(css = ".woocommerce-button"),
		@FindBy(xpath = "/html/body/div/div/div/div/article/div/div/form/p[3]/button") })
	@CacheLookup
	public WebElement FE_Login_Button;
	
	@FindAll({
		@FindBy(css = "#post-8 > div > div > div > p"),
		@FindBy(xpath = "//*[@id=\"post-8\"]/div/div/div/p") })
	//@CacheLookup
	public WebElement FE_Thank_You_Page_Text;
	
	/*@FindAll({ @FindBy(css = "#post-57 > div > div > form > table > tbody > tr.woocommerce-cart-form__cart-item.cart_item > td.product-remove > a"),
		@FindBy(xpath = "//*[@id=\"post-57\"]/div/div/form/table/tbody/tr[1]/td[1]/a") })				
	@CacheLookup
	public WebElement Cart_Clear;*/
	
	// Buvana code starts
	@FindAll({
			@FindBy(css = "#post-7 > div > div > form > table > tbody > tr.woocommerce-cart-form__cart-item.cart_item > td.product-remove > a"),		
			@FindBy(xpath = "//*[@id=\"post-7\"]/div/div/form/table/tbody/tr[1]/td[1]/a") })
	//@CacheLookup
	public WebElement Cart_Clear;
	

		@FindBy(className = "remove")	
//@CacheLookup
public WebElement Remove_Cart;
	

	// Buvana code ends
	
	// Buvana code starts
	@FindAll({
			@FindBy(css = "#post-8 > div > div > form.checkout.woocommerce-checkout > div.woocommerce-NoticeGroup.woocommerce-NoticeGroup-checkout > ul"),
			@FindBy(xpath = "//*[@id=\"post-8\"]/div/div/form[2]/div[1]/ul") })
	//@CacheLookup
	public WebElement Woocommerce_Checkout_Error;
	
	@FindAll({
		@FindBy(css = "#post-8 > div > div > div:nth-child(1) > div"),
		@FindBy(xpath = "//*[@id=\"post-8\"]/div/div/div[1]/div") })
//@CacheLookup
public WebElement Woocommerce_Checkout_Notice;	

	// Buvana code ends
	@FindAll({
			@FindBy(css = "#woocommerce_errors > p"),
			@FindBy(xpath = "//*[@id=\"woocommerce_errors\"]/p") })
	//@CacheLookup
	public WebElement Global_Config_Error;
	
	@FindAll({
		@FindBy(css = "#woocommerce_errors > p"),
		@FindBy(xpath = "//*[@id=\"woocommerce_errors\"]/p") })
//@CacheLookup
public WebElement Payment_Page_Error;
	
	@FindAll({
		@FindBy(css = "#wp-admin-bar-my-account > a > img"),
		@FindBy(xpath = "//*[@id=\"wp-admin-bar-my-account\"]/a/img") })
public WebElement Woocommerce_Shop_Admin_Image;
	
	@FindAll({
		@FindBy(css = "#wp-admin-bar-logout"),
		@FindBy(xpath = "//*[@id=\"wp-admin-bar-logout\"]"),
		@FindBy(linkText = "Log Out")})
public WebElement Woocommerce_LogOut;	
	


		
	// Buvana code ends

	/* **Peter*************/
	
	@FindAll({ @FindBy(xpath = "//*[@id=\"mainform\"]/nav/a[8]"),
			@FindBy(css = "#mainform > nav > a.nav-tab.nav-tab-active") })
	//@CacheLookup
	public WebElement Novalnet_Global_Config_Tab;

	@FindAll({ @FindBy(linkText = "Settings"), @FindBy(css = "#toplevel_page_woocommerce > ul > li:nth-child(6) > a") })
	//@CacheLookup
	public WebElement WooCommerce_Settings;

	@FindAll({ @FindBy(css = "#toplevel_page_woocommerce > a > div.wp-menu-name"),
			@FindBy(id = "toplevel_page_woocommerce") })
	//@CacheLookup
	public WebElement WooCommerce;
	
	/* **Peter*************/
	
	//Buvana
	@FindAll({
		@FindBy(css = "#post-8 > header > h1"),
		@FindBy(xpath = "//*[@id=\"post-8\"]/header/h1") })
	//@CacheLookup
	public WebElement Checkout_Page;
	//Buvana
	
	//========================== Card portal =================

	// Novalnet card portal
	@FindAll({ @FindBy(id = "tid"), @FindBy(name = "tid"), @FindBy(css = "#tid"), @FindBy(xpath = "//*[@id=\"tid\"]") })
	public WebElement Cardportal_TID_Textbox;

	@FindAll({ @FindBy(name = "tidsubmit"),
	@FindBy(css = "#tidform_id_de > table > tbody > tr:nth-child(4) > td > input"),
	@FindBy(xpath = "//*[@id=\"tidform_id_de\"]/table/tbody/tr[4]/td/input") })
	public WebElement Cardportal_Submit;
	
	
	

// Payment Details in Novalnet card portal

	// **************************************************************************

	// Amount verification WebElement
	@FindBy(css = "#payment_formula_checkout_page > div > div:nth-child(4) > div > div > div > label")
	public WebElement GetAmountFromApplication;

	@FindBy(css = "body > div > div.inner-page > div:nth-child(4) > div:nth-child(2) > table > tbody > tr:nth-child(3) > td.color-text")
	public WebElement GetAmountFromCardPortal;

	// **************************************************************************

	// Payment Details in Novalnet card portal
	/* @FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(4) > div:nth-child(2) > table > tbody > tr:nth-child(1) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]") })
	public WebElement Cardportal_Payment_Name;*/
	
	@FindAll({
			@FindBy(css = "body > div > div.inner-page > div:nth-child(4) > div:nth-child(2) > table > tbody > tr:nth-child(1) > td.color-text"),
			@FindBy(xpath = "/html/body/div/div[2]/div[4]/div[2]/table/tbody/tr[1]/td[2]") })
	public WebElement Cardportal_Payment_Name;
	

	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(4) > div:nth-child(2) > table > tbody > tr:nth-child(2) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[4]/div[2]/table/tbody/tr[2]/td[2]") })
	public WebElement Cardportal_amount;
	// **************************************************************************

	// Booking Details in Novalnet card portal
	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(4) > div.custmor-textbox.line.rightbor > table > tbody > tr:nth-child(1) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[4]/div[1]/table/tbody/tr[1]/td[2]") })
	public WebElement Booking_Date;

	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(4) > div.custmor-textbox.line.rightbor > table > tbody > tr:nth-child(2) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[4]/div[1]/table/tbody/tr[2]/td[2]") })
	public WebElement Booking_Time;

	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(4) > div.custmor-textbox.line.rightbor > table > tbody > tr:nth-child(3) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[4]/div[1]/table/tbody/tr[3]/td[2]") })
	public WebElement Booking_IP;
	
	/* @FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(4) > div.custmor-textbox.line.rightbor > table > tbody > tr:nth-child(4) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[4]/div[1]/table/tbody/tr[4]/td[2]") })
	public WebElement Status_Code;*/
	
	@FindAll({
			@FindBy(css = "body > div > div.inner-page > div:nth-child(4) > div.custmor-textbox.line.rightbor > table > tbody > tr:nth-child(6) > td.color-text"),
			@FindBy(xpath = "/html/body/div/div[2]/div[4]/div[1]/table/tbody/tr[6]/td[2]") })
	public WebElement Status_Code;

	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(4) > div.custmor-textbox.line.rightbor > table > tbody > tr:nth-child(5) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[4]/div[1]/table/tbody/tr[5]/td[2]") })
	public WebElement Status_desc;
	// **************************************************************************

	// Personal Informtion in Novalnet card portal
	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(2) > div.custmor-textbox.line > table > tbody > tr:nth-child(2) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[2]/div[1]/table/tbody/tr[2]/td[2]") })
	public WebElement Cardportal_First_name;

	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(2) > div.custmor-textbox.line > table > tbody > tr:nth-child(3) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[2]/div[1]/table/tbody/tr[3]/td[2]") })
	public WebElement Cardportal_last_name;

	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(2) > div.custmor-textbox.line > table > tbody > tr:nth-child(4) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[2]/div[1]/table/tbody/tr[4]/td[2]") })
	public WebElement Cardportal_Street_info;

	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(2) > div.custmor-textbox.line > table > tbody > tr:nth-child(5) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[2]/div[1]/table/tbody/tr[5]/td[2]") })
	public WebElement Cardportal_City_info;

	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(2) > div.custmor-textbox.line > table > tbody > tr:nth-child(6) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[2]/div[1]/table/tbody/tr[6]/td[2]") })
	public WebElement Cardportal_Country;

	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(2) > div.custmor-textbox.line > table > tbody > tr:nth-child(7) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[2]/div[1]/table/tbody/tr[7]/td[2]") })
	public WebElement Cardportal_mail;

	@FindAll({
	@FindBy(css = "body > div > div.inner-page > div:nth-child(2) > div.custmor-textbox.line > table > tbody > tr:nth-child(8) > td.color-text"),
	@FindBy(xpath = "/html/body/div/div[2]/div[2]/div[1]/table/tbody/tr[8]/td[2]") })
	public WebElement Cardportal_telephone;

	@FindBy(css = "body > div > div.inner-page > div:nth-child(2) > div.custmor-textbox.leftbor > table > tbody > tr:nth-child(10) > td.color-text")
	public WebElement Project_url;
	
	
	// **************************************************************************
	@FindAll({
		@FindBy(css = "#post-8 > div > div > div > section.woocommerce-order-details > table > tfoot > tr:nth-child(3) > td > span"),
		@FindBy(xpath = "//*[@id=\"post-8\"]/div/div/div/section[2]/table/tfoot/tr[3]/td/span") })

public WebElement OrderDetails_TotalAmount;

@FindAll({
		@FindBy(css = "#post-8 > div > div > div > section.woocommerce-order-details > table > tfoot > tr:nth-child(4) > td > p"),
		@FindBy(xpath = "//*[@id=\"post-8\"]/div/div/div/section[2]/table/tfoot/tr[3]/td/span") })
public WebElement OrderDetails_Note_TID;

@FindAll({ @FindBy(css = "#post-8 > header > h1"), @FindBy(xpath = "//*[@id=\"post-8\"]/header/h1") })
public WebElement OrderReceived;

@FindAll({ @FindBy(xpath = "//*[@id=\"toplevel_page_woocommerce\"]/ul/li[2]/a"),
	@FindBy(css = "#toplevel_page_woocommerce > ul > li.wp-first-item > a") })
public WebElement WooCommerce_Orders;

@FindBy(xpath ="//table/tbody/tr[1]/td[3]")
public WebElement Backend_Order_Status;

@FindBy(xpath ="//table/tbody/tr[1]/td[3]")
public WebElement Frontend_Order_Status;

@FindAll({ @FindBy(css = "#post-9 > div > div > nav > ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--orders > a"),
	@FindBy(xpath = "//*[@id=\"post-9\"]/div/div/nav/ul/li[2]/a") })
public WebElement MyAccount_Orders;

@FindBy(id = "btnNext")
public WebElement PayPal_NextButton;

@FindAll({
	@FindBy(className = "btn full confirmButton continueButton"), 
	@FindBy(id = "button")})
public WebElement PayPal_Continue_button;

@FindBy(xpath ="//table/tbody/tr[1]/td[1]")
public WebElement Backend_Order_Number;


@FindAll({ @FindBy(css = "#woocommerce-order-notes > div > ul > li:nth-child(1) > div > p"),
@FindBy(xpath = "//*[@id=\"woocommerce-order-notes\"]/div/ul/li[1]/div/p") })
public WebElement BE_OrderNotes_Message;

@FindAll({
	@FindBy(css = "body > main > div:nth-child(3) > div:nth-child(3) > section > div > a:nth-child(1) > div:nth-child(1) > div > span"),
	@FindBy(xpath = "/html/body/main/div[2]/div[3]/section/div/a[1]/div[1]/div/span") })
public WebElement Przelewy24_Choose_PaymentMethod;

@FindBy(id = "username")
public WebElement Przelewy24_Login;

@FindBy(id = "password")
public WebElement Przelewy24_Password;

@FindAll({
	@FindBy(css = "#mainform > nav > a:nth-child(1)"),
	@FindBy(xpath = "//*[@id=\"mainform\"]/nav/a[1]") })
public WebElement Woocommerce_General_tab;

	@FindBy(id = "select2-woocommerce_currency-container")
	public WebElement General_Currency;
	
	@FindAll({
		@FindBy(name = "save"),
		@FindBy(className = "button-primary woocommerce-save-button") })
	public WebElement General_Save_Changes_Button;

	@FindBy(id = "adminbar-search")
	public WebElement Shopadmin_Search;

	@FindAll({ @FindBy(css = "body > span > span > span.select2-search.select2-search--dropdown > input"),
		@FindBy(xpath = "/html/body/span/span/span[1]/input") })
	public WebElement Checkout_Page_Country_Dropdown_Textbox;
	
	
	@FindBy(id = "novalnet_invoice_dob")
	public WebElement Invoice_DOB_Textbox;
	
	@FindBy(id = "AbortLink")
	public WebElement Sofort_Cancel_Link;
	
	@FindBy(id = "CancelTransaction")
	public WebElement Sofort_Cancel_Payment_Button;
	
	@FindAll({ @FindBy(css = "#post-8 > div > div > div:nth-child(1) > ul > li"),
		@FindBy(xpath = "/html/body/span/span/span[1]/input") })
	public WebElement Checkout_Page_Error_Message;
	
	@FindAll({
		@FindBy(className = "#post-9 > div > div > div > section.woocommerce-order-details > table > tfoot > tr:nth-child(3) > td > span"), 
		@FindBy(xpath = "//*[@id=\"post-9\"]/div/div/div/section[1]/table/tfoot/tr[3]/td/span")})
	public WebElement MyAccount_OrderDetails_TotalAmount;	
	
	@FindAll({
		@FindBy(css = "#post-9 > div > div > div > section.woocommerce-order-details > table > tfoot > tr:nth-child(4) > td > p"),
		@FindBy(xpath = "//*[@id=\"post-9\"]/div/div/div/section[2]") })
public WebElement MyAccount_OrderDetails_Note_TID;
	
	@FindBy(xpath ="//table/tbody/tr[1]/td[1]")
	public WebElement Frontend_Order_Number;

	/* Thamaraiselvan codes starts **/

	//lable for eps
	@FindBy(xpath="/html/body/div/div/div/div/article/div/div/form[2]/div[2]/div/ul/li[4]/label/a/img")
	@CacheLookup
	public WebElement EPS_Label;

	@FindBy(id = "sbtnSign")
	@CacheLookup
	public WebElement EPS_Confirmation_Page_Login_Button;

	//Eps order Abort button

		@FindBy(css="#epsLogo > form > div > div > input[type=submit]")
	    	@CacheLookup
	public WebElement Eps_Payment_Abort_Button;
	 
		//Eps conform  button for order Abort
	@FindAll({
		@FindBy(css="#content > div.inputarea > div > form > div.actions > input[type=submit]"),
		@FindBy(xpath="//*[@id=\"content\"]/div[2]/div/form/div[2]/input")})
	@CacheLookup
		public WebElement Eps_Abort_Confiorm_Button;


	/* Thamaraiselvan codes ends *****/
	
	/* *********Thamaraiselvan codes stars *******/
	@FindAll({
	@FindBy(xpath="#payment > ul > li.wc_payment_method.payment_method_novalnet_giropay > label > a > img"),
	@FindBy(xpath="//*[@id=\"payment\"]/ul/li[9]/label/a/img")})
	@CacheLookup
	public WebElement Giropay_Label;
	
	@FindBy(id="tags")
	@CacheLookup
	public WebElement Giropay_Enter_The_Bic;
	
	@FindBy(id = "XjAAADYfePJarVlf")
	public WebElement Giropay_Username;
	
	@FindBy(id = "WdGSSNPyXoBJOdNW")
	public WebElement Giropay_PINCode;
	
	@FindAll({ @FindBy(name = "weiterButton"), @FindBy(xpath = "//*[@id=\"84457d95be99cd84\"]/div[6]/div[2]/input"),
		@FindBy(css = "#\\38 4457d95be99cd84 > div.osppbuttonbereich > div.buttonWrap > input") })
public WebElement Giropay_weiter;
	
	@FindAll({
		@FindBy(css = "body > section > div > div > div > div.stdBoxLayout > div.osppbuttonbereich > div.buttonWrap.hiddenXsSpk > input"),
		@FindBy(xpath = "/html/body/section/div/div/div/div[2]/div[2]/div[3]/input") })
public WebElement Giropay_PayNow;
	
	
	
	//Giropay Abort button
@FindAll({	
	@FindBy(css="body > header > div.cookie > div > button:nth-child(1)"),
	@FindBy(xpath="/html/body/header/div[1]/div/button[1]")})
	@CacheLookup
	public WebElement Giropay_Annehmen_POpUp_Botton;
	
	@FindAll({
	@FindBy(css="body > section > div > div > div.contentcontainerMainLayout > div.loginformgrund > div > form > div > div.stdBoxLayout.osppinfoinhalt.floatLeft > p:nth-child(3) > button > span.addStyleOption.hideOnMobile"),
	@FindBy(xpath="/html/body/section/div/div/div[2]/div[1]/div/form/div/div[2]/p[2]/button/span[1]")})
	@CacheLookup
	public WebElement Giropay_Payment_Abort_Button;
	
	@FindAll({
	@FindBy(css="#myModal > div.modal-dialog > div > div.modal-footer > button.btn.btn-default"),
	@FindBy(xpath="//*[@id=\"myModal\"]/div[2]/div/div[3]/button[1]")})
	@CacheLookup
	public WebElement Giropay_Abort_Confiorm_Button;
	
	@FindAll({
		@FindBy(css="body > table > tbody > tr > td > table > tbody > tr:nth-child(4) > td > table"),
		@FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[4]/td/table")})
	@CacheLookup
	public WebElement CC3D_Password_iframe;
	
	@FindAll({
	@FindBy(id = "password"),
	@FindBy(css="#password"),
	@FindBy(xpath="//*[@id=\"password\"]")})
	@CacheLookup
	public WebElement CC3D_Password_TextBox;
	
	@FindAll({
		@FindBy(css="body > table > tbody > tr > td > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(6) > td > form > div > table > tbody > tr > td > font > b > center > table > tbody > tr:nth-child(4) > td:nth-child(2) > b > input[type=submit]"),
	    @FindBy(xpath="html/body/table/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr[6]/td/form/div/table/tbody/tr/td/font/b/center/table/tbody/tr[4]/td[2]/b/input")})
	@CacheLookup
	public WebElement CC3D_Submit_Button;
	
	/* *****Thamaraiselvan codes ends ***********/

/* ******** Vendor Script ************/
	@FindAll({
			@FindBy(css = "#callback > form > table > tbody > tr:nth-child(1) > td:nth-child(2) > input[type=text]\r\n"),
			@FindBy(xpath = "//*[@id=\"callback\"]/form/table/tbody/tr[1]/td[2]/input") })
	public WebElement Vendor_Script_Url;

	@FindAll({ @FindBy(css = "#callback > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type=text]"),
			@FindBy(xpath = "//*[@id=\"callback\"]/form/table/tbody/tr[2]/td[2]/input") })
	public WebElement Vendor_Id;

	@FindAll({ @FindBy(css = "#callback > form > table > tbody > tr:nth-child(3) > td:nth-child(2) > input[type=text]"),
			@FindBy(xpath = "//*[@id=\"callback\"]/form/table/tbody/tr[3]/td[2]/input") })
	public WebElement Vendor_Auth_Code;
	
	@FindAll({ @FindBy(css = "#callback > form > table > tbody > tr:nth-child(4) > td:nth-child(2) > input[type=text]"),
			@FindBy(xpath = "//*[@id=\"callback\"]/form/table/tbody/tr[4]/td[2]/input") })
	public WebElement Product_Id;
	
	@FindAll({ @FindBy(css = "#callback > form > table > tbody > tr:nth-child(5) > td:nth-child(2) > input[type=text]"),
			@FindBy(xpath = "//*[@id=\"callback\"]/form/table/tbody/tr[5]/td[2]/input") })
	public WebElement Tariff_Id;	
	
	@FindAll({ @FindBy(css = "#callback > form > table > tbody > tr:nth-child(6) > td:nth-child(2) > div:nth-child(2) > img"),
		@FindBy(xpath = "//*[@id=\"callback\"]/form/table/tbody/tr[6]/td[2]/div[2]/img") })
	public WebElement Payment_Type_Edit_Button;
	
	@FindBy(id = "payment_type_select")
	public WebElement Payment_Type_Selectbox;
	
	@FindBy(name = "test_mode")
	public WebElement Test_Mode;
	
	@FindBy(name = "tid_payment")
	public WebElement Tid_Payment;

	@FindBy(name = "amount")
	public WebElement Amount;
	
	@FindBy(name = "currency")
	public WebElement Currency;
	
	@FindBy(name = "status")
	public WebElement Status;
	
	@FindBy(name = "tid_status")
	public WebElement Tid_Status;
	
	@FindBy(name = "tid")
	public WebElement Tid;
	
	@FindBy(name = "order_no")
	public WebElement Order_Number;
	
	@FindBy(name = "submit")
	public WebElement Execute_Button;
	
	@FindBy(className = "response_div")
	public WebElement Execution_Message;
	
	@FindAll({ @FindBy(css = "#response > label:nth-child(3) > p"),
	@FindBy(xpath = "//*[@id=\"response\"]/label[1]/p") })
	public WebElement callback_message;

	
	

}