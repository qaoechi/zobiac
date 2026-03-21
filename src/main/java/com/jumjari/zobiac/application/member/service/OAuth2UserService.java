package com.jumjari.zobiac.application.member.service;

import java.util.Optional;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.Member;
import com.jumjari.zobiac.domain.member.entity.OauthAccount;
import com.jumjari.zobiac.domain.member.entity.User;
import com.jumjari.zobiac.domain.member.repository.OauthAccountRepository;
import com.jumjari.zobiac.domain.member.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final OauthAccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(request);

        String provider = request.getClientRegistration().getRegistrationId();
        String providerId = oauth2User.getAttribute("id").toString();

        Optional<OauthAccount> optionalAccount = accountRepository.findByProviderAndProviderId(provider, providerId);
        User user;
        if (optionalAccount.isPresent()) {
            user = optionalAccount.get().getUser();
        } else {
            user = User.create();
            userRepository.save(user);
            OauthAccount account = OauthAccount.create(user, provider, providerId);
            accountRepository.save(account);
        }

        return new Member(user, oauth2User.getAttributes());
    }
}