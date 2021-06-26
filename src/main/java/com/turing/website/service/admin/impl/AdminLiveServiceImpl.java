package com.turing.website.service.admin.impl;


import com.turing.website.dao.LiveDao;
import com.turing.website.dao.LivePhotoDao;
import com.turing.website.dto.LiveDTO;
import com.turing.website.entity.Live;
import com.turing.website.entity.LivePhoto;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminLiveService;
import com.turing.website.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author CHEN
 * @date 2020/3/2 21:01
 */
@Service
@CacheConfig(cacheNames = "live")
public class AdminLiveServiceImpl implements AdminLiveService {

    @Autowired
    LiveDao liveDao;
    @Autowired
    FileUploadUtil fileUploadUtil;
    @Autowired
    LivePhotoDao livePhotoDao;

    @Transactional(rollbackFor = {})
    @Override
    public void addOrUpdateLive(LiveDTO liveDTO, MultipartFile[] livePhotos) {
        //获取文件上传名字
        String[] livePhotosUploadNames = fileUploadUtil.getFileNewNames(livePhotos);
        //生活图片的Set集合,用于绑定live实体
        Set<LivePhoto> livePhotoSet = new HashSet<>();

        Long liveDTOId = liveDTO.getLiveId();
        if(liveDTOId == null){
            //创建
            Live live = new Live();
            //将图片绑定Live实体且存入Set集合中
            for (String livePhotosUploadName : livePhotosUploadNames) {
                LivePhoto livePhoto = new LivePhoto();
                livePhoto.setLive(live);
                livePhoto.setLivePhotoLoc(livePhotosUploadName);
                livePhotoSet.add(livePhoto);
            }

            live.setLiveContent(liveDTO.getLiveContent());
            live.setLiveName(liveDTO.getLiveName());
            live.setLiveTime(liveDTO.getLiveTime());
            live.setLivePhotos(livePhotoSet);

            //存入数据库
            liveDao.save(live);
            //文件上传到磁盘中
            fileUploadUtil.uploadFiles(livePhotos, livePhotosUploadNames);

        }else{
            //更新
            Live dbLive;
            try {
                dbLive = liveDao.findById(liveDTOId).get();
            }catch(Exception ex){
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.LIVE_NOT_FOUND);
            }
            //获取数据库中的所有图片
            Set<LivePhoto> dbLivePhotosSet = dbLive.getLivePhotos();
            //解除团队活动图片与对应团队活动的依赖关系,否则无法删除图片
            dbLive.setLivePhotos(null);
            dbLivePhotosSet.forEach(dbLivePhoto->{
                Long dbLivePhotoId = dbLivePhoto.getLivePhotoId();
                //删除数据库中的图片
                livePhotoDao.deleteById(dbLivePhotoId);
                //删除本地磁盘中的图片
                String dbLivePhotoName = dbLivePhoto.getLivePhotoLoc();
                fileUploadUtil.deletePhoto(dbLivePhotoName);
            });

            //将图片绑定Live实体且存入Set集合中
            for (String livePhotosUploadName : livePhotosUploadNames) {
                LivePhoto livePhoto = new LivePhoto();
                livePhoto.setLive(dbLive);
                livePhoto.setLivePhotoLoc(livePhotosUploadName);
                livePhotoSet.add(livePhoto);
            }

            dbLive.setLivePhotos(livePhotoSet);
            dbLive.setLiveTime(liveDTO.getLiveTime());
            dbLive.setLiveName(liveDTO.getLiveName());
            dbLive.setLiveContent(liveDTO.getLiveContent());

            liveDao.save(dbLive);
            //上传图片
            fileUploadUtil.
                                  uploadFiles(livePhotos, livePhotosUploadNames);
        }

    }

    @CacheEvict(key = "#liveId")
    @Override
    public void deleteLive(Long liveId) {

        Live dbLive;
        try {
            dbLive = liveDao.findById(liveId).get();
        }catch(Exception ex){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.LIVE_NOT_FOUND);
        }
        //获取数据库中的所有图片
        Set<LivePhoto> dbLivePhotosSet = dbLive.getLivePhotos();
        //获取图片名字
        List<String> dbLivePhotoNamesArr =
                dbLivePhotosSet.stream().map(livePhoto -> livePhoto.getLivePhotoLoc()).collect(Collectors.toList());
        //删除本地磁盘中的图片
        dbLivePhotoNamesArr.forEach(dbLivePhotoName->fileUploadUtil.deletePhoto(dbLivePhotoName));

        liveDao.deleteById(liveId);

    }

    @Cacheable(key = "#liveId")
    @Override
    public Live getLiveById(Long liveId) {

        Live dbLive;
        try {
            dbLive = liveDao.findById(liveId).get();
            System.out.println(dbLive);
        }catch (Exception ex){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.LIVE_NOT_FOUND);
        }
        return dbLive;

    }
}
