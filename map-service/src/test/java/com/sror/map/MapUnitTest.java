package com.sror.map;

import com.sror.map.common.AddressUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MapUnitTest {


    @Test
    public void checkGeneratedListOfAddresses() {
        AddressUtility.getListOfAddress().forEach(System.out::println);
    }

}
