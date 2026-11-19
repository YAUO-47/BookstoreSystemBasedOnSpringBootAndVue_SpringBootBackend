package cn.tom.config;

import cn.tom.entity.User;
import cn.tom.tool.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthoriztionFilter extends BasicAuthenticationFilter {

    private StringRedisTemplate stringRedisTemplate;

    Logger log2 = LoggerFactory.getLogger(JwtAuthoriztionFilter.class);
    public JwtAuthoriztionFilter(AuthenticationManager authenticationManager,StringRedisTemplate stringRedisTemplate){
        super(authenticationManager);
        this.stringRedisTemplate = stringRedisTemplate;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log2.info("获取头部Token");
        String bearer = request.getHeader("Authorization");
        log2.info("Bearer="+bearer);
        if (bearer == null || bearer.length()<7){
            log2.info("直接放开的,无需Token就可以访问");
            chain.doFilter(request,response);
            return;
        }
        String token = bearer.substring(7);
        log2.info("Token="+token);
        log2.info("验证 Token 有没有被人篡改,过期");
        ValueOperations<String,String> op = stringRedisTemplate.opsForValue();
        if (op.get(token) == null){
            log2.error("Token 被人篡改或者过期,拒绝访问");
            response.setStatus(403);
            response.getWriter().println("Token 被人篡改或者过期,拒绝访问");
            return;
        }
        //Token合格,让 SpringSecurity 去判断角色能干什么
        log2.info("Token合格,让SpringSecurity 去判断角色能干什么");
        log2.info("把角色信息给到 SpringSecurity");
        String username = JwtUtil.getUsername(token);
        log2.info("Token 校验成功 username="+username);
        String role = JwtUtil.getRole(token);
        int id = JwtUtil.getId(token);
        String img = JwtUtil.getImg(token);
        log2.info("Token 校验成功 img="+img);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        User user = new User(username,role,id,img);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
        //authenticationToken.setAuthenticated(true);
        log2.info("放置登录信息=" + authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log2.info("让SpringSecurity 的过滤器去处理权限管理事宜");
        chain.doFilter(request, response);
    }
}
