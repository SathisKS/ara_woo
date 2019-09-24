/* *************************************************************************
 * Project Name: Seamless Payment Form
 * Description:  This file contain HTML report updation of final results 
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 **************************************************************************/

/* ****************************************************************
* Test case results will saved in Extend HTML Report
* Note: If not flushed out the report it produce the result file
******************************************************************/

package com.nn.TestReport;

import org.testng.annotations.AfterSuite;

public class FlushResults extends ExtendReport {
	@AfterSuite
	public static void flush() {
		extend.flush();
	}
}