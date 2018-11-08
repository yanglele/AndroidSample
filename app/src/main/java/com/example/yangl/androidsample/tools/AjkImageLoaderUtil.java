package com.example.yangl.androidsample.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Px;
import android.text.TextUtils;

import com.example.yangl.androidsample.R;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.ByteConstants;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.io.InputStream;
import static android.R.attr.height;
import static android.R.attr.width;

import okhttp3.OkHttpClient;

/**
 * desc:图片加载工具类
 * version: 9.9
 * email: genhuliu
 */

public class AjkImageLoaderUtil {
    private static String DEBUG_TAG = "AjkImageLoaderUtil";

    @SuppressLint("StaticFieldLeak")
    private static final AjkImageLoaderUtil sInstance = new AjkImageLoaderUtil();

    public static AjkImageLoaderUtil getInstance() {
        return sInstance;
    }

    private Context mContext;

    // 分配的可用内存
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    // 使用的缓存数量
    private static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 8;
    // 默认图磁盘缓存的最大值
    private static final int MAX_DISK_CACHE_SIZE = 128 * ByteConstants.MB;
    // 默认图所放路径的文件夹名
    private static final String IMAGE_CACHE_DIR = "ajkimage";
    // 未自定义默认图资源id
    public static final int UN_CUSTOME_DEFAULT_IMAGE_RES_ID = -1;

    /**
     * 初始化(需在Application的onCreate中调用)
     */
    public void init(Context context) {
        mContext = context;
        Fresco.initialize(context, getOkHttpImagePipelineConfig(context));
    }

    /**
     * 创建使用OkHttp作为网络请求的配置
     */
    public static ImagePipelineConfig getOkHttpImagePipelineConfig(Context context) {
        OkHttpClient client = new OkHttpClient();
        ImagePipelineConfig.Builder configBuilder = OkHttpImagePipelineConfigFactory.newBuilder(context, client);
        configureCaches(configBuilder, context);
        configureLoggingListeners(configBuilder);
        return configBuilder.build();
    }

    /**
     * 配置缓存
     */
    private static void configureCaches(ImagePipelineConfig.Builder configBuilder, Context context) {

        long maxDiskCacheSize = StorageUtil.getAvailableSize() / 8;
        maxDiskCacheSize = (maxDiskCacheSize <= 0) ? MAX_DISK_CACHE_SIZE : maxDiskCacheSize;
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                getMaxMemoryCacheSize(),  // 内存缓存中总图片的最大大小,以字节为单位
                Integer.MAX_VALUE,        // 内存缓存中图片的最大数量
                Integer.MAX_VALUE,        // 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位
                Integer.MAX_VALUE,        // 内存缓存中准备清除的总图片的最大数量
                Integer.MAX_VALUE);       // 内存缓存中单个图片的最大大小

        configBuilder.setBitmapMemoryCacheParamsSupplier(
                new Supplier<MemoryCacheParams>() {
                    public MemoryCacheParams get() {
                        return bitmapCacheParams;
                    }
                })
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(context)
                                .setBaseDirectoryPath(StorageUtil.getCacheDirectory(context.getApplicationContext())) // 基目录
                                .setBaseDirectoryName(IMAGE_CACHE_DIR) // 文件名
                                .setMaxCacheSize(maxDiskCacheSize) // 最大缓存数量
                                .setDiskTrimmableRegistry(NoOpDiskTrimmableRegistry.getInstance())
                                .build())
                .setMemoryTrimmableRegistry(NoOpMemoryTrimmableRegistry.getInstance())
                .setDownsampleEnabled(true)
