package qianfeng.a7_1neteasenews;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            MyAdapter adapter = new MyAdapter(mList,MainActivity.this);
            lv.setAdapter(adapter);
        }
    };

    private List<NewsBean> mList;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = ((ListView) findViewById(R.id.lv));


        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection con = null;
                try {
                    URL url = new URL("http://c.m.163.com/nc/article/headline/T1348647853363/0-20.html");
                    con = (HttpURLConnection) url.openConnection();
                    con.setConnectTimeout(5 * 1000);
                    con.connect();
                    BufferedReader bf = null;
                    String str = null;
                    StringBuffer buffer = new StringBuffer();

                    if (con.getResponseCode() == 200) {
                        bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        while ((str = bf.readLine()) != null) {
                            buffer.append(str);
                        }
                        parseJSON(buffer.toString());
                        Log.d("google-my:", "run: JSON---->" + buffer.toString());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }

    private void parseJSON(String s) {

        mList = new ArrayList<>();
        // json解析
        try {
            JSONObject jo = new JSONObject(s);
            JSONArray t1348647853363 = jo.getJSONArray("T1348647853363");
            for (int i = 0; i < t1348647853363.length(); i++) {
                JSONObject data = t1348647853363.getJSONObject(i);
                if(data.has("ads"))
                {
                    JSONArray ads = data.getJSONArray("ads");
                    String[] adsArray = new String[ads.length()];
//                    String[] titleArray = new String[ads.length()];
                    for (int j = 0; j < ads.length(); j++) {
//                       titleArray[j] = ads.getJSONObject(j).getString("title");
                       adsArray[j] = ads.getJSONObject(j).getString("imgsrc");

                    }

                    NewsBean newsBean = new NewsBean();
//                        newsBean.setTitles(titleArray);
                    newsBean.setImgextra(adsArray);
                    newsBean.setType(0);
                    mList.add(newsBean);
                }else if(data.has("imgextra"))
                {
                    JSONArray imgextra = data.getJSONArray("imgextra");
                    String[] imgArray = new String[imgextra.length()];
                    for (int j = 0; j < imgextra.length(); j++) {
                        imgArray[j] = imgextra.getJSONObject(j).getString("imgsrc");
                    }

                    String title = data.getString("title");
                    String imgsrc = data.getString("imgsrc");

                    NewsBean newsBean = new NewsBean();
                    newsBean.setImgextra(imgArray);
                    newsBean.setTitle(title);
                    newsBean.setImgsrc(imgsrc);
                    newsBean.setType(1);
                    mList.add(newsBean);

                }else
                {
                    String title = data.getString("title");
                    String imgsrc = data.getString("imgsrc");
                    NewsBean newsBean = new NewsBean();
                    newsBean.setImgsrc(imgsrc);
                    newsBean.setType(2);
                    newsBean.setTitle(title);
                    mList.add(newsBean);
                }
            }

            mHandler.sendEmptyMessage(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
}
