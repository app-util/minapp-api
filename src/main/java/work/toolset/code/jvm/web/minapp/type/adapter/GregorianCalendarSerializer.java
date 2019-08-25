package work.toolset.code.jvm.web.minapp.type.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import work.toolset.code.jvm.web.minapp.util.DateUtil;

import java.lang.reflect.Type;
import java.util.GregorianCalendar;

public class GregorianCalendarSerializer implements JsonSerializer<GregorianCalendar> {
    
    @Override
    public JsonElement serialize(GregorianCalendar src, Type typeOfSrc, JsonSerializationContext context) {
        String string = DateUtil.formatDBDateString(src);
        return string != null ? new JsonPrimitive(string) : JsonNull.INSTANCE;
    }
    
}
