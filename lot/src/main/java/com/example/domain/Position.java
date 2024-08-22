package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("car_position")
public class Position {
     private Integer id;
     private Integer x;
     private Integer y;
     private Integer z;
}
