/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.endpoint;

import com.panthera.gs_ws.AddStudentRequest;
import com.panthera.gs_ws.AddStudentResponse;
import com.panthera.gs_ws.DeleteStudentRequest;
import com.panthera.gs_ws.DeleteStudentResponse;
import com.panthera.gs_ws.GetAllStudentResponse;
import com.panthera.gs_ws.GetStudentByIdRequest;
import com.panthera.gs_ws.GetStudentByIdResponse;
import com.panthera.gs_ws.ServiceStatus;
import com.panthera.gs_ws.Students;
import com.panthera.gs_ws.UpdateStudentRequest;
import com.panthera.gs_ws.UpdateStudentResponse;
import com.panthera.service.IStudentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StudentEndpoint {

    private static final String NAMESPACE_URI = "http://www.panthera.com/student-ws";
    @Autowired
    private IStudentService studentService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getArticleByIdRequest")
    @ResponsePayload
    public GetStudentByIdResponse getStudent(@RequestPayload GetStudentByIdRequest request) {
        GetStudentByIdResponse response = new GetStudentByIdResponse();
        response.setStudents(studentService.getStudentById(request.getStudentId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllArticlesRequest")
    @ResponsePayload
    public GetAllStudentResponse getAllArticles() {
        GetAllStudentResponse response = new GetAllStudentResponse();
        List<Students> studentList = new ArrayList<>();
        studentList = studentService.getAllStudents();
        response.getStudents().addAll(studentList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addArticleRequest")
    @ResponsePayload
    public AddStudentResponse addArticle(@RequestPayload AddStudentRequest request) {
        AddStudentResponse response = new AddStudentResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Students student = new Students();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setDob(request.getDob());

        boolean flag = studentService.addStudent(student);
        if (flag == false) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");
            response.setServiceStatus(serviceStatus);
        } else {
            response.setStudents(student);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
            response.setServiceStatus(serviceStatus);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateArticleRequest")
    @ResponsePayload
    public UpdateStudentResponse updateArticle(@RequestPayload UpdateStudentRequest request) {
        Students student = new Students();
        studentService.updateStudent(student);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Content Updated Successfully");
        UpdateStudentResponse response = new UpdateStudentResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteArticleRequest")
    @ResponsePayload
    public DeleteStudentResponse deleteArticle(@RequestPayload DeleteStudentRequest request) {
        Students student = studentService.getStudentById(request.getStudentId());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (student == null) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Content Not Available");
        } else {
            studentService.deleteStudent(student);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }
        DeleteStudentResponse response = new DeleteStudentResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

}
