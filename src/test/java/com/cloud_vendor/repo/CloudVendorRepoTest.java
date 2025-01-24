package com.cloud_vendor.repo;

import com.cloud_vendor.model.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // lets SB know to use embedded DB
public class CloudVendorRepoTest {

    @Autowired
    private CloudVendorRepo cloudVendorRepo;
    private CloudVendor cloudVendor;

    @BeforeEach
    void setup() {
        cloudVendor = new CloudVendor("1", "Amazon", "USA", "xxx");
        cloudVendorRepo.save(cloudVendor);
    }

    @AfterEach
    void tearDown() {
        cloudVendorRepo.deleteAll();
    }

    @Test
    public void testFindByVendorNameFound() {
        List<CloudVendor> cloudVendorList = cloudVendorRepo.findByVendorName("Amazon");
        assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo("1");
        assertThat(cloudVendorList.get(0).getVendorName()).isEqualTo("Amazon");
        assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo("USA");
    }

    @Test
    public void testFindByVendorNameNotFound() {
        List<CloudVendor> cloudVendorList = cloudVendorRepo.findByVendorName("GCP");
        assertThat(cloudVendorList.isEmpty()).isTrue();
    }
}
