package com.tensquare.spit.service;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.operation.UpdateOperation;
import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.PartialUpdate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @author xiaokuan
 * 2020/11/25 19:06 星期三
 */
@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 查询全部记录
     *
     * @return
     */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /**
     * 根据主键查询实体
     *
     * @return
     */
    public Spit findById(String id) {
        Spit spit = spitDao.findById(id).get();
        return spit;
    }

    /*** 增加
     *  @param spit
     */
    public void add(Spit spit) {
        spit.set_id(idWorker.nextId() + ""); //主键值
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if(!"".equals(spit.getParentid())&&spit.getParentid()!=null){
            mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(spit.getParentid())), new Update().inc("comment",1),"spit");
        }
        spitDao.save(spit);
    }

    /*** 修改 * @param spit */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /*** 删除
     * @param id
     */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    /*** 根据上级ID查询吐槽列表（分页）
     * @param parentid
     * @param page
     * @param size
     * @return */
    public Page<Spit> findByParentid(String parentid, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return spitDao.findSpitsByParentid(parentid, pageRequest);
    }

    /**
     * 点赞
     *
     * @param id
     */
    public void updateThumbup(String id) {
        //方式一   效率低
//        Spit spit = spitDao.findById(id).get();
//        spit.setThumbup((spit.getThumbup() == null ? 0 : spit.getThumbup()) + 1);
//        spitDao.save(spit);
        //方式二  操作命令  db.spit.update({"_id":1},{$inc:{"thumbup":NumberInt(1)}})
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);

        mongoTemplate.updateFirst(query, update, "spit");
    }
}
