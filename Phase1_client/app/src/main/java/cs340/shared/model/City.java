package cs340.shared.model;

import com.google.android.gms.maps.model.LatLng;

public class City {
    public static final City ATLANTA = new City("Atlanta", "atlanta", 33.755, -84.39);
    public static final City BOSTON = new City("Boston", "boston", 42.361145, -71.057083);
    public static final City CALGARY = new City("Calgary", "calgary", 51.0501100, -114.0852900);
    public static final City CHARLESTON = new City("Charleston", "charleston", 32.784618, -79.940918);
    public static final City CHICAGO = new City("Chicago", "chicago", 41.881832, -87.623177);
    public static final City DALLAS = new City("Dallas", "dallas", 32.897480, -97.040443);
    public static final City DENVER = new City("Denver", "denver", 39.742043, -104.991531);
    public static final City DULUTH = new City("Duluth", "duluth", 46.786671, -92.100487);
    public static final City EL_PASO = new City("El Paso", "elpaso", 31.772543, -106.460953);
    public static final City HELENA = new City("Helena", "helena", 46.595806, -112.027031);
    public static final City HOUSTON = new City("Houston", "houston", 29.7632800, -95.3632700);
    public static final City KANSAS_CITY = new City("Kansas City", "kansascity", 39.0997300, -94.5785700);
    public static final City LAS_VEGAS = new City("Las Vegas", "lasvegas", 36.114647, -115.172813);
    public static final City LITTLE_ROCK = new City("Little Rock", "littlerock", 34.746483, -92.289597);
    public static final City LOS_ANGELES = new City("Los Angeles", "losangeles", 34.052235, -118.243683);
    public static final City MIAMI = new City("Miami", "miami", 25.761681, -80.191788);
    public static final City MONTREAL = new City("Montreal", "montreal", 45.6320200, -73.5075000);
    public static final City NASHVILLE = new City("Nashville", "nashville", 36.174465, -86.767960);
    public static final City NEW_ORLEANS = new City("New Orleans", "neworleans", 29.951065, -90.071533);
    public static final City NEW_YORK = new City("New York", "newyork", 40.730610, -73.935242);
    public static final City OKLAHOMA_CITY = new City("Oklahoma City", "oklahomacity", 35.481918, -97.508469);
    public static final City OMAHA = new City("Omaha", "omaha", 41.257160, -95.995102);
    public static final City PHOENIX = new City("Phoenix", "phoenix", 33.448376, -112.074036);
    public static final City PITTSBURGH = new City("Pittsburgh", "pittsburgh", 40.440624, -79.995888);
    public static final City PORTLAND = new City("Portland", "portland", 45.523064, -122.676483);
    public static final City RALEIGH = new City("Raleigh", "raleigh", 35.787743, -78.644257);
    public static final City SALT_LAKE_CITY = new City("Salt Lake City", "saltlakecity", 40.758701, -111.876183);
    public static final City SAN_FRANCISCO = new City("San Francisco", "sanfrancisco", 37.773972, -122.431297);
    public static final City SANTA_FE = new City("Santa Fe", "santafe", 35.691544, -105.944183);
    public static final City SAULT_ST_MARIE = new City("Sault St. Marie", "saultstmarie", 46.496944, -84.345556);
    public static final City SEATTLE = new City("Seattle", "seattle", 47.608013, -122.335167);
    public static final City ST_LOUIS = new City("St. Louis", "stlouis", 38.627003, -90.199402);
    public static final City TORONTO = new City("Toronto", "toronto", 43.761539, -79.411079);
    public static final City VANCOUVER = new City("Vancouver", "vancouver", 49.246292, -123.116226);
    public static final City WASHINGTON = new City("Washington", "washington", 38.889931, -77.009003);
    public static final City WINNIPEG = new City("Winnipeg", "winnipeg", 49.895077, -97.138451);
    private String key;
    private Double latitude;
    private Double longitude;
    private String name;

    private City(String name, String key, Double latitude, Double longitude) {
        this.key = key;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }
}
