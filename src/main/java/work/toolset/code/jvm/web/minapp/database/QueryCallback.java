package work.toolset.code.jvm.web.minapp.database;

import work.toolset.code.jvm.web.minapp.util.PagedList;

public interface QueryCallback {

    void onSuccess(PagedList<Record> list);

    void onFailure(Table table, Exception e);
}
