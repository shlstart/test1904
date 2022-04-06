package com.bjpowernode.service.impl;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import com.bjpowernode.util.SqlSessionUtil;

import java.util.List;

/**
 * @author shlstart
 * @date 4/3/2022-5:30 PM
 */
public class StudentServiceImpl implements StudentService {
    // create proxy dao object
    StudentDao studentDao = SqlSessionUtil.getSession().getMapper(StudentDao.class);
    @Override
    public Student getById(String id) {
        return studentDao.getById(id);
    }

    @Override
    public void save(Student s) {
        studentDao.save(s);

    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }
}
