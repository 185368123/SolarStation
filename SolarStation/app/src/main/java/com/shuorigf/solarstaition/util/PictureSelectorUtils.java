package com.shuorigf.solarstaition.util;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

/**
 * Created by clx on 2017/10/25.
 */

public class PictureSelectorUtils {

    public static void intoPictureSelector(Activity activity,int cropWidth,int cropHeight,int ratioX ,int ratioY){
        intoPictureSelector(PictureSelector.create(activity),cropWidth,cropHeight,ratioX,ratioY);
    }

    public static void intoPictureSelector(Fragment fragment, int cropWidth, int cropHeight, int ratioX , int ratioY){
        intoPictureSelector(PictureSelector.create(fragment),cropWidth,cropHeight,ratioX,ratioY);
    }


    private static void intoPictureSelector(PictureSelector pictureSelector, int cropWidth, int cropHeight, int ratioX , int ratioY){
        pictureSelector.openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .imageSpanCount(3)// 每行显示个数
                .previewImage(true)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .sizeMultiplier(0.9f)//加载图片大小 0~1之间
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .withAspectRatio(ratioX,ratioY)// int 裁剪比例
                .synOrAsy(false)
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验
                .cropWH(cropWidth,cropHeight)// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

}
