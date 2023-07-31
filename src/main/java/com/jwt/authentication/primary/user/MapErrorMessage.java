package com.jwt.authentication.primary.user;

import java.util.Map;

public interface MapErrorMessage {
    public Map<String,String> MapErrorMessage(Exception exception);
}