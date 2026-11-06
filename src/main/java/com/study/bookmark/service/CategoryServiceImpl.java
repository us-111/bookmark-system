package com.study.bookmark.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.study.bookmark.exception.BusinessException;
import com.study.bookmark.mapper.CategoryMapper;
import com.study.bookmark.pojo.Category;
import com.study.bookmark.utils.UserContext;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public Page<Category> categoryPage(Long pageNum, Long pageSize, String name) {

        Long userId = UserContext.getUserId();

        Page<Category> categoryPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Category::getUserId, userId);

        wrapper.like(name != null, Category::getName, name);
        wrapper.orderByAsc(Category::getSort);

        return this.page(categoryPage, wrapper);
    }


    @Override
    public Category getMyCategoryById(Long id) {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Category::getId, id).eq(Category::getUserId, UserContext.getUserId());

        return this.getOne(wrapper);
    }

    @Override
    public List<Category> getCategoryList() {

        Long userId = UserContext.getUserId();

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getUserId,userId);

        return this.list(wrapper);
    }

    @Override
    public boolean removeById(Serializable id) {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Category::getId, id).eq(Category::getUserId, UserContext.getUserId());

        boolean isSuccess = this.remove(wrapper);
        if (!isSuccess) throw new BusinessException("删除失败,无权限或是数据不存在");

        return true;
    }

    @Override
    public boolean save(Category category) {

        category.setUserId(UserContext.getUserId());

        return super.save(category);
    }

    @Override
    public boolean updateById(Category category) {

        if (category.getDeleted() == null) category.setDeleted(0);
        category.setUserId(null);

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getId, category.getId()).eq(Category::getUserId, UserContext.getUserId());

        boolean isSuccess = this.update(category, wrapper);
        if (!isSuccess) throw new BusinessException("修改失败，无权限或数据不存在");

        return true;
    }
}
