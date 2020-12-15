/*
* DISCLAIMER
* 
* 1) This source code has been provided as-is for the purpose of learning 
* 2) The code snippet/samples shown here are only meant to highlight concepts provided in the training
*    They may not cover all important, clean coding & best practices like documentation comments or naming conventions (e.g., CheckStyle rules)
* 3) Some part of code/class abstractions may have been intentionally edited
*/

/*
 * Generates the report body in HTML format - this class incrementally builds up 
 * the report body and is an example of the Builder pattern 
 */
package after.reporting;

public class HtmlReportBody implements IReportBodyFormatter {
	private final StringBuilder htmlBodyBuilder;
	
	public HtmlReportBody(){
		htmlBodyBuilder = new StringBuilder();
	}
	
	@Override
	public IReportBodyFormatter appendContent(String content) {
        	appendLineBreak();
        	htmlBodyBuilder.append(content);
        	return this;
	}

	@Override
	public String toReportString() {
		return htmlBodyBuilder.toString() ;
	}

   	private void appendLineBreak() {
		htmlBodyBuilder.append("<BR/>");
   	}
}
