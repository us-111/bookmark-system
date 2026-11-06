package com.study.bookmark.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.bookmark.annottation.Log;
import com.study.bookmark.common.Result;
import com.study.bookmark.pojo.Category;
import com.study.bookmark.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Log("新增分类")
    @PostMapping
    public Result<Void> add(@Valid @RequestBody Category category){
        categoryService.save(category);
        return Result.success();
    }

    @GetMapping("{id}")
    public Result<Category> getById(@PathVariable Long id){
        return Result.success(categoryService.getMyCategoryById(id));
    }

    @Log("更新分类")
    @PutMapping("{id}")
    public Result<Void> update(@PathVariable Long id,@Valid @RequestBody Category category){
        category.setId(id);
        categoryService.updateById(category);
        return Result.success();
    }

    @Log("删除分类")
    @DeleteMapping("{id}")
    public Result<Void> delete(@PathVariable Long id){
        categoryService.removeById(id);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<Page<Category>> pageResult(@RequestParam(defaultValue = "1") Long pageNum,
                                             @RequestParam(defaultValue = "10") Long pageSize,
                                             @RequestParam(required = false) String name){
        return Result.success(categoryService.categoryPage(pageNum,pageSize,name));
    }

    @GetMapping("list")
    public Result<List<Category>> getAll(){
        return Result.success(categoryService.getCategoryList());
    }
}
