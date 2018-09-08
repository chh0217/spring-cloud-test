package app;

import static com.cronutils.model.CronType.QUARTZ;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronConstraintsFactory;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chenhang
 * @date 2018/8/3 下午2:26
 * @description
 */
public class CronTest {

    public static void main(String[] args) {
        String cron = "0 30 1 * * ?";
        System.out.println(getTimeByCron(cron));
        String time = "03:02";
        System.out.println(getCronByTime(time));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        System.out.println(dealChannel(list));

        List<Integer> alist = new ArrayList<>();
        alist.add(null);
        System.out.println(CollectionUtils.isNotEmpty(alist));
        System.out.println(alist.size());
        dealChannel(new ArrayList<>());
        System.out.println(dealChannel(7));
        System.out.println(dealChannel(5));
    }

    private static Integer dealChannel(List<Integer> channelList) {
        Integer result = 0;
        for (Integer i : channelList) {
            result = result | i;
        }
        return 0 != result ? result : null;
    }

    private static List<Integer> dealChannel(Integer channel){
        List<Integer> channelList = new ArrayList<>();
        Integer[] codeArr = {1,2,4};
        for (Integer code : codeArr) {
            if(code.equals(code & channel)){
                channelList.add(code);
            }
        }
        return channelList;
    }

    private static String getTimeByCron(String cron) {
        final String replaced = cron.replaceAll("\\s+", " ").trim();
        if (StringUtils.isEmpty(replaced)) {
            throw new IllegalArgumentException("Empty expression!");
        }
        final String[] expressionParts = replaced.toUpperCase().split(" ");
        String minute = expressionParts[1];
        String hour = expressionParts[2];
        return String.format("%s:%s", hour, minute);
    }

    private static String getCronByTime(String time) {
        final String replaced = time.replaceAll("\\s+", " ").trim();
        if (StringUtils.isEmpty(replaced)) {
            throw new IllegalArgumentException("Empty time!");
        }
        final String[] expressionParts = replaced.toUpperCase().split(":");
        Integer hour = Integer.valueOf(expressionParts[0]);
        Integer minute = Integer.valueOf(expressionParts[1]);
        return String.format("%s %s %s %s %s %s", "0", minute, hour, "*", "*", "?");
    }

    private static CronDefinition defineOwnCronDefinition() {
        //define your own cron: arbitrary fields are allowed and last field can be optional
        return CronDefinitionBuilder.defineCron()
            .withSeconds().and()
            .withMinutes().and()
            .withHours().and()
            .withDayOfMonth()
            .supportsHash().supportsL().supportsW().and()
            .withMonth().and()
            .withDayOfWeek()
            .withIntMapping(7, 0) //we support non-standard non-zero-based numbers!
            .supportsHash().supportsL().supportsW().and()
            .withYear().optional().and()
            .instance();
    }

    private static void howToWithCronDefinitions() {
        // Define your own cron: arbitrary fields are allowed and last field can be optional
        CronDefinition cronDefinition1 =
            CronDefinitionBuilder.defineCron()
                .withSeconds().and()
                .withMinutes().and()
                .withHours().and()
                .withDayOfMonth().supportsL().supportsW().supportsLW().supportsQuestionMark().and()
                .withMonth().and()
                .withDayOfWeek().withValidRange(1, 7).withMondayDoWValue(2).supportsHash().supportsL()
                .supportsQuestionMark().and()
                .withYear().withValidRange(1970, 2099).optional().and()
                .withCronValidation(CronConstraintsFactory.ensureEitherDayOfWeekOrDayOfMonth())
                .instance();

        // or get a predefined instance
        CronDefinition cronDefinition2 = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);

        System.out
            .println(String.format("Are those definitions the same? %s", cronDefinition1.equals(cronDefinition2)));
        //now you can use it to build a parser or to programatically build cron expressions!
    }

    private static void howToWithCronParser() {
        //once we created a cron definition, we can use it to build a parser
        CronDefinition cronDefinition;
        cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
        CronParser parser = new CronParser(cronDefinition);
        String expression = "0 23 * ? * 1-5 *";
        Cron quartzCron = parser.parse(expression);

        System.out.println(String
            .format("Was the parsing correct? Lets turn it to string again! original: '%s' parsed: '%s'", expression,
                quartzCron.asString()));

        /* We can even parse multi-crons!
         * How about squashing multiple crons into a single line?
         * Instead of writting "0 0 9 * * ? *", "0 0 10 * * ? *", "0 30 11 * * ? *" and "0 0 12 * * ? *"
         * we can wrap it into "0 0|0|30|0 9|10|11|12 * * ? *"
         */
        String multicron = "0 0|0|30|0 9|10|11|12 * * ? *";
        Cron cron = parser.parse(multicron);
        System.out.println(String.format("Are those the same? %s", multicron.equals(cron.asString())));
    }

    private static void howToHumanReadableDescriptions() {
        //we first need to setup a parser
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
        CronParser parser = new CronParser(cronDefinition);
        String expression = "0 23 * ? * 1-5 *";

        //and then just ask for a description
        CronDescriptor descriptor = CronDescriptor
            .instance(Locale.SIMPLIFIED_CHINESE);//we support multiple languages! Just pick one!
        String quartzBuiltCronExpressionDescription = descriptor.describe(parser.parse(expression));
        System.out.println(
            String
                .format("Quartz expression '%s' is described as '%s'", expression, quartzBuiltCronExpressionDescription)
        );
    }

}
