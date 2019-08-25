package work.toolset.code.jvm.web.minapp.category;

import org.jetbrains.annotations.Nullable;
import work.toolset.code.jvm.web.minapp.database.Record;

public class BaseCategory extends Record {

    public static final String NAME = "name";

    public @Nullable
    String getName() {
        return getString(BaseCategory.NAME);
    }

    public void setName(String name) {
        put(BaseCategory.NAME, name);
    }
}
