package com.system.garbageclassification.ui.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.squareup.picasso.Picasso;
import com.system.garbageclassification.R;
import com.system.garbageclassification.http.HttpClientUtils;
import com.system.garbageclassification.model.User;
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.tools.UserInfo;
import com.system.garbageclassification.tools.mPicassoEngine;
import com.system.garbageclassification.ui.BaseFragment;
import com.system.garbageclassification.ui.activity.AboutActivity;
import com.system.garbageclassification.ui.activity.ChangePsdActivity;
import com.system.garbageclassification.ui.activity.EditInfoActivity;
import com.system.garbageclassification.ui.activity.LoginActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;
import static com.system.garbageclassification.tools.Constants.getPath;

/**
 * 首页
 */
public class CentralFragment extends BaseFragment {

    /**----------控件----------**/
    @BindView(R.id.user_avatar)
    CircleImageView userAvatar;
    @BindView(R.id.user_nickname)
    TextView userNickname;
    /**----------变量----------**/
    private SVProgressHUD dialog;  //进度条
    private RxPermissions rxPermissions;  //请求权限
    private File avatarFile;  //图片文件
    private boolean isLogin = false;  //判断是否登录的标志
    private User.ResultBean user;  //用户类

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_central;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        dialog = new SVProgressHUD(getContext());  //初始化进度条
        rxPermissions = new RxPermissions(this);  //初始化权限请求
        if(UserInfo.getInstance().getUserId() == null ||
                UserInfo.getInstance().getUserId().isEmpty()){
            userNickname.setText("点击登录");
            userNickname.setTextColor(getResources().getColor(R.color.blue));
            isLogin = false;
        }else {
            isLogin = true;
            loadUserInfo();
        }
    }

    /**
     * 请求用户的信息
     */
    private void loadUserInfo() {
        HttpClientUtils.getHttpUrl(Constants.HOST).getUserById(UserInfo.getInstance().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mResult -> {
                    //根据code判断有否成功与数据库交互
                    if(mResult.getCode() == 1){
                        user = mResult.getResult();
                        //获取成功
                        User.ResultBean data = mResult.getResult();
                        //设置头像
                        if(data.getAvatar() != null && !data.getAvatar().isEmpty()){
                            Picasso.get().load(data.getAvatar()).into(userAvatar);
                        }
                        userNickname.setText(data.getNickname());
                        userNickname.setTextColor(getResources().getColor(R.color.black));
                    }
                }, throwable -> {
                    //查看失败的原因
                    LogUtils.e(throwable.getMessage());
                    //提示用户失败的原因
                    ToastUtils.showShort(throwable.getMessage());
                });
    }

    @OnClick({R.id.user_avatar, R.id.user_nickname, R.id.change_password, R.id.edit_user_info, R.id.about_us, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_avatar:
                if(isLogin){  //登录之后才能修改
                    //修改头像
                    if (Build.VERSION.SDK_INT >= 23) {  //大于等于6.0系统需要动态申请权限
                        //使用rxPermission判断是否允许了读写内存卡和相机权限
                        rxPermissions
                                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.CAMERA)
                                .subscribe(granted -> {
                                    if (granted) {
                                        //用户同意了所有权限
                                        selectPhoto();
                                    } else {
                                        //拒绝了至少一条权限
                                        ToastUtils.showLong("请到设置打开权限后再试");
                                    }
                                });
                    }else {
                        //直接选择图片
                        selectPhoto();
                    }
                }else {
                    ToastUtils.showShort("请先登录.");
                }
                break;
            case R.id.user_nickname:
                if(!isLogin){
                    //未登录，点击之后就跳转到登录页面
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class),Constants.LOGIN);
                }
                break;
            case R.id.change_password:
                if(isLogin){
                    startActivity(new Intent(getActivity(), ChangePsdActivity.class));
                }else {
                    ToastUtils.showShort("请先登录.");
                }
                break;
            case R.id.edit_user_info:
                if(isLogin){
                    Intent intent = new Intent(getActivity(), EditInfoActivity.class);
                    intent.putExtra(Constants.USER,user);  //把用户类传到修改信息界面
                    startActivityForResult(intent,Constants.EDIT_INFO);
                }else {
                    ToastUtils.showShort("请先登录.");
                }
                break;
            case R.id.about_us:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.logout:
                if(isLogin){
                    new AlertDialog.Builder(getContext())
                            .setTitle("提示")
                            .setMessage("是否确认退出登录？")
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", (dialogInterface, i) -> {
                                UserInfo.getInstance().clearUserInfo();
                                isLogin = false;
                                userNickname.setText("点击登录");
                                userNickname.setTextColor(getResources().getColor(R.color.blue));
                                Picasso.get().load(R.mipmap.morentouxiang).into(userAvatar);  //修改成默认头像
                                ToastUtils.showShort("退出登录成功");
                            }).create().show();
                }else {
                    ToastUtils.showShort("请先登录.");
                }
                break;
        }
    }

    /**
     * 选择图片
     */
    private void selectPhoto() {
        Matisse.from(this)
                .choose(MimeType.ofImage(), false)  //图片
                .theme(R.style.Matisse_Dracula)  //主题
                .countable(true)
                .maxSelectable(1)  //最大选择数
                .capture(true)  //允许相机
                .captureStrategy(
                        new CaptureStrategy(true, "com.system.garbageclassification.fileprovider"))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new mPicassoEngine())
                .forResult(100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //选择头像之后，会在这里得到头像的相关信息
        if (requestCode == 100 && resultCode == RESULT_OK) {
            List<String> images = Matisse.obtainPathResult(data);
            //手机拍照图片太大，会造成界面卡顿，使用Luban对图片进行压缩，再显示
            Luban.with(getContext())
                    .load(images)
                    .ignoreBy(100)
                    .setTargetDir(getPath())
                    .filter(path -> !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif")))
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
                            // TODO 压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            // TODO 压缩成功后调用，返回压缩后的图片文件
                            avatarFile = file;  //获取压缩后的图片文件
                            upload();
                        }

                        @Override
                        public void onError(Throwable e) {
                            // TODO 当压缩过程出现问题时调用
                        }
                    }).launch();
        }

        //登录成功后
        if(requestCode == Constants.LOGIN && resultCode == Constants.LOGIN){
            //请求用户的信息
            isLogin = true;
            loadUserInfo();
        }

        //编辑个人信息成功后
        if(requestCode == Constants.EDIT_INFO && resultCode == Constants.EDIT_INFO){
            //重新请求用户的信息
            loadUserInfo();
        }
    }

    /**
     * 网路请求-上传头像
     */
    private void upload() {
        if(avatarFile != null){
            //显示进度条
            dialog.showWithStatus("头像上传中");
            //图片文件存在
            RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    avatarFile);  //文件
            MultipartBody.Part photoBody = MultipartBody.Part.createFormData
                    ("avatar", avatarFile.getName(), fileBody);  //图片
            RequestBody idBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    UserInfo.getInstance().getUserId());  //用户id
            HttpClientUtils.getHttpUrl(Constants.HOST).changeAvatar(photoBody,idBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mResult -> {
                        //隐藏进度条
                        dialog.dismiss();
                        //提示服务端返回的消息
                        ToastUtils.showShort(mResult.getMessage());
                        //根据code判断有否成功与数据库交互
                        if(mResult.getCode() == 1){
                            //上传成功,更新头像
                            Picasso.get().load(mResult.getResult()).into(userAvatar);
                        }
                    }, throwable -> {
                        //隐藏进度条
                        dialog.dismiss();
                        //查看失败的原因
                        LogUtils.e(throwable.getMessage());
                        //提示用户失败的原因
                        ToastUtils.showShort(throwable.getMessage());
                    });
        }
    }
}
