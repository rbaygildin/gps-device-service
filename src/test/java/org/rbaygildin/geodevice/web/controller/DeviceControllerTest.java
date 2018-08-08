package org.rbaygildin.geodevice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.rbaygildin.geodevice.domain.Device;
import org.rbaygildin.geodevice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(
        scripts = "classpath:sqlScripts/devices.sql",
        config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)
)
public class DeviceControllerTest extends BaseControllerTest{

    private static final String NEW_DEVICE_JSON = "classpath:json/newDevice.json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeviceService service;

    @Test
    public void testSaveDevice() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(
                post("/device")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loadToString(NEW_DEVICE_JSON))

        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();
        Device device = objectMapper.readValue(response.getContentAsByteArray(), Device.class);
        assertTrue(service.existsById(device.getId()));
    }

    @Test
    public void testDeleteDevice() throws Exception{
        assertTrue(service.existsById(1));
        mockMvc.perform(
                delete("/device/1")
                        .accept(MediaType.APPLICATION_JSON)

        )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(service.existsById(1));
    }
}
