package com.lbl.passbook.controller;

import com.alibaba.fastjson.JSON;
import com.lbl.passbook.service.IMerchantsService;
import com.lbl.passbook.vo.CreateMerchantsRequest;
import com.lbl.passbook.vo.PassTemplate;
import com.lbl.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchants")
@Slf4j
public class MerchantsController {

    private final IMerchantsService merchantsService;

    @Autowired
    public MerchantsController(IMerchantsService merchantsService) {
        this.merchantsService = merchantsService;
    }

    /**
     * 创建商户
     * @param merchantsRequest
     * @return
     */
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest merchantsRequest) {
        log.info("CreateMerchants: {}", JSON.toJSONString(merchantsRequest));

        return merchantsService.createMerchants(merchantsRequest);
    }

    /**
     * 根据id查询商户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id) {

        log.info("BuildMerchantsInfo: {}", id);
        return merchantsService.buildMerchantsInfoById(id);
    }

    /**
     * 商户投放优惠券
     * @param passTemplate
     * @return
     */
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate) {

        log.info("DropPassTemplate: {}", passTemplate);
        return merchantsService.dropPassTemplate(passTemplate);
    }

}
