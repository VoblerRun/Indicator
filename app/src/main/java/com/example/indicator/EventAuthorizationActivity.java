package com.example.indicator;

public class EventAuthorizationActivity extends AuthorizationData{
    private static final EventAuthorizationActivity authorizationData  = new EventAuthorizationActivity();
    public static AuthorizationData getInstance() { return authorizationData; }
    private EventAuthorizationActivity() {}
}
