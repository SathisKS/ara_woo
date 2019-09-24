/* *************************************************************************
 * Project Name: Seamless Payment Form
 * Description:  This file contain HTML report creation
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 **************************************************************************/

/* ******************************************************
 * Extend HTML Report Creation & Configuration
 ********************************************************/

package com.nn.TestReport;

import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nn.TestConfiguration.Constant;

public class ExtendReport {
	public static ExtentReports extend;	// This object write in HTML report
	public static ExtentHtmlReporter htmlreporter;	//Create the HTML
	public static ExtentTest test;
	@BeforeSuite
	public static void config() {
	  	String folderPath = System.getProperty("user.dir")+"/src/test/resources/ExtendReport/TestReport.html";
		htmlreporter=new ExtentHtmlReporter(folderPath);
	  	extend= new ExtentReports();
	  	extend.attachReporter(htmlreporter);
	  	htmlreporter.config().setReportName("Novalnet AG");
	  	htmlreporter.config().setDocumentTitle("Seamless Payment Form");
	  	htmlreporter.config().setTheme(Theme.STANDARD);
	  	htmlreporter.config().setTimeStampFormat(null);
	  	extend.setSystemInfo("OS", Constant.OperatingSystem);
	  	extend.setSystemInfo("Browser", Constant.browser);
	  	extend.setSystemInfo("User Name", "Novalnet AG Team");
	}
}