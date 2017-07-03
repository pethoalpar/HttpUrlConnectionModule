package pethoalpar.com.alphttpclient.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by petho on 2017-07-02.
 */

public class RequestUtil {

    public static byte[] getBytes(InputStream inputStream){
        try {
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            return output.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getString(InputStream inputStream){
        try {
            String line ;
            StringBuilder response = new StringBuilder();
            BufferedReader br = new BufferedReader( new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null){
                response.append(line);
            }
            return response.toString();
        } catch (IOException e) {
        }
        return null;
    }
}
