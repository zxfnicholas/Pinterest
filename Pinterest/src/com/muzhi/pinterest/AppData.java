package com.muzhi.pinterest;


import java.io.File;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public final class AppData extends Application{
	
	private static AppData instance;
	public static ImageLoader imageLoader;
	
	public static final String DISK_CACHE_DIR = "vsion";
    public static final float MEMORY_CACHE_PERCENT = 0.5f;
    public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
	
	public static boolean isDebug = true;
	public static String DB_NAME = "vsion_db";
	
	
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        imageLoader = initImageLoader(this, imageLoader, DISK_CACHE_DIR); 
    }
    
    public static AppData getInstance() {
        return instance;
    }
    
    public static String getDiskCacheDir() {
		return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + DISK_CACHE_DIR;
	}
	 
	/**
	 * 初始化图片下载器，图片缓存地址<i>("/Android/data/[app_package_name]/cache/dirName")</i>
	 */
	public ImageLoader initImageLoader(Context context,ImageLoader imageLoader, String dirName) {
			
		imageLoader = ImageLoader.getInstance();
		if (imageLoader.isInited()) {
			// 重新初始化ImageLoader时,需要释放资源.
			imageLoader.destroy();
		}
		imageLoader.init(initImageLoaderConfig(context, dirName));
		return imageLoader;
	}

	/**
	 * 配置图片下载器
	 * 
	 * @param dirName 文件名
	 *            
	 */
	private ImageLoaderConfiguration initImageLoaderConfig(Context context, String dirName) {
		
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)				
				.threadPoolSize(3).memoryCacheSize(getMemoryCacheSize(context))
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				//.discCache(new UnlimitedDiscCache(new File(dirName)))
				.discCache(new UnlimitedDiscCache(dir))
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		return config;
	}

	private int getMemoryCacheSize(Context context) {
		int memoryCacheSize;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
			int memClass = ((ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE))
					.getMemoryClass();
			memoryCacheSize = (memClass / 8) * 1024 * 1024; // 1/8 of app memory
															// limit
		} else {
			memoryCacheSize = 2 * 1024 * 1024;
		}
		return memoryCacheSize;
	}
	
	
	
}

