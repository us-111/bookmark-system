package com.study.bookmark.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.IService;
import com.study.bookmark.pojo.BookMark;
import com.study.bookmark.pojo.Category;

import java.util.List;

public interface BookMarkService extends IService<BookMark> {
   Page<BookMark> bookPage(Long pageNum,Long pageSize,Long categoryId,String title);

   List<BookMark> getByCategoryId(Long categoryId);

   BookMark getBookMarkById(Long id);
}
