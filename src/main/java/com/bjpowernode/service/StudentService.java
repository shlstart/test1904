package com.bjpowernode.service;

import com.bjpowernode.domain.Student;

import java.util.List;

/**
 * @author shlstart
 * @date 4/3/2022-5:28 PM
 */
public interface StudentService {
    public Student getById(String id);

    void save(Student s);

    List<Student> getAll();
}
