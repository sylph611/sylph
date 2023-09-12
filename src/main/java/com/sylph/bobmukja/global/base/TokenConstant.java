package com.sylph.bobmukja.global.base;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class TokenConstant {
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public final Long ACCESS_TOKEN_VALID_MILLISECOND = 30 * 60 * 1000L; // 0.5 hour
    public static final Long REFRESH_TOKEN_VALID_MILLI_SECOND = 7 * 24 * 60 * 60 * 1000L; // 7 days
    public static final String PROVIDER = "provider";
    public static final String AUTHORITY = "authorities";
    public static final String EMAIL = "email";
    public static final String USER_ID = "userId";

}
