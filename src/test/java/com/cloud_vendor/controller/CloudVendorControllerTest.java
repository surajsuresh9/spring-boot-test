package com.cloud_vendor.controller;

import com.cloud_vendor.model.CloudVendor;
import com.cloud_vendor.service.impl.CloudVendorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CloudVendorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CloudVendorServiceImpl cloudVendorService;

    private CloudVendorController cloudVendorController;
    private CloudVendor cloudVendor1;
    private CloudVendor cloudVendor2;
    private List<CloudVendor> cloudVendors = new ArrayList<>();

    @BeforeEach
    public void setup() {
        cloudVendor1 = new CloudVendor("1", "Amazon", "USA", "xxxx");
        cloudVendor2 = new CloudVendor("2", "GCP", "UK", "yyyy");
        cloudVendors.add(cloudVendor1);
        cloudVendors.add(cloudVendor2);
    }

    @Test
    void testGetCloudVendorDetails() throws Exception {
        when(cloudVendorService.getCloudVendor("1")).thenReturn(cloudVendor1);
        this.mockMvc.perform(get("/cloudvendor/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllCloudVendorDetails() throws Exception {
        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendors);
        this.mockMvc.perform(get("/cloudvendor/"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testCreateCloudVendorDetails() throws Exception {
        when(cloudVendorService.createCloudVendor(cloudVendor1)).thenReturn("Success");
        this.mockMvc.perform(post("/cloudvendor/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cloudVendor1))
                )
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateCloudVendorDetails() throws Exception {
        when(cloudVendorService.updateCloudVendor(cloudVendor2)).thenReturn("Success");
        this.mockMvc.perform(put("/cloudvendor/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cloudVendor2))
                )
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteCloudVendorDetails() throws Exception {
        when(cloudVendorService.deleteCloudVendor("1")).thenReturn("Success");
        this.mockMvc.perform(delete("/cloudvendor/1"))
                .andDo(print()).andExpect(status().isOk());
    }
}
