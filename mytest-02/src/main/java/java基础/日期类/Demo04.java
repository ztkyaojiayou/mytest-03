package java基础.日期类;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2022-02-11 16:53
 * @Version: 1.0.0
 */


import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

///**
// * 1.按时间段返回每个交易类型的汇总 历史部分
// */
//@VariableDataSource(value = DbType.MASTER, handler = PMSVariableDataSourceHandler.class)
//public RestResponse<GetTranTypAmountResponseDTO> getTranTypAmountByDate(GetTranTypAmountRequestDTO request, DataSourceParam dataSourceParam) {
//
//        //region 获取明细数据
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String startStr = formatter.format(request.getStartDate().toDate());
//        String endStr = formatter.format(request.getEndDate().toDate());
//        List<BusinessData> businessDataList = businessOvertDataMapper.getTranTypAmountByDate(startStr, endStr, request.getHotelCode());

/**
 * 2.testDemo
 */
public class Demo04 {
    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        Date date = dateTime.toDate();
        String s = dateTime.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        System.out.println(format);
    }


    /**
     * 3.日期比较（且当日期首先为字符串时）
     *///

    private void testDateCompare2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2009-12-31");
        Date date2 = sdf.parse("2019-01-31");

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        if (date1.after(date2)) {
            System.out.println("Date1 时间在 Date2 之后/晚/大");
        }

        if (date1.before(date2)) {
            System.out.println("Date1 时间在 Date2 之前/早/小");
        }

        if (date1.equals(date2)) {
            System.out.println("Date1 时间与 Date2 相等");
        }
    }
}
////输出结果
//date1 : 2009-12-31
//        date2 : 2019-01-31
//        Date1 时间在 Date2 之前/早/小