package work.toolset.code.jvm.web.minapp.database;

import org.jetbrains.annotations.Nullable;
import work.toolset.code.jvm.web.minapp.database.query.Query;
import work.toolset.code.jvm.web.minapp.util.PagedList;
import work.toolset.code.jvm.web.minapp.util.BaseCallback;
import work.toolset.code.jvm.web.minapp.util.Util;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

public class Table
{
    
    private String tableName;
    
    
    public Table(String tableName)
    {
        this.tableName = tableName;
    }
    
    public String getTableName()
    {
        return tableName;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Table)
        {
            return this.tableName.equals(((Table) obj).tableName);
        }
        return false;
    }
    
    
    /******************************** simple curd **************************************/
    
    
    public void countInBackground(final Query query, BaseCallback<Integer> cb)
    {
        Util.inBackground(cb, new Callable<Integer>()
        {
            @Override
            public Integer call() throws Exception
            {
                List list = query(query).getObjects();
                return list != null ? list.size() : 0;
            }
        });
    }
    
    /**
     * 通过此 api 创建一条新记录
     *
     * @return
     */
    public Record createRecord()
    {
        return new Record(this);
    }
    
    /**
     * 通过此 api 获取一条记录
     *
     * @return
     */
    public Record fetchRecord(String recordId, Query query) throws Exception
    {
        return Database.fetch(this, recordId, query);
    }
    
    public Record fetchRecord(
            String recordId,
            @Nullable Collection<String> expands,
            @Nullable Collection<String> keys
    ) throws Exception
    {
        
        Query query = new Query();
        query.put(Query.EXPAND, Util.joinToNull(expands));
        query.put(Query.KEYS, Util.joinToNull(keys));
        return fetchRecord(recordId, query);
    }
    
    public Record fetchRecord(String recordId) throws Exception
    {
        return fetchRecord(recordId, null);
    }
    
    public void fetchRecordInBackground(final String recordId, final Query query, final BaseCallback<Record> cb)
    {
        Util.inBackground(cb, new Callable<Record>()
        {
            @Override
            public Record call() throws Exception
            {
                return fetchRecord(recordId, query);
            }
        });
    }
    
    public void fetchRecordInBackground(
            final String recordId,
            @Nullable final Collection<String> expands,
            @Nullable final Collection<String> keys,
            BaseCallback<Record> cb
    )
    {
        
        Util.inBackground(cb, new Callable<Record>()
        {
            @Override
            public Record call() throws Exception
            {
                return fetchRecord(recordId, expands, keys);
            }
        });
    }
    
    public void fetchRecordInBackground(final String recordId, BaseCallback<Record> cb)
    {
        Util.inBackground(cb, new Callable<Record>()
        {
            @Override
            public Record call() throws Exception
            {
                return fetchRecord(recordId);
            }
        });
    }
    
    /**
     * 查询
     *
     * @param query
     * @return
     * @throws Exception
     */
    public PagedList<Record> query(Query query) throws Exception
    {
        return Database.query(this, query);
    }
    
    /**
     * 查询
     *
     * @param query
     * @param cb
     * @see #query(Query)
     */
    public void queryInBackground(final Query query, final BaseCallback<PagedList<Record>> cb)
    {
        Util.inBackground(cb, new Callable<PagedList<Record>>()
        {
            @Override
            public PagedList<Record> call() throws Exception
            {
                return query(query);
            }
        });
    }
    
    /**
     * 根据 id 构造一条记录，但不去抓取这条记录的内容，所以这条记录只有 table 和 id 信息
     *
     * @param id
     * @return
     */
    public Record fetchWithoutData(String id)
    {
        Record record = createRecord();
        record.put(Record.ID, id);
        return record;
    }
    
    
    /******************************** batch operation **************************************/
    
    /**
     * 批量删除，通过 where 查询条件
     *
     * @param query
     * @return
     * @throws Exception
     */
    public BatchResult batchDelete(Query query) throws Exception
    {
        return Database.batchDelete(this, query);
    }
    
    /**
     * 批量保存
     *
     * @param records
     * @return
     * @throws Exception
     */
    public BatchResult batchSave(List<Record> records) throws Exception
    {
        return Database.batchSave(this, records, null);
    }
    
    /**
     * 批量保存
     *
     * @param records
     * @return
     * @throws Exception
     */
    public BatchResult batchSave(List<Record> records, Query query) throws Exception
    {
        return Database.batchSave(this, records, query);
    }
    
    /**
     * 批量更新
     *
     * @param query
     * @param update
     * @return
     * @throws Exception
     */
    private BatchResult batchUpdate(Query query, Record update) throws Exception
    {
        return Database.batchUpdate(this, query, update);
    }
    
    
    public void batchDeleteInBackground(final Query query, final BaseCallback<BatchResult> cb)
    {
        Util.inBackground(cb, new Callable<BatchResult>()
        {
            @Override
            public BatchResult call() throws Exception
            {
                return batchDelete(query);
            }
        });
    }
    
    public void batchSaveInBackground(final List<Record> records, final BaseCallback<BatchResult> cb)
    {
        Util.inBackground(cb, new Callable<BatchResult>()
        {
            @Override
            public BatchResult call() throws Exception
            {
                return batchSave(records);
            }
        });
    }
    
    public void batchSaveInBackground(final List<Record> records, final Query query, final BaseCallback<BatchResult> cb)
    {
        Util.inBackground(cb, new Callable<BatchResult>()
        {
            @Override
            public BatchResult call() throws Exception
            {
                return batchSave(records, query);
            }
        });
    }
    
    public void batchUpdateInBackground(final Query query, final Record update, final BaseCallback<BatchResult> cb)
    {
        Util.inBackground(cb, new Callable<BatchResult>()
        {
            @Override
            public BatchResult call() throws Exception
            {
                return batchUpdate(query, update);
            }
        });
    }
    
}
