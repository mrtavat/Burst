package burstcoin.com.burst;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

/**
 * Created by IceBurst on 7/7/2016.
 */

public class GetAsync extends AsyncTask<String, String, JSONObject> {

    JSONParser jsonParser = new JSONParser();

    private JSONCaller cb;
    private String URL = "" ;
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_MESSAGE = "message";

    public GetAsync(String u) {
        this.URL = u;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected JSONObject doInBackground(String... args) {

        try {

            HashMap<String, String> params = new HashMap<>();
            JSONObject json = jsonParser.makeHttpRequest(URL, "GET", params);

            if (json != null) {
                //Log.d("JSON result", json.toString());
                return json;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(JSONObject json) {

        // Most of the Time This will be Overridden
        int success = 0;
        String message = "";

        if (json != null) {
            //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_LONG).show();
            try {
                success = json.getInt(TAG_SUCCESS);
                message = json.getString(TAG_MESSAGE);
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }

        if (success == 1) {
            //Log.d("Success!", message);
        }else{
            //Log.d("Failure", message);
        }
    }
}