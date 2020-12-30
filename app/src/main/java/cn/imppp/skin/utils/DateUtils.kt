package cn.imppp.skin.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        val DATE_FORMATE_NORMAL = "yyyy-MM-dd HH:mm:ss"
        val DATE_FORMATE_DAY = "yyyy-MM-dd"
        val DATE_FORMATE_DAY2 = "yyyy/MM/dd"
        val DATE_FORMATE_HOUR = "HH:mm"
        val TIME_DEFAULT_FORMAT = "HH:mm:ss"
        val DATE_FORMATE_MIN = "yyyy-MM-dd HH:mm"
        private var sdf: SimpleDateFormat? = null

        fun getCurrentDataDefault(): String {
            sdf = SimpleDateFormat(DATE_FORMATE_NORMAL, Locale.getDefault())
            return sdf!!.format(Date(System.currentTimeMillis()))
        }

        fun getCurrentDataDate(): String {
            sdf = SimpleDateFormat(DATE_FORMATE_DAY2, Locale.getDefault())
            return sdf!!.format(Date(System.currentTimeMillis()))
        }

        fun getCurrentDataDate2(): String? {
            sdf = SimpleDateFormat(TIME_DEFAULT_FORMAT, Locale.getDefault())
            return sdf!!.format(Date(System.currentTimeMillis()))
        }

        fun getCurrentDataChinese(): String? {
            sdf = SimpleDateFormat("MM月dd日", Locale.getDefault())
            return sdf!!.format(Date(System.currentTimeMillis()))
        }

        fun getCurrentHour(): String? {
            val date = Date(System.currentTimeMillis())
            sdf = SimpleDateFormat(DATE_FORMATE_HOUR, Locale.getDefault())
            return sdf!!.format(date)
        }

        /**
         * 获取手机当前时区
         */
        fun getCurrentTimeZone(): String? {
            val tz = TimeZone.getDefault()
            return tz.getDisplayName(false, TimeZone.SHORT)
        }

        fun longToDate(s: String, formate: String?): String? {
            return longToDate(s.toLong(), formate)
        }

        fun longToDate(l: Long, formate: String?): String? {
            var formate = formate
            try {
                val date = Date(l)
                if (formate == null) formate = DATE_FORMATE_NORMAL
                sdf = SimpleDateFormat(formate, Locale.getDefault())
                return sdf!!.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }


        /**
         * @param str
         * @param formate
         * @return
         * @throws Exception
         */
        fun stringToDate(str: String?, formate: String?): Date? {
            try {
                if (str != null && "" != str) {
                    val format: DateFormat = SimpleDateFormat(formate)
                    return format.parse(str)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun getSystemTime(): Long {
            val date = Date()
            return date.time / 1000
        }


        /**
         * @param date
         * @param dateformat
         * @return
         */
        fun dateToString(date: Date?, dateformat: String?): String? {
            val format: DateFormat = SimpleDateFormat(dateformat)
            return format.format(date)
        }

        /**
         * 根据日期获取周几
         *
         * @param date 时间
         * @return 返回周几
         */
        fun getWeekOfDate(date: Date?): String? {
            val weekDays =
                arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
            val cal = Calendar.getInstance()
            cal.time = date
            var w = cal[Calendar.DAY_OF_WEEK] - 1
            if (w < 0) w = 0
            return weekDays[w]
        }
    }
}