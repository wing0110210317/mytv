
package com.github.catvod.spider; 
 
import android.content.Context; 
import android.util.Base64; 
import com.github.catvod.crawler.Spider; 
import com.github.catvod.crawler.SpiderDebug; 
import com.github.catvod.crawler.SpiderReq; 
import com.github.catvod.crawler.SpiderUrl; 
import java.net.URLEncoder; 
import java.security.MessageDigest; 
import java.text.SimpleDateFormat; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.Iterator; 
import java.util.List; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import javax.crypto.Cipher; 
import javax.crypto.spec.IvParameterSpec; 
import javax.crypto.spec.SecretKeySpec; 
import org.json.JSONArray; 
import org.json.JSONObject; 
 
public class Zjdr extends Spider { 
    protected JSONObject eC = new JSONObject(); 
    private Pattern iM = Pattern.compile("player_list=(\\S+),MacPlayerConfig"); 
    protected HashMap<String, String> k = new HashMap(); 
 
    private static byte[] C(String str) { 
        try { 
            int i; 
            byte[] bytes = str.getBytes("GBK"); 
            byte[] bArr = new byte[256]; 
            for (i = 0; i < 256; i++) { 
                bArr[i] = (byte) i; 
            } 
            if (bytes == null || bytes.length == 0) { 
                return null; 
            } 
            i = 0; 
            int i2 = 0; 
            for (int i3 = 0; i3 < 256; i3++) { 
                i2 = (((bytes[i] & 255) + (bArr[i3] & 255)) + i2) & 255; 
                byte b = bArr[i3]; 
                bArr[i3] = bArr[i2]; 
                bArr[i2] = b; 
                i = (i + 1) % bytes.length; 
            } 
            return bArr; 
        } catch (Exception e) { 
            e.printStackTrace(); 
            return null; 
        } 
    } 
 
    private static byte[] eC(byte[] bArr, String str) { 
        byte[] C = C(str); 
        byte[] bArr2 = new byte[bArr.length]; 
        int i = 0; 
        int i2 = 0; 
        for (int i3 = 0; i3 < bArr.length; i3++) { 
            i = (i + 1) & 255; 
            i2 = ((C[i] & 255) + i2) & 255; 
            byte b = C[i]; 
            C[i] = C[i2]; 
            C[i2] = b; 
            bArr2[i3] = (byte) (C[((C[i] & 255) + (C[i2] & 255)) & 255] ^ bArr[i3]); 
        } 
        return bArr2; 
    } 
 
    private static byte[] iM(String str) { 
        try { 
            int length = str.length(); 
            byte[] bArr = new byte[(length / 2)]; 
            byte[] bytes = str.getBytes("GBK"); 
            for (int i = 0; i < length / 2; i++) { 
                int i2 = i * 2; 
                bArr[i] = j8(bytes[i2], bytes[i2 + 1]); 
            } 
            return bArr; 
        } catch (Exception e) { 
            e.printStackTrace(); 
            return new byte[0]; 
        } 
    } 
 
    private static byte j8(byte b, byte b2) { 
        StringBuilder stringBuilder = new StringBuilder(); 
        String str = "0x"; 
        stringBuilder.append(str); 
        stringBuilder.append(new String(new byte[]{b})); 
        char byteValue = (char) (((char) Byte.decode(stringBuilder.toString()).byteValue()) << 4); 
        stringBuilder = new StringBuilder(); 
        stringBuilder.append(str); 
        stringBuilder.append(new String(new byte[]{b2})); 
        return (byte) (byteValue ^ ((char) Byte.decode(stringBuilder.toString()).byteValue())); 
    } 
 
    public static String m1741BASE64(byte[] bArr) { 
        return Base64.encodeToString(bArr, 0); 
    } 
 
    public static byte[] m1742BASE64(String str) { 
        return Base64.decode(str, 0); 
    } 
 
