package apwdevs.football.club

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.annotation.DrawableRes
import android.support.annotation.Px
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

object LoadScalledImages {
    fun getScalledFromResources(res: Resources, id: Int, @Px height: Int, @Px width: Int, quality_factor: Float) : Bitmap{
        val bitmapRes = decodeAndResizeBitmapsResources(res, id, height, width)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmapRes, width, height, false)
        var scaling = (bitmapRes.getHeight() / height).toFloat()
        scaling = if (scaling < 1.0f) 1.0f else scaling
        val bos = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, Math.round(quality_factor/scaling), bos)
        bos.flush()
        bitmapRes.recycle()
        scaledBitmap.recycle()
        val bytes = bos.toByteArray()
        bos.close()
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    fun getScalledCachesFromResources(ctx: Context, id: Int, @Px height: Int, @Px width: Int, quality_factor: Float) : Bitmap{
        val cacheFile = ctx.filesDir
        cacheFile.mkdir()
        val names = "${id}_${height}_${width}_${quality_factor}"
        val fileNames = File(cacheFile, names)
        if(fileNames.exists()){
            return BitmapFactory.decodeFile(fileNames.absolutePath)
        } else {
            val bitmapRes = decodeAndResizeBitmapsResources(ctx.resources, id, height, width)
            val scaledBitmap = Bitmap.createScaledBitmap(bitmapRes, width, height, false)
            var scaling = (bitmapRes.getHeight() / height).toFloat()
            scaling = if (scaling < 1.0f) 1.0f else scaling
            val bos = ByteArrayOutputStream()
            scaledBitmap.compress(Bitmap.CompressFormat.PNG, Math.round(quality_factor / scaling), bos)
            bos.flush()
            bitmapRes.recycle()
            scaledBitmap.recycle()
            val bytes = bos.toByteArray()
            val osf = FileOutputStream(fileNames)
            osf.write(bytes)
            osf.flush()
            osf.close()
            bos.close()
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }
    }

    private fun decodeAndResizeBitmapsResources(res: Resources, @DrawableRes resImageDrawable: Int, @Px reqHeight: Int, @Px reqWidth: Int): Bitmap {
        val optionBitmaps = BitmapFactory.Options()
        optionBitmaps.inJustDecodeBounds = true

        BitmapFactory.decodeResource(res, resImageDrawable, optionBitmaps)

        optionBitmaps.inSampleSize = calculateImageInSampleSize(optionBitmaps, reqHeight, reqWidth)
        optionBitmaps.inJustDecodeBounds = false
        System.gc()
        return BitmapFactory.decodeResource(res, resImageDrawable, optionBitmaps)
    }

    private fun calculateImageInSampleSize(optionBitmaps: BitmapFactory.Options, reqHeight: Int, reqWidth: Int): Int {
        val height = optionBitmaps.outHeight
        val width = optionBitmaps.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfWidth = width / 2
            val halfHeight = height / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}