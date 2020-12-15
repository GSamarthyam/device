
/*
* DISCLAIMER
* 
* 1) This source code has been provided as-is for the purpose of learning 
* 2) The code snippet/samples shown here are only meant to highlight concepts provided in the training
*    They may not cover all important, clean coding & best practices like documentation comments or naming conventions (e.g., CheckStyle rules)
* 3) Some part of code/class abstractions may have been intentionally edited
*/
package after.devices;
/*
 * Represents a Agricultural devices.Device
 */
public class AgriculturalDevice extends Device {
	private int soilQuality;
	
	public AgriculturalDevice(int deviceId) {
		super(deviceId);
	}

	public int getSoilQuality(){
	     return soilQuality;
	}
	
	public void setSoilQuality(int value){
		soilQuality = value;
	}

	@Override
	public String getStatus() {
		return String.valueOf(getSoilQuality());
	} 
	
	@Override
	public String getDeviceType() { 
		return "Agricultural";
	}
}
