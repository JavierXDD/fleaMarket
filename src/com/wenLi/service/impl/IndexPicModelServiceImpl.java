package com.wenLi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenLi.dao.IndexPicModelDao;
import com.wenLi.entity.IndexPicModel;
import com.wenLi.service.IndexPicModelService;
import com.wenLi.util.entity.Page;

@Service
@Transactional
public class IndexPicModelServiceImpl implements IndexPicModelService{
	@Resource
	private IndexPicModelDao  indexPicModelDao;

	@Override
	public Page<IndexPicModel> getIndexPicList(int pc, int ps) {
		Page<IndexPicModel>	indexPicList = 	indexPicModelDao.getIndexPicList(pc,ps);
		return indexPicList;
	}

	@Override
	public IndexPicModel findIndexPicById(String id) {
		IndexPicModel indexPic = indexPicModelDao.findIndexPicById(id);
		return indexPic;
	}

	@Override
	public boolean saveOrUpdateIndexPic(IndexPicModel indexPic) {
		
		return indexPicModelDao.saveOrUpdateIndexPic(indexPic);
	}

	@Override
	public boolean forbiddenIndexPic(String id) {
		
		return indexPicModelDao.forbiddenIndexPic(id);
	}

	@Override
	public boolean usingIndexPic(String id) {
		return indexPicModelDao.usingIndexPic(id);
	}

	@Override
	public Integer getMaxOrder() {
		
		return indexPicModelDao.getMaxOrder();
	}

	@Override
	public List<IndexPicModel> getUsingIndexPicList() {
	
		return indexPicModelDao.getUsingIndexPicList();
	}
}
