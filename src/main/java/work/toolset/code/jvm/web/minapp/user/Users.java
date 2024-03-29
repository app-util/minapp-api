package work.toolset.code.jvm.web.minapp.user;

import work.toolset.code.jvm.web.minapp.Global;
import work.toolset.code.jvm.web.minapp.database.query.Query;
import work.toolset.code.jvm.web.minapp.util.BaseCallback;
import work.toolset.code.jvm.web.minapp.util.PagedList;
import work.toolset.code.jvm.web.minapp.util.Util;

import java.util.concurrent.Callable;

public abstract class Users
{
    
    /**
     * 用户列表
     *
     * @return
     * @throws Exception
     */
    public static PagedList<User> users(Query query) throws Exception
    {
        return Global.httpApi().users(query != null ? query : new Query()).execute().body().readonly();
    }
    
    public static void usersInBackground(final Query query, BaseCallback<PagedList<User>> cb)
    {
        Util.inBackground(cb, new Callable<PagedList<User>>()
        {
            @Override
            public PagedList<User> call() throws Exception
            {
                return Users.users(query);
            }
        });
    }
    
    /**
     * 用户明细
     *
     * @param id
     * @return
     * @throws Exception
     */
    public static User user(String id) throws Exception
    {
        return Global.httpApi().user(id).execute().body();
    }
    
    public static User user(Number id) throws Exception
    {
        return Users.user(id != null ? id.toString() : "");
    }
    
    
    public static void userInBackground(final String id, BaseCallback<User> cb)
    {
        Util.inBackground(cb, new Callable<User>()
        {
            @Override
            public User call() throws Exception
            {
                return Users.user(id);
            }
        });
    }
    
    public static void userInBackground(Number id, BaseCallback<User> cb)
    {
        Users.userInBackground(id != null ? id.toString() : "", cb);
    }
    
    public static User userWithoutData(Number id)
    {
        User user = new User();
        user.put(User.ID, id);
        return user;
    }
}
