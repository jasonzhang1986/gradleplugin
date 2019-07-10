package com.sogou.teemo.watch.gradle;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.AppPlugin;
import com.android.build.gradle.internal.api.BaseVariantImpl;
import com.android.builder.core.AndroidBuilder;
import com.android.sdklib.BuildToolInfo;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("自定义插件>>>>>");
        project.getPlugins().all( plugin -> {
            if (plugin instanceof AppPlugin) {
                System.out.println("app plugin");
                AppExtension appPlugin = project.getExtensions().getByType(AppExtension.class);
                modifyAaptPathTask(project,appPlugin);
            }
        });
        System.out.println("自定义插件<<<<<");
    }


    private void modifyAaptPathTask(Project project, AppExtension android){
        //1.设置aapt参数
        //2.修改aapt路径使用 修改过的aapt文件
        //3.不启用aapt2 android.enableAapt2=false
        android.getAaptOptions().additionalParameters("--PLUG-resoure-id","0x8f");
        android.getApplicationVariants().all(variant -> {
            try {
                System.out.println("name:"+variant.getName());
                Field androidBuilderField = BaseVariantImpl.class.getDeclaredField("androidBuilder");
                androidBuilderField.setAccessible(true);
                AndroidBuilder androidBuilder = (AndroidBuilder) androidBuilderField.get(variant);
                BuildToolInfo buildToolInfo = androidBuilder.getTargetInfo().getBuildTools();
                Method addMethod = BuildToolInfo.class.getDeclaredMethod("add",BuildToolInfo.PathId.class, File.class);
                addMethod.setAccessible(true);
                String osName = System.getProperty("os.name");
                System.out.println("osName:"+osName);
                String aaptName;
                if (osName.contains("Mac OS")){
                    aaptName="aapt_mac";
                }else {
                    aaptName="aapt_win";
                }
                addMethod.invoke(buildToolInfo,BuildToolInfo.PathId.AAPT,new File(project.getRootDir()+"/tools/",aaptName));
                System.out.println("new aapt path:"+buildToolInfo.getPath(BuildToolInfo.PathId.AAPT));

            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
