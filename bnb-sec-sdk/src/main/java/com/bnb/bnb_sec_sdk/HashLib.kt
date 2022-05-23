package com.bnb.bnb_sec_sdk

import android.util.Log
import java.io.*
import java.math.BigInteger
import java.security.MessageDigest

class HashLib {
    companion object {
        const val TAG = "HashLib"

        fun getStringHash(input: String, hashType: HashType): String {
            val algorithm = when (hashType) {
                HashType.MD5 -> "MD5"
                HashType.SHA1 -> "SHA-1"
                HashType.SHA256 -> "SHA-256"
            }

            return MessageDigest
                .getInstance(algorithm)
                .digest(input.toByteArray())
                .fold("") { str, it -> str + "%02x".format(it) }
        }

        fun getFileHash(updateFile: File, hashType: HashType): String? {
            val algorithm = when (hashType) {
                HashType.MD5 -> "MD5"
                HashType.SHA1 -> "SHA-1"
                HashType.SHA256 -> "SHA-256"
            }

            val digest = MessageDigest
                .getInstance(algorithm)

            val inputStream: InputStream?
            try {
                inputStream = FileInputStream(updateFile)
            } catch (e: FileNotFoundException) {
                Log.e(TAG, "Exception while getting FileInputStream", e)
                return null
            }
            val buffer = ByteArray(8192)
            var read: Int
            return try {
                while (inputStream.read(buffer).also { read = it } > 0) {
                    digest.update(buffer, 0, read)
                }
                val sum = digest.digest()
                val bigInt = BigInteger(1, sum)
                var output = bigInt.toString(16)
                // Fill to 32 chars
                output = String.format("%32s", output).replace(' ', '0')
                output
            } catch (e: IOException) {
                throw RuntimeException("Unable to process file for ${hashType.name}", e)
            } finally {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    Log.e(TAG, "Exception on closing ${hashType.name} input stream", e)
                }
            }
        }
    }

    enum class HashType {
        MD5,
        SHA1,
        SHA256
    }
}