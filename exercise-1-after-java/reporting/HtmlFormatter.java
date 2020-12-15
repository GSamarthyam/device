/*
* DISCLAIMER
* 
* 1) This source code has been provided as-is for the purpose of learning 
* 2) The code snippet/samples shown here are only meant to highlight concepts provided in the training
*    They may not cover all important, clean coding & best practices like documentation comments or naming conventions (e.g., CheckStyle rules)
* 3) Some part of code/class abstractions may have been intentionally edited
*/

/*
 * Implements HTML specific formatting logic to generate the report as per the reporting.IFormatter interface
 * Internally uses the HTML Report Body class to generate the body, and adds the header and body content 
 */

package after.reporting;

public class HtmlFormatter implements IFormatter {
	private String header;
	private final IReportBodyFormatter reportBody;
	
	public HtmlFormatter(){
        	reportBody = new HtmlReportBody();
	}
	
	@Override
	public void setHeader(String text) {
		header = "<H1> "+text+" </H1>";
	}

	@Override
	public IReportBodyFormatter getReportBody() {		
		return reportBody;
	}

	@Override
	public String toReportString() {
		return header + reportBody.toReportString();
	}
}
