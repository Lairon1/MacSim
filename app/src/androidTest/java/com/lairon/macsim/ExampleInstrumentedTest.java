package com.lairon.macsim;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.lairon.macsim.http.helper.GetHttpRequest;
import com.lairon.macsim.http.utils.HttpServerULR;
import com.lairon.macsim.servlet.api.MacSimWepApi;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void httpGetRequestTest() {
        new GetHttpRequest((request, code) -> {
            assertEquals(request, "{\"Tariffs\":[{\"ID\":1,\"Name\":\"Безлимитный интернет\",\"Price\":899,\"Description\":\"Безлимитный интернет на каждом уголке планеты\",\"Minutes\":400,\"Gigabytes\":-1,\"SMS\":300},{\"ID\":2,\"Name\":\"Безлимитные минуты и SMS\",\"Price\":599,\"Description\":\"Общайтесь сколько влезет!\",\"Minutes\":-1,\"Gigabytes\":10,\"SMS\":-1},{\"ID\":3,\"Name\":\"Для дома\",\"Price\":299,\"Description\":\"Тариф для дома\",\"Minutes\":100,\"Gigabytes\":5,\"SMS\":100},{\"ID\":4,\"Name\":\"Безлимит на все!\",\"Price\":999,\"Description\":\"Безлимитные минуты, гигабайты и SMS!\",\"Minutes\":-1,\"Gigabytes\":-1,\"SMS\":-1},{\"ID\":6,\"Name\":\"Стандарт\",\"Price\":299,\"Description\":\"Самый обычный тариф\",\"Minutes\":250,\"Gigabytes\":30,\"SMS\":50},{\"ID\":20,\"Name\":\"Супер онлайн+\",\"Price\":699,\"Description\":\"Тариф для тех кто всегда на связи\",\"Minutes\":1000,\"Gigabytes\":40,\"SMS\":0},{\"ID\":21,\"Name\":\"Мой разговор\",\"Price\":399,\"Description\":\"Для самых долгих бесед\",\"Minutes\":200,\"Gigabytes\":10,\"SMS\":0},{\"ID\":22,\"Name\":\"Игровой\",\"Price\":890,\"Description\":\"Для гей меров\",\"Minutes\":700,\"Gigabytes\":30,\"SMS\":100}]}");
        }).execute(HttpServerULR.TARIFF);
    }

    @Test
    public void getTariffsMacSimWebApiTest(){
        MacSimWepApi wepApi = new MacSimWepApi();

        wepApi.getAllTariffs(tariffList -> {
            assertEquals(tariffList.size(), 8);
            return;
        });
    }
}