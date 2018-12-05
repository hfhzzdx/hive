package RandomPhoneLog;

import com.google.common.primitives.Bytes;
import javafx.scene.shape.Path;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 随机数据
 * 字段: call_phone   call_name   callee_phone  callee_name   start_time   call_long
 */

public class PhoneLog {


    public static ArrayList<Long> getPhoneList() {

        ArrayList<Long> phoneNumberList = new ArrayList<Long>();
        phoneNumberList.add(17755364600l);
        phoneNumberList.add(13288940364l);
        phoneNumberList.add(16991082028l);
        phoneNumberList.add(14692570569l);
        phoneNumberList.add(15644668027l);
        phoneNumberList.add(18401456522l);
        phoneNumberList.add(19641660102l);
        phoneNumberList.add(18462123954l);
        phoneNumberList.add(18491428393l);
        phoneNumberList.add(15142556083l);
        phoneNumberList.add(18291298080l);
        phoneNumberList.add(16911414502l);
        phoneNumberList.add(14385342683l);
        phoneNumberList.add(14865818526l);
        phoneNumberList.add(18506948961l);
        phoneNumberList.add(13113007783l);
        phoneNumberList.add(15093813308l);
        phoneNumberList.add(16125422921l);
        phoneNumberList.add(17445954339l);
        phoneNumberList.add(14361606522l);
        phoneNumberList.add(13059125383l);
        phoneNumberList.add(19920594188l);
        phoneNumberList.add(13658626467l);
        phoneNumberList.add(15785008965l);
        phoneNumberList.add(13305040991l);
        phoneNumberList.add(17533432302l);
        phoneNumberList.add(15060932038l);

        return phoneNumberList;
    }

    public static ArrayList<String> getNameList() {
        ArrayList<String> nameList = new ArrayList<String>();
        nameList.add("霍风浩");
        nameList.add("贾鑫瑜");
        nameList.add("余建堂");
        nameList.add("陈猛");
        nameList.add("王倩");
        nameList.add("杨占昊");
        nameList.add("刘洋");
        nameList.add("李伟");
        nameList.add("张苗");
        nameList.add("赵晓露");
        nameList.add("杨青林");
        nameList.add("孙凯迪");
        nameList.add("陈凯");
        nameList.add("常天罡");
        nameList.add("冀缨菲");
        nameList.add("孙良明");
        nameList.add("贾明灿");
        nameList.add("陈鑫");
        nameList.add("张文豪");
        nameList.add("刘优");
        nameList.add("郭振君");
        nameList.add("段雪鹏");
        nameList.add("刘海涛");
        nameList.add("董润华");
        nameList.add("高永斌");
        nameList.add("张文举");
        nameList.add("闵强");

        return nameList;
    }

    public static HashMap<Long, String> getNameMap() {
        HashMap<Long, String> phoneAndNameMap = new HashMap<Long, String>();
        ArrayList<Long> phoneList = getPhoneList();
        ArrayList<String> nameList = getNameList();
        for (int i = 0; i < phoneList.size(); i++) {
            phoneAndNameMap.put(phoneList.get(i), nameList.get(i));
        }
        return phoneAndNameMap;
    }


    public static void main(String[] args) {
        /**
         * list 存手机号
         * map  list与姓名
         * 数据已经有了  现在开始打电话
         */
        HashMap<Long, String> poneAndName = getNameMap();
        ArrayList<Long> phones = getPhoneList();
        ArrayList<String> names = getNameList();
        boolean flag = true;
        while (flag) {
            Random random = new Random();
            //随机取28以内的随机数
            int i = random.nextInt(27);
            int j = random.nextInt(27);
            Long firstPhone = phones.get(i);
            String firstName = names.get(i);
            Long secondPhone = phones.get(j);
            String secondName = names.get(j);

//        System.out.println(i);
            //判断  如果是同一个人  跳出本次循环

            String longsName = poneAndName.get(firstPhone);
            String logsNames = poneAndName.get(secondPhone);
            //判断是否是通一个人
            if (!logsNames.equals(longsName)) {
                //call_phone   call_name   callee_phone  callee_name   start_time   call_long
                String res = firstPhone + "\t" + firstName + "\t" + secondPhone + "\t" + secondName;
//                System.out.println(res);
                //  16125422921	陈鑫	13059125383	郭振君
                Random time = new Random();
                long l = time.nextLong();
                if(l>0){
                    Date date = new Date(l);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                    String start_time = simpleDateFormat.format(date);
                    String times = String.valueOf(l);
                    String finalTime = times.substring(0, 4);
                    Long aLong = Long.valueOf(finalTime);
                    if(aLong<=7200){
                        DecimalFormat decimalFormat = new DecimalFormat("####");
                        String call_long = decimalFormat.format(aLong);
                        res += "\t" + start_time+"\t"+call_long+"\n";
                        System.out.println(res);
                        try {
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(new File(args[0]),true);
                                byte[] bytes = res.getBytes();
                                int len = -1;

                                    try {
                                        fileOutputStream.write(bytes);
                                        fileOutputStream.flush();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }

            }

      //处理start_time 和 call_long


        }
    }
}

