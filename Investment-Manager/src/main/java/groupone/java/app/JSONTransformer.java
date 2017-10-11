package groupone.java.app;

import spark.ResponseTransformer;
import com.google.gson.*;

public class JSONTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }

}
