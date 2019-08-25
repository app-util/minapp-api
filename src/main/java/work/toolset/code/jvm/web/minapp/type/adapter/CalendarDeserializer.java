package work.toolset.code.jvm.web.minapp.type.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import work.toolset.code.jvm.web.minapp.util.DateUtil;

import java.lang.reflect.Type;
import java.util.Calendar;

public class CalendarDeserializer implements JsonDeserializer<Calendar>
{
    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        return json.isJsonNull() ? null : DateUtil.parseDBDateString(json.getAsString());
    }
}
