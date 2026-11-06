package com.study.bookmark.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.study.bookmark.exception.BusinessException;
import com.study.bookmark.mapper.BookMarkMapper;
import com.study.bookmark.pojo.BookMark;
import com.study.bookmark.utils.UserContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

@Service
public class BookMarkServiceImpl extends ServiceImpl<BookMarkMapper, BookMark> implements BookMarkService{


    @Override
    public Page<BookMark> bookPage(Long pageNum, Long pageSize, Long categoryId, String title) {

        Long userId = UserContext.getUserId();

        Page<BookMark> bookMarkPage = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<BookMark> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BookMark::getUserId,userId);

        wrapper.eq(categoryId!=null,BookMark::getCategoryId,categoryId)
                .like(StringUtils.hasText(title),BookMark::getTitle,title);

        return this.page(bookMarkPage,wrapper);
    }

    @Override
    public List<BookMark> getByCategoryId(Long categoryId) {

        LambdaQueryWrapper<BookMark> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BookMark::getCategoryId,categoryId);

        return list(wrapper);
    }

    @Override
    public BookMark getBookMarkById(Long id) {

        LambdaQueryWrapper<BookMark> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BookMark::getId,id).eq(BookMark::getUserId,UserContext.getUserId());

        return this.getOne(wrapper);
    }

    @Override
    public boolean removeById(Serializable id) {

        LambdaQueryWrapper<BookMark> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BookMark::getId,id).eq(BookMark::getUserId,UserContext.getUserId());

        boolean isSuccess = this.remove(wrapper);
        if (!isSuccess) throw new BusinessException("删除失败，无权限或是数据不存在");

        return true;
    }

    @Override
    public boolean save(BookMark bookMark) {

        bookMark.setUserId(UserContext.getUserId());

        return super.save(bookMark);
    }

    @Override
    public boolean updateById(BookMark bookMark) {

        if (bookMark.getDeleted() == null) bookMark.setDeleted(0);
        bookMark.setUserId(null);

        LambdaQueryWrapper<BookMark> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BookMark::getId,bookMark.getId()).eq(BookMark::getUserId,UserContext.getUserId());

        boolean isSuccess = this.update(bookMark, wrapper);
        if (!isSuccess) throw new BusinessException("修改失败，无权限或是数据不存在");

        return true;
    }
}
