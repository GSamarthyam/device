package after;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

abstract class PowerGenerationDevice {
    abstract double generationCapacity();
}

class SolarUnit extends PowerGenerationDevice
{
    public UUID deviceId;
    public String deviceName;

    private List<PowerGenerationDevice> devices;

    public SolarUnit(UUID deviceId, String deviceName)
    {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.devices = new ArrayList<>();
    }

    public SolarUnit(UUID deviceId, String deviceName, List<PowerGenerationDevice> devices)
    {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        if (devices != null)
        {
            this.devices = devices;
        }
    }

    public void addDevice(PowerGenerationDevice device)
    {
        devices.add(device);
    }

    public double generationCapacity()
    {
        return devices.stream().mapToDouble(PowerGenerationDevice::generationCapacity).sum();
    }
}

class SolarPanel extends PowerGenerationDevice
{
    public UUID deviceId;
    public String deviceName;
    public double capacity;
    public PowerGenerationDevice parentUnit;

    public SolarPanel(UUID deviceId, String deviceName, double capacity, PowerGenerationDevice parentUnit)
    {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.capacity = capacity;
        this.parentUnit = parentUnit;
    }

    public double generationCapacity()
    {
        return capacity;
    }

    public PowerGenerationDevice getParentSolarUnit()
    {
        return parentUnit;
    }
}

class Test
{
        public static void main(String []args)
        {
            SolarUnit smallUnit = new SolarUnit(UUID.randomUUID(), "SU001");
            List<SolarPanel> panels = new ArrayList<>();
            panels.add(new SolarPanel(UUID.randomUUID(), "P01", 100, smallUnit));
            panels.add(new SolarPanel(UUID.randomUUID(), "P02", 93, smallUnit));
            panels.forEach(smallUnit::addDevice);

            SolarUnit anotherUnit = new SolarUnit(UUID.randomUUID(), "SU002");

            List<SolarPanel> anotherPanels = new ArrayList<>();
            panels.add(new SolarPanel(UUID.randomUUID(), "P04", 60, anotherUnit));
            panels.add(new SolarPanel(UUID.randomUUID(), "P05", 67, anotherUnit));
            anotherPanels.forEach(anotherUnit::addDevice);

            smallUnit.addDevice(anotherUnit);

            System.out.println(smallUnit.generationCapacity());
        }
}