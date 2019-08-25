package work.toolset.code.jvm.web.minapp.database;

import com.google.gson.*;

import java.lang.reflect.Type;

public class GeoPoint
{
    
    /**
     * 经度
     */
    private float longitude;
    
    /**
     * 纬度
     */
    private float latitude;
    
    public GeoPoint()
    {
    }
    
    public GeoPoint(float longitude, float latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public float getLongitude()
    {
        return longitude;
    }
    
    public float getLatitude()
    {
        return latitude;
    }
    
    /**
     * {
     * coordinates: [10.123, 8.543],
     * type: "Point"
     * }
     */
    public static class Serializer implements JsonSerializer<GeoPoint>
    {
        @Override
        public JsonElement serialize(GeoPoint src, Type typeOfSrc, JsonSerializationContext context)
        {
            JsonObject json = new JsonObject();
            json.addProperty("type", "Point");
            JsonArray coordinates = new JsonArray(2);
            coordinates.add(src.getLongitude());
            coordinates.add(src.getLatitude());
            json.add("coordinates", coordinates);
            return json;
        }
    }
    
    public static class Deserializer implements JsonDeserializer<GeoPoint>
    {
        @Override
        public GeoPoint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            try
            {
                JsonArray coordinates = ((JsonObject) json).getAsJsonArray("coordinates");
                return new GeoPoint(
                        coordinates.get(0).getAsFloat(),
                        coordinates.get(1).getAsFloat()
                );
            }
            catch (Exception e)
            {
                throw new JsonParseException(e);
            }
        }
    }
}
