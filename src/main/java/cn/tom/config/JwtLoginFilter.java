package cn.tom.config;

import cn.tom.entity.User;
import cn.tom.tool.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JwtLoginFilter  extends UsernamePasswordAuthenticationFilter{


    Logger log2 = LoggerFactory.getLogger(JwtLoginFilter.class);
    private AuthenticationManager authenticationManager;//密码比对器
    private StringRedisTemplate stringRedisTemplate;
    public JwtLoginFilter(AuthenticationManager authenticationManager,StringRedisTemplate stringRedisTemplate){
        this.authenticationManager = authenticationManager;
        this.stringRedisTemplate = stringRedisTemplate;
        super.setFilterProcessesUrl("/api/login");
        //获取 username 和 password 的路径
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        String uname = super.obtainUsername(request);
        String pwd = super.obtainPassword(request);
        String type = request.getParameter("type");
        uname = type+":"+uname;

        log2.info("user="+uname+"pwd="+pwd);
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(uname,pwd);
        return authenticationManager.authenticate(authRequest);
        //1.调用MyUserDetailService.loadUserByUsername(uname)
        //2.成功了:successfulAuthentication 返回JWT  Token
        //3.失败了 unsuccessfulAuthentication 登录失败
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        log2.info("successfulAuthentication 登录成功");
        User user = (User) authResult.getPrincipal();//XUser 对象
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        String roles = "";
        for (GrantedAuthority e : authorities){
            log2.info("e.getAuthority().toString()="+e.getAuthority().toString());
            roles = roles +e.getAuthority().toString();
        }
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println("roles="+roles);
        String token = JwtUtil.createToken(username,roles,user.getId(),user.getImg());
        ValueOperations<String,String> op = stringRedisTemplate.opsForValue();
        ObjectMapper objectMapper = new ObjectMapper();
        op.set(token,objectMapper.writeValueAsString(user),10,TimeUnit.HOURS);
        response.setContentType("application/json");
        response.setStatus(200);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "登录成功");
        map.put("token", token);
        String json = objectMapper.writeValueAsString(map);
        response.getWriter().println(json);
        response.flushBuffer();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        log2.warn("登录失败 ....");
        response.setStatus(401);   // 未授权
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Map<String, Object> map = new HashMap<>();
        map.put("code", 401);
        map.put("msg", failed.getMessage());
        map.put("token", "");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(map);
        response.getWriter().println(json);
        response.flushBuffer();
    }

}
