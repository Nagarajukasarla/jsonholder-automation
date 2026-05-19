package com.nagaraju.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagaraju.data.PostData;
import com.nagaraju.data.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class JsonReader {
    private static final Logger log = LoggerFactory.getLogger(JsonReader.class);
    private static List<UserData> userData;
    private static List<PostData> postData;

    public static void load() {

        if (userData != null && postData != null) return;

        try {
            InputStream userInputStream = JsonReader.class.getClassLoader().getResourceAsStream("data/users.json");
            InputStream postInputStream = JsonReader.class.getClassLoader().getResourceAsStream("data/posts.json");

            ObjectMapper objectMapper = new ObjectMapper();
            userData = objectMapper.readValue(userInputStream, new TypeReference<List<UserData>>() {});
            postData = objectMapper.readValue(postInputStream, new TypeReference<List<PostData>>() {});

            log.info("Json data has been successfully loaded.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load json: ", e);
        }
    }

    public static List<UserData> loadUserData() {
        try {
            InputStream userInputStream = JsonReader.class.getClassLoader().getResourceAsStream("data/users.json");
            ObjectMapper objectMapper = new ObjectMapper();
            userData = objectMapper.readValue(userInputStream, new TypeReference<List<UserData>>() {});
            return userData;
        }
        catch (Exception e) {
            log.error("Failed to load user data: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public static List<PostData> loadPostData() {
         try {
             InputStream postInputStream = JsonReader.class.getClassLoader().getResourceAsStream("data/posts.json");
             ObjectMapper objectMapper = new ObjectMapper();
             postData = objectMapper.readValue(postInputStream, new TypeReference<List<PostData>>() {});
             return postData;
         }
         catch (Exception e) {
             log.error("Failed to load Post data: {}", e.getMessage());
             return Collections.emptyList();
         }
    }
}
