package com.macsim.http.server.handler.get;

import com.macsim.http.server.databasecontroller.DataBaseController;
import com.macsim.http.server.handler.utils.HttpPrimitiveHandler;
import com.macsim.http.server.obj.Tariff;
import com.serializer.json.JSONArray;
import com.serializer.json.JSONException;
import com.serializer.json.JSONObject;
import com.serializer.parser.JsonObjectSerializer;

import java.util.ArrayList;

public class TariffHandler extends HttpPrimitiveHandler {

    private DataBaseController db = new DataBaseController();
    private JsonObjectSerializer serializer = new JsonObjectSerializer();

    @Override
    public void postHandle() {
        JSONObject answer = new JSONObject();
        JSONArray tariffsJsonArray = new JSONArray();
        ArrayList<Tariff> tariffs = db.getAllTariffs();
        for(Tariff tariff : tariffs){
            tariffsJsonArray.put(serializer.serializeObject(tariff));
        }
        try {
            answer.put("Tariffs", tariffsJsonArray);
        } catch (JSONException e) {
            sendError( "Серверная ошибка", 500);
            return;
        }
        sendRequest(answer.toString(), 200);
    }
}
