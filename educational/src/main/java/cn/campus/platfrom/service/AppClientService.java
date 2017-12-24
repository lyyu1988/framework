package cn.campus.platfrom.service;

import cn.campus.platfrom.entity.AppClient;

public interface AppClientService {

    AppClient getById(String id);

    AppClient insert(AppClient appClient);

}
