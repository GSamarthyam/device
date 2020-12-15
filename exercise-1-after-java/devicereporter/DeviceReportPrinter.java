package after.devicereporter;

import after.devices.Device;
import after.resources.DeviceResources;
import after.reporting.IFormatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 * DISCLAIMER
 *
 * 1) This source code has been provided as-is for the purpose of learning
 * 2) The code snippet/samples shown here are only meant to highlight concepts provided in the training
 *    They may not cover all important, clean coding & best practices like documentation comments or naming conventions (e.g., CheckStyle rules)
 * 3) Some part of code/class abstractions may have been intentionally edited
 */

/*
 * This is an important class which prints out the report for the list
 * of devices as per the given reporting.IFormatter interface implementation.
 * The report contains a header, status for all devices, count for each type of devices.Device and the total device count
 */
public class DeviceReportPrinter {
    private IFormatter formatter;
    private List<Device> devices;
    
    private String asReport(){
        return formatter.toReportString();
    }

    private DeviceReportPrinter createHeader() {
        formatter.setHeader(devices.size() == 0 ? DeviceResources.EmptyList : DeviceResources.DeviceReport);
        return this;
    }

    private DeviceReportPrinter appendDeviceStatuses() {
        for (Device device : devices) {
            formatter.getReportBody()
                .appendContent(DeviceResources.DeviceID+" : " + String.valueOf(device.getDeviceId()) + "; " + device.getStatus());
        }
        return this;
    }

    private DeviceReportPrinter appendDeviceTotalCount() {
        formatter.getReportBody().appendContent(DeviceResources.Total+": ")
            .appendContent(String.valueOf(devices.size()+" "))
            .appendContent(DeviceResources.Devices);
        return this;
    }

    private DeviceReportPrinter appendDeviceTypeCounts() {
    	Map<String, Integer> deviceTypeDictionary = new HashMap<>();
        for (Device device : devices) {
            if (deviceTypeDictionary.containsKey(device.getDeviceType())) {
            	String type = device.getDeviceType();
                int count = (int)deviceTypeDictionary.get(type);
                deviceTypeDictionary.replace(type, ++count);
            }
            else {
                deviceTypeDictionary.put(DeviceResources.getString(device.getDeviceType()), 1);
            }
        }

        deviceTypeDictionary.keySet().forEach(item -> {
            formatter.getReportBody()
                .appendContent(item+": "+ String.valueOf((int)deviceTypeDictionary.get(item)) + DeviceResources.Devices);
        });
        return this;
    }

    public String generate(IFormatter formatter, List<Device> devices){
    	this.formatter = formatter;
        this.devices = devices;
        
        return createHeader()
            .appendDeviceStatuses()
            .appendDeviceTypeCounts()
            .appendDeviceTotalCount()
            .asReport();
    }
}
