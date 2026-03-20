package com.pragmafood.talentpool.square.domain.clients;

public interface UserClientPort {

    boolean hasOwnerRole(Long userId);
}
