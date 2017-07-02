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
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        } catch (IOException e) {
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
