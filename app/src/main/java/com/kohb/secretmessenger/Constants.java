package com.kohb.secretmessenger;

public class Constants {

    public static class URL {
        private static final String HOST = "http://192.168.10.102:8081/";

        public static final String GET_ALL_MESSAGES = HOST + "messages";
        public static final String GET_ALL_MESSAGES_IN_CHAT = HOST + "messages_chat/";

        public static final String GET_ALL_USERS = HOST + "users";
        public static final String SAVE_MESSAGE = HOST + "save_message";



    }
}
