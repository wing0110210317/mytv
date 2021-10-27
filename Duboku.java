package com.github.catvod.spider; 
 
import android.content.Context; 
import android.text.TextUtils; 
import com.github.catvod.crawler.Spider; 
import com.github.catvod.crawler.SpiderDebug; 
import com.github.catvod.crawler.SpiderReq; 
import com.github.catvod.crawler.SpiderUrl; 
import com.github.catvod.spider.merge.DM; 
import com.github.catvod.spider.merge.Ey; 
import com.github.catvod.spider.merge.f; 
import com.github.catvod.spider.merge.y; 
import java.net.URLEncoder; 
import java.util.ArrayList; 
import java.util.Comparator; 
import java.util.HashMap; 
import java.util.List; 
import java.util.TreeMap; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import org.json.JSONArray; 
import org.json.JSONException; 
import org.json.JSONObject; 
 
public class Duboku extends Spider { 
    private JSONObject Be; 
    private Pattern NE = Pattern.compile("/voddetail/(\\w+).html"); 
    private Pattern PV = Pattern.compile("/vodplay/(\\w+)-(\\d+)-(\\d+).html"); 
    private JSONObject bQ; 
    private Pattern jB = Pattern.compile("/v/(\\w+).html"); 
    private Pattern k = Pattern.compile("/show/(\\S+).html"); 
 
