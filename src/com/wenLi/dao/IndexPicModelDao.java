package com.wenLi.dao;

import java.util.List;

import com.wenLi.entity.IndexPicModel;
import com.wenLi.util.entity.Page;

public interface IndexPicModelDao {

	Page<IndexPicModel> getIndexPicList(int pc, int ps);

	IndexPicModel findIndexPicById(String id);

	boolean saveOrUpdateIndexPic(IndexPicModel indexPic);

	boolean forbiddenIndexPic(String id);

	boolean usingIndexPic(String id);

	Integer getMaxOrder();

	List<IndexPicModel> getUsingIndexPicList();

}