    public static String m1743DES(String str, String str2) { 
        String str3 = "GBK"; 
        try { 
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[]{(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8}); 
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(str3), "DES"); 
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
            instance.init(1, secretKeySpec, ivParameterSpec); 
            return m1741BASE64(instance.doFinal(str.getBytes(str3))); 
        } catch (Exception unused) { 
            return ""; 
        } 
    } 
 
    public static String m1744DES(String str, String str2) { 
        String str3 = "GBK"; 
        try { 
            byte[] m1742BASE64 = m1742BASE64(str); 
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[]{(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8}); 
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(str3), "DES"); 
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
            instance.init(2, secretKeySpec, ivParameterSpec); 
            return new String(instance.doFinal(m1742BASE64), str3); 
        } catch (Exception unused) { 
            return ""; 
        } 
    } 
 
    public static String m1745RC4(String str, String str2) { 
        if (!(str == null || str2 == null)) { 
            try { 
                byte[] eC = eC(str.getBytes("GBK"), str2); 
                r9 = new char[16]; 
                int i = 0; 
                r9[0] = '0'; 
                r9[1] = '1'; 
                r9[2] = '2'; 
                r9[3] = '3'; 
                r9[4] = '4'; 
                r9[5] = '5'; 
                r9[6] = '6'; 
                r9[7] = '7'; 
                r9[8] = '8'; 
                r9[9] = '9'; 
                r9[10] = 'A'; 
                r9[11] = 'B'; 
                r9[12] = 'C'; 
                r9[13] = 'D'; 
                r9[14] = 'E'; 
                r9[15] = 'F'; 
                char[] cArr = new char[(eC.length * 2)]; 
                int length = eC.length; 
                int i2 = 0; 
                while (i < length) { 
                    byte b = eC[i]; 
                    int i3 = i2 + 1; 
                    cArr[i2] = r9[(b >>> 4) & 15]; 
                    i2 = i3 + 1; 
                    cArr[i3] = r9[b & 15]; 
                    i++; 
                } 
                return new String(cArr); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        } 
        return ""; 
    } 
 
    public static byte[] m1746RC42(byte[] bArr, String str) { 
        if (bArr == null || str == null) { 
            return new byte[0]; 
        } 
        try { 
            bArr = eC(bArr, str); 
            byte[] bArr2 = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70}; 
            byte[] bArr3 = new byte[(bArr.length * 2)]; 
            int i = 0; 
            for (byte b : bArr) { 
                int i2 = i + 1; 
                bArr3[i] = bArr2[(b >>> 4) & 15]; 
                i = i2 + 1; 
                bArr3[i2] = bArr2[b & 15]; 
            } 
            return bArr3; 
        } catch (Exception e) { 
            e.printStackTrace(); 
            return new byte[0]; 
        } 
    } 
 
    public static String m1747RC4(String str, String str2) { 
        if (!(str == null || str2 == null)) { 
            try { 
                return new String(eC(iM(str), str2), "GBK"); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        } 
        return ""; 
    } 
 
    public static byte[] m1748RC42(byte[] bArr, String str) { 
        if (bArr == null || str == null) { 
            return new byte[0]; 
        } 
        try { 
            return eC(bArr, str); 
        } catch (Exception e) { 
            e.printStackTrace(); 
            return new byte[0]; 
        } 
    } 
 
    public static String m1749MD5(byte[] bArr) { 
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'}; 
        try { 
            MessageDigest instance = MessageDigest.getInstance("MD5"); 
            instance.update(bArr); 
            bArr = instance.digest(); 
            char[] cArr2 = new char[(bArr.length * 2)]; 
            int i = 0; 
            for (byte b : bArr) { 
                int i2 = i + 1; 
                cArr2[i] = cArr[(b >>> 4) & 15]; 
                i = i2 + 1; 
                cArr2[i2] = cArr[b & 15]; 
            } 
            return new String(cArr2); 
        } catch (Exception e) { 
            e.printStackTrace(); 
            return ""; 
        } 
    } 
 
    public static String m1983(String str) { 
        StringBuilder stringBuilder = new StringBuilder(); 
        stringBuilder.append("yyyy"); 
        stringBuilder.append(str); 
        stringBuilder.append("MM"); 
        stringBuilder.append(str); 
        stringBuilder.append("dd"); 
        return new SimpleDateFormat(stringBuilder.toString()).format(new Date(System.currentTimeMillis())); 
    } 
 
    private HashMap<String, String> q1(String str) { 
        HashMap hashMap = new HashMap(); 
        hashMap.put("Host", "vipmv.tv"); 
        hashMap.put("Connection", "Keep-Alive"); 
        hashMap.put("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)"); 
        return hashMap; 
    } 
 
    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) { 
        String str3 = "vod_remarks"; 
        String str4 = "vod_pic"; 
        String str5 = "vod_name"; 
        String str6 = "pagecount"; 
        String str7 = "total"; 
        String str8 = "vod_id"; 
        String str9 = "page"; 
        String str10 = "list"; 
        try { 
            String str11 = "http://vipmv.tv/api.php/Kdtv/vod/?ac=list"; 
            if (!hashMap.containsKey("class")) { 
                StringBuilder stringBuilder = new StringBuilder(); 
                stringBuilder.append(str11); 
                stringBuilder.append("&class="); 
                stringBuilder.append(URLEncoder.encode(str)); 
                str11 = stringBuilder.toString(); 
            } 
            for (String str12 : hashMap.keySet()) { 
                String trim = ((String) hashMap.get(str12)).trim(); 
                if (trim.length() != 0) { 
                    StringBuilder stringBuilder2 = new StringBuilder(); 
                    stringBuilder2.append(str11); 
                    stringBuilder2.append("&"); 
                    stringBuilder2.append(str12); 
                    stringBuilder2.append("="); 
                    stringBuilder2.append(URLEncoder.encode(trim)); 
                    str11 = stringBuilder2.toString(); 
                } 
            } 
            StringBuilder stringBuilder3 = new StringBuilder(); 
            stringBuilder3.append(str11); 
            stringBuilder3.append("&page="); 
            stringBuilder3.append(str2); 
            str = stringBuilder3.toString(); 
            JSONObject jSONObject = new JSONObject(k(SpiderReq.get(new SpiderUrl(str, q1(str))).content)); 
            JSONArray jSONArray = jSONObject.getJSONArray(str10); 
            JSONArray jSONArray2 = new JSONArray(); 
            for (int i = 0; i < jSONArray.length(); i++) { 
                JSONObject jSONObject2 = jSONArray.getJSONObject(i); 
                JSONObject jSONObject3 = new JSONObject(); 
                jSONObject3.put(str8, jSONObject2.getString(str8)); 
                jSONObject3.put(str5, jSONObject2.getString(str5)); 
                jSONObject3.put(str4, jSONObject2.getString(str4)); 
                jSONObject3.put(str3, jSONObject2.getString(str3)); 
                jSONArray2.put(jSONObject3); 
            } 
            JSONObject jSONObject4 = new JSONObject(); 
            int i2 = jSONObject.getInt(str9); 
            int i3 = jSONObject.getInt(str7); 
            int i4 = jSONObject.getInt(str6); 
            jSONObject4.put(str9, i2); 
            jSONObject4.put(str6, i4); 
            jSONObject4.put("limit", 20); 
            jSONObject4.put(str7, i3); 
            jSONObject4.put(str10, jSONArray2); 
            return jSONObject4.toString(); 
        } catch (Exception e) { 
            SpiderDebug.log(e); 
            return ""; 
        } 
    } 
 
    public String detailContent(List<String> list) { 
        String str = "vod_play_url"; 
        String str2 = "vod_play_from"; 
        String str3 = "vod_content"; 
        String str4 = "vod_director"; 
        String str5 = "vod_actor"; 
        String str6 = "vod_remarks"; 
        String str7 = "vod_area"; 
        String str8 = "vod_year"; 
        String str9 = "vod_pic"; 
        String str10 = "vod_name"; 
        String str11 = "vod_id"; 
        String str12 = "list"; 
        try { 
            StringBuilder stringBuilder = new StringBuilder(); 
            stringBuilder.append("http://vipmv.tv/api.php/Kdtv/vod/?ac=detail&ids="); 
            stringBuilder.append((String) list.get(0)); 
            String stringBuilder2 = stringBuilder.toString(); 
            JSONArray jSONArray = new JSONObject(k(SpiderReq.get(new SpiderUrl(stringBuilder2, q1(stringBuilder2))).content)).getJSONArray(str12); 
            JSONObject jSONObject = new JSONObject(); 
            JSONArray jSONArray2 = new JSONArray(); 
            String str13 = str12; 
            int i = 0; 
            while (i < jSONArray.length()) { 
                JSONObject jSONObject2 = jSONArray.getJSONObject(i); 
                JSONArray jSONArray3 = jSONArray; 
                JSONObject jSONObject3 = new JSONObject(); 
                JSONObject jSONObject4 = jSONObject; 
                jSONObject3.put(str11, jSONObject2.getString(str11)); 
                jSONObject3.put(str10, jSONObject2.getString(str10)); 
                jSONObject3.put(str9, jSONObject2.getString(str9)); 
                String str14 = str9; 
                jSONObject3.put("type_name", jSONObject2.getString("vod_class")); 
                jSONObject3.put(str8, jSONObject2.getString(str8)); 
                jSONObject3.put(str7, jSONObject2.getString(str7)); 
                jSONObject3.put(str6, jSONObject2.getString(str6)); 
                jSONObject3.put(str5, jSONObject2.getString(str5)); 
                jSONObject3.put(str4, jSONObject2.getString(str4)); 
                jSONObject3.put(str3, jSONObject2.getString(str3)); 
                jSONObject3.put(str2, jSONObject2.getString(str2)); 
                jSONObject3.put(str, jSONObject2.getString(str)); 
                jSONArray2.put(jSONObject3); 
                i++; 
                jSONArray = jSONArray3; 
                jSONObject = jSONObject4; 
                str9 = str14; 
            } 
            JSONObject jSONObject5 = jSONObject; 
            jSONObject5.put(str13, jSONArray2); 
            return jSONObject5.toString(); 
        } catch (Exception e) { 
            SpiderDebug.log(e); 
            return ""; 
        } 
    } 
 
    /* JADX WARNING: Missing block: B:37:0x00c5, code skipped: 
            r4 = -1; 
     */ 
    /* JADX WARNING: Missing block: B:38:0x00c6, code skipped: 
            if (r4 == 0) goto L_0x00dd; 
     */ 
    /* JADX WARNING: Missing block: B:39:0x00c8, code skipped: 
            r18 = r7; 
     */ 
    /* JADX WARNING: Missing block: B:40:0x00cb, code skipped: 
            if (r4 == 1) goto L_0x00da; 
     */ 
    /* JADX WARNING: Missing block: B:42:0x00ce, code skipped: 
            if (r4 == 2) goto L_0x00d7; 
     */ 
    /* JADX WARNING: Missing block: B:44:0x00d1, code skipped: 
            if (r4 == 3) goto L_0x00d4; 
     */ 
    /* JADX WARNING: Missing block: B:46:?, code skipped: 
            r16 = "year"; 
     */ 
    /* JADX WARNING: Missing block: B:47:0x00d7, code skipped: 
            r16 = "lang"; 
     */ 
    /* JADX WARNING: Missing block: B:48:0x00da, code skipped: 
            r16 = "area"; 
     */ 
    /* JADX WARNING: Missing block: B:49:0x00dd, code skipped: 
            r18 = r7; 
            r4 = r1; 
     */ 
    /* JADX WARNING: Missing block: B:75:0x016e, code skipped: 
            r0 = e; 
     */ 
    public java.lang.String homeContent(boolean r21) { 
        /* 
        r20 = this; 
        r1 = r20; 
        r2 = "v"; 
        r3 = "n"; 
        r4 = "field"; 
        r5 = "name"; 
        r6 = ""; 
        r0 = new com.github.catvod.crawler.SpiderUrl;	 Catch:{ Exception -> 0x019a } 
        r7 = "http://vipmv.tv/api.php/Kdtv/vod/?ac=flitter"; 
        r8 = "http://vipmv.tv/api.php/Kdtv/vod/"; 
        r8 = r1.q1(r8);	 Catch:{ Exception -> 0x019a } 
        r0.<init>(r7, r8);	 Catch:{ Exception -> 0x019a } 
        r0 = com.github.catvod.crawler.SpiderReq.get(r0);	 Catch:{ Exception -> 0x019a } 
        r7 = new org.json.JSONObject;	 Catch:{ Exception -> 0x019a } 
        r0 = r0.content;	 Catch:{ Exception -> 0x019a } 
        r7.<init>(r0);	 Catch:{ Exception -> 0x019a } 
        r8 = r7.keys();	 Catch:{ Exception -> 0x019a } 
        r9 = new org.json.JSONArray;	 Catch:{ Exception -> 0x019a } 
        r9.<init>();	 Catch:{ Exception -> 0x019a } 
        r10 = new org.json.JSONObject;	 Catch:{ Exception -> 0x019a } 
        r10.<init>();	 Catch:{ Exception -> 0x019a } 
    L_0x0032: 
        r0 = r8.hasNext();	 Catch:{ Exception -> 0x019a } 
        if (r0 == 0) goto L_0x0184; 
    L_0x0038: 
        r0 = r8.next();	 Catch:{ Exception -> 0x019a } 
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x019a } 
        r11 = "classify"; 
        r11 = r0.startsWith(r11);	 Catch:{ Exception -> 0x019a } 
        if (r11 == 0) goto L_0x0047; 
    L_0x0046: 
        goto L_0x0032; 
    L_0x0047: 
        r11 = r1.k;	 Catch:{ Exception -> 0x019a } 
        r11 = r11.containsKey(r0);	 Catch:{ Exception -> 0x019a } 
        if (r11 == 0) goto L_0x0180; 
    L_0x004f: 
        r11 = r1.k;	 Catch:{ Exception -> 0x019a } 
        r11 = r11.get(r0);	 Catch:{ Exception -> 0x019a } 
        r11 = (java.lang.String) r11;	 Catch:{ Exception -> 0x019a } 
        r12 = new org.json.JSONObject;	 Catch:{ Exception -> 0x019a } 
        r12.<init>();	 Catch:{ Exception -> 0x019a } 
        r13 = "type_id"; 
        r12.put(r13, r0);	 Catch:{ Exception -> 0x019a } 
        r13 = "type_name"; 
        r12.put(r13, r11);	 Catch:{ Exception -> 0x019a } 
        r9.put(r12);	 Catch:{ Exception -> 0x019a } 
        r11 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0170 } 
        r11.<init>();	 Catch:{ Exception -> 0x0170 } 
        r12 = r7.getJSONArray(r0);	 Catch:{ Exception -> 0x0170 } 
        r14 = 0; 
    L_0x0073: 
        r15 = r12.length();	 Catch:{ Exception -> 0x0170 } 
        if (r14 >= r15) goto L_0x0160; 
    L_0x0079: 
        r15 = r12.getJSONObject(r14);	 Catch:{ Exception -> 0x0170 } 
        r13 = r15.getString(r5);	 Catch:{ Exception -> 0x0170 } 
        r16 = 0; 
        r17 = r15.has(r4);	 Catch:{ Exception -> 0x0170 } 
        if (r17 == 0) goto L_0x008d; 
    L_0x0089: 
        r16 = r15.getString(r4);	 Catch:{ Exception -> 0x0170 } 
    L_0x008d: 
        r1 = "type"; 
        r17 = r4; 
        if (r16 != 0) goto L_0x00e4; 
    L_0x0093: 
        r18 = -1; 
        r19 = r13.hashCode();	 Catch:{ Exception -> 0x00e1 } 
        switch(r19) { 
            case 713226: goto L_0x00bb; 
            case 769801: goto L_0x00b1; 
            case 1010288: goto L_0x00a7; 
            case 1145779: goto L_0x009d; 
            default: goto L_0x009c; 
        };	 Catch:{ Exception -> 0x00e1 } 
    L_0x009c: 
        goto L_0x00c5; 
    L_0x009d: 
        r4 = "语言"; 
        r4 = r13.equals(r4);	 Catch:{ Exception -> 0x00e1 } 
        if (r4 == 0) goto L_0x00c5; 
    L_0x00a5: 
        r4 = 2; 
        goto L_0x00c6; 
    L_0x00a7: 
        r4 = "类型"; 
        r4 = r13.equals(r4);	 Catch:{ Exception -> 0x00e1 } 
        if (r4 == 0) goto L_0x00c5; 
    L_0x00af: 
        r4 = 0; 
        goto L_0x00c6; 
    L_0x00b1: 
        r4 = "年份"; 
        r4 = r13.equals(r4);	 Catch:{ Exception -> 0x00e1 } 
        if (r4 == 0) goto L_0x00c5; 
    L_0x00b9: 
        r4 = 3; 
        goto L_0x00c6; 
    L_0x00bb: 
        r4 = "地区"; 
        r4 = r13.equals(r4);	 Catch:{ Exception -> 0x00e1 } 
        if (r4 == 0) goto L_0x00c5; 
    L_0x00c3: 
        r4 = 1; 
        goto L_0x00c6; 
    L_0x00c5: 
        r4 = -1; 
    L_0x00c6: 
        if (r4 == 0) goto L_0x00dd; 
    L_0x00c8: 
        r18 = r7; 
        r7 = 1; 
        if (r4 == r7) goto L_0x00da; 
    L_0x00cd: 
        r7 = 2; 
        if (r4 == r7) goto L_0x00d7; 
    L_0x00d0: 
        r7 = 3; 
        if (r4 == r7) goto L_0x00d4; 
    L_0x00d3: 
        goto L_0x00e6; 
    L_0x00d4: 
        r16 = "year"; 
        goto L_0x00e6; 
    L_0x00d7: 
        r16 = "lang"; 
        goto L_0x00e6; 
    L_0x00da: 
        r16 = "area"; 
        goto L_0x00e6; 
    L_0x00dd: 
        r18 = r7; 
        r4 = r1; 
        goto L_0x00e8; 
    L_0x00e1: 
        r0 = move-exception; 
        goto L_0x0173; 
    L_0x00e4: 
        r18 = r7; 
    L_0x00e6: 
        r4 = r16; 
    L_0x00e8: 
        if (r4 != 0) goto L_0x00ee; 
    L_0x00ea: 
        com.github.catvod.crawler.SpiderDebug.log(r13);	 Catch:{ Exception -> 0x016e } 
        goto L_0x0156; 
    L_0x00ee: 
        r1 = r15.getString(r1);	 Catch:{ Exception -> 0x016e } 
        r7 = ","; 
        r1 = r1.split(r7);	 Catch:{ Exception -> 0x016e } 
        r7 = r1.length;	 Catch:{ Exception -> 0x016e } 
        if (r7 != 0) goto L_0x00fc; 
    L_0x00fb: 
        goto L_0x0156; 
    L_0x00fc: 
        r7 = new org.json.JSONObject;	 Catch:{ Exception -> 0x016e } 
        r7.<init>();	 Catch:{ Exception -> 0x016e } 
        r15 = "key"; 
        r7.put(r15, r4);	 Catch:{ Exception -> 0x016e } 
        r7.put(r5, r13);	 Catch:{ Exception -> 0x016e } 
        r4 = new org.json.JSONArray;	 Catch:{ Exception -> 0x016e } 
        r4.<init>();	 Catch:{ Exception -> 0x016e } 
        r13 = new org.json.JSONObject;	 Catch:{ Exception -> 0x016e } 
        r13.<init>();	 Catch:{ Exception -> 0x016e } 
        r15 = "全部"; 
        r13.put(r3, r15);	 Catch:{ Exception -> 0x016e } 
        r13.put(r2, r6);	 Catch:{ Exception -> 0x016e } 
        r4.put(r13);	 Catch:{ Exception -> 0x016e } 
        r13 = 0; 
    L_0x011f: 
        r15 = r1.length;	 Catch:{ Exception -> 0x016e } 
        if (r13 >= r15) goto L_0x0146; 
    L_0x0122: 
        r15 = r1[r13];	 Catch:{ Exception -> 0x016e } 
        r16 = r15.trim();	 Catch:{ Exception -> 0x016e } 
        r16 = r16.length();	 Catch:{ Exception -> 0x016e } 
        if (r16 != 0) goto L_0x0131; 
    L_0x012e: 
        r16 = r1; 
        goto L_0x0141; 
    L_0x0131: 
        r16 = r1; 
        r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x016e } 
        r1.<init>();	 Catch:{ Exception -> 0x016e } 
        r1.put(r3, r15);	 Catch:{ Exception -> 0x016e } 
        r1.put(r2, r15);	 Catch:{ Exception -> 0x016e } 
        r4.put(r1);	 Catch:{ Exception -> 0x016e } 
    L_0x0141: 
        r13 = r13 + 1; 
        r1 = r16; 
        goto L_0x011f; 
    L_0x0146: 
        r1 = r4.length();	 Catch:{ Exception -> 0x016e } 
        r13 = 1; 
        if (r1 != r13) goto L_0x014e; 
    L_0x014d: 
        goto L_0x0156; 
    L_0x014e: 
        r1 = "value"; 
        r7.put(r1, r4);	 Catch:{ Exception -> 0x016e } 
        r11.put(r7);	 Catch:{ Exception -> 0x016e } 
    L_0x0156: 
        r14 = r14 + 1; 
        r1 = r20; 
        r4 = r17; 
        r7 = r18; 
        goto L_0x0073; 
    L_0x0160: 
        r17 = r4; 
        r18 = r7; 
        r1 = r11.length();	 Catch:{ Exception -> 0x016e } 
        if (r1 <= 0) goto L_0x0178; 
    L_0x016a: 
        r10.put(r0, r11);	 Catch:{ Exception -> 0x016e } 
        goto L_0x0178; 
    L_0x016e: 
        r0 = move-exception; 
        goto L_0x0175; 
    L_0x0170: 
        r0 = move-exception; 
        r17 = r4; 
    L_0x0173: 
        r18 = r7; 
    L_0x0175: 
        com.github.catvod.crawler.SpiderDebug.log(r0);	 Catch:{ Exception -> 0x019a } 
    L_0x0178: 
        r1 = r20; 
        r4 = r17; 
        r7 = r18; 
        goto L_0x0032; 
    L_0x0180: 
        r1 = r20; 
        goto L_0x0032; 
    L_0x0184: 
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x019a } 
        r0.<init>();	 Catch:{ Exception -> 0x019a } 
        r1 = "class"; 
        r0.put(r1, r9);	 Catch:{ Exception -> 0x019a } 
        if (r21 == 0) goto L_0x0195; 
    L_0x0190: 
        r1 = "filters"; 
        r0.put(r1, r10);	 Catch:{ Exception -> 0x019a } 
    L_0x0195: 
        r0 = r0.toString();	 Catch:{ Exception -> 0x019a } 
        return r0; 
    L_0x019a: 
        r0 = move-exception; 
        com.github.catvod.crawler.SpiderDebug.log(r0); 
        return r6; 
        */ 
        throw new UnsupportedOperationException("Method not decompiled: com.github.catvod.spider.Zjdr.homeContent(boolean):java.lang.String"); 
    } 
 
    public String homeVideoContent() { 
        String str = "vod_remarks"; 
        String str2 = "vod_pic"; 
        String str3 = "vod_name"; 
        String str4 = "vod_id"; 
        String str5 = "list"; 
        try { 
            String str6 = "http://vipmv.tv/api.php/Kdtv/vod/?ac=list&page=1"; 
            JSONArray jSONArray = new JSONObject(k(SpiderReq.get(new SpiderUrl(str6, q1(str6))).content)).getJSONArray(str5); 
            JSONArray jSONArray2 = new JSONArray(); 
            for (int i = 0; i < jSONArray.length(); i++) { 
                JSONObject jSONObject = jSONArray.getJSONObject(i); 
                JSONObject jSONObject2 = new JSONObject(); 
                jSONObject2.put(str4, jSONObject.getString(str4)); 
                jSONObject2.put(str3, jSONObject.getString(str3)); 
                jSONObject2.put(str2, jSONObject.getString(str2)); 
                jSONObject2.put(str, jSONObject.getString(str)); 
                jSONArray2.put(jSONObject2); 
            } 
            JSONObject jSONObject3 = new JSONObject(); 
            jSONObject3.put(str5, jSONArray2); 
            return jSONObject3.toString(); 
        } catch (Exception e) { 
            SpiderDebug.log(e); 
            return ""; 
        } 
    } 
 
    public void init(Context context) { 
        super.init(context); 
        this.k.put("豆瓣高分电影", "电影"); 
        this.k.put("豆瓣高分电视剧", "连续剧"); 
        this.k.put("豆瓣高分综艺", "综艺"); 
        this.k.put("豆瓣高分动漫", "动漫"); 
        try { 
            StringBuilder stringBuilder = new StringBuilder(); 
            stringBuilder.append("http://vipmv.co/static/js/playerconfig.js?t="); 
            stringBuilder.append(new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()))); 
            String stringBuilder2 = stringBuilder.toString(); 
            Matcher matcher = this.iM.matcher(SpiderReq.get(new SpiderUrl(stringBuilder2, q1(stringBuilder2))).content.trim()); 
            if (matcher.find()) { 
                JSONObject jSONObject = new JSONObject(matcher.group(1).trim()); 
                Iterator keys = jSONObject.keys(); 
                while (keys.hasNext()) { 
                    String str = (String) keys.next(); 
                    String string = jSONObject.getJSONObject(str).getString("show"); 
                    String string2 = jSONObject.getJSONObject(str).getString("parse"); 
                    String string3 = jSONObject.getJSONObject(str).getString("ps"); 
                    JSONObject jSONObject2 = new JSONObject(); 
                    jSONObject2.put("sh", string); 
                    jSONObject2.put("pu", string2); 
                    jSONObject2.put("sn", string3); 
                    jSONObject2.put("or", 999); 
                    this.eC.put(str, jSONObject2); 
                } 
            } 
        } catch (Exception e) { 
            SpiderDebug.log(e); 
        } 
    } 
 
    /* Access modifiers changed, original: protected */ 
    public String k(String str) { 
        return m1747RC4(str, m1983("/")); 
    } 
 
    public String playerContent(String str, String str2, List<String> list) { 
        String str3 = "pu"; 
        JSONObject jSONObject = new JSONObject(); 
        try { 
            if (this.eC.has(str)) { 
                JSONObject jSONObject2 = this.eC.getJSONObject(str); 
                jSONObject.put("parse", jSONObject2.getInt("sn")); 
                jSONObject.put("playUrl", jSONObject2.getString(str3)); 
                jSONObject.put("url", str2); 
                HashMap hashMap = new HashMap(); 
                hashMap.put("Connection", "Keep-Alive"); 
                hashMap.put("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)"); 
                StringBuilder stringBuilder = new StringBuilder(); 
                stringBuilder.append(jSONObject2.getString(str3)); 
                stringBuilder.append(str2); 
                hashMap.put("referer", stringBuilder.toString()); 
                jSONObject.put("header", hashMap.toString()); 
            } 
            return jSONObject.toString(); 
        } catch (Exception e) { 
            SpiderDebug.log(e); 
            return ""; 
        } 
    } 
 
    public String searchContent(String str, boolean z) { 
        String str2 = "vod_remarks"; 
        String str3 = "vod_pic"; 
        String str4 = "vod_name"; 
        String str5 = "vod_id"; 
        String str6 = "list"; 
        String str7 = ""; 
        if (z) { 
            return str7; 
        } 
        try { 
            StringBuilder stringBuilder = new StringBuilder(); 
            stringBuilder.append("http://vipmv.tv/api.php/Kdtv/vod/?ac=list&zm="); 
            stringBuilder.append(URLEncoder.encode(str)); 
            stringBuilder.append("&page=1"); 
            str = stringBuilder.toString(); 
            JSONArray jSONArray = new JSONObject(k(SpiderReq.get(new SpiderUrl(str, q1(str))).content)).getJSONArray(str6); 
            JSONArray jSONArray2 = new JSONArray(); 
            for (int i = 0; i < jSONArray.length(); i++) { 
                JSONObject jSONObject = jSONArray.getJSONObject(i); 
                JSONObject jSONObject2 = new JSONObject(); 
                jSONObject2.put(str5, jSONObject.getString(str5)); 
                jSONObject2.put(str4, jSONObject.getString(str4)); 
                jSONObject2.put(str3, jSONObject.getString(str3)); 
                jSONObject2.put(str2, jSONObject.getString(str2)); 
                jSONArray2.put(jSONObject2); 
            } 
            JSONObject jSONObject3 = new JSONObject(); 
            jSONObject3.put(str6, jSONArray2); 
            return jSONObject3.toString(); 
        } catch (Exception e) { 
            SpiderDebug.log(e); 
            return str7; 
        } 
    } 
}
