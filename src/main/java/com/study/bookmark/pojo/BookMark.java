package com.study.bookmark.pojo;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("bookmark")
public class BookMark {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NotNull(message = "categoryId不能为空")
    private Long categoryId;
    private Long userId;
    @NotBlank(message = "title不能为空或全是空格")
    private String title;
    @NotBlank(message = "url不能为空或全是空格")
    private String url;
    private String description;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    private Integer deleted;
}
