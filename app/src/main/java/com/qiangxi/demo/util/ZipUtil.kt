package com.qiangxi.demo.util

import android.text.TextUtils
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.zip.CRC32
import java.util.zip.CheckedOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

/**
 * Create By renqiangqiang . 2019/2/14
 */
object ZipUtil {

    private const val BUFFER = 4096
    private const val TAG = "ZipUtil"

    // 压缩

    fun zip(srcDir: String?, dstDir: String?) {
        if (TextUtils.isEmpty(srcDir) || TextUtils.isEmpty(dstDir)) {
            Log.i(TAG, "srcDir or dstDir is empty. ")
            return
        }
        val srcFile = File(srcDir)
        val dstFile = File(dstDir)
        zip(srcFile, dstFile)
    }

    fun zip(srcFile: File?, dstFile: File?) {
        if (srcFile == null || dstFile == null) {
            Log.i(TAG, "srcFile or dstFile is null. ")
            return
        }
        if (!srcFile.exists()) {
            Log.i(TAG, "srcFile not exists. ")
            return
        }
        val dstPath = dstFile.absolutePath
        val parentDir = File(dstPath.substring(0, dstPath.lastIndexOf(File.separator)))
        if (!parentDir.exists()) {
            parentDir.mkdirs()
        }
        var out: FileOutputStream? = null
        var zipOut: ZipOutputStream? = null
        try {
            out = FileOutputStream(dstFile)
            val cos = CheckedOutputStream(out, CRC32())
            zipOut = ZipOutputStream(cos)
            if (srcFile.isDirectory) {
                val files = srcFile.listFiles()
                if (files == null || files.isEmpty()) {
                    return
                }
                for (i in files.indices) {
                    compressInternal(files[i], zipOut, "")
                }
            } else if (srcFile.isFile) {
                compressInternal(srcFile, zipOut, "")
            }
        } catch (e: Throwable) {
            Log.i(TAG, "zip file has occur exception. srcFile = $srcFile, dstFile = $dstFile, e = $e")
        } finally {
            zipOut?.closeEntry()
            zipOut?.close()
            out?.close()
        }
    }

    @Throws(IOException::class)
    private fun compressInternal(srcFile: File, zipOut: ZipOutputStream, baseDir: String) {
        if (srcFile.isDirectory) {
            compressDirectory(srcFile, zipOut, baseDir)
        } else {
            compressFile(srcFile, zipOut, baseDir)
        }
    }

    /** 压缩一个目录  */
    @Throws(IOException::class)
    private fun compressDirectory(dir: File, zipOut: ZipOutputStream, baseDir: String) {
        val files = dir.listFiles()
        if (files == null || files.isEmpty()) {
            return
        }
        for (i in files.indices) {
            compressInternal(files[i], zipOut, baseDir + dir.name + File.separator)
        }
    }

    /** 压缩一个文件  */
    @Throws(IOException::class)
    private fun compressFile(file: File, zipOut: ZipOutputStream, baseDir: String) {
        if (!file.exists()) {
            return
        }
        val entry = ZipEntry(baseDir + file.name)
        zipOut.putNextEntry(entry)

        file.forEachBlock(BUFFER) { buffer, bytesRead ->
            zipOut.write(buffer, 0, bytesRead)
        }
    }


    // 解压

    fun unzip(zipFile: String, destDir: String) {
        if (TextUtils.isEmpty(zipFile) || TextUtils.isEmpty(destDir)) {
            Log.i(TAG, "zipFile or destDir is empty. ")
            return
        }
        val destTemp = if (destDir.endsWith(File.separator)) destDir else destDir + File.separator
        val zip = File(zipFile)
        val dest = File(destTemp)
        unzip(zip, dest)
    }

    fun unzip(zipFile: File, destDir: File) {
        if (destDir.isFile) {
            throw IOException("destDir must be a Directory.")
        }
        if (!destDir.exists()) {
            destDir.mkdirs()
        }
        try {
            val zip = ZipFile(zipFile)
            zip.forEachUnzip { zipEntry, input ->
                var entryName = zipEntry.name
                if (zipEntry.isDirectory) {
                    entryName = entryName.substring(0, entryName.length - 1)
                    val folder = File(destDir.absolutePath + File.separator + entryName)
                    folder.mkdirs()
                } else {
                    val outputFile = (destDir.absolutePath + File.separator + entryName).replace("\\*".toRegex(), File.separator)
                    //判断路径是否存在,不存在则创建文件路径
                    val parentDir = File(outputFile.substring(0, outputFile.lastIndexOf(File.separator)))
                    if (!parentDir.exists()) {
                        parentDir.mkdirs()
                    }

                    val file = File(destDir.absolutePath + File.separator + entryName)
                    val out = FileOutputStream(file)

                    input.forEachBlock { buffer, bytesRead ->
                        out.write(buffer, 0, bytesRead)
                    }
                    out.flush()
                    out.close()
                }
            }
        } catch (e: Throwable) {
            Log.i(TAG, "unzip file has occur exception. zipFile = $zipFile, destDir = $destDir, e = $e")
        }
    }

    private fun ZipFile.forEachUnzip(action: (zipEntry: ZipEntry, input: InputStream) -> Unit) {
        val entries = entries() ?: return
        do {
            val nextEntry = entries.nextElement()
            if (nextEntry == null) {
                break
            } else {
                action(nextEntry, getInputStream(nextEntry))
            }
        } while (entries.hasMoreElements())
    }

    private fun InputStream.forEachBlock(
        blockSize: Int = BUFFER,
        action: (buffer: ByteArray, bytesRead: Int) -> Unit
    ) {
        val arr = ByteArray(blockSize.coerceAtLeast(512))
        use { input ->
            do {
                val size = input.read(arr)
                if (size <= 0) {
                    break
                } else {
                    action(arr, size)
                }
            } while (true)
        }
    }
}