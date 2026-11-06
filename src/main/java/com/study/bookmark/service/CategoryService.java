package com.study.bookmark.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.IService;
import com.study.bookmark.pojo.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    Page<Category> categoryPage(Long pageNum,Long pageSize,String name);


    Category getMyCategoryById(Long id);

    List<Category> getCategoryList();
}
