package com.jumjari.zobiac.application.classroom.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SignHelperService {
    public byte[] getBytes(List<String> file) {
        StringBuilder builder = new StringBuilder();
        for (String string : file) {
            builder.append(string);
        }
        return builder.toString().getBytes(StandardCharsets.UTF_8);
    }
}