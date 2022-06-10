package com.bnb.bnb_sec_sdk

import android.content.Context
import android.util.Log
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.StandardCharsets

class EncryptionLib {
    /**
     * Encryption lib is a wrap around jetpack security to provide content encryption saved to a file.
     * */
    companion object {
        const val TAG = "EncryptionLib"

        private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        private val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        /**
         * @param context Context is required when building the EncryptedFile
         * @param contentToEncrypt string that encrypted and added to the file content after encryption.
         * @param fileName the file name that contains the encrypted content in string format. The file will be saved to default directory accessible via Context.filesDir
         *
         * @return returns EncryptionStatus enum that can be used by the caller to determine status of the encryption.
         * */
        fun encryptContentToFile(
            context: Context,
            contentToEncrypt: String,
            fileName: String
        ): EncrpytionStatus {
            val file = File(context.filesDir, fileName)
            Log.d(TAG, file.absolutePath)

            if (file.exists()) {
                /**
                 * file is already exist - return a status enum.
                 * */
                return EncrpytionStatus.ENCRYPT_WRITE_OPERATION_FAILED_FILE_ALREADY_EXIST
            }

            val encryptedFile = EncryptedFile.Builder(
                file,
                context,
                mainKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            /**
             * writes the content into the directory.
             * */
            val fileContent = contentToEncrypt
                .toByteArray(StandardCharsets.UTF_8)

            encryptedFile.openFileOutput().apply {
                write(fileContent)
                flush()
                close()
            }

            return EncrpytionStatus.ENCRYPT_WRITE_OPERATION_SUCCESS
        }

        fun readEncryptedFile(context: Context, encryptedFileName: String): File {
            val fileToRead = File(context.filesDir, encryptedFileName)
            if (!fileToRead.exists()) {
                /**
                 * File not found - throws exception.
                 * */
                throw FileNotFoundException()
            }

            val encryptedFile = EncryptedFile.Builder(
                fileToRead,
                context,
                mainKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            val fileInputStream = encryptedFile.openFileInput()
            val decryptedFile = File(context.filesDir, "decrypted_$encryptedFileName")

            try {
                val fos = FileOutputStream(decryptedFile)
                val buffer = ByteArray(1024)
                var len1 = 0
                while (fileInputStream.read(buffer).also { len1 = it } > 0) {
                    fos.write(buffer, 0, len1)
                }

                fileInputStream.close()
                fos.close()
            } catch (e: FileNotFoundException) {
                throw FileNotFoundException()
            } catch (e: IOException) {
                /**
                 * if decryption fails - ioexception will be thrown.
                 * */
                throw IOException()
            }

            return decryptedFile
        }
    }

    enum class EncrpytionStatus {
        ENCRYPT_WRITE_OPERATION_SUCCESS,
        ENCRYPT_WRITE_OPERATION_FAILED_FILE_ALREADY_EXIST,
    }
}