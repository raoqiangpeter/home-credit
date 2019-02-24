package com.raoqiang.homecredit;


import com.alibaba.fastjson.JSON;
import com.raoqiang.homecredit.calculate.Calculate;
import com.raoqiang.homecredit.calculate.GetValueFromMap;
import com.raoqiang.homecredit.calculate.constant.DropIndex;
import com.raoqiang.homecredit.entry.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlowTest {

//    @Autowired
//    private TableOperator tableOperator;

    @Test
    public void labelCalculate() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        File file = new File("E:\\workspace\\spark-study\\home-credit\\src\\main\\resources\\application.json");
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        String rs = "";
//        int a;
        while ((is.read(bytes))>0){
            rs += new String(bytes);
            bytes=new byte[1024];
        }
//        System.out.println(rs);
        Application application = JSON.parseObject(rs, Application.class);
        System.out.println(application);
        Map map = application.getData();

        // -----------------------------------------------------------------------------
        // 处理 application_train/test
        String[] strs = new String[]{"DaysEmployed", "ExtSourcesMax", "ExtSourcesMean", "ExtSourcesMin", "NewDocKurt",
                "ExtSourcesProd", "ExtSourcesWeighted", "AgeRange", "DocumentCount", "CreditToAnnuityRatio",
                "CreditToGoodsRatio", "AnnuityToIncomeRatio", "CreditToIncomeRatio", "IncomeToEmployedRatio",
                "EmployedToBirthRatio", "IdToBirthRatio", "CarToBirthRatio", "CarToEmployedRatio", "PhoneToBirthRatio",
                "CodeGender", "EmergencystateMode", "FlagOwnCar", "FlagOwnRealty", "FondkapremontMode", "HousetypeMode",
                "NameContractType", "NameEducationType", "NameFamilyStatus", "NameHousingType", "NameIncomeType",
                "NameTypeSuite", "OccupationType", "OrganizationType", "WallsmaterialMode", "WeekdayApprProcessStart"

        };
        Class<?> clazz;
        for(String s: strs){
            clazz = Class.forName("com.raoqiang.homecredit.calculate.application."+s);
            Calculate calculate = (Calculate) clazz.newInstance();
            calculate.labelCalculate(map);
        }
        GetValueFromMap.removeKeysFromMap(map, DropIndex.APPLICATION_DROP_INDEX);

        System.out.println(application);



    }
}
