/*
* DISCLAIMER
* 
* 1) This source code has been provided as-is for the purpose of learning 
* 2) The code snippet/samples shown here are only meant to highlight concepts provided in the training
*    They may not cover all important, clean coding & best practices like documentation comments or naming conventions (e.g., CheckStyle rules)
* 3) Some part of code/class abstractions may have been intentionally edited
*/
package after.main;

import after.devicereporter.DeviceReportPrinter;
import after.devices.AgriculturalDevice;
import after.devices.Device;
import after.devices.MedicalDevice;
import after.devices.RefineryDevice;
import after.reporting.HtmlFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// for resources and localization


public class Program {
	public static void main(String[] args) {
		// Comment/Uncomment this line to reset/set the Locale to Dutch language		
        // Locale.setDefault(Locale.forLanguageTag("nl-NL"));

		List<Device> devices = new ArrayList<Device>();
		
		AgriculturalDevice agri = new AgriculturalDevice(1);
		agri.setSoilQuality(30);
		devices.add(agri);
		
		MedicalDevice med = new MedicalDevice(2);
		med.setHeartRate(75);
		devices.add(med);
		
		RefineryDevice refinery = new RefineryDevice(3);
		refinery.setTemperature(100); 
		devices.add(refinery);
		
		List<Device> noDevices = new ArrayList<Device>();
		
		System.out.println(new DeviceReportPrinter().generate(new HtmlFormatter(), devices));
	}
}
