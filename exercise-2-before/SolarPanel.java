package before;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class SolarUnit
{
    public UUID deviceId;
    public String deviceName;

    private List<SolarPanel> panels = new ArrayList<>();
    private List<SolarUnit> units = new ArrayList<>();

    public SolarUnit(UUID deviceId, String deviceName)
    {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.panels = new ArrayList<>();
        this.units = new ArrayList<>();
    }

    public SolarUnit(UUID deviceId, String deviceName,
                     List<SolarPanel> panels, List<SolarUnit> units)
    {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        if (panels != null)
        {
            this.panels = panels;
        }

        if (units != null)
        {
            this.units = units;
        }
    }

    public void addUnits(SolarUnit unit)
    {
        units.add(unit);
    }

    public void addPanels(SolarPanel panel)
    {
        panels.add(panel);
    }

    public  float generationCapacity()
    {
        float capacity = 0;
        for (SolarPanel panel : panels)
        {
            capacity += panel.generationCapacity();
        }

        for (SolarUnit unit : units)
        {
            capacity += unit.generationCapacity();
        }

        return capacity;
    }
}

class SolarPanel
{
    public UUID deviceId;
    public String deviceName;
    public float capacity;
    public SolarUnit parentUnit;

    public SolarPanel(UUID deviceId, String deviceName, float capacity, SolarUnit parentUnit)
    {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.capacity = capacity;
        this.parentUnit = parentUnit;
    }

    public float generationCapacity()
    {
        return capacity;
    }

    public SolarUnit getParentSolarUnit()
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
            panels.forEach(smallUnit::addPanels);

            SolarUnit anotherUnit = new SolarUnit(UUID.randomUUID(), "SU002");

            List<SolarPanel> anotherPanels = new ArrayList<>();
            panels.add(new SolarPanel(UUID.randomUUID(), "P04", 60, anotherUnit));
            panels.add(new SolarPanel(UUID.randomUUID(), "P05", 67, anotherUnit));
            anotherPanels.forEach(anotherUnit::addPanels);

            smallUnit.addUnits(anotherUnit);

            System.out.println(smallUnit.generationCapacity());
        }
}