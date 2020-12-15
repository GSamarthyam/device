package after.resources;

import java.util.ResourceBundle;

/**
 * Utility class which enables Localization of the various messages shown in the report.
 * This class uses the Locale and ResourceBundle features of Java to obtain locale-specific content.
 * The underlying values are stored in the .properties file in the format "base file name_locale.properties"
 * See the Java API Document for example http://docs.oracle.com/javase/6/docs/api/java/util/ResourceBundle.html 
*/

public final class DeviceResources {
	private static ResourceBundle bundle;
		
	static {
		bundle = ResourceBundle.getBundle("after.resources.DeviceResources");
	}
	
	private DeviceResources() { }	

	public static final String DeviceReport = bundle.getString("DeviceReport");
	public static final String EmptyList = bundle.getString("EmptyList");
	public static final String DeviceID = bundle.getString("DeviceID");
	public static final String Devices = bundle.getString("Devices");
	public static final String Total = bundle.getString("Total");

	public static String getString(String deviceType) {		
		return bundle.getString(deviceType);
	}
}
