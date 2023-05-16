package me.jdvp.androidaspectexample.config;

public class ApiUrls {
    // Mazen
    // public static final String IP_ADDRESS="192.168.100.19";

    // Abouraya
    public static final String IP_ADDRESS="192.168.1.2";

    // Login Port
    public static final String LOGIN_PORT=":3003/";

    public static final String LOGIN_URL = "http://"+IP_ADDRESS+LOGIN_PORT+"api/account/";

    //User Port
    public static final String USER_PORT=":3000/";

    public static final String  USER_URL = "http://"+IP_ADDRESS+USER_PORT+"api/users/";

    //Agent Port
    public static final String AGENT_PORT=":3002/";

    public static final String AGENT_URL = "http://"+IP_ADDRESS+AGENT_PORT+"api/ticket-agent/";


}
