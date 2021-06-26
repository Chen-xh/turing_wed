package com.turing.website.service.admin.impl;


import com.turing.website.dao.AwardDao;
import com.turing.website.dao.AwardPhotoDao;
import com.turing.website.dao.MemberDao;
import com.turing.website.dto.AwardDTO;
import com.turing.website.entity.Award;
import com.turing.website.entity.AwardPhoto;
import com.turing.website.entity.Member;
import com.turing.website.enums.MyCustomizeErrorCode;
import com.turing.website.exception.CustomizeRuntimeException;
import com.turing.website.service.admin.AdminAwardService;
import com.turing.website.util.FileUploadUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author CHEN
 * @date 2020/3/2 21:01
 */
@Service
public class AdminAwardServiceImpl implements AdminAwardService {

    @Autowired
    AwardDao awardDao;

    @Autowired
    MemberDao memberDao;

    @Autowired
    AwardPhotoDao awardPhotoDao;

    @Autowired
    FileUploadUtil fileUploadUtil;

    @Override
    public void addOrUpdateAward(AwardDTO awardDTO, MultipartFile[] awardPhotos) {

        List<Member> members = new ArrayList<>();
        awardDTO.getMembers().forEach(memberName->{
            System.out.println(memberName);
            Member dbMember = memberDao.findByMemberNameEquals(memberName);
            if(dbMember == null){
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
            }
            members.add(dbMember);
        });
        if(members.size() == 0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.MEMBER_NOT_FOUND);
        }
        //将List转为Set,用于绑定对应奖项
        Set<Member> memberSet = new HashSet<>(members);
        //文件上传处理
        //图片集合,用于绑定对应奖项
        Set<AwardPhoto> awardPhotoList = new HashSet<>();
        //获取上传文件的文件名,UUID形式
        String[] photoUploadNames = fileUploadUtil.getFileNewNames(awardPhotos);
        if(awardDTO.getAwardId() == null){
            //创建
            Award award = new Award();
            //创建AwardPhoto实体并添加进图片集合中
            for (String photoName : photoUploadNames) {
                AwardPhoto awardPhoto = new AwardPhoto();
                awardPhoto.setAward(award);
                awardPhoto.setAwardPhotoLoc(photoName);
                awardPhotoList.add(awardPhoto);
            }
            award.setAwardPhoto(awardPhotoList);
            award.setAwardMember(memberSet);
            award.setAwardTime(awardDTO.getAwardTime());
            award.setAwardName(awardDTO.getAwardName());

            awardDao.save(award);
            //上传文件
            fileUploadUtil.uploadFiles(awardPhotos, photoUploadNames);
        }else{
            //更新
            Award dbAward;
            try {
                dbAward = awardDao.findById(awardDTO.getAwardId()).get();
            }catch (Exception ex){
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.AWARD_NOT_FOUND);
            }

            for (String photoName : photoUploadNames) {
                AwardPhoto awardPhoto = new AwardPhoto();
                awardPhoto.setAward(dbAward);
                awardPhoto.setAwardPhotoLoc(photoName);
                awardPhotoList.add(awardPhoto);
            }
            Set<AwardPhoto> dbProjectPhotosSet = dbAward.getAwardPhoto();
            //解除图片和项目的依赖关系,否则无法删除,因为有mappedBy属性
            dbAward.setAwardPhoto(null);
            dbProjectPhotosSet.forEach(dbAwardPhoto->{
                //获取数据库中的照片id
                Long dbAwardPhotoId = dbAwardPhoto.getAwardPhotoId();
                //删除数据库中的照片
                awardPhotoDao.deleteById(dbAwardPhotoId);
                //删除本地磁盘的图片
                String awardPhotoLoc = dbAwardPhoto.getAwardPhotoLoc();
                fileUploadUtil.deletePhoto(awardPhotoLoc);
            });

            dbAward.setAwardName(awardDTO.getAwardName());
            dbAward.setAwardTime((awardDTO.getAwardTime()));
            dbAward.setAwardMember(memberSet);
            dbAward.setAwardPhoto(awardPhotoList);
            dbAward.setAwardId(awardDTO.getAwardId());

            awardDao.save(dbAward);
            //上传文件
            fileUploadUtil.uploadFiles(awardPhotos, photoUploadNames);
        }

    }

    @Override
    public void deleteAward(Long awardId) {

        Award dbAward;
        try {
            dbAward = awardDao.findById(awardId).get();
        }catch (Exception ex){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.AWARD_NOT_FOUND);
        }
        awardDao.deleteById(awardId);
        //删除本地磁盘的图片
        dbAward.getAwardPhoto().stream().forEach(awardPhoto -> {
            fileUploadUtil.deletePhoto(awardPhoto.getAwardPhotoLoc());
        });

    }

    @Override
    public AwardDTO findAwardWithMembers(Long id) {

        Award dbAward;
        try {
            dbAward = awardDao.findById(id).get();
        }catch (Exception ex){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.AWARD_NOT_FOUND);
        }

        Set<Member> awardMember = dbAward.getAwardMember();
        List<String> members = awardMember.stream().map(member -> member.getMemberName()).collect(Collectors.toList());
        AwardDTO awardDTO = new AwardDTO();
        BeanUtils.copyProperties(dbAward,awardDTO);
        awardDTO.setMembers(members);
        return awardDTO;

    }

}
