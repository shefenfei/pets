package com.fenfei.pets.elasticsearch;

import com.fenfei.pets.elasticsearch.models.AccountInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * 账户信息ES
 */
@Component("accountInfoElasticRepository")
public interface AccountInfoElasticRepository extends ElasticsearchRepository<AccountInfo , String> {
    AccountInfo findAccountInfoByAccountName(String accountName);
}
