package com.lbl.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.lbl.passbook.constant.Constants;
import com.lbl.passbook.constant.ErrorCode;
import com.lbl.passbook.dao.MerchantsDao;
import com.lbl.passbook.entity.Merchants;
import com.lbl.passbook.service.IMerchantsService;
import com.lbl.passbook.vo.CreateMerchantsRequest;
import com.lbl.passbook.vo.CreateMerchantsResponse;
import com.lbl.passbook.vo.PassTemplate;
import com.lbl.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MerchantsService implements IMerchantsService {

    private final MerchantsDao merchantsDao;

    /** kafka 客户端 */
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MerchantsService(KafkaTemplate<String, String> kafkaTemplate, MerchantsDao merchantsDao) {
        this.kafkaTemplate = kafkaTemplate;
        this.merchantsDao = merchantsDao;
    }

    @Transactional
    @Override
    public Response createMerchants(CreateMerchantsRequest request) {

        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();
        Response response = new Response();
        ErrorCode errorCode = request.validate(merchantsDao);
        if(errorCode != ErrorCode.SUCCESS) {
            merchantsResponse.setId(-1);
            response.setErrCode(errorCode.getCode());
            response.setErrMsg(errorCode.getDesc());
        } else {
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }
        response.setData(merchantsResponse);
        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();

        Merchants merchants = merchantsDao.findById(id);
        if(null == merchants) {
            response.setErrCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }

        response.setData(merchants);

        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {

        Response response = new Response();

        ErrorCode errorCode = template.validate(merchantsDao);

        if (errorCode != ErrorCode.SUCCESS) {
            response.setErrCode(errorCode.getCode());
            response.setErrMsg(errorCode.getDesc());
        } else {
            String passTemplate = JSON.toJSONString(template);
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplate
            );

            log.info("DropPassTemplate: {}", passTemplate);

        }
        return response;
    }
}
