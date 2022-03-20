package com.haven.vodService.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.haven.baseService.ExceptionHandler.GuliException;
import com.haven.utilscommon.vo.R;
import com.haven.vodService.entity.AliyunKeyProperties;
import com.haven.vodService.service.VodService;
import com.haven.vodService.util.AliyunVodUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            UploadStreamRequest request=new UploadStreamRequest(AliyunKeyProperties.ACCESSKEYID,AliyunKeyProperties.ACCESSKEYSECRET,title,originalFilename,inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                String videoId=response.getVideoId();
              return videoId;
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException(20001,"上传视频失败");

        }
        return null;
    }

    @Override
    public String getVideoPlayInfo(String videoId) {

        DefaultAcsClient client = AliyunVodUtil.initVodClient(AliyunKeyProperties.ACCESSKEYID,AliyunKeyProperties.ACCESSKEYSECRET);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        try {
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            //播放凭证
            String playAuth= response.getPlayAuth();
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
            return playAuth;
        } catch (ClientException e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            e.printStackTrace();
            throw new GuliException(20001,"获取播放凭证失败");
        }
    }

    @Override
    public void deleteVideo(String videoId) {
        DefaultAcsClient client = AliyunVodUtil.initVodClient(AliyunKeyProperties.ACCESSKEYID,AliyunKeyProperties.ACCESSKEYSECRET);
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoId);
        try {
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (ClientException e) {
            e.printStackTrace();
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteVideoList(List<String> videoList) {
        String vlist = StringUtils.join(videoList.toArray(),",");
        DefaultAcsClient client = AliyunVodUtil.initVodClient(AliyunKeyProperties.ACCESSKEYID,AliyunKeyProperties.ACCESSKEYSECRET);
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(vlist);
        try {
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (ClientException e) {
            e.printStackTrace();
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }

    }
}
