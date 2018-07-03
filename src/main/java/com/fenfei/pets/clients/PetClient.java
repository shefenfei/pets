package com.fenfei.pets.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Component
@FeignClient(name = "PetClient" , url = "${pet.url.host}")
public interface PetClient {

    /**
     * 获取会员标签
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/member/label/labelsByMemberId?memberId={memberId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    String labelByMemberId(@PathVariable("memberId") String memberId);


    /**
     * 获取会员积分
     * @param memberId
     * @return
     */
    /**
     * 获取会员积分
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/member-level/{memberId}" ,produces = {"application/json"} , method = RequestMethod.GET)
    String memberLevelById(@PathVariable("memberId") String memberId);
}
