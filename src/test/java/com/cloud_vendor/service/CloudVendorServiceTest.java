package com.cloud_vendor.service;

import com.cloud_vendor.model.CloudVendor;
import com.cloud_vendor.repo.CloudVendorRepo;
import com.cloud_vendor.service.impl.CloudVendorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CloudVendorServiceTest {

    // mocking repo layer
    // all results should be mocked
    // no DB layer (its being mocked)
    @Mock
    private CloudVendorRepo cloudVendorRepo;
    private CloudVendorService cloudVendorService;
    private AutoCloseable autoCloseable;
    private CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        // release all resources once class finished execution
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepo);
        cloudVendor = new CloudVendor("1", "Amazon", "USA", "xxx");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class);
        when(cloudVendorRepo.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Success");
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class);
        when(cloudVendorRepo.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.updateCloudVendor(cloudVendor)).isEqualTo("Success");
    }

    @Test
    void testDeleteCloudVendor() {
        mock(CloudVendor.class, Mockito.CALLS_REAL_METHODS);
        mock(CloudVendorRepo.class);
        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepo).deleteById(any());
        assertThat(cloudVendorService.deleteCloudVendor("1")).isEqualTo("Success");

    }

    @Test
    void testGetCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class);
        when(cloudVendorRepo.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorService.getCloudVendor("1").getVendorName()).isEqualTo("Amazon");
    }

    @Test
    void testGetByVendorName() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class);
        when(cloudVendorRepo.findByVendorName("Amazon")).thenReturn(Collections.singletonList(cloudVendor));
        assertThat(cloudVendorService.getByVendorName("Amazon").get(0).getVendorName()).isEqualTo("Amazon");
    }

    @Test
    void getAllCloudVendors() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class);
        when(cloudVendorRepo.findAll()).thenReturn(new ArrayList<>(Collections.singletonList(cloudVendor)));
        assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorName()).isEqualTo("Amazon");
    }
}
