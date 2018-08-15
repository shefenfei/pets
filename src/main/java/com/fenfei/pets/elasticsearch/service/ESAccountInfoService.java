package com.fenfei.pets.elasticsearch.service;

import com.fenfei.pets.elasticsearch.models.AccountInfo;

public interface ESAccountInfoService {

    AccountInfo queryAccountInfoName(String accountName);

    AccountInfo saveAccountInfo(AccountInfo accountInfo);
}
