package com.zenq.cloud507b.service;

public interface CosService {
    /**
     * 上传图片
     */
    void upload(String imgUrl, String imgname);
    /**
     * 下载图片
     */
    void download(String imgUrl);
    /**
     * 删除图片
     */
    void delet(String imgUrl);
}
