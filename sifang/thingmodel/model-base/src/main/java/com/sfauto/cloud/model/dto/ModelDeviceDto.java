package com.sfauto.cloud.model.dto;

import com.sfauto.cloud.model.entity.ModelDevice;
import com.sfauto.cloud.model.entity.ModelEvent;
import com.sfauto.cloud.model.entity.ModelProperty;
import com.sfauto.cloud.model.entity.ModelService;
import lombok.Data;

import java.util.List;

@Data
public class ModelDeviceDto {
    private ModelDevice modelDevice;
    private List<ModelProperty> listProperty;
    private List<ModelService> listService;
    private List<ModelEvent> listEvent;
}
