1	package com.github.catvod.spider; 
2	 
3	import android.content.Context; 
4	import android.text.TextUtils; 
5	import com.github.catvod.crawler.Spider; 
6	import com.github.catvod.crawler.SpiderDebug; 
7	import com.github.catvod.crawler.SpiderReq; 
8	import com.github.catvod.crawler.SpiderUrl; 
9	import com.github.catvod.spider.merge.DM; 
10	import com.github.catvod.spider.merge.Ey; 
11	import com.github.catvod.spider.merge.f; 
12	import com.github.catvod.spider.merge.y; 
13	import java.net.URLEncoder; 
14	import java.util.ArrayList; 
15	import java.util.Comparator; 
16	import java.util.HashMap; 
17	import java.util.List; 
18	import java.util.TreeMap; 
19	import java.util.regex.Matcher; 
20	import java.util.regex.Pattern; 
21	import org.json.JSONArray; 
22	import org.json.JSONException; 
23	import org.json.JSONObject; 
24	 
25	public class Duboku extends Spider { 
26	    private JSONObject Be; 
27	    private Pattern NE = Pattern.compile("/voddetail/(\\w+).html"); 
28	    private Pattern PV = Pattern.compile("/vodplay/(\\w+)-(\\d+)-(\\d+).html"); 
29	    private JSONObject bQ; 
30	    private Pattern jB = Pattern.compile("/v/(\\w+).html"); 
31	    private Pattern k = Pattern.compile("/show/(\\S+).html"); 
32	 
33	    /* Access modifiers changed, original: protected */ 
34	    public HashMap<String, String> bQ(String str) { 
35	        HashMap hashMap = new HashMap(); 
36	        hashMap.put("method", "GET"); 
37	        hashMap.put("Host", "ca.duboku.fun"); 
38	        String str2 = "1"; 
39	        hashMap.put("Upgrade-Insecure-Requests", str2); 
40	        hashMap.put("DNT", str2); 
41	        hashMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36"); 
42	        hashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"); 
43	        hashMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"); 
44	        return hashMap; 
45	    } 
46	 
47	    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) { 
48	        return ""; 
49	    } 
50	 
51	    public String detailContent(List<String> list) { 
52	        Throwable e; 
53	        List<String> list2 = list; 
54	        String str = "-"; 
55	        String str2 = "$$$"; 
56	        String str3 = ""; 
57	        String str4; 
58	        try { 
59	            JSONObject jSONObject; 
60	            String str5; 
61	            String str6; 
62	            Ey eK; 
63	            int i; 
64	            StringBuilder stringBuilder = new StringBuilder(); 
65	            stringBuilder.append("https://ca.duboku.fun/voddetail/"); 
66	            stringBuilder.append((String) list2.get(0)); 
67	            stringBuilder.append(".html"); 
68	            String stringBuilder2 = stringBuilder.toString(); 
69	            y Be = f.Be(SpiderReq.get(new SpiderUrl(stringBuilder2, bQ(stringBuilder2))).content); 
70	            JSONObject jSONObject2 = new JSONObject(); 
71	            JSONObject jSONObject3 = new JSONObject(); 
72	            String jB = Be.Gp("div.myui-content__thumb a.myui-vodlist__thumb img").jB("data-original"); 
73	            String h = Be.Gp("div.myui-content__detail h1.title").h(); 
74	            String h2 = Be.Gp("div#desc span.sketch").h(); 
75	            ArrayList eK2 = Be.eK("div.myui-content__detail span"); 
76	            Object obj = str3; 
77	            Object obj2 = obj; 
78	            String str7 = obj2; 
79	            String str8 = str7; 
80	            String str9 = str8; 
81	            String str10 = str9; 
82	            int i2 = 0; 
83	            while (i2 < eK2.size()) { 
84	                DM dm = (DM) eK2.get(i2); 
85	                ArrayList arrayList = eK2; 
86	                String h3 = dm.h(); 
87	                str4 = str3; 
88	                try { 
89	                    if (h3.equals("分类：")) { 
90	                        obj = dm.A6().h(); 
91	                    } else if (h3.equals("年份：")) { 
92	                        obj2 = dm.A6().h(); 
93	                    } else if (h3.equals("地区：")) { 
94	                        str7 = dm.A6().h(); 
95	                    } else if (h3.equals("更新：")) { 
96	                        str8 = dm.A6().h(); 
97	                    } else { 
98	                        jSONObject = jSONObject2; 
99	                        str5 = ","; 
100	                        str6 = str2; 
101	                        str2 = "a"; 
102	                        ArrayList arrayList2; 
103	                        if (h3.equals("导演：")) { 
104	                            arrayList2 = new ArrayList(); 
105	                            eK = dm.MQ().eK(str2); 
106	                            for (i = 0; i < eK.size(); i++) { 
107	                                arrayList2.add(((DM) eK.get(i)).h()); 
108	                            } 
109	                            str10 = TextUtils.join(str5, arrayList2); 
110	                        } else if (h3.equals("主演：")) { 
111	                            arrayList2 = new ArrayList(); 
112	                            eK = dm.MQ().eK(str2); 
113	                            for (i = 0; i < eK.size(); i++) { 
114	                                arrayList2.add(((DM) eK.get(i)).h()); 
115	                            } 
116	                            str9 = TextUtils.join(str5, arrayList2); 
117	                        } 
118	                        i2++; 
119	                        eK2 = arrayList; 
120	                        str3 = str4; 
121	                        jSONObject2 = jSONObject; 
122	                        str2 = str6; 
123	                    } 
124	                    str6 = str2; 
125	                    jSONObject = jSONObject2; 
126	                    i2++; 
127	                    eK2 = arrayList; 
128	                    str3 = str4; 
129	                    jSONObject2 = jSONObject; 
130	                    str2 = str6; 
131	                } catch (Exception e2) { 
132	                    e = e2; 
133	                    SpiderDebug.log(e); 
134	                    return str4; 
135	                } 
136	            } 
137	            str6 = str2; 
138	            str4 = str3; 
139	            jSONObject = jSONObject2; 
140	            jSONObject3.put("vod_id", list2.get(0)); 
141	            jSONObject3.put("vod_name", h); 
142	            jSONObject3.put("vod_pic", jB); 
143	            jSONObject3.put("type_name", obj); 
144	            jSONObject3.put("vod_year", obj2); 
145	            jSONObject3.put("vod_area", str7); 
146	            jSONObject3.put("vod_remarks", str8); 
147	            jSONObject3.put("vod_actor", str9); 
148	            jSONObject3.put("vod_director", str10); 
149	            jSONObject3.put("vod_content", h2); 
150	            TreeMap treeMap = new TreeMap(new Comparator<String>() { 
151	                public int compare(String str, String str2) { 
152	                    String str3 = "or"; 
153	                    int i = 1; 
154	                    try { 
155	                        int i2 = Duboku.this.Be.getJSONObject(str).getInt(str3); 
156	                        int i3 = Duboku.this.Be.getJSONObject(str2).getInt(str3); 
157	                        if (i2 == i3) { 
158	                            return 1; 
159	                        } 
160	                        if (i2 - i3 <= 0) { 
161	                            i = -1; 
162	                        } 
163	                        return i; 
164	                    } catch (JSONException e) { 
165	                        SpiderDebug.log(e); 
166	                        return 1; 
167	                    } 
168	                } 
169	            }); 
170	            eK = Be.eK("div#playlist1 a"); 
171	            ArrayList arrayList3 = new ArrayList(); 
172	            for (i = 0; i < eK.size(); i++) { 
173	                DM dm2 = (DM) eK.get(i); 
174	                Matcher matcher = this.PV.matcher(dm2.jB("href")); 
175	                if (matcher.find()) { 
176	                    StringBuilder stringBuilder3 = new StringBuilder(); 
177	                    stringBuilder3.append(matcher.group(1)); 
178	                    stringBuilder3.append(str); 
179	                    stringBuilder3.append(matcher.group(2)); 
180	                    stringBuilder3.append(str); 
181	                    stringBuilder3.append(matcher.group(3)); 
182	                    str5 = stringBuilder3.toString(); 
183	                    stringBuilder3 = new StringBuilder(); 
184	                    stringBuilder3.append(dm2.h()); 
185	                    stringBuilder3.append("$"); 
186	                    stringBuilder3.append(str5); 
187	                    arrayList3.add(stringBuilder3.toString()); 
188	                } 
189	            } 
190	            treeMap.put("videojs", arrayList3.size() > 0 ? TextUtils.join("#", arrayList3) : str4); 
191	            if (treeMap.size() > 0) { 
192	                str2 = str6; 
193	                str = TextUtils.join(str2, treeMap.keySet()); 
194	                String join = TextUtils.join(str2, treeMap.values()); 
195	                jSONObject3.put("vod_play_from", str); 
196	                jSONObject3.put("vod_play_url", join); 
197	            } 
198	            JSONArray jSONArray = new JSONArray(); 
199	            jSONArray.put(jSONObject3); 
200	            JSONObject jSONObject4 = jSONObject; 
201	            jSONObject4.put("list", jSONArray); 
202	            return jSONObject4.toString(); 
203	        } catch (Exception e3) { 
204	            e = e3; 
205	            str4 = str3; 
206	            SpiderDebug.log(e); 
207	            return str4; 
208	        } 
209	    } 
210	 
211	    public String homeContent(boolean z) { 
212	        return ""; 
213	    } 
214	 
215	    public void init(Context context) { 
216	        super.init(context); 
217	        try { 
218	            this.Be = new JSONObject("{\"xg_app_player\":{\"sh\":\"app全局解析\",\"pu\":\"\",\"sn\":0,\"or\":999},\"dplayer\":{\"sh\":\"dplayer\",\"pu\":\"\",\"sn\":0,\"or\":999},\"videojs\":{\"sh\":\"videojs-H5播放器\",\"pu\":\"\",\"sn\":0,\"or\":999},\"iva\":{\"sh\":\"iva-H5播放器\",\"pu\":\"\",\"sn\":0,\"or\":999},\"iframe\":{\"sh\":\"iframe外链数据\",\"pu\":\"\",\"sn\":0,\"or\":999},\"link\":{\"sh\":\"外链数据\",\"pu\":\"top.\",\"sn\":0,\"or\":999},\"swf\":{\"sh\":\"Flash文件\",\"pu\":\"\",\"sn\":0,\"or\":999},\"flv\":{\"sh\":\"Flv文件\",\"pu\":\"\",\"sn\":0,\"or\":999},\"qiepian\":{\"sh\":\"爱迪云播\",\"pu\":\"\",\"sn\":0,\"or\":999},\"ckm3u8\":{\"sh\":\"爱迪影视\",\"pu\":\"\",\"sn\":0,\"or\":999},\"xin\":{\"sh\":\"爱迪高速\",\"pu\":\"\",\"sn\":0,\"or\":999},\"ppyun\":{\"sh\":\"爱迪云链\",\"pu\":\"\",\"sn\":0,\"or\":999},\"jisu\":{\"sh\":\"极速路线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"ppayun\":{\"sh\":\"pp云\",\"pu\":\"https://wy.ppayun.cn/api/ShowVideoWy/973ed6d6891c43269706718cb0aedb72/\",\"sn\":0,\"or\":999},\"yun3edu\":{\"sh\":\"爱迪云三\",\"pu\":\"\",\"sn\":0,\"or\":999},\"guoji\":{\"sh\":\"國際路線\",\"pu\":\"\",\"sn\":0,\"or\":999},\"ddyunp\":{\"sh\":\"蓝光线路\",\"pu\":\"\",\"sn\":0,\"or\":999},\"eduyun\":{\"sh\":\"爱迪云二\",\"pu\":\"\",\"sn\":0,\"or\":999},\"dbm3u8\":{\"sh\":\"720P线路\",\"pu\":\"\",\"sn\":0,\"or\":999},\"yjm3u8\":{\"sh\":\"备用线路\",\"pu\":\"\",\"sn\":0,\"or\":999}}"); 
219	            this.bQ = new JSONObject("{\"dianying\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"dianying\"},{\"n\":\"动作片\",\"v\":\"dongzuopian\"},{\"n\":\"喜剧片\",\"v\":\"xijupian\"},{\"n\":\"爱情片\",\"v\":\"aiqingpian\"},{\"n\":\"科幻片\",\"v\":\"kehuanpian\"},{\"n\":\"恐怖片\",\"v\":\"kongbupian\"},{\"n\":\"剧情片\",\"v\":\"juqingpian\"},{\"n\":\"战争片\",\"v\":\"zhanzhengpian\"},{\"n\":\"传记\",\"v\":\"zhuanji\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"喜剧\",\"v\":\"喜剧\"},{\"n\":\"爱情\",\"v\":\"爱情\"},{\"n\":\"恐怖\",\"v\":\"恐怖\"},{\"n\":\"动作\",\"v\":\"动作\"},{\"n\":\"科幻\",\"v\":\"科幻\"},{\"n\":\"剧情\",\"v\":\"剧情\"},{\"n\":\"战争\",\"v\":\"战争\"},{\"n\":\"警匪\",\"v\":\"警匪\"},{\"n\":\"犯罪\",\"v\":\"犯罪\"},{\"n\":\"动画\",\"v\":\"动画\"},{\"n\":\"奇幻\",\"v\":\"奇幻\"},{\"n\":\"武侠\",\"v\":\"武侠\"},{\"n\":\"冒险\",\"v\":\"冒险\"},{\"n\":\"枪战\",\"v\":\"枪战\"},{\"n\":\"恐怖\",\"v\":\"恐怖\"},{\"n\":\"悬疑\",\"v\":\"悬疑\"},{\"n\":\"惊悚\",\"v\":\"惊悚\"},{\"n\":\"经典\",\"v\":\"经典\"},{\"n\":\"青春\",\"v\":\"青春\"},{\"n\":\"文艺\",\"v\":\"文艺\"},{\"n\":\"微电影\",\"v\":\"微电影\"},{\"n\":\"古装\",\"v\":\"古装\"},{\"n\":\"历史\",\"v\":\"历史\"},{\"n\":\"运动\",\"v\":\"运动\"},{\"n\":\"农村\",\"v\":\"农村\"},{\"n\":\"儿童\",\"v\":\"儿童\"},{\"n\":\"网络电影\",\"v\":\"网络电影\"},{\"n\":\"情色\",\"v\":\"情色\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"大陆\",\"v\":\"大陆\"},{\"n\":\"香港\",\"v\":\"香港\"},{\"n\":\"台湾\",\"v\":\"台湾\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"法国\",\"v\":\"法国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"德国\",\"v\":\"德国\"},{\"n\":\"泰国\",\"v\":\"泰国\"},{\"n\":\"印度\",\"v\":\"印度\"},{\"n\":\"意大利\",\"v\":\"意大利\"},{\"n\":\"西班牙\",\"v\":\"西班牙\"},{\"n\":\"加拿大\",\"v\":\"加拿大\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"法语\",\"v\":\"法语\"},{\"n\":\"德语\",\"v\":\"德语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"lianxuju\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"lianxuju\"},{\"n\":\"国产剧\",\"v\":\"guochanju\"},{\"n\":\"港台剧\",\"v\":\"gangtaiju\"},{\"n\":\"韩剧\",\"v\":\"hanju\"},{\"n\":\"美剧\",\"v\":\"meiju\"},{\"n\":\"日剧\",\"v\":\"riju\"},{\"n\":\"英剧\",\"v\":\"yingju\"},{\"n\":\"泰剧\",\"v\":\"taiju\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"古装\",\"v\":\"古装\"},{\"n\":\"战争\",\"v\":\"战争\"},{\"n\":\"青春偶像\",\"v\":\"青春偶像\"},{\"n\":\"喜剧\",\"v\":\"喜剧\"},{\"n\":\"家庭\",\"v\":\"家庭\"},{\"n\":\"犯罪\",\"v\":\"犯罪\"},{\"n\":\"动作\",\"v\":\"动作\"},{\"n\":\"奇幻\",\"v\":\"奇幻\"},{\"n\":\"剧情\",\"v\":\"剧情\"},{\"n\":\"历史\",\"v\":\"历史\"},{\"n\":\"经典\",\"v\":\"经典\"},{\"n\":\"乡村\",\"v\":\"乡村\"},{\"n\":\"情景\",\"v\":\"情景\"},{\"n\":\"商战\",\"v\":\"商战\"},{\"n\":\"网剧\",\"v\":\"网剧\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"内地\",\"v\":\"内地\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"香港\",\"v\":\"香港\"},{\"n\":\"台湾\",\"v\":\"台湾\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"泰国\",\"v\":\"泰国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"新加坡\",\"v\":\"新加坡\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"zongyi\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"zongyi\"},{\"n\":\"内地综艺\",\"v\":\"ndzy\"},{\"n\":\"港台综艺\",\"v\":\"gtzy\"},{\"n\":\"韩国综艺节目\",\"v\":\"variety\"},{\"n\":\"欧美综艺\",\"v\":\"oumeizongyi\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"选秀\",\"v\":\"选秀\"},{\"n\":\"情感\",\"v\":\"情感\"},{\"n\":\"访谈\",\"v\":\"访谈\"},{\"n\":\"播报\",\"v\":\"播报\"},{\"n\":\"旅游\",\"v\":\"旅游\"},{\"n\":\"音乐\",\"v\":\"音乐\"},{\"n\":\"美食\",\"v\":\"美食\"},{\"n\":\"纪实\",\"v\":\"纪实\"},{\"n\":\"曲艺\",\"v\":\"曲艺\"},{\"n\":\"生活\",\"v\":\"生活\"},{\"n\":\"游戏互动\",\"v\":\"游戏互动\"},{\"n\":\"财经\",\"v\":\"财经\"},{\"n\":\"求职\",\"v\":\"求职\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"内地\",\"v\":\"内地\"},{\"n\":\"港台\",\"v\":\"港台\"},{\"n\":\"日韩\",\"v\":\"日韩\"},{\"n\":\"欧美\",\"v\":\"欧美\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"dongman\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"dongman\"},{\"n\":\"日韩动漫\",\"v\":\"rihan\"},{\"n\":\"国产动漫\",\"v\":\"guoman\"},{\"n\":\"欧美动漫\",\"v\":\"oumei\"},{\"n\":\"动漫电影\",\"v\":\"cartoon\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"情感\",\"v\":\"情感\"},{\"n\":\"科幻\",\"v\":\"科幻\"},{\"n\":\"热血\",\"v\":\"热血\"},{\"n\":\"推理\",\"v\":\"推理\"},{\"n\":\"搞笑\",\"v\":\"搞笑\"},{\"n\":\"冒险\",\"v\":\"冒险\"},{\"n\":\"萝莉\",\"v\":\"萝莉\"},{\"n\":\"校园\",\"v\":\"校园\"},{\"n\":\"动作\",\"v\":\"动作\"},{\"n\":\"机战\",\"v\":\"机战\"},{\"n\":\"运动\",\"v\":\"运动\"},{\"n\":\"战争\",\"v\":\"战争\"},{\"n\":\"少年\",\"v\":\"少年\"},{\"n\":\"少女\",\"v\":\"少女\"},{\"n\":\"社会\",\"v\":\"社会\"},{\"n\":\"原创\",\"v\":\"原创\"},{\"n\":\"亲子\",\"v\":\"亲子\"},{\"n\":\"益智\",\"v\":\"益智\"},{\"n\":\"励志\",\"v\":\"励志\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国产\",\"v\":\"国产\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"欧美\",\"v\":\"欧美\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"jilu\":[{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}]}"); 
220	        } catch (JSONException e) { 
221	            SpiderDebug.log(e); 
222	        } 
223	    } 
224	 
225	    public String playerContent(String str, String str2, List<String> list) { 
226	        str = "url"; 
227	        String str3 = "from"; 
228	        try { 
229	            StringBuilder stringBuilder = new StringBuilder(); 
230	            stringBuilder.append("https://ca.duboku.fun/vodplay/"); 
231	            stringBuilder.append(str2); 
232	            stringBuilder.append(".html"); 
233	            str2 = stringBuilder.toString(); 
234	            Ey eK = f.Be(SpiderReq.get(new SpiderUrl(str2, bQ(str2))).content).eK("script"); 
235	            JSONObject jSONObject = new JSONObject(); 
236	            for (int i = 0; i < eK.size(); i++) { 
237	                String trim = ((DM) eK.get(i)).eP().trim(); 
238	                if (trim.startsWith("var player_")) { 
239	                    JSONObject jSONObject2 = new JSONObject(trim.substring(trim.indexOf(123), trim.lastIndexOf(125) + 1)); 
240	                    if (this.Be.has(jSONObject2.getString(str3))) { 
241	                        JSONObject jSONObject3 = this.Be.getJSONObject(jSONObject2.getString(str3)); 
242	                        str3 = jSONObject2.getString(str); 
243	                        String string = jSONObject3.getString("pu"); 
244	                        jSONObject.put("parse", jSONObject3.getInt("sn")); 
245	                        jSONObject.put("playUrl", string); 
246	                        jSONObject.put(str, str3); 
247	                        try { 
248	                            JSONObject jSONObject4 = new JSONObject(); 
249	                            jSONObject4.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36"); 
250	                            jSONObject.put("header", jSONObject4.toString()); 
251	                        } catch (Exception e) { 
252	                            SpiderDebug.log(e); 
253	                        } 
254	                    } 
255	                    return jSONObject.toString(); 
256	                } 
257	            } 
258	            return jSONObject.toString(); 
259	        } catch (Exception e2) { 
260	            SpiderDebug.log(e2); 
261	            return ""; 
262	        } 
263	    } 
264	 
265	    public String searchContent(String str, boolean z) { 
266	        String str2 = ""; 
267	        if (z) { 
268	            return str2; 
269	        } 
270	        try { 
271	            StringBuilder stringBuilder = new StringBuilder(); 
272	            stringBuilder.append("https://ca.duboku.fun/vodsearch/-------------.html?wd="); 
273	            stringBuilder.append(URLEncoder.encode(str)); 
274	            stringBuilder.append("&submit="); 
275	            str = stringBuilder.toString(); 
276	            y Be = f.Be(SpiderReq.get(new SpiderUrl(str, bQ(str))).content); 
277	            JSONObject jSONObject = new JSONObject(); 
278	            JSONArray jSONArray = new JSONArray(); 
279	            Ey eK = Be.eK("ul.myui-vodlist__media a.myui-vodlist__thumb"); 
280	            for (int i = 0; i < eK.size(); i++) { 
281	                DM dm = (DM) eK.get(i); 
282	                String jB = dm.jB("title"); 
283	                String jB2 = dm.jB("data-original"); 
284	                String h = dm.Gp("span.pic-text").h(); 
285	                Matcher matcher = this.NE.matcher(dm.jB("href")); 
286	                if (matcher.find()) { 
287	                    String group = matcher.group(1); 
288	                    JSONObject jSONObject2 = new JSONObject(); 
289	                    jSONObject2.put("vod_id", group); 
290	                    jSONObject2.put("vod_name", jB); 
291	                    jSONObject2.put("vod_pic", jB2); 
292	                    jSONObject2.put("vod_remarks", h); 
293	                    jSONArray.put(jSONObject2); 
294	                } 
295	            } 
296	            jSONObject.put("list", jSONArray); 
297	            return jSONObject.toString(); 
298	        } catch (Exception e) { 
299	            SpiderDebug.log(e); 
300	            return str2; 
301	        } 
302	    } 
303	} 
304	 
