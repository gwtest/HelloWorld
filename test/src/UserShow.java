import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import cn.sina.api.commons.util.ApacheHttpClient;
import cn.sina.api.commons.util.ApiHttpClient;

public class UserShow {
	public static void main(String[] args) {
		ApiHttpClient httpclient = new ApacheHttpClient();
        String showurl = "http://i.api.weibo.com/2/users/show.json";
        BufferedReader reader;
        ArrayList<String> uidlist =  new ArrayList<>(); 
        //测试分支
		try {
		reader = new BufferedReader(new FileReader("/data1/guowu/tmp"));
        String line = reader.readLine();
        while (line != null) {	
            	StringBuffer sb = new StringBuffer();
                sb.append("source=").append("2975945008").append('&');
                sb.append("uid=").append(line);
    	        String url = String.format("%s?%s", showurl, sb.toString());
    	        String rst = httpclient.get(url);
    	        JsonWrapper jw = new JsonWrapper(rst);
    	        if (jw.getString("error") != null) {
    	        	uidlist.add(line);
                }
    	        line = reader.readLine();
            }
         reader.close(); 
        }catch(Exception e){
        }
        for(String uid: uidlist){
        	System.out.println(uid);
        }
		System.exit(0);
	}
}
