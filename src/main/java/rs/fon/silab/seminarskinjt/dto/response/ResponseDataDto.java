/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.dto.response;

/**
 *
 * @author Bozidar
 */
public class ResponseDataDto {

    private String status = "OK";
    private String message;
    private Object result;

    public ResponseDataDto() {
    }

    public ResponseDataDto(String message, Object result) {
        this.message = message;
        this.result = result;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    
}
