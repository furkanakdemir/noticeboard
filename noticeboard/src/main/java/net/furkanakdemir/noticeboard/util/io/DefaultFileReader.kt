package net.furkanakdemir.noticeboard.util.io

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader

internal class DefaultFileReader(val context: Context?) : FileReader {

    init {
        requireNotNull(context) { "Context cannot be null" }
    }
    override fun getFile(filename: String): String? {
        var bufferedReader: BufferedReader? = null
        var data: String? = null

        try {
            val inputStreamReader: Reader? = InputStreamReader(
                context?.assets?.open(filename),
                Charsets.UTF_8
            )
            bufferedReader = BufferedReader(inputStreamReader)

            data = bufferedReader.readLines().joinToString(separator = "\n")
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                bufferedReader?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return data
    }
}
