package com.arindam.tms.utility;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.TimeZone;

import com.arindam.tms.models.User;
import com.arindam.tms.security.model.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@UtilityClass
public class AppUtilities {

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        objectMapper.setTimeZone(TimeZone.getDefault());
        // to handle date/time value in mapping
        objectMapper.registerModule(new JavaTimeModule());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        objectMapper.registerModule(module);
        //objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    public static User getLoggedInUser() {
        User user = new User();
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setId(userDetails.getLoginUserId());
        user.setUserName(userDetails.getUsername());
        return user;

    }
}
