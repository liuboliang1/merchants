package com.lbl.passbook.dao;

import com.lbl.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {

    /**
     * 根据商家id查询
     * @param id  商家id
     * @return {@link Merchants}
     */
    Merchants findById(Integer id);

    /**
     * 根据商家名称查询
     * @param name  商家名称
     * @return  {@link Merchants}
     */
    Merchants findByName(String name);
}
