package net.furkanakdemir.noticeboard.util.io

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

internal class DefaultFileReader @Inject constructor(val context: Context) : FileReader {
    override fun getFile(filename: String): String? {
        var bufferedReader: BufferedReader? = null
        var data: String? = null

        try {
            val inputStreamReader = InputStreamReader(
                context.assets?.open(filename),
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
