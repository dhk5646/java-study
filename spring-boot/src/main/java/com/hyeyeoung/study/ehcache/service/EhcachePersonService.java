package com.hyeyeoung.study.ehcache.service;

import com.hyeyeoung.study.ehcache.constants.EhcacheConfigurationConstants;
import com.hyeyeoung.study.ehcache.entity.Person;
import com.hyeyeoung.study.ehcache.repository.EhcachePersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EhcachePersonService {

    private final EhcachePersonRepository ehcachePersonRepository;

    public Person selectPerson(String id) {
        return ehcachePersonRepository.selectPerson(id);
    }

    // @Cacheable을 사용하여 캐시에서 값을 가져오는 메서드
    @Cacheable(cacheNames= EhcacheConfigurationConstants.PERSON_CACHE, key = "#id")
    public Person selectPersonFromCache(String id) {
        return ehcachePersonRepository.selectPerson(id);
    }

    // @CachePut을 사용하여 캐시에 값을 추가 또는 갱신하는 메서드
    @CachePut(cacheNames= EhcacheConfigurationConstants.PERSON_CACHE, key = "#id")
    public Person updatePersonInCache(String id, String newName) {
        Person person = this.selectPerson(id); // selectPersonFromCache 호출 하지 않는 이유는, 프록시 객체의 경우 self-invocation 이 발생하여 @Cacheable AOP 가 동작하지 않음.
        person.updateName(newName);

        return person;
    }

    // @CacheEvict를 사용하여 캐시에서 값을 삭제하는 메서드
    @CacheEvict(cacheNames= EhcacheConfigurationConstants.PERSON_CACHE, key = "#id")
    public void deletePersonFromCache(String id) {
        ehcachePersonRepository.deletePerson(id);
    }
}
