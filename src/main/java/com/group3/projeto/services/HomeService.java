package com.group3.projeto.services;

import com.group3.projeto.repositories.HomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeService {
    private final HomeRepository homeRepository;

}
