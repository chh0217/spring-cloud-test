package app;

import com.google.common.collect.Lists;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * @author chenhang
 * @date 2018/8/16 下午5:00
 * @description
 */
public class JodaTimeTest {

    public static void main(String[] args) {
        DateTime start = new DateTime(2018, 8, 20, 5, 0, 0);
        DateTime end = new DateTime(2018, 8, 24, 4, 0, 0);
        System.out.println(Days.daysBetween(start,end).getDays());

        System.out.println(start.getHourOfDay());
        System.out.println(start.getMinuteOfDay());
        System.out.println(start.getMinuteOfHour());

        List<Integer> list = Lists.newArrayList();
        list.add(null);
        list.add(null);
        if(CollectionUtils.isNotEmpty(list)){
            System.out.println("hahahah");
        }
    }

}
