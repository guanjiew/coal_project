package com.dream.ccms.authorization;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class JTWManager {
	public static final long EXPIRATION_TIME = 36_000_0L; // 10 hour
	public String createToken() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 构建头部信息
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		// 构建密钥信息
		Algorithm algorithm = Algorithm.HMAC256("secret");
		/* 设置 载荷 Payload */
		String token = JWT.create().withHeader(map)
				/* 设置 载荷 Payload */				
				// 签名是有谁生成 例如 服务器
				.withIssuer("issuer")
				// 签名的主题
				.withSubject("subject")
				// 签名的观众 也可以理解谁接受签名的
				.withAudience("audience")
				// 生成签名的时间
				.withIssuedAt(new Date())
				// 签名过期的时间
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				/* 签名 Signature */
				.sign(algorithm);

		return token;
	}

	public String createTokenWithClaim() {
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("alg", "HS256");
			map.put("typ", "JWT");
			Date nowDate = new Date();
			// 2小过期
			// Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0)
			String token = JWT.create()
					.withHeader(map)
					.withClaim("loginName", "lijunkui")
					.withIssuer("SERVICE")// 签名是有谁生成 例如 服务器																												// 
					.withSubject("this is test token")// 签名的主题
					.withAudience("APP")// 签名的观众 也可以理解谁接受签名的
					.withIssuedAt(nowDate) // 生成签名的时间
					// .withExpiresAt(expireDate)//签名过期的时间
					.sign(algorithm);
			return token;
		} catch (JWTCreationException exception) {
			exception.printStackTrace();
		}
		return null;
	}
}
