/*
 * DISCLAIMER
 *
 * 1) This source code has been provided as-is for the purpose of learning
 * 2) The code snippet/samples shown here are only meant to highlight concepts provided in the training
 *    They may not cover all important, clean coding & best practices like documentation comments or naming conventions (e.g., CheckStyle rules)
 * 3) Some part of code/class abstractions may have been intentionally edited
 */

/* An abstract base class which represents the concept of a "devices.Device"
 * It contains common functionality such as toggling device status
 * and various properties/accessors for devices.Device Id and devices.Device Type.
 */
package after.devices;

public abstract class Device {
    private boolean onOff = false;
    private int deviceId;

    protected Device(int deviceId) {
        this.deviceId = deviceId;
    }

    public boolean getOnOff() {
        return onOff;
    }

    private void setOnOff(boolean value) {
        onOff = value;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public abstract String getDeviceType();

    public void toggleStatus() {
        onOff = !onOff;
    }

    public abstract String getStatus();
}
