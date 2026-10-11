package ru.bacha.registration.security

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.bacha.registration.dto.JwtAuthenticationDto
import java.security.Key
import java.security.KeyFactory
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date
import javax.crypto.SecretKey
import kotlin.io.encoding.Base64

@Service
class JwtService(
    @Value("\${app.secret}")
    val jwtSecret: String
) {
    companion object {
        private val logger = LoggerFactory.getLogger(JwtService::class.java)
    }
    fun generateAuthToken(email: String): JwtAuthenticationDto {
        val jwt = JwtAuthenticationDto(generateJwtToken(email), generateRefreshToken(email));
        return jwt
    }

    fun refreshBaseToken(email: String, refreshToken: String): JwtAuthenticationDto {
        val jwt = JwtAuthenticationDto(generateJwtToken(email), refreshToken)
        return jwt
    }

    fun generateJwtToken(email: String): String {
        val date: Date = Date.from(Instant.now().plus(1, ChronoUnit.MINUTES))
        return Jwts.builder()
            .subject(email)
            .expiration(date)
            .signWith(getSignInKey())
            .compact()
    }

    fun generateRefreshToken(email: String): String {
        val date: Date = Date.from(Instant.now().plus(1, ChronoUnit.DAYS))
        return Jwts.builder()
            .subject(email)
            .expiration(date)
            .signWith(getSignInKey())
            .compact()
    }
    fun getEmailFromToken(token: String): String {
        val email = Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
        return email
    }

    fun validateJwtToken(token: String): Boolean {
        try {
            Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .payload

            return true

        } catch (exception: ExpiredJwtException) {
            logger.error("Token expired: ${exception.message}")
        } catch (exception: UnsupportedJwtException) {
            logger.error("Token unsupported: ${exception.message}")
        } catch (exception: MalformedJwtException) {
            logger.error("MalformedJwt exception: ${exception.message}")
        } catch (exception: SecurityException) {
            logger.error("Security exception: ${exception.message}")
        } catch (exception: Exception) {
            logger.error("Token invalid: ${exception.message}")
        }

        return false
    }

    private fun getSignInKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(jwtSecret)
        return Keys.hmacShaKeyFor(jwtSecret.toByteArray())
    }
}