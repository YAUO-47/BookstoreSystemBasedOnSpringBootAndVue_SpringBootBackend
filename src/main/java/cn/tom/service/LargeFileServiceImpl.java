package cn.tom.service;

import cn.tom.dao.LargeFileDao;
import cn.tom.entity.LargeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LargeFileServiceImpl implements LargeFileService {
    @Autowired(required = false)
    private LargeFileDao largeFileDao;
    @Override
    public void add(String id, String filename, byte[] content) {
        LargeFile lf = new LargeFile();
        lf.setId(id);
        lf.setFilename(filename);
        lf.setContent(content);        //向上转型为Object
        largeFileDao.add(lf);
    }

    @Override
    public String getString(String id) {
        return (String)largeFileDao.findOne(id).getContent();
    }

    @Override
    public LargeFile findOne(String id) {
        return largeFileDao.findOne(id);
    }

    @Override
    public void delete(String id) {
        largeFileDao.delete(id);
    }
}
