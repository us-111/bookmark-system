package com.study.bookmark.pojo;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@TableName("sys_log")
public class SysLog {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NotNull(message = "userId不能为空")
    private Long userId;
    private String operation;
    private String method;
    private String params;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
