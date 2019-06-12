package com.tensquare.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class LabelService {

	@Autowired
	private LabelDao labelDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Label> findAll() {
		return labelDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Label> findSearch(Map whereMap, int page, int size) {
		Specification<Label> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return labelDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Label> findSearch(Map whereMap) {
		Specification<Label> specification = createSpecification(whereMap);
		return labelDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Label findById(String id) {
		return labelDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param label
	 */
	public void add(Label label) {
		label.setId( idWorker.nextId()+"" );
		labelDao.save(label);
	}

	/**
	 * 修改
	 * @param label
	 */
	public void update(Label label) {
		labelDao.save(label);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		labelDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Label> createSpecification(Map searchMap) {

		return new Specification<Label>() {

			@Override
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 标签ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 标签名称
                if (searchMap.get("labelname")!=null && !"".equals(searchMap.get("labelname"))) {
                	predicateList.add(cb.like(root.get("labelname").as(String.class), "%"+(String)searchMap.get("labelname")+"%"));
                }
                // 状态
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                	predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
                // 是否推荐
                if (searchMap.get("recommend")!=null && !"".equals(searchMap.get("recommend"))) {
                	predicateList.add(cb.like(root.get("recommend").as(String.class), "%"+(String)searchMap.get("recommend")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
