package com.fenfei.pets.elasticsearch.service.impl;

import com.fenfei.pets.elasticsearch.AccountInfoElasticRepository;
import com.fenfei.pets.elasticsearch.models.AccountInfo;
import com.fenfei.pets.elasticsearch.service.ESAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("esAccountInfoServiceImpl")
public class ESAccountInfoServiceImpl implements ESAccountInfoService {

    @Autowired
    private AccountInfoElasticRepository accountInfoElasticRepository;

    @Override
    public AccountInfo queryAccountInfoName(String accountName) {
        return accountInfoElasticRepository.findAccountInfoByAccountName(accountName);
    }

    @Override
    public AccountInfo saveAccountInfo(AccountInfo accountInfo) {
        return accountInfoElasticRepository.save(accountInfo);
    }
}
