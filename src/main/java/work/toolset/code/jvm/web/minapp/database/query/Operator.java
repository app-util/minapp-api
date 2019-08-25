package work.toolset.code.jvm.web.minapp.database.query;


public enum Operator
{
    
    EQ("eq"),                 // 等于
    NE("ne"),                 // 不等于
    LT("lt"),                 // 小于
    LTE("lte"),               // 小于等于
    GT("gt"),                 // 大于
    GTE("gte"),               // 大于等于
    CONTAINS("contains"),     // 包含任意一个值
    NIN("nin"),               // 不包含任意一个数组值
    IN("in"),                 // 包含任意一个数组值
    IS_NULL("isnull"),        // 是否为 NULL
    RANGE("range");           // 包含数组值区间的值
    
    final String value;
    
    Operator(String value)
    {
        this.value = value;
    }
    
    public static Operator from(String op)
    {
        if (op.equals(EQ.value))
        {
            return EQ;
        }
        else if (op.equals(NE.value))
        {
            return NE;
        }
        else if (op.equals(LT.value))
        {
            return LT;
        }
        else if (op.equals(LTE.value))
        {
            return LTE;
        }
        else if (op.equals(GT.value))
        {
            return GT;
        }
        else if (op.equals(GTE.value))
        {
            return GTE;
        }
        else if (op.equals(CONTAINS.value))
        {
            return CONTAINS;
        }
        else if (op.equals(NIN.value))
        {
            return NIN;
        }
        else if (op.equals(IN.value))
        {
            return IN;
        }
        else if (op.equals(IS_NULL.value))
        {
            return IS_NULL;
        }
        else if (op.equals(RANGE.value))
        {
            return RANGE;
        }
        else
        {
            throw new IllegalArgumentException(String.format("can't recognize operator [%s]", op));
        }
    }
}
