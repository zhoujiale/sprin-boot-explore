package com.zjl.spring_boot_quartz.dao.impl;

import com.zjl.spring_boot_quartz.dao.SelfJobDao;
import com.zjl.spring_boot_quartz.dao.repository.SelfJobRepository;
import com.zjl.spring_boot_quartz.model.SelfJobPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zhou
 * @version 1.0
 * @className SelfJobDaoImpl
 * @description
 * @date 2021/03/24 15:42
 **/
@Repository
public class SelfJobDaoImpl implements SelfJobDao {

    @Autowired
    private SelfJobRepository selfJobRepository;

    @Override
    public SelfJobPO add(SelfJobPO selfJobPO) {
        return selfJobRepository.save(selfJobPO);
    }

    @Override
    public void modify(SelfJobPO selfJobPO) {
        selfJobRepository.save(selfJobPO);
    }

    @Override
    public void delete(Long jobId) {
        selfJobRepository.deleteById(jobId);
    }

    @Override
    public Page<SelfJobPO> getPage(Integer pageNum, Integer pageSize, String name) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC,"updateDate"));
        Specification<SelfJobPO> specification = (Specification<SelfJobPO>)(root,criteriaQuery,criteriaBuilder)->{
            List<Predicate> andList = new ArrayList<>();
            if (StringUtils.isNotBlank(name)){
                andList.add(criteriaBuilder.like(root.get("jobName"),"%"+name+"%"));
            }
            return criteriaBuilder.and(andList.toArray(new Predicate[andList.size()]));
        };
        return selfJobRepository.findAll(specification,pageRequest);
    }

    @Override
    public SelfJobPO getById(Long jobId) {
        Optional<SelfJobPO> byId = selfJobRepository.findById(jobId);
        return byId.orElse(null);
    }

    @Override
    public List<SelfJobPO> getAllJob() {
        return selfJobRepository.findAll();
    }
}
