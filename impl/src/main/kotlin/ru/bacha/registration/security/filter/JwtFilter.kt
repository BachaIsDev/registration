package ru.bacha.registration.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.bacha.registration.security.CustomUserService
import ru.bacha.registration.security.JwtService

@Component
class JwtFilter(
    private val jwtService: JwtService,
    private val userService: CustomUserService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getTokenFromRequest(request)
        if (token != null && jwtService.validateJwtToken(token)) {
            setCustomUserDetailsToSecurityContextHolder(token)
        }
        filterChain.doFilter(request, response)
    }

    private fun setCustomUserDetailsToSecurityContextHolder(token: String) {
        val email = jwtService.getEmailFromToken(token)
        val customUserDetails = userService.loadUserByUsername(email)
        val authentication = UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.authorities)
        SecurityContextHolder.getContext().authentication = authentication
    }

    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }

        return null
    }

}