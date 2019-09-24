/* ***************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to read excel sheet
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 *******************************************************/

package com.nn.TestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import com.nn.TestConfiguration.Utility;

public class TestInputData extends Utility {

	// This data belongs to login shop backend login
	@Test
	public Map<String, String> ExcelReader() throws IOException {

		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet = ReadSpreadSheets.getSheetAt(0);
		Map<String, String> UserData = new HashMap<String, String>();

		/*
		 * *****************************************************************************
		 * ****************************************** Storing the excel data in
		 * variable. Need to pass ID to the test cases as a user data. Example:
		 * UserData.put("UserFirstName", FirstName); UserFirstName is the ID.
		 **************************************************************************************************************************/

		// Shop backend login
		String Username_data = ReadTheSheet.getRow(5).getCell(1).getStringCellValue();
		UserData.put("ShopBackendLogin_username", Username_data);
		String Password_data = ReadTheSheet.getRow(6).getCell(1).getStringCellValue();
		UserData.put("ShopBackendLogin_password", Password_data);
		return UserData;
	}
	// This data belongs to login shop backend login
	@Test
	public Map<String, String> ExcelReader_FrontEnd() throws IOException {
		
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "/src/main/resources/TestData/NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet = ReadSpreadSheets.getSheetAt(0);
		Map<String, String> FrontEndUserData = new HashMap<String, String>();

		// Shop front end login
		String Username_data = ReadTheSheet.getRow(11).getCell(1).getStringCellValue();
		FrontEndUserData.put("ShopFrontendLogin_username", Username_data);
		String Password_data = ReadTheSheet.getRow(12).getCell(1).getStringCellValue();
		FrontEndUserData.put("ShopFrontendLogin_password", Password_data);
		ReadSpreadSheets.close();
		return FrontEndUserData;
	}
	
	// This data belongs to payment login
	@Test	
	public Map<String, String> ExcelReader_PaymentMethods() throws IOException {

		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "/src/main/resources/TestData/NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet = ReadSpreadSheets.getSheetAt(0);
		Map<String, String> UserData = new HashMap<String, String>();
		
		// Direct Debit SEPA payment method
		String SEPA_AccountHolder = ReadTheSheet.getRow(17).getCell(1).getStringCellValue();
		UserData.put("SEPAAccountHolder", SEPA_AccountHolder);
		String SEPA_Iban = ReadTheSheet.getRow(18).getCell(1).getStringCellValue();
		UserData.put("SEPAIBAN", SEPA_Iban);		
		String DateOfBirth = ReadTheSheet.getRow(97).getCell(1).getStringCellValue();
		UserData.put("dob_TextBox", DateOfBirth);	
				
		// Credit Card payment method
		String CC_CardHolder = ReadTheSheet.getRow(23).getCell(1).getStringCellValue();
		UserData.put("CreditcardCardHolder", CC_CardHolder);
		String CC_CardNumber = ReadTheSheet.getRow(24).getCell(1).getRawValue();
		UserData.put("CreditcardCardNumber", CC_CardNumber);
		String CC_ExpiryDate = ReadTheSheet.getRow(25).getCell(1).getRawValue();
		UserData.put("CreditcardExpiryDate", CC_ExpiryDate);
		String CC_CVC_CVV = ReadTheSheet.getRow(26).getCell(1).getRawValue();
		UserData.put("CreditcardCVC_CVV", CC_CVC_CVV);
		
		// Instant Bank Transfer payment method
		String Instant_BankName = ReadTheSheet.getRow(37).getCell(1).getRawValue();
		UserData.put("InstantBankName_BIC", Instant_BankName);
		String Instant_AccountNumber = ReadTheSheet.getRow(38).getCell(1).getRawValue();
		UserData.put("InstantAccountNumber", Instant_AccountNumber);
		String Instant_PINCode = ReadTheSheet.getRow(39).getCell(1).getRawValue();
		UserData.put("InstantPINCode", Instant_PINCode);
		String Instant_TAN = ReadTheSheet.getRow(40).getCell(1).getRawValue();
		UserData.put("InstantTAN", Instant_TAN);
		
		// Giropay payment method
		String GIRO_BankName = ReadTheSheet.getRow(45).getCell(1).getStringCellValue();
		UserData.put("GIROBankNameorBIC", GIRO_BankName);
		String GIRO_AccountNumber = ReadTheSheet.getRow(46).getCell(1).getStringCellValue();
		UserData.put("GIROAccountNumber", GIRO_AccountNumber);
		String GIRO_PINCode = ReadTheSheet.getRow(47).getCell(1).getRawValue();
		UserData.put("GIROPINCode", GIRO_PINCode);
		String GIRO_TAN = ReadTheSheet.getRow(48).getCell(1).getRawValue();
		UserData.put("GIROTAN", GIRO_TAN);
		
		// Przelewy24 payment method
		String Przelewy24_username = ReadTheSheet.getRow(61).getCell(1).getStringCellValue();
		UserData.put("Przelewy24UserName", Przelewy24_username);
		String Przelewy24_Password = ReadTheSheet.getRow(62).getCell(1).getStringCellValue();
		UserData.put("Przelewy24Password", Przelewy24_Password);
		
		// eps payment method
		String EPS_ChooseBank = ReadTheSheet.getRow(67).getCell(1).getStringCellValue();
		UserData.put("EPChooseBank", EPS_ChooseBank);
		
		// PayPal Login credentials
		String PayPal_UserName = ReadTheSheet.getRow(31).getCell(1).getStringCellValue();
		UserData.put("PayPalUserName", PayPal_UserName);
		String PayPal_Password = ReadTheSheet.getRow(32).getCell(1).getStringCellValue();
		UserData.put("PayPalPassword", PayPal_Password);


		ReadSpreadSheets.close();
		return UserData;
	}
	
