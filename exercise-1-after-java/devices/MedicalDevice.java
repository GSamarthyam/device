/*
* DISCLAIMER
* 
* 1) This source code has been provided as-is for the purpose of learning 
* 2) The code snippet/samples shown here are only meant to highlight concepts provided in the training
*    They may not cover all important, clean coding & best practices like documentation comments or naming conventions (e.g., CheckStyle rules)
* 3) Some part of code/class abstractions may have been intentionally edited
*/

/*
 * Represents a Medical devices.Device
 */
package after.devices;

public class MedicalDevice extends Device {
	private int heartRate;
	
	public MedicalDevice(int deviceId) {
		super(deviceId);
	}

	public int getHeartRate(){
	     return heartRate;
	}
	
	public void setHeartRate(int value){
	      heartRate = value;
	}

	@Override
	public String getStatus() {
		return String.valueOf(getHeartRate());
	}
	
	@Override
	public String getDeviceType() { 
		return "Medical";
	}
}
