package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest=(Button) findViewById(R.id.send_request);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.send_request){
                    //sendRequestWithHttpURLConnection();
                    sendRequestWithOkHttp();
                }
            }
        });
    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建一个OkHttpClient实例
                    OkHttpClient client=new OkHttpClient();
                    //发起一条HTTP请求，需要创建一个Request对象，利用url方法设置目标的网络地址
                    //指定访问的服务器地址是电脑本机
                    Request request=new Request.Builder().url("http://10.0.2.2/get_data.json").build();
                    //之后调用OkHttpClient的newCall 方法创建一个Call对象，并调用他的execute 方法来发送请求
                    //并获取服务器返回的数据
                    Response response=client.newCall(request).execute();
                    //Response对象就是服务器返回的数据了 如下写法获取返回的具体内容
                    String responseData =response.body().string();
                    parseJSONWithGSON(responseData);
                    //parseJSONWithJSONObject(responseData);
                    //parseXMLWithSAX(responseData);
                    //parseXMLWithPull(responseData);
                    //showResponse(responseData);
                    //如果是发起一条POST请求，需要构建一个RequestBody 对象来存放提交的参数
                   /* RequestBody requestBody=new FormBody.Builder().add("username","admin").
                            add("password","123456").build();
                    //随后在Request.Builder中调用Post方法，并将RequestBody对象传入
                    Request request1=new Request.Builder().url("http://www.baidu.com")
                            .post(requestBody).build();*/
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData){
        Gson gson= new Gson();
        List<App> apps =gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for(App app:apps){
            Log.d("MainActivity","id is + " + app.getId());
        }
    }

    private void parseJSONWithJSONObject(String jsonData){
        try {
            //将服务器返回的数据传入待一个JSONArray 对象中
            //遍历循环这个对象，取出的每一个元素都是一个JSONObject 对象
            JSONArray jsonArray=new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                String id= jsonObject.getString("id");
                String name=jsonObject.getString("name");
                String version=jsonObject.getString("version");
                Log.d("MainActivity","id is + " + id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void parseXMLWithSAX(String xmlData){
        try {
            SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
            XMLReader xmlReader=saxParserFactory.newSAXParser().getXMLReader();
            MyHandler handler=new MyHandler();
            //将ContenteHandler的实例设置到 XML对象中
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    private void parseXMLWithPull(String xmlData){
        try {
            //借助XmlPullParserFactory实例获取XmlPullParser 对象
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=factory.newPullParser();
            //利用xmlPullParser.setInput 对XML数据进行解析
            xmlPullParser.setInput(new StringReader(xmlData));
            //getEventType 得到当前解析事件
            int eventType =xmlPullParser.getEventType();
            String id="";
            String name="";
            String version="";
            while (eventType!=xmlPullParser.END_DOCUMENT){
                String nodeName =xmlPullParser.getName();
                switch (eventType){
                    //开始解析某个节点
                    case XmlPullParser.START_TAG:{
                        if("id".equals(nodeName)){
                            id=xmlPullParser.nextText();
                        }else if("name".equals(nodeName)){
                            name=xmlPullParser.nextText();
                        }else if("version".equals(nodeName)){
                            version =xmlPullParser.nextText();
                        }
                        break;
                    }
                    //完成解析某个节点
                         case XmlPullParser.END_TAG:{
                             if("app".equals(nodeName)){
                                 Log.d("MainActivity",id);
                             }
                             break;
                         }
                    default:
                        break;
                }
                eventType=xmlPullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void sendRequestWithHttpURLConnection(){
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try {
                    URL url= new URL("http://www.baidu.com");
                    connection =(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in =connection.getInputStream();
                    //利用BufferedReader对获取的输入流进行读取，并将结果传入到showResponse
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    /*
                    * 提交数据给服务器 将HTTP请求码改为POST 并在获取输入流之前把要提交的数据写出即可
                    * connection.setRequestMethod("POST");
                    * 每条数据以键值对的形式存在，数据与数据之间用&隔开
                    *  DataOutputStream out=new DataOutputStream(connection.getOutputStream());
                    * out.writeBytes("username=admin&password=123456");
                    * */
                    DataOutputStream out=new DataOutputStream(connection.getOutputStream());
                    out.writeBytes("username=admin&password=123456");
                    String line;
                    while((line= reader.readLine())!=null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    private void  showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //这里面进行UI操作，将结果显示到界面上
                //android 不允许在子线程中进行UI操作，需要通过这个方法将线程切换到主线程，然后更新UI元素
                responseText =(TextView)findViewById(R.id.response_text);
                responseText.setText(response);
                System.out.println(responseText);
            }
        });
    }
}