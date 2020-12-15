/*
* DISCLAIMER
* 
* 1) This source code has been provided as-is for the purpose of learning 
* 2) The code snippet/samples shown here are only meant to highlight concepts provided in the training
*    They may not cover all important, clean coding & best practices like documentation comments or naming conventions (e.g., CheckStyle rules)
* 3) Some part of code/class abstractions may have been intentionally edited
*/

/*
 * Interface to perform formatting of the report in the Header-Body-HTML string conversion format 
 */
package after.reporting;
public interface IFormatter {
	void setHeader(String header);
	IReportBodyFormatter getReportBody();
	String toReportString();
}