//                .setResizeAndRotateEnabledForNetwork(false)
                .setBitmapsConfig(Bitmap.Config.RGB_565);

        // 用于清理缓存
        NoOpMemoryTrimmableRegistry.getInstance().registerMemoryTrimmable(new MemoryTrimmable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void trim(MemoryTrimType trimType) {
                final double suggestedTrimRatio = trimType.getSuggestedTrimRatio();

                if (MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio() == suggestedTrimRatio
                        || MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.getSuggestedTrimRatio() == suggestedTrimRatio
                        || MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.getSuggestedTrimRatio() == suggestedTrimRatio
                        ) {
                    ImagePipelineFactory.getInstance().getImagePipeline().clearMemoryCaches();
                }
            }
        });
    }

    /**
     * 获取最大内存缓存尺寸
     */
    private static int getMaxMemoryCacheSize() {
        if (MAX_HEAP_SIZE < 32 * ByteConstants.MB) {
            return 4 * ByteConstants.MB;
        } else if (MAX_HEAP_SIZE < 64 * ByteConstants.MB) {
            return 6 * ByteConstants.MB;
        } else {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD) {
                return 8 * ByteConstants.MB;
            } else {
                return MAX_MEMORY_CACHE_SIZE;
            }
        }
    }

    /**
     * 配置log监听
     *
     * @param configBuilder
     */
    private static void configureLoggingListeners(ImagePipelineConfig.Builder configBuilder) {
        configBuilder.setRequestListeners(
                Sets.newHashSet((RequestListener) new RequestLoggingListener()));
    }

    /**
     * 显示图片
     */
    public void displayImage(String url, SimpleDraweeView view) {
        displayImage(url, view, null, UN_CUSTOME_DEFAULT_IMAGE_RES_ID, true, 0, 0);
    }

    /**
     * 显示图片
     */
    public void displayImage(String url, SimpleDraweeView view, boolean isShowDefaultImage) {
        displayImage(url, view, null, UN_CUSTOME_DEFAULT_IMAGE_RES_ID, isShowDefaultImage, 0, 0);
    }

    /**
     * 显示图片
     */
    public void displayImage(String url, SimpleDraweeView view, int customDefaultImageResId) {
        displayImage(url, view, null, customDefaultImageResId, false, 0, 0);
    }

    /**
     * 显示图片
     */
    public void displayImage(String url, SimpleDraweeView view, ControllerListener controllerListener) {
        displayImage(url, view, controllerListener, UN_CUSTOME_DEFAULT_IMAGE_RES_ID, true, 0, 0);
    }

    /**
     * 显示图片
     */
    public void displayImage(String url, final SimpleDraweeView view, ControllerListener controllerListener, boolean isShowDefaultImage) {
        displayImage(url, view, controllerListener, UN_CUSTOME_DEFAULT_IMAGE_RES_ID, isShowDefaultImage, 0, 0);
    }

    /**
     * 显示图片
     */
    public void displayImage(String url, SimpleDraweeView view, int width, int height) {
        displayImage(url, view, null, UN_CUSTOME_DEFAULT_IMAGE_RES_ID, true, width, height);
    }

    public void displayImage(String url, SimpleDraweeView view, int defaultImage, int width, int height) {
        displayImage(url, view, null, defaultImage, false, width, height);
    }

    /**
     * 显示图片-动态大图显示
     */
    public void displayImage(String url, SimpleDraweeView view, int width, int height, int maxSize, boolean isShowDefaultImage, ControllerListener controllerListener) {
        displayImage(url, view, UN_CUSTOME_DEFAULT_IMAGE_RES_ID, isShowDefaultImage, width, height, maxSize, controllerListener);
    }

    /**
     * 显示图片
     */
    public void displayImage(String url, SimpleDraweeView view, ControllerListener controllerListener,
                             int customDefaultImageResId, boolean isShowDefaultImage, int width, int height) {
        if (null == view) {
            DevUtil.d(AjkImageLoaderUtil.class.getSimpleName(), "displayImage params error");
            return;
        }

        // ImageRequest
        url = (url == null) ? "" : url;
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url));
        if (width > 0 && height > 0) {
            DevUtil.d(AjkImageLoaderUtil.class.getSimpleName(), "displayImage width=" + width + ",height=" + height);
            builder.setResizeOptions(new ResizeOptions(width, height));
        }
        ImageRequest request = builder.build();

        // DraweeController
        PipelineDraweeControllerBuilder controllerBuilder = Fresco.newDraweeControllerBuilder();
        controllerBuilder.setOldController(view.getController());
        controllerBuilder.setImageRequest(request);
        if (controllerListener != null) {
            controllerBuilder.setControllerListener(controllerListener);
        }
        DraweeController controller = controllerBuilder.build();

        // DefaultImage
        if (customDefaultImageResId == UN_CUSTOME_DEFAULT_IMAGE_RES_ID && isShowDefaultImage) {
            DevUtil.d(AjkImageLoaderUtil.class.getSimpleName(), "displayImage show default image");
            customDefaultImageResId = R.drawable.image_bg_default;
        }
        if (customDefaultImageResId != UN_CUSTOME_DEFAULT_IMAGE_RES_ID) {
            GenericDraweeHierarchy hierarchy = view.getHierarchy();
            hierarchy.setPlaceholderImage(customDefaultImageResId, ScalingUtils.ScaleType.FIT_XY);
            hierarchy.setFailureImage(customDefaultImageResId, ScalingUtils.ScaleType.FIT_XY);
        }

        view.setController(controller);
    }

    /**
     * 加载大图
     */
    public void displayImage(String url, SimpleDraweeView view,
                             int customDefaultImageResId, boolean isShowDefaultImage,
                             int width, int height, @Px int maxImageSize, ControllerListener controllerListener) {
        if (null == view) {
            DevUtil.d(AjkImageLoaderUtil.class.getSimpleName(), "displayImage params error");
            return;
        }

        // ImageRequest
        url = (url == null) ? "" : url;
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url));
        if (width > 0 && height > 0) {
            DevUtil.d(AjkImageLoaderUtil.class.getSimpleName(), "displayImage width=" + width + ",height=" + height);
            builder.setResizeOptions(new ResizeOptions(width, height, maxImageSize));
        }
        ImageRequest request = builder.build();

        // DraweeController
        PipelineDraweeControllerBuilder controllerBuilder = Fresco.newDraweeControllerBuilder();
        controllerBuilder.setOldController(view.getController());
        controllerBuilder.setImageRequest(request);
        if (controllerListener != null) {
            controllerBuilder.setControllerListener(controllerListener);
        }
        DraweeController controller = controllerBuilder.build();

        // DefaultImage
        if (customDefaultImageResId == UN_CUSTOME_DEFAULT_IMAGE_RES_ID && isShowDefaultImage) {
            DevUtil.d(AjkImageLoaderUtil.class.getSimpleName(), "displayImage show default image");
            customDefaultImageResId = R.drawable.image_bg_default;
        }
        if (customDefaultImageResId != UN_CUSTOME_DEFAULT_IMAGE_RES_ID) {
            GenericDraweeHierarchy hierarchy = view.getHierarchy();
            hierarchy.setPlaceholderImage(customDefaultImageResId, ScalingUtils.ScaleType.FIT_XY);
            hierarchy.setFailureImage(customDefaultImageResId, ScalingUtils.ScaleType.FIT_XY);
        }

        view.setController(controller);
    }

    /**
     * 加载bitmap
     */
    public void loadBitmap(String url, final LoadBitmapListener loadBitmapListener) {
        loadBitmap(url, 0, 0, loadBitmapListener);
    }

    /**
     * 加载bitmap
     */
    public void loadBitmap(final String url, final int width, final int height, final LoadBitmapListener loadBitmapListener) {
        if (TextUtils.isEmpty(url)) {
            DevUtil.d(AjkImageLoaderUtil.class.getSimpleName(), "loadBitmap params error");
            if (loadBitmapListener != null) {
                loadBitmapListener.onFailure(url);
            }
            return;
        }

        DataSubscriber dataSubscriber = new BaseDataSubscriber<CloseableReference<CloseableBitmap>>() {
            @Override
            public void onNewResultImpl(
                    DataSource<CloseableReference<CloseableBitmap>> dataSource) {
                Bitmap resizeBitmap = null;
                if (dataSource != null && dataSource.isFinished()) {
                    CloseableReference<CloseableBitmap> imageReference = dataSource.getResult();
                    if (imageReference != null) {
                        try {
                            CloseableBitmap closeableBitmap = imageReference.get();
                            Bitmap bitmap = closeableBitmap.getUnderlyingBitmap();
                            float scale = 1.0f;
                            if (width > 0 && height > 0) {
                                if (width < bitmap.getWidth()) {
                                    scale = (float) width / bitmap.getWidth();
                                }
                                if (height < bitmap.getHeight()) {
                                    if ((float) height / bitmap.getHeight() < scale) {
                                        scale = (float) height / bitmap.getHeight();
                                    }
                                }
                            }
                            Matrix matrix = new Matrix();
                            matrix.postScale(scale, scale);
                            resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//                            if (bitmap != null && !bitmap.isRecycled()) {
//                                bitmap.recycle();
//                            }
                        } catch (Exception ex) {
                            if (ex != null) {
                                DebugUtil.e(DEBUG_TAG, ex.getMessage());
                            }
                        } catch (OutOfMemoryError error) {
                            if (error != null) {
                                DebugUtil.e(DEBUG_TAG, error.getMessage());
                            }
                        } finally {
                            imageReference.close();
                        }
                    }
                }

                if (loadBitmapListener != null) {
                    if (resizeBitmap != null && !resizeBitmap.isRecycled()) {
                        loadBitmapListener.onSuccess(url, resizeBitmap);
                    } else {
                        loadBitmapListener.onFailure(url);
                    }
                }
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {
                if (loadBitmapListener != null) {
                    loadBitmapListener.onFailure(url);
                }
            }
        };

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url));
        if (width > 0 && height > 0) {
            builder.setResizeOptions(new ResizeOptions(width, height));
        }
        ImageRequest request = builder.build();
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(request, mContext);
        dataSource.subscribe(dataSubscriber, UiThreadImmediateExecutorService.getInstance());
    }

    /**
     * 加载数据流
     */
    public void loadInputSteam(final String url, final LoadInputStreamListener loadInputStreamListener) {
        DataSubscriber dataSubscriber = new BaseDataSubscriber<CloseableReference<PooledByteBuffer>>() {
            @Override
            public void onNewResultImpl(
                    DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
                if (dataSource != null && dataSource.isFinished()) {
                    CloseableReference<PooledByteBuffer> imageReference = dataSource.getResult();
                    if (imageReference != null) {
                        try {
                            PooledByteBuffer buffer = imageReference.get();
                            InputStream is = new PooledByteBufferInputStream(buffer);
                            if (loadInputStreamListener != null) {
                                loadInputStreamListener.onSuccess(url, is);
                            }
                        } catch (Exception ex) {
                            if (ex != null) {
                                DebugUtil.e(DEBUG_TAG, ex.getMessage());
                            }
                        } catch (OutOfMemoryError error) {
                            if (error != null) {
                                DebugUtil.e(DEBUG_TAG, error.getMessage());
                            }
                        } finally {
                            imageReference.close();
                        }
                    }
                }


            }

            @Override
            public void onFailureImpl(DataSource dataSource) {
                if (loadInputStreamListener != null) {
                    loadInputStreamListener.onFailure(url);
                }
            }
        };

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url));
        if (width > 0 && height > 0) {
            builder.setResizeOptions(new ResizeOptions(width, height));
        }
        ImageRequest request = builder.build();
        DataSource<CloseableReference<PooledByteBuffer>> dataSource =
                imagePipeline.fetchEncodedImage(request, mContext);
        dataSource.subscribe(dataSubscriber, UiThreadImmediateExecutorService.getInstance());
    }

    /**
     * 获取缓存文件的路径
     */
    public String getCacheFilePath(String url) {
        if (TextUtils.isEmpty(url)) {
            DevUtil.d(AjkImageLoaderUtil.class.getSimpleName(), "getCacheFilePath params error");
            return "";
        }

        File localFile = null;
        try {
            CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(url), null);
            if (ImagePipelineFactory.getInstance().getMainFileCache().hasKey(cacheKey)) {
                BinaryResource resource = ImagePipelineFactory.getInstance().getMainFileCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            }
        } catch (Exception ex) {
            if (ex != null) {
                DebugUtil.e(DEBUG_TAG, ex.getMessage());
            }
        }
        return localFile == null ? "" : localFile.getPath();
    }

    /**
     * 获取缓存文件的bitmap
     */
    public Bitmap getCacheFileBitmap(String url) {
        if (TextUtils.isEmpty(url)) {
            DevUtil.d(AjkImageLoaderUtil.class.getSimpleName(), "getCacheFileBitmap params error");
            return null;
        }
        String cacheFilePath = getCacheFilePath(url);
        if (TextUtils.isEmpty(cacheFilePath)) {
            DevUtil.d(AjkImageLoaderUtil.class.getSimpleName(), "getCacheFileBitmap:cacheFilePath=null");
            return null;
        }

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(cacheFilePath);
        } catch (Exception ex) {
            if (ex != null) {
                DebugUtil.e(DEBUG_TAG, ex.getMessage());
            }
        } catch (OutOfMemoryError ex) {
            if (ex != null) {
                DebugUtil.e(DEBUG_TAG, ex.getMessage());
            }
        }

        return bitmap;
    }

    /**
     * 是否在内存缓存
     */
    public boolean isInBitmapMemoryCache(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        return imagePipeline.isInBitmapMemoryCache(Uri.parse(url));
    }

    /**
     * 暂停加载
     */
    public void pauseLoader() {
        if (Fresco.getImagePipeline() != null && !Fresco.getImagePipeline().isPaused()) {
            Fresco.getImagePipeline().pause();
        }
    }

    /**
     * 恢复加载
     */
    public void resumeLoader() {
        if (Fresco.getImagePipeline() != null && Fresco.getImagePipeline().isPaused()) {
            Fresco.getImagePipeline().resume();
        }
    }

    public interface LoadBitmapListener {
        void onSuccess(String url, Bitmap bitmap);

        void onFailure(String url);
    }

    public interface LoadInputStreamListener {
        void onSuccess(String url, InputStream is);

        void onFailure(String url);
    }
}
