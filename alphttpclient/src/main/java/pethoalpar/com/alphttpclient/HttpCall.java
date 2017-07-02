package pethoalpar.com.alphttpclient;

import java.util.HashMap;

/**
 * Created by pethoalpar on 4/16/2016.
 */
public class HttpCall {

    public static final int GET = 1;
    public static final int POST = 2;
    public static final int STRING = 5;
    public static final int BYTE_ARRAY = 6;

    private String url;
    private int methodtype;
    private int returnTye = STRING;
    private HashMap<String,String> params ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMethodtype() {
        return methodtype;
    }

    public void setMethodtype(int methodtype) {
        this.methodtype = methodtype;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public int getReturnTye() {
        return returnTye;
    }

    public void setReturnTye(int returnTye) {
        this.returnTye = returnTye;
    }
}
