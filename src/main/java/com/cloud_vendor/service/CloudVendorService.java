package com.cloud_vendor.service;

import com.cloud_vendor.model.CloudVendor;

import java.util.List;

public interface CloudVendorService {


    public String createCloudVendor(CloudVendor cloudVendor);

    public String updateCloudVendor(CloudVendor cloudVendor);

    public String deleteCloudVendor(String cloudVendorId);

    public CloudVendor getCloudVendor(String cloudVendorId);

    public List<CloudVendor> getAllCloudVendors();

    public List<CloudVendor> getByVendorName(String vendorName);


}
