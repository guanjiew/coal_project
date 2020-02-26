package com.dream.ccms.authorization;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenManager {
	public static final long EXPIRATION_TIME = 36_000_0L; // 10 hour
	public static final String SECRET = "ThisIsASecret";// please change to your own encryption secret.
	private static String JWT_SECRET = "asdfghjkl1234567890";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String USER_NAME = "userName";

	public static String generateToken(String userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		String jwt = Jwts.builder().setClaims(map).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		return "Bearer " + jwt;
	}

	public static String createToken(String userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		JwtBuilder builder = Jwts.builder()
				/*
				 * //如果有私有声明，一定要先设置这个自己创建的私有的声明， 这个是给builder的claim赋值，
				 * 一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
				 */
				.setClaims(map)
				.setIssuedAt(new Date()) // jwt的签发时间
				.signWith(signatureAlgorithm, SECRET)// 设置签名使用的签名算法和签名使用的秘钥
				// 设置过期时间戳
				.setExpiration((new Date(System.currentTimeMillis() + EXPIRATION_TIME)));
		return "Bearer " + builder.compact();
	}
	

	public static Claims parseJWT(String jwt) throws Exception {
		Claims claims = Jwts
				.parser()
				.setSigningKey(SECRET)
				.parseClaimsJwt(jwt)
				.getBody();
	
		return claims;
	}

	private static SecretKey generalKey() {

		// StringKey
		String stringKey = JWT_SECRET;
		// 使用base64解码
		byte[] encodedKey = Base64.decodeBase64(stringKey);
		// 根据给定的字节数组使用AES加密算法构造一个密钥
		SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return secretKey;

	}

	
}
