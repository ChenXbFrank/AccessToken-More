package com.pls.accesstoken.dao;

import com.pls.accesstoken.model.SellerPublicNumber;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 81046 on 2018-07-12
 */
public interface SellerPublicDao {

    @Select("select * from TB_SELLER_PUBLICNUMBER")
    List<SellerPublicNumber> getAllList();

}
