package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000116693890";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCCD4XvFWGYWdgGqoTyItPg2kxnd/5Ak+r2hUj6FMBY2+y65vV5nalL12jnraGH4aqiYSjyAfD3rldj1AiprdcKK6AYVV/2WUAjKxRwUUfW6v/ZeQuNFYDzaKomqKdfadfXiIi6fRAU3/+smPj7Q5KejSi16P3b0greyvkrHmV/jn9YL8L9CPvguisvH+pUT/1d9HUa9qKPpia7kbnID8tdFOZk24epCrivBk8gzIUzlZS8p1zbz7Ww1GraqMR2zwLt52UADWu3tOWuVtu+65Thi0Jd8ai6BZzn6a0xzWbqvbA41+C57BIHfwF8fJfXnRNhGC0OFvy983Bkif3fSCT5AgMBAAECggEAEM0UjigC7X1drK7GcckR+c/5Yrxbedgh2CqrnLKljlUKpbJEL6uwPP3crWD+puD9++/1LB4YFMbOvzSa0eCFEOlFyx7sQJSS9ngQMdouH5NH7b33QghvGgKBSHEs/eRcX5lhoEWbmDa/fnpcX4kBHb46bhVlBcqCYPlzaqCImAuPPnAbBRwRtV2FKv3DpAyORqbKgDUkfJTAojfiyFbAFqEiHL+z+JLTnA25xicjT+9aPLtQ0WZnfS+OXu519xG7lU6HWHWXA6XrqTjLBdrjWjLFYmqIGmJpxTAL7UMc8nncTxQXQTNcghoBERHVH3Rk/Df6EJcCVJfP+m0Zs9jTAQKBgQC4TjW2oOluwzZdVhMLpj9P8MWuUamJ3TRrBoP6BsEvHokm+U6KMZdbl+VNGkm+DlaCf4FYoOEra7VC6c8JKdv4XstJYRK49ZD48EOkJ1yk6zgvu4lnZjnQkqrc90ijYdUjq7hslOP2oJNftYJ5CT/FgFBqH/zhnTAZj/+xqD00MQKBgQC0p2soM/5yCeISad1vxTjTETmgp869FRsIXOw2424qoFxuoErd457H3dEP8Fk/ChJvEZ5RvYaNjFUN+zo55MGclH0ncdXDOy/7CoEoRY3P79mGtvjXKYHE94LghJJdZrbfnljGqDAm2TT0mTqkyOAp4CV+FwM3vugXkFmMmDazSQKBgGv+2BaVn3MZzEdQ4oQW/E5ZZVxJ2bBBADAed0MQpi54FCHUQCNV/7zZ+rD9UGyfdi2tjRtj2c1ZpbL50ikqcAzPbwsmaJQcwMA9+US9E9pHQnslxtPh4MrIxnoIJHYoI35tZvmpHRMs8G5Kq5NEyitujrBA4itJsh+oc0mkrYhhAoGBAJgscrph2dLr+lAydwIgqrY4y0dGM4LZFXVc+OlfeLxVaqcRRR9WDrkjY2i+uB6++DoxKCCIup/fdwIY/rontOOVvTsNy9fxsoAQIC3xMPzYeG7fEAwGfbaSG9/OLJCa5i7F7tggcrc2vkHQFTMbtp/vnQVykxZRLKY0xQoGagVpAoGBAKlc88NQ8RFL5CjO6d7ZlqzlAoORtCT1+cbg/sv1nR8CerYt53cRSDQ3AtbnmxFywl/u89+OVM5fUGrADT2Iro6oBQX0s+cu7UsB5ccJPoefHzMlEV5lFFmMR3lJwemdpnDmpqJ+6xFYjcR+eZ7FwFLi3jOOALvyNMY4vnIQ0iUq";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo7kxjlliIcYQlPRJ861+pKMXq9Ss0dKbslVIiKC5LJXgBiryXa117cNDI3RgflhisW/TjcjLsqGGFEMmNsVvzaI3TS5nQmeZVRRKY1zjgoxdTLer375ycW1rAcSdK5nZ38RYX+UILuckAaG0KBiOvdTCBE9dir5lMw+CMKrpzL/KzlMDINakN2bMavapexZF/RB+A4mya/pPBcJ+tcbEyg5Q4zg7FjLp1/5+l+deZTpNSnWxOQ4wWw+87KHeuWvC7X5jV/FQQOPROyCmuAvpObMEV955fBeTWf0M0sY5DnqqxBP/VIy4IPFl/nAkQ+IyIxN1nBf07wWUld6g/EB3hQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/learn/notify_url.jsp";
//alipay.trade.page.pay-JAVA-UTF-8
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/learn/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

