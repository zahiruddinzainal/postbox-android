package zvhir.dev.postboxx;

import com.google.firebase.database.Exclude;

public class Upload {

    private String mCode;
    private String mURL;
    private String mKey;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String code, String URL) {
        mCode = code;
        mURL = URL;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getURL() {
        return mURL;
    }

    public void setURL(String URL) {
        mURL = URL;
    }

    @Exclude
    public String getKey() {

        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}