package domain;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SensorTest {
    Sensor sensor;

    @BeforeAll
    void setUp() {
        ArrayList<SensorReading> sensorReadings = new ArrayList<SensorReading>();
        this.sensor = new Sensor(2, 1, 1,"Heat register");
        JSONObject obj = new JSONObject(json);
        JSONArray jsonReadings = obj.getJSONArray("readings");
        if (jsonReadings != null) {
            for (int i = 0; i < jsonReadings.length(); i++) {
                JSONObject jsonSensorReading = (JSONObject) jsonReadings.get(i);
                SensorReading sensorReading = null;
                int sensorReadingId = jsonSensorReading.getInt("sensorReadingId");
                int sensorId = jsonSensorReading.getInt("sensorId");
                String strDateTime = jsonSensorReading.getString("dateTime");
                LocalDateTime localDateTime = LocalDateTime.parse(strDateTime);
                float value = jsonSensorReading.getFloat("value");
                sensorReading = new SensorReading(sensorReadingId, sensorId, localDateTime, value);
                sensorReadings.add(sensorReading);
            }
        }
        this.sensor.setSensorReadings(sensorReadings);
    }

    @Test
    void findMinReadingIndex() {
        assertEquals(40, this.sensor.findMinReadingIndex());
    }

    @Test
    void findMinReadingIndex1() {

        assertEquals(8, this.sensor.findMinReadingIndex(2,10));
    }

    @Test
    void findMaxReadingIndex() {
        assertEquals(68, this.sensor.findMaxReadingIndex());
    }

    @Test
    void findMaxReadingIndex1() {
        assertEquals(5, this.sensor.findMaxReadingIndex(2,10));
    }


    @Test void findMinReadingTest2() {
        assertThrows(IndexOutOfBoundsException.class, () -> {this.sensor.findMinReadingIndex(-1, 10);});
    }

    @Test
    void findMinReadingTest3() {
        assertThrows(IndexOutOfBoundsException.class, () -> {this.sensor.findMinReadingIndex(1, 1000);});
    }

    @Test
    void findMinReadingTest4() {
        assertThrows(IndexOutOfBoundsException.class, () -> {this.sensor.findMinReadingIndex(1, 1);});
    }

    @Test
    void findMinReadingTest5() {
        assertThrows(IndexOutOfBoundsException.class, () -> {this.sensor.findMaxReadingIndex(-1, 10);});
    }

    @Test
    void findMinReadingTest6() {
        assertThrows(IndexOutOfBoundsException.class, () -> {this.sensor.findMaxReadingIndex(1, 1000);});
    }

    @Test
    void findMinReadingTest7() {
        assertThrows(IndexOutOfBoundsException.class, () -> {this.sensor.findMaxReadingIndex(1, 1);});
    }

    @Test
    void findNextCycleMaxIndex() {
        assertEquals(5, this.sensor.findNextCycleMaxIndex(0));
    }

    @Test
    void findNextCycleMinIndex() {
        assertEquals(8, this.sensor.findNextCycleMinIndex(5));
    }

    private String json = "{\n" +
            "  \"readings\": [\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60161,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:01:43.54235\",\n" +
            "      \"value\": 29,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60162,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:03:04.7657807\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60163,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:05:06.5187565\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60164,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:05:47.3773175\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60165,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:06:48.2299219\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60166,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:07:28.8307888\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60167,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:08:09.4228174\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60168,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:08:29.7470375\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60169,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:08:50.0713275\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60170,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:09:10.414181\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60171,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:09:30.9600659\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60172,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:10:32.0007729\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60173,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:11:53.0991712\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60174,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:13:54.6877721\",\n" +
            "      \"value\": 35,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60175,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:14:35.260303\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60176,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:14:55.5949902\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60177,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:15:15.9163545\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60178,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:15:36.4555901\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60179,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:15:56.780231\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60180,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:17:38.1135469\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60181,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:17:58.438008\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60182,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:18:39.0121566\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60183,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:18:59.3359573\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60184,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:19:39.9102218\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60185,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:20:40.7363397\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60186,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:22:01.8122458\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60187,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:23:22.8950029\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60188,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:26:05.196114\",\n" +
            "      \"value\": 29,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60189,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:27:26.2865441\",\n" +
            "      \"value\": 28,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60190,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:30:48.8790083\",\n" +
            "      \"value\": 27,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60191,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:33:30.971949\",\n" +
            "      \"value\": 28,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60192,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:36:13.0535836\",\n" +
            "      \"value\": 27,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60193,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:42:17.693931\",\n" +
            "      \"value\": 26,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60194,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T10:48:02.0387876\",\n" +
            "      \"value\": 25,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60195,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T11:01:12.9611326\",\n" +
            "      \"value\": 24,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60196,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T11:22:33.0648328\",\n" +
            "      \"value\": 23,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60201,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T11:50:55.0672955\",\n" +
            "      \"value\": 22,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60202,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T11:51:15.4076671\",\n" +
            "      \"value\": 23,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60203,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T11:51:35.9459439\",\n" +
            "      \"value\": 22,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60208,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T12:32:51.469847\",\n" +
            "      \"value\": 21,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60213,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:48:08.1320987\",\n" +
            "      \"value\": 20,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60214,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:48:28.4541734\",\n" +
            "      \"value\": 21,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60215,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:48:48.7767317\",\n" +
            "      \"value\": 20,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60216,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:50:09.8739666\",\n" +
            "      \"value\": 21,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60217,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:51:10.7074963\",\n" +
            "      \"value\": 22,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60218,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:51:31.0383578\",\n" +
            "      \"value\": 23,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60219,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:52:11.6218027\",\n" +
            "      \"value\": 24,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60220,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:52:52.212881\",\n" +
            "      \"value\": 25,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60221,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:53:32.7856902\",\n" +
            "      \"value\": 26,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60222,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:53:53.1214268\",\n" +
            "      \"value\": 27,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60223,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:54:33.9108861\",\n" +
            "      \"value\": 28,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60224,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:55:34.7362142\",\n" +
            "      \"value\": 29,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60225,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:55:55.3411377\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60226,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:56:35.9303777\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60227,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:56:56.2663413\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60228,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:57:36.8532873\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60229,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:57:57.1880754\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60230,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:58:58.2335302\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60231,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:59:39.0374387\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60232,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T13:59:59.3898961\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60233,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:00:39.9885161\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60234,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:01:20.7787973\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60235,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:02:21.629545\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60236,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:03:22.6865704\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60237,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:04:03.2855436\",\n" +
            "      \"value\": 35,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60238,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:05:24.5905913\",\n" +
            "      \"value\": 36,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60239,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:05:45.1385919\",\n" +
            "      \"value\": 37,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60240,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:07:46.7357838\",\n" +
            "      \"value\": 38,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60241,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:11:09.5955986\",\n" +
            "      \"value\": 39,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60242,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:14:52.4413784\",\n" +
            "      \"value\": 38,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60243,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:15:12.7898206\",\n" +
            "      \"value\": 37,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60244,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:15:33.3373645\",\n" +
            "      \"value\": 35,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60245,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:15:53.658113\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60246,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:16:13.9922513\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60247,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:17:35.2899231\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60248,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:18:36.3436364\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60249,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:18:56.6769865\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60250,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:19:57.626683\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60251,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:20:38.2067554\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60252,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:20:58.5526392\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60253,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:22:19.6405295\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60254,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:23:00.4272208\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60255,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:25:22.2752224\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60256,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:27:03.609654\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60257,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:28:05.4453558\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60258,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:28:46.0310623\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60259,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:29:26.6167099\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60260,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:30:07.2064846\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60261,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:30:27.9454829\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60262,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:31:49.2413853\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60263,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:33:50.8270372\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60264,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:35:32.1712535\",\n" +
            "      \"value\": 35,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60265,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:36:33.2225793\",\n" +
            "      \"value\": 36,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60266,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:36:53.7568251\",\n" +
            "      \"value\": 37,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60267,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:38:14.8700197\",\n" +
            "      \"value\": 38,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60268,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:40:36.6996328\",\n" +
            "      \"value\": 39,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60269,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:43:39.0419092\",\n" +
            "      \"value\": 38,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60270,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:43:59.390159\",\n" +
            "      \"value\": 37,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60271,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:44:19.7374035\",\n" +
            "      \"value\": 35,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60272,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:44:40.0701687\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60273,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:45:41.1205073\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60274,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:46:41.9467014\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60282,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:47:43.7730741\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60283,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:48:04.1073045\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60284,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:49:25.2012607\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60285,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:49:45.5247647\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60286,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:50:05.8482081\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60287,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:50:26.1908045\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60288,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:50:46.5376276\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60289,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:51:47.3748689\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60290,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:52:27.9512018\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60291,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:54:29.5363789\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60292,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:56:10.8617993\",\n" +
            "      \"value\": 33,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60293,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:57:31.9581601\",\n" +
            "      \"value\": 34,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60294,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:58:12.5498282\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60295,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:58:53.1484965\",\n" +
            "      \"value\": 30,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60296,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:59:13.4962696\",\n" +
            "      \"value\": 31,\n" +
            "      \"sensor\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"sensorReadingId\": 60297,\n" +
            "      \"sensorId\": 2,\n" +
            "      \"dateTime\": \"2018-11-26T14:59:33.8406899\",\n" +
            "      \"value\": 32,\n" +
            "      \"sensor\": null\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}