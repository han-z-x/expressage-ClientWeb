package  ts.filters;
 
import  javax.servlet.*;
import  javax.servlet.http.HttpServletResponse;
import  java.io.IOException;
 
public  class  CORSFilter  implements  Filter {
 
     @Override
     public  void  init(FilterConfig filterConfig)  throws  ServletException {
 
     }
 
     @Override
     public  void  doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)  throws  IOException, ServletException {
         HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
         httpResponse.addHeader( "Access-Control-Allow-Origin" ,  "*" );
         //新添加的部分
         httpResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
         httpResponse.addHeader("Access-Control-Allow-Credentials", "true");//这里如果前端请求是小写也是不行的，锁业大小写都写上就没问题了
         httpResponse.addHeader("Access-Control-Allow-Headers", "access-control-allow-origin,content-type,x-requested-with,Content-Type,Access-Control-Allow-Headers,Content-Length,Accept,Authorization,X-Requested-With");
         //新添加的结尾
         filterChain.doFilter(servletRequest, servletResponse);
     }
 
     @Override
     public  void  destroy() {
 
     }
}