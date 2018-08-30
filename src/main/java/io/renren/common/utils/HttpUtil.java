package io.renren.common.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

import io.renren.modules.bj.bjEntity.BjUser;
import io.renren.modules.bj.controller.ApiController;
import io.renren.modules.bj.entity.JqbEntity;
import io.renren.modules.bj.util.Http;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @author 马弦
 * @date 2017年10月23日 下午2:49
 * HttpClient工具类
 */
public class HttpUtil {


    /**
     * get请求
     * @return
     */
    public static String doGet(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());

                return strResult;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * get请求
     * @return
     */
    public static String doGet1(String url,String token) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization","Bearer " +token);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());

                return strResult;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * post请求(用于key-value格式的参数)
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map params){

        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));

                //System.out.println(name +"-"+value);
            }
            request.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(code == 200){	//请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(),"utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();

                return sb.toString();
            }
            else{	//
                System.out.println("状态码：" + code);
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();

            return null;
        }
    }

    /**
     * post请求(用于key-value格式的参数)
     * @param url
     * @param
     * @return
     */
    public static String doPost2(String url, String json,String token){

        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setHeader("Authorization","Bearer " +token);
            //request.setHeader("Content-Type","application/json");
            request.setURI(new URI(url));
            //设置参数
            /*List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));

                //System.out.println(name +"-"+value);
            }*/
            StringEntity myEntity = new StringEntity(json, ContentType.APPLICATION_JSON);// 构造请求数据
            request.setEntity(myEntity);
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(code == 201 || code == 200){	//请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(),"utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();

                return sb.toString();
            }
            else{	//
                System.out.println("状态码：" + code);
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * put请求(用于key-value格式的参数)
     * @param url
     * @param
     * @return
     */
    public static String doPut(String url, String json,String token){

        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpPut request = new HttpPut();
            request.setHeader("Authorization","Bearer " +token);
            //request.setHeader("Content-Type","application/json");
            request.setURI(new URI(url));
            //设置参数
            /*List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));

                //System.out.println(name +"-"+value);
            }*/
            StringEntity myEntity = new StringEntity(json, ContentType.APPLICATION_JSON);// 构造请求数据
            request.setEntity(myEntity);
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(code == 204){	//请求成功
                /*in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(),"utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();*/

                return null;
            }
            else{	//
                System.out.println("状态码：" + code);
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * post请求（用于请求json格式的参数）
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params,String token) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization","Bearer " +token);
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
                System.out.print("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    /**
     * post1请求（用于请求json格式的参数）
     * @param url
     * @param params
     * @return
     */
    public static String doPost1(String url, String params,String token) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization","Bearer " +token);
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
                System.out.print("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * post1请求（用于请求json格式的参数）
     * @param url
     * @param params
     * @return
     */
    public static String doPost1(String url, Map params,String token) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization","Bearer " +token);
        String charSet = "UTF-8";
        //设置参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String value = String.valueOf(params.get(name));
            nvps.add(new BasicNameValuePair(name, value));
            //System.out.println(name +"-"+value);
        }
        //httpPost.setEntity(entity);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
                System.out.print("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args){
        /*Map<String,Object> map = new HashMap<>();
        map.put("username","test001@liujingwei.cn");
        map.put("password","Admin@001");
        map.put("grant_type","password");
        String post = HttpUtil.doPost(Http.token,map);
        System.out.print("============================================");
        System.out.print(post);
        System.out.print("============================================");
        JSONObject json = JSONObject.fromObject(post);
        if (json != null) {
            BjUser bjUser =new BjUser();
            bjUser.setAccess_token(json.get("access_token").toString());
            bjUser.setToken_type(json.get("token_type").toString());
            bjUser.setUserName(json.get("userName").toString());
            bjUser.setExpires_in(Integer.parseInt(json.get("expires_in").toString()));
        }*/
        String token = "afsAR2h-yesWCZFnyYtNdTwN4sPjfaZS6Fzq-qcl3EL80y_hO8JrYQcNrFb3G7iZ9W5s9CtGO3awa2cYN_LiL9wuAItS_SmNH5jZ5cu3JW_Ua7FHXgte_ob1vF9lhzJh1gnJqQ9juZzCsGKThLCADqVE6gJEif7htXpLS9vmEHdGaOUkHhLYMrLNBZiNBnX7d8xKqsTTSM4uvMHXBBS8AKor96z6N3ZDuaQQwCS8EU1TXIlX7RqSLCJuLWEHJpQ-V0QuBb1Sr2b_b9n5BuTSsfJbPfhjT_dkXWCO--PeJ4HBhzw49MnHEl8SlmH1zQsH4YSM7Yift3AtywNj50A7aLqnDsyae-ruidYaejya0riGfbgIpT5V7mlMbM5fTvtKg5kCt1uhZUUdGUrDKunP15JaDyR-oVsBv2aQjxp4WF8sCPOjs8ccCrLdxXG6r5UARE4SIJ9QDPZ80CJ7C49lsJL9lggeUVgP5xNnI4Y66R055f1yof54ibU9WonbBcxX";
        JqbEntity jqbEntity = new JqbEntity();
        ApiController apiController = new ApiController();

        jqbEntity.setSjmc("娱乐西街着火了");
        jqbEntity.setSjnr("麻辣烫店煤气BOOM，起火了");
        jqbEntity.setJjr("test001@liujingwei.cn");
        jqbEntity.setStatus(0);
        jqbEntity.setLng(120.655946);
        jqbEntity.setLat(27.969866);
        jqbEntity.setJjcd(1);
        jqbEntity.setFhcphsl("1");
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jqbEntity.setCreateTime("2018-08-08T08:08:00");
        // String post1 = HttpUtil.doGet1(Http.MyData001,token);
        //apiController.xrJq(jqbEntity,token);

        jqbEntity.setBjId(27);
        apiController.gxJq(jqbEntity,token);





    }

}
