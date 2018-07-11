/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.service;

import com.panthera.gs_ws.Students;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface IStudentService {

    public Students getStudentById(long studentId);

    public List<Students> getAllStudents();

    public boolean addStudent(Students student);

    public void updateStudent(Students student);

    public void deleteStudent(Students student);
    
}
