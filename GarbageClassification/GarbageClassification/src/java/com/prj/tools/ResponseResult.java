package com.prj.tools;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xxx
 * @Date: 2019/10/23 12:09
 * @Description:  响应实体
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    private int code;
    private long count;
    private String message;
    private boolean success;
    private List<T> data=new ArrayList<T>();
    public ResponseResult(int code, String message, boolean success){
      this.code=code;
      this.message=message;
      this.success=success;
    }



}