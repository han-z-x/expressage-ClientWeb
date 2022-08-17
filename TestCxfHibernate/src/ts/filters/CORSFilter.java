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
         //����ӵĲ���
         httpResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
         httpResponse.addHeader("Access-Control-Allow-Credentials", "true");//�������ǰ��������СдҲ�ǲ��еģ���ҵ��Сд��д�Ͼ�û������
         httpResponse.addHeader("Access-Control-Allow-Headers", "access-control-allow-origin,content-type,x-requested-with,Content-Type,Access-Control-Allow-Headers,Content-Length,Accept,Authorization,X-Requested-With");
         //����ӵĽ�β
         filterChain.doFilter(servletRequest, servletResponse);
     }
 
     @Override
     public  void  destroy() {
 
     }
}