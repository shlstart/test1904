package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;

import java.util.List;

/**
 * @author shlstart
 * @date 4/3/2022-5:18 PM
 */
public interface StudentDao {
    public Student getById(String id);

    public void save(Student s);

    List<Student> getAll();


}
