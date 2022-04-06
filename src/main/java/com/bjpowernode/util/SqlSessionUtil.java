package com.bjpowernode.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * SqlSession class provides sqlsession obj
 * @author shlstart
 * @date 4/3/2022-5:32 PM
 */
public class SqlSessionUtil {

    private SqlSessionUtil() {}

    private static SqlSessionFactory sqlSessionFactory = null;

    // create sqlSessionfactory when class is loaded into memory and only once
    static {
        // read config file
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    private static ThreadLocal<SqlSession> t = new ThreadLocal<>();
    /**
     * each thread should its own sqlSession(connection) for transaction
     * @return
     */
    public static SqlSession getSession() {
        SqlSession sqlSession = t.get();
        // sqlsession might be null if didnt add before
        if (sqlSession == null) {
            // create a new sqlSession
            sqlSession = sqlSessionFactory.openSession();
            t.set(sqlSession);
        }
        return sqlSession;
    }

    public static void myClose(SqlSession session) {
        if (session != null) {
            session.close();
            // dont foreget to remove session from threadlocal
            // current thread and session remove from threadlocal
            t.remove();
        }
    }

}
