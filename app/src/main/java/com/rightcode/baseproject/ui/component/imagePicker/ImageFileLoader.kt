package com.rightcode.baseproject.ui.component.imagePicker

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.rightcode.baseproject.ui.component.imagePicker.listener.OnImageLoaderListener
import com.rightcode.baseproject.ui.component.imagePicker.model.Image
import com.rightcode.baseproject.util.extension.LogD
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ImageFileLoader(private val context: Context) {

    private val projection = arrayOf(
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DISPLAY_NAME,
//        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.BUCKET_ID,
        MediaStore.Images.Media.BUCKET_DISPLAY_NAME
    )

    private val executorService = Executors.newSingleThreadExecutor()
    private val futures = arrayListOf<Future<*>>()

    fun loadDeviceImages(listener: OnImageLoaderListener) {
        val future = executorService.submit(ImageLoadRunnable((listener)))
        futures.add(future)
    }

    fun abortLoadImages() {
        for (future in futures) {
            future.cancel(true)
        }
        futures.clear()
    }

    private inner class ImageLoadRunnable(private val listener: OnImageLoaderListener) : Runnable {
        override fun run() {
            val cursor = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Images.Media.DATE_ADDED + " DESC"
            )
            if (cursor == null) {
                listener.onFailed(NullPointerException())
                return
            }
            // TODO (MediaStore.Images.Media.DATA Deprecated)
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
//            val dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
            val bucketIdColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID)
            val bucketNameColumn =
                cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            LogD(cursor.isClosed)
            val images = arrayListOf<Image>()
            if (cursor.moveToFirst()) {
                do {
                    if (Thread.interrupted()) {
                        listener.onFailed(NullPointerException())
                        return
                    }
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
//                    val path = cursor.getString(dataColumn)
                    val bucketId = cursor.getLong(bucketIdColumn)
                    val bucketName = cursor.getString(bucketNameColumn)

//                    val file = makeSafeFile(path)
//                    if (file != null && file.exists()) {
//                        if (name != null && path != null && bucketName != null) {
                            val uri = ContentUris.withAppendedId(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                id
                            )
                            LogD(uri)
                            val image = Image(id, name, uri,  bucketId, bucketName)
                            images.add(image)
//                        }
//                    }

                } while (cursor.moveToNext())
            }

            cursor.close()
            listener.onImageLoaded(images)
            Thread.interrupted()
        }

    }

    companion object {
        private fun makeSafeFile(path: String?): File? {
            return if (path == null || path.isEmpty()) {
                null
            } else try {
                File(path)
            } catch (ignored: Exception) {
                null
            }
        }
    }
}