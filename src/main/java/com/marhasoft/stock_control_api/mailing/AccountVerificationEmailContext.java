package com.marhasoft.stock_control_api.mailing;

import com.marhasoft.stock_control_api.models.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmailContext extends AbstractEmailContext {

    private String token;

    @Override
    public <T> void init(T context) {
        Usuario usuario = (Usuario) context;
        put("firstName", usuario.getNome());
        setTemplateLocation("mailing/email-verification");
        setSubject("Complete seu cadastro");
        setFrom("no-reply@marhapro.com");
        setTo(usuario.getEmail());
    }

    public void setToken(String token){
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url = UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/register/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }

}
