package org.rbaygildin.geodevice.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.rbaygildin.geodevice.domain.GpsData;
import org.rbaygildin.geodevice.service.GpsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.rbaygildin.geodevice.matchers.CommonMatchers.anyMatches;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(
        scripts = "classpath:sqlScripts/devices.sql",
        config = @SqlConfig(
                transactionMode = SqlConfig.TransactionMode.ISOLATED
        )
)
public class GpsDataControllerTest extends BaseControllerTest {

    private static final String NEW_GPS_DATA_JSON = "classpath:json/newPosition.json";
    private static final Integer DEVICE_ID = 1;
    private static final OffsetDateTime TIME_START = OffsetDateTime.parse("2018-07-07T13:20:14+03:00");
    private static final OffsetDateTime TIME_END = OffsetDateTime.parse("2018-07-07T16:20:14+03:00");

    @Autowired
    private GpsDataService service;

    @Test
    public void testSavePosition() throws Exception {
        mockMvc.perform(
                post("/gpsdata")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loadToString(NEW_GPS_DATA_JSON))

        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        List<GpsData> positions = service.findDevicePositions(DEVICE_ID, TIME_START, TIME_END);
        assertThat(positions, anyMatches(d -> {
            if (Double.compare(d.getLongitude(), 50.2) != 0)
                return false;
            if (Double.compare(d.getLatitude(), 45.5) != 0)
                return false;
            if (Double.compare(d.getRadius(), 5.0) != 0)
                return false;
            return d.getTime().isEqual(OffsetDateTime.parse("2018-07-07T15:20:14+03:00"));
        }));
    }

    @Test
    public void testFindPositions() throws Exception {
        mockMvc.perform(
                post("/gpsdata")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loadToString(NEW_GPS_DATA_JSON))

        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        MockHttpServletResponse response = mockMvc.perform(
                get("/gpsdata")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loadToString(NEW_GPS_DATA_JSON))
                        .param("id", DEVICE_ID.toString())
                        .param("timestart", TIME_START.toString())
                        .param("timeend", TIME_END.toString())

        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();
        List<GpsData> gpsData = objectMapper.readValue(response.getContentAsByteArray(), new TypeReference<List<GpsData>>() {

        });
        assertThat(gpsData, anyMatches(d -> {
            if (Double.compare(d.getRadius(), 5.0) != 0)
                return false;
            if (Double.compare(d.getLatitude(), 45.5) != 0)
                return false;
            if (Double.compare(d.getLongitude(), 50.2) != 0)
                return false;
            return d.getTime().isEqual(OffsetDateTime.parse("2018-07-07T15:20:14+03:00"));
        }));
    }
}
