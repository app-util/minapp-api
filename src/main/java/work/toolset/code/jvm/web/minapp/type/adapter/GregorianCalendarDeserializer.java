package work.toolset.code.jvm.web.minapp.type.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import work.toolset.code.jvm.web.minapp.util.DateUtil;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GregorianCalendarDeserializer implements JsonDeserializer<GregorianCalendar>
{
    
    @Override
    public GregorianCalendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException
    {
        try
        {
            Calendar calendar = DateUtil.parseDBDateString(json.getAsString());
            GregorianCalendar retVal = new GregorianCalendar();
            if (calendar == null)
            {
                //todo:完善发生错误的原因：正则表达式不匹配
                throw new JsonParseException(new NullPointerException());
            }
            retVal.setTimeZone(calendar.getTimeZone());
            retVal.setTimeInMillis(calendar.getTimeInMillis());
            return retVal;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
