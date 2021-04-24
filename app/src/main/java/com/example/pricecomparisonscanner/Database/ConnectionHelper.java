package com.example.pricecomparisonscanner.Database;
//
//import android.os.StrictMode;
//import android.util.Log;
//
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//
public class ConnectionHelper {
//
//    private Connection connection;
//    private String uname, pw, ip, port, db;
//
//    public Connection connectionClass() {
//        ip = Login.getIp();
//        port = Login.getPort();
//        pw = Login.getPassword();
//        db = Login.getDatabase();
//        uname = Login.getUsername();
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        Connection connection = null;
//
//        String ConnectionURL = null;
//
//        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            ConnectionURL= "jdbc:jtds:sqlserver://" + ip + ":" + port + "/ " + db + ";" + "user=" + uname + ";password=" + pw + ";" + "databaseName=" + db;
//            //connection = DriverManager.getConnection(ConnectionURL);
//            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + db + ";databaseName=" + db, uname, pw);
//        } catch (Exception e) {
//            Log.e("Error ", e.getMessage());
//        }
//
//        return connection;
//    }
}
