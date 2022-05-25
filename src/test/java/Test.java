import com.hxcy.ocr.entity.Vmware;
import com.hxcy.ocr.utils.PropertiesUtil;

import java.time.LocalDate;

/**
 * @author kevin
 * @date 2022/5/24
 * @desc
 */
public class Test {
    public static void main(String[] args) {
        /*String path = "D:/Users/13090/Desktop/application.properties";
        //("image.path")
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        propertiesUtil.readProperties(path);
        System.out.println(propertiesUtil.get(("image.path")));*/
        /*String str = "GST 35.37\n" +
                "GMT 20\n" +
                "BNB 0";*/
        String str ="GST 35.37\n" +
                "GMT 20\n" +
                "BNB 00M EE LEE MEE I I\n" +
                "0ut 0f the app 0r get a new device .\n" +
                "1 unusual\n" +
                "2 turtle\n" +
                "3 ten\n" +
                "4 split";
        String[] money = str.split("\n");
        Vmware vmware = new Vmware("vmid", 20011L, Double.parseDouble(money[0].split(" ")[1]), Double.parseDouble(money[1].split(" ")[1]), money[2].contains("BNB")? Double.parseDouble(money[2].split(" ")[1]):0D,  money[2].contains("SOL")? Double.parseDouble(money[2].split(" ")[1]):0D, LocalDate.now().toString());
        System.out.println(vmware);
    }
}
