package me.jdvp.androidaspectexample.config;

public class ApiUrls {
    // Mazen
    // public static final String IP_ADDRESS="192.168.100.19";

    // Abouraya
    // public static final String IP_ADDRESS="192.168.1.3";

   // Etch
    public static final String IP_ADDRESS="192.168.1.3";

    // Login Port
    public static final String LOGIN_PORT=":3003/";

    public static final String LOGIN_URL = "http://"+IP_ADDRESS+LOGIN_PORT+"api/account/";

    //User Port
    public static final String USER_PORT=":3000/";

    public static final String  USER_URL = "http://"+IP_ADDRESS+USER_PORT+"api/users/";

    //Admin Port
    //User Port
    public static final String ADMIN_PORT=":3001/";

    public static final String  ADMIN_URL = "http://"+IP_ADDRESS+ADMIN_PORT+"api/admin/";


    //Agent Port
    public static final String AGENT_PORT=":3002/";

    public static final String AGENT_URL = "http://"+IP_ADDRESS+AGENT_PORT+"api/ticket-agent/";

    public static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/";

    public static final String API_KEY = "e10904f16c4332538809c497e421048c";
}
