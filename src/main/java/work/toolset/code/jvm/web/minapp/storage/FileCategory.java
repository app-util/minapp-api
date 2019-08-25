package work.toolset.code.jvm.web.minapp.storage;

import org.jetbrains.annotations.Nullable;
import work.toolset.code.jvm.web.minapp.category.BaseCategory;

public class FileCategory extends BaseCategory {

    public static final String FILES = "files";

    public @Nullable
    Long getFiles() {
        return getLong(FILES);
    }

    public void setFiles(Long files) {
        put(FILES, files);
    }

}
