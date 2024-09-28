package com.arindam.tms.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AllTaskDto extends BaseDto{

    private List<TaskDto> tasks = new ArrayList<>();

}
