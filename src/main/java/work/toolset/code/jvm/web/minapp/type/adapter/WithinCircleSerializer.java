package work.toolset.code.jvm.web.minapp.type.adapter;

import com.google.gson.*;
import work.toolset.code.jvm.web.minapp.database.GeoPoint;
import work.toolset.code.jvm.web.minapp.database.query.WithinCircle;

import java.lang.reflect.Type;

public class WithinCircleSerializer implements JsonSerializer<WithinCircle> {
    @Override
    public JsonElement serialize(WithinCircle src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("radius", src.getRadius());

        JsonArray coordinates = new JsonArray(2);
        json.add("coordinates", coordinates);
        GeoPoint center = src.getCenter();
        if (center != null) {
            coordinates.add(center.getLongitude());
            coordinates.add(center.getLatitude());
        }

        return json;
    }
}