	@Test
	public Map<String, String> ExcelReader_BackEnd() throws IOException {
		
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "/src/main/resources/TestData/NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet = ReadSpreadSheets.getSheetAt(0);
		Map<String, String> FrontEndUserData = new HashMap<String, String>();

		// Shop front end login
		String Username_data = ReadTheSheet.getRow(5).getCell(1).getStringCellValue();
		FrontEndUserData.put("ShopBackendLogin_username", Username_data);
		String Password_data = ReadTheSheet.getRow(6).getCell(1).getStringCellValue();
		FrontEndUserData.put("ShopBackendLogin_password", Password_data);
		ReadSpreadSheets.close();
		return FrontEndUserData;
	}
	
		/* ******************************************************************
		 * Read the value from excel
		 * Assign the value in array variable
		 * Pass the test data to 'card holder name' validation test case
		 *******************************************************************/
	@Test
	public String[] ExcelReader_CardholderEmptyValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardholderEmptyData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(8).getCell(2).getStringCellValue();
		CardholderEmptyData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(9).getCell(2).getRawValue();
		CardholderEmptyData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(10).getCell(2).getStringCellValue();
		CardholderEmptyData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(11).getCell(2).getRawValue();
		CardholderEmptyData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardholderEmptyData;
	}

	@Test
	public String[] ExcelReader_CardholderNumericValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardholderNumericData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(14).getCell(2).getRawValue();
		CardholderNumericData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(15).getCell(2).getRawValue();
		CardholderNumericData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(16).getCell(2).getStringCellValue();
		CardholderNumericData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(17).getCell(2).getRawValue();
		CardholderNumericData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardholderNumericData;
	}

	@Test
	public String[] ExcelReader_CardholderSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardholderSpecialcharData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(20).getCell(2).getStringCellValue();
		CardholderSpecialcharData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(21).getCell(2).getRawValue();
		CardholderSpecialcharData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(22).getCell(2).getStringCellValue();
		CardholderSpecialcharData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(23).getCell(2).getRawValue();
		CardholderSpecialcharData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardholderSpecialcharData;
	}

	@Test
	public String[] ExcelReader_CardholderAlphanumericValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardholderAlphanumericData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(26).getCell(2).getStringCellValue();
		CardholderAlphanumericData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(27).getCell(2).getRawValue();
		CardholderAlphanumericData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(28).getCell(2).getStringCellValue();
		CardholderAlphanumericData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(29).getCell(2).getRawValue();
		CardholderAlphanumericData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardholderAlphanumericData;
	}

	@Test
	public String[] ExcelReader_CardholderAlphaSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardholderAlphaSpecialcharData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(32).getCell(2).getStringCellValue();
		CardholderAlphaSpecialcharData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(33).getCell(2).getRawValue();
		CardholderAlphaSpecialcharData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(34).getCell(2).getStringCellValue();
		CardholderAlphaSpecialcharData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(35).getCell(2).getRawValue();
		CardholderAlphaSpecialcharData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardholderAlphaSpecialcharData;
	}

	@Test
	public String[] ExcelReader_CardholderNumberSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardholderNumberSpecialcharData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(38).getCell(2).getStringCellValue();
		CardholderNumberSpecialcharData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(39).getCell(2).getRawValue();
		CardholderNumberSpecialcharData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(40).getCell(2).getStringCellValue();
		CardholderNumberSpecialcharData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(41).getCell(2).getRawValue();
		CardholderNumberSpecialcharData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardholderNumberSpecialcharData;
	}

	@Test
	public String[] ExcelReader_CardholderAlphabetsValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardholderAlphabetsData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(44).getCell(2).getStringCellValue();
		CardholderAlphabetsData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(45).getCell(2).getRawValue();
		CardholderAlphabetsData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(46).getCell(2).getStringCellValue();
		CardholderAlphabetsData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(47).getCell(2).getRawValue();
		CardholderAlphabetsData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardholderAlphabetsData;
	}
	
	/* ******************************************************************
	 * Read the value from excel
	 * Assign the value in array variable
	 * Pass the test data to 'card number' validation test case
	 *******************************************************************/
	
	@Test
	public String[] ExcelReader_CardnumberEmptyValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardnumberEmptyData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(50).getCell(2).getStringCellValue();
		CardnumberEmptyData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(51).getCell(2).getStringCellValue();
		CardnumberEmptyData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(52).getCell(2).getStringCellValue();
		CardnumberEmptyData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(53).getCell(2).getRawValue();
		CardnumberEmptyData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardnumberEmptyData;
	}
	
	@Test
	public String[] ExcelReader_CardnumberAlphabetsValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardnumberAlphabetsData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(56).getCell(2).getStringCellValue();
		CardnumberAlphabetsData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(57).getCell(2).getStringCellValue();
		CardnumberAlphabetsData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(58).getCell(2).getStringCellValue();
		CardnumberAlphabetsData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(59).getCell(2).getRawValue();
		CardnumberAlphabetsData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardnumberAlphabetsData;
	}
	
	@Test
	public String[] ExcelReader_CardnumberLessThanLowerLimitValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardnumberLessThanLowerLimitData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(62).getCell(2).getStringCellValue();
		CardnumberLessThanLowerLimitData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(63).getCell(2).getRawValue();
		CardnumberLessThanLowerLimitData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(64).getCell(2).getStringCellValue();
		CardnumberLessThanLowerLimitData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(65).getCell(2).getRawValue();
		CardnumberLessThanLowerLimitData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardnumberLessThanLowerLimitData;
	}
	
	@Test
	public String[] ExcelReader_CardnumberSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardnumberSpecialcharData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(68).getCell(2).getStringCellValue();
		CardnumberSpecialcharData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(69).getCell(2).getStringCellValue();
		CardnumberSpecialcharData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(70).getCell(2).getStringCellValue();
		CardnumberSpecialcharData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(71).getCell(2).getRawValue();
		CardnumberSpecialcharData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardnumberSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_CardnumberAlphanumericValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardnumberAlphanumericData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(74).getCell(2).getStringCellValue();
		CardnumberAlphanumericData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(75).getCell(2).getStringCellValue();
		CardnumberAlphanumericData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(76).getCell(2).getStringCellValue();
		CardnumberAlphanumericData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(77).getCell(2).getRawValue();
		CardnumberAlphanumericData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardnumberAlphanumericData;
	}
	
	@Test
	public String[] ExcelReader_CardnumberAlphaSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardnumberAlphaSpecialcharData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(80).getCell(2).getStringCellValue();
		CardnumberAlphaSpecialcharData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(81).getCell(2).getStringCellValue();
		CardnumberAlphaSpecialcharData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(82).getCell(2).getStringCellValue();
		CardnumberAlphaSpecialcharData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(83).getCell(2).getRawValue();
		CardnumberAlphaSpecialcharData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardnumberAlphaSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_CardnumberNumberSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardnumberNumberSpecialcharData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(86).getCell(2).getStringCellValue();
		CardnumberNumberSpecialcharData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(87).getCell(2).getStringCellValue();
		CardnumberNumberSpecialcharData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(88).getCell(2).getStringCellValue();
		CardnumberNumberSpecialcharData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(89).getCell(2).getRawValue();
		CardnumberNumberSpecialcharData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardnumberNumberSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_CardnumberLowerLimitValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardnumberLowerLimitData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(92).getCell(2).getStringCellValue();
		CardnumberLowerLimitData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(93).getCell(2).getRawValue();
		CardnumberLowerLimitData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(94).getCell(2).getStringCellValue();
		CardnumberLowerLimitData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(95).getCell(2).getRawValue();
		CardnumberLowerLimitData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardnumberLowerLimitData;
	}
	
	@Test
	public String[] ExcelReader_CardnumberUpperLimitValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardnumberUpperLimitData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(98).getCell(2).getStringCellValue();
		CardnumberUpperLimitData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(99).getCell(2).getRawValue();
		CardnumberUpperLimitData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(100).getCell(2).getStringCellValue();
		CardnumberUpperLimitData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(101).getCell(2).getRawValue();
		CardnumberUpperLimitData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardnumberUpperLimitData;
	}
	
	@Test
	public String[] ExcelReader_CardnumberRandomValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CardnumberRandomData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(104).getCell(2).getStringCellValue();
		CardnumberRandomData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(105).getCell(2).getRawValue();
		CardnumberRandomData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(106).getCell(2).getStringCellValue();
		CardnumberRandomData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(107).getCell(2).getRawValue();
		CardnumberRandomData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return CardnumberRandomData;
	}
	
	/* ******************************************************************
	 * Read the value from excel
	 * Assign the value in array variable
	 * Pass the test data to 'Expiry date' validation test case
	 *******************************************************************/
	@Test
	public String[] ExcelReader_ExpirydateEmptyValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String ExpirydateEmptyData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(110).getCell(2).getStringCellValue();
		ExpirydateEmptyData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(111).getCell(2).getRawValue();
		ExpirydateEmptyData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(112).getCell(2).getStringCellValue();
		ExpirydateEmptyData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(113).getCell(2).getRawValue();
		ExpirydateEmptyData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return ExpirydateEmptyData;
	}
	
	@Test
	public String[] ExcelReader_ExpirydateAlphabetsValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String ExpirydateAlphabetsData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(116).getCell(2).getStringCellValue();
		ExpirydateAlphabetsData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(117).getCell(2).getRawValue();
		ExpirydateAlphabetsData[1] = cardnumber_value;
		String expirydate_value= ReadTheSheet_Validation.getRow(118).getCell(2).getStringCellValue();
		ExpirydateAlphabetsData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(119).getCell(2).getRawValue();
		ExpirydateAlphabetsData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return ExpirydateAlphabetsData;
	}
	
	@Test
	public String[] ExcelReader_ExpirydateSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String ExpirydateSpecialcharData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(122).getCell(2).getStringCellValue();
		ExpirydateSpecialcharData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(123).getCell(2).getRawValue();
		ExpirydateSpecialcharData[1] = cardnumber_value;
		String expirydate_value= ReadTheSheet_Validation.getRow(124).getCell(2).getStringCellValue();
		ExpirydateSpecialcharData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(125).getCell(2).getRawValue();
		ExpirydateSpecialcharData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return ExpirydateSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_ExpirydateAlphabetSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String ExpirydateAlphabetSpecialcharData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(128).getCell(2).getStringCellValue();
		ExpirydateAlphabetSpecialcharData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(129).getCell(2).getRawValue();
		ExpirydateAlphabetSpecialcharData[1] = cardnumber_value;
		String expirydate_value= ReadTheSheet_Validation.getRow(130).getCell(2).getStringCellValue();
		ExpirydateAlphabetSpecialcharData[2] = expirydate_value;
		String cvc_value = ReadTheSheet_Validation.getRow(131).getCell(2).getRawValue();
		ExpirydateAlphabetSpecialcharData[3] = cvc_value;
		ReadSpreadSheets_Validation.close();
		return ExpirydateAlphabetSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_ExpirydatePastValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String ExpirydatePastData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(134).getCell(2).getStringCellValue();
		ExpirydatePastData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(135).getCell(2).getRawValue();
		ExpirydatePastData[1] = cardnumber_value;
		String expirydate_value= ReadTheSheet_Validation.getRow(136).getCell(2).getStringCellValue();
		ExpirydatePastData[2] = expirydate_value;		
		String cvc_value = ReadTheSheet_Validation.getRow(137).getCell(2).getRawValue();
		ExpirydatePastData[3] = cvc_value;		
		ReadSpreadSheets_Validation.close();
		return ExpirydatePastData;
	}
	
	@Test
	public String[] ExcelReader_ExpirydateFutureValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String ExpirydateFutureValidationData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(140).getCell(2).getStringCellValue();
		ExpirydateFutureValidationData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(141).getCell(2).getRawValue();
		ExpirydateFutureValidationData[1] = cardnumber_value;
		String expirydate_value= ReadTheSheet_Validation.getRow(142).getCell(2).getStringCellValue();
		ExpirydateFutureValidationData[2] = expirydate_value;		
		String cvc_value = ReadTheSheet_Validation.getRow(143).getCell(2).getRawValue();
		ExpirydateFutureValidationData[3] = cvc_value;		
		ReadSpreadSheets_Validation.close();
		return ExpirydateFutureValidationData;
	}
	
	@Test
	public String[] ExcelReader_ExpirydatePresentValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String ExpirydatePresentData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(146).getCell(2).getStringCellValue();
		ExpirydatePresentData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(147).getCell(2).getRawValue();
		ExpirydatePresentData[1] = cardnumber_value;
		String expirydate_value= ReadTheSheet_Validation.getRow(148).getCell(2).getStringCellValue();
		ExpirydatePresentData[2] = expirydate_value;		
		String cvc_value = ReadTheSheet_Validation.getRow(149).getCell(2).getRawValue();
		ExpirydatePresentData[3] = cvc_value;		
		ReadSpreadSheets_Validation.close();
		return ExpirydatePresentData;
	}
	
	@Test
	public String[] ExcelReader_CVCEmptyValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CVCEmptyData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(152).getCell(2).getStringCellValue();
		CVCEmptyData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(153).getCell(2).getRawValue();
		CVCEmptyData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(154).getCell(2).getStringCellValue();
		CVCEmptyData[2] = expirydate_value;		
		String cvc_value = ReadTheSheet_Validation.getRow(155).getCell(2).getStringCellValue();
		CVCEmptyData[3] = cvc_value;		
		ReadSpreadSheets_Validation.close();
		return CVCEmptyData;
	}
	
	@Test
	public String[] ExcelReader_CVCAlphabetsValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CVCAlphabetsData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(158).getCell(2).getStringCellValue();
		CVCAlphabetsData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(159).getCell(2).getRawValue();
		CVCAlphabetsData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(160).getCell(2).getStringCellValue();
		CVCAlphabetsData[2] = expirydate_value;		
		String cvc_value = ReadTheSheet_Validation.getRow(161).getCell(2).getStringCellValue();
		CVCAlphabetsData[3] = cvc_value;		
		ReadSpreadSheets_Validation.close();
		return CVCAlphabetsData;
	}
	
	@Test
	public String[] ExcelReader_CVCSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CVCSpecialcharData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(164).getCell(2).getStringCellValue();
		CVCSpecialcharData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(165).getCell(2).getRawValue();
		CVCSpecialcharData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(166).getCell(2).getStringCellValue();
		CVCSpecialcharData[2] = expirydate_value;		
		String cvc_value = ReadTheSheet_Validation.getRow(167).getCell(2).getStringCellValue();
		CVCSpecialcharData[3] = cvc_value;		
		ReadSpreadSheets_Validation.close();
		return CVCSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_CVCLessThanLowerLimitValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CVCLessThanLowerLimitData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(170).getCell(2).getStringCellValue();
		CVCLessThanLowerLimitData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(171).getCell(2).getRawValue();
		CVCLessThanLowerLimitData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(172).getCell(2).getStringCellValue();
		CVCLessThanLowerLimitData[2] = expirydate_value;		
		String cvc_value = ReadTheSheet_Validation.getRow(173).getCell(2).getRawValue();
		CVCLessThanLowerLimitData[3] = cvc_value;		
		ReadSpreadSheets_Validation.close();
		return CVCLessThanLowerLimitData;
	}
	
	@Test
	public String[] ExcelReader_CVCLowerLimitValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CVCLowerLimitData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(176).getCell(2).getStringCellValue();
		CVCLowerLimitData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(177).getCell(2).getRawValue();
		CVCLowerLimitData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(178).getCell(2).getStringCellValue();
		CVCLowerLimitData[2] = expirydate_value;		
		String cvc_value = ReadTheSheet_Validation.getRow(179).getCell(2).getRawValue();
		CVCLowerLimitData[3] = cvc_value;		
		ReadSpreadSheets_Validation.close();
		return CVCLowerLimitData;
	}
	
	@Test
	public String[] ExcelReader_CVCUpperLimitValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CVCUpperLimitData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(182).getCell(2).getStringCellValue();
		CVCUpperLimitData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(183).getCell(2).getRawValue();
		CVCUpperLimitData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(184).getCell(2).getStringCellValue();
		CVCUpperLimitData[2] = expirydate_value;		
		String cvc_value = ReadTheSheet_Validation.getRow(185).getCell(2).getRawValue();
		CVCUpperLimitData[3] = cvc_value;		
		ReadSpreadSheets_Validation.close();
		return CVCUpperLimitData;
	}
	
	@Test
	public String[] ExcelReader_CVCGreaterThanUpperLimitValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(1);
		String CVCGreaterThanUpperLimitData[] = new String[4];
		String cardholder_value = ReadTheSheet_Validation.getRow(188).getCell(2).getStringCellValue();
		CVCGreaterThanUpperLimitData[0] = cardholder_value;
		String cardnumber_value = ReadTheSheet_Validation.getRow(189).getCell(2).getRawValue();
		CVCGreaterThanUpperLimitData[1] = cardnumber_value;
		String expirydate_value = ReadTheSheet_Validation.getRow(190).getCell(2).getStringCellValue();
		CVCGreaterThanUpperLimitData[2] = expirydate_value;		
		String cvc_value = ReadTheSheet_Validation.getRow(191).getCell(2).getRawValue();
		CVCGreaterThanUpperLimitData[3] = cvc_value;		
		ReadSpreadSheets_Validation.close();
		return CVCGreaterThanUpperLimitData;
	}
	
	/* ***************************************************************************
	 * Read the value from excel
	 * Assign the value in array variable
	 * Pass the test data to 'SEPA account holde name field' validation test case
	 *****************************************************************************/
	
	@Test
	public String[] ExcelReader_AccountholderEmptyValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String AccountholderEmptyData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(8).getCell(2).getStringCellValue();
		AccountholderEmptyData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(9).getCell(2).getStringCellValue();
		AccountholderEmptyData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return AccountholderEmptyData;
	}
	
	@Test
	public String[] ExcelReader_AccountholderNumericValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String AccountholderNumericData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(12).getCell(2).getRawValue();
		AccountholderNumericData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(13).getCell(2).getStringCellValue();
		AccountholderNumericData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return AccountholderNumericData;
	}
	
	@Test
	public String[] ExcelReader_AccountholderSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String AccountholderSpecialcharData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(16).getCell(2).getStringCellValue();
		AccountholderSpecialcharData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(17).getCell(2).getStringCellValue();
		AccountholderSpecialcharData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return AccountholderSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_AccountholderAlphanumericValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String AccountholderAlphanumericData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(20).getCell(2).getStringCellValue();
		AccountholderAlphanumericData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(21).getCell(2).getStringCellValue();
		AccountholderAlphanumericData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return AccountholderAlphanumericData;
	}
	
	@Test
	public String[] ExcelReader_AccountholderAlphaSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String AccountholderAlphaSpecialcharData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(24).getCell(2).getStringCellValue();
		AccountholderAlphaSpecialcharData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(25).getCell(2).getStringCellValue();
		AccountholderAlphaSpecialcharData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return AccountholderAlphaSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_AccountholderNumberSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String AccountholderNumberSpecialcharData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(28).getCell(2).getStringCellValue();
		AccountholderNumberSpecialcharData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(29).getCell(2).getStringCellValue();
		AccountholderNumberSpecialcharData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return AccountholderNumberSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_AccountholderAlphabetsValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String AccountholderAlphabetsData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(32).getCell(2).getStringCellValue();
		AccountholderAlphabetsData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(33).getCell(2).getStringCellValue();
		AccountholderAlphabetsData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return AccountholderAlphabetsData;
	}
	
	/* ***************************************************************************
	 * Read the value from excel
	 * Assign the value in array variable
	 * Pass the test data to 'SEPA IBAN field' validation test case
	 *****************************************************************************/
	
	@Test
	public String[] ExcelReader_IbanEmptyValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String IbanEmptyData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(36).getCell(2).getStringCellValue();
		IbanEmptyData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(37).getCell(2).getStringCellValue();
		IbanEmptyData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return IbanEmptyData;
	}
	
	@Test
	public String[] ExcelReader_IbanNumericValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String IbanNumericData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(40).getCell(2).getStringCellValue();
		IbanNumericData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(41).getCell(2).getRawValue();
		IbanNumericData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return IbanNumericData;
	}
	
	@Test
	public String[] ExcelReader_IbanSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String IbanSpecialcharData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(44).getCell(2).getStringCellValue();
		IbanSpecialcharData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(45).getCell(2).getStringCellValue();
		IbanSpecialcharData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return IbanSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_IbanAlphaSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String IbanAlphaSpecialcharData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(48).getCell(2).getStringCellValue();
		IbanAlphaSpecialcharData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(49).getCell(2).getStringCellValue();
		IbanAlphaSpecialcharData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return IbanAlphaSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_IbanNumberSpecialcharValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String IbanNumberSpecialcharData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(52).getCell(2).getStringCellValue();
		IbanNumberSpecialcharData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(53).getCell(2).getStringCellValue();
		IbanNumberSpecialcharData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return IbanNumberSpecialcharData;
	}
	
	@Test
	public String[] ExcelReader_IbanAlphabetsValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String IbanAlphabetsData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(56).getCell(2).getStringCellValue();
		IbanAlphabetsData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(57).getCell(2).getStringCellValue();
		IbanAlphabetsData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return IbanAlphabetsData;
	}
	
	@Test
	public String[] ExcelReader_IbanWrongAlphanumericValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String IbanWrongAlphanumericData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(60).getCell(2).getStringCellValue();
		IbanWrongAlphanumericData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(61).getCell(2).getStringCellValue();
		IbanWrongAlphanumericData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return IbanWrongAlphanumericData;
	}
	
	@Test
	public String[] ExcelReader_IbanValidAlphanumericValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(2);
		String IbanValidAlphanumericData[] = new String[4];
		String accountholder_value = ReadTheSheet_Validation.getRow(64).getCell(2).getStringCellValue();
		IbanValidAlphanumericData[0] = accountholder_value;
		String iban_value = ReadTheSheet_Validation.getRow(65).getCell(2).getStringCellValue();
		IbanValidAlphanumericData[1] = iban_value;
		ReadSpreadSheets_Validation.close();
		return IbanValidAlphanumericData;
	}
	
	/* ***************************************************************************
	 * Read the value from excel
	 * Assign the value in array variable
	 * Pass the test data to 'SEPA due date' validation test case
	 *****************************************************************************/
	
	@Test
	public String[] ExcelReader_SepaDuedateValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(3);
		String SepaDuedateData[] = new String[6];
		String SepaDuedateValidation1 = ReadTheSheet_Validation.getRow(7).getCell(2).getStringCellValue();
		SepaDuedateData[0] = SepaDuedateValidation1;
		String SepaDuedateValidation2 = ReadTheSheet_Validation.getRow(8).getCell(2).getStringCellValue();
		SepaDuedateData[1] = SepaDuedateValidation2;		
		String SepaDuedateValidation3 = ReadTheSheet_Validation.getRow(9).getCell(2).getStringCellValue();
		SepaDuedateData[2] = SepaDuedateValidation3;		
		String SepaDuedateValidation4 = ReadTheSheet_Validation.getRow(10).getCell(2).getStringCellValue();
		SepaDuedateData[3] = SepaDuedateValidation4;		
		String SepaDuedateValidation5 = ReadTheSheet_Validation.getRow(11).getCell(2).getRawValue();
		SepaDuedateData[4] = SepaDuedateValidation5;		
		String SepaDuedateValidation6 = ReadTheSheet_Validation.getRow(12).getCell(2).getRawValue();
		SepaDuedateData[5] = SepaDuedateValidation6;		
		ReadSpreadSheets_Validation.close();
		return SepaDuedateData;
	}
	
	/* ***************************************************************************
	 * Read the value from excel
	 * Assign the value in array variable
	 * Pass the test data to 'Guarantee minimum order amount' validation test case
	 *****************************************************************************/
	
	@Test
	public String[] ExcelReader_GuaranteeMinamountStringValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(3);
		String GuaranteeMinamountStringData[] = new String[4];
		String GuaranteeMinamountStringValidation1 = ReadTheSheet_Validation.getRow(14).getCell(2).getStringCellValue();
		GuaranteeMinamountStringData[0] = GuaranteeMinamountStringValidation1;
		String GuaranteeMinamountStringValidation2 = ReadTheSheet_Validation.getRow(15).getCell(2).getStringCellValue();
		GuaranteeMinamountStringData[1] = GuaranteeMinamountStringValidation2;		
		String GuaranteeMinamountStringValidation3 = ReadTheSheet_Validation.getRow(16).getCell(2).getStringCellValue();
		GuaranteeMinamountStringData[2] = GuaranteeMinamountStringValidation3;		
		String GuaranteeMinamountStringValidation4 = ReadTheSheet_Validation.getRow(17).getCell(2).getStringCellValue();
		GuaranteeMinamountStringData[3] = GuaranteeMinamountStringValidation4;		
		ReadSpreadSheets_Validation.close();
		return GuaranteeMinamountStringData;
	}
	
	@Test
	public String[] ExcelReader_GuaranteeMinamountLimitValidation() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets_Validation = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet_Validation = ReadSpreadSheets_Validation.getSheetAt(3);
		String GuaranteeMinamountLimitData[] = new String[3];
		String GuaranteeMinamountLimitDataValidation1 = ReadTheSheet_Validation.getRow(19).getCell(2).getRawValue();
		GuaranteeMinamountLimitData[0] = GuaranteeMinamountLimitDataValidation1;
		String GuaranteeMinamountLimitDataValidation2 = ReadTheSheet_Validation.getRow(20).getCell(2).getRawValue();
		GuaranteeMinamountLimitData[1] = GuaranteeMinamountLimitDataValidation2;		
		String GuaranteeMinamountLimitDataValidation3 = ReadTheSheet_Validation.getRow(21).getCell(2).getRawValue();
		GuaranteeMinamountLimitData[2] = GuaranteeMinamountLimitDataValidation3;			
		ReadSpreadSheets_Validation.close();
		return GuaranteeMinamountLimitData;
	}
	
	// This data belongs to personal details form
	@Test
	public Map<String, String> ExcelReader_GuarateeCustomerDetails() throws IOException {
		// This code is to read the excel sheet
		File ExcelSource = new File(System.getProperty("user.dir") + "/src/main/resources/TestData/NN_DATA.xlsx");
		FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
		XSSFWorkbook ReadSpreadSheets = new XSSFWorkbook(GetTheInputFromExcel);
		XSSFSheet ReadTheSheet = ReadSpreadSheets.getSheetAt(0);
		Map<String, String> PersonalDetailsFormUserData = new HashMap<String, String>();
		String Customer_Firstname = ReadTheSheet.getRow(102).getCell(1).getStringCellValue();
		PersonalDetailsFormUserData.put("Firstname", Customer_Firstname);
		String Customer_Lastname = ReadTheSheet.getRow(103).getCell(1).getStringCellValue();
		PersonalDetailsFormUserData.put("Lastname", Customer_Lastname);		
		String Customer_Streetaddress = ReadTheSheet.getRow(104).getCell(1).getStringCellValue();
		PersonalDetailsFormUserData.put("StreetAddress", Customer_Streetaddress);
		String Customer_ZIP = ReadTheSheet.getRow(105).getCell(1).getRawValue();
		PersonalDetailsFormUserData.put("ZIP", Customer_ZIP);
		String Customer_City = ReadTheSheet.getRow(106).getCell(1).getStringCellValue();
		PersonalDetailsFormUserData.put("City", Customer_City);
		String Customer_Email = ReadTheSheet.getRow(107).getCell(1).getStringCellValue();
		PersonalDetailsFormUserData.put("Email", Customer_Email);
		String Customer_DOB = ReadTheSheet.getRow(108).getCell(1).getStringCellValue();
		PersonalDetailsFormUserData.put("DOB", Customer_DOB);
		ReadSpreadSheets.close();
		return PersonalDetailsFormUserData;
	}
	
	// This data belongs to personal details form
		@Test
		public Map<String, String> ExcelReader_GuarateeB2CCustomerDetails() throws IOException {
			// This code is to read the excel sheet
			File ExcelSource = new File(System.getProperty("user.dir") + "/src/main/resources/TestData/NN_DATA.xlsx");
			FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
			XSSFWorkbook ReadSpreadSheets = new XSSFWorkbook(GetTheInputFromExcel);
			XSSFSheet ReadTheSheet = ReadSpreadSheets.getSheetAt(0);
			Map<String, String> PersonalDetailsFormUserData = new HashMap<String, String>();
			String B2CCustomer_Firstname = ReadTheSheet.getRow(113).getCell(1).getStringCellValue();
			PersonalDetailsFormUserData.put("Firstname", B2CCustomer_Firstname);
			String B2CCustomer_Lastname = ReadTheSheet.getRow(114).getCell(1).getStringCellValue();
			PersonalDetailsFormUserData.put("Lastname", B2CCustomer_Lastname);			
			String B2CCustomer_Streetaddress = ReadTheSheet.getRow(115).getCell(1).getStringCellValue();
			PersonalDetailsFormUserData.put("StreetAddress", B2CCustomer_Streetaddress);
			String B2CCustomer_ZIP = ReadTheSheet.getRow(116).getCell(1).getRawValue();
			PersonalDetailsFormUserData.put("ZIP", B2CCustomer_ZIP);
			String B2CCustomer_City = ReadTheSheet.getRow(117).getCell(1).getStringCellValue();
			PersonalDetailsFormUserData.put("City", B2CCustomer_City);
			String B2CCustomer_Email = ReadTheSheet.getRow(118).getCell(1).getStringCellValue();
			PersonalDetailsFormUserData.put("Email", B2CCustomer_Email);
			String B2CCustomer_DOB = ReadTheSheet.getRow(119).getCell(1).getStringCellValue();
			PersonalDetailsFormUserData.put("DOB", B2CCustomer_DOB);
			ReadSpreadSheets.close();
			return PersonalDetailsFormUserData;
		}
		
		// This data belongs to login shop backend login
		@Test
		public Map<String, String> ExcelReader_FraudmodulePINValidation() throws IOException {
			
			// This code is to read the excel sheet
			File ExcelSource = new File(System.getProperty("user.dir") + "/src/main/resources/TestData/NN_DATA.xlsx");
			FileInputStream GetTheInputFromExcel = new FileInputStream(ExcelSource);
			XSSFWorkbook ReadSpreadSheets = new XSSFWorkbook(GetTheInputFromExcel);
			XSSFSheet ReadTheSheet = ReadSpreadSheets.getSheetAt(4);
			Map<String, String> FrontEndUserData = new HashMap<String, String>();

			// Shop front end login
			String Username_data = ReadTheSheet.getRow(11).getCell(1).getStringCellValue();
			FrontEndUserData.put("ShopFrontendLogin_username", Username_data);
			String Password_data = ReadTheSheet.getRow(12).getCell(1).getStringCellValue();
			FrontEndUserData.put("ShopFrontendLogin_password", Password_data);
			ReadSpreadSheets.close();
			return FrontEndUserData;
		}
		
}
