package cn.tom.service;

import cn.tom.entity.LargeFile;

public interface LargeFileService {
    public void add(String id, String filename, byte[] content);
    public String getString(String id);
    public LargeFile findOne(String id);
    public void delete(String id);
}
