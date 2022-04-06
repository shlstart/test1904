package com.bjpowernode.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author shlstart
 * @date 4/3/2022-5:32 PM
 */
public class TransactionInvocationHandler implements InvocationHandler {

    // target
    private Object target;
    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }


    // this invoke method is call by poxy obj
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)  {
        SqlSession session = null;
        Object obj = null;
        try {
            // transaction business
            // here target is from service class
            // try to enhance target method with transaction
            session = SqlSessionUtil.getSession();
            // this method is proxy's method name
            obj = method.invoke(target, args);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlSessionUtil.myClose(session);
        }
        return obj;
    }
}
