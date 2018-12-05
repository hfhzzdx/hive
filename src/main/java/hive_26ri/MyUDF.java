package hive_26ri;


import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class MyUDF extends UDF {
    /**
     * 天通苑北一区 3室2厅 510万	name
     * 1.01101E+11	                link
     * 天通苑北一区	                regin
     * 3-2厅                          huxing
     * 143.09 平米	                area
     * 南北	                        chaoxiang
     * 简装                          zhuangxiu
     * 有电梯	                     dianti
     * 35642	                      danprice
     * 510                           sumprice
     */
    public Text evaluate(final Text name,final Text region,final Text huxing,
                         final Text area,final Text chaoxiang,final Text zhuangxiu,
                         final Text dianti,final Float danPrice,final Text sumPrice){
        String[] split = name.toString().split(" ");
        //拼装新的name
        String newName = split[0]+"[";
        for(int i =0;i<split.length;i++){
            newName += split[i]+" ";
        }
        newName += "]";
        //将户型中的 - 替换成室
        String newHuxing = huxing.toString().replace("-", "室");
        String newArea = area.toString().trim();
        String newDetail = chaoxiang.toString() + "-" + zhuangxiu.toString() + "-" + dianti.toString();
        String newSumPrice = sumPrice.toString() + "万";
        return new Text(newName+"	"+region.toString()+"	"
                +newHuxing+" "+newArea+" "+newDetail+" "+danPrice.floatValue()+" "+newSumPrice);

    }
}
