package com.wenLi.service;

import java.util.List;

import com.wenLi.entity.IndexPicModel;
import com.wenLi.util.entity.Page;

public interface IndexPicModelService {

	Page<IndexPicModel> getIndexPicList(int i, int j);

	IndexPicModel findIndexPicById(String id);

	boolean saveOrUpdateIndexPic(IndexPicModel indexPic);

	boolean forbiddenIndexPic(String id);

	boolean usingIndexPic(String id);

	Integer getMaxOrder();

	List<IndexPicModel> getUsingIndexPicList();

}
