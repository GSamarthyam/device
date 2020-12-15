package after;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SolarDevice {
    private UUID deviceId;
    private String name;
    private double powerGenerationCapacity;
    private double latitude;
    private double longitude;
    private boolean status;
    private LocalDateTime startDateTime;
    private double generatedPower;

    private SolarDevice() {
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

    static class Builder {
        SolarDevice device = new SolarDevice();

        public Builder() {
        }

        public Builder setUUID(UUID deviceId) {
            device.deviceId = deviceId;
            return this;
        }

        public Builder name(String name) {
            device.name = name;
            return this;
        }

        public Builder powerGenerationCapacity(double powerGenerationCapacity) {
            device.powerGenerationCapacity = powerGenerationCapacity;
            return this;
        }

        public Builder location(double latitude, double longitude) {
            device.latitude = latitude;
            device.longitude = longitude;
            return this;
        }

        public Builder status(boolean status) {
            device.status = status;
            return this;
        }

        public Builder startDateTime(LocalDateTime startDateTime) {
            device.startDateTime = startDateTime;
            return this;
        }

        public Builder generatedPower(double generatedPower) {
            device.generatedPower = generatedPower;
            return this;
        }

        public SolarDevice build() {
            return device;
        }
    }
}

// abstract factory pattern
class SolarDeviceFactory {
    public List<SolarDevice> makeSolarDevicesFromCSV(String path) {
        List<SolarDevice> devices = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(line -> makeAndAddSolarDevice(line, devices));
        } catch (IOException e) {
            System.err.println("Invalid file path given for processing the csv file");
            System.exit(-1);
        }
        return devices;
    }

    private void makeAndAddSolarDevice(String input, List<SolarDevice> devices) {
        String[] deviceEntries = input.split(",");

        SolarDevice device = new SolarDevice.Builder()
                .setUUID(UUID.fromString(deviceEntries[0]))
                .name(deviceEntries[1])
                .powerGenerationCapacity(Double.parseDouble(deviceEntries[2]))
                .location(Double.parseDouble(deviceEntries[3]), Double.parseDouble(deviceEntries[4]))
                .status(Boolean.parseBoolean(deviceEntries[5]))
                .startDateTime(LocalDateTime.parse(deviceEntries[6]))
                .generatedPower(Double.parseDouble(deviceEntries[7]))
                .build();

        devices.add(device);
    }
}

class TestGetDeviceObjectsFromCSV {
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "/src/SolarDevices.csv";
        List<SolarDevice> devices = new SolarDeviceFactory().makeSolarDevicesFromCSV(filePath);
        devices.forEach(device -> System.out.println(device));
    }
}
