package com.study.bookmark.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.bookmark.annottation.Log;
import com.study.bookmark.common.Result;
import com.study.bookmark.pojo.BookMark;
import com.study.bookmark.service.BookMarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
public class BookMarkController {
    private final BookMarkService bookMarkService;

    @Log("新增书签")
    @PostMapping
    public Result<Void> add(@Valid @RequestBody BookMark bookMark){
        bookMarkService.save(bookMark);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<BookMark> getById(@PathVariable Long id){
        return Result.success(bookMarkService.getBookMarkById(id));
    }

    @Log("更新书签")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id,@Valid @RequestBody BookMark bookMark){
        bookMark.setId(id);
        bookMarkService.updateById(bookMark);
        return Result.success();
    }

    @Log("删除书签")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id){
        bookMarkService.removeById(id);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<Page<BookMark>> pageResult(@RequestParam(defaultValue = "1") Long pageNum,
                                             @RequestParam(defaultValue = "10") Long pageSize,
                                             @RequestParam(required = false) Long categoryId,
                                             @RequestParam(required = false) String title){
        return Result.success(bookMarkService.bookPage(pageNum,pageSize,categoryId,title));
    }

    @GetMapping("/list")
    public Result<List<BookMark>> getByCategoryId(@RequestParam Long categoryId){
        return Result.success(bookMarkService.getByCategoryId(categoryId));
    }
}
