package project.com.nybestsellerbooksapp;

/**
 * Created by mansshah on 7/4/18.
 */

public class NyBsClient {


    private static NyBsClient _instance = null;

    private NyBsClient(){}

    public static synchronized NyBsClient getInstance() {
        if (_instance == null) {
            _instance = new NyBsClient();
        }
        return _instance;
    }


    static {
        loadNDKLibrary();
    }


    private static void loadNDKLibrary(){

        try{
            System.loadLibrary("nybestseller");
        }
        catch(Error e){
            throw new RuntimeException("Cannot load native library");
        }

    }

    public static native String getGIdentitifer();

    public static native String getBSIdentifier();

}
