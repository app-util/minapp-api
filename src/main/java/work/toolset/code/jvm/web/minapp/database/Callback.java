package work.toolset.code.jvm.web.minapp.database;


import org.jetbrains.annotations.Nullable;

public interface Callback {

    void onSuccess(Record record);

    void onFailure(@Nullable Record record, Exception e);
}
