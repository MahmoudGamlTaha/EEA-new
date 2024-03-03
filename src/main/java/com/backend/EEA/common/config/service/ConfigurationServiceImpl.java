package com.backend.EEA.common.config.service;

import com.backend.EEA.common.config.domain.Configuration;
import com.backend.EEA.common.config.repository.ConfigurationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigurationServiceImpl implements ConfigurationService {

    private final ConfigurationRepository configurationRepo;

   // @Override
    public String getAuditApiUrl() {
        return configurationRepo.findById("SAVE_AUDIT_URL").map(Configuration::getValue).orElse(null);
    }

   // @Override
    public String getValue(String key) {
        return configurationRepo.findById(key).map(Configuration::getValue).orElse(null);
    }
}
