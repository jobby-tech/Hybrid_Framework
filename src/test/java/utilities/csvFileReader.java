package utilities;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

public class csvFileReader {
    private  static  final String  csvFilePath="./testData/testData.csv";
    @Test
    private static void loadDataFromCsv() {

        try {
            File file = new File(csvFilePath);
            byte[] bytes= FileUtils.readFileToByteArray(file);
            String data = new String(bytes);

            data = StringUtils.replaceAll(data,"\r","");
            String[] dataArray = data.split("\n");

            String keys = dataArray[0];

           Map<String, Map<String ,String >> outerMap = new HashMap<>();

            //System.out.println(dataArray[0]);

            List<String> keysFromFile = new ArrayList<>();
            String[] keyArr =keys.split(",");
            keysFromFile.addAll(Arrays.asList(keyArr));
            keysFromFile.remove(0);


            for(int i=1;i<dataArray.length;i++){
                Map<String ,String > mp = new HashMap<>();
                List<String > row = new ArrayList<>();

                String[] rowArr = dataArray[i].split(",");
                row.addAll(Arrays.asList(rowArr));

                String keyForTestcase = row.get(0);
                row.remove(0);

                for (int j=0;j<keysFromFile.size();j++){
                    mp.put(keysFromFile.get(j).trim(),row.get(j).trim());
                }
                outerMap.put(keyForTestcase,mp);
            }

            System.out.println(outerMap.get("TC_001"));

            Map<String ,String > rowMap = outerMap.get("TC_001");
            System.out.println(rowMap.get("UserName"));


            JSONObject jsonData = new JSONObject(outerMap);
            System.out.println(jsonData);


        }

        catch (Exception e){
            System.out.println(e.getStackTrace());
        }

    }
}
