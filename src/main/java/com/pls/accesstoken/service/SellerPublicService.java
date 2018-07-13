package com.pls.accesstoken.service;

import com.pls.accesstoken.dao.SellerPublicDao;
import com.pls.accesstoken.model.SellerPublicNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取公众号信息service
 * Created by 81046 on 2018-07-12
 */
@Service
public class SellerPublicService {

    @Autowired
    private SellerPublicDao sellerPublicDao;

    public List<SellerPublicNumber> getAllList(){
        return sellerPublicDao.getAllList();
    }
}
