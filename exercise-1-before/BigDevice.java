package before;

/*
* DISCLAIMER
* 
* 1) This source code has been provided as-is for the purpose of learning 
* 2) The code snippet/samples shown here are only meant to highlight concepts provided in the training
*    They may not cover all important, clean coding & best practices like documentation comments or naming conventions (e.g., CheckStyle rules)
* 3) Some part of code/class abstractions may have been intentionally edited
*/

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/**
* Refactor the before.BigDevice class to make it clean
* Axis of change: 
* 	a) support for new device types 
*	b) support more languages
* 	c) support for new kinds of report formats (XML report planned for next iteration) 
*
* You can make any change you see fit in the code
*/
public class BigDevice {
	// named constants for different types of devices
        public static final int MEDICAL = 0;
        public static final int AGRI = 1;
        public static final int REFINARY = 2;

        // properties common to all devices
        public int type;
        public boolean status = true;
        public int deviceId;

        // properties for individual types of devices
        public int soilQuality;     // for Agricultural device 
        public int heartRate;       // for Medical device 
        public int temperature;     // for Refinary device 

        // constants for English and Dutch language
        public static final int EN = 1;
        public static final int DU = 2;

        public BigDevice(int type, int deviceId, boolean status, int value) {        
            this.type = type;
            this.deviceId = deviceId;
            this.status= status;
            switch(type) {
            case BigDevice.MEDICAL: 
            	this.heartRate = value; break;
            case BigDevice.AGRI:
            	this.soilQuality = value; break;
            case BigDevice.REFINARY:
            	this.temperature = value; break; 
            default: 
            	throw new IllegalArgumentException ("Invalid value for argument value"); 
            }
        }

        public void toggleStatus() {        
            status = !status;
        }
        
        public static String getStatus(BigDevice device, int language) {
            String onOrOff;
            String statusMessage;
            
            // If not English, then Dutch
            if (language == EN) {
                statusMessage = "OnOff";
                onOrOff = device.status ? "ON" : "OFF";
            }
            else {
                statusMessage = "Toestand";
                onOrOff = device.status ? "OP" : "UIT";
            }

            switch (device.type) {
                case BigDevice.MEDICAL:
                    return statusMessage + onOrOff + device.heartRate;
                case BigDevice.AGRI:
                    return statusMessage + onOrOff + device.soilQuality;
                case BigDevice.REFINARY:
                    return statusMessage + onOrOff + device.temperature;
            }
            return "";
       }
        
       public static String printStatus(List<BigDevice> devices, int userLanguage) {
            String returnString = "";

            // test list is empty
            if (devices.size() == 0)
            {
                returnString = userLanguage == EN ? "<h1>Empty list of devices!</h1>" : "<h1>Lege lijst met apparaten!</h1>";
            }
            else
            {
                // we have devices
                // let's create a header
                if (userLanguage == EN)
                {
                    returnString += "<h1>Devices report</h1><br/>";
                }
                else
                {
                    // default is Dutch
                    returnString += "<h1>apparaten Report</h1><br/>";
                }

                int numberMedical = 0;
                int numberAgricultural = 0;
                int numberRefinaries = 0;

                StringBuilder builder = new StringBuilder();
                try(Formatter formatter = new Formatter(builder, Locale.US)) { 
                	// Get status of the devices 
                	for (BigDevice device : devices) {
                		if (device.type == BigDevice.MEDICAL) {
                			numberMedical++;
                			returnString += formatter.format("<BR/> Device ID: %d;%s <BR>",device.deviceId, 
                        		                          BigDevice.getStatus(device, userLanguage));
                		} 
                		else if (device.type == BigDevice.AGRI) {
                			numberAgricultural++;
                			returnString += formatter.format("<BR/> Device ID: %d;%s <BR>",device.deviceId, 
		                          BigDevice.getStatus(device, userLanguage));
                		}
                		else if (device.type == BigDevice.REFINARY) {
                			numberRefinaries++;
                			returnString += formatter.format("<BR/> Device ID: %d;%s <BR>",device.deviceId, 
		                          BigDevice.getStatus(device, userLanguage));
                		}
                	}
                }	

                // let's print this
                returnString += getLine(BigDevice.MEDICAL, userLanguage, numberMedical);
                returnString += getLine(BigDevice.AGRI, userLanguage, numberAgricultural);
                returnString += getLine(BigDevice.REFINARY, userLanguage, numberRefinaries);

                // finally, lets print the footer
                returnString += "TOTAL: ";
                returnString += (numberAgricultural + numberMedical + numberRefinaries) + " " +
                                (userLanguage == EN ? "devices" : "apparaten");
            }
            return returnString;
        }

        public static String getLine(int deviceType, int userLanguage, int numberOfDevices) {
            if (numberOfDevices > 0) {
                if (userLanguage == EN) {
                    return numberOfDevices + " " + translateDevice(deviceType, userLanguage) + " devices" + "<br/>";
                }
                return numberOfDevices + " " + translateDevice(deviceType, userLanguage) + " apparaten" + "<br/>";
            }
            return "";
       }
       
       private static String translateDevice(int type, int userLanguage) {
    	   switch (type) {
                case 0:
                    return userLanguage == EN ? "Medical" : "Medisch";
                case 1:
                    return userLanguage == EN ? "Agricultural" : "Agrarisch";
                case 2:
                    return userLanguage == EN ? "Refinary" : "Raffinaderij";
           }
           return "";
       }
       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	BigDevice d1 = new BigDevice(BigDevice.MEDICAL, 1, false, 75);
        BigDevice d2 = new BigDevice(BigDevice.AGRI, 2, true, 32);
        BigDevice d3 = new BigDevice(BigDevice.REFINARY, 3, false, 60);

        List<BigDevice> devices = new ArrayList<BigDevice>();
        devices.add(d1);
        devices.add(d2);
        devices.add(d3);

        String printStatus = BigDevice.printStatus(devices, BigDevice.EN);
        System.out.println(printStatus);
    }
}
