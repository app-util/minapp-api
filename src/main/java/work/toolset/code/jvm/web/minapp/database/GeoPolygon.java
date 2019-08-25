package work.toolset.code.jvm.web.minapp.database;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeoPolygon
{
    
    private List<GeoPoint> points = new ArrayList<>();
    
    public List<GeoPoint> getPoints()
    {
        return points;
    }
    
    public void setPoints(List<GeoPoint> points)
    {
        this.points = points;
    }
    
    public GeoPolygon(List<GeoPoint> list)
    {
        if (list != null && list.size() > 0)
        {
            points.addAll(list);
        }
    }
    
    public GeoPolygon(GeoPoint... array)
    {
        if (array != null && array.length > 0)
        {
            points.addAll(Arrays.asList(array));
        }
    }
    
    public GeoPolygon(float[]... arrays)
    {
        if (arrays != null && arrays.length > 0)
        {
            for (float[] item : arrays)
            {
                if (item != null && item.length >= 2)
                {
                    points.add(new GeoPoint(item[0], item[1]));
                }
            }
        }
    }
    
    /**
     * {
     * coordinates: [[[10.123, 10], [20.12453, 10], [30.564654, 20], [20.654, 30], [10.123, 10]]],
     * type: "Polygon"
     * }
     */
    public static class Serializer implements JsonSerializer<GeoPolygon>
    {
        @Override
        public JsonElement serialize(GeoPolygon src, Type typeOfSrc, JsonSerializationContext context)
        {
            JsonObject json = new JsonObject();
            json.addProperty("type", "Polygon");
            
            List<GeoPoint> points = src.getPoints();
            JsonArray coordinates = new JsonArray();
            if (points != null && points.size() > 0)
            {
                for (GeoPoint point : points)
                {
                    JsonArray coordinate = new JsonArray();
                    coordinate.add(point.getLongitude());
                    coordinate.add(point.getLatitude());
                    coordinates.add(coordinate);
                }
            }
            
            JsonArray array = new JsonArray();
            array.add(coordinates);
            json.add("coordinates", array);
            return json;
        }
    }
    
    public static class Deserializer implements JsonDeserializer<GeoPolygon>
    {
        @Override
        public GeoPolygon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            try
            {
                JsonArray coordinates = ((JsonObject) json).getAsJsonArray("coordinates").get(0).getAsJsonArray();
                List<GeoPoint> points = new ArrayList<>(coordinates.size());
                
                for (int i = 0; i < coordinates.size(); i++)
                {
                    try
                    {
                        JsonArray coordinate = coordinates.get(i).getAsJsonArray();
                        GeoPoint point = new GeoPoint(coordinate.get(0).getAsFloat(), coordinate.get(1).getAsFloat());
                        points.add(point);
                    }
                    catch (Exception ignored) {}
                }
                return new GeoPolygon(points);
            }
            catch (Exception e)
            {
                throw new JsonParseException(e);
            }
        }
    }
}