    /* Access modifiers changed, original: protected */ 
    public HashMap<String, String> bQ(String str) { 
        HashMap hashMap = new HashMap(); 
        hashMap.put("method", "GET"); 
        hashMap.put("Host", "ca.duboku.fun"); 
        String str2 = "1"; 
        hashMap.put("Upgrade-Insecure-Requests", str2); 
        hashMap.put("DNT", str2); 
        hashMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36"); 
        hashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"); 
        hashMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"); 
        return hashMap; 
    } 
 
    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) { 
        return ""; 
    } 
 
    public String detailContent(List<String> list) { 
        Throwable e; 
        List<String> list2 = list; 
        String str = "-"; 
        String str2 = "$$$"; 
        String str3 = ""; 
        String str4; 
        try { 
            JSONObject jSONObject; 
            String str5; 
            String str6; 
            Ey eK; 
            int i; 
            StringBuilder stringBuilder = new StringBuilder(); 
            stringBuilder.append("https://ca.duboku.fun/voddetail/"); 
            stringBuilder.append((String) list2.get(0)); 
            stringBuilder.append(".html"); 
            String stringBuilder2 = stringBuilder.toString(); 
            y Be = f.Be(SpiderReq.get(new SpiderUrl(stringBuilder2, bQ(stringBuilder2))).content); 
            JSONObject jSONObject2 = new JSONObject(); 
            JSONObject jSONObject3 = new JSONObject(); 
            String jB = Be.Gp("div.myui-content__thumb a.myui-vodlist__thumb img").jB("data-original"); 
            String h = Be.Gp("div.myui-content__detail h1.title").h(); 
            String h2 = Be.Gp("div#desc span.sketch").h(); 
            ArrayList eK2 = Be.eK("div.myui-content__detail span"); 
            Object obj = str3; 
            Object obj2 = obj; 
            String str7 = obj2; 
            String str8 = str7; 
            String str9 = str8; 
            String str10 = str9; 
            int i2 = 0; 
            while (i2 < eK2.size()) { 
                DM dm = (DM) eK2.get(i2); 
                ArrayList arrayList = eK2; 
                String h3 = dm.h(); 
                str4 = str3; 
                try { 
                    if (h3.equals("分类：")) { 
                        obj = dm.A6().h(); 
                    } else if (h3.equals("年份：")) { 
                        obj2 = dm.A6().h(); 
                    } else if (h3.equals("地区：")) { 
                        str7 = dm.A6().h(); 
                    } else if (h3.equals("更新：")) { 
                        str8 = dm.A6().h(); 
                    } else { 
                        jSONObject = jSONObject2; 
                        str5 = ","; 
                        str6 = str2; 
                        str2 = "a"; 
                        ArrayList arrayList2; 
                        if (h3.equals("导演：")) { 
                            arrayList2 = new ArrayList(); 
                            eK = dm.MQ().eK(str2); 
                            for (i = 0; i < eK.size(); i++) { 
                                arrayList2.add(((DM) eK.get(i)).h()); 
                            } 
                            str10 = TextUtils.join(str5, arrayList2); 
                        } else if (h3.equals("主演：")) { 
                            arrayList2 = new ArrayList(); 
                            eK = dm.MQ().eK(str2); 
                            for (i = 0; i < eK.size(); i++) { 
                                arrayList2.add(((DM) eK.get(i)).h()); 
                            } 
                            str9 = TextUtils.join(str5, arrayList2); 
                        } 
                        i2++; 
                        eK2 = arrayList; 
                        str3 = str4; 
                        jSONObject2 = jSONObject; 
                        str2 = str6; 
                    } 
                    str6 = str2; 
                    jSONObject = jSONObject2; 
                    i2++; 
                    eK2 = arrayList; 
                    str3 = str4; 
                    jSONObject2 = jSONObject; 
                    str2 = str6; 
                } catch (Exception e2) { 
                    e = e2; 
                    SpiderDebug.log(e); 
                    return str4; 
                } 
            } 
            str6 = str2; 
            str4 = str3; 
            jSONObject = jSONObject2; 
            jSONObject3.put("vod_id", list2.get(0)); 
            jSONObject3.put("vod_name", h); 
            jSONObject3.put("vod_pic", jB); 
            jSONObject3.put("type_name", obj); 
            jSONObject3.put("vod_year", obj2); 
            jSONObject3.put("vod_area", str7); 
            jSONObject3.put("vod_remarks", str8); 
            jSONObject3.put("vod_actor", str9); 
            jSONObject3.put("vod_director", str10); 
            jSONObject3.put("vod_content", h2); 
            TreeMap treeMap = new TreeMap(new Comparator<String>() { 
                public int compare(String str, String str2) { 
                    String str3 = "or"; 
                    int i = 1; 
                    try { 
                        int i2 = Duboku.this.Be.getJSONObject(str).getInt(str3); 
                        int i3 = Duboku.this.Be.getJSONObject(str2).getInt(str3); 
                        if (i2 == i3) { 
                            return 1; 
                        } 
                        if (i2 - i3 <= 0) { 
                            i = -1; 
                        } 
                        return i; 
                    } catch (JSONException e) { 
                        SpiderDebug.log(e); 
                        return 1; 
                    } 
                } 
            }); 
            eK = Be.eK("div#playlist1 a"); 
            ArrayList arrayList3 = new ArrayList(); 
            for (i = 0; i < eK.size(); i++) { 
                DM dm2 = (DM) eK.get(i); 
                Matcher matcher = this.PV.matcher(dm2.jB("href")); 
                if (matcher.find()) { 
                    StringBuilder stringBuilder3 = new StringBuilder(); 
                    stringBuilder3.append(matcher.group(1)); 
                    stringBuilder3.append(str); 
                    stringBuilder3.append(matcher.group(2)); 
                    stringBuilder3.append(str); 
                    stringBuilder3.append(matcher.group(3)); 
                    str5 = stringBuilder3.toString(); 
                    stringBuilder3 = new StringBuilder(); 
                    stringBuilder3.append(dm2.h()); 
                    stringBuilder3.append("$"); 
                    stringBuilder3.append(str5); 
                    arrayList3.add(stringBuilder3.toString()); 
                } 
            } 
            treeMap.put("videojs", arrayList3.size() > 0 ? TextUtils.join("#", arrayList3) : str4); 
            if (treeMap.size() > 0) { 
                str2 = str6; 
                str = TextUtils.join(str2, treeMap.keySet()); 
                String join = TextUtils.join(str2, treeMap.values()); 
                jSONObject3.put("vod_play_from", str); 
                jSONObject3.put("vod_play_url", join); 
            } 
            JSONArray jSONArray = new JSONArray(); 
            jSONArray.put(jSONObject3); 
            JSONObject jSONObject4 = jSONObject; 
            jSONObject4.put("list", jSONArray); 
            return jSONObject4.toString(); 
        } catch (Exception e3) { 
            e = e3; 
            str4 = str3; 
            SpiderDebug.log(e); 
            return str4; 
        } 
    } 
 
    public String homeContent(boolean z) { 
        return ""; 
    } 
 
    public void init(Context context) { 
        super.init(context); 
        try { 
            this.Be = new JSONObject("{\"xg_app_player\":{\"sh\":\"app全局解析\",\"pu\":\"\",\"sn\":0,\"or\":999},\"dplayer\":{\"sh\":\"dplayer\",\"pu\":\"\",\"sn\":0,\"or\":999},\"videojs\":{\"sh\":\"videojs-H5播放器\",\"pu\":\"\",\"sn\":0,\"or\":999},\"iva\":{\"sh\":\"iva-H5播放器\",\"pu\":\"\",\"sn\":0,\"or\":999},\"iframe\":{\"sh\":\"iframe外链数据\",\"pu\":\"\",\"sn\":0,\"or\":999},\"link\":{\"sh\":\"外链数据\",\"pu\":\"top.\",\"sn\":0,\"or\":999},\"swf\":{\"sh\":\"Flash文件\",\"pu\":\"\",\"sn\":0,\"or\":999},\"flv\":{\"sh\":\"Flv文件\",\"pu\":\"\",\"sn\":0,\"or\":999},\"qiepian\":{\"sh\":\"爱迪云播\",\"pu\":\"\",\"sn\":0,\"or\":999},\"ckm3u8\":{\"sh\":\"爱迪影视\",\"pu\":\"\",\"sn\":0,\"or\":999},\"xin\":{\"sh\":\"爱迪高速\",\"pu\":\"\",\"sn\":0,\"or\":999},\"ppyun\":{\"sh\":\"爱迪云链\",\"pu\":\"\",\"sn\":0,\"or\":999},\"jisu\":{\"sh\":\"极速路线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"ppayun\":{\"sh\":\"pp云\",\"pu\":\"https://wy.ppayun.cn/api/ShowVideoWy/973ed6d6891c43269706718cb0aedb72/\",\"sn\":0,\"or\":999},\"yun3edu\":{\"sh\":\"爱迪云三\",\"pu\":\"\",\"sn\":0,\"or\":999},\"guoji\":{\"sh\":\"國際路線\",\"pu\":\"\",\"sn\":0,\"or\":999},\"ddyunp\":{\"sh\":\"蓝光线路\",\"pu\":\"\",\"sn\":0,\"or\":999},\"eduyun\":{\"sh\":\"爱迪云二\",\"pu\":\"\",\"sn\":0,\"or\":999},\"dbm3u8\":{\"sh\":\"720P线路\",\"pu\":\"\",\"sn\":0,\"or\":999},\"yjm3u8\":{\"sh\":\"备用线路\",\"pu\":\"\",\"sn\":0,\"or\":999}}"); 
            this.bQ = new JSONObject("{\"dianying\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"dianying\"},{\"n\":\"动作片\",\"v\":\"dongzuopian\"},{\"n\":\"喜剧片\",\"v\":\"xijupian\"},{\"n\":\"爱情片\",\"v\":\"aiqingpian\"},{\"n\":\"科幻片\",\"v\":\"kehuanpian\"},{\"n\":\"恐怖片\",\"v\":\"kongbupian\"},{\"n\":\"剧情片\",\"v\":\"juqingpian\"},{\"n\":\"战争片\",\"v\":\"zhanzhengpian\"},{\"n\":\"传记\",\"v\":\"zhuanji\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"喜剧\",\"v\":\"喜剧\"},{\"n\":\"爱情\",\"v\":\"爱情\"},{\"n\":\"恐怖\",\"v\":\"恐怖\"},{\"n\":\"动作\",\"v\":\"动作\"},{\"n\":\"科幻\",\"v\":\"科幻\"},{\"n\":\"剧情\",\"v\":\"剧情\"},{\"n\":\"战争\",\"v\":\"战争\"},{\"n\":\"警匪\",\"v\":\"警匪\"},{\"n\":\"犯罪\",\"v\":\"犯罪\"},{\"n\":\"动画\",\"v\":\"动画\"},{\"n\":\"奇幻\",\"v\":\"奇幻\"},{\"n\":\"武侠\",\"v\":\"武侠\"},{\"n\":\"冒险\",\"v\":\"冒险\"},{\"n\":\"枪战\",\"v\":\"枪战\"},{\"n\":\"恐怖\",\"v\":\"恐怖\"},{\"n\":\"悬疑\",\"v\":\"悬疑\"},{\"n\":\"惊悚\",\"v\":\"惊悚\"},{\"n\":\"经典\",\"v\":\"经典\"},{\"n\":\"青春\",\"v\":\"青春\"},{\"n\":\"文艺\",\"v\":\"文艺\"},{\"n\":\"微电影\",\"v\":\"微电影\"},{\"n\":\"古装\",\"v\":\"古装\"},{\"n\":\"历史\",\"v\":\"历史\"},{\"n\":\"运动\",\"v\":\"运动\"},{\"n\":\"农村\",\"v\":\"农村\"},{\"n\":\"儿童\",\"v\":\"儿童\"},{\"n\":\"网络电影\",\"v\":\"网络电影\"},{\"n\":\"情色\",\"v\":\"情色\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"大陆\",\"v\":\"大陆\"},{\"n\":\"香港\",\"v\":\"香港\"},{\"n\":\"台湾\",\"v\":\"台湾\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"法国\",\"v\":\"法国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"德国\",\"v\":\"德国\"},{\"n\":\"泰国\",\"v\":\"泰国\"},{\"n\":\"印度\",\"v\":\"印度\"},{\"n\":\"意大利\",\"v\":\"意大利\"},{\"n\":\"西班牙\",\"v\":\"西班牙\"},{\"n\":\"加拿大\",\"v\":\"加拿大\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"法语\",\"v\":\"法语\"},{\"n\":\"德语\",\"v\":\"德语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"lianxuju\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"lianxuju\"},{\"n\":\"国产剧\",\"v\":\"guochanju\"},{\"n\":\"港台剧\",\"v\":\"gangtaiju\"},{\"n\":\"韩剧\",\"v\":\"hanju\"},{\"n\":\"美剧\",\"v\":\"meiju\"},{\"n\":\"日剧\",\"v\":\"riju\"},{\"n\":\"英剧\",\"v\":\"yingju\"},{\"n\":\"泰剧\",\"v\":\"taiju\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"古装\",\"v\":\"古装\"},{\"n\":\"战争\",\"v\":\"战争\"},{\"n\":\"青春偶像\",\"v\":\"青春偶像\"},{\"n\":\"喜剧\",\"v\":\"喜剧\"},{\"n\":\"家庭\",\"v\":\"家庭\"},{\"n\":\"犯罪\",\"v\":\"犯罪\"},{\"n\":\"动作\",\"v\":\"动作\"},{\"n\":\"奇幻\",\"v\":\"奇幻\"},{\"n\":\"剧情\",\"v\":\"剧情\"},{\"n\":\"历史\",\"v\":\"历史\"},{\"n\":\"经典\",\"v\":\"经典\"},{\"n\":\"乡村\",\"v\":\"乡村\"},{\"n\":\"情景\",\"v\":\"情景\"},{\"n\":\"商战\",\"v\":\"商战\"},{\"n\":\"网剧\",\"v\":\"网剧\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"内地\",\"v\":\"内地\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"香港\",\"v\":\"香港\"},{\"n\":\"台湾\",\"v\":\"台湾\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"泰国\",\"v\":\"泰国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"新加坡\",\"v\":\"新加坡\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"zongyi\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"zongyi\"},{\"n\":\"内地综艺\",\"v\":\"ndzy\"},{\"n\":\"港台综艺\",\"v\":\"gtzy\"},{\"n\":\"韩国综艺节目\",\"v\":\"variety\"},{\"n\":\"欧美综艺\",\"v\":\"oumeizongyi\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"选秀\",\"v\":\"选秀\"},{\"n\":\"情感\",\"v\":\"情感\"},{\"n\":\"访谈\",\"v\":\"访谈\"},{\"n\":\"播报\",\"v\":\"播报\"},{\"n\":\"旅游\",\"v\":\"旅游\"},{\"n\":\"音乐\",\"v\":\"音乐\"},{\"n\":\"美食\",\"v\":\"美食\"},{\"n\":\"纪实\",\"v\":\"纪实\"},{\"n\":\"曲艺\",\"v\":\"曲艺\"},{\"n\":\"生活\",\"v\":\"生活\"},{\"n\":\"游戏互动\",\"v\":\"游戏互动\"},{\"n\":\"财经\",\"v\":\"财经\"},{\"n\":\"求职\",\"v\":\"求职\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"内地\",\"v\":\"内地\"},{\"n\":\"港台\",\"v\":\"港台\"},{\"n\":\"日韩\",\"v\":\"日韩\"},{\"n\":\"欧美\",\"v\":\"欧美\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"dongman\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"dongman\"},{\"n\":\"日韩动漫\",\"v\":\"rihan\"},{\"n\":\"国产动漫\",\"v\":\"guoman\"},{\"n\":\"欧美动漫\",\"v\":\"oumei\"},{\"n\":\"动漫电影\",\"v\":\"cartoon\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"情感\",\"v\":\"情感\"},{\"n\":\"科幻\",\"v\":\"科幻\"},{\"n\":\"热血\",\"v\":\"热血\"},{\"n\":\"推理\",\"v\":\"推理\"},{\"n\":\"搞笑\",\"v\":\"搞笑\"},{\"n\":\"冒险\",\"v\":\"冒险\"},{\"n\":\"萝莉\",\"v\":\"萝莉\"},{\"n\":\"校园\",\"v\":\"校园\"},{\"n\":\"动作\",\"v\":\"动作\"},{\"n\":\"机战\",\"v\":\"机战\"},{\"n\":\"运动\",\"v\":\"运动\"},{\"n\":\"战争\",\"v\":\"战争\"},{\"n\":\"少年\",\"v\":\"少年\"},{\"n\":\"少女\",\"v\":\"少女\"},{\"n\":\"社会\",\"v\":\"社会\"},{\"n\":\"原创\",\"v\":\"原创\"},{\"n\":\"亲子\",\"v\":\"亲子\"},{\"n\":\"益智\",\"v\":\"益智\"},{\"n\":\"励志\",\"v\":\"励志\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国产\",\"v\":\"国产\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"欧美\",\"v\":\"欧美\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"jilu\":[{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}]}"); 
        } catch (JSONException e) { 
            SpiderDebug.log(e); 
        } 
    } 
 
    public String playerContent(String str, String str2, List<String> list) { 
        str = "url"; 
        String str3 = "from"; 
        try { 
            StringBuilder stringBuilder = new StringBuilder(); 
            stringBuilder.append("https://ca.duboku.fun/vodplay/"); 
            stringBuilder.append(str2); 
            stringBuilder.append(".html"); 
            str2 = stringBuilder.toString(); 
            Ey eK = f.Be(SpiderReq.get(new SpiderUrl(str2, bQ(str2))).content).eK("script"); 
            JSONObject jSONObject = new JSONObject(); 
            for (int i = 0; i < eK.size(); i++) { 
                String trim = ((DM) eK.get(i)).eP().trim(); 
                if (trim.startsWith("var player_")) { 
                    JSONObject jSONObject2 = new JSONObject(trim.substring(trim.indexOf(123), trim.lastIndexOf(125) + 1)); 
                    if (this.Be.has(jSONObject2.getString(str3))) { 
                        JSONObject jSONObject3 = this.Be.getJSONObject(jSONObject2.getString(str3)); 
                        str3 = jSONObject2.getString(str); 
                        String string = jSONObject3.getString("pu"); 
                        jSONObject.put("parse", jSONObject3.getInt("sn")); 
                        jSONObject.put("playUrl", string); 
                        jSONObject.put(str, str3); 
                        try { 
                            JSONObject jSONObject4 = new JSONObject(); 
                            jSONObject4.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36"); 
                            jSONObject.put("header", jSONObject4.toString()); 
                        } catch (Exception e) { 
                            SpiderDebug.log(e); 
                        } 
                    } 
                    return jSONObject.toString(); 
                } 
            } 
            return jSONObject.toString(); 
        } catch (Exception e2) { 
            SpiderDebug.log(e2); 
            return ""; 
        } 
    } 
 
    public String searchContent(String str, boolean z) { 
        String str2 = ""; 
        if (z) { 
            return str2; 
        } 
        try { 
            StringBuilder stringBuilder = new StringBuilder(); 
            stringBuilder.append("https://ca.duboku.fun/vodsearch/-------------.html?wd="); 
            stringBuilder.append(URLEncoder.encode(str)); 
            stringBuilder.append("&submit="); 
            str = stringBuilder.toString(); 
            y Be = f.Be(SpiderReq.get(new SpiderUrl(str, bQ(str))).content); 
            JSONObject jSONObject = new JSONObject(); 
            JSONArray jSONArray = new JSONArray(); 
            Ey eK = Be.eK("ul.myui-vodlist__media a.myui-vodlist__thumb"); 
            for (int i = 0; i < eK.size(); i++) { 
                DM dm = (DM) eK.get(i); 
                String jB = dm.jB("title"); 
                String jB2 = dm.jB("data-original"); 
                String h = dm.Gp("span.pic-text").h(); 
                Matcher matcher = this.NE.matcher(dm.jB("href")); 
                if (matcher.find()) { 
                    String group = matcher.group(1); 
                    JSONObject jSONObject2 = new JSONObject(); 
                    jSONObject2.put("vod_id", group); 
                    jSONObject2.put("vod_name", jB); 
                    jSONObject2.put("vod_pic", jB2); 
                    jSONObject2.put("vod_remarks", h); 
                    jSONArray.put(jSONObject2); 
                } 
            } 
            jSONObject.put("list", jSONArray); 
            return jSONObject.toString(); 
        } catch (Exception e) { 
            SpiderDebug.log(e); 
            return str2; 
        } 
    } 
} 
