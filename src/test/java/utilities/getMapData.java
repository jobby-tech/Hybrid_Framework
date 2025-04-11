package utilities;

import org.testng.annotations.Test;

import java.util.Map;

public class getMapData {

    @Test
    public void test1(){

        try {
            Map<String,String> testData = utilities.excelHashMap.getMapData();

            for (Map.Entry<String,String> map:testData.entrySet()){

                System.out.println("Key " + map.getKey() +" Value "+map.getValue());
            }
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
