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
17	import java.util.Iterator; 
18	import java.util.List; 
19	import java.util.TreeMap; 
20	import java.util.regex.Matcher; 
21	import java.util.regex.Pattern; 
22	import org.json.JSONArray; 
23	import org.json.JSONException; 
24	import org.json.JSONObject; 
25	 
26	public class Xkys extends Spider { 
27	    private JSONObject Be; 
28	    private Pattern NE = Pattern.compile("/Moviedetail/(\\w+).html"); 
29	    private Pattern PV = Pattern.compile("/okplay/(\\w+)-(\\d+)-(\\d+).html"); 
30	    private JSONObject bQ; 
31	    private Pattern jB = Pattern.compile("/v/(\\w+).html"); 
32	    private Pattern k = Pattern.compile("/show/(\\S+).html"); 
33	 
34	    /* Access modifiers changed, original: protected */ 
35	    public HashMap<String, String> Be(String str) { 
36	        HashMap hashMap = new HashMap(); 
37	        hashMap.put("method", "GET"); 
38	        hashMap.put("Host", "xkys.tv"); 
39	        String str2 = "1"; 
40	        hashMap.put("Upgrade-Insecure-Requests", str2); 
41	        hashMap.put("DNT", str2); 
42	        hashMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36"); 
43	        hashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"); 
44	        hashMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"); 
45	        return hashMap; 
46	    } 
47	 
48	    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) { 
49	        return ""; 
50	    } 
51	 
52	    public String detailContent(List<String> list) { 
53	        Throwable e; 
54	        List<String> list2 = list; 
55	        String str = "-"; 
56	        String str2 = "$$$"; 
57	        String str3 = ""; 
58	        String str4; 
59	        try { 
60	            JSONObject jSONObject; 
61	            String str5; 
62	            String str6; 
63	            Ey eK; 
64	            int i; 
65	            StringBuilder stringBuilder = new StringBuilder(); 
66	            stringBuilder.append("http://xkys.tv/Moviedetail/"); 
67	            stringBuilder.append((String) list2.get(0)); 
68	            stringBuilder.append(".html"); 
69	            String stringBuilder2 = stringBuilder.toString(); 
70	            y Be = f.Be(SpiderReq.get(new SpiderUrl(stringBuilder2, Be(stringBuilder2))).content); 
71	            JSONObject jSONObject2 = new JSONObject(); 
72	            JSONObject jSONObject3 = new JSONObject(); 
73	            String jB = Be.Gp("div.returl a.s-cover-img img").jB("src"); 
74	            jB = jB.substring(jB.indexOf("http")); 
75	            String h = Be.Gp("div.s-top-info-title h1").h(); 
76	            String h2 = Be.Gp("div.desc_txt span").h(); 
77	            Object h3 = Be.Gp("div.play-detail span.remarks").h(); 
78	            ArrayList eK2 = Be.eK("div.s-top-info-detail span"); 
79	            Object obj = str3; 
80	            String str7 = obj; 
81	            String str8 = str7; 
82	            String str9 = str8; 
83	            String str10 = str9; 
84	            int i2 = 0; 
85	            while (i2 < eK2.size()) { 
86	                DM dm = (DM) eK2.get(i2); 
87	                ArrayList arrayList = eK2; 
88	                String h4 = dm.h(); 
89	                str4 = str3; 
90	                try { 
91	                    jSONObject = jSONObject2; 
92	                    str5 = ","; 
93	                    str6 = str2; 
94	                    str2 = "a"; 
95	                    ArrayList arrayList2; 
96	                    if (h4.equals("类型：")) { 
97	                        arrayList2 = new ArrayList(); 
98	                        eK = dm.MQ().eK(str2); 
99	                        for (i = 0; i < eK.size(); i++) { 
100	                            arrayList2.add(((DM) eK.get(i)).h()); 
101	                        } 
102	                        obj = TextUtils.join(str5, arrayList2); 
103	                    } else if (h4.equals("年代：")) { 
104	                        str7 = dm.A6().h(); 
105	                    } else if (h4.equals("地区：")) { 
106	                        str8 = dm.A6().h(); 
107	                    } else if (h4.equals("更新：")) { 
108	                        h3 = dm.A6().h(); 
109	                    } else if (h4.equals("导演：")) { 
110	                        arrayList2 = new ArrayList(); 
111	                        eK = dm.MQ().eK(str2); 
112	                        for (i = 0; i < eK.size(); i++) { 
113	                            arrayList2.add(((DM) eK.get(i)).h()); 
114	                        } 
115	                        str10 = TextUtils.join(str5, arrayList2); 
116	                    } else if (h4.equals("演员：")) { 
117	                        arrayList2 = new ArrayList(); 
118	                        eK = dm.MQ().eK(str2); 
119	                        for (i = 0; i < eK.size(); i++) { 
120	                            arrayList2.add(((DM) eK.get(i)).h()); 
121	                        } 
122	                        str9 = TextUtils.join(str5, arrayList2); 
123	                    } 
124	                    i2++; 
125	                    eK2 = arrayList; 
126	                    str3 = str4; 
127	                    jSONObject2 = jSONObject; 
128	                    str2 = str6; 
129	                } catch (Exception e2) { 
130	                    e = e2; 
131	                    SpiderDebug.log(e); 
132	                    return str4; 
133	                } 
134	            } 
135	            str6 = str2; 
136	            str4 = str3; 
137	            jSONObject = jSONObject2; 
138	            jSONObject3.put("vod_id", list2.get(0)); 
139	            jSONObject3.put("vod_name", h); 
140	            jSONObject3.put("vod_pic", jB); 
141	            jSONObject3.put("type_name", obj); 
142	            jSONObject3.put("vod_year", str7); 
143	            jSONObject3.put("vod_area", str8); 
144	            jSONObject3.put("vod_remarks", h3); 
145	            jSONObject3.put("vod_actor", str9); 
146	            jSONObject3.put("vod_director", str10); 
147	            jSONObject3.put("vod_content", h2); 
148	            TreeMap treeMap = new TreeMap(new Comparator<String>() { 
149	                public int compare(String str, String str2) { 
150	                    return 1; 
151	                } 
152	            }); 
153	            eK = Be.eK("div.play_source_tab a.channelname"); 
154	            Ey eK3 = Be.eK("div#tagContent ul.content_playlist"); 
155	            for (i = 0; i < eK.size(); i++) { 
156	                int i3; 
157	                Object obj2; 
158	                str5 = ((DM) eK.get(i)).h().trim(); 
159	                str5 = str5.substring(2, str5.indexOf("(")); 
160	                Iterator keys = this.Be.keys(); 
161	                do { 
162	                    i3 = 1; 
163	                    if (!keys.hasNext()) { 
164	                        obj2 = null; 
165	                        break; 
166	                    } 
167	                } while (!this.Be.getJSONObject((String) keys.next()).getString("show").equals(str5)); 
168	                obj2 = 1; 
169	                if (obj2 != null) { 
170	                    Ey eK4 = ((DM) eK3.get(i)).eK("li > a"); 
171	                    ArrayList arrayList3 = new ArrayList(); 
172	                    int i4 = 0; 
173	                    while (i4 < eK4.size()) { 
174	                        DM dm2 = (DM) eK4.get(i4); 
175	                        Matcher matcher = this.PV.matcher(dm2.jB("href")); 
176	                        if (matcher.find()) { 
177	                            StringBuilder stringBuilder3 = new StringBuilder(); 
178	                            stringBuilder3.append(matcher.group(i3)); 
179	                            stringBuilder3.append(str); 
180	                            stringBuilder3.append(matcher.group(2)); 
181	                            stringBuilder3.append(str); 
182	                            stringBuilder3.append(matcher.group(3)); 
183	                            str3 = stringBuilder3.toString(); 
184	                            StringBuilder stringBuilder4 = new StringBuilder(); 
185	                            stringBuilder4.append(dm2.h()); 
186	                            stringBuilder4.append("$"); 
187	                            stringBuilder4.append(str3); 
188	                            arrayList3.add(stringBuilder4.toString()); 
189	                        } 
190	                        i4++; 
191	                        i3 = 1; 
192	                    } 
193	                    str3 = arrayList3.size() > 0 ? TextUtils.join("#", arrayList3) : str4; 
194	                    if (str3.length() != 0) { 
195	                        treeMap.put(str5, str3); 
196	                    } 
197	                } 
198	            } 
199	            if (treeMap.size() > 0) { 
200	                str2 = str6; 
201	                str = TextUtils.join(str2, treeMap.keySet()); 
202	                String join = TextUtils.join(str2, treeMap.values()); 
203	                jSONObject3.put("vod_play_from", str); 
204	                jSONObject3.put("vod_play_url", join); 
205	            } 
206	            JSONArray jSONArray = new JSONArray(); 
207	            jSONArray.put(jSONObject3); 
208	            JSONObject jSONObject4 = jSONObject; 
209	            jSONObject4.put("list", jSONArray); 
210	            return jSONObject4.toString(); 
211	        } catch (Exception e3) { 
212	            e = e3; 
213	            str4 = str3; 
214	            SpiderDebug.log(e); 
215	            return str4; 
216	        } 
217	    } 
218	 
219	    public String homeContent(boolean z) { 
220	        return ""; 
221	    } 
222	 
223	    public void init(Context context) { 
224	        super.init(context); 
225	        try { 
226	            this.Be = new JSONObject("{\"xg_app_player\":{\"show\":\"app全局解析\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"\",\"or\":999},\"xkys\":{\"show\":\"超音速线路\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"https://play.xkys.tv/jiexi.php?url=\",\"or\":999},\"ftqp4\":{\"show\":\"蓝光极速\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"https://play.xkys.tv/jiexi.php?url=\",\"or\":999},\"dm\":{\"show\":\"动漫专线\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"https://play.xkys.tv/jiexi.php?url=\",\"or\":999},\"pll\":{\"show\":\"音速追剧\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"https://play.xkys.tv/bfq.php?url=\",\"or\":999},\"qq\":{\"show\":\"腾讯线路\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"https://play.xkys.tv/jiexi.php?url=\",\"or\":999},\"youku\":{\"show\":\"优酷线路\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"https://play.xkys.tv/jiexi.php?url=\",\"or\":999},\"qiyi\":{\"show\":\"爱奇艺线路\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"https://play.xkys.tv/jiexi.php?url=\",\"or\":999},\"mgtv\":{\"show\":\"芒果线路\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"https://play.xkys.tv/jiexi.php?url=\",\"or\":999},\"sohu\":{\"show\":\"搜狐线路\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"https://play.xkys.tv/jiexi.php?url=\",\"or\":999},\"bilibili\":{\"show\":\"动漫线路\",\"des\":\"\",\"ps\":\"1\",\"parse\":\"https://play.xkys.tv/jiexi.php?url=\",\"or\":999}}}"); 
227	            this.bQ = new JSONObject("{\"dianying\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"dianying\"},{\"n\":\"动作片\",\"v\":\"dongzuopian\"},{\"n\":\"喜剧片\",\"v\":\"xijupian\"},{\"n\":\"爱情片\",\"v\":\"aiqingpian\"},{\"n\":\"科幻片\",\"v\":\"kehuanpian\"},{\"n\":\"恐怖片\",\"v\":\"kongbupian\"},{\"n\":\"剧情片\",\"v\":\"juqingpian\"},{\"n\":\"战争片\",\"v\":\"zhanzhengpian\"},{\"n\":\"传记\",\"v\":\"zhuanji\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"喜剧\",\"v\":\"喜剧\"},{\"n\":\"爱情\",\"v\":\"爱情\"},{\"n\":\"恐怖\",\"v\":\"恐怖\"},{\"n\":\"动作\",\"v\":\"动作\"},{\"n\":\"科幻\",\"v\":\"科幻\"},{\"n\":\"剧情\",\"v\":\"剧情\"},{\"n\":\"战争\",\"v\":\"战争\"},{\"n\":\"警匪\",\"v\":\"警匪\"},{\"n\":\"犯罪\",\"v\":\"犯罪\"},{\"n\":\"动画\",\"v\":\"动画\"},{\"n\":\"奇幻\",\"v\":\"奇幻\"},{\"n\":\"武侠\",\"v\":\"武侠\"},{\"n\":\"冒险\",\"v\":\"冒险\"},{\"n\":\"枪战\",\"v\":\"枪战\"},{\"n\":\"恐怖\",\"v\":\"恐怖\"},{\"n\":\"悬疑\",\"v\":\"悬疑\"},{\"n\":\"惊悚\",\"v\":\"惊悚\"},{\"n\":\"经典\",\"v\":\"经典\"},{\"n\":\"青春\",\"v\":\"青春\"},{\"n\":\"文艺\",\"v\":\"文艺\"},{\"n\":\"微电影\",\"v\":\"微电影\"},{\"n\":\"古装\",\"v\":\"古装\"},{\"n\":\"历史\",\"v\":\"历史\"},{\"n\":\"运动\",\"v\":\"运动\"},{\"n\":\"农村\",\"v\":\"农村\"},{\"n\":\"儿童\",\"v\":\"儿童\"},{\"n\":\"网络电影\",\"v\":\"网络电影\"},{\"n\":\"情色\",\"v\":\"情色\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"大陆\",\"v\":\"大陆\"},{\"n\":\"香港\",\"v\":\"香港\"},{\"n\":\"台湾\",\"v\":\"台湾\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"法国\",\"v\":\"法国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"德国\",\"v\":\"德国\"},{\"n\":\"泰国\",\"v\":\"泰国\"},{\"n\":\"印度\",\"v\":\"印度\"},{\"n\":\"意大利\",\"v\":\"意大利\"},{\"n\":\"西班牙\",\"v\":\"西班牙\"},{\"n\":\"加拿大\",\"v\":\"加拿大\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"法语\",\"v\":\"法语\"},{\"n\":\"德语\",\"v\":\"德语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"lianxuju\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"lianxuju\"},{\"n\":\"国产剧\",\"v\":\"guochanju\"},{\"n\":\"港台剧\",\"v\":\"gangtaiju\"},{\"n\":\"韩剧\",\"v\":\"hanju\"},{\"n\":\"美剧\",\"v\":\"meiju\"},{\"n\":\"日剧\",\"v\":\"riju\"},{\"n\":\"英剧\",\"v\":\"yingju\"},{\"n\":\"泰剧\",\"v\":\"taiju\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"古装\",\"v\":\"古装\"},{\"n\":\"战争\",\"v\":\"战争\"},{\"n\":\"青春偶像\",\"v\":\"青春偶像\"},{\"n\":\"喜剧\",\"v\":\"喜剧\"},{\"n\":\"家庭\",\"v\":\"家庭\"},{\"n\":\"犯罪\",\"v\":\"犯罪\"},{\"n\":\"动作\",\"v\":\"动作\"},{\"n\":\"奇幻\",\"v\":\"奇幻\"},{\"n\":\"剧情\",\"v\":\"剧情\"},{\"n\":\"历史\",\"v\":\"历史\"},{\"n\":\"经典\",\"v\":\"经典\"},{\"n\":\"乡村\",\"v\":\"乡村\"},{\"n\":\"情景\",\"v\":\"情景\"},{\"n\":\"商战\",\"v\":\"商战\"},{\"n\":\"网剧\",\"v\":\"网剧\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"内地\",\"v\":\"内地\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"香港\",\"v\":\"香港\"},{\"n\":\"台湾\",\"v\":\"台湾\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"泰国\",\"v\":\"泰国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"新加坡\",\"v\":\"新加坡\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"zongyi\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"zongyi\"},{\"n\":\"内地综艺\",\"v\":\"ndzy\"},{\"n\":\"港台综艺\",\"v\":\"gtzy\"},{\"n\":\"韩国综艺节目\",\"v\":\"variety\"},{\"n\":\"欧美综艺\",\"v\":\"oumeizongyi\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"选秀\",\"v\":\"选秀\"},{\"n\":\"情感\",\"v\":\"情感\"},{\"n\":\"访谈\",\"v\":\"访谈\"},{\"n\":\"播报\",\"v\":\"播报\"},{\"n\":\"旅游\",\"v\":\"旅游\"},{\"n\":\"音乐\",\"v\":\"音乐\"},{\"n\":\"美食\",\"v\":\"美食\"},{\"n\":\"纪实\",\"v\":\"纪实\"},{\"n\":\"曲艺\",\"v\":\"曲艺\"},{\"n\":\"生活\",\"v\":\"生活\"},{\"n\":\"游戏互动\",\"v\":\"游戏互动\"},{\"n\":\"财经\",\"v\":\"财经\"},{\"n\":\"求职\",\"v\":\"求职\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"内地\",\"v\":\"内地\"},{\"n\":\"港台\",\"v\":\"港台\"},{\"n\":\"日韩\",\"v\":\"日韩\"},{\"n\":\"欧美\",\"v\":\"欧美\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"dongman\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"dongman\"},{\"n\":\"日韩动漫\",\"v\":\"rihan\"},{\"n\":\"国产动漫\",\"v\":\"guoman\"},{\"n\":\"欧美动漫\",\"v\":\"oumei\"},{\"n\":\"动漫电影\",\"v\":\"cartoon\"}]},{\"key\":3,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"情感\",\"v\":\"情感\"},{\"n\":\"科幻\",\"v\":\"科幻\"},{\"n\":\"热血\",\"v\":\"热血\"},{\"n\":\"推理\",\"v\":\"推理\"},{\"n\":\"搞笑\",\"v\":\"搞笑\"},{\"n\":\"冒险\",\"v\":\"冒险\"},{\"n\":\"萝莉\",\"v\":\"萝莉\"},{\"n\":\"校园\",\"v\":\"校园\"},{\"n\":\"动作\",\"v\":\"动作\"},{\"n\":\"机战\",\"v\":\"机战\"},{\"n\":\"运动\",\"v\":\"运动\"},{\"n\":\"战争\",\"v\":\"战争\"},{\"n\":\"少年\",\"v\":\"少年\"},{\"n\":\"少女\",\"v\":\"少女\"},{\"n\":\"社会\",\"v\":\"社会\"},{\"n\":\"原创\",\"v\":\"原创\"},{\"n\":\"亲子\",\"v\":\"亲子\"},{\"n\":\"益智\",\"v\":\"益智\"},{\"n\":\"励志\",\"v\":\"励志\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国产\",\"v\":\"国产\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"欧美\",\"v\":\"欧美\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"jilu\":[{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}]}"); 
228	        } catch (JSONException e) { 
229	            SpiderDebug.log(e); 
230	        } 
231	    } 
232	 
233	    public String playerContent(String str, String str2, List<String> list) { 
234	        str = "parse"; 
235	        String str3 = "url"; 
236	        String str4 = "from"; 
237	        try { 
238	            StringBuilder stringBuilder = new StringBuilder(); 
239	            stringBuilder.append("http://xkys.tv/okplay/"); 
240	            stringBuilder.append(str2); 
241	            stringBuilder.append(".html"); 
242	            str2 = stringBuilder.toString(); 
243	            Ey eK = f.Be(SpiderReq.get(new SpiderUrl(str2, Be(str2))).content).eK("script"); 
244	            JSONObject jSONObject = new JSONObject(); 
245	            for (int i = 0; i < eK.size(); i++) { 
246	                String trim = ((DM) eK.get(i)).eP().trim(); 
247	                if (trim.startsWith("var player_")) { 
248	                    JSONObject jSONObject2 = new JSONObject(trim.substring(trim.indexOf(123), trim.lastIndexOf(125) + 1)); 
249	                    if (this.Be.has(jSONObject2.getString(str4))) { 
250	                        JSONObject jSONObject3 = this.Be.getJSONObject(jSONObject2.getString(str4)); 
251	                        str4 = jSONObject2.getString(str3); 
252	                        String string = jSONObject3.getString(str); 
253	                        jSONObject.put(str, jSONObject3.getInt("ps")); 
254	                        jSONObject.put("playUrl", string); 
255	                        jSONObject.put(str3, str4); 
256	                        try { 
257	                            JSONObject jSONObject4 = new JSONObject(); 
258	                            jSONObject4.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/85.0.4183.109 Mobile/15E148 Safari/604.1"); 
259	                            jSONObject.put("header", jSONObject4.toString()); 
260	                        } catch (Exception e) { 
261	                            SpiderDebug.log(e); 
262	                        } 
263	                    } 
264	                    return jSONObject.toString(); 
265	                } 
266	            } 
267	            return jSONObject.toString(); 
268	        } catch (Exception e2) { 
269	            SpiderDebug.log(e2); 
270	            return ""; 
271	        } 
272	    } 
273	 
274	    public String searchContent(String str, boolean z) { 
275	        String str2 = ""; 
276	        if (z) { 
277	            return str2; 
278	        } 
279	        try { 
280	            StringBuilder stringBuilder = new StringBuilder(); 
281	            stringBuilder.append("http://xkys.tv/vod/search.html?wd="); 
282	            stringBuilder.append(URLEncoder.encode(str)); 
283	            str = stringBuilder.toString(); 
284	            y Be = f.Be(SpiderReq.get(new SpiderUrl(str, Be(str))).content); 
285	            JSONObject jSONObject = new JSONObject(); 
286	            JSONArray jSONArray = new JSONArray(); 
287	            Ey eK = Be.eK("div.ec-search a.ecimgbor"); 
288	            for (int i = 0; i < eK.size(); i++) { 
289	                DM dm = (DM) eK.get(i); 
290	                String jB = dm.jB("title"); 
291	                String jB2 = dm.Gp("div").jB("data-original"); 
292	                jB2 = jB2.substring(jB2.indexOf("http")); 
293	                String h = dm.Gp("span.pack-prb").h(); 
294	                Matcher matcher = this.NE.matcher(dm.jB("href")); 
295	                if (matcher.find()) { 
296	                    String group = matcher.group(1); 
297	                    JSONObject jSONObject2 = new JSONObject(); 
298	                    jSONObject2.put("vod_id", group); 
299	                    jSONObject2.put("vod_name", jB); 
300	                    jSONObject2.put("vod_pic", jB2); 
301	                    jSONObject2.put("vod_remarks", h); 
302	                    jSONArray.put(jSONObject2); 
303	                } 
304	            } 
305	            jSONObject.put("list", jSONArray); 
306	            return jSONObject.toString(); 
307	        } catch (Exception e) { 
308	            SpiderDebug.log(e); 
309	            return str2; 
310	        } 
311	    } 
312	} 
313	 
