package com.nagaraju.dataproviders;

import com.nagaraju.config.JsonReader;
import com.nagaraju.data.UserData;
import org.testng.annotations.DataProvider;

import java.util.List;

public class UserDataProvider {

    @DataProvider(name = "userData")
    public Object[][] getData() {
        List<UserData> users = JsonReader.loadUserData();

        int size = users.size();
        Object[][] data = new Object[size][1];

        for(int i = 0; i < size; i++) {
            data[i][0] = users.get(i);
        }

        return data;
    }
}
