package com.nagaraju.dataproviders;

import com.nagaraju.config.JsonReader;
import com.nagaraju.data.PostData;
import org.testng.annotations.DataProvider;

import java.util.List;

public class PostDataProvider {

    @DataProvider(name = "postData")
    public Object[][] getPostData() {
        List<PostData> posts = JsonReader.loadPostData();

        int size = posts.size();
        Object[][] data = new Object[size][1];

        for (int i = 0; i < size; i++) {
            data[i][0] = posts.get(i);
        }

        return data;
    }
}
