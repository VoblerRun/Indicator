package com.example.indicator;

public class Validator {

    public boolean isCorrectAuthorizationData(AuthorizationData authorizationData){
        Boolean isCorrectData = false;
        if(authorizationData.getIsSuccessAuthorization() && !authorizationData.getTokenSession().isEmpty()){
            isCorrectData = true;
        }else{
            isCorrectData = false;
        }
        return isCorrectData;
    }

}
