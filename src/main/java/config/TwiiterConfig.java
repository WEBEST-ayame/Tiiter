package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.twiiter.base.service.TwiiterService;


@Configuration
@EnableWebSecurity
public class TwiiterConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private TwiiterService twiiterService;

    //フォームの値と比較するDBから取得したパスワードは暗号化されているのでフォームの値も暗号化するために利用
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエスト設定
        // 静的リソース(images、css、javascript)に対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers(
                            "/images/**",
                            "/css/**",
                            "/javascript/**",
                            "/webjars/**"
                            );
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 認可の設定
        http.authorizeRequests()
        	// ログイン画面とユーザー登録は全ユーザーアクセス許可
            .antMatchers("/twiiter/login","/twiiter/registration").permitAll() 
            .anyRequest().authenticated();  // それ以外は全て認証無しの場合アクセス不許可

        // ログイン設定
        http.formLogin()
            .loginProcessingUrl("/twiiter/sign_in")   // 認証処理のパス
            .loginPage("/twiiter/login")            // ログインフォームのパス
            .defaultSuccessUrl("/twiiter/twiit")     // 認証成功時の遷移先
            .failureUrl("/login?error")
            .usernameParameter("user_name")
            .passwordParameter("password")  // ユーザー名、パスワードのパラメータ名
            .and();

        // ログアウト設定
        http.logout()
	        .logoutUrl("/twiiter/logout")
	        .logoutSuccessUrl("/twiiter/login?logout")
	        .permitAll();                                      // ログアウト完了時のパス

    }
    
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
            .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER");
    }

}
