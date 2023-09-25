package com.huo.huoapiadmin.service.impl.inner;

import com.huo.huoapiadmin.service.InterfaceInfoService;
import com.huo.huoapicommon.model.entity.InterfaceInfo;
import com.huo.huoapicommon.service.InnerInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
* @author huo
*/
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Override
    public InterfaceInfo getInterfaceInfo(String path, String method) {
        return interfaceInfoService.query()
                .eq("url", path)
                .eq("method", method)
                .one();
    }
}




