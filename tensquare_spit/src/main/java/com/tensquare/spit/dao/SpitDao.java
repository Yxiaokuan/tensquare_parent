package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author xiaokuan
 * 2020/11/25 19:03 星期三
 */
public interface SpitDao extends MongoRepository<Spit, String>{

    public Page<Spit> findSpitsByParentid(String parentId, Pageable pageable);
//    public void
}
