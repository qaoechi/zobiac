package com.jumjari.zobiac.application.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.domain.member.OauthAccount;
import com.jumjari.zobiac.domain.member.OauthAccountRepository;
import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.domain.member.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuthUserService {
    private final UserRepository userRepository;
    private final OauthAccountRepository oauthRepository;

    public User findORCreate(String provider, String prividerId) {
        return oauthRepository.findByProviderAndProviderId(provider, prividerId)
            .map(OauthAccount::getUser)
            .orElseGet(() -> {
                User user = userRepository.save(User.create());
                OauthAccount account = OauthAccount.create(user, provider, provider);
                oauthRepository.save(account);

                return userRepository.save(user);
            });
    }
}