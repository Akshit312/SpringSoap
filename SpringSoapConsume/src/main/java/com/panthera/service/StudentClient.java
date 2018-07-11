/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.service;

import com.panthera.student_ws.AddStudentRequest;
import com.panthera.student_ws.AddStudentResponse;
import com.panthera.student_ws.DeleteStudentRequest;
import com.panthera.student_ws.DeleteStudentResponse;
import com.panthera.student_ws.GetAllStudentRequest;
import com.panthera.student_ws.GetAllStudentResponse;
import com.panthera.student_ws.GetStudentByIdRequest;
import com.panthera.student_ws.GetStudentByIdResponse;
import com.panthera.student_ws.Students;
import com.panthera.student_ws.UpdateStudentRequest;
import com.panthera.student_ws.UpdateStudentResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 *
 * @author Administrator
 */
public class StudentClient extends WebServiceGatewaySupport {

    public GetStudentByIdResponse getStudentById(long studentId) {
        GetStudentByIdRequest request = new GetStudentByIdRequest();
        request.setStudentId(studentId);
        GetStudentByIdResponse response = (GetStudentByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/soapws/getStudentByIdRequest"));
        return response;
    }

    public GetAllStudentResponse getAllStudents() {
        GetAllStudentRequest request = new GetAllStudentRequest();
        GetAllStudentResponse response = (GetAllStudentResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/soapws/getAllStudentRequest"));
        return response;
    }

    public AddStudentResponse addArticle(String firstName, String lastName) {
        AddStudentRequest request = new AddStudentRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        AddStudentResponse response = (AddStudentResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/soapws/addStudentRequest"));
        return response;
    }

    public UpdateStudentResponse updateArticle(Students student) {
        UpdateStudentRequest request = new UpdateStudentRequest();
        request.setStudents(student);
        UpdateStudentResponse response = (UpdateStudentResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/soapws/updateStudentRequest"));
        return response;
    }

    public DeleteStudentResponse deleteArticle(long student) {
        DeleteStudentRequest request = new DeleteStudentRequest();
        request.setStudentId(student);
        DeleteStudentResponse response = (DeleteStudentResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/soapws/deleteStudentRequest"));
        return response;
    }
}
