/*
* DISCLAIMER
* 
* 1) This source code has been provided as-is for the purpose of learning 
* 2) The code snippet/samples shown here are only meant to highlight concepts provided in the training
*    They may not cover all important, clean coding & best practices like documentation comments or naming conventions (e.g., CheckStyle rules)
* 3) Some part of code/class abstractions may have been intentionally edited
*/

/*
 * Interface to create the report body by appending various strings 
 */
package after.reporting;
public interface IReportBodyFormatter {
    IReportBodyFormatter appendContent(String content);
    String toReportString();
}
