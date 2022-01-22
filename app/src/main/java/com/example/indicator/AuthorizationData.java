package com.example.indicator;

public class AuthorizationData {

        private String token = "";
        private Boolean isSuccessAuthorization = false;

        public AuthorizationData(){}

        public void setTokenSession(String tokenSession){
            this.token = tokenSession;
        }

        public String getTokenSession(){
            return this.token;
        }

        public void setIsSuccessAuthorization(Boolean isSuccessAuthorization){
            this.isSuccessAuthorization = isSuccessAuthorization;
        }

        public Boolean getIsSuccessAuthorization(){
            return this.isSuccessAuthorization;
        }

    }
