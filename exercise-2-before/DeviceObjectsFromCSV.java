package before;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class SolarDevice {
    private UUID deviceId;
    private String name;
    private double powerGenerationCapacity;
    private double latitude;
    private double longitude;
    private boolean status;
    private LocalDateTime startDateTime;
    private double generatedPower;
    public SolarDevice(UUID deviceId, String name, double powerGenerationCapacity,
                       double latitude, double longitude, boolean status,
                       LocalDateTime startDateTime, double generatedPower) {
        this.deviceId = deviceId;
        this.name = name;
        this.powerGenerationCapacity = powerGenerationCapacity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.startDateTime = startDateTime;
        this.generatedPower = generatedPower;
    }

    @Override
    public String toString() {
        return "SolarDevice{" +
                "deviceId=" + deviceId +
                ", name='" + name + '\'' +
                ", powerGenerationCapacity=" + powerGenerationCapacity +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", status=" + status +
                ", startDateTime=" + startDateTime +
                ", generatedPower=" + generatedPower +
                '}';
    }
}

class DeviceObjectsFromCSV {
    public static List<SolarDevice> GetSolarDevicesFromCSV(String path) {
        Class SolarDeviceType = SolarDevice.class;
        Constructor[] ci = SolarDeviceType.getConstructors();
        // the first entry is the constructor we're looking for!
        Constructor solarDeviceCtor = ci[0];
        // the parameters are prepared each time from the values read from the file in the loop
        List<SolarDevice> devices = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parameters = line.split(",");
                Object[] ctorParameters = new Object[parameters.length];
                // public SolarDevice(Guid deviceId, string name, double powerGenerationCapacity,
                // double latitude, double longitude, bool status, DateTime startDateTime, double generatedPower)
                ctorParameters[0] = UUID.fromString(parameters[0]);
                ctorParameters[1] = parameters[1];
                ctorParameters[2] = Double.parseDouble(parameters[2]);
                ctorParameters[3] = Double.parseDouble(parameters[3]);
                ctorParameters[4] = Double.parseDouble(parameters[4]);
                ctorParameters[5] = Boolean.parseBoolean(parameters[5]);
                ctorParameters[6] = LocalDateTime.parse(parameters[6]);
                ctorParameters[7] = Double.parseDouble(parameters[7]);
                SolarDevice device = (SolarDevice) solarDeviceCtor.newInstance(ctorParameters);
                devices.add(device);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return devices;
    }
}

class TestGetDeviceObjectsFromCSV {
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "/src/SolarDevices.csv";
        List<SolarDevice> devices = DeviceObjectsFromCSV.GetSolarDevicesFromCSV(filePath);
        devices.forEach(device -> System.out.println(device));
    }
}
